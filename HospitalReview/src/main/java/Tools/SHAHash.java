/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author hai06
 */
public class SHAHash {

    public static String hash(String data) {
        return DigestUtils.sha256Hex(data);
    }
    
    public static void main(String args[]) {
        System.out.print(SHAHash.hash("mo!on@light010200552"));
    }
}
