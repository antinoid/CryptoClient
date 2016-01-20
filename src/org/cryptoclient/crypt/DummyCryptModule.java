package org.cryptoclient.crypt;

/**
 *
 * @author da
 */
public class DummyCryptModule implements CryptModule {

    @Override
    public String encrypt(String message) {
        return message;
    }

    @Override
    public String decrypt(String message) {
        return message;
    }
}
