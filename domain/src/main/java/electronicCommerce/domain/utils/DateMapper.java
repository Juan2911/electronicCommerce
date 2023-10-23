package electronicCommerce.domain.utils;

import electronicCommerce.domain.constants.Constants;
import electronicCommerce.domain.exceptions.ValidationException;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Slf4j
public class DateMapper {
    public static LocalDateTime fromStringToLocalDateTime(String localDateTime) throws ValidationException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constants.DATE_FORMAT);

            return LocalDateTime.parse(localDateTime, formatter);
        } catch (DateTimeParseException e) {
            log.warn("Date Format Invalid");

            throw new ValidationException(Constants.DATE_FORMAT_VALIDATION_MESSAGE);
        }
    }
}
