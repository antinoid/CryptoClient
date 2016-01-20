package org.cryptoclient.crypt;

/**
 *
 * @author da
 */
public class Crypt {

    private static final CryptModule DUMMY_CRYPT_MODULE = new DummyCryptModule();
    private static final CryptModule JULKA_MODUL = new Chiffre();
    private static final CryptModule DARI_MODUL = new CaesarModule();
        
    public static CryptModule getModule(int type) {
        //return modules[type];
        
        if (type == 1) {
            return DARI_MODUL;
        } else if (type == 2) {
            return JULKA_MODUL;
        } else if ( type == 3) {
            return DUMMY_CRYPT_MODULE;
        }
        
        return DUMMY_CRYPT_MODULE;
    }
}
