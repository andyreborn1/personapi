package com.nemowave.personapi.services;

import com.nemowave.personapi.model.Person;
import com.nemowave.personapi.model.Phone;
import com.nemowave.personapi.repository.PersonRepository;
import com.nemowave.personapi.repository.PhoneRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhoneService{
    PhoneRepository phoneRepository;

    public PhoneService(PhoneRepository phoneRepository) {
        this.phoneRepository = phoneRepository;
    }

    public Phone create(Phone phone){
        return phoneRepository.save(phone);
    }

    public List<Phone> listAll(){
        return phoneRepository.findAll();
    }

    public void deleteById(long idPhone){
        phoneRepository.deleteById(idPhone);
    }

    public Phone update(Phone phone){
        return phoneRepository.save(phone);
    }

}
