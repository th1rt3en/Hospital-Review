/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author PC
 */
public class hos_bookmark {
    
    int patientID;
    int hosID;
    
    public hos_bookmark(int patientID, int hosID){
        this.patientID = patientID;
        this.hosID = hosID;
    }
    
    public int getpatientID(){
        return this.patientID;
    }
}
