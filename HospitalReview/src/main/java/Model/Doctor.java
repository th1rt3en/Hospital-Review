/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author hai06
 */
public class Doctor {
    private int id;
    private String firstName;
    private String lastName;
    private String gender;
    private String degree;
    private boolean acceptedInsurance;
    private String officeHours;
    private String languages;
    private int hospitalId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public boolean isAcceptedInsurance() {
        return acceptedInsurance;
    }

    public void setAcceptedInsurance(boolean acceptedInsurance) {
        this.acceptedInsurance = acceptedInsurance;
    }

    public String getOfficeHours() {
        return officeHours;
    }

    public void setOfficeHours(String officeHours) {
        this.officeHours = officeHours;
    }

    public String getLanguages() {
        return languages;
    }

    public void setLanguages(String languages) {
        this.languages = languages;
    }

    public int getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(int hospitalId) {
        this.hospitalId = hospitalId;
    }

    public Doctor() {
    }
    
    @Override
    public String toString() {
        return String.join(",", firstName, lastName, gender, degree, String.valueOf(acceptedInsurance), officeHours, languages, String.valueOf(hospitalId));
    }
}
