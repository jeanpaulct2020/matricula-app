package com.jeanct.matriculaapp.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean("courseMapper")
    public ModelMapper courseMapper(){
        return new ModelMapper();
    }

    @Bean("enrollmentMapper")
    public ModelMapper enrollmentMapper(){
        return new ModelMapper();
    }

    @Bean("roleMapper")
    public ModelMapper roleMapper(){
        return new ModelMapper();
    }

    @Bean("studentMapper")
    public ModelMapper studentMapper(){
        return new ModelMapper();
    }

    @Bean("userMapper")
    public ModelMapper userMapper(){
        return new ModelMapper();
    }

}
