package com.company;


import java.io.*;
import java.math.BigInteger;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import com.company.Base58;
import org.bitcoinj.core.VerificationException;
import org.bouncycastle.util.encoders.Hex;

import javax.print.attribute.standard.Media;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.time.Duration;
import java.time.Instant;
import java.util.*;

import static com.company.HexToWIF.bytesToHex;
import static com.company.HexToWIF.hexStringToByteArray;


public  class Main {
    static BufferedWriter bw;
    static BufferedReader br;

    public static void main(String[] args) throws Exception {
       // Test.test();

        String line = "";
        int i = 0;
        ArrayList<String> list = new ArrayList<>();
        //new HexToWIF().convert("0000000000000000000000000000000000000000000000000000000000000031");
        br = new BufferedReader(new FileReader("dict.txt"));
        BufferedReader br2 = new BufferedReader(new FileReader("bitcoinAddresses18042021.tsv"));
        bw = new BufferedWriter(new FileWriter("out.txt"));
        HashMap<String, Long> listBalances = new HashMap<>();

        //parsing hex string
       /* while ((line = br.readLine()) != null){
            //System.out.println(line);
            list.add(line);
            i++;
            if(i % 1000000 == 0) System.out.println("Reading dict, " + i + "M");
        }
        System.out.println("Readed " + i + " lines");
*/
        System.out.println("Parsing balances");
        i = 0;
        while ((line = br2.readLine()) != null){
            String[] arr = line.split("\t");
            i++;
            if(i % 1000000 == 0) {
                System.out.println(i/1000000 + "M");
                //Thread.sleep(3000);
            }
            listBalances.put(arr[0], Long.valueOf(arr[1]));

        }
        System.out.println("Balances parsed");

     /*   BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();*/
        //new HexToAddreses().convert(line);

        //findHexStrings(list, listBalances);
        System.out.println("Start searching");
        findStringsToSha256(listBalances);
        br.close();
        br2.close();

    }

    private static void findStringsToSha256(HashMap<String, Long> listBalances) throws Exception {
        String line = "", address, addressCompressed;
        int i = 0;
        while((line = br.readLine()) != null) {
            i++;
            //if statement for continue, if program execution was interrupted
            if(i>476000000) {
                address = new HexToAddreses().hexToUncompressed(bytesToHex(MessageDigest.getInstance("SHA-256").digest(hexStringToByteArray(line))));
                addressCompressed = new HexToAddreses().hexToСompressed(bytesToHex(MessageDigest.getInstance("SHA-256").digest(hexStringToByteArray(line))));

                if (listBalances.get(address) != null) {
                    bw.write("Balance - " + listBalances.get(address) + " Public key - " + line + " address - " + address + "\n");
                    bw.flush();
                    // System.out.println(listBalances.get(address));
                }
                if (listBalances.get(addressCompressed) != null) {
                    bw.write("Balance - " + listBalances.get(addressCompressed) + " Public key - " + line + " address - " + address + "\n");
                    bw.flush();
                    // System.out.println(listBalances.get(addressCompressed));
                }
                if (i % 100000 == 0) System.out.println("Checked " + i);
            }else if (i % 1000000 == 0) System.out.println("Skiped " + i);
        }
        bw.close();
    }



    private static void findHexStrings(ArrayList<String> list, HashMap<String, Long> listBalances) throws Exception {
        String line = "", address, addressCompressed;
        for(int i = 0;i<list.size();i++) {
            address = new HexToAddreses().hexToUncompressed(list.get(i));
            addressCompressed = new HexToAddreses().hexToСompressed(list.get(i));
            //System.out.println(address);
            // Instant start = Instant.now();
            /*for(Map.Entry<String, Long> entry : listBalances.entrySet()){
                if(entry.getKey().equals(address)){
                    bw.write("Private key № " + (i+1) + " address - " + address + "balance - " + entry.getValue() + "\n");
                    bw.flush();
                }
            }
            for(Map.Entry<String, Long> entry : listBalances.entrySet()){
                if(entry.getKey().equals(addressCompressed)){
                    bw.write("Private key № " + (i+1) + " address - " + address + " balance - " + entry.getValue() + "\n");
                    bw.flush();
                    System.out.println(listBalances.get(addressCompressed));
                }
            }*/
            if(listBalances.get(address) != null){
                bw.write("Private key № " + (i+1) + " address - " + address + " balance - " + listBalances.get(address) + "\n");
                bw.flush();
                // System.out.println(listBalances.get(address));
            }
            if(listBalances.get(addressCompressed) != null){
                bw.write("Private key № " + (i+1) + " address - " + addressCompressed + " balance - " + listBalances.get(addressCompressed) + "\n");
                bw.flush();
                // System.out.println(listBalances.get(addressCompressed));
            }
            if(i % 100000 == 0) System.out.println("Checked " + i);
            /*Instant end = Instant.now();
            System.out.println(Duration.between(start, end));*/
        }
        bw.close();
    }
}