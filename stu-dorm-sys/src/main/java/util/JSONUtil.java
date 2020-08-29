package util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;

public class JSONUtil {

    private static final ObjectMapper MAPPER = new ObjectMapper();
    static {
        MAPPER.setDateFormat(new SimpleDateFormat("yyy-MM-dd HH:mm:ss"));//设置日期格式
    }

    public static <T> T read(InputStream is, Class<T> clazz) {
        try {
            return MAPPER.readValue(is,clazz);
        } catch (IOException e) {
            throw new RuntimeException("json反序列化失败",e);
        }
    }

    public static String write(Object o) {
        try {
            return MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(o);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("json序列化失败",e);
        }
    }
}
