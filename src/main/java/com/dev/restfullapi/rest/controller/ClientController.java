package com.dev.restfullapi.rest.controller;

import com.dev.restfullapi.domain.entity.Client;
import com.dev.restfullapi.rest.dto.input.ClientDTO;
import com.dev.restfullapi.service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    private ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @Valid ClientDTO clientDTO) {
        this.clientService.create(clientDTO);
    }

    @GetMapping
    public List<Client> getAll() {
        return this.clientService.getAll();
    }

    @GetMapping("/{id}")
    public Client getById(@PathVariable Integer id) {
       return this.clientService.getById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Integer id, @RequestBody @Valid ClientDTO clientDTO) {
       this.clientService.update(id, clientDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
      this.clientService.delete(id);
    }
}
