package org.cryptoclient.crypt;

/**
 *
 * @author da
 */
public interface CryptModule {

    public String encrypt(String message);
    public String decrypt(String message);
    
    //public void setKey();
}
