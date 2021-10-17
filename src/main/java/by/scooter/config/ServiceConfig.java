package by.scooter.config;

import by.scooter.entity.dto.event.OrderCreateDTO;
import by.scooter.entity.dto.location.RentPointDTO;
import by.scooter.entity.dto.user.ClientInfoDTO;
import by.scooter.entity.dto.user.ClientUserDTO;
import by.scooter.entity.event.Order;
import by.scooter.entity.location.RentPoint;
import by.scooter.entity.user.Client;
import by.scooter.service.ScooterDTOSetConvertor;
import by.scooter.service.ScooterSetConvertor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
}
