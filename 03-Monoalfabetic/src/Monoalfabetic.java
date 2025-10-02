import java.util.*;

public class Monoalfabetic {
    final static char[] alfabet = "AÀÁBCÇDEÈÉFGHIÌÍÏJKLMNÑOÒÓPQRSTUÙÚÜVWXYZ".toCharArray();
    final static Character[] permuta = permutaAlfabet(alfabet);

    public static Character[] permutaAlfabet(char[] alfabet) {
        List<Character> liste = new ArrayList<>();
        for (char c : alfabet) {
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
                    for (int i = 0; i < alfabet.length; i++) {
                        if (alfabet[i] == upper) {
                            index = i;
                            break;
                        }
                    }
                    
                    if (index != -1) {
                        
                        char chiff = permuta[index];
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
       
        for (int i = 0; i < permuta.length; i++) {
            if (permuta[i] == upper) {
                index = i;
                break;
            }
        }

        if (index != -1) {
            
            char dechiff = alfabet[index];
            
            if (isLower) dechiff = Character.toLowerCase(dechiff);
            resultat.append(dechiff);
        } else {
            
            resultat.append(c);
        }
    }

    return resultat.toString(); 
}

    public static void main(String[] args) {
        System.out.println(xifraMonoAlfa("hola")); 
        System.out.println(desxifraMonoAlfa("HOLA"));
        
    }
    
}
