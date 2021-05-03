package com.company;

import java.security.MessageDigest;

public class HexToWIF {
    public static void convert(String hexText) throws Exception{

    hexText = "80" + hexText;
    byte[] data = hexStringToByteArray(hexText);
    byte[] digest = MessageDigest.getInstance("SHA-256").digest(hexStringToByteArray(hexText));
    byte[] digest2 = MessageDigest.getInstance("SHA-256").digest(digest);
    String first8chars = bytesToHex(digest2).substring(0,8);
    String step1and4 = hexText + first8chars;
        System.out.println(new Base58().encode(hexStringToByteArray(step1and4)));
}

    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            try {
                data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                        + Character.digit(s.charAt(i + 1), 16));
            }catch (StringIndexOutOfBoundsException e){
                System.out.println(s);

            }
        }
        return data;
    }

    public static String bytesToHex(byte[] bytes) {
        StringBuffer result = new StringBuffer();
        for (byte b : bytes) result.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
        return result.toString();
    }
}
