package com.stancloud.apigateway.config;

import com.stancloud.apigateway.client.AuthClient;
import com.stancloud.apigateway.filter.AuthFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.reactive.LoadBalancedExchangeFilterFunction;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class AppConfig {

  @Autowired
  private AuthFilter authFilter;

  @Autowired
  private LoadBalancedExchangeFilterFunction loadBalancedExchangeFilterFunction;
  @Bean
  public WebClient employeeWebClient() {
    return WebClient.builder()
        .baseUrl("http://authentication-service")
        .filter(loadBalancedExchangeFilterFunction)
        .build();
  }

  @Bean
  public AuthClient authClient() {
    HttpServiceProxyFactory factory = HttpServiceProxyFactory
        .builder(WebClientAdapter.forClient(employeeWebClient()))
        .build();

    return factory.createClient(AuthClient.class);
  }

  @Bean
  public RouteLocator routes(RouteLocatorBuilder builder) {
    return builder.routes()
        .route("department-service", r -> r.path("/department/**")
            .filters(f -> f.filter(authFilter))
            .uri("lb://department-service"))

        .route("employee-service", r -> r.path("/employee/**")
            .filters(f -> f.filter(authFilter))
            .uri("lb://employee-service"))

        .route("auth-service", r -> r.path("/auth/**")
            .uri("lb://auth-service"))
        .build();
  }
}