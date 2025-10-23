import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import java.nio.charset.StandardCharsets;
import java.security.*;

public class AES {

    public static final String ALGORISME_XIFRAT= "AES";
    public static final String ALGORISME_HASH = "sha-256";
    public static final String FORMAT_AES ="AES/CBC/PKCS5Padding";

    private static final int MIDA_IV = 16;
    private static byte[] iv = new byte [MIDA_IV];
    private static final String CLAU = "Omar123";


        public static byte[] xifraAES (String msg, String password) throws Exception{
            //obtenir els bytes de l'string
            byte msgBytes[] = msg.getBytes();

            // Genera IvParameterSpec

            // L’IV est une séquence de bytes aléatoires
            // Il permet d’assurer que deux messages identiques chiffrés avec la même clé produisent des textes chiffrés différents
            
            SecureRandom random = new SecureRandom();
            random.nextBytes(iv);
            // IvParameterSpec est une classencapsule le vecteur d’initialisation
            // Elle permet de transmettre l’IV au Cipher lors de l’initialisation pour le chiffrement ou le déchiffrement
            IvParameterSpec ivSpec = new IvParameterSpec(iv);
            
            // Genera hash 
            // On crée un objet MessageDigest pour calculer le hash selon l’algorithme défini
            //Cela permet de transformer une donnée de taille variable en un résumé de taille fixe (digest).
            MessageDigest digest = MessageDigest.getInstance(ALGORISME_HASH);

            // transformer un mot de passe arbitraire en clé AES de taille correcte.
            byte[] keyBytes = digest.digest(password.getBytes(StandardCharsets.UTF_8));

            // réparer la clé dans le format attendu par AES.
            SecretKeySpec keySpec = new SecretKeySpec(keyBytes,ALGORISME_XIFRAT);

            //Initialiser le Cipher AES en mode CBC avec padding
            // Cipher est un objet Java qui effectue le chiffrement et le déchiffrement.

            Cipher cipher = Cipher.getInstance(FORMAT_AES);

            cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
            // Encrypt 
            byte[] encrypted = cipher.doFinal(msgBytes);
            // combinar Iv i part xifrada 
            byte[] result = new byte[iv.length + encrypted.length];
            System.arraycopy(iv, 0, result, 0, iv.length);
            System.arraycopy(encrypted, 0, result, iv.length, encrypted.length);

            // return iv + mshxifrat 
            return result;
        }
    public static String desxifraAES(byte[] bIvIMsgXifrat,String clau) throws Exception {

        // Extreure lIV
        //On récupère les 16 premiers bytes du tableau, qui correspondent à l’IV utilisé lors du chiffrement.
        byte[] iv = new byte[MIDA_IV];
        System.arraycopy(bIvIMsgXifrat, 0, iv, 0, MIDA_IV);
        IvParameterSpec ivSpec = new IvParameterSpec(iv);
        //Extreure la part xifrada
        //On récupère le reste du tableau, qui correspond au texte chiffré.
        byte[] encrypted = new byte[bIvIMsgXifrat.length - MIDA_IV];
        System.arraycopy(bIvIMsgXifrat, MIDA_IV, encrypted, 0, encrypted.length);
    

        //Fer hash de la clau 
        MessageDigest digest = MessageDigest.getInstance(ALGORISME_HASH);
        byte[] keyBytes = digest.digest(clau.getBytes(StandardCharsets.UTF_8));
        SecretKeySpec keySpec = new SecretKeySpec(keyBytes, ALGORISME_XIFRAT);
        //Transforme le mot de passe en hash SHA-256 pour obtenir une clé AES de 32 bytes.

        //Initialiser le Cipher en mode déchiffrement
        Cipher cipher = Cipher.getInstance(FORMAT_AES);
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
        //configure le Cipher pour déchiffrer avec la clé et l’IV correspondants.

        //Desxifrar 
        byte[] decryptedBytes = cipher.doFinal(encrypted);

        //return String desxifrat
        return new String(decryptedBytes, StandardCharsets.UTF_8);

    }

    public static void main(String[] args) {
    String msgs[] = {"Lorem ipsum dicet",
    "Hola Andrés cómo está tu cuñado",
    "Agora lila Ótto"};

    for (int i = 0; i < msgs.length; i++) {
        String msg = msgs[i];

        byte[] bXifrats = null;
        String desxifrat = "";
        try {
            bXifrats = xifraAES(msg, CLAU);
            desxifrat = desxifraAES(bXifrats, CLAU);
        } catch (Exception e) {
            System.err.println("Error de xifrat: " + e.getLocalizedMessage());
        }
        System.out.println("---");
        System.out.println("Msg: " + msg);
        System.out.println("Enc: " + new String(bXifrats));
        System.out.println("DEC: " + desxifrat);
    }
}

}
