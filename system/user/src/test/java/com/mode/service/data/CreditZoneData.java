package com.mode.service.data;

import com.mode.base.Status;
import com.mode.entity.CreditZone;

/**
 * Created by Lei on 4/8/16.
 */
public class CreditZoneData {

    public static CreditZone getCreditZone() {

        final long now = System.currentTimeMillis();

        CreditZone creditZone = new CreditZone();
        creditZone.setCtime(now);
        creditZone.setUtime(now);
        creditZone.setCountryCode("US");
        creditZone.setPrizeDetail("{\n" +
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
        creditZone.setSortOrder(100);
        creditZone.setStatus(Status.NORMAL.getCode());

        return creditZone;
    }
}
