/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package config;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 *
 * @author DELL
 */
public class GenerateAppoinmentCode {
    
    public static String generateAppoinmentCode() {
    String datePart = new SimpleDateFormat("yyMMdd").format(new Date());
    String randomPart = randomAlphaNumeric(6); // Random 6 ký tự
    return "T" + datePart + randomPart;
}

public static String randomAlphaNumeric(int count) {
    String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    StringBuilder builder = new StringBuilder();
    Random random = new Random();
    for (int i = 0; i < count; i++) {
        builder.append(characters.charAt(random.nextInt(characters.length())));
    }
    return builder.toString();
}
}
