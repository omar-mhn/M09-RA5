import java.util.*;

public class Polialfabetic{
    
    final static char[] ALFABET = "AÀÁBCÇDEÈÉFGHIÌÍÏJKLMNÑOÒÓPQRSTUÙÚÜVWXYZ".toCharArray();
    static Random random;
    static Character[] PERMUTA ;
    final private static long clauSecreta = 1234;
    

    public static void initRandom(long seed) {
        random = new Random(seed);
    }

    public static Character[] permutaAlfabet(char[] ALFABET) {
        List<Character> liste = new ArrayList<>();
        for (char c : ALFABET) {
            liste.add(c);
        }
            Collections.shuffle(liste, random);

            return liste.toArray(new Character[liste.size()]); 
    }

    public static String xifraPoliAlfa(String msg){
        StringBuilder resultat = new StringBuilder(); 
            PERMUTA = permutaAlfabet(ALFABET);
                for (char c : msg.toCharArray()) {
                    boolean isLower = Character.isLowerCase(c);   // savoir si c’est une minuscule
                    char upper = Character.toUpperCase(c);        
                    // -1 (signifie “non trouvé”)
                    int index = -1;
                    // chercher l’index dans l’alphabet
                    for (int i = 0; i < ALFABET.length; i++) {
                        if (ALFABET[i] == upper) {
                            index = i;
                            break;
                        }
                    }
                    
                    if (index != -1) {
                        
                        char chiff = PERMUTA[index];
                        // si c’était une minuscule, on convertit en minuscule
                        if (isLower) chiff = Character.toLowerCase(chiff);
                        resultat.append(chiff);

                    } else{
                        resultat.append(c);
                    }
            
        }

        return resultat.toString(); 
    }

    public static String desxifraPoliAlfa(String msgXifrat){
        StringBuilder resultat = new StringBuilder(); 
        
    for (char c : msgXifrat.toCharArray()) {
        boolean isLower = Character.isLowerCase(c);   
        char upper = Character.toUpperCase(c);        

        int index = -1;
       
        for (int i = 0; i < PERMUTA.length; i++) {
            if (PERMUTA[i] == upper) {
                index = i;
                break;
            }
        }

        if (index != -1) {
            
            char dechiff = ALFABET[index];
            
            if (isLower) dechiff = Character.toLowerCase(dechiff);
            resultat.append(dechiff);
        } else {
            
            resultat.append(c);
        }
    }

    return resultat.toString(); 
    }

    public static void main(String[] args) {
        String msgs[] = {"Test 01 àrbitre,coixi,Perímetre",
                        "Test 02 Taüll,DÍA,año",
                        "Test 03 Perça, Òrrius,Bòvila"};

        String msgsXifrats[] = new String[msgs.length];

        System.out.println("Xifratge:\n-------");
        for (int i = 0; i < msgs.length; i++) {
            initRandom(clauSecreta);
            msgsXifrats[i] = xifraPoliAlfa(msgs[i]);
            System.out.printf("%-34s -> %s%n", msgs[i], msgsXifrats[i]);
            
        }

        System.out.println("Desxifratge:\n-------");
        for (int i = 0; i < msgs.length; i++) {
            
            initRandom(clauSecreta);
            String msg = desxifraPoliAlfa(msgsXifrats[i]);
            System.out.printf("%-34s -> %s%n", msgsXifrats[i], msg);
            
        }

    }

}