package Utils;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateUtils {
    public static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    public static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    public static LocalDate parseDate(String date){
        try {
            return LocalDate.parse(date, dateFormatter);
        } catch (DateTimeParseException e) {
            System.out.println("Please enter by format 'dd-mm-yyyy'");
        }
        return null;
    }
    public static String formatDate(LocalDate localDate){
        try {
            return localDate.format(dateFormatter);
        } catch (DateTimeException e){
            e.printStackTrace();
        }
        return null;
    }
    public static LocalDate parseDateTime(String dateTime) {
        try {
            return LocalDate.parse(dateTime, dateTimeFormatter);
        } catch (DateTimeParseException e) {
            System.out.println("Please enter by format 'dd-MM-yyyy hh:mm:ss'");
        }
        return null;
    }

    public static String formatDateTime(LocalDate localDateTime) {
        try {
            return localDateTime.format(dateTimeFormatter);
        } catch (DateTimeException e) {
            e.printStackTrace();
        }
        return null;
    }
}
