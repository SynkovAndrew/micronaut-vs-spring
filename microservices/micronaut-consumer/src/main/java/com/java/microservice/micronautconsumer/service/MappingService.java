package com.java.microservice.micronautconsumer.service;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.builtin.PassThroughConverter;
import ma.glasnost.orika.impl.ConfigurableMapper;

import javax.inject.Singleton;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;

@Singleton
public class MappingService extends ConfigurableMapper {
    private MapperFactory factory;

    @Override
    public void configure(MapperFactory factory) {
        this.factory = factory;
        this.factory.getConverterFactory().registerConverter(new PassThroughConverter(LocalDateTime.class));
        this.factory.getConverterFactory().registerConverter(new PassThroughConverter(LocalDate.class));
        this.factory.getConverterFactory().registerConverter(new PassThroughConverter(YearMonth.class));
    }
}
