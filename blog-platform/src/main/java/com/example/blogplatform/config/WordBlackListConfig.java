package com.example.blogplatform.config;

import com.example.utils.WordBlackListUtil;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yenanren
 * @date 2022/4/11 0011
 * @Description
 */

@Configuration
public class WordBlackListConfig implements InitializingBean, DisposableBean {


    @Override
    public void afterPropertiesSet() throws Exception {
        WordBlackListUtil.init();
    }

    @Override
    public void destroy() throws Exception {

    }
}
