/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Negarr
 */
public class Patient extends User{
    private String firstName;
    private String lastName;
    private String address;
    private String gender;
<<<<<<< HEAD
    private String email;
    private String password;
    private String address;
    private String languages;

    public Patient() {
    }

    public Patient(String firstName, String lastName, String gender, String email, String password, String address, String languages) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.email = email;
        this.password = password;
        this.address = address;
        this.languages = languages;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
=======
    private String languages;
>>>>>>> 46337c911e803dd0a8cacfb118ee41e9e0b32a9c

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

<<<<<<< HEAD
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLanguages() {
        return languages;
    }

    public void setLanguages(String languages) {
        this.languages = languages;
=======
    public String getLanguages() {
        return languages;
    }

    public void setLanguages(String languages) {
        this.languages = languages;
    }

    public Patient() {
    }
    
    public Patient(User user) {
        super(user.getId(), user.getEmail(), user.getPassword(), user.getType());
>>>>>>> 46337c911e803dd0a8cacfb118ee41e9e0b32a9c
    }
}