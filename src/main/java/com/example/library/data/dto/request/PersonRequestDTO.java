package com.example.library.data.dto.request;

import com.example.library.data.entity.Person;
import java.util.Date;

public record PersonRequestDTO(
        String cpf,
        String name,
        Long age,
        String email,
        Date dateOfBirth,
        String cep
) {
    public PersonRequestDTO (Person person){
        this(person.getCpf(),person.getName(),person.getAge(),person.getEmail(),person.getDateOfBirth(),person.getCep());
    }
}
