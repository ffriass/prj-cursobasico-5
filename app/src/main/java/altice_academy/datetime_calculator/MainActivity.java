package altice_academy.datetime_calculator;

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

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    private static final  int [] BUTTONS_IDS = {
            R.id.btn_ok_1, R.id.btn_ok_2, R.id.btn_Calcular, R.id.txt_Time1,
            R.id.txt_Time2, R.id.txt_Date1, R.id.txt_Date2, R.id.txt_Time1
    };

    private TextView dateDisplay, dateDisplay2, timeDisplay,timeDisplay2, yearDisplay, yearDisplay2;
    private DatePickerDialog myDatePicker, myDatePicker2;
    private TimePickerDialog myTimePicker, myTimePicker2;
    private Calendar myCalendar, myCalendar2;
    private SimpleDateFormat timeFormat, dateFormat, yearFormat, dateTimeFormat;

    //For Dates to be calculated

    private DatePickerDialog.OnDateSetListener
            mySetDateListener2 = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            myCalendar2.set(Calendar.YEAR, year);
            myCalendar2.set(Calendar.MONTH, month);
            myCalendar2.set(Calendar.DAY_OF_MONTH, dayOfMonth);
           refreshDisplay();
        }};

    private DatePickerDialog.OnDateSetListener
            mySetDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, month);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            refreshDisplay();
        }};

    private  TimePickerDialog.OnTimeSetListener
            mySetTimeListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            myCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
            myCalendar.set(Calendar.MINUTE, minute);
            refreshDisplay();
        }};

    private  TimePickerDialog.OnTimeSetListener
            mySetTimeListener2 = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            myCalendar2.set(Calendar.HOUR_OF_DAY, hourOfDay);
            myCalendar2.set(Calendar.MINUTE, minute);
            refreshDisplay();
        }};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.calendar_icon);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setTitle("DateTime Calculator");

        myCalendar = Calendar.getInstance();
        myCalendar2 = Calendar.getInstance();

        myDatePicker = new DatePickerDialog(this,
                mySetDateListener,
                myCalendar.get(Calendar.DAY_OF_MONTH),
                myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.YEAR));

        myTimePicker = new TimePickerDialog(this,
                mySetTimeListener,
                myCalendar.get(Calendar.HOUR_OF_DAY),
                myCalendar.get(Calendar.MINUTE),
                false);

        myDatePicker2 = new DatePickerDialog(this,
                mySetDateListener2,
                myCalendar2.get(Calendar.DAY_OF_MONTH),
                myCalendar2.get(Calendar.MONTH),
                myCalendar2.get(Calendar.YEAR));

        myTimePicker2 = new TimePickerDialog(this,
                mySetTimeListener2,
                myCalendar2.get(Calendar.HOUR_OF_DAY),
                myCalendar2.get(Calendar.MINUTE),
                false);

        timeFormat = new SimpleDateFormat("hh:mm a", Locale.getDefault());
        dateFormat = new SimpleDateFormat("MMMM/dd" , Locale.getDefault());
        yearFormat = new SimpleDateFormat("YYYY", Locale.getDefault());
        dateTimeFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm a", Locale.getDefault());

        dateDisplay = findViewById(R.id.txt_Date1);
        dateDisplay2 = findViewById(R.id.txt_Date2);
        timeDisplay = findViewById(R.id.txt_Time1);
        timeDisplay2 = findViewById(R.id.txt_Time2);
        yearDisplay = findViewById(R.id.txt_year1);
        yearDisplay2 = findViewById(R.id.txt_year2);

        for(int id: BUTTONS_IDS)
            findViewById(id).setOnClickListener(this);

        refreshDisplay();

    }

    private void refreshDisplay(){

        dateDisplay.setText(dateFormat.format(myCalendar.getTime()));
        dateDisplay2.setText(dateFormat.format(myCalendar2.getTime()));
        timeDisplay.setText(timeFormat.format(myCalendar.getTime()));
        timeDisplay2.setText(timeFormat.format(myCalendar2.getTime()));
        yearDisplay.setText(yearFormat.format(myCalendar.getTime()));
        yearDisplay2.setText(yearFormat.format(myCalendar2.getTime()));

    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.btn_ok_1:
                myDatePicker.show();
                return;

            case R.id.txt_Date1:
                myDatePicker.show();
                break;

            case R.id.txt_Time1:
                myTimePicker.show();
                break;

            case R.id.txt_Time2:
                myTimePicker2.show();
                break;

            case R.id.txt_Date2:
                myDatePicker2.show();
                break;
            case R.id.btn_ok_2:
                myDatePicker2.show();
                break;

            case R.id.btn_Calcular:
               getDateDistance(myCalendar,myCalendar2);
                break;
        }

    }

    public void getDateDistance(Calendar date1, Calendar date2){

        int diffYear, diffMonth, diffDay, diffHour, diffMin, months;

        if(date1.get(Calendar.YEAR) > date2.get(Calendar.YEAR)){

            if(date1.get(Calendar.MONTH) < date2.get(Calendar.MONTH)){

                diffMonth = 12 - (date2.get(Calendar.MONTH) - date1.get(Calendar.MONTH));
            }
            else {
                diffMonth = date1.get(Calendar.MONTH) - date2.get(Calendar.MONTH);
            }

             diffYear = date1.get(Calendar.YEAR) - date2.get(Calendar.YEAR);
             diffDay = ((int) Math.sqrt(Math.pow(date1.get(Calendar.DAY_OF_MONTH) - date2.get(Calendar.DAY_OF_MONTH),2)));
             diffHour = ((int) Math.sqrt(Math.pow(date1.get(Calendar.HOUR_OF_DAY) - date2.get(Calendar.HOUR_OF_DAY),2)));
             diffMin = ((int) Math.sqrt(Math.pow(date1.get(Calendar.MINUTE) - date2.get(Calendar.MINUTE),2)));
             months = date1.get(Calendar.MONTH) - date2.get(Calendar.MONTH);

        }
        else {
            if(date2.get(Calendar.MONTH) < date1.get(Calendar.MONTH)){

                diffMonth = 12- (date1.get(Calendar.MONTH) - date2.get(Calendar.MONTH));
            }
            else {
                diffMonth = date2.get(Calendar.MONTH) - date1.get(Calendar.MONTH);
            }
             diffYear = date2.get(Calendar.YEAR) - date1.get(Calendar.YEAR);
             diffDay = ((int) Math.sqrt(Math.pow(date2.get(Calendar.DAY_OF_MONTH) - date1.get(Calendar.DAY_OF_MONTH),2)));
             diffHour = ((int) Math.sqrt(Math.pow(date2.get(Calendar.HOUR_OF_DAY) - date1.get(Calendar.HOUR_OF_DAY),2)));
             diffMin = ((int) Math.sqrt(Math.pow(date2.get(Calendar.MINUTE) - date1.get(Calendar.MINUTE),2)));
            months = date2.get(Calendar.MONTH) - date1.get(Calendar.MONTH);

        }

        if (months < 0 || (months == 0 && diffDay < 0)) {
            if(diffYear >0){

                diffYear--;
            }

        }

        printResult(diffYear, diffMonth, diffDay, diffHour,diffMin);

    }


    private void printResult(int years, int months, int days, int hours, int mins){

        TextView dateResult = findViewById(R.id.txt_DateResult);
        TextView timeResult = findViewById(R.id.txt_TimeResult);

        dateResult.setText(Integer.toString(years)
                .concat(" Years, ")
                .concat(Integer.toString(months))
                .concat(" Months, ")
                .concat(Integer.toString(days))
                .concat(" Days "));

        timeResult.setText(
                Integer.toString(hours)
                .concat(" Hours ")
                .concat(Integer.toString(mins))
                .concat(" Minutes "));

    }
}
