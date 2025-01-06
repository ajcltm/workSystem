package org.hgtech.worksystem.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class StringToLocalDateTimeConverter implements Converter<String, LocalDateTime> {

    @Override
    public LocalDateTime convert(String source) {
        if (source != null && source.matches("\\d{4}-\\d{2}-\\d{2}")) {
            // yyyy-MM-dd 형식 처리
            LocalDate localDate = LocalDate.parse(source);
            return localDate.atStartOfDay();
        } else if (source != null && source.matches("\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}")) {
            // ISO-8601 형식 처리
            return LocalDateTime.parse(source);
        }
        throw new IllegalArgumentException("Invalid date format: " + source);
    }
}