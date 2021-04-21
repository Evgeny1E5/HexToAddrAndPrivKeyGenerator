package com.company;

import java.security.MessageDigest;

import static com.company.HexToWIF.bytesToHex;
import static com.company.HexToWIF.hexStringToByteArray;

public class Test {
    public static void test() throws Exception{
        String testLine = "04f2dac991cc4ce4b9ea44887e5c7c0bce58c80074ab9d4dbaeb28531b7739f530e0dedc9b3b2f8dad4da1f32dec2531df9eb5fbeb0598e4fd1a117dba703a3c37";
        byte[] sha256Bytes = MessageDigest.getInstance("SHA-256").digest(hexStringToByteArray(testLine));
        String sha256Hex = bytesToHex(sha256Bytes);

        System.out.println(HexToAddreses.hexToUncompressed(sha256Hex));
        System.out.println(HexToAddreses.hexToСompressed(sha256Hex));
    }

}
