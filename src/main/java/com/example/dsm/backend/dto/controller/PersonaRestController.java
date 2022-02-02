package com.example.dsm.backend.dto.controller;

import com.example.dsm.backend.dto.PersonaDTO;
import com.example.dsm.backend.model.Persona;
import com.example.dsm.backend.service.api.PersonaServiceAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/persona/api/v1/")
@CrossOrigin("*")
public class PersonaRestController {

    @Autowired
    private PersonaServiceAPI personaServiceAPI;

    @GetMapping("/saludo")
    public String saludo() {
        return "Hola personas";
    }

    @GetMapping(value = "all")
    public List<PersonaDTO> getAll() throws Exception {
        return personaServiceAPI.getAll();
    }

    @GetMapping(value = "find/{id}")
    public PersonaDTO find(@PathVariable String id) throws Exception {
        return personaServiceAPI.get(id);
    }

    @PostMapping(value = "save/{id}")
    public ResponseEntity<String> save(@RequestBody Persona persona, @PathVariable String id) throws Exception {
        if (id == null || id.length() == 0 || id.equals("null")) {
            id = personaServiceAPI.save(persona);
        } else {
            personaServiceAPI.save(persona, id);
        }
        return new ResponseEntity<String>(id, HttpStatus.OK);
    }

    @GetMapping(value = "delete/{id}")
    public ResponseEntity<PersonaDTO> delete(@PathVariable String id) throws Exception {
        PersonaDTO persona = personaServiceAPI.get(id);
        if (persona != null) {
            personaServiceAPI.delete(id);
        } else {
            return new ResponseEntity<PersonaDTO>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<PersonaDTO>(persona, HttpStatus.OK);
    }

}
