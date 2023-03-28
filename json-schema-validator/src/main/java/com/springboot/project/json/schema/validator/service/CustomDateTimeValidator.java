package com.springboot.project.json.schema.validator.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.everit.json.schema.FormatValidator;
import org.joda.time.DateTimeFieldType;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;
import org.joda.time.format.DateTimeParser;
import org.springframework.stereotype.Component;

@Component
public class CustomDateTimeValidator implements FormatValidator {

    private static final String DATE_TIME_FORMAT = "date-time";
    private static final List<String> FORMATS = Collections.unmodifiableList(
        Arrays.asList("yyyy-MM-dd'T'HH:mm:ssZ", "yyyy-MM-dd'T'HH:mm:ss.[0-9]{1,12}Z")
    );
    private static final DateTimeFormatter FORMATTER;

    static {
        final DateTimeParser secFracsParser = new DateTimeFormatterBuilder()
            .appendLiteral('.').appendFractionOfSecond(1,12)
            .toParser();

        final DateTimeParser timeZoneOffset = new DateTimeFormatterBuilder()
            .appendTimeZoneOffset("Z", false, 2, 2)
            .toParser();    

        DateTimeFormatterBuilder builder = new DateTimeFormatterBuilder();

        builder = builder.appendFixedDecimal(DateTimeFieldType.year(), 4)
            .appendLiteral('-')
            .appendFixedDecimal(DateTimeFieldType.monthOfYear(), 2)
            .appendLiteral('-')
            .appendFixedDecimal(DateTimeFieldType.dayOfMonth(), 2)
            .appendLiteral('T')
            .appendFixedDecimal(DateTimeFieldType. hourOfDay(), 2)
            .appendLiteral(':')
            .appendFixedDecimal(DateTimeFieldType.minuteOfHour(), 2)
            .appendLiteral(':')
            .appendFixedDecimal(DateTimeFieldType.secondOfMinute(), 2)
            .appendOptional(secFracsParser)
            .appendOptional(timeZoneOffset);

        FORMATTER = builder.toFormatter();
    }

    @Override
    public Optional<String> validate(String dateTime) {
        try {
            FORMATTER.parseDateTime(dateTime);
            return Optional.empty();
        } catch(IllegalArgumentException ex) {
            return Optional.of(String.format("[%s] is not a valid %s. Expected %s", dateTime, formatName(), FORMATS));
        }
    }

    @Override
    public String formatName() {
        return DATE_TIME_FORMAT;
    }

}