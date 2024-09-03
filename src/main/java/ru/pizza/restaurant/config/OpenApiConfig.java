package ru.pizza.restaurant.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        servers = {@Server(url = "http://localhost:8085", description = "restaurantMS")},
        info = @Info(
                title = "RestaurantMS",
                version = "1.0",
                description = "Микросервис для мониторинга складов и обработки онлайн-заказов"
        )
)
@Configuration
public class OpenApiConfig {
}
