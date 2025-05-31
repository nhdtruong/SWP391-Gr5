/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Random;

/**
 *
 * @author DELL
 */
public class OTPutil {
    

 public static String generateOTP() {
    Random rand = new Random();
    String otp = "";

    for (int i = 0; i < 6; i++) {
        int digit = rand.nextInt(9) + 1; 
        otp += digit;
//        if (i < 5) { 
//            ;
//        }
    }

    return otp;
}
 
    public static void main(String[] args) {
        System.out.println(OTPutil.generateOTP());
    }
    
    
    
    
}

    

