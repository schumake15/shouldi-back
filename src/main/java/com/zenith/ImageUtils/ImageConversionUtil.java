/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zenith.ImageUtils;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Base64;
import org.hibernate.Hibernate;

/**
 *
 * @author wayne
 */
public class ImageConversionUtil {
    
    public static Blob convertToBlob(String encodedString) {
        //System.out.println(encodedString); 
        Blob imageToDB;
        byte[] decodedByte = Base64.getDecoder().decode(encodedString); 
        try {
            imageToDB = new javax.sql.rowset.serial.SerialBlob(decodedByte); 
            return imageToDB; 
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; 
    }
    
    public static String convertToB64(Blob image){
        try{
            int len = (int)image.length(); 
            return Base64.getEncoder().encodeToString(image.getBytes(1, len)); 
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null; 
    }
}
