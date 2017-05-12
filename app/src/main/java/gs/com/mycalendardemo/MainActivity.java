package gs.com.mycalendardemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.gs.calendarlibrary.MyCalendarView;
/**
 * Created by Ghanshyam on 21/02/2017.
 */
public class MainActivity extends AppCompatActivity implements MyCalendarView.OnDateSetListener {

    MyCalendarView myCalendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((Button) findViewById(R.id.button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                myCalendarView = MyCalendarView.getInstance(MainActivity.this,true);

                myCalendarView.setOnDateSetListener(MainActivity.this);
                myCalendarView.setMinMaxHijriYear(1430, 1450);
                myCalendarView.setMinMaxGregorianYear(2013, 2020);
                myCalendarView.setMode(MyCalendarView.Mode.Gregorian);
                myCalendarView.setUILanguage(MyCalendarView.Language.English);
//                myCalendarView.setDaysOfWeekTextColor(getResources().getColor(R.color.orange_light));
//                myCalendarView.setDatePickerMonthlyDaysBckground(getResources().getColor(R.color.orange_light));
//                        .setDefaultHijriDate(8, 0, 1437)//months start from 0
                myCalendarView.setEnableScrolling(false);

                myCalendarView.showDialog();

            }
        });

        ((Button) findViewById(R.id.button22)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                myCalendarView = MyCalendarView.getInstance(MainActivity.this,true);

                myCalendarView.setOnDateSetListener(MainActivity.this);
                myCalendarView.setMinMaxHijriYear(1430, 1450);
                myCalendarView.setMinMaxGregorianYear(2013, 2020);
                myCalendarView.setMode(MyCalendarView.Mode.Hijri);
                myCalendarView.setUILanguage(MyCalendarView.Language.Arabic);
//                        .setDefaultHijriDate(8, 0, 1437)//months start from 0
                myCalendarView.setEnableScrolling(false);

                myCalendarView.showDialog();

            }
        });
    }

    @Override
    public void onDateSet(int year, int month, int day, int hour, int minute, int seconds, String ampm) {
        ((TextView) findViewById(R.id.textView)).setText(day + "/" + (month) + "/" + year);
    }
}
