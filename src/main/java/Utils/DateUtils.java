package Utils;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateUtils {
    public static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    public static LocalDate parseDate(String date){
        try {
            return LocalDate.parse(date, dateTimeFormatter);
        } catch (DateTimeParseException e) {
            System.err.println("Please enter by format 'dd-mm-yyyy'");
        }
        return null;
    }
    public static String formatDate(LocalDate localDate){
        try {
            return localDate.format(dateTimeFormatter);
        } catch (DateTimeException e){
            e.printStackTrace();
        }
        return null;
    }
}
