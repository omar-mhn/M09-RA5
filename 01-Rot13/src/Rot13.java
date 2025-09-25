
public class Rot13 {

public static char xifraRot13(char cadena) {
    char[] minuscules = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    char[] majuscules = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

    for (int i = 0; i < minuscules.length; i++) {
        if (cadena == minuscules[i]) {
            return minuscules[(i + 13) % minuscules.length];
        }
        if (cadena == majuscules[i]) {
            return majuscules[(i + 13) % minuscules.length];
        }
    }
    return cadena; 
}
public static char desxifraRot13( char cadena ){
      char[] minuscules = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    char[] majuscules = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

    for (int i = 0; i < minuscules.length; i++) {
        if (cadena == minuscules[i]) {
            return minuscules[(i - 13) % minuscules.length];
        }
        if (cadena == majuscules[i]) {
            return majuscules[(i - 13) % minuscules.length];
        }
    }
    return cadena; 
}

public static void main(String[] args) {
    System.out.println(xifraRot13('a')); 
    System.out.println(desxifraRot13('n'));

}
}
