package com.company;

import org.bitcoinj.core.*;

import java.math.BigInteger;
import java.security.MessageDigest;


import static com.company.HexToWIF.bytesToHex;
import static com.company.HexToWIF.hexStringToByteArray;

public class HexToAddreses {
    public static void convert(String k) throws Exception{
       /* KeyPairGenerator keyGen = KeyPairGenerator.getInstance("EC");
        ECGenParameterSpec ecSpec = new ECGenParameterSpec("secp256k1");
        keyGen.initialize(ecSpec);
        KeyPair kp = keyGen.generateKeyPair();

        PublicKey pub = kp.getPublic();
        ECPublicKey epub = (ECPublicKey) pub;
        ECPoint pt = epub.getW();
        String sx = adjustTo64(pt.getAffineX().toString(16)).toUpperCase();
        String sy = adjustTo64(pt.getAffineY().toString(16)).toUpperCase();
        String bcPub = "04" + sx + sy;
        System.out.println("bcPub: " + bcPub);
    }
    static private String adjustTo64(String s) {
        switch(s.length()) {
            case 62: return "00" + s;
            case 63: return "0" + s;
            case 64: return s;
            default:
                throw new IllegalArgumentException("not a valid key: " + s);
        }*/
        // An example of private key from the book 'Mastering Bitcoin'


        // Converting our string encoding as an actual number
      /*  BigInteger priv = new BigInteger(k,16);

        // Creating a key object from our private key, with compressed public key
        ECKey k1 = ECKey.fromPrivate(priv, true);
        // Creating a key object from our private key, with uncompressed public key
        ECKey k2 = ECKey.fromPrivate(priv, false);
        // 03f028892bad7ed57d2fb57bf33081d5cfcf6f9ed3d3d7f159c2e2fff579dc341a
        //System.out.println(k1.getPublicKeyAsHex()); // compressed

        // 04f028892bad7ed57d2fb57bf33081d5cfcf6f9ed3d3d7f159c2e2fff579dc341a...
        //...07cf33da18bd734c600b96a72bbc4749d5141c90ec8ac328ae52ddfe2e505bdb
        //System.out.println(k2.getPublicKeyAsHex()); // uncompressed

        NetworkParameters main = MainNetParams.get();   // main bitcoin network

        String addr1 = k1.getPrivateKeyAsWiF(main); // main network, compressed
        String addr3 = k2.getPrivateKeyAsWiF(main); // main network, uncompressed

        System.out.println("Uncompressed WIF \n"+addr3.toString()); // 1424C2F4bC9JidNjjTUZCbUxv6Sa1Mt62x
        System.out.println("Compressed WIF \n" +addr1.toString()); // 1J7mdg5rbQyUHENYdx39WVWK7fsLpEoXZy

        hexToUncompressed(k2);
        hexToCompressed(k1);
*/
        /*
        byte[] step2Bytes = MessageDigest.getInstance("SHA-256").digest(hexStringToByteArray(k2.getPublicKeyAsHex()));
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


        System.out.println("Uncompressed address " + new Base58().encode(hexStringToByteArray(step8Hex)));
        */


    }
    public static String pubKeyToUncompressed(String pubkey) throws Exception{

        //byte[] step2Bytes = MessageDigest.getInstance("SHA-256").digest(hexStringToByteArray(k2.getPublicKeyAsHex()));
        byte[] step2Bytes = MessageDigest.getInstance("SHA-256").digest(hexStringToByteArray(pubkey));

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
        return new Base58().encode(hexStringToByteArray(step8Hex));
    }
    public static String pubKeyToCompressed(String pubkey) throws Exception{

        //byte[] step2Bytes = MessageDigest.getInstance("SHA-256").digest(hexStringToByteArray(k2.getPublicKeyAsHex()));
        byte[] step2Bytes = MessageDigest.getInstance("SHA-256").digest(hexStringToByteArray(pubkey));
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
        return new Base58().encode(hexStringToByteArray(step8Hex));
    }
    public static String hexToUncompressed(String hex130) throws Exception{
        BigInteger priv = new BigInteger(hex130,16);
        ECKey k2 = ECKey.fromPrivate(priv, false);
        byte[] step2Bytes = MessageDigest.getInstance("SHA-256").digest(hexStringToByteArray(k2.getPublicKeyAsHex()));
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
        //System.out.println("Uncompressed address \n" + new Base58().encode(hexStringToByteArray(step8Hex)));
        return new Base58().encode(hexStringToByteArray(step8Hex));
    }
    public static String hexToСompressed(String hex130) throws Exception{
        BigInteger priv = new BigInteger(hex130,16);
        ECKey k2 = ECKey.fromPrivate(priv, true);
        byte[] step2Bytes = MessageDigest.getInstance("SHA-256").digest(hexStringToByteArray(k2.getPublicKeyAsHex()));
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
        //System.out.println("Uncompressed address \n" + new Base58().encode(hexStringToByteArray(step8Hex)));
        return new Base58().encode(hexStringToByteArray(step8Hex));
    }
}