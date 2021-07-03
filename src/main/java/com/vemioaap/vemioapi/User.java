package com.vemioaap.vemioapi;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class User {

    private  int id;
    private String Fname;
    private String Lname;


   /* private  String ResponseStatus="200";
    public String getResponseStatus() {
        return ResponseStatus;
    }

    public void setResponseStatus(String responseStatus) {
        ResponseStatus = responseStatus;
    }*/


    public User() {
    }

    public User(int id, String fname, String lname) {
        this.id = id;
        Fname = fname;
        Lname = lname;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFname() {
        return Fname;
    }

    public void setFname(String fname) {
        Fname = fname;
    }

    public String getLname() {
        return Lname;
    }

    public void setLname(String lname) {
        Lname = lname;
    }
}
