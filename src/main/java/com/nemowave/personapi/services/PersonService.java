package com.nemowave.personapi.services;

import com.nemowave.personapi.dto.request.PersonDTO;
import com.nemowave.personapi.dto.response.MessageResponseDTO;
import com.nemowave.personapi.exception.PersonNotFoundException;
import com.nemowave.personapi.mapper.PersonMapper;
import com.nemowave.personapi.model.Person;
import com.nemowave.personapi.repository.PersonRepository;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public List<PersonDTO> listAll() {
        List<Person> all = personRepository.findAll();
        return all.stream()
                .map(personMapper::toDTO).collect(Collectors.toList());
    }

    public MessageResponseDTO deleteById(long idPerson) {
        personRepository.deleteById(idPerson);
        return MessageResponseDTO.builder()
                .message("Person with ID " + idPerson + " deleted")
                .build();
    }


    public PersonDTO findById(Long id) throws PersonNotFoundException {
        Person person = personRepository.findById(id)
                .orElseThrow(()->new PersonNotFoundException(id));
        return personMapper.toDTO(person);
    }
}
