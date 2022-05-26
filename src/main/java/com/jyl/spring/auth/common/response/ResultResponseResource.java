package com.jyl.spring.auth.common.response;

import lombok.Getter;

import java.util.HashMap;

/**
 * packageName    : com.jyl.spring.auth.common.response
 * fileName       : ResultResponseResource
 * author         : leejaeyoon
 * date           : 2022/05/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/05/24        leejaeyoon       최초 생성
 */
@Getter
public class ResultResponseResource {
    private HashMap<String, Object> resultHashMap;

    public void setResultHashMap(String type, Object value) {
        HashMap<String, Object> resultHashMap = new HashMap<>();
        resultHashMap.put(type, value);
        this.resultHashMap = resultHashMap;
    }

}
