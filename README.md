# HirjiGregorianCalendar
This Android Demo Example Shows  the  Hirji(Islamic) and Gregorian Calendar. Developer can use the Calendar by import the Calendar library in Project.


##Getting Started

you can  create it porgrammatically according to your requirement etc...

##Gregorian
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

##Hirji
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



## License
[MIT](https://github.com/ghanshyam34/HirjiGregorianCalendar/blob/master/LICENSE)

MIT License

Copyright (c) 2019 Ghanshyamp

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

