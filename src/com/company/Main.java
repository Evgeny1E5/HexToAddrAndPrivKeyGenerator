package com.company;


import java.io.*;
import java.math.BigInteger;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import com.company.Base58;


import javax.print.attribute.standard.Media;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.time.Duration;
import java.time.Instant;
import java.util.*;

import static com.company.HexToWIF.bytesToHex;
import static com.company.HexToWIF.hexStringToByteArray;
import static com.company.ASCIIToHex.*;


public  class Main {
    static BufferedWriter bw;
    static BufferedReader br;
    static BufferedReader br2;
    static BufferedWriter bwProgress;

    public static void main(String[] args) throws Exception {
        Test.test();
        System.out.println(Runtime.getRuntime().totalMemory());
        System.out.println(Runtime.getRuntime().freeMemory());

        String line = "", test = "";
        int i = 0;
        //TreeSet<String> listBalances = new TreeSet<>();
        ArrayList<String> list = new ArrayList<>();
        //new HexToWIF().convert("0000000000000000000000000000000000000000000000000000000000000031");
        br = new BufferedReader(new FileReader("pub_keys1.txt")); //Input file with private key or public key or brainwallet words
        br2 = new BufferedReader(new FileReader("addressesPuzzles")); // Input file with addresses
        bw = new BufferedWriter(new FileWriter("out.txt", true)); //Output file result
        bwProgress = new BufferedWriter(new FileWriter("outProgress.txt")); //Output file result

        HashMap<String, Long> listBalances = new HashMap<>();

        //parsing hex string
        /*
        while ((line = br.readLine()) != null){
            //System.out.println(line);
            list.add(line);
            i++;
            if(i % 1000000 == 0) System.out.println("Reading dict, " + i + "M");
        }
        System.out.println("Readed " + i + " lines");
        */

        System.out.println("Parsing balances");
        //parsing addreses without balances to arraylist
        i = 0;
        while ((line = br2.readLine()) != null){

            i++;
            if(i % 1000000 == 0) {
                System.out.println(i/1000000 + "M");
            }
            listBalances.put(line, 1L);

        }

        //parsing addreses with balances to arraylist
        /*i = 0;
        while ((line = br2.readLine()) != null){

            i++;
            if(i % 1000000 == 0) {
                System.out.println(i/1000000 + "M");
            }
            String arr[] = line.split(" ");
            listBalances.put(arr[0], Long.valueOf(arr[1]));

        }*/
        System.out.println("Balances parsed");
        System.out.println("Start searching");


        findHexStrings(listBalances);
        //findStringsToSha256(listBalances);
        //findBrainwallet(listBalances);
        //addressesAndBalancesToFile(listBalances);
        //findPuzzleWallet();
        //findPubKeyBalance(listBalances);


        br.close();
        br2.close();
        bw.close();

    }
    private static void addressesAndBalancesToFile(HashMap<String, Long> listBalances) throws Exception {
        for(Map.Entry<String, Long> map: listBalances.entrySet()) {
            bw.write(map.getKey());
            bw.newLine();
        }
        bw.close();
    }


    private static void findBrainwallet(HashMap<String, Integer> listBalances) throws Exception {
        String line = "", address, addressCompressed;
        int i = 0;
        long start=0, end;
        while((line = br.readLine()) != null) {
            i++;
            //if statement for continue, if program execution was interrupted
            if(i>575700000) {
                address = new HexToAddreses().hexToUncompressed(bytesToHex(MessageDigest.getInstance("SHA-256").digest(hexStringToByteArray(ASCIIToHex.convert(line)))));
                addressCompressed = new HexToAddreses().hexToСompressed(bytesToHex(MessageDigest.getInstance("SHA-256").digest(hexStringToByteArray(ASCIIToHex.convert(line)))));
                //System.out.println(address);
                //System.out.println(addressCompressed);
                if (listBalances.get(address) != null) {
                    bw.write("Address - " + address + " brainwallet key - " + line + "\n");
                    bw.flush();
                    // System.out.println(listBalances.get(address));
                }
                if (listBalances.get(addressCompressed) != null) {
                    bw.write("Address - " + addressCompressed + " brainwallet key - " + line + "\n");
                    bw.flush();
                    // System.out.println(listBalances.get(addressCompressed));
                }
                //System.out.println(i);
                if (i % 100000 == 0) {
                    System.out.println("Checked " + i);
                    end = System.nanoTime();
                    System.out.println(((end - start)/1000000000) + " seconds");
                    start = System.nanoTime();
                    bwProgress.write("Checked " + i + "\n");
                    bwProgress.flush();
                }
            }else if (i % 1000000 == 0) System.out.println("Skiped " + i);
        }
        bw.close();

    }

    private static void findStringsToSha256(HashMap<String, Integer> listBalances) throws Exception {
        String line = "", address, addressCompressed;
        int i = 0;
        long start=0, end;
        while((line = br.readLine()) != null) {
            i++;
            //if statement for continue, if program execution was interrupted
            if(i>0) {
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
                if (i % 100000 == 0){
                    System.out.println("Checked " + i);
                    end = System.nanoTime();
                    System.out.println(((end - start)/1000000000) + " seconds");
                    start = System.nanoTime();
                    bwProgress.write("Checked " + i + "\n");
                    bwProgress.flush();
                }
            }else if (i % 1000000 == 0) System.out.println("Skiped " + i);
        }
        bw.close();
    }



    private static void findHexStrings(HashMap<String, Long> listBalances) throws Exception {
        String line = "";
        ArrayList<String> listAddresses = new ArrayList<>();

        while ((line = br.readLine()) != null) {
            if (listAddresses.size() < 1000000) {
                listAddresses.add(line);
            }else findWithList(listAddresses, listBalances);
        }
        bw.close();
    }
    private  static void findWithList(ArrayList<String> listAddresses, HashMap<String, Long> listBalances) throws Exception{
        String line = "", address, addressCompressed;
        int i = 0;
        long start=0, end;
        while(listAddresses.size() > 0){
            line = listAddresses.get(i);
            listAddresses.remove(i);
            i++;
            if (i > 0) {    //if statement for continue, if program execution was interrupted
                address = new HexToAddreses().hexToUncompressed(line);
                addressCompressed = new HexToAddreses().hexToСompressed(line);
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
                if (listBalances.get(address) != null) {
                    bw.write("Private key " + (line) + " address - " + address + " balance - " + listBalances.get(address) + "\n");
                    bw.flush();
                    // System.out.println(listBalances.get(address));
                }
                if (listBalances.get(addressCompressed) != null) {
                    bw.write("Private key " + (line) + " address - " + addressCompressed + " balance - " + listBalances.get(addressCompressed) + "\n");
                    bw.flush();
                    // System.out.println(listBalances.get(addressCompressed));
                }
                if (i % 100000 == 0){
                    System.out.println("Checked " + i);
                    end = System.nanoTime();
                    System.out.println(((end - start)/1000000000) + " seconds");
                    start = System.nanoTime();
                    bwProgress.write("Checked " + i + "\n");
                    bwProgress.flush();
                }

            }else if (i % 1000000 == 0) System.out.println("Skiped " + i);
        }
    }

    private static void findPubKeyBalance(HashMap<String, Long> listBalances) throws Exception {
        String line = "", address, addressCompressed;
        int i = 0;
        long start=0, end;
        //ArrayList<String> list = new ArrayList<>();

        while ((line = br.readLine()) != null) {
            i++;
            //if statement for continue, if program execution was interrupted
            if (i > 0) {
                address = new HexToAddreses().pubKeyToUncompressed(line);
                addressCompressed = new HexToAddreses().pubKeyToUncompressed(line);


                if (listBalances.get(addressCompressed) != null) {
                    bw.write("Public key " + (line) + " Balance " + listBalances.get(addressCompressed) + "\n");
                   // System.out.println("Public key " + (line) + " Balance " + listBalances.get(addressCompressed));
                    bw.flush();
                }
                if (listBalances.get(address) != null) {
                    bw.write("Public key " + (line) + " Balance " + listBalances.get(address) + "\n");
                    //System.out.println("Public key " + (line) + " Balance " + listBalances.get(address));
                    bw.flush();
                }
                if (i % 100000 == 0){
                    System.out.println("Checked " + i);
                    end = System.nanoTime();
                    System.out.println(((end - start)/1000000000) + " seconds");
                    start = System.nanoTime();
                    bwProgress.write("Checked " + i + "\n");
                    bwProgress.flush();
                }

            }else if (i % 1000000 == 0) System.out.println("Skiped " + i);
        }
        bw.close();
    }
}