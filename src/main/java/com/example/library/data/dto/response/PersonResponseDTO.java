package com.example.library.data.dto.response;

import com.example.library.data.entity.Person;
import java.util.Date;

public record PersonResponseDTO(
    String cpf,
    String name,
    Long age,
    String email,
    Date dateOfBirth,
    String cep
) {
    public PersonResponseDTO (Person person){
        this(person.getCpf(),person.getName(),person.getAge(),person.getEmail(),person.getDateOfBirth(),person.getCep());
    }
}