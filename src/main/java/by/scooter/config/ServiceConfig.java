package by.scooter.config;

import by.scooter.entity.dto.ClientUserDTO;
import by.scooter.entity.user.Client;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {

    @Bean
    public ModelMapper getModelMapper(){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.typeMap(Client.class, ClientUserDTO.class)
                .addMappings(mapper -> mapper.map(src -> src.getUser().getUsername(), ClientUserDTO::setUsername))
                .addMappings(mapper -> mapper.map(src -> src.getUser().getId(), ClientUserDTO::setUserId));
        return modelMapper;
    }
}
