package com.dev.restfullapi.service.impl;

import com.dev.restfullapi.domain.entity.Client;
import com.dev.restfullapi.domain.repository.ClientRepository;
import com.dev.restfullapi.exception.NotFoundException;
import com.dev.restfullapi.rest.dto.input.ClientDTO;
import com.dev.restfullapi.service.ClientService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    private static String NOT_FOUND_MESSAGE = "Client not found!";
    private ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public void create(ClientDTO clientDTO) {
        var entity = new Client(clientDTO.getName());
        this.clientRepository.save(entity);
    }

    @Override
    public List<Client> getAll() {
        return this.clientRepository.findAll();
    }

    @Override
    public Client getById(Integer id) {
        return this
                .clientRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(NOT_FOUND_MESSAGE));
    }

    @Override
    public void update(Integer id, ClientDTO clientDTO) {
        var entity = this
                .clientRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(NOT_FOUND_MESSAGE));
        entity.setName(clientDTO.getName());
        this.clientRepository.save(entity);
    }

    @Override
    public void delete(Integer id) {
        var entity = this
                .clientRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(NOT_FOUND_MESSAGE));
        this.clientRepository.delete(entity);
    }
}
