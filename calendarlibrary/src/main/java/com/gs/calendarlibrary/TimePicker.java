package com.gs.calendarlibrary;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.NumberPicker;
import android.widget.NumberPicker.Formatter;

import java.text.DateFormatSymbols;
import java.util.Calendar;

import com.gs.calendarlibrary.calendar.CalendarInstance;
import com.gs.calendarlibrary.calendar.MyCustomCalendar;

import static com.gs.calendarlibrary.MyCalendarView.Language.Arabic;

/**
 * Created by Ghanshyam on 21/02/2017.
 */
public class TimePicker extends FrameLayout {

    private static final OnTimeChangedListener TimechangeListener = new OnTimeChangedListener() {
        public void onTimeChanged(TimePicker view, int hourOfDay, int minute, int seconds) {

        }
    };

    public static final Formatter TWO_DIGIT_FORMATTER_HOUR =
            new Formatter() {

                @Override
                public String format(int value) {
                    if (MyCalendarView.language == Arabic.getLanguageValue()) {
                        return CalendarInstance.toArabicNumbers(String.format("%02d", value));
                    } else {
                        return CalendarInstance.toEnglishNumbers(String.format("%02d", value));
                    }
                }
            };

    public static final Formatter TWO_DIGIT_FORMATTER_MINUTE =
            new Formatter() {
                @Override
                public String format(int value) {
                    if (MyCalendarView.language == Arabic.getLanguageValue()) {
                        return CalendarInstance.toArabicNumbers(String.format("%02d", value));
                    } else {
                        return CalendarInstance.toEnglishNumbers(String.format("%02d", value));
                    }
                }
            };


    Calendar calendar;
    int hour;
    int minute;
    int second;
    int Ampm;

    private int mCurrentHour = 0;
    private int mCurrentMinute = 0;
    private int mCurrentSeconds = 0;
    private Boolean mIs24HourView = false;
    private boolean mIsAm;

    String amTempText = "AM";
    String pmTempText = "PM";

    private NumberPicker mHourPicker;
    private NumberPicker mMinutePicker;
    private NumberPicker mAmpmnunberPicker;
    private String mAmText;
    private String mPmText;

    int currentDay, currentMonth, currentYear;
    int calDay, calMonth, calYear;

    final String[] arrtemp = new String[]{"AM", "PM", "AM", "PM", "AM", "PM", "AM", "PM"};

    final String[] arrtempArabic = new String[]{"صباحا", "بعد الظهر", "صباحا", "بعد الظهر", "صباحا", "بعد الظهر", "صباحا", "بعد الظهر"};

    String[] arrtempHolder;

    private OnTimeChangedListener mOnTimeChangedListener;

    public interface OnTimeChangedListener {

        void onTimeChanged(TimePicker view, int hourOfDay, int minute, int seconds);
    }

    public TimePicker(Context context) {
        this(context, null);
    }

    public TimePicker(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TimePicker(Context context, AttributeSet attrs, int defStyle) {

        super(context, attrs, defStyle);

        if (MyCalendarView.language == Arabic.getLanguageValue()) {
            arrtempHolder = arrtempArabic;
            amTempText = "صباحا";
            pmTempText = "بعد الظهر";
        } else {
            arrtempHolder = arrtemp;
            amTempText = "AM";
            pmTempText = "PM";
        }

        calendar = Calendar.getInstance();

        calendar.add(Calendar.HOUR_OF_DAY,minHour);
        calendar.add(Calendar.MINUTE,minMinute);
        calendar.add(Calendar.SECOND,minSecond);

        hour = calendar.get(Calendar.HOUR);
        minute = calendar.get(Calendar.MINUTE);
        second = calendar.get(Calendar.SECOND);

        Ampm = calendar.get(Calendar.AM_PM);

        mCurrentHour = hour;
        mCurrentMinute = minute;

        LayoutInflater inflater =
                (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.time_picker_widget,
                this,
                true);

        mHourPicker = (NumberPicker) findViewById(R.id.hour);
        mHourPicker.setMinValue(0);
        mHourPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {

                updateCalendar();

                mCurrentHour = newVal;

                if (calDay == currentDay && mCurrentHour <= hour
                        && Ampm == Calendar.AM && arrtempHolder[mAmpmnunberPicker.getValue()].equals(amTempText)) {
                    mCurrentHour = hour;
                    mHourPicker.setValue(hour);

                    if (mCurrentMinute < minute) {
                        mCurrentMinute = minute;
                        mMinutePicker.setValue(minute);
                    }
                } else if (calDay == currentDay && mCurrentHour <= hour
                        && Ampm == Calendar.PM && arrtempHolder[mAmpmnunberPicker.getValue()].equals(pmTempText)) {
                    mCurrentHour = hour;
                    mHourPicker.setValue(hour);

                    if (mCurrentMinute < minute) {
                        mCurrentMinute = minute;
                        mMinutePicker.setValue(minute);
                    }
                } else if (calDay == currentDay && Ampm == Calendar.PM && arrtempHolder[mAmpmnunberPicker.getValue()].equals(pmTempText)) {
                    if (mCurrentHour <= hour) {
                        mCurrentHour = hour;
                        mHourPicker.setValue(hour);

                        if (mCurrentMinute < minute) {
                            mCurrentMinute = minute;
                            mMinutePicker.setValue(minute);
                        }
                    }
                } else if (calDay == currentDay && Ampm == Calendar.PM && arrtempHolder[mAmpmnunberPicker.getValue()].equals(amTempText)) {
                    if (mCurrentHour <= hour) {
                        mCurrentHour = hour;
                        mHourPicker.setValue(hour);

                        if (mCurrentMinute < minute) {
                            mCurrentMinute = minute;
                            mMinutePicker.setValue(minute);
                        }
                    }
                }
                onTimeChanged();
            }
        });

        mMinutePicker = (NumberPicker) findViewById(R.id.minute);
        mMinutePicker.setMinValue(0);
        mMinutePicker.setMaxValue(59);
        mMinutePicker.setFormatter(TWO_DIGIT_FORMATTER_MINUTE);
        mMinutePicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker spinner, int oldVal, int newVal) {
                updateCalendar();
                mCurrentMinute = newVal;
                if (calDay == currentDay && mCurrentMinute < minute && mCurrentHour <= hour
                        && Ampm == Calendar.AM && arrtempHolder[mAmpmnunberPicker.getValue()].equals(amTempText)) {
                    mCurrentMinute = minute;
                    mMinutePicker.setValue(minute);

                } else if (calDay == currentDay && mCurrentMinute < minute && mCurrentHour <= hour
                        && Ampm == Calendar.PM && arrtempHolder[mAmpmnunberPicker.getValue()].equals(pmTempText)) {
                    mCurrentMinute = minute;
                    mMinutePicker.setValue(minute);
                } else if (calDay == currentDay && Ampm == Calendar.PM && arrtempHolder[mAmpmnunberPicker.getValue()].equals(pmTempText)) {
                    if (mCurrentMinute < minute && mCurrentHour <= hour) {
                        mCurrentMinute = minute;
                        mMinutePicker.setValue(minute);
                    }
                } else if (calDay == currentDay && Ampm == Calendar.PM && arrtempHolder[mAmpmnunberPicker.getValue()].equals(amTempText)) {
                    if (mCurrentMinute < minute && mCurrentHour <= hour) {
                        mCurrentMinute = minute;
                        mMinutePicker.setValue(minute);
                    }
                }
                onTimeChanged();
            }
        });

        mAmpmnunberPicker = (NumberPicker) findViewById(R.id.ampmnunberpicker);
        mAmpmnunberPicker.setMinValue(0);
        mAmpmnunberPicker.setMaxValue(arrtempHolder.length - 1);
        mAmpmnunberPicker.setDisplayedValues(arrtempHolder);

        mAmpmnunberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker spinner, int oldVal, int newVal) {
                updateCalendar();

                if (calDay == currentDay && Ampm == Calendar.PM && arrtempHolder[newVal].equals(amTempText)) {
                    if (Ampm == Calendar.AM) {
                        mAmpmnunberPicker.setValue(Calendar.AM);
                    } else {
                        mAmpmnunberPicker.setValue(Calendar.PM);
                    }
                    return;
                }


                if (calDay == currentDay && Ampm == Calendar.PM && arrtempHolder[newVal].equals(pmTempText)) {
                    if (mCurrentHour <= hour) {
                        mCurrentHour = hour;
                        mHourPicker.setValue(hour);

                        if (mCurrentMinute < minute) {
                            mCurrentMinute = minute;
                            mMinutePicker.setValue(minute);
                        }
                    }
                } else if (calDay == currentDay && Ampm == Calendar.AM && arrtempHolder[newVal].equals(amTempText)) {
                    if (mCurrentHour <= hour) {
                        mCurrentHour = hour;
                        mHourPicker.setValue(hour);

                        if (mCurrentMinute < minute) {
                            mCurrentMinute = minute;
                            mMinutePicker.setValue(minute);
                        }
                    }
                } else if (calDay == currentDay && Ampm == Calendar.PM && arrtempHolder[newVal].equals(pmTempText)) {
                    if (mCurrentHour <= hour) {
                        mCurrentHour = hour;
                        mHourPicker.setValue(hour);

                        if (mCurrentMinute < minute) {
                            mCurrentMinute = minute;
                            mMinutePicker.setValue(minute);
                        }
                    }

                } else if (calDay == currentDay && Ampm == Calendar.PM && arrtempHolder[newVal].equals(amTempText)) {
                    if (mCurrentHour <= hour) {
                        mCurrentHour = hour;
                        mHourPicker.setValue(hour);

                        if (mCurrentMinute < minute) {
                            mCurrentMinute = minute;
                            mMinutePicker.setValue(minute);
                        }
                    }
                }
            }
        });

        configurePickerRanges();

        Calendar cal = Calendar.getInstance();
        setOnTimeChangedListener(TimechangeListener);

        setCurrentMinute(cal.get(Calendar.MINUTE));
        setCurrentSecond(cal.get(Calendar.SECOND));

        mIsAm = (mCurrentHour < 12);

        DateFormatSymbols dfs = new DateFormatSymbols();
        String[] dfsAmPm = dfs.getAmPmStrings();
        mAmText = dfsAmPm[Calendar.AM];
        mPmText = dfsAmPm[Calendar.PM];
        if (!isEnabled()) {
            setEnabled(false);
        }
        mHourPicker.setValue(hour);
        mMinutePicker.setValue(minute);
        if (Ampm == Calendar.AM) {
            mAmpmnunberPicker.setValue(Calendar.AM);
        } else {
            mAmpmnunberPicker.setValue(Calendar.PM);
        }
    }


    int minHour = 0;
    int minMinute= 0;
    int minSecond = 0;
    public void addHourMinnuteSecondLimit(int hour,int minute,int second){
        this.minHour = hour;
        this.minMinute = minute;
        this.minSecond = second;
        updateCalendar();
    }

    public void updateCalendar() {

        try {

            calendar = Calendar.getInstance();
            calendar.add(Calendar.HOUR_OF_DAY,minHour);
            calendar.add(Calendar.MINUTE,minMinute);
            calendar.add(Calendar.SECOND,minSecond);

            hour = calendar.get(Calendar.HOUR);
            minute = calendar.get(Calendar.MINUTE);
            second = calendar.get(Calendar.SECOND);
            Ampm = calendar.get(Calendar.AM_PM);

            if (MyCalendarView.language == Arabic.getLanguageValue()) {
                int[] arrcaldata = MyCustomCalendar.UmmalquraGregorianConverter.toHijri(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE));
                calDay = arrcaldata[2];
                calMonth = arrcaldata[1];
                calYear = arrcaldata[0];

            } else {
                calDay = calendar.get(Calendar.DATE);
                calMonth = calendar.get(Calendar.MONTH);
                calYear = calendar.get(Calendar.YEAR);
            }
        } catch (Exception e) {
            e.printStackTrace();
            calDay = calendar.get(Calendar.DATE);
            calMonth = calendar.get(Calendar.MONTH);
            calYear = calendar.get(Calendar.YEAR);
        }
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        mMinutePicker.setEnabled(enabled);
        mHourPicker.setEnabled(enabled);
    }

    private static class SavedState extends BaseSavedState {

        private final int mHour;
        private final int mMinute;

        private SavedState(Parcelable superState, int hour, int minute) {
            super(superState);
            mHour = hour;
            mMinute = minute;
        }

        private SavedState(Parcel in) {
            super(in);
            mHour = in.readInt();
            mMinute = in.readInt();
        }

        public int getHour() {
            return mHour;
        }

        public int getMinute() {
            return mMinute;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            super.writeToParcel(dest, flags);
            dest.writeInt(mHour);
            dest.writeInt(mMinute);
        }

        public static final Creator<SavedState> CREATOR
                = new Creator<SavedState>() {
            public SavedState createFromParcel(Parcel in) {
                return new SavedState(in);
            }

            public SavedState[] newArray(int size) {
                return new SavedState[size];
            }
        };
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();
        return new SavedState(superState, mCurrentHour, mCurrentMinute);
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        SavedState ss = (SavedState) state;
        super.onRestoreInstanceState(ss.getSuperState());
        setCurrentHour(ss.getHour());
        setCurrentMinute(ss.getMinute());
    }

    public void setOnTimeChangedListener(OnTimeChangedListener onTimeChangedListener) {
        mOnTimeChangedListener = onTimeChangedListener;
    }


    public Integer getCurrentHour() {
        return mCurrentHour;
    }

    public String getAMPM() {
        String displayvalue[] = mAmpmnunberPicker.getDisplayedValues();
        if (displayvalue[mAmpmnunberPicker.getValue()].equalsIgnoreCase("صباحا")) {
            return "AM";
        } else if (displayvalue[mAmpmnunberPicker.getValue()].equalsIgnoreCase("بعد الظهر")) {
            return "PM";
        } else {
            return displayvalue[mAmpmnunberPicker.getValue()];
        }
    }

    public void setCurrentHour(Integer currentHour) {
        this.mCurrentHour = currentHour;
        updateHourDisplay();
    }

    public void setIs24HourView(Boolean is24HourView) {
        if (mIs24HourView != is24HourView) {
            mIs24HourView = is24HourView;
            configurePickerRanges();
            updateHourDisplay();
        }
    }

    public boolean is24HourView() {
        return mIs24HourView;
    }

    public Integer getCurrentMinute() {
        return mCurrentMinute;
    }

    public void setCurrentMinute(Integer currentMinute) {
        this.mCurrentMinute = currentMinute;
        updateMinuteDisplay();
    }



    public Integer getCurrentSeconds() {
        return mCurrentSeconds;
    }

    public void setCurrentSecond(Integer currentSecond) {
        this.mCurrentSeconds = currentSecond;
    }

    @Override
    public int getBaseline() {
        return mHourPicker.getBaseline();
    }

    private void updateHourDisplay() {
        int currentHour = mCurrentHour;
        if (!mIs24HourView) {
            if (currentHour > 12) currentHour -= 12;
            else if (currentHour == 0) currentHour = 12;
        }
        mHourPicker.setValue(currentHour);
        mIsAm = mCurrentHour < 12;
        onTimeChanged();
    }

    private void configurePickerRanges() {
        mHourPicker.setMinValue(1);
        mHourPicker.setMaxValue(12);
        mHourPicker.setFormatter(TWO_DIGIT_FORMATTER_HOUR);
    }

    private void onTimeChanged() {
        mOnTimeChangedListener.onTimeChanged(this, getCurrentHour(), getCurrentMinute(), getCurrentSeconds());
    }

    private void updateMinuteDisplay() {
        mMinutePicker.setValue(mCurrentMinute);
        mOnTimeChangedListener.onTimeChanged(this, getCurrentHour(), getCurrentMinute(), getCurrentSeconds());
    }

    private void updateSecondsDisplay() {
        mOnTimeChangedListener.onTimeChanged(this, getCurrentHour(), getCurrentMinute(), getCurrentSeconds());
    }

    public void updateDay(int date, int month, int year) {
        this.currentDay = date;
        this.currentMonth = month;
        this.currentYear = year;

        updateCalendar();

        if (calDay == currentDay && Ampm == Calendar.PM && arrtempHolder[mAmpmnunberPicker.getValue()].equals(amTempText)) {
            if (Ampm == Calendar.AM) {
                mAmpmnunberPicker.setValue(Calendar.AM);
            } else {
                mAmpmnunberPicker.setValue(Calendar.PM);
            }
        }

        if (calDay == currentDay && Ampm == Calendar.PM && arrtempHolder[mAmpmnunberPicker.getValue()].equals(pmTempText)) {
            if (mCurrentHour <= hour) {
                mCurrentHour = hour;
                mHourPicker.setValue(hour);

                if (mCurrentMinute < minute) {
                    mCurrentMinute = minute;
                    mMinutePicker.setValue(minute);
                }
            }
        } else if (calDay == currentDay && Ampm == Calendar.AM && arrtempHolder[mAmpmnunberPicker.getValue()].equals(amTempText)) {
            if (mCurrentHour <= hour) {
                mCurrentHour = hour;
                mHourPicker.setValue(hour);

                if (mCurrentMinute < minute) {
                    mCurrentMinute = minute;
                    mMinutePicker.setValue(minute);
                }
            }
        } else if (calDay == currentDay && Ampm == Calendar.PM && arrtempHolder[mAmpmnunberPicker.getValue()].equals(pmTempText)) {
            if (mCurrentHour <= hour) {
                mCurrentHour = hour;
                mHourPicker.setValue(hour);

                if (mCurrentMinute < minute) {
                    mCurrentMinute = minute;
                    mMinutePicker.setValue(minute);
                }
            }

        } else if (calDay == currentDay && Ampm == Calendar.PM && arrtempHolder[mAmpmnunberPicker.getValue()].equals(amTempText)) {
            if (mCurrentHour <= hour) {
                mCurrentHour = hour;
                mHourPicker.setValue(hour);

                if (mCurrentMinute < minute) {
                    mCurrentMinute = minute;
                    mMinutePicker.setValue(minute);
                }
            }
        }

    }
}
