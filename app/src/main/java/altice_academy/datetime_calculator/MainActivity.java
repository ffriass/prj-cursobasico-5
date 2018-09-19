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

    private TextView dateDisplay, dateDisplay2, timeDisplay,timeDisplay2, yearDisplay, yearDisplay2, dateResult, timeResult;
    private DatePickerDialog  myDatePicker, myDatePicker2;
    private TimePickerDialog  myTimePicker, myTimePicker2;
    private Calendar myCalendar, myCalendar2;

    //My own class declaration
    private  DateTimeTools myDateTimeTools, myDateTimeTools2;

    //For Dates to be calculated



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setLogo(R.mipmap.calendar_icon);
            getSupportActionBar().setDisplayUseLogoEnabled(true);
            getSupportActionBar().setTitle("DateTime Calculator");

        }catch (Exception e){

            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }


        myDateTimeTools = new DateTimeTools();
        myDateTimeTools2 = new DateTimeTools();

        myCalendar =  myDateTimeTools.getMyCalendar();
        myCalendar2 =  myDateTimeTools2.getMyCalendar();

        myDatePicker = myDateTimeTools.getMyDatePicker(this);
        myDatePicker2 = myDateTimeTools2.getMyDatePicker(this);

        myTimePicker = myDateTimeTools.getMyTimePicker(this);
        myTimePicker2 = myDateTimeTools2.getMyTimePicker(this);


        dateResult = findViewById(R.id.txt_DateResult);
        timeResult = findViewById(R.id.txt_TimeResult);

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

    public void refreshDisplay(){

        dateDisplay.setText(myDateTimeTools.getDateFormat().format(myCalendar.getTime()));
        dateDisplay2.setText(myDateTimeTools.getDateFormat().format(myCalendar2.getTime()));
        timeDisplay.setText(myDateTimeTools.getTimeFormat().format(myCalendar.getTime()));
        timeDisplay2.setText(myDateTimeTools.getTimeFormat().format(myCalendar2.getTime()));
        yearDisplay.setText(myDateTimeTools.getYearFormat().format(myCalendar.getTime()));
        yearDisplay2.setText(myDateTimeTools.getYearFormat().format(myCalendar2.getTime()));

    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.btn_ok_1:
                myDatePicker.show();
                break;

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
               myDateTimeTools.getDateDistance(myCalendar,myCalendar2);
               myDateTimeTools.printResult(dateResult, timeResult);
                break;
        }
        refreshDisplay();
    }


}
