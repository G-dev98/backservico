package com.servico.backservico.model.enums;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.Map;

import org.apache.commons.lang3.EnumUtils;

import javax.persistence.Id;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class EnumDeserializer<T extends Enum<T>> extends JsonDeserializer<T> {

    @SuppressWarnings("unchecked")
    @Override
    public T deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {

        try {
            Object value = p.getValueAsString();

            Class<T> clazz = clazz();
            Field field = null;

            for (Field f : clazz.getDeclaredFields()) {
                if (f.getAnnotation(Id.class) != null) {
                    field = f;
                    break;
                }
            }

            if (field == null) {
                return null;
            }

            if (value == null) {
                Map<String, Object> map = ctxt.readValue(p, Map.class);
                value = map.get(field.getName());
            }

            boolean isNumeric = Number.class.isAssignableFrom(field.getType());
            long longValue = isNumeric && value != null ? Long.parseLong(value.toString()) : 0;

            for (T enumerator : EnumUtils.getEnumList(clazz)) {
                Object enumValue = field.get(enumerator);

                // int or long ?
                if (isNumeric) {
                    long longEnumValue = ((Number) enumValue).longValue();
                    if (longEnumValue == longValue) {
                        return enumerator;
                    }

                    // other object ?
                } else if (value.equals(enumValue)) {
                    return enumerator;
                }
            }

            return null;

        } catch (IllegalAccessException | IllegalArgumentException | SecurityException e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
    public Class<T> clazz() {
        return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

}
