package iticbcn.xifratge;

public class XifradorRotX implements Xifrador{
    private  char[] minuscules = "aàábcçdeèéfghiìíïjklmnñoòópqrstuùúüvwxyz".toCharArray();
    private  char[] majuscules = "AÀÁBCÇDEÈÉFGHIÌÍÏJKLMNÑOÒÓPQRSTUÙÚÜVWXYZ".toCharArray();
    private static final int MAX_CLAU = 40; // Limite de la clé selon le resultat donnee par le prof.


     public  String xifraRotX(String cadena, int desplaçament) {
        // ici j'ai transformè le texte en tableau 
        char [] chars = cadena.toCharArray();
        for (int j = 0; j < chars.length; j++) {
            // crée une variable temporaire c qui contient ce caractère pour pouvoir le manipuler plus facilement dans la boucle
            char c = chars[j];
            for (int i = 0; i < minuscules.length; i++) {
                if ( c == minuscules[i]){
                chars[j] = minuscules[(i + desplaçament + minuscules.length) % minuscules.length];
                break;
            }
            if (c == majuscules[i]) {
                chars[j] = majuscules[(i + desplaçament + majuscules.length) % majuscules.length];
                break;
                }
            }
        }
        return new String(chars);
    }
    public  String desxifraRotX( String cadena, int desplaçament ){
        return xifraRotX(cadena, - desplaçament);
    }

    // Vérifie que la clé est un entier valide et dans la plage 0–MAX_CLAU
    private int validarClau(String clau) throws ClauNoSuportada {
        try {
            int desplaçament = Integer.parseInt(clau);
            if (desplaçament < 0 || desplaçament > MAX_CLAU) {
                throw new ClauNoSuportada("Clau de RotX ha de ser un sencer de 0 a " + MAX_CLAU);
            }
            return desplaçament;
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("Clau de RotX ha de ser un sencer de 0 a " + MAX_CLAU);
        }
    }

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        int desplaçament = validarClau(clau);
        String resultat = xifraRotX(msg, desplaçament);
        return new TextXifrat(resultat.getBytes());
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        int desplaçament = validarClau(clau);
        String textXifrat = new String(xifrat.getBytes());
        return desxifraRotX(textXifrat, desplaçament);
    }
    

            /*
            public  void forcaBrutaRotX( String cadenaXifrada ){
                for (int i = 0 ;i < minuscules.length ; i++){
                    System.out.println("("+ i + ") -> "+xifraRotX(cadenaXifrada, i)); 
                }
            }
            
        
                
                public  void main(String[] args) {
                    System.out.println(xifraRotX("ABC",1)); 
                    System.out.println(desxifraRotX("ÀCÇ", 1));
                    System.out.println(xifraRotX("hola",3));
                    System.out.println(desxifraRotX("ípñb", 3));

                    
                    forcaBrutaRotX("hola");
                    

                }
                    */
        
}

