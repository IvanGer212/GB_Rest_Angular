package com.geekbrains.ru.gb_rest_angular.core.config;

import com.geekbrains.ru.gb_rest_angular.core.properties.CartServiceIntegrationProperties;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.tcp.TcpClient;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableConfigurationProperties(CartServiceIntegrationProperties.class)
@RequiredArgsConstructor
public class AppConfig {
    private final CartServiceIntegrationProperties properties;

    @Bean
    public WebClient cartServiceWebClient(){
        TcpClient tcpClient = TcpClient
                .create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, properties.getConnectTimeout())
                .doOnConnected(connection -> {
                    connection.addHandlerLast(new ReadTimeoutHandler(properties.getReadTimeout(), TimeUnit.MILLISECONDS));
                    connection.addHandlerLast(new WriteTimeoutHandler(properties.getWriteTimeout(),TimeUnit.MILLISECONDS));
                });

        return WebClient
                .builder()
                .baseUrl(properties.getUrl())
                .clientConnector(new ReactorClientHttpConnector(HttpClient.from(tcpClient)))
                .build();
    }
//    @Bean
//    public RestTemplate restTemplate(){
//        return new RestTemplate();
//    }
}
