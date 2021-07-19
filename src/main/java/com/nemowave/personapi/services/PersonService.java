package com.nemowave.personapi.services;

import com.nemowave.personapi.dto.request.PersonDTO;
import com.nemowave.personapi.dto.response.MessageResponseDTO;
import com.nemowave.personapi.exception.PersonNotFoundException;
import com.nemowave.personapi.mapper.PersonMapper;
import com.nemowave.personapi.model.Person;
import com.nemowave.personapi.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonService {
    PersonRepository personRepository;

    private final PersonMapper personMapper = PersonMapper.INSTANCE;

    public MessageResponseDTO create(PersonDTO personDTO) {
        Person personToSave = personMapper.toModel(personDTO);
        Person savedPerson = personRepository.save(personToSave);
        return createMessageResponse(savedPerson.getId(), "Created person with ID" + personToSave.getId());
    }

    public List<PersonDTO> listAll() {
        List<Person> all = personRepository.findAll();
        return all.stream()
                .map(personMapper::toDTO).collect(Collectors.toList());
    }

    public void deleteById(long idPerson) throws PersonNotFoundException {
        verifyIfExists(idPerson);
        personRepository.deleteById(idPerson);
    }


    public PersonDTO findById(Long id) throws PersonNotFoundException {
        Person person = verifyIfExists(id);
        return personMapper.toDTO(person);
    }

    public MessageResponseDTO updateById(Long id, PersonDTO personDTO) throws PersonNotFoundException {
        verifyIfExists(id);
        Person personToUpdate = personMapper.toModel(personDTO);
        Person updatedPerson = personRepository.save(personToUpdate);
        return createMessageResponse(updatedPerson.getId(), "Person with ID " + updatedPerson.getId() + " updated.");
    }

    private MessageResponseDTO createMessageResponse(Long id, String s) {
        return MessageResponseDTO.builder()
                .message(s + id)
                .build();
    }

    private Person verifyIfExists(Long id) throws PersonNotFoundException {
        return personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));
    }
}
