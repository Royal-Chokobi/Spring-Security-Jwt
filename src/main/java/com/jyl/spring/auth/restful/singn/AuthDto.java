package com.jyl.spring.auth.restful.singn;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

/**
 * packageName    : com.jyl.spring.auth.restful.singn
 * fileName       : AuthDto
 * author         : leejaeyoon
 * date           : 2022/05/23
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/05/23        leejaeyoon       최초 생성
 */
@Data @Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthDto {
    @NotEmpty
    private String id;
    @NotEmpty
    private String password;
}
