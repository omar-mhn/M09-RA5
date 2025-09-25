
public class Rot13 {

    public static String xifraRot13(String cadena) {
        char[] minuscules = "aàábcçdeèéfghiìíïjklmnñoòópqrstuùúüvwxyz".toCharArray();
        char[] majuscules = "AÀÁBCÇDEÈÉFGHIÌÍÏJKLMNÑOÒÓPQRSTUÙÚÜVWXYZ".toCharArray();
        // ici j'ai transformè le texte en tableau 
        char [] chars = cadena.toCharArray();
        for (int j = 0; j < chars.length; j++) {
            // crée une variable temporaire c qui contient ce caractère pour pouvoir le manipuler plus facilement dans la boucle
            char c = chars[j];
            for (int i = 0; i < minuscules.length; i++) {
                if ( c == minuscules[i]){
                    chars[j] = minuscules[(i + 13) % minuscules.length]  ;
                    break;
                }
                if ( c == majuscules[i]){
                    chars[j] = majuscules[(i + 13) % majuscules.length]  ;
                    break ;
                }
            }
        }
        return new String(chars);
    }
    // ici j'appelle une deuxieme fois la xifra pour faire un autre tour car c'est symétrique  le chiffrement et le déchiffrement sont identiques 
     public static String desxifraRot13(String cadena) {
        
        return xifraRot13(cadena);
    }

public static void main(String[] args) {
    System.out.println(xifraRot13("ABC")); 
    System.out.println(xifraRot13("hola")); 
    

}
}