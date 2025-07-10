package com.example.demo.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
    info = @Info(
        title = "Task JWT API",
        version = "1.0.0",
        description = "API para gerenciamento de tarefas com autenticação JWT",
        contact = @Contact(
            name = "Breno Nunes",
            email = "morais.brenonunes@hotmail.com",
            url = "https://github.com/brenonun3s"
        ),
        license = @License(
            name = "MIT License",
            url = "https://opensource.org/licenses/MIT"
        )
    ),
    servers = {
        @Server(url = "http://localhost:8080", description = "Desenvolvimento local")
    }
)
public class OpenApiConfig {
}
