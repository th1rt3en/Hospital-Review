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
public class Hospital {
    private int id;
    private String name;
    private String address;
    private String website;
    private String hospitalAdminName;
    private String hospitalAdminEmailAddress;

    public Hospital(int id, String name, String address, String website, String hospitalAdminName, String hospitalAdminEmailAddress) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.website = website;
        this.hospitalAdminName = hospitalAdminName;
        this.hospitalAdminEmailAddress = hospitalAdminEmailAddress;
    }

    public Hospital() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getHospitalAdminName() {
        return hospitalAdminName;
    }

    public void setHospitalAdminName(String hospitalAdminName) {
        this.hospitalAdminName = hospitalAdminName;
    }

    public String getHospitalAdminEmailAddress() {
        return hospitalAdminEmailAddress;
    }

    public void setHospitalAdminEmailAddress(String hospitalAdminEmailAddress) {
        this.hospitalAdminEmailAddress = hospitalAdminEmailAddress;
    }
}
