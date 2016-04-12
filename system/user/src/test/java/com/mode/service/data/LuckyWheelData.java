package com.mode.service.data;

import com.mode.base.Status;
import com.mode.entity.LuckyWheel;

/**
 * Created by Lei on 4/8/16.
 */
public class LuckyWheelData {

    public static LuckyWheel getLuckyWheel() {
        final long now = System.currentTimeMillis();

        LuckyWheel luckyWheel = new LuckyWheel();
        luckyWheel.setUtime(now);
        luckyWheel.setCtime(now);
        luckyWheel.setBase(100);
        luckyWheel.setCountryCode("US");
        luckyWheel.setEndRange(1000);
        luckyWheel.setLevel(1);
        luckyWheel.setPrizeDetail("{\n" +
                "  \"type\": \"item\",\n" +
                "  \"title\": \"Top Show\",\n" +
                "  \"description\": \"Fashion Week\",\n" +
                "  \"image\": \"http://img.cdn.whatsmode" +
                ".com/images/redeems/c3a31a8ada141c0b3836964cd9c4278f.jpg\",\n" +
                "  \"price\": \"2000\",\n" +
                "  \"subImage\": \"http://img.cdn.whatsmode" +
                ".com/images/redeems/c3a31a8ada141c0b3836964cd9c4278f.jpg\",\n" +
                "  \"credit\": \"100000\"\n" +
                "} ");
        luckyWheel.setProbability(50);
        luckyWheel.setStartRange(100);
        luckyWheel.setStatus(Status.NORMAL.getCode());

        return luckyWheel;
    }
}
