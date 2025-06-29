package com.rafaelmeloni.crud_demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class FieldMessage {

    private String fieldName;
    private String message;
}
