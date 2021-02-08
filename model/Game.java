package model;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;


public class Game {

    public static String createSecretKey() {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[16];
        random.nextBytes(bytes);
        StringBuilder str = new StringBuilder();
        for (byte aByte : bytes) {
            str.append(aByte);
        }
        return new String(str);
    }

    public static String getSha256(String value) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(value.getBytes());
            return bytesToHex(md.digest());
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuffer result = new StringBuffer();
        for (byte b : bytes) {
            result.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
        }
        return new String(result);
    }

    public static int generateComputerMove(String[] arr) {
        return new Random().nextInt(arr.length);
    }

    public static String creatHMAC(String hashSecretKey, String scriptChoice) {
        Mac sha256_HMAC = null;
        try {
            sha256_HMAC = Mac.getInstance("HmacSHA256");
            sha256_HMAC.init(new SecretKeySpec(hashSecretKey.getBytes(), "HmacSHA256"));
            byte[] result = sha256_HMAC.doFinal(scriptChoice.getBytes());
            return DatatypeConverter.printHexBinary(result);
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }

    public static void writeWhoWin(String[] arr, int userMove, int computerChoice) {
        computerChoice++; // increment index because array start from 0
        int a = (arr.length - 1) / 2;
        if (userMove > computerChoice && (computerChoice + a == userMove || computerChoice + a >= userMove + 1)) {
            System.out.println("You win");
        } else if (userMove > computerChoice && arr.length - computerChoice <= userMove + 1) {
            System.out.println("You lose");
        } else if (computerChoice > userMove && computerChoice - userMove <= a) {
            System.out.println("You lose");
        } else if (userMove == computerChoice) {
            System.out.println("Draw");
        } else {
            System.out.println("You win");
        }
    }
}



