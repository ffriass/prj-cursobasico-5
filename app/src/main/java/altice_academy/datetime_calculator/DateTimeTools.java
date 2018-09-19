package altice_academy.datetime_calculator;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.ParseException;
import java.time.*;
import java.text.SimpleDateFormat;
import java.time.format.TextStyle;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateTimeTools {

    private int year, month, day, hour, minute;

    private DatePickerDialog myDatePicker;
    private TimePickerDialog myTimePicker;
    private Calendar myCalendar;
    private SimpleDateFormat timeFormat, dateFormat, yearFormat, dateTimeFormat;

    private  TimePickerDialog.OnTimeSetListener
            mySetTimeListener;

    private DatePickerDialog.OnDateSetListener
            mySetDateListener;


    public DateTimeTools() {
        myCalendar = Calendar.getInstance();

        timeFormat = new SimpleDateFormat("hh:mm a", Locale.getDefault());
        dateFormat = new SimpleDateFormat("MMMM/dd" , Locale.getDefault());
        yearFormat = new SimpleDateFormat("YYYY", Locale.getDefault());
        dateTimeFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm a", Locale.getDefault());
    }

    public SimpleDateFormat getTimeFormat() {
        return timeFormat;
    }

    public SimpleDateFormat getDateFormat() {
        return dateFormat;
    }

    public SimpleDateFormat getYearFormat() {
        return yearFormat;
    }

    public Calendar getMyCalendar(){

        getMySetDateListener();
        getMySetTimeListener();
        return  myCalendar;
    }

    private TimePickerDialog.OnTimeSetListener getMySetTimeListener() {

        mySetTimeListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                myCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                myCalendar.set(Calendar.MINUTE, minute);
            }};

        return mySetTimeListener;
    }

    private DatePickerDialog.OnDateSetListener getMySetDateListener() {

        mySetDateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, month);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            }};
        return mySetDateListener;
    }

    public DatePickerDialog getMyDatePicker(Activity myActivity){
        myDatePicker = new DatePickerDialog( myActivity,
                getMySetDateListener(),
                myCalendar.get(Calendar.DAY_OF_MONTH),
                myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.YEAR));

        return  myDatePicker;
    }

    public TimePickerDialog getMyTimePicker(Activity myActivity){
        myTimePicker = new TimePickerDialog(myActivity,
                getMySetTimeListener(),
                myCalendar.get(Calendar.HOUR_OF_DAY),
                myCalendar.get(Calendar.MINUTE),
                false);
        return  myTimePicker;
    }


    public int getYear() { return year; }

    public void setYear(int year) { this.year = year; }

    public int getMonth() { return month; }

    public void setMonth(int month) { this.month = month; }

    public int getDay() { return day; }

    public void setDay(int day) { this.day = day; }

    public int getHour() { return hour; }

    public void setHour(int hour) { this.hour = hour; }

    public int getMinute() { return minute; }

    public void setMinute(int minute) { this.minute = minute; }

    public void getDateDistance(Calendar date1, Calendar date2){

        int months;

        if(date1.get(Calendar.YEAR) > date2.get(Calendar.YEAR)){

            if(date1.get(Calendar.MONTH) < date2.get(Calendar.MONTH)){

                month = 12 - (date2.get(Calendar.MONTH) - date1.get(Calendar.MONTH));
            }
            else {
                month = date1.get(Calendar.MONTH) - date2.get(Calendar.MONTH);
            }

            year = date1.get(Calendar.YEAR) - date2.get(Calendar.YEAR);
            day = ((int) Math.sqrt(Math.pow(date1.get(Calendar.DAY_OF_MONTH) - date2.get(Calendar.DAY_OF_MONTH),2)));
            hour = ((int) Math.sqrt(Math.pow(date1.get(Calendar.HOUR_OF_DAY) - date2.get(Calendar.HOUR_OF_DAY),2)));
            minute = ((int) Math.sqrt(Math.pow(date1.get(Calendar.MINUTE) - date2.get(Calendar.MINUTE),2)));
            months = date1.get(Calendar.MONTH) - date2.get(Calendar.MONTH);

        }
        else {
            if(date2.get(Calendar.MONTH) < date1.get(Calendar.MONTH)){

                month = 12- (date1.get(Calendar.MONTH) - date2.get(Calendar.MONTH));
            }
            else {
                month = date2.get(Calendar.MONTH) - date1.get(Calendar.MONTH);
            }
            year = date2.get(Calendar.YEAR) - date1.get(Calendar.YEAR);
            day = ((int) Math.sqrt(Math.pow(date2.get(Calendar.DAY_OF_MONTH) - date1.get(Calendar.DAY_OF_MONTH),2)));
            hour = ((int) Math.sqrt(Math.pow(date2.get(Calendar.HOUR_OF_DAY) - date1.get(Calendar.HOUR_OF_DAY),2)));
            minute = ((int) Math.sqrt(Math.pow(date2.get(Calendar.MINUTE) - date1.get(Calendar.MINUTE),2)));
            months = date2.get(Calendar.MONTH) - date1.get(Calendar.MONTH);

        }

        if (months < 0 || (months == 0 && year < 0)) {
            if(year >0)
                year--;
        }

    }

    public void printResult(TextView dateResult, TextView timeResult ){

        dateResult.setText(Integer.toString(getYear())
                .concat(" Years, ")
                .concat(Integer.toString(getMonth()))
                .concat(" Months, ")
                .concat(Integer.toString(getDay()))
                .concat(" Days "));

        timeResult.setText(
                Integer.toString(getHour())
                        .concat(" Hours ")
                        .concat(Integer.toString(getMinute()))
                        .concat(" Minutes "));

    }

}
