package iticbcn.xifratge;
import java.security.KeyPair;

import javax.xml.bind.DatatypeConverter;

public class Main {
    public static void main(String[] args) throws Exception{
        ClauPublica cp = new ClauPublica();
        String msg = "Missatge de prova per xifrar áéíóú àèìòù äëïöü";
        KeyPair parellClaus = cp.generaParellClausRSA();
        byte[]msgXifrat = cp.xifraRSA(msg, parellClaus.getPublic());

        System.out.println("==========================================");
        System.out.println("Text xifrat:");
        System.out.println(DatatypeConverter.printHexBinary(msgXifrat));

        String msgDexifrat = cp.desxifraRSA(msgXifrat, parellClaus.getPrivate());
         System.out.println("==========================================");
        System.out.println("Text desxifrat:"  + msgDexifrat);

        String strClauPub = DatatypeConverter.printHexBinary(parellClaus.getPublic().getEncoded());
        String strClauPriv = DatatypeConverter.printHexBinary(parellClaus.getPrivate().getEncoded());

        System.out.println("==========================================");
        System.out.println("Clau pública:");
        System.out.println(strClauPub);
        System.out.println("==========================================");
        System.out.println("Clau privada");
        System.out.println(strClauPriv);




    }
}