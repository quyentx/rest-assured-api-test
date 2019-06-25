package utils;

public class DataGenerator {

    private static final String ALPHABET_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static String randomString(int count) {
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int)(Math.random()* ALPHABET_STRING.length());
            builder.append(ALPHABET_STRING.charAt(character));
        }
        return builder.toString();
    }
}
