import java.util.*;

public class Monoalfabetic {
    final static char[] ALFABET = "AÀÁBCÇDEÈÉFGHIÌÍÏJKLMNÑOÒÓPQRSTUÙÚÜVWXYZ".toCharArray();
    final static Character[] PERMUTA = PERMUTAALFABET(ALFABET);

    public static Character[] PERMUTAALFABET(char[] ALFABET) {
        List<Character> liste = new ArrayList<>();
        for (char c : ALFABET) {
            liste.add(c);
            }
            Collections.shuffle(liste);
            return liste.toArray(new Character[liste.size()]); 
        }

        public static String xifraMonoAlfa(String cadena) {

                StringBuilder resultat = new StringBuilder(); 

                for (char c : cadena.toCharArray()) {
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
                    } 
        }

        return resultat.toString(); // retourne la chaîne chiffrée
    }
    public static String desxifraMonoAlfa(String cadena) {
    StringBuilder resultat = new StringBuilder(); 

    for (char c : cadena.toCharArray()) {
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
        String a = xifraMonoAlfa("hola");
        System.out.println(a); 
        System.out.println(desxifraMonoAlfa(a));
        
    }
    
}
