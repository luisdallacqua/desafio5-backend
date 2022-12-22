package br.com.banco.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtilTest {

    public static final ZonedDateTime DATE_2020 = ZonedDateTime.of(
            LocalDateTime.of(2020, 1, 1, 0, 0, 0),
            ZoneId.systemDefault()
    );
    public static final ZonedDateTime DATE_2019 = ZonedDateTime.of(
            LocalDateTime.of(2019, 1, 1, 0, 0, 0),
            ZoneId.systemDefault()
    );

    public static ZonedDateTime formatDateToZonaDateTime(String dateStr){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(dateStr, formatter);

        ZonedDateTime resultado = date.atStartOfDay(ZoneId.systemDefault());
        return resultado;
    }
    public static LocalDate formatDateToLocalDate(String dateStr){
        DateTimeFormatter dateformatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(dateStr, dateformatter);

        return localDate;
    }
}
