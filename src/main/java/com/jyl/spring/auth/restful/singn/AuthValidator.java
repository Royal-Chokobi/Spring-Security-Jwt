package com.jyl.spring.auth.restful.singn;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * packageName    : com.jyl.spring.auth.restful.singn
 * fileName       : AuthValidator
 * author         : leejaeyoon
 * date           : 2022/05/24
 * description    : Auth Validation data
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/05/24        leejaeyoon       최초 생성
 */
@Component
public class AuthValidator {

    public void checkedUrlValid(AuthDto dto, Errors errors){
//        try {
//            URL url = new URL(dto.getUrl());
//            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
//            httpURLConnection.setRequestMethod("HEAD");
//            httpURLConnection.setConnectTimeout(2000);
//            httpURLConnection.setReadTimeout(2000);
//            httpURLConnection.setInstanceFollowRedirects(false);
//            if(httpURLConnection.getResponseCode() != HttpURLConnection.HTTP_OK){
//                throw new Exception();
//            }
//
//        } catch (Exception ignored) {
//            errors.reject("400", "Unknown Host. Please Checked Site URL.");
//        }
    }
}
