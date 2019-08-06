package com.sergeystets.backoffice.api.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MapperUtils {

    private static final XmlMapper XML_MAPPER = new XmlMapper();
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @SneakyThrows
    public static String asJson(Object object) {
        return OBJECT_MAPPER.writeValueAsString(object);
    }

    @SneakyThrows
    public static <T> T fromJson(String json, Class<T> type) {
        return OBJECT_MAPPER.readValue(json, type);
    }

    @SneakyThrows
    public static <T> T fromJson(String json, TypeReference<T> type) {
        return OBJECT_MAPPER.readValue(json, type);
    }

    @SneakyThrows
    public static <T> T fromXml(String xml, Class<T> type) {
        return XML_MAPPER.readValue(xml, type);
    }
}
