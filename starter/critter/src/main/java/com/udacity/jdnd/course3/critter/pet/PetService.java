package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.exception.CustomerNotFoundException;
import com.udacity.jdnd.course3.critter.exception.PetHasNoOwnerException;
import com.udacity.jdnd.course3.critter.exception.PetNotFoundException;
import com.udacity.jdnd.course3.critter.user.Customer;
import com.udacity.jdnd.course3.critter.user.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PetService {
    @Autowired
    private PetRepository petRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public Pet savePet(Pet pet){
        petRepository.save(pet);

        Customer owner = pet.getOwner();
        if(owner == null){
            throw new PetHasNoOwnerException();
        }

        owner.getPets().add(pet);
        customerRepository.save(owner);

        return pet;
    }

    public Pet getPet(long id){
        return petRepository.findById(id).orElseThrow(PetNotFoundException::new);
    }

    public List<Pet> getPets(){
        return petRepository.findAll();
    }

    public List<Pet> getPetsByOwner(long ownerId){
        Customer owner = customerRepository.findById(ownerId).orElseThrow(CustomerNotFoundException::new);
        return owner.getPets();
    }
}
