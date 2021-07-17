package com.nemowave.personapi.services;

import com.nemowave.personapi.dto.request.PersonDTO;
import com.nemowave.personapi.dto.response.MessageResponseDTO;
import com.nemowave.personapi.mapper.PersonMapper;
import com.nemowave.personapi.model.Person;
import com.nemowave.personapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {
    PersonRepository personRepository;
    private final PersonMapper personMapper = PersonMapper.INSTANCE;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public MessageResponseDTO create(PersonDTO personDTO) {
        Person personToSave = personMapper.toModel(personDTO);
        Person savedPerson = personRepository.save(personToSave);
        return MessageResponseDTO.builder()
                .message("Person created with ID " + savedPerson.getId())
                .build();
    }

    public List<Person> listAll() {
        return personRepository.findAll();
    }

    public MessageResponseDTO deleteById(long idPerson) {
        personRepository.deleteById(idPerson);
        return MessageResponseDTO.builder()
                .message("Person with ID " + idPerson + " deleted")
                .build();
    }

    public MessageResponseDTO update(Person person) {
        Person savedPerson = personRepository.save(person);
        return MessageResponseDTO.builder()
                .message("Person with ID " + savedPerson.getId()+" updated")
                .build();
    }
}
