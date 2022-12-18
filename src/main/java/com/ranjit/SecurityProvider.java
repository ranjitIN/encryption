package com.ranjit;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class SecurityProvider {
    
    public SecretKey getSecurityKeyFromFile(String filePath)throws IOException
    {
        FileHandler fileHandler = new FileHandler();
        String fileContent = fileHandler.readDataFromFile(filePath);
        return new SecretKeySpec(fileContent.getBytes(), "AES");
    }

    public void saveSecretkey(String filepath,SecretKey secretKey)throws Exception
    {
        FileHandler fileHandler = new FileHandler();
        final byte[] encodedkey = secretKey.getEncoded();
        fileHandler.writeIntoFile(filepath, new String(encodedkey));
    }

    public SecretKey generatekey(String key)throws Exception
    {
        final SecureRandom random = new SecureRandom(new String(key).getBytes());
        final KeyGenerator instance = KeyGenerator.getInstance("AES");
        instance.init(256, random);
        return instance.generateKey();
    }

    public void encryptFile(String rawFilePath,String encryptedFilePath,SecretKey secretKey)throws Exception
    {
        FileHandler fileHandler = new FileHandler();
        String fileContent = fileHandler.readDataFromFile(rawFilePath);   
        Cipher encryptionCypher = Cipher.getInstance("AES/CFB/NoPadding");
        encryptionCypher.init(encryptionCypher.ENCRYPT_MODE, secretKey, new IvParameterSpec(new byte[encryptionCypher.getBlockSize()]));
        byte[] finalMessage = encryptionCypher.doFinal(fileContent.getBytes());
        // String encryptedData = new String(finalMessage);
        String encryptedData = new String(Base64.getEncoder().encode(finalMessage));
        // System.out.println(encryptedData);
        fileHandler.writeIntoFile(encryptedFilePath, encryptedData);
        fileHandler.inputStream.close();
        System.out.println("Encryption Complete");
    }

    public void decryptFile(String encryPtedFilePath,String decryptedFilePath,SecretKey secretKey)throws Exception
    {
        FileHandler fileHandler = new FileHandler();
        String fileContent = fileHandler.readDataFromFile(encryPtedFilePath);
        Cipher decryptionCipher = Cipher.getInstance("AES/CFB/NoPadding");
        decryptionCipher.init(decryptionCipher.DECRYPT_MODE, secretKey,new IvParameterSpec(new byte[decryptionCipher.getBlockSize()]));
        byte[] plainText = decryptionCipher.doFinal(Base64.getDecoder().decode(fileContent.getBytes()));
        fileHandler.writeIntoFile(decryptedFilePath, new String(plainText));
        System.out.println("Decryption Complete");
    }
}
