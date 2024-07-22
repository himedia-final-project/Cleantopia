package aircleanprojectback.restapi.util;

import java.util.Random;

public class RandomStringGenerator {

    public static String getPassword(){
        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";
        Random random = new Random();

        StringBuilder randomPass = new StringBuilder();

        for (int i =0 ; i<2 ; i++){
            int index = random.nextInt(letters.length());
            randomPass.append(letters.charAt(index));


        }

        for(int i = 0 ; i < 4 ;i++){
            int index = random.nextInt(numbers.length());
            randomPass.append(numbers.charAt(index));
        }

        return randomPass.toString();

    }
}
