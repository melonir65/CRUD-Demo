package com.rafaelmeloni.crud_demo.controllers;

import com.rafaelmeloni.crud_demo.dto.ClientDTO;
import com.rafaelmeloni.crud_demo.entities.Client;
import com.rafaelmeloni.crud_demo.repositories.ClientRepository;
import com.rafaelmeloni.crud_demo.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;


    @GetMapping(value = "/{id}")
    public ResponseEntity<ClientDTO> findById(@PathVariable Integer id){
        ClientDTO dto = clientService.findById(id);
        return ResponseEntity.ok(dto);
    }


}
