package com.vemioaap.vemioapi.service;

import com.vemioaap.vemioapi.model.VemioUsers;
import com.vemioaap.vemioapi.repository.VemioUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class VemioUserService {
    @Autowired
    private VemioUserRepository vemioUserService;

    public List<VemioUsers> listAllUser() {
        return vemioUserService.findAll();
    }

    public void saveUser(VemioUsers user) {
        vemioUserService.save(user);
    }


    public VemioUsers getUser(Integer id) {
        return vemioUserService.findById(id).get();
    }

    public void deleteUser(Integer id) {

        vemioUserService.deleteById(id);
    }

}
