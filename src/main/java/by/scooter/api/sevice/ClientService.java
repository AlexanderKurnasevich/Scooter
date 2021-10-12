package by.scooter.api.sevice;

import by.scooter.entity.dto.user.ClientInfoDTO;
import by.scooter.entity.dto.user.ClientUserDTO;
import by.scooter.entity.user.Client;
import org.springframework.security.access.AccessDeniedException;

import java.util.List;

public interface ClientService {
    ClientInfoDTO getById(Long id);

    ClientInfoDTO addClient(ClientUserDTO client);

    List<ClientInfoDTO> getAll(Integer page, Integer size);

    void removeClient(Long id);

    void updateClient(Long updatedId, ClientUserDTO update);

    Client checkOwner(Long id) throws AccessDeniedException;
}
