package com.windcf.dockerboot.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author chunf
 */
@Configuration
public class JacksonConfig {
    @Primary
    @Bean
    public ObjectMapper objectMapper(Jackson2ObjectMapperBuilder builder) {
        DateTimeFormatter datePat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter datetimePat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        builder.serializerByType(LocalDate.class, new LocalDateSerializer(datePat));
        builder.deserializerByType(LocalDate.class, new LocalDateDeserializer(datePat));
        builder.serializerByType(LocalDateTime.class, new LocalDateTimeSerializer(datetimePat));
        builder.deserializerByType(LocalDateTime.class, new LocalDateTimeDeserializer(datetimePat));

        return builder.createXmlMapper(false).build();
    }
}
