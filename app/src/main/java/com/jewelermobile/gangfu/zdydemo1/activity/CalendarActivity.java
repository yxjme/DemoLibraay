package com.jewelermobile.gangfu.zdydemo1.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.jewelermobile.gangfu.zdydemo1.R;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CalendarActivity extends AppCompatActivity {
    String TAG = getClass().getName();

    TextView showTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clendar);
        showTime = findViewById(R.id.showTime);
        calendar = Calendar.getInstance();
        initData();
    }



    public void up(View view){
        calendar.roll(Calendar.MONTH,true);
        showTime.setText(getDateFormat(calendar.getTime()));
        Log.v("=====roll======",getDateFormat(calendar.getTime()));
    }


    public void down(View view){
        calendar.roll(Calendar.YEAR,false);
        showTime.setText(getDateFormat(calendar.getTime()));
        Log.v("=====roll======",getDateFormat(calendar.getTime()));
    }





    /**获取日历*/
    private Calendar calendar;

    private void initData() {


        Date data = calendar.getTime();//当时时间 （包含年月日时分秒）
        calendar.getTimeInMillis();//当前毫秒（从1970-当前时间）
        calendar.getFirstDayOfWeek();

        int var0 = calendar.get(Calendar.DAY_OF_WEEK_IN_MONTH); //今天是本月的第几周
        int var1 = calendar.get(Calendar.DAY_OF_MONTH);//今天是本月的第几天
        int var2 = calendar.get(Calendar.DAY_OF_WEEK);//今天是本周的星期几 (从周日算起)
        int var3 = calendar.get(Calendar.DAY_OF_YEAR);//今天是本年的第几天
        int var4 = calendar.get(Calendar.YEAR); //当前年
        int var5 = calendar.get(Calendar.MONTH);//当前月
        int var6 = calendar.get(Calendar.DATE);//当前日
        int var7 = calendar.get(Calendar.WEEK_OF_MONTH);//当月的第几个星期
        int var8 = calendar.get(Calendar.WEEK_OF_YEAR);//当年的第几个星期
        int var9 = calendar.getActualMaximum(Calendar.DATE);//本月之后一天


        calendar.get(Calendar.SUNDAY);//周日
        calendar.get(Calendar.MONDAY);//周一
        calendar.get(Calendar.TUESDAY);//周二
        calendar.get(Calendar.WEDNESDAY);//周三
        calendar.get(Calendar.THURSDAY);//周四
        calendar.get(Calendar.FRIDAY);//周五
        calendar.get(Calendar.SATURDAY);//周六
        calendar.get(Calendar.JANUARY);//1月
        calendar.get(Calendar.FEBRUARY);//2月
        calendar.get(Calendar.MARCH);//3月
        calendar.get(Calendar.APRIL);//4月
        calendar.get(Calendar.MAY);//5月
        calendar.get(Calendar.JUNE);//6月
        calendar.get(Calendar.JULY);//7月
        calendar.get(Calendar.AUGUST);//8月
        calendar.get(Calendar.SEPTEMBER);//9月
        calendar.get(Calendar.OCTOBER);//10月
        calendar.get(Calendar.NOVEMBER);//11月
        calendar.get(Calendar.DECEMBER);//12月





        StringBuffer stringBuffer=new StringBuffer();
        stringBuffer.append("\n");
        stringBuffer.append("var0="+var0+"\n");
        stringBuffer.append("var1="+var1+"\n");
        stringBuffer.append("var2="+var2+"\n");
        stringBuffer.append("var3="+var3+"\n");
        stringBuffer.append("var4="+var4+"\n");
        stringBuffer.append("var5="+var5+"\n");
        stringBuffer.append("var6="+var6+"\n");
        stringBuffer.append("var7="+var7+"\n");
        stringBuffer.append("var8="+var8+"\n");
        stringBuffer.append("var9="+var9+"\n");
        stringBuffer.append("var10="+calendar.getMinimum(Calendar.DATE)+"\n");
        stringBuffer.append("calendar.getFirstDayOfWeek()="+calendar.getFirstDayOfWeek()+"\n");
        Log.v(TAG,"==stringBuffer="+stringBuffer);


        Calendar calendar=Calendar.getInstance();
//      calendar.set(Calendar.MONTH,Calendar.JANUARY);
//      calendar.set(2000,1,23);
//      calendar.set(Calendar.DAY_OF_MONTH,-1);
        calendar.add(Calendar.MONTH,-1);
        Log.v(TAG,"==calendar="+getDateFormat(calendar.getTime()));
        Log.v(TAG,"==calendar="+ Calendar.getInstance().after(calendar));
    }


    /**
     *
     * @param date
     * @return
     */
    public String getDateFormat(Date date){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return simpleDateFormat.format(date);
    }

}
