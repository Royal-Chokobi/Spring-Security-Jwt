package com.jyl.spring.auth.common.keystore;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.PublicKey;
import java.util.Base64;

/**
 * packageName    : com.jyl.spring.auth.common.keystore
 * fileName       : Keystore
 * author         : leejaeyoon
 * date           : 2022/05/23
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/05/23        leejaeyoon       최초 생성
 */

public class Keystore implements KeystoreInterface{
    private final String keyStorePath;
    private final String keyStoreInstance;
    private final String alias = "SAMPLE";
    private final char[] password = "PASSWORD".toCharArray();
    private KeyStore keyStore;
    private Key key;

    public Keystore(String keyStorePath, String keyStoreInstance) {
        this.keyStorePath = keyStorePath;
        this.keyStoreInstance = keyStoreInstance;
        this.init();
    }

    @Override
    public void init() {
        try {
            this.keyStore = KeyStore.getInstance(this.keyStoreInstance);
            File file = new File(this.keyStorePath);
            InputStream inputStream = new FileInputStream(file);
            this.keyStore.load(inputStream, this.password);
            this.key = this.keyStore.getKey(this.alias, this.password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getPublicKeyToString() throws KeyStoreException {
        PublicKey publicKey = this.keyStore.getCertificate(this.alias).getPublicKey();
        byte[] encodedPublicKey = publicKey.getEncoded();
        String b64PublicKey = Base64.getMimeEncoder().encodeToString(encodedPublicKey);
        return "-----BEGIN PUBLIC KEY-----\n" + b64PublicKey + "\n-----END PUBLIC KEY-----";
    }

    @Override
    public String getPrivateToString() {
        String b64PrivateKey = Base64.getMimeEncoder().encodeToString(this.key.getEncoded());
        return "-----BEGIN RSA PRIVATE KEY-----\n" + b64PrivateKey + "\n-----END RSA PRIVATE KEY-----";
    }

    @Override
    public PublicKey getPublicKey() throws KeyStoreException {
        return this.keyStore.getCertificate(this.alias).getPublicKey();
    }

    public KeyStore getKeyStore() {
        return this.keyStore;
    }

    public Key getKey() throws Exception {
        return this.keyStore.getKey(this.alias, this.password);
    }

    public File getFile() {
        return new File(this.keyStorePath);
    }

}
