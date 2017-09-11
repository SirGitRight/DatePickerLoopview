package com.tiny.datapickerloopview.view.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.tiny.datapickerloopview.R;
import com.tiny.datapickerloopview.view.LoopListener;
import com.tiny.datapickerloopview.view.LoopView;

import java.net.DatagramPacket;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by tiny on 16/3/2.
 */
public class DateTimePickerDialog  extends DatePickerDialog{

    public LoopView hourLoopView;
    public LoopView minuteLoopView;


    List<String> hourList;
    List<String> minuteList;

    int hourPos;
    int minutePos;


    public DateTimePickerDialog(Builder builder) {
        super(builder);
    }



    protected void initView()
    {
        super.initView();
        hourLoopView = (LoopView) contentView.findViewById(R.id.picker_hour);
        minuteLoopView = (LoopView) contentView.findViewById(R.id.picker_minute);

        hourLoopView.setVisibility(View.VISIBLE);
        minuteLoopView.setVisibility(View.VISIBLE);
        hourLoopView.setNotLoop();
        minuteLoopView.setNotLoop();
        hourLoopView.setTextSize(viewTextSize);
        minuteLoopView.setTextSize(viewTextSize);
        int loopViewHeight = yearLoopView.getMaxTextHeight();
        LinearLayout.LayoutParams paramsHour = (LinearLayout.LayoutParams) hourLoopView.getLayoutParams();
        paramsHour.height = loopViewHeight * 8;
        hourLoopView.setLayoutParams(paramsHour);
        hourLoopView.setPadding(0, dip2px(mContext, 7), 0, dip2px(mContext, 5));


        LinearLayout.LayoutParams paramsMinute = (LinearLayout.LayoutParams) minuteLoopView.getLayoutParams();
        paramsMinute.height = loopViewHeight * 8;
        minuteLoopView.setLayoutParams(paramsMinute);
        minuteLoopView.setPadding(0, dip2px(mContext, 7), 0, dip2px(mContext, 5));
        hourPos=minMinute;
        minutePos=minMinute;

        hourLoopView.setListener(new LoopListener() {
            @Override
            public void onItemSelect(int item) {
                hourPos = item;
            }

            @Override
            public void onDown(boolean down) {
                buEnabled(down);
            }
        });
        minuteLoopView.setListener(new LoopListener() {
            @Override
            public void onItemSelect(int item) {
                minutePos = item;
            }

            @Override
            public void onDown(boolean down) {
                buEnabled(down);
            }
        });

        initYearPickerHours();
        initYearPickerMinute();
    }

    private void initYearPickerHours() {
        hourList = new ArrayList<>();
        for (int i = 0; i <24; i++) {
            if (i<10)
                hourList.add("0"+i + "时");
            else
            hourList.add(i + "时");
        }

        hourLoopView.setArrayList((ArrayList) hourList);
        hourLoopView.setInitPosition(0);

    }

    private void initYearPickerMinute() {
        minuteList = new ArrayList<>();
        for (int i = 0; i <60; i++) {
            if (i<10)
                minuteList.add("0"+i + "分");
            else
            minuteList.add(i + "分");
        }

        minuteLoopView.setArrayList((ArrayList) minuteList);
        minuteLoopView.setInitPosition(0);

    }



    @Override
    public void onClick(View v) {

        if (v == cancelBtn) {
            dismissDialog();
        } else if (v == confirmBtn) {
            if (null != mListener) {


                //get current date
                int year;
                int month;
                int day;
                year = minYear + yearPos;
                if (mode == DatePickerMode.DAY) {
                    if (0 == yearPos) {
                        month = monthPos + minMonth;
                        if (0 == monthPos) {
                            day = dayPos + minDay;
                        } else {
                            day = dayPos + 1;
                        }
                    } else {
                        month = monthPos + 1;
                        day = dayPos + 1;
                    }
                } else {
                    month = monthPos + 1;
                    day = dayPos + 1;
                }

                mListener.onDateTimePickCompleted(year, month, day, hourPos,minutePos);
            }
            dismissDialog();
        }
    }

}
