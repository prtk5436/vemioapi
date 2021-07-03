package com.vemioaap.vemioapi;


import com.vemioaap.vemioapi.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/listAll")
    public List<User> list() {
        return userService.listAllUser();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<User> get(@PathVariable Integer id) {
        try {
            User user = userService.getUser(id);
            return new ResponseEntity<User>(user, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);

        }
    }


    @PostMapping("/add")
    public HashMap<String, Object> add(@RequestBody User user) {
        userService.saveUser(user);
        HashMap<String, Object> map = new HashMap<>();
        map.put("Response", "OK");
        return map;


    }

    @PutMapping("/update/{id}")
    public Object update(@RequestBody User user, @PathVariable Integer id) {
        try {
            //  User existUser = userService.getUser(id);
            user.setId(id);
            userService.saveUser(user);

            return new ResponseEntity<>(HttpStatus.OK).getBody();
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND).getStatusCode();
        }
    }


    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Integer id) {
        userService.deleteUser(id);
    }

}
