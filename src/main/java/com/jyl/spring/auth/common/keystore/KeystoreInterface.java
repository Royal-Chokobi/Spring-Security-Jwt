package com.jyl.spring.auth.common.keystore;

import java.security.KeyStoreException;
import java.security.PublicKey;

/**
 * packageName    : com.jyl.spring.auth.common.keystore
 * fileName       : KeystoreInterface
 * author         : leejaeyoon
 * date           : 2022/05/23
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/05/23        leejaeyoon       최초 생성
 */
public interface KeystoreInterface {
    /**
     * methodName : init
     * author : Jae-Yoon Lee
     * description :
     */
    void init();

    /**
     * methodName : getPublicKeyToString
     * author : Jae-Yoon Lee
     * description :
     *
     * @return string
     * @throws KeyStoreException the key store exception
     */
    String getPublicKeyToString() throws KeyStoreException;

    /**
     * methodName : getPrivateToString
     * author : Jae-Yoon Lee
     * description :
     *
     * @return string
     */
    String getPrivateToString();

    /**
     * methodName : getPublicKey
     * author : Jae-Yoon Lee
     * description :
     *
     * @return public key
     * @throws KeyStoreException the key store exception
     */
    PublicKey getPublicKey() throws KeyStoreException;
}
