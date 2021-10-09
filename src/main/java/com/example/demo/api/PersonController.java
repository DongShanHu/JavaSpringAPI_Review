package com.example.demo.api;

import com.example.demo.model.Person;
import com.example.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/person")
public class PersonController {
    private  final PersonService personService;
    @Autowired
    public  PersonController(PersonService personService){
        this.personService=personService;
    }

    @PostMapping
    public void addPerson(@RequestBody Person person){
        personService.addPerson(person);
    }

    @GetMapping
    public List<Person>getPeople(){
        return personService.getAllpeople();
    }
    @GetMapping(path ="{id}")
    public  Person getPersonById(@PathVariable("id") UUID id){
        return  personService.getPersonById(id).orElse(null);

    }

    @DeleteMapping(path ="{id}")
    public void   DeletePersonById(@PathVariable("id") UUID id){
          personService.deletePersonById(id);

    }
    public void   UpdatePersonById(@PathVariable("id")UUID id, @RequestBody Person personToUpdate){
        personService.updatetePersonById(id,personToUpdate);

    }











}
