package com.jyl.spring.auth.common.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.jyl.spring.auth.common.response.ResultResponseResource;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

/**
 * packageName    : com.jyl.spring.auth.common.serializer
 * fileName       : SuccessSerializer
 * author         : leejaeyoon
 * date           : 2022/05/18
 * description    : Success 발생시 해당 Serializer 사용됨.
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/05/18        leejaeyoon       최초 생성
 */
@JsonComponent
public class SuccessSerializer extends JsonSerializer<ResultResponseResource> {
    @Override
    public void serialize(ResultResponseResource res, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        var responseObject = res.getResultHashMap().get("data");
        int statusObject = (int) res.getResultHashMap().get("status");

        gen.writeStartObject();
        gen.writeBooleanField("success", true);
        gen.writeNumberField("status", statusObject);
        gen.writeObjectField("response", responseObject);
        gen.writeBooleanField("error", false);
        gen.writeEndObject();
    }
}
