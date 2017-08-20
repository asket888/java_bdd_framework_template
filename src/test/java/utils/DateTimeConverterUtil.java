package utils;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class DateTimeConverterUtil {

    public static void main(String[] args) {

        System.out.println(currentDay());
        System.out.println(currentDate());
        System.out.println(calculateDate(-3));
    }

    public static String currentDay() {

        return new SimpleDateFormat("EEEE", Locale.ENGLISH)
                .format(System.currentTimeMillis());
    }

    public static String currentDate() {

        DateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");

        Calendar calendar = Calendar.getInstance();

        return dateFormat.format(calendar.getTime());
    }

    public static String calculateDate(int dayNum) {

        DateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, dayNum);

        return dateFormat.format(calendar.getTime());
    }
}
