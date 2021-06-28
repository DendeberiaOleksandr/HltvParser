package com.dendeberia;

import org.jsoup.nodes.Element;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.*;

public class DateParser {

    private CommonParser commonParser;
    private Calendar calendar;

    public DateParser(CommonParser commonParser) {
        this.commonParser = commonParser;
        this.calendar = Calendar.getInstance();
    }

    //Parses date format Jun 1st 2021
    public Date parseDate(String date) throws ParseException {
        if(date == null){
            throw new IllegalArgumentException("Date can't be null");
        }

        String[] split = date.split(" ");

        String month = null;
        int day = 0;
        int year = 0;

        for(int i = 0; i < split.length; i++){
            String dateString = split[i];

            //Contains only words
            if(dateString.matches("^[a-zA-Z]+$")){
                month = dateString;
            }
            //Begins of digit, after digits contains words
            else if(dateString.matches("^[0-9]+[a-zA-Z]+$")){
                String replaced = dateString.replaceAll("[a-zA-Z]", "");

                day = Integer.parseInt(replaced);
            }
            //Contains only digits
            else if(dateString.matches("^[0-9]+$")){
                year = Integer.parseInt(dateString);
            }
        }

        Date result = new SimpleDateFormat("MMM d yyy").parse(month + " " + day + " " + year);

        return result;
    }

    public boolean containsYear(String date){
        if(date == null){
            throw new IllegalArgumentException("Date should be non null");
        }

        String[] split = date.split(" ");

        for(int i = 0; i < split.length; i++){
            if(split[i].matches("^[0-9]+$")){
                return true;
            }
        }

        return false;
    }

    public int parseYear(String date) throws ParseException {
        if(date == null){
            throw new IllegalArgumentException("Date should be non null");
        }

        String[] split = date.split(" ");

        for(int i = 0; i < split.length; i++){
            if(split[i].matches("^[0-9]+$")){
                return Integer.parseInt(split[i]);
            }
        }

        throw new ParseException("Can't parse year", 0);
    }

    public List<Date> parseDates(List<String> dates) throws ParseException {
        List<Date> datesList = new ArrayList<Date>();

        int year = 0;

        for(int d = 0; d < dates.size(); d++){
            String dateString = dates.get(d);
            if(containsYear(dateString)){
                year = parseYear(dateString);
            }
        }

        for (int d = 0; d < dates.size(); d++){
            String dateString = dates.get(d);
            if(!containsYear(dateString)){
                dateString += " " + year;
            }
        }

        Date startDate = parseDate(dates.get(0));
        Date endDate = startDate;

        if(dates.size() == 2){
            endDate = parseDate(dates.get(1));
        }

        datesList.add(startDate);
        datesList.add(endDate);

        return datesList;
    }

    public Date parseDateFromSlotWrapperHeader(Element slotWrapperHeader) throws IOException {
        int minute = 0;
        int hour = 0;
        int date = 1;
        int month = 1;
        int year = 1900;

        Element timeSpan = commonParser.getElementByClass(slotWrapperHeader, "time-time");
        String time = "";
        if(timeSpan != null){
            time = timeSpan.text();
            hour = parseHourOfDay(time);
            minute = parseMinuteOfHour(time);
        }

        Element timeDayOfWeek = commonParser.getElementByClass(slotWrapperHeader, "time-day-of-week");
        Element timeDate = commonParser.getElementByClass(slotWrapperHeader, "time-day");//TODO get css style name

        if(timeDayOfWeek != null){
            String dayOfWeekText = timeDayOfWeek.text();
            DayOfWeek dayOfWeek = DayOfWeek.valueOf(dayOfWeekText);
            Date dateOfDayOfWeek = getDateOfDayOfWeek(dayOfWeek);
            year = calendar.get(Calendar.YEAR);
            month = calendar.get(Calendar.MONTH);
            date = calendar.get(Calendar.DAY_OF_MONTH);
        } else if(timeDate != null){
            //TODO
        }

        calendar.set(year, month, date, hour, minute);

        return calendar.getTime();
    }

    private int parseHourOfDay(String time){
        if(time != null){
            String[] split = time.split(":");
            if (split.length == 2){
                return Integer.parseInt(split[0]);
            }
        }

        throw new DateTimeException("Provided not time format hh:mm");
    }

    private int parseMinuteOfHour(String time){
        if(time != null){
            String[] split = time.split(":");
            if (split.length == 2){
                return Integer.parseInt(split[1]);
            }
        }

        throw new DateTimeException("Provided not time format hh:mm");
    }

    private Date getDateOfDayOfWeek(DayOfWeek dayOfWeek){
        if(dayOfWeek == null){
            return null;
        }

        LocalDate now = LocalDate.now();
        DayOfWeek nowDayOfWeek = now.getDayOfWeek();
        for (int i = 0; i < 7; i++){
            if(nowDayOfWeek.equals(dayOfWeek)){
                Calendar calendar = Calendar.getInstance();
                calendar.set(now.getYear(),
                                            now.getMonthValue(),
                                            now.getDayOfMonth());
                return calendar.getTime();
            }

            nowDayOfWeek.plus(1);
        }

        throw new DateTimeException("Can't get date of " + dayOfWeek.toString());
    }

}
