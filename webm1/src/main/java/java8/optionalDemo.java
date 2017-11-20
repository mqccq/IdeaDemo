package java8;

import java.io.*;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/10/10.
 */
public class optionalDemo {
    public static void main(String[] args) {
        /*LocalDate today = LocalDate.now();
        System.out.println(today);
        LocalDate firstDay_2017 = LocalDate.of(2017, Month.OCTOBER, 19);
        System.out.println(firstDay_2017);
        LocalTime time = LocalTime.now();
        System.out.println(time);
        LocalDate date = LocalDate.now(ZoneId.of("America/Phoenix"));
        System.out.println(date);*/
        /*LocalDateTime today = LocalDateTime.now();
        System.out.println(today);
        Instant timestamp = Instant.now();
        System.out.println(timestamp);*/
        /*LocalDate today = LocalDate.now();

        //Get the Year, check if it's leap year
        System.out.println("Year "+today.getYear()+" is Leap Year? "+today.isLeapYear());

        //Compare two LocalDate for before and after
        System.out.println("Today is before 01/01/2015? "+today.isBefore(LocalDate.of(2015,1,1)));

        //Create LocalDateTime from LocalDate
        System.out.println("Current Time="+today.atTime(LocalTime.now()));

        //plus and minus operations
        System.out.println("10 days after today will be "+today.plusDays(10));
        System.out.println("3 weeks after today will be "+today.plusWeeks(3));
        System.out.println("20 months after today will be "+today.plusMonths(20));

        System.out.println("10 days before today will be "+today.minusDays(10));
        System.out.println("3 weeks before today will be "+today.minusWeeks(3));
        System.out.println("20 months before today will be "+today.minusMonths(20));

        //Temporal adjusters for adjusting the dates
        System.out.println("First date of this month= "+today.with(TemporalAdjusters.firstDayOfMonth()));
        LocalDate lastDayOfYear = today.with(TemporalAdjusters.lastDayOfYear());
        System.out.println("Last date of this year= "+lastDayOfYear);

        Period period = today.until(lastDayOfYear);
        System.out.println("Period Format= "+period);
        System.out.println("Months remaining in the year= "+period.getMonths());*/
        //Format examples
       /* LocalDate date = LocalDate.now();
        //default format
        System.out.println("Default format of LocalDate="+date);
        //specific format
        System.out.println(date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        System.out.println(date.format(DateTimeFormatter.BASIC_ISO_DATE));

        LocalDateTime dateTime = LocalDateTime.now();
        //default format
        System.out.println("Default format of LocalDateTime="+dateTime);
        //specific format
        System.out.println(dateTime.format(DateTimeFormatter.ofPattern("d::MMM::uuuu HH::mm::ss")));
        System.out.println(dateTime.format(DateTimeFormatter.ofPattern("yyyy:MM:dd HH::mm::ss")));
        System.out.println(dateTime.format(DateTimeFormatter.BASIC_ISO_DATE));

        Instant dateTime1 = new Date().toInstant();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(dateTime1,ZoneId.systemDefault());
        System.out.println(localDateTime);*/

//        LocalDate localDate = LocalDate.of(2017,10,22);
        /*LocalDate localDate = LocalDate.now();

        System.out.println(localDate.plusDays(7-localDate.getDayOfWeek().getValue()+1));*/
        //        LocalTime localTime = LocalTime.now();
//        localTime.plusMinutes(20);
//        System.out.println(localTime.plusMinutes(20));
//        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDateTime localDate = LocalDate.now().atTime(LocalTime.now());
        System.out.println(localDate);
//        Duration duration = Duration.between(LocalDate.now(),LocalDate.of(2017,10,20));
//        System.out.println(duration);

    }
}
