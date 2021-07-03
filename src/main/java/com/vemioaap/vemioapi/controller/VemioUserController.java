package com.vemioaap.vemioapi.controller;

import com.vemioaap.vemioapi.model.VemioUsers;
import com.vemioaap.vemioapi.repository.VemioUserRepository;
import com.vemioaap.vemioapi.service.VemioUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/vemiousers")
public class VemioUserController {


    HashMap<String, Object> map = new HashMap<>();
    @Autowired
    VemioUserService vemioUserService;

    @Autowired
    VemioUserRepository vemioUserRepository;

    @GetMapping("/listAll")
    public List<VemioUsers> list() {
        return vemioUserService.listAllUser();
    }


    @GetMapping("/get/{id}")
    public ResponseEntity<VemioUsers> get(@PathVariable Integer id) {
        try {
            VemioUsers user = vemioUserService.getUser(id);
            return new ResponseEntity<VemioUsers>(user, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<VemioUsers>(HttpStatus.NOT_FOUND);

        }
    }


    @PostMapping("/register")
    public HashMap<String, Object> add(@RequestBody VemioUsers newUser) {
        List<VemioUsers> oldusers = vemioUserRepository.findAll();
        for (VemioUsers user : oldusers) {
            System.out.println("Registered user: " + newUser.getEmail().toString());
            if (user.getEmail().equals(newUser.getEmail())) {
                System.out.println("User Already exists!");
                map.put("Response", "Already Exists");
                return map;
            }
        }

        vemioUserService.saveUser(newUser);
        map.put("Response", "OK");
        return map;

    }

    @PostMapping("/login")
    public HashMap<String, Object> login(@RequestBody VemioUsers newUser) {
        String testMail = newUser.getEmail();
        String testpwd = newUser.getPassword();
        List<VemioUsers> oldusers = vemioUserRepository.findAll();
        for (VemioUsers user : oldusers) {
            String mail = user.getEmail();
            String password = user.getPassword();
            if (mail.equals(testMail) && password.equals(testpwd)) {
                System.out.println("Login successfull!");
                map.put("Response", "OK");
                return map;
            }
        }

        map.put("Response", "FAILED");
        return map;

    }


    @PutMapping("/update/{id}")
    public Object update(@RequestBody VemioUsers user, @PathVariable Integer id) {
        try {
            //  User existUser = userService.getUser(id);
            user.setId(id);
            vemioUserService.saveUser(user);

            return new ResponseEntity<>(HttpStatus.OK).getBody();
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND).getStatusCode();
        }
    }


    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Integer id) {
        vemioUserService.deleteUser(id);
    }


}
