package com.example.library.data.dto.mapper;

import com.example.library.data.dto.request.PersonRequestDTO;
import com.example.library.data.dto.response.PersonResponseDTO;
import com.example.library.data.entity.Person;
import com.example.library.exception.argument.IllegalParamException;

public class PersonMapper {
    public static PersonResponseDTO toDto(Person person) {
        if (person == null) {
            return null;
        }
        return new PersonResponseDTO(
                person.getCpf(),
                person.getName(),
                person.getAge(),
                person.getEmail(),
                person.getDateOfBirth(),
                person.getCep()
        );
    }

    public static Person toEntity(PersonRequestDTO personRequestDTO) {
        if (personRequestDTO == null) {
            throw new IllegalParamException();
        }
        return new Person(
                personRequestDTO.cpf(),
                personRequestDTO.name(),
                personRequestDTO.age(),
                personRequestDTO.email(),
                personRequestDTO.dateOfBirth(),
                personRequestDTO.cep()
        );
    }
}
