package com.example.library.service;

import com.example.library.data.dto.mapper.PersonMapper;
import com.example.library.data.dto.request.PersonRequestDTO;
import com.example.library.data.dto.response.PersonResponseDTO;
import com.example.library.data.entity.Person;
import com.example.library.exception.resource.person.NoPersonFoundException;
import com.example.library.exception.resource.person.NoPersonsFoundException;
import com.example.library.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersonService {
    private final PersonRepository personRepository;

    public List<PersonResponseDTO> findAll() {
        return Optional.of(personRepository.findAll())
                .filter(list -> !list.isEmpty())
                .map(persons -> convertToDTOList(persons, PersonMapper::toDto))
                .orElseThrow(NoPersonsFoundException::new);
    }

    public PersonResponseDTO findById(String id) {
        return personRepository.findById(id)
                .map(PersonMapper::toDto)
                .orElseThrow(NoPersonFoundException::new);
    }

    public PersonResponseDTO save (PersonRequestDTO person) {
        return Optional.of(person)
                .map(PersonMapper::toEntity)
                .map(personRepository::save)
                .map(PersonMapper::toDto)
                .orElseThrow(IllegalArgumentException::new);
    }

    public PersonResponseDTO update (String id, PersonRequestDTO personDetails) {
        return personRepository.findById(id)
                .map(person -> {
                    person.setName(personDetails.name());
                    person.setAge(personDetails.age());
                    person.setEmail(personDetails.email());
                    person.setCep(personDetails.cep());
                    person.setDateOfBirth(personDetails.dateOfBirth());
                    return personRepository.save(person);
                })
                .map(PersonMapper::toDto)
                .orElseThrow(NoPersonFoundException::new);
    }

    public void delete(String id) {
        Person person = personRepository.findById(id)
                .orElseThrow(NoPersonFoundException::new);
        personRepository.delete(person);
    }

    private <T, R> List<R> convertToDTOList(List<T> entity, Function<T, R> mapper) {
        return entity.stream()
                .map(mapper)
                .collect(Collectors.toList());
    }
}
