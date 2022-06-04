package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.utils.ConvertDataService;
import com.udacity.jdnd.course3.critter.user.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {

    @Autowired
    private PetService petService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ConvertDataService convertDataService;

    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {
        return convertDataService.toPetDTO(petService.savePet(convertDataService.toPet(petDTO)));
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {
        return convertDataService.toPetDTO(petService.getPet(petId));
    }

    @GetMapping
    public List<PetDTO> getPets(){
        return petService.getPets().stream().map(pet -> convertDataService.toPetDTO(pet)).collect(Collectors.toList());
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
        return petService.getPetsByOwner(ownerId).stream().map(pet -> convertDataService.toPetDTO(pet)).collect(Collectors.toList());
    }
}
