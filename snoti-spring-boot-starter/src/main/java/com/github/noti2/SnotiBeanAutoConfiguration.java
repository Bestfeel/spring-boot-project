package com.github.noti2;

import com.gizwits.noti2.client.LoginData;
import com.gizwits.noti2.client.NotiClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by feel on 2017/10/11.
 */
@Configuration
@ConditionalOnProperty(prefix = "snoti.boot", value = "enable", name = "snoti", matchIfMissing = true)
public class SnotiBeanAutoConfiguration extends SnotiAutoConfiguration {


    @Bean
    @ConditionalOnMissingBean
    protected NotiClient notiClient() {

        List<LoginData> loginData = new ArrayList<LoginData>();

        String[] productKeys = super.getProductKeys();

        for (int i = 0; i < productKeys.length; i++) {

            loginData.add(new LoginData(productKeys[i], super.getAuthId()[i], super.getAuthSecret()[i], super.getSubkey()[i], getEvents()[i].getEvents()));
        }

        NotiClient notiClient = NotiClient
                .build()
                .setHost(super.getHost())
                .setPort(2017)
                .setMaxFrameLength(super.getMaxFrameLength())
                .login(loginData);
        notiClient.doStart();

        return notiClient;

    }
}
