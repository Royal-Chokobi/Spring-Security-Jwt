package com.jyl.spring.auth.restful.singn;

import com.jyl.spring.auth.restful.RestApiComponent;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * packageName    : com.jyl.spring.auth.restful.singn
 * fileName       : AuthController
 * author         : leejaeyoon
 * date           : 2022/05/23
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/05/23        leejaeyoon       최초 생성
 */
@RestController
@RequestMapping(value = "/api/auth", produces = MediaTypes.HAL_JSON_VALUE)
public class AuthController extends RestApiComponent {

    @PostMapping(value = "/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthDto authDto, Errors errors){
        if (errors.hasErrors()) {
            return badRequest(errors);
        }
        return null;
    }
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody @Valid AuthDto authDto, Errors errors) {
        if (errors.hasErrors()) {
            return badRequest(errors);
        }
        return null;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody AuthDto authDto, Errors errors) {
        if (errors.hasErrors()) {
            return badRequest(errors);
        }
       return null;
    }

}
