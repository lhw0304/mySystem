package com.mode.service.data;

import com.mode.base.Status;
import com.mode.entity.Ad;

/**
 * Created by Lei on 4/8/16.
 */
public class AdData {

    public static Ad getAd() {
        final long now = System.currentTimeMillis();

        Ad ad = new Ad();
        ad.setStatus(Status.NORMAL.getCode());
        ad.setContent("");
        ad.setCountryCode("US");
        ad.setCtime(now);
        ad.setDefaultImage("http://img.cdn.whatsmode.com/images/1.jpg");
        ad.setDescription("30% Off");
        ad.setDisplayPage("startup");
        ad.setTitle("30% OFF");
        ad.setType("article");
        ad.setUtime(now);

        return ad;
    }
}
