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
public class GenerateVnpTxnRef {
   public static String generateVnpTxnRef() {
    String timePart = new SimpleDateFormat("yyMMddHHmm").format(new Date()); // 12 ký tự: ngày giờ
    String randomPart = randomAlphaNumeric(3); // thêm 3 ký tự random
    return "VT" + timePart + randomPart;
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
    public static void main(String[] args) {
        System.out.println(GenerateVnpTxnRef.generateVnpTxnRef());
    }
  
}
