# HirjiGregorianCalendar
This Android Demo Example Shows  the  Hirji(Islamic) and Gregorian Calendar. Developer can Customize the Calendar by import the Calendar library in Project  and can use as MainActivity in example.


##Getting Started

you can  create it porgrammatically and can customize to fit according to your requirement without extra padding and fit to center according to your requirement etc...

#Gregorian
```
               MyCalendarView myCalendarView;
                myCalendarView = MyCalendarView.getInstance(MainActivity.this,true);

                myCalendarView.setOnDateSetListener(MainActivity.this);
                myCalendarView.setMinMaxHijriYear(1430, 1450);
                myCalendarView.setMinMaxGregorianYear(2013, 2020);
                myCalendarView.setMode(MyCalendarView.Mode.Gregorian);
                myCalendarView.setUILanguage(MyCalendarView.Language.English);
                myCalendarView.setEnableScrolling(false);

                myCalendarView.showDialog();
```

#Hirji
```
               myCalendarView = MyCalendarView.getInstance(MainActivity.this,true);

                myCalendarView.setOnDateSetListener(MainActivity.this);
                myCalendarView.setMinMaxHijriYear(1430, 1450);
                myCalendarView.setMinMaxGregorianYear(2013, 2020);
                myCalendarView.setMode(MyCalendarView.Mode.Hijri);
                myCalendarView.setUILanguage(MyCalendarView.Language.Arabic);
                myCalendarView.setEnableScrolling(false);
                myCalendarView.showDialog();
```


Please check the below screen shots 


<img src="https://user-images.githubusercontent.com/13448460/28629052-f148410e-7243-11e7-940f-cf42e578a7ca.png" data-canonical-src="https://user-images.githubusercontent.com/13448460/28629052-f148410e-7243-11e7-940f-cf42e578a7ca.png" width="320" height="600" />

<img src="https://user-images.githubusercontent.com/13448460/28629086-0335d552-7244-11e7-9ec0-0919b6dea491.png" data-canonical-src="https://user-images.githubusercontent.com/13448460/28629086-0335d552-7244-11e7-9ec0-0919b6dea491.png" width="320" height="600" />

<img src="https://user-images.githubusercontent.com/13448460/28629127-284f6cf4-7244-11e7-9d4a-cc2bf87ed2f0.png" data-canonical-src="https://user-images.githubusercontent.com/13448460/28629127-284f6cf4-7244-11e7-9d4a-cc2bf87ed2f0.png" width="320" height="600" />
