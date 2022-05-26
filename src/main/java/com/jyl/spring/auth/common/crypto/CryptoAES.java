package com.jyl.spring.auth.common.crypto;

import com.fasterxml.jackson.core.JsonFactory;
import org.springframework.boot.json.JsonParserFactory;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.time.Duration;
import java.util.Base64;
import java.util.Date;

/**
 * packageName    : com.jyl.spring.auth.common.crypto
 * fileName       : CryptoAES
 * author         : leejaeyoon
 * date           : 2022/05/23
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/05/23        leejaeyoon       최초 생성
 */
public class CryptoAES {
    /**
     * The constant alg.
     */
    public static String alg = "AES/CBC/PKCS5Padding";
    private final String key = "a1431199c16b41fa9409309eff8925a2";
    private final String iv = "1199c09efa143f89";

    /**
     * methodName : encrypt
     * author : Jae-Yoon Lee
     * description :
     *
     * @param text
     * @return string
     * @throws Exception the exception
     */
    public String encrypt(String text) throws Exception {
        Cipher cipher = Cipher.getInstance(alg);
        SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "AES");
        IvParameterSpec ivParamSpec = new IvParameterSpec(iv.getBytes());
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivParamSpec);

        byte[] encrypted = cipher.doFinal(text.getBytes("UTF-8"));
        return Base64.getEncoder().encodeToString(encrypted);
    }

    /**
     * methodName : decrypt
     * author : Jae-Yoon Lee
     * description :
     *
     * @param cipherText
     * @return string
     * @throws Exception the exception
     */
    public String decrypt(String cipherText) throws Exception {
        Cipher cipher = Cipher.getInstance(alg);
        SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "AES");
        IvParameterSpec ivParamSpec = new IvParameterSpec(iv.getBytes());
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivParamSpec);

        byte[] decodedBytes = Base64.getDecoder().decode(cipherText);
        byte[] decrypted = cipher.doFinal(decodedBytes);
        return new String(decrypted, "UTF-8");
    }

    /**
     * methodName : checkedDecryptAES256
     * author : Jae-Yoon Lee
     * description :
     *
     * @param enc
     * @return boolean
     */
    public Boolean checkedDecryptAES256(String enc){
        try{
            var decrypt = decrypt(enc);
            JsonFactory jf = new JsonFactory();
            var jsonParser = JsonParserFactory.getJsonParser();
            var stringObjectMap = jsonParser.parseMap(decrypt);
            var exp = Integer.parseInt(stringObjectMap.get("exp").toString());
            var currentTM = new Date().getTime()/1000;

            if((currentTM-exp) > 10 || (currentTM < exp)){
                return false;
            }

            return true;
        }catch (Exception e){
            return false;
        }
    }

    public static void main(String[] args) throws Exception {
        CryptoAES aes256 = new CryptoAES();
        String text = "!! Hello World !123123123!";
        String cipherText = aes256.encrypt(text);
        System.out.println("test : "+text);
        System.out.println(cipherText);
        System.out.println(aes256.decrypt(cipherText));
        System.out.println(aes256.decrypt("uGD9uM/QvDabh497TAbMpuaRVY7Yi36BE5maqbxbHvk="));

        var l = System.currentTimeMillis()/1000;
        System.out.println("l = " + l);
        long now = new Date().getTime()/1000;
        var l1 = now + Duration.ofSeconds(10).toMillis();
        System.out.println("l1 = " + now);


    }
}
