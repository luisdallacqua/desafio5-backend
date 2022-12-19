package br.com.banco.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    public static LocalDate dateNowFormatted(){
        // 1. get current calendar Date
        Calendar calendar = Calendar.getInstance();
        System.out.println("Current Date is :- \n" + calendar.getTime());


        // 2. get system default zone
        ZoneId zoneId = ZoneId.systemDefault();
        System.out.println("\nDefault System Zone is :- \n" + zoneId);


        // 3. convert java.util.Calendar -> Date -> LocalDate
        Date date = calendar.getTime();

        return date.toInstant().atZone(zoneId).toLocalDate();
    }
}
