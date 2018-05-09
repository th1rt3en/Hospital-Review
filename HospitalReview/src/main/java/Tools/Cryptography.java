/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

import org.apache.commons.codec.binary.Base64;

/**
 *
 * @author Negarr
 */
public class Cryptography {
    public static String encrypt(String password) {
        return new String(Base64.encodeBase64(password.getBytes()));
    }
    public static String decrypt(String encrypted) {
        return new String(Base64.decodeBase64(encrypted.getBytes()));
    }
}
