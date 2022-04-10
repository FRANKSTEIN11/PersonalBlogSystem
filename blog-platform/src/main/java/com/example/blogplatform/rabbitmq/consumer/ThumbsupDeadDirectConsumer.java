package com.example.blogplatform.rabbitmq.consumer;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.blogplatform.conf.Conf;
import com.example.blogplatform.utils.ArticleUtil;
import com.example.entity.Thumbsup;
import com.example.service.ArticleService;
import com.example.service.ThumbsupService;
import com.example.vo.MqKeyAndValueVO;
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
@RabbitListener(queues = Conf.THUMBSUP_DEAD_DIRECT_QUEUE)
public class ThumbsupDeadDirectConsumer {

    @Resource
    private ArticleService articleService;
    @Resource
    private ThumbsupService thumbsupService;

    @RabbitHandler
    public void receive(MqKeyAndValueVO mqKeyAndValueVO, @Header(AmqpHeaders.DELIVERY_TAG) long tag, Channel channel) {

        log.info("thumbsup.dead.direct.queue收到的消息dead: " + mqKeyAndValueVO.toString());
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
                Integer userId = Integer.valueOf(ArticleUtil.parseKeyUserId(redisKey));


                //查看数据库是否有信息
                Thumbsup thumbsup = isThumbsup(redisKey);

                //如果是单数,则点赞了,否则没点赞
                if (count % 2 == 1) {
                    if (thumbsup == null) {
                        thumbsup = new Thumbsup();
                        thumbsup.setArticleId(articleId);
                        thumbsup.setUserId(userId);
                        thumbsupService.save(thumbsup);
                        //更新到数据库
                        log.info("点赞成功,更新到数据库: " + thumbsup);
                    } else {
                        log.info("点赞无效,不更新到数据库");
                    }
                } else {

                    if (thumbsup != null) {
                        thumbsupService.removeById(thumbsup.getThumbsupId());
                        log.info("点赞取消,更新到数据库");
                    }
                    log.info("点赞无效,不更新到数据库");
                }

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

    public Thumbsup isThumbsup(String thumbsupKey) {
        String articleId = ArticleUtil.parseKeyArticleId(thumbsupKey);
        String userId = ArticleUtil.parseKeyUserId(thumbsupKey);
        QueryWrapper<Thumbsup> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("article_id", articleId);
        queryWrapper.eq("user_id", userId);
        return thumbsupService.getOne(queryWrapper);
    }
}
