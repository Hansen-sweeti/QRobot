package com.dorby.qrobot.utils;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.StringWriter;

/**
 * @Author: dorby
 * @Description:
 * @Date: 2022/1/12 16:16
 */
@Slf4j
public class JsonUtil {
    private static JsonUtil ju;
    private static JsonFactory jf;
    private static ObjectMapper mapper;
    private JsonUtil(){}

    public static JsonUtil getInstance() {
        if(ju==null) {
            ju = new JsonUtil();
        }
        return ju;
    }

    public static ObjectMapper getMapper() {
        if(mapper==null) {
            mapper = new ObjectMapper();
        }
        return mapper;
    }

    public static JsonFactory getFactory() {
        if(jf==null) {
            jf = new JsonFactory();
        }
        return jf;
    }

    public String obj2json(Object obj) {
        JsonGenerator jg = null;
        try {
            jf = getFactory();
            mapper = getMapper();
            mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS,false);
            StringWriter out = new StringWriter();
            jg = jf.createGenerator(out);
            mapper.writeValue(jg, obj);
            return out.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(jg!=null) jg.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public Object json2obj(String json,Class<?> clz) {
        try {
            mapper = getMapper();
            return mapper.readValue(json,clz);
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static <T> T string2Obj(String str,Class<?> collectionClass,Class<?>... elementClasses){
        JavaType javaType = mapper.getTypeFactory().constructParametricType(collectionClass,elementClasses);
        try {
            return mapper.readValue(str,javaType);
        } catch (IOException e) {
            System.out.println("Parse String to Object error");
            e.printStackTrace();
            return null;
        }
    }
}
