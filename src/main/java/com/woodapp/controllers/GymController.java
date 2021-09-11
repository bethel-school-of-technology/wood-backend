package com.woodapp.controllers;

import com.woodapp.models.Gym;
import com.woodapp.repositories.GymRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/gym")
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class GymController {

    GymRepository dao;

    @GetMapping("/find/all")
    public List<Gym> getGyms() {
        List<Gym> foundGyms = dao.findAll();
        return foundGyms;
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Gym> getOneGym(@PathVariable("id") Integer id) {
        Gym foundGym = dao.findById(id).orElse(null);
        if(foundGym == null) {
            return ResponseEntity.notFound().header("Gym","Nothing found with that id").build();
        }
        return ResponseEntity.ok(foundGym);
    }

    @PostMapping("/add")
    public ResponseEntity<Gym> postOneGym(@RequestBody Gym gym) {
        Gym createdGym = dao.save(gym);
        return ResponseEntity.ok(createdGym);
    }

    @PutMapping("/update/{id}")
    public Gym updateGym(@PathVariable("id") Integer id, @RequestBody Gym gym)
            throws Exception {
        Gym foundGym = dao.findById(id).orElse(null);
        foundGym.setName(gym.getName());
        foundGym.setPhoneNumber(gym.getPhoneNumber());
        foundGym.setStreetAddress(gym.getStreetAddress());
        foundGym.setCity(gym.getCity());
        foundGym.setState(gym.getState());
        foundGym.setZipCode(gym.getZipCode());
        dao.save(foundGym);
        return foundGym;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Gym> deleteGym(@PathVariable("id") Integer id) {
        Gym foundGym = dao.findById(id).orElse(null);
        if(foundGym == null) {
            return ResponseEntity.notFound().header("Gym","Nothing found with that id").build();
        }else {
            dao.delete(foundGym);
        }
        return ResponseEntity.ok().build();
    }


}
