package com.company;

public class ASCIIToHex {
    public static String  convert(String in) {
        // Step-1 - Convert ASCII string to char array
        char[] ch = in.toCharArray();

        // Step-2 Iterate over char array and cast each element to Integer.
        StringBuilder builder = new StringBuilder();

        for (char c : ch) {
            int i = (int) c;
            // Step-3 Convert integer value to hex using toHexString() method.
            builder.append(Integer.toHexString(i).toUpperCase());
        }

        return builder.toString();
    }
}
