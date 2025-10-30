package iticbcn.xifratge;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.Cipher;

public class ClauPublica {

    public KeyPair generaParellClausRSA() throws Exception{
        // Creamos un generador de claus RSA con tamaño de 2048 bits
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);
        // Generamos el par de claves
        KeyPair parellClaus = keyGen.generateKeyPair();
        return parellClaus;

    }
    public byte[] xifraRSA(String msg, PublicKey clauPublica) throws Exception{
         // Creamos un cifrador RSA
        Cipher cipher = Cipher.getInstance("RSA");
        // Inicializamos en modo ENCRYPTION con la clau pública
        cipher.init(Cipher.ENCRYPT_MODE, clauPublica);
        // Retornamos el mensaje xifrat
        return cipher.doFinal(msg.getBytes("UTF-8"));
    }
    public String desxifraRSA(byte[]msgXifrat,PrivateKey ClauPrivada) throws Exception{
         // Creamos un descifrador RSA
        Cipher cipher = Cipher.getInstance("RSA");
        // Inicializamos en modo DECRYPTION con la clau privada
        cipher.init(Cipher.DECRYPT_MODE, ClauPrivada);
        // Retornamos el mensaje desxifrat com a String
        return new String(cipher.doFinal(msgXifrat), "UTF-8");
    }
}
