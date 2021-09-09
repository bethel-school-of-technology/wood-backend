package com.woodapp.controllers;

import com.woodapp.models.GymInfo;
import com.woodapp.models.Post;
import com.woodapp.repositories.GymRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api")
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class GymController {

    GymRepository dao;

    @GetMapping("/gym")
    public List<GymInfo> getGyms() {
        List<GymInfo> foundGyms = dao.findAll();
        return foundGyms;
    }

    @GetMapping("/gym/{id}")
    public ResponseEntity<GymInfo> getOneGym(@PathVariable("id") Integer id) {
        GymInfo foundGym = dao.findById(id).orElse(null);
        if(foundGym == null) {
            return ResponseEntity.notFound().header("Gym","Nothing found with that id").build();
        }
        return ResponseEntity.ok(foundGym);
    }

    @PostMapping("/gym")
    public ResponseEntity<GymInfo> postOneGym(@RequestBody GymInfo gymInfo) {
        GymInfo createdGym = dao.save(gymInfo);
        return ResponseEntity.ok(createdGym);
    }

    @PutMapping("/gym/{id}")
    public GymInfo updateGym(@PathVariable("id") Integer id, @RequestBody GymInfo gymInfo)
            throws Exception {
        GymInfo foundGym = dao.findById(id).orElse(null);
        foundGym.setGymName(gymInfo.getGymName());
        foundGym.setPhoneNumber(gymInfo.getPhoneNumber());
        foundGym.setStreetAddress(gymInfo.getStreetAddress());
        foundGym.setCity(gymInfo.getCity());
        foundGym.setState(gymInfo.getState());
        foundGym.setZipCode(gymInfo.getZipCode());
        dao.save(foundGym);
        return foundGym;
    }

    @DeleteMapping("/gym/{id}")
    public ResponseEntity<GymInfo> deleteGym(@PathVariable("id") Integer id) {
        GymInfo foundGym = dao.findById(id).orElse(null);
        if(foundGym == null) {
            return ResponseEntity.notFound().header("Gym","Nothing found with that id").build();
        }else {
            dao.delete(foundGym);
        }
        return ResponseEntity.ok().build();
    }


}
