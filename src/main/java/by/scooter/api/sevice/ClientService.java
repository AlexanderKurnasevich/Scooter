package by.scooter.api.sevice;

import by.scooter.entity.dto.user.ClientInfoDTO;
import by.scooter.entity.dto.user.ClientUserDTO;

import java.util.List;

public interface ClientService {
    ClientInfoDTO getById(Long id);

    ClientInfoDTO addClient(ClientUserDTO client);

    List<ClientInfoDTO> getAll(Integer page, Integer size);

    void removeClient(Long id);

    void updateClient(Long updatedId, ClientUserDTO update);
}
