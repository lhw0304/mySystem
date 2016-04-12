package com.mode.util;

import java.util.Calendar;

/**
 * Created by Lei on 10/5/15.
 */
public class TimeHandler {

    /**
     * Get start time of a day
     *
     * @return
     */
    private static Long getStartTime(){
        Calendar todayStart = Calendar.getInstance();
        todayStart.set(Calendar.HOUR, 0);
        todayStart.set(Calendar.MINUTE, 0);
        todayStart.set(Calendar.SECOND, 0);
        todayStart.set(Calendar.MILLISECOND, 0);

        return todayStart.getTime().getTime();
    }

    /**
     * Get end time of a day
     *
     * @return
     */
    private static Long getEndTime(){
        Calendar todayEnd = Calendar.getInstance();
        todayEnd.set(Calendar.HOUR, 23);
        todayEnd.set(Calendar.MINUTE, 59);
        todayEnd.set(Calendar.SECOND, 59);
        todayEnd.set(Calendar.MILLISECOND, 999);
        return todayEnd.getTime().getTime();
    }

    /**
     * Check if the time is in today
     *
     * @param time
     * @return
     */
    public static boolean isInToday(Long time){
        if(time < getEndTime() && time > getStartTime()){
            return true;
        }
        return false;
    }
}
