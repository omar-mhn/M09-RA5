
public class Rot13 {
    final static char[] MINUSCULES = "aàábcçdeèéfghiìíïjklmnñoòópqrstuùúüvwxyz".toCharArray();
    final static char[] MAJUSCULES = "AÀÁBCÇDEÈÉFGHIÌÍÏJKLMNÑOÒÓPQRSTUÙÚÜVWXYZ".toCharArray();

    public static String xifraRot13(String cadena) {
       
        // ici j'ai transformè le texte en tableau 
        char [] chars = cadena.toCharArray();
        for (int j = 0; j < chars.length; j++) {
            // crée une variable temporaire c qui contient ce caractère pour pouvoir le manipuler plus facilement dans la boucle
            char c = chars[j];
            for (int i = 0; i < MINUSCULES .length; i++) {
                if ( c == MINUSCULES [i]){
                    chars[j] = MINUSCULES [(i + 13) % MINUSCULES .length]  ;
                    break;
                }
                if ( c == MAJUSCULES[i]){
                    chars[j] = MAJUSCULES[(i + 13) % MAJUSCULES.length]  ;
                    break ;
                }
            }
        }
        return new String(chars);
    }
     public static String desxifraRot13(String cadena) {
        
          // ici j'ai transformè le texte en tableau 
        char [] chars = cadena.toCharArray();
        for (int j = 0; j < chars.length; j++) {
            // crée une variable temporaire c qui contient ce caractère pour pouvoir le manipuler plus facilement dans la boucle
            char c = chars[j];
            for (int i = 0; i < MINUSCULES .length; i++) {
                if ( c == MINUSCULES [i]){
                    chars[j] = MINUSCULES[(i - 13) + (MINUSCULES.length % MINUSCULES.length)];

                    break;
                }
                if ( c == MAJUSCULES[i]){
                    chars[j] = MAJUSCULES[( (i - 13 + MAJUSCULES.length) % MAJUSCULES.length )]  ;
                    break ;
                }
            }
        }
        return new String(chars);
    }

public static void main(String[] args) {
    System.out.println(xifraRot13("ABC")); 
    System.out.println(desxifraRot13("IÏJ"));
    System.out.println(xifraRot13("hola")); 
    System.out.println(desxifraRot13("ówùi"));
    

}
}