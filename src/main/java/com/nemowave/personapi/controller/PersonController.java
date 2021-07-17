package com.nemowave.personapi.controller;

import com.nemowave.personapi.dto.MessageResponseDTO;
import com.nemowave.personapi.model.Person;
import com.nemowave.personapi.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    public MessageResponseDTO createPerson(@RequestBody Person person) {
        return personService.create(person);
    }

    @DeleteMapping("/{idPerson}")
    public MessageResponseDTO deletePerson(@PathVariable("idPerson") long idPerson) {
        return personService.deleteById(idPerson);
    }

    @GetMapping
    public List<Person> listAllPerson() {
        return personService.listAll();
    }
}
