package utilities;
import java.util.Random;

public class FakerUtils {

    private static int generateRandomDigit() {
        Random random = new Random();
        return random.nextInt(10); // Generates a random digit between 0 and 9
    }
    public static String generateName(){
        return "Playlist" + generateRandomDigit();
    }

    public static String generateDescription(){
        return "Testing a playlist" + generateRandomDigit();
    }


    public static String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder randomString = new StringBuilder(length);
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            char randomChar = characters.charAt(random.nextInt(characters.length()));
            randomString.append(randomChar);
        }

        return randomString.toString();
    }

    public static String generateArraysOfIds(){
        String id1=generateRandomString(22);
        String id2=generateRandomString(22);
       return  "{ \"ids\":[ \"" + id1 + "\", \"" + id2 + "\"]}";
    }
}
