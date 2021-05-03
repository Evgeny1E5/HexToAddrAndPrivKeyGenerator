package com.company;



import org.bitcoinj.core.*;
import org.bouncycastle.jce.interfaces.ECPublicKey;
import org.bouncycastle.math.ec.ECPoint;

import java.math.BigInteger;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.PublicKey;
import java.security.spec.ECGenParameterSpec;


import static com.company.HexToWIF.bytesToHex;
import static com.company.HexToWIF.hexStringToByteArray;

public class Test {
    public static void test() throws Exception{
        /*byte[] step2Bytes = MessageDigest.getInstance("SHA-256").digest(hexStringToByteArray("04f2dac991cc4ce4b9ea44887e5c7c0bce58c80074ab9d4dbaeb28531b7739f530e0dedc9b3b2f8dad4da1f32dec2531df9eb5fbeb0598e4fd1a117dba703a3c37"));

        String step2Hex = bytesToHex(step2Bytes);
        //System.out.println(step2Hex);
        byte[] step3Bytes = Ripemd160.getHash(hexStringToByteArray(step2Hex));
        String step3Hex = bytesToHex(step3Bytes);
        //System.out.println(step3Hex);
        String step4Hex = "00"+step3Hex;
        //System.out.println(step4Hex);
        byte[] step5Bytes = MessageDigest.getInstance("SHA-256").digest(hexStringToByteArray(step4Hex));
        byte[] step6Bytes = MessageDigest.getInstance("SHA-256").digest(step5Bytes);
        String step6Hex = bytesToHex(step6Bytes);
        //System.out.println(step6Hex);
        String step7Hex = step6Hex.substring(0,8);
        //System.out.println(step7Hex);
        String step8Hex = step4Hex + step7Hex;
        //System.out.println(step8Hex);
        System.out.println("Uncompressed address \n" + new Base58().encode(hexStringToByteArray(step8Hex)));
*/
    }

}
