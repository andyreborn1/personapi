package com.nemowave.personapi.controller;

import com.nemowave.personapi.dto.request.PersonDTO;
import com.nemowave.personapi.dto.response.MessageResponseDTO;
import com.nemowave.personapi.exception.PersonNotFoundException;
import com.nemowave.personapi.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/person")
public class PersonController {
    PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO createPerson(@RequestBody @Valid PersonDTO personDTO) {
        return personService.create(personDTO);
    }

    @GetMapping("/{id}")
    public PersonDTO findById(@PathVariable("id") Long id) throws PersonNotFoundException {
        return  personService.findById(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{idPerson}")
    public void deletePerson(@PathVariable("idPerson") long idPerson) throws PersonNotFoundException {
        personService.deleteById(idPerson);
    }

    @PutMapping("/{id}")
    public MessageResponseDTO updateById(@PathVariable("id") Long id, PersonDTO personDTO) throws PersonNotFoundException {
        return personService.updateById(id,personDTO);
    }

    @GetMapping
    public List<PersonDTO> listAllPerson() {
        return personService.listAll();
    }
}
