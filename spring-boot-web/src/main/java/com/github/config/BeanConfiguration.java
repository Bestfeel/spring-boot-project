package com.github.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
/**
 * @author guojie.yf
 * @date 2018/7/6
 * @email guojie.yf@alibaba-inc.com
 */
@Configuration
public class BeanConfiguration {
    @Bean
    public RestTemplate restTemplate() {
        HttpHeaders headers = new HttpHeaders();
        MediaType mediaType = new MediaType("application", "merge-patch+json");
        headers.setContentType(mediaType);
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        return new RestTemplate(requestFactory);
    }
}
