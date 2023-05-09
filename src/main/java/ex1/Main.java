package ex1;

import ex1.UtilitatsXifrar;

import javax.crypto.BadPaddingException;
import javax.crypto.SecretKey;

import java.util.Arrays;

import static ex1.UtilitatsXifrar.encryptData;

public class Main {
    public static void main(String[] args) {
        String textToEncrypt = "Texto secreto";
        int keySize = 128;

        // Generar la clave de cifrado
        SecretKey key = UtilitatsXifrar.passwordKeyGeneration("Mi contraseña", keySize);

        // Encriptar el texto
        byte[] encryptedData = encryptData(key, textToEncrypt.getBytes());

        // Mostrar el texto encriptado
        System.out.println("Texto encriptado: " + new String(encryptedData));

        // Desencriptar el texto
        byte[] decryptedData = UtilitatsXifrar.decryptData(key, encryptedData);

        // Mostrar el texto desencriptado
        System.out.println("Texto desencriptado: " + new String(decryptedData));

        //----------------------------------------------------------------------------
        String password2 = "mypassword";
        SecretKey keyP = UtilitatsXifrar.passwordKeyGeneration(password2, 128);

        // Obtener el algoritmo utilizado para generar la clave
        String algorithm = keyP.getAlgorithm();
        System.out.println("Algoritme: " + algorithm);

        // Obtener la clave en bytes
        byte[] keyBytes = keyP.getEncoded();
        System.out.println("Key bytes: " + Arrays.toString(keyBytes));

        String password = "mypassword";
        SecretKey key3 = UtilitatsXifrar.passwordKeyGeneration(password, 128);

        // Texto cifrado del punto 6
        // -----------------------------------Texto cifrado del punto 6

        // Desencriptar el texto cifrado
        try {
            decryptedData = UtilitatsXifrar.decryptData(key3, encryptedData);
            String decryptedText = new String(decryptedData, "UTF-8");
            System.out.println("Decrypted text: " + decryptedText);
        } catch (Exception ex) {
            if (ex instanceof BadPaddingException) {
                System.err.println("La contraseña es incorrecta");
            } else {
                System.err.println("Error al desencriptar el texto: " + ex.getMessage());
            }
        }        try {
            decryptedData = UtilitatsXifrar.decryptData(key, encryptedData);
            String decryptedText = new String(decryptedData, "UTF-8");
            System.out.println("Decrypted text: " + decryptedText);
        } catch (Exception ex) {
            if (ex instanceof BadPaddingException) {
                System.err.println("La contraseña es incorrecta");
            } else {
                System.err.println("Error al desencriptar el texto: " + ex.getMessage());
            }
        }
    }
}
