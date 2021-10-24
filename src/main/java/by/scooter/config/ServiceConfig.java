package by.scooter.config;

import by.scooter.entity.dto.location.RentPointDTO;
import by.scooter.entity.dto.user.ClientInfoDTO;
import by.scooter.entity.dto.user.ClientUserDTO;
import by.scooter.entity.location.RentPoint;
import by.scooter.entity.user.Client;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;

@Configuration
@RequiredArgsConstructor
public class ServiceConfig {

    private final ApplicationContext applicationContext;

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
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setApplicationContext(applicationContext);
        templateResolver.setPrefix("classpath:/templates/"); //FIXME remove classpath
        templateResolver.setSuffix(".html");
        return templateResolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        templateEngine.setEnableSpringELCompiler(true);
        return templateEngine;
    }
}
