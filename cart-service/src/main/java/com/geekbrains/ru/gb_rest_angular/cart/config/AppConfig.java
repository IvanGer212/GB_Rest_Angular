package com.geekbrains.ru.gb_rest_angular.cart.config;

import com.geekbrains.ru.gb_rest_angular.cart.properties.ProductServiceIntegrationProperties;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.tcp.TcpClient;

import java.util.concurrent.TimeUnit;


@Configuration
@EnableConfigurationProperties(
        ProductServiceIntegrationProperties.class
)
@RequiredArgsConstructor
public class AppConfig {
    private final ProductServiceIntegrationProperties productServiceIntegrationProperties;

    @Bean
    public WebClient productServiceWebClient() {
        TcpClient tcpClient = TcpClient
                .create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, productServiceIntegrationProperties.getConnectTimeout())
                .doOnConnected(connection -> {
                    connection.addHandlerLast(new ReadTimeoutHandler(productServiceIntegrationProperties.getReadTimeout(), TimeUnit.MILLISECONDS));
                    connection.addHandlerLast(new WriteTimeoutHandler(productServiceIntegrationProperties.getWriteTimeout(), TimeUnit.MILLISECONDS));
                });


        return WebClient
                .builder()
                .baseUrl(productServiceIntegrationProperties.getUrl())
                .clientConnector(new ReactorClientHttpConnector(HttpClient.from(tcpClient)))
                .build();
    }
}

//@Configuration
//@EnableConfigurationProperties(ProductServiceIntegrationProperties.class)
//@RequiredArgsConstructor
//public class AppConfig {
//
//    private final ProductServiceIntegrationProperties productServiceIntegrationProperties;
//
////    @Bean
////    public RestTemplate restTemplate(){
////        return new RestTemplate();
////    }
//
//    @Bean
//    public WebClient productServiceWebClient(){
//        TcpClient tcpClient = TcpClient
//                .create()
//                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, productServiceIntegrationProperties.getConnectTimeout())
//                .doOnConnected(connection ->{
//                    connection.addHandlerLast(new ReadTimeoutHandler(productServiceIntegrationProperties.getReadTimeout(), TimeUnit.MILLISECONDS));
//                    connection.addHandlerLast(new WriteTimeoutHandler(productServiceIntegrationProperties.getWriteTimeout(),TimeUnit.MILLISECONDS));
//
//                });
//        return WebClient.builder()
//                .baseUrl(productServiceIntegrationProperties.getUrl())
//                .clientConnector(new ReactorClientHttpConnector(HttpClient.from(tcpClient)))
//                .build();
//
//    }
//}
