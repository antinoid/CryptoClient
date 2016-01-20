package org.cryptoclient.crypt;

/**
 *
 * @author zachskorn
 */
public class Chiffre implements CryptModule{

    // Allgemeine Variablen
    private static String schluessel = "ZGVFLKJDSUGHSFAÖLJFSHDGKAUAFHRETIUWOFVBAEWRZRD"
            + "COKWLASDLKFJASLDKJFÖLAFAJDSFAFASDFAÖGSERHTIAGJAGQORUGGH";

    @Override
    public String encrypt(String nachricht) {
        nachricht = nachricht.toLowerCase();
        schluessel = schluessel.toLowerCase();
        char[] char_nachricht = nachricht.toCharArray();
        char[] char_schluessel = schluessel.toCharArray();
        int i = 0;

        for (i = 0; i < nachricht.length(); i++) {
            int a = (int) char_nachricht[i] - 96 + (int) char_schluessel[i] - 96;
            char_nachricht[i] = (char) (a + 96);
        }
        
        return new String(char_nachricht);
    }

    @Override
    public String decrypt(String nachricht) {
        nachricht = nachricht.toLowerCase();
        schluessel = schluessel.toLowerCase();
        char[] char_nachricht = nachricht.toCharArray();
        char[] char_schluessel = schluessel.toCharArray();
        int i = 0;

        for (i = 0; i < nachricht.length(); i++) {
            int a = ((int) char_nachricht[i] - 96) - ((int) char_schluessel[i] - 96);
            char_nachricht[i] = (char) (a + 96);
        }
        return new String(char_nachricht);
    }
}
