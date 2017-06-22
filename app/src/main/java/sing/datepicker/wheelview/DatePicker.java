package sing.datepicker.wheelview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import sing.datepicker_wheelview.R;

/**
 * 日期滚轮选择布局
 */
public class DatePicker extends LinearLayout implements WheelView.OnSelectListener {

    public DatePicker(Context context) {
        this(context, null);
    }

    public DatePicker(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private WheelView mWheelYear;
    private WheelView mWheelMonth;
    private WheelView mWheelDay;

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        LayoutInflater.from(getContext()).inflate(R.layout.date_picker, this);

        mWheelYear = (WheelView) findViewById(R.id.year);
        mWheelMonth = (WheelView) findViewById(R.id.month);
        mWheelDay = (WheelView) findViewById(R.id.day);

        // 格式化当前时间，并转换为年月日整型数据
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String[] split = sdf.format(new Date()).split("-");
        int currentYear = Integer.parseInt(split[0]);
        int currentMonth = Integer.parseInt(split[1]);
        int currentDay = Integer.parseInt(split[2]);

        // 设置默认年月日为当前日期
        mWheelYear.setData(getYearData(currentYear));
        mWheelYear.setDefault(0);
        mWheelMonth.setData(getMonthData());
        mWheelMonth.setDefault(currentMonth - 1);
        mWheelDay.setData(getDayData(getLastDay(currentYear, currentMonth)));
        mWheelDay.setDefault(currentDay - 1);

        mWheelYear.setOnSelectListener(this);
        mWheelMonth.setOnSelectListener(this);
        mWheelDay.setOnSelectListener(this);
    }

    // 年范围在：1900~今年
    private ArrayList<String> getYearData(int currentYear) {
        ArrayList<String> list = new ArrayList<>();
        for (int i = currentYear; i >= 1900; i--) {
            list.add(String.valueOf(i));
        }
        return list;
    }

    private ArrayList<String> getMonthData() {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            list.add(i<10?("0"+i):(""+i));
        }
        return list;
    }

    // 日范围在1~lastDay
    private ArrayList<String> getDayData(int lastDay) {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 1; i <= lastDay; i++) {
            list.add(i<10?("0"+i):(""+i));
        }
        return list;
    }

    // 判断是否闰年
    private boolean isLeapYear(int year) {
        return (year % 100 == 0 && year % 400 == 0) || (year % 100 != 0 && year % 4 == 0);
    }

    // 获取特定年月对应的天数
    private int getLastDay(int year, int month) {
        if (month == 2) {
            // 2月闰年的话返回29，防止28
            return isLeapYear(year) ? 29 : 28;
        }
        // 一三五七八十腊，三十一天永不差
        return month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12 ? 31 : 30;
    }

    @Override
    public void endSelect(View view, int id, String text) {
        switch (view.getId()) {
            case R.id.year:
            case R.id.month:
                // 记录当前选择的天数
                int selectDay = Integer.parseInt(getDay());
                // 根据当前选择的年月获取对应的天数
                int lastDay = getLastDay(Integer.parseInt(getYear()), Integer.parseInt(getMonth()));
                // 设置天数
                mWheelDay.setData(getDayData(lastDay));
                // 如果选中的天数大于实际天数，那么将默认天数设为实际天数;否则还是设置默认天数为选中的天数
                if (selectDay > lastDay) {
                    mWheelDay.setDefault(lastDay - 1);
                } else {
                    mWheelDay.setDefault(selectDay - 1);
                }
                break;
        }
    }

    @Override
    public void selecting(View view, int id, String text) {
    }

    public interface OnSelectDate {
        void result(String year, String month, String day);
    }

    // 获取选择的年
    public String getYear() {
        return mWheelYear.getSelectedText();
    }

    // 获取选择的月
    public String getMonth() {
        return mWheelMonth.getSelectedText();
    }

    // 获取选择的日
    public String getDay() {
        return mWheelDay.getSelectedText();
    }
}