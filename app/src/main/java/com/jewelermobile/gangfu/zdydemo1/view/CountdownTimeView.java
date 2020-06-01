package com.jewelermobile.gangfu.zdydemo1.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.widget.TextView;

import com.jewelermobile.gangfu.zdydemo1.OrderCountdownUtil;

@SuppressLint("AppCompatCustomView")
public class CountdownTimeView extends TextView{

    private OrderCountdownUtil orderCountdownUtil;

    public CountdownTimeView(Context context) {
        this(context,null);
    }

    public CountdownTimeView(Context context,AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CountdownTimeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    /**
     * @param time
     */
    public void setTime(String time){
        orderCountdownUtil = new OrderCountdownUtil(System.currentTimeMillis(), time);
        orderCountdownUtil.AddOrderCountdownListener(new OrderCountdownUtil.AddOrderCountdownListener() {

            @Override
            public void onCountdownCurrentTime(final OrderCountdownUtil.TimeBean timeBean) {
                String text = timeBean.getHour()+":"+timeBean.getMinute()+":"+timeBean.getSeconds();
                Message message=Message.obtain();
                message.obj = text ;
                handler.sendMessage(message) ;
            }

            @Override
            public void onCountdownError() {

            }

            @Override
            public void onComplete() {

            }
        }).start();
    }



    public void stopCountdown(){
        if (orderCountdownUtil!=null){
            orderCountdownUtil.stop();
        }
    }




    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            setText(msg.obj.toString());
        }
    };
}
