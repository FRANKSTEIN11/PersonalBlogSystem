package com.example.blogplatform.rabbitmq.consumer;

import com.example.blogplatform.utils.ArticleUtil;
import com.example.service.ArticleService;
import com.example.vo.MqKeyAndValueVO;
import com.example.vo.ViewsVO;
import com.rabbitmq.client.Channel;
import com.sso.ssoCore.utils.JedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @author yenanren
 * @date 2022/4/5 0005
 * @Description
 */
@Slf4j
@Service
@RabbitListener(queues = "views.dead.direct.queue")
public class ViewsDeadDirectConsumer {

    @Resource
    private ArticleService articleService;

    @RabbitHandler
    public void receive(MqKeyAndValueVO mqKeyAndValueVO, @Header(AmqpHeaders.DELIVERY_TAG) long tag, Channel channel) {

        log.info("views.dead.direct.queue收到的消息dead: " + mqKeyAndValueVO.toString());
        String redisKey = mqKeyAndValueVO.getRedisKey();
        String redisValue = mqKeyAndValueVO.getRedisValue();

        //如果value相等,那么说明是redis最后一次
        if (redisValue.equals(JedisUtil.getStringValue(redisKey))) {
            try {
                //删除redis键
                log.info("删除redisKey: " + redisKey);
                JedisUtil.del(redisKey);
                Integer count = ArticleUtil.parseValueCount(redisValue);
                Integer articleId = Integer.valueOf(ArticleUtil.parseKeyArticleId(redisKey));

                ViewsVO viewsVO = new ViewsVO(articleId,count);
                //更新到数据库
                log.info("更新到数据库: " + viewsVO);
                articleService.updateViews(viewsVO);

                channel.basicAck(tag, true);
            } catch (IOException e) {
                try {
                    // 处理失败,重新压入MQ
                    channel.basicRecover();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        } else {
            //如果不相等,什么业务都不做
            try {
                log.info("该请求不是最后一次请求,不更新到数据库");
                channel.basicAck(tag, true);
            } catch (IOException e) {
                try {
                    // 处理失败,重新压入MQ
                    channel.basicRecover();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }
}
