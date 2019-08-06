package com.sergeystets.backoffice.etl.model.converter;

import com.opencsv.bean.AbstractBeanField;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeConverter extends AbstractBeanField {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.0000000Z");

    @Override
    protected Object convert(String s) {
        return LocalDateTime.parse(s.replaceAll("Z$", "+0000"), DATE_TIME_FORMATTER);
    }
}