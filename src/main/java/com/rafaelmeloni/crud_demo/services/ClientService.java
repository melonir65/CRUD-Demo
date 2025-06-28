package com.rafaelmeloni.crud_demo.services;


import com.rafaelmeloni.crud_demo.dto.ClientDTO;
import com.rafaelmeloni.crud_demo.entities.Client;
import com.rafaelmeloni.crud_demo.repositories.ClientRepository;
import com.rafaelmeloni.crud_demo.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Transactional(readOnly = true)
    public ClientDTO findById(Integer id){

       Client client = clientRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Resource not found with id " + id));
       return new ClientDTO(client);
    }

    @Transactional(readOnly = true)
    public Page<ClientDTO> findAll(Pageable pageable) {

        Page<Client> clientList = clientRepository.findAll(pageable);
        return clientList.map(ClientDTO::new);

    }



}
