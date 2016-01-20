/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cryptoclient.crypt;

/**
 *
 * @author schoen
 */
public class CaesarModule implements CryptModule {

    private static final int SHIFT_VALUE = 5;

    private static char[] calphabet = {'C', 'T', 'S', 'M', 'O', 'F', 'A', 'G', 'I', 'H', 'Y',
        'J', 'L', 'U', 'Q', 'V', 'Z', 'E', 'P', 'K', 'B', 'D', 'W', 'X', 'N', 'R'};
    private static char[] calphabetsmall = {'c', 't', 's', 'm', 'o', 'f', 'a', 'g', 'i', 'h', 'y',
        'j', 'l', 'u', 'q', 'v', 'z', 'e', 'p', 'k', 'b', 'd', 'w', 'x', 'n', 'r'};

    @Override
    public String decrypt(String inputstring) {

        //fillcustomalphabet();
        //fillcustomalphabetsmall();
        char[] inputarray = inputstring.toCharArray();

        //die arrays mit dem custom alphabet füllen
        for (int i = 0; i < inputarray.length; i++) {
            //char newletter;

            short status = 0, status2 = 0;

            for (int i2 = 0; i2 < 26; i2++) {
                if (inputarray[i] == calphabet[i2]) {
                    if (i2 - SHIFT_VALUE < 0) {
                        i2 += 26;
                        status = 1;
                    }
                    inputarray[i] = calphabet[(i2 - SHIFT_VALUE) % 26];

                    if (status == 1) {
                        i2 -= 26;
                        status = 0;
                    }
                    break;

                }
                if (inputarray[i] == calphabetsmall[i2]) {
                    if (i2 - SHIFT_VALUE < 0) {
                        i2 += 26;
                        status2 = 1;
                    }
                    inputarray[i] = calphabetsmall[(i2 - SHIFT_VALUE) % 26];

                    if (status2 == 1) {
                        i2 -= 26;
                        status2 = 0;
                    }
                    break;

                }
            }

        }

        return new String(inputarray);

    }

    @Override
    public String encrypt(String inputstring) {

        //fillcustomalphabet();
        //fillcustomalphabetsmall();
        char[] inputarray = inputstring.toCharArray();

        for (int i = 0; i < inputarray.length; i++) {
            for (int i2 = 0; i2 < 26; i2++) {
                if (inputarray[i] == calphabet[i2]) {

                    inputarray[i] = calphabet[(i2 + SHIFT_VALUE) % 26];
                    break;

                }
                if (inputarray[i] == calphabetsmall[i2]) {
                    inputarray[i] = calphabetsmall[(i2 + SHIFT_VALUE) % 26];
                    break;
                }
            }
        }
        return new String(inputarray);
    }
}
