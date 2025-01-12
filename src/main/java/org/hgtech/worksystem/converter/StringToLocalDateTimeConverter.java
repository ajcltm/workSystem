package org.hgtech.worksystem.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

@Component
public class StringToLocalDateTimeConverter implements Converter<String, LocalDateTime> {

    private final List<DateTimeFormatter> formatters = new ArrayList<>();

    public StringToLocalDateTimeConverter() {
        // 지원할 날짜 및 시간 형식 등록
        formatters.add(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        formatters.add(DateTimeFormatter.ISO_LOCAL_DATE_TIME); // yyyy-MM-ddTHH:mm:ss
        formatters.add(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        formatters.add(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));
        formatters.add(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    @Override
    public LocalDateTime convert(String source) {
        if (source == null || source.isEmpty()) {
            throw new IllegalArgumentException("Input date string cannot be null or empty");
        }

        if (source != null && source.matches("\\d{4}-\\d{2}-\\d{2}")) {
            // yyyy-MM-dd 형식 처리
            return LocalDate.parse(source).atStartOfDay();
        } else if (source != null && source.matches("\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}")) {
            // yyyy-MM-dd'T'HH:mm:ss 형식 처리
            return LocalDateTime.parse(source);
        }

        // 각 포맷을 시도하여 변환
        for (DateTimeFormatter formatter : formatters) {
            try {
                if (formatter.equals(DateTimeFormatter.ofPattern("yyyy-MM-dd"))) {
                    // yyyy-MM-dd는 LocalDate로 파싱 후 시작 시간으로 변환
                    LocalDate localDate = LocalDate.parse(source, formatter);
                    return localDate.atStartOfDay();
                }
                return LocalDateTime.parse(source, formatter);
            } catch (DateTimeParseException ignored) {
                // 현재 포맷으로 파싱 실패, 다음 포맷 시도
            }
        }

        // 모든 포맷 실패 시 예외
        throw new IllegalArgumentException("Invalid date format: " + source +
                ". Supported formats: yyyy-MM-dd, yyyy-MM-ddTHH:mm:ss, yyyy-MM-dd HH:mm:ss, yyyy-MM-dd'T'HH:mm, yyyy-MM-dd HH:mm");
    }
}
