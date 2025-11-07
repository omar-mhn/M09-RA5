import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.HexFormat;
import java.util.concurrent.TimeUnit;

public class Hashes {
    private int npass = 0;
    private static final String CHARSET = "abcdefABCDEF1234567890!";

    public String getSHA512AmbSalt(String pw, String salt) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        
        md.update(salt.getBytes(StandardCharsets.UTF_8)); 
        byte[] bytes = md.digest(pw.getBytes(StandardCharsets.UTF_8));
        HexFormat hex = HexFormat.of();
        return hex.formatHex(bytes);
    }

    public String getPBKDF2AmbSalt(String pw, String salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
        int iterations = 65536;
        int keyLength = 128;
        KeySpec spec = new PBEKeySpec(pw.toCharArray(), salt.getBytes(StandardCharsets.UTF_8), iterations, keyLength);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        byte[] bytes = factory.generateSecret(spec).getEncoded();
        HexFormat hex = HexFormat.of();
        return hex.formatHex(bytes);
    }
    public String forcaBruta(String alg, String hash, String salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
        this.npass = 0;

        for (int len = 1; len <= 6; len++) {
            char[] passwordAttempt = new char[len];
            String result = generateAndCheck(alg, hash, salt, passwordAttempt, 0);
            if (result != null) {
                return result; 
            }
        }
        return null; 
    }

   
    private String generateAndCheck(String alg, String hash, String salt, char[] currentPw, int pos) throws NoSuchAlgorithmException, InvalidKeySpecException {
        
        
        if (pos == currentPw.length) {
            this.npass++;
            String pwStr = new String(currentPw);
            String currentHash;

            if (alg.equals("SHA-512")) {
                currentHash = getSHA512AmbSalt(pwStr, salt);
            } else { 
                currentHash = getPBKDF2AmbSalt(pwStr, salt);
            }

            if (hash.equals(currentHash)) {
                return pwStr;
            }
            return null;
        }

        
        for (char c : CHARSET.toCharArray()) {
            currentPw[pos] = c;
            
            String found = generateAndCheck(alg, hash, salt, currentPw, pos + 1);
            
           
            if (found != null) {
                return found;
            }
        }
        
        return null;
    }

    public String getInterval(long t1, long t2) {
        long duration = t2 - t1;
        long days = TimeUnit.MILLISECONDS.toDays(duration);
        duration -= TimeUnit.DAYS.toMillis(days);
        long hours = TimeUnit.MILLISECONDS.toHours(duration);
        duration -= TimeUnit.HOURS.toMillis(hours);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(duration);
        duration -= TimeUnit.MINUTES.toMillis(minutes);
        long seconds = TimeUnit.MILLISECONDS.toSeconds(duration);
        long millis = duration - TimeUnit.SECONDS.toMillis(seconds);

        return String.format("%d dies / %d hores / %d minuts / %d segons / %d millis",
                days, hours, minutes, seconds, millis);
    }

    public static void main(String[] args) throws Exception {
        String salt = "qpoweiruañslkdfjz";
        String pw = "aaabF!";
        Hashes h = new Hashes();
        String[] aHashes = {h.getSHA512AmbSalt(pw, salt), h.getPBKDF2AmbSalt(pw, salt)};
        String pwTrobat = null;
        String[] algoritmes = {"SHA-512", "PBKDF2"};
        for (int i = 0; i < aHashes.length; i++) {
            System.out.printf("=================================\n");
            System.out.printf("Algorisme: %s\n", algoritmes[i]);
            System.out.printf("Hash: %s\n", aHashes[i]);
            System.out.printf("---------------------------------\n");
            System.out.printf("-- Inici de força bruta ---\n");

            long t1 = System.currentTimeMillis();
            pwTrobat = h.forcaBruta(algoritmes[i], aHashes[i], salt);
            long t2 = System.currentTimeMillis();

            System.out.printf("Pass      : %s\n", pwTrobat);
            System.out.printf("Provats   : %d\n", h.npass);
            System.out.printf("Temps     : %s\n", h.getInterval(t1, t2));
            System.out.printf("---------------------------------\n\n");
        }
    }
}