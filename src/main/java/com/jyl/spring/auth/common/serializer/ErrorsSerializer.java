package com.jyl.spring.auth.common.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.validation.Errors;

import java.io.IOException;

/**
 * packageName    : com.jyl.spring.auth.common.serializer
 * fileName       : ErrorsSerializer
 * author         : leejaeyoon
 * date           : 2022/05/18
 * description    : Error 발생시 해당 Serializer 사용됨.
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/05/18        leejaeyoon       최초 생성
 */
@JsonComponent
public class ErrorsSerializer extends JsonSerializer<Errors> {

    @Override
    public void serialize(Errors errors, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        String errorsMessage;
        int errorsCode;
        try {
            errorsMessage = errors.getAllErrors().get(0).getDefaultMessage();
            String strCode = errors.getAllErrors().get(0).getCode();
            errorsCode = Integer.parseInt(strCode);
        }catch (Exception ignored){
            errorsMessage ="An error occurred, Please contact your representative.";
            errorsCode = 400;
        }

        gen.writeStartObject();
        gen.writeBooleanField("success", false);
        gen.writeNumberField("status", errorsCode);
        gen.writeNullField("response");
        gen.writeFieldName("error");
        gen.writeStartObject();
        gen.writeStringField("message", errorsMessage);
        gen.writeEndObject();
        gen.writeEndObject();
    }
}
