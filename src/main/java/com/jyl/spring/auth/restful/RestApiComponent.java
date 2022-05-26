package com.jyl.spring.auth.restful;

import com.jyl.spring.auth.common.response.ResultResponseResource;
import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import java.util.Locale;

/**
 * packageName    : com.jyl.spring.auth.restful
 * fileName       : RestApiComponent
 * author         : leejaeyoon
 * date           : 2022/05/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/05/24        leejaeyoon       최초 생성
 */
@Component
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RestApiComponent {

    @Autowired
    protected ResultResponseResource res;
    @Autowired
    protected MessageSource messageSource;

    protected String getErrorMessage(String msgCode, Object... obj){
        return messageSource.getMessage(msgCode, obj, new Locale("errors"));
    }

    @NotNull
    protected ResponseEntity<?> badRequest(Errors errors) {
        return ResponseEntity.badRequest().body(errors);
    }

}
