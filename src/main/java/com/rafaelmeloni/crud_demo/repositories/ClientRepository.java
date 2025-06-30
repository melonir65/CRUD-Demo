package com.rafaelmeloni.crud_demo.repositories;

import com.rafaelmeloni.crud_demo.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
