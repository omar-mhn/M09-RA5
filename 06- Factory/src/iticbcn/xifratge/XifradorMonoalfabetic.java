package iticbcn.xifratge;

import java.util.*;

public class XifradorMonoalfabetic {
    final  char[] ALFABET = "AÀÁBCÇDEÈÉFGHIÌÍÏJKLMNÑOÒÓPQRSTUÙÚÜVWXYZ".toCharArray();
    Character[] PERMUTA;

    public Character[] PERMUTAALFABET(char[] ALFABET) {
        List<Character> liste = new ArrayList<>();
        for (char c : ALFABET) {
            liste.add(c);
            }
            Collections.shuffle(liste);
            return liste.toArray(new Character[liste.size()]); 
        }

        public XifradorMonoalfabetic() {
            PERMUTA = PERMUTAALFABET(ALFABET);
        }

        public String xifraMonoAlfa(String cadena,String clau) throws ClauNoSuportada {
            if (clau != null) {
                throw new ClauNoSuportada("Clau no suportada: la clau ha de ser null");
            }

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
    public String desxifraMonoAlfa(String cadena, String clau) throws ClauNoSuportada {
        if (clau != null) {
                throw new ClauNoSuportada("Clau no suportada: la clau ha de ser null");
            }

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

   // public void main(String[] args) {
    //    String a = xifraMonoAlfa("hola");
    //    System.out.println(a); 
    //    System.out.println(desxifraMonoAlfa(a));
        
   // }
    
}
