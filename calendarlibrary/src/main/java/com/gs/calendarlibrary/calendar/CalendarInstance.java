package com.gs.calendarlibrary.calendar;
import android.content.Context;
import android.util.Log;

import com.gs.calendarlibrary.MyCalendarView;
import com.gs.calendarlibrary.R;

import java.util.Calendar;

/**
 * Created by Ghanshyam on 21/02/2017.
 */
public class CalendarInstance {

    private HijriCalendar hijri;
    private GregorianCalendar georgian;
    private Context mContext;
    private int mMode;
    private int currentYear;

    public CalendarInstance(Context context, int mode) {
        this.hijri = new HijriCalendar(context);
        this.georgian = new GregorianCalendar(context);
        this.mMode = mode;
    }

    public void plusMonth() {
        if (mMode == MyCalendarView.Mode.Hijri.getModeValue()) {
            hijri.plusMonth();
            return;
        }
        georgian.plusMonth();
    }


    public void minusMonth() {
        if (mMode == MyCalendarView.Mode.Hijri.getModeValue()) {
            hijri.minusMonth();
            return;
        }
        georgian.minusMonth();
    }

    public boolean isCurrentMonth() {
        if (mMode == MyCalendarView.Mode.Hijri.getModeValue())
            return hijri.isCurrentMonth();
        return georgian.isCurrentMonth();
    }

    public void setMonth(int month) {
        if (mMode == MyCalendarView.Mode.Hijri.getModeValue()) {
            hijri.setMonth(month);
            return;
        }
        georgian.setMonth(month);
    }

    public void setDay(int day) {
        if (mMode == MyCalendarView.Mode.Hijri.getModeValue()) {
            hijri.setDay(day);
            return;
        }
        georgian.setDay(day);
    }

    public void setSelectedDayMonthYear(int day,int month,int year){

        if (mMode == MyCalendarView.Mode.Hijri.getModeValue()) {
            hijri.setSelectedDayMonthYear(day,month,year);
            return;
        }
        georgian.setSelectedDayMonthYear(day,month,year);
    }

    public int[] getSelectedDayMonthYear(){
        if (mMode == MyCalendarView.Mode.Hijri.getModeValue()) {
            return hijri.getSelectedDayMonthYear();
        }
        return georgian.getSelectedDayMonthYear();
    }


    public void setYear(int year) {
        if (mMode == MyCalendarView.Mode.Hijri.getModeValue()) {
            hijri.setYear(year);
            return;
        }
        georgian.setYear(year);
    }


    public int getWeekStartFrom() {
        if (mMode == MyCalendarView.Mode.Hijri.getModeValue())
            return hijri.getWeekStartFrom();
        return georgian.getWeekStartFrom();
    }

    public int getLastDayOfMonth() {
        if (mMode == MyCalendarView.Mode.Hijri.getModeValue())
            return hijri.getLastDayOfMonth();
        return georgian.getLastDayOfMonth();
    }

    public int getDayOfMonth() {
        if (mMode == MyCalendarView.Mode.Hijri.getModeValue())
            return hijri.getDayOfMonth();
        return georgian.getDayOfMonth();
    }

    public int getMonth() {
        if (mMode == MyCalendarView.Mode.Hijri.getModeValue())
            return hijri.getMonth();
        return georgian.getMonth();
    }

    public String getMonthName() {
        if (mMode == MyCalendarView.Mode.Hijri.getModeValue())
            return hijri.getMonthName();
        return georgian.getMonthName();
    }

    public int getYear() {
        if (mMode == MyCalendarView.Mode.Hijri.getModeValue())
            return hijri.getYear();
        return georgian.getYear();
    }

    public int lengthOfMonth() {
        if (mMode == MyCalendarView.Mode.Hijri.getModeValue())
            return hijri.lengthOfMonth();
        return georgian.lengthOfMonth();
    }

    public int lengthOfMonthAccordingToyear(int year, int month) {
        if (mMode == MyCalendarView.Mode.Hijri.getModeValue())
            return hijri.lengthOfMonth();
        return georgian.lengthOfMonth(year, month);
    }

    public int getCurrentMonth() {
        if (mMode == MyCalendarView.Mode.Hijri.getModeValue())
            return hijri.getCurrentMonth();
        return georgian.getCurrentMonth();
    }

    public int getOffsetMonthCount() {
        if (mMode == MyCalendarView.Mode.Hijri.getModeValue())
            return hijri.getOffsetMonthCount();
        return georgian.getOffsetMonthCount();
    }

    public String[] getMonths() {
        if (mMode == MyCalendarView.Mode.Hijri.getModeValue())
            return hijri.getMonths();
        return georgian.getMonths();
    }

    public int getCurrentYear() {
        if (mMode == MyCalendarView.Mode.Hijri.getModeValue())
            return hijri.getCurrentYear();
        return georgian.getCurrentYear();
    }

    public int getCalendarCurrentMonth() {
        if (mMode == MyCalendarView.Mode.Hijri.getModeValue())
            return hijri.getCalendarCurrentMonth();
        return georgian.getCalendarCurrentMonth();
    }

    public int getCalendarDay() {
        if (mMode == MyCalendarView.Mode.Hijri.getModeValue())
            return hijri.getCalendarDay();
        return georgian.getCalendarDay();
    }

    public int getCalendarCurrentYear() {
        if (mMode == MyCalendarView.Mode.Hijri.getModeValue())
            return hijri.getCalendarCurrentYear();
        return georgian.getCalendarCurrentYear();
    }


    public static class GregorianCalendar {

        private static final String TAG = "GregorianCalendar";
        private Calendar calendar;
        private String[] monthNames;
        private int countMonth, countYear, currentMonth, currentYear;
        private int selectedDay,selectedMonth,selectedYear;

        public GregorianCalendar(Context context) {
            calendar = Calendar.getInstance();
            monthNames = new String[]{
                    context.getResources().getString(R.string.January),
                    context.getResources().getString(R.string.February),
                    context.getResources().getString(R.string.March),
                    context.getResources().getString(R.string.April),
                    context.getResources().getString(R.string.May),
                    context.getResources().getString(R.string.June),
                    context.getResources().getString(R.string.July),
                    context.getResources().getString(R.string.August),
                    context.getResources().getString(R.string.September),
                    context.getResources().getString(R.string.October),
                    context.getResources().getString(R.string.November),
                    context.getResources().getString(R.string.December)};
            countMonth = calendar.get(Calendar.MONTH);
            countYear = calendar.get(Calendar.YEAR);
            currentMonth = countMonth;
            Log.d(TAG, "HijriCalendar: " + countMonth);
            currentYear = countYear;

            setSelectedDayMonthYear(getDayOfMonth(),getCurrentMonth()+1,getCurrentYear());
        }


        public void plusMonth() {
            countMonth++;
            if (countMonth == 12) {
                countMonth = 0;
                countYear++;
            }
            calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, countYear);
            calendar.set(Calendar.MONTH, countMonth);

        }

        public void minusMonth() {
            countMonth--;
            if (countMonth == -1) {
                countMonth = 11;
                countYear--;
            }
            calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, countYear);
            calendar.set(Calendar.MONTH, countMonth);
        }


        public boolean isCurrentMonth() {
            return (countMonth == currentMonth && currentYear == countYear);
        }

        public void setMonth(int month) {
            Log.d(TAG, "setMonth() called with: " + "month = [" + month + "]");
            this.countMonth = month - 1;
            calendar.set(Calendar.MONTH, month);
        }

        public void setDay(int day) {
            calendar.set(Calendar.DAY_OF_MONTH, day);
        }

        public void setSelectedDayMonthYear(int day,int month,int year) {
            selectedDay = day;
            selectedMonth = month;
            selectedYear = year;
        }

        public int[] getSelectedDayMonthYear(){
            int arr[] = {selectedDay,selectedMonth,selectedYear};
            return arr;
        }

        public void setYear(int year) {
            this.countYear = year;
            calendar.set(Calendar.YEAR, year);
        }

        public int getWeekStartFrom() {
            Calendar temp = Calendar.getInstance();
            temp.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), 1);
            return temp.get(Calendar.DAY_OF_WEEK);
        }

        public int getLastDayOfMonth() {
            return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        }

        public int getDayOfMonth() {
            return calendar.get(Calendar.DAY_OF_MONTH);
        }

        public int getCalendarDay() {
            return calendar.get(Calendar.DAY_OF_MONTH);
        }

        public int getMonth() {
            return countMonth + 1;
        }

        public String getMonthName() {

            return monthNames[getOffsetMonthCount()];
        }

        public int getYear() {
            return calendar.get(Calendar.YEAR);
        }

        public int lengthOfMonth() {
            return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        }

        public int lengthOfMonth(int year, int month) {
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.YEAR, year);
            cal.set(Calendar.MONTH, month);
            return cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        }

        public boolean isLeapYear(int year) {
            assert year >= 1583; // not valid before this date.
            return ((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0);
        }

        public int getCalendarCurrentMonth() {
            return currentMonth;
        }

        public int getCalendarCurrentYear() {
            return currentYear;
        }

        public int getCurrentMonth() {
            return getOffsetMonthCount();
        }

        public int getOffsetMonthCount() {
            if (countMonth < 0)
                return 11;
            else if (countMonth > 11)
                return 0;
            return countMonth;
        }

        public String[] getMonths() {
            return monthNames;
        }

        public int getCurrentYear() {
            return countYear;
        }
    }


    public class HijriCalendar {

        private static final String TAG = "HijriCalendar";
        private MyCustomCalendar calendar;
        private String[] monthNames;
        private int countMonth, countYear, currentMonth, currentYear;
        private int selectedDay,selectedMonth,selectedYear;

        public HijriCalendar(Context context) {
            calendar = new MyCustomCalendar();
            monthNames = new String[]{
                    context.getResources().getString(R.string.muharram),
                    context.getResources().getString(R.string.safar),
                    context.getResources().getString(R.string.rabi_i),
                    context.getResources().getString(R.string.rabi_ii),
                    context.getResources().getString(R.string.jamada_i),
                    context.getResources().getString(R.string.jamada_ii),
                    context.getResources().getString(R.string.rajab),
                    context.getResources().getString(R.string.shaban),
                    context.getResources().getString(R.string.ramadan),
                    context.getResources().getString(R.string.shawal),
                    context.getResources().getString(R.string.dhu_alqadah),
                    context.getResources().getString(R.string.dhu_alhijjah)};
            countMonth = calendar.get(Calendar.MONTH);
            countYear = calendar.get(Calendar.YEAR);
            currentMonth = countMonth;
            Log.d(TAG, "HijriCalendar: " + countMonth);
            currentYear = countYear;

            setSelectedDayMonthYear(getDayOfMonth(),getCurrentMonth()+1,getCurrentYear());
        }

//        public void plusMonth(){
//            countMonth++;
//            if(countMonth==13) {
//                countMonth = 1;
//                countYear++;
//            }
//            calendar=new MyCustomCalendar(countYear,countMonth,calendar.get(Calendar.DAY_OF_MONTH));
//
//        }
//
//        public void minusMonth(){
//            countMonth--;
//            if(countMonth==0) {
//                countMonth = 12;
//                countYear--;
//            }
//            calendar=new MyCustomCalendar(countYear,countMonth,calendar.get(Calendar.DAY_OF_MONTH));
//
//        }


        public void plusMonth() {
            countMonth++;
            if (countMonth == 12) {
                countMonth = 0;
                countYear++;
            }
            calendar = new MyCustomCalendar(countYear, countMonth, calendar.get(Calendar.DAY_OF_MONTH));
        }

        public void minusMonth() {
            countMonth--;
            if (countMonth == -1) {
                countMonth = 11;
                countYear--;
            }
            calendar = new MyCustomCalendar(countYear, countMonth, calendar.get(Calendar.DAY_OF_MONTH));
        }

        public boolean isCurrentMonth() {
            return (countMonth == currentMonth && currentYear == countYear);
        }

        public void setMonth(int month) {
            this.countMonth = month;
            calendar.set(Calendar.MONTH, month);
        }

        public void setDay(int day) {
            calendar.set(Calendar.DAY_OF_MONTH, day);
        }

        public void setSelectedDayMonthYear(int day,int month,int year) {
            selectedDay = day;
            selectedMonth = month;
            selectedYear = year;
        }

        public int[] getSelectedDayMonthYear(){
            int arr[] = {selectedDay,selectedMonth,selectedYear};
            return arr;
        }

        public void setYear(int year) {
            this.countYear = year;
            calendar.set(Calendar.YEAR, year);
        }

        public int getWeekStartFrom() {
            MyCustomCalendar temp = new MyCustomCalendar();
            temp.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), 1);
            return temp.get(Calendar.DAY_OF_WEEK);
        }

        public int getLastDayOfMonth() {
            return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        }

        public int getCalendarDay() {
            return calendar.getCalDay(Calendar.DAY_OF_MONTH);
        }

        public int getDayOfMonth() {
            return calendar.get(Calendar.DAY_OF_MONTH);
        }

        public int getMonth() {
            return countMonth;
        }

        public String getMonthName() {
            return monthNames[getOffsetMonthCount()];
        }

        public int getYear() {
            return calendar.get(Calendar.YEAR);
        }


        public int lengthOfMonth() {
            return calendar.lengthOfMonth();
        }

        public int getCurrentMonth() {
            return getOffsetMonthCount();
        }

        public int getOffsetMonthCount() {
            int temp = countMonth;
            if (temp == -1)
                temp = 11;
            else if (temp >= 12)
                temp = 0;
            return temp;
        }


        public String[] getMonths() {
            return monthNames;
        }

        public int getCalendarCurrentMonth() {
            return currentMonth;
        }

        public int getCalendarCurrentYear() {
            return currentYear;
        }

        public int getCurrentYear() {
            return countYear;
        }
    }

    public static String toEnglishNumbers(String day) {
        String englishNumbers =
                day.replace("١", "1")
                        .replace("٢", "2")
                        .replace("٣", "3")
                        .replace("٤", "4")
                        .replace("٥", "5")
                        .replace("٦", "6")
                        .replace("٧", "7")
                        .replace("٨", "8")
                        .replace("٩", "9")
                        .replace("٠", "0");
        return englishNumbers;
    }

    public static String toArabicNumbers(String day) {
        String arabicNumbers =
                day.replace("1", "١")
                        .replace("2", "٢")
                        .replace("3", "٣")
                        .replace("4", "٤")
                        .replace("5", "٥")
                        .replace("6", "٦")
                        .replace("7", "٧")
                        .replace("8", "٨")
                        .replace("9", "٩")
                        .replace("0", "٠");
        return arabicNumbers;
    }

}
