package com.rafaelmeloni.crud_demo.services;


import com.rafaelmeloni.crud_demo.dto.ClientDTO;
import com.rafaelmeloni.crud_demo.entities.Client;
import com.rafaelmeloni.crud_demo.repositories.ClientRepository;
import com.rafaelmeloni.crud_demo.services.exceptions.DatabaseException;
import com.rafaelmeloni.crud_demo.services.exceptions.ResourceNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Transactional(readOnly = true)
    public ClientDTO findById(Long id){

       Client client = clientRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Client not found with id " + id));
       return new ClientDTO(client);
    }

    @Transactional(readOnly = true)
    public Page<ClientDTO> findAll(Pageable pageable) {

        Page<Client> clientList = clientRepository.findAll(pageable);
        return clientList.map(ClientDTO::new);

    }

    @Transactional
    public ClientDTO insert(ClientDTO clientDTO){

        Client Entity = new Client();
        copyDtoToEntity(clientDTO,Entity);
        Entity = clientRepository.save(Entity);
        return new ClientDTO(Entity);
    }


    @Transactional()
    public ClientDTO update(Long id, ClientDTO clientDTO){

        Client entity =clientRepository.getReferenceById(id);
        copyDtoToEntity(clientDTO,entity);
        return new ClientDTO(entity);

    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        if (!clientRepository.existsById(id)) {
            throw new ResourceNotFoundException("Client not found with id " + id);
        }
        try {
            clientRepository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Referential integrity violation");
        }
    }


    private void copyDtoToEntity(ClientDTO clientDTO, Client entity){

        entity.setName(clientDTO.getName());
        entity.setCpf(clientDTO.getCpf());
        entity.setIncome(clientDTO.getIncome());
        entity.setBirthDate(clientDTO.getBirthDate());
        entity.setChildren(clientDTO.getChildren());

    }



}
