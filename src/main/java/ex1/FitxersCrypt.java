package ex1;

import javax.crypto.SecretKey;
import java.io.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Scanner;

public class FitxersCrypt {

    public static void main(String[] args) throws UnsupportedEncodingException {

        // llegir el contingut del fitxer textamagat.crypt
        byte[] encryptedContent = FitxersCrypt.llegirFitxer("src/main/resources/textamagat.crypt");

// llegir les possibles contrasenyes del fitxer clausA4.txt
        List<String> passwords = FitxersCrypt.readPasswordsFromFile("src/main/resources/clausA4.txt");
        for (String p : passwords) System.out.println(p);
        System.out.println(encryptedContent);
// provar totes les contrasenyes fins que es trobi la correcta
        String password = null;
        byte[] decryptedData = null;
        for (String possiblePassword : passwords) {
            System.out.println(possiblePassword);

            // intentar desxifrar amb la contrasenya actual
            SecretKey key = UtilitatsXifrar.passwordKeyGeneration(possiblePassword, 256);
            decryptedData = UtilitatsXifrar.decryptData(key, encryptedContent);
            if (decryptedData != null) {
                // si s'ha pogut desxifrar, es pot sortir del bucle i guardar la contrasenya
                password = possiblePassword;
                break;
            }
        }

// comprovar si s'ha trobat la contrasenya correcta i imprimir el contingut desxifrat
        if (password != null) {
            System.out.println("La contrasenya correcta es: " + password);
            System.out.println("El contingut desxifrat es: " + new String(decryptedData));
        } else {
            System.out.println("No s'ha trobat cap contrasenya correcta.");
        }

    }
    public static byte[] llegirFitxer(String nomFitxer) {
        byte[] data=null;
        try {
            FileInputStream fis = new FileInputStream(nomFitxer);
             data = new byte[(int) fis.available()];
            fis.read(data);
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
    public static List<String> readPasswordsFromFile(String filePath) {
        List<String> passwords = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String password = reader.readLine();
            while (password != null) {
                passwords.add(password);
                password = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            System.err.println("Error al llegir el fitxer: " + e.getMessage());
        }
        return passwords;
    }
}
