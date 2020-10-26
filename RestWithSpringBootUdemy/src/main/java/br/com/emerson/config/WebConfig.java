package br.com.emerson.config;

import br.com.emerson.converter.YamlToHttpMessageConverter;
import java.util.List;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    public static final MediaType APPLICATION_YML = MediaType.valueOf("application/x-yaml");

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new YamlToHttpMessageConverter());
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods("GET", "PUT", "POST", "DELETE", "PATCH", "OPTIONS", "HEAD", "TRACE", "CONNECT");
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {

        /**
         * Configuração para devolver contentType xml e json via extensão na url.
         * Exemplo: http://localhost:8080/api/person/v1.xml
         *
         * configurer.favorParameter(false).ignoreAcceptHeader(false).defaultContentType(MediaType.APPLICATION_JSON)
         *                 .mediaType("json", MediaType.APPLICATION_JSON).mediaType("xml", MediaType.APPLICATION_XML);
         *
         *  ------------------------------------------------------------------------------------------------------------
         *
         * Configuração para devolver contentType xml e json via queryParams.
         * Exemplo: http://localhost:8080/api/person/v1?mediaType=xml
         *
         * configurer.favorPathExtension(false).favorParameter(true).parameterName("mediaType").ignoreAcceptHeader(true)
         *                 .useRegisteredExtensionsOnly(false).defaultContentType(MediaType.APPLICATION_JSON)
         *                 .mediaType("json", MediaType.APPLICATION_JSON).mediaType("xml", MediaType.APPLICATION_XML);
         *
         *  ------------------------------------------------------------------------------------------------------------
         *
         * Configuração para devolver contentType xml e json via header.
         * configurer.favorPathExtension(false).favorParameter(false).ignoreAcceptHeader(false)
         *                 .useRegisteredExtensionsOnly(false).defaultContentType(MediaType.APPLICATION_JSON)
         *                 .mediaType("json", MediaType.APPLICATION_JSON).mediaType("xml", MediaType.APPLICATION_XML);
         *
         */
        configurer.favorPathExtension(false).favorParameter(false).ignoreAcceptHeader(false)
                .useRegisteredExtensionsOnly(false).defaultContentType(MediaType.APPLICATION_JSON)
                .mediaType("json", MediaType.APPLICATION_JSON)
                .mediaType("xml", MediaType.APPLICATION_XML)
                .mediaType("x-yaml", APPLICATION_YML);
    }
}
