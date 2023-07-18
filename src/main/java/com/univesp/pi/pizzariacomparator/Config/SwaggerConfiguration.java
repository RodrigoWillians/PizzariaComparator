package com.univesp.pi.pizzariacomparator.Config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;


@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("bearerAuth", new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                        )
                )
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth", Collections.emptyList()))
                .info(new Info()
                        .title("API Comparador de Pizzarias")
                        .version("v1")
                        .description("API de cadastro de diversas pizzarias")
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://www.apache.org/licenses/LICENSE-2.0.html")
                        )
                );
    }

}


// @OpenAPIDefinition(info =
// @Info(title = "API Comparador Pizzarias",
//     version = "v1",
//         description = "API cadastros de diversas pizzarias"))

// public class SwaggerConfiguration {

//     @Bean
//     public OpenAPI customOpenAPI() {
//         return new OpenAPI()
//             .components(new Components())
//             .info(
//                 new io.swagger.v3.oas.models.info.Info()
//                 .title("API Comparador de pizzarias")
//                 .version("v1")
//                 .license(
//                     new License()
//                         .name("Apache 2.0")
//                         .url("http://springdoc.org")
//                 )
//             );

            
            
//     }
// }