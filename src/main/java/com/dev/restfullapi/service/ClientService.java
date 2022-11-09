package com.dev.restfullapi.service;

import com.dev.restfullapi.domain.entity.Client;
import com.dev.restfullapi.rest.dto.input.ClientDTO;

import java.util.List;

public interface ClientService {
    void create(ClientDTO clientDTO);

    List<Client> getAll();

    Client getById(Integer id);

    void update(Integer id, ClientDTO clientDTO);

    void delete(Integer id);
}
