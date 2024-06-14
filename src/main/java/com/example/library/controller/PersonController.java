package com.example.library.controller;

import com.example.library.data.dto.request.PersonRequestDTO;
import com.example.library.data.dto.response.PersonResponseDTO;
import com.example.library.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
@RequiredArgsConstructor
public class PersonController {
    private final PersonService personService;

    @GetMapping
    public ResponseEntity<List<PersonResponseDTO>> getAll() {
        List<PersonResponseDTO> persons = personService.findAll();
        return ResponseEntity.ok(persons);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonResponseDTO> getById(@PathVariable String id) {
        PersonResponseDTO person = personService.findById(id);
        return ResponseEntity.ok(person);
    }

    @PostMapping
    public ResponseEntity<PersonResponseDTO> postPerson (@RequestBody PersonRequestDTO person) {
        PersonResponseDTO savedPerson = personService.save(person);
        return ResponseEntity.ok(savedPerson);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonResponseDTO> putPerson (@PathVariable String id, @RequestBody PersonRequestDTO person) {
        PersonResponseDTO updatedPerson = personService.update(id, person);
        return ResponseEntity.ok(updatedPerson);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePerson (@PathVariable String id) {
        personService.delete(id);
        return ResponseEntity.ok("Pessoa excluida com sucesso");
    }
}
