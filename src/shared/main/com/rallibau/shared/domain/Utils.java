package com.rallibau.shared.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.CaseFormat;


import java.io.IOException;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;

public final class Utils {

    public static final String DATA_TIME_FORMAT = "yyyyMMdd HH:mm:ss.SSSSSS Z";

    public static String dateToString(ZonedDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern(DATA_TIME_FORMAT));
    }

    public static String dateToString(Timestamp timestamp) {
        return dateToString(timestamp.toLocalDateTime().atZone(ZoneId.systemDefault()));
    }

    public static ZonedDateTime dateFromString(String dateTimeString) {
        return ZonedDateTime.parse(dateTimeString, DateTimeFormatter.ofPattern(DATA_TIME_FORMAT));
    }

    public static Date convertToDateViaInstant(ZonedDateTime dateToConvert) {
        return java.util.Date
                .from(dateToConvert.toInstant());
    }

    public static String jsonEncode(HashMap<String, Serializable> map) {
        try {
            return new ObjectMapper().writeValueAsString(map);
        } catch (JsonProcessingException e) {
            return "";
        }
    }

    public static HashMap<String, Serializable> jsonDecode(String body) {
        try {
            return new ObjectMapper().readValue(body, HashMap.class);
        } catch (IOException e) {
            return null;
        }
    }

    public static String toSnake(String text) {
        return CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, text);
    }

    public static String toCamel(String text) {
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, text);
    }

    public static String toCamelFirstLower(String text) {
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, text);
    }
}
