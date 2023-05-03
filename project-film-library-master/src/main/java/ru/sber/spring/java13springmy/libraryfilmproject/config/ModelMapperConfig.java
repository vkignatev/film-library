package ru.sber.spring.java13springmy.libraryfilmproject.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import static org.modelmapper.config.Configuration.AccessLevel.PRIVATE;

// библиотека мапинга, MatchingStrategies.STRICT -жесткая стратегия по наименованию
@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)      //жесткая стратегия
                .setFieldMatchingEnabled(true)                       // сравнение по полям
                .setSkipNullEnabled(true)                            // пропуская пустые поля
                .setFieldAccessLevel(PRIVATE);                        // все поля пр создании нового класса будут private
        return modelMapper;
    }
}
