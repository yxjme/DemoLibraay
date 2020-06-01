package com.jewelermobile.gangfu.zdydemo1;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class OrderCountdownUtil {

    /**测试时间*/
    public static String time = "2019-7-26 14:30:12";
    public static String time1 = "2019-7-26 15:13:28";
    public static String time2 = "2019-7-26 14:24:31";
    /**四个小时*/
    private long countdown = 4 * 60 * 60 ;
    /**订单创建时间*/
    private String createTime ;
    /**当前时间*/
    private long currentTime ;


    /**
     * @param currentTime
     * @param createTime
     */
    public OrderCountdownUtil(long currentTime,String createTime){
        this.currentTime = currentTime ;
        this.createTime = createTime ;
    }



    /**
     * 获取倒计时的时间
     *
     * 单位秒
     */
    private long getCountdownTime(){
        long time=getOffsetTime();
        return countdown>= time? countdown - time : 0 ;
    }



    /**
     * 获取下单时间和当时间差额
     *
     * 单位 秒
     *
     * @return
     */
    private long getOffsetTime() {
        long offsetTime = 0;
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            /*下单时间  单位毫秒*/
            Date inputTime = simpleDateFormat.parse(createTime);
            long createOrderTime = inputTime.getTime();
            /*当前时间 单位毫秒*/
            offsetTime = (currentTime - createOrderTime) / 1000;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return offsetTime;
    }



    /**
     * 时分秒
     *
     * @return
     *
     */
    private TimeBean getTime(long totalSeconds){
        int hour = (int) (totalSeconds / 3600);
        int s1 = (int) (totalSeconds % 3600);
        int minutes = s1/60 ;
        int s2 = s1 % 60 ;
        int seconds = s2;


        return  new TimeBean(hour,minutes,seconds) ;
    }




    private TimerTask timerTask;
    private Timer timer;




    /**
     * 开始计时
     */
    public void start(){
        timer=new Timer();
        timer.schedule(timerTask = new MyTimerTask(),0,1000);
    }



    /**
     * 停止计时
     */
    public void stop(){
        if (timer!=null){
            timer.cancel();
            timer=null;
        }
        if (timerTask!=null){
            timerTask.cancel();
            timerTask=null;
        }
        if (addOrderCountdownListener!=null){
            addOrderCountdownListener.onComplete();
        }
    }



    public class MyTimerTask extends TimerTask{

        private long a_var;

        public MyTimerTask(){
            a_var = getCountdownTime();
        }


        @Override
        public void run() {
            if (a_var>0){
                a_var -- ;
                if (addOrderCountdownListener!=null){
                    addOrderCountdownListener.onCountdownCurrentTime(getTime(a_var));
                }
            }else {
                //倒计时完成
                if (addOrderCountdownListener!=null){
                    addOrderCountdownListener.onComplete();
                }
                stop();
            }
        }
    }





    private AddOrderCountdownListener addOrderCountdownListener;
    public interface AddOrderCountdownListener{
        void onCountdownCurrentTime(TimeBean timeBean);
        void onCountdownError();
        void onComplete();
    }

    public OrderCountdownUtil AddOrderCountdownListener(AddOrderCountdownListener addOrderCountdownListener) {
        this.addOrderCountdownListener = addOrderCountdownListener;
        return this;
    }





    public class TimeBean{
        int hour;
        int minute;
        int seconds;

        public TimeBean(){}
        public TimeBean( int hour, int minute, int seconds) {
            this.hour = hour;
            this.minute = minute;
            this.seconds = seconds;
        }

        public int getHour() {
            return hour;
        }

        public void setHour(int hour) {
            this.hour = hour;
        }

        public int getMinute() {
            return minute;
        }

        public void setMinute(int minute) {
            this.minute = minute;
        }

        public int getSeconds() {
            return seconds;
        }

        public void setSeconds(int seconds) {
            this.seconds = seconds;
        }
    }
}
