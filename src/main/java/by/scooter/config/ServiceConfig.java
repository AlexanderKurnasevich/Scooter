package by.scooter.config;

import by.scooter.entity.dto.location.RentPointDTO;
import by.scooter.entity.dto.user.ClientInfoDTO;
import by.scooter.entity.dto.user.ClientUserDTO;
import by.scooter.entity.location.RentPoint;
import by.scooter.entity.user.Client;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.thymeleaf.spring5.SpringTemplateEngine;

@Configuration
public class ServiceConfig {

    @Bean
    public ModelMapper getModelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.typeMap(Client.class, ClientUserDTO.class)
                .addMappings(mapper -> mapper.map(src -> src.getUser().getUsername(), ClientUserDTO::setUsername))
                .addMappings(mapper -> mapper.map(src -> src.getUser().getId(), ClientUserDTO::setUserId));

        modelMapper.typeMap(Client.class, ClientInfoDTO.class)
                .addMappings(mapper -> mapper.map(src -> src.getUser().getUsername(), ClientInfoDTO::setUsername))
                .addMappings(mapper -> mapper.map(src -> src.getUser().getId(), ClientInfoDTO::setUserId));

        modelMapper.typeMap(RentPoint.class, RentPointDTO.class)
                .addMappings(mapper -> mapper.map(src -> src.getAddress().getCity().getCountry().getCountryName(),
                        RentPointDTO::setCountry))
                .addMappings(mapper -> mapper.map(src -> src.getAddress().getCity().getCityName(), RentPointDTO::setCity))
                .addMappings(mapper -> mapper.map(src -> src.getAddress().getStreet(), RentPointDTO::setStreet))
                .addMappings(mapper -> mapper.map(src -> src.getAddress().getNumber(), RentPointDTO::setNumber))
                .addMappings(mapper -> mapper.map(src -> src.getAddress().getPostfix(), RentPointDTO::setPostfix));

        return modelMapper;
    }

    @Bean
    public SpringTemplateEngine getSpringTemplateEngine() {
        return new SpringTemplateEngine();
    }
}
