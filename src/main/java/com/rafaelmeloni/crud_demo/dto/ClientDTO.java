package com.rafaelmeloni.crud_demo.dto;

import com.rafaelmeloni.crud_demo.entities.Client;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@NoArgsConstructor
@Getter
public class ClientDTO {

    private Long id;

    @Size(min = 3, max = 80, message = "Name must have more than 3 and less than 80 char")
    @NotBlank(message = "Please insert a not blank name")
    private String name;

    @NotBlank(message = "Please insert a not blank CPF")
    private String cpf;
    private Double income;
    private LocalDate birthDate;
    private Integer children;

    public ClientDTO(Client entity){
        id = entity.getId();
        name = entity.getName();
        cpf = entity.getCpf();
        income = entity.getIncome();
        birthDate = entity.getBirthDate();
        children = entity.getChildren();
    }
}
