package com.mode.service.data;

import com.mode.base.Status;
import com.mode.entity.Item;

/**
 * Created by Lei on 4/8/16.
 */
public class ItemData {

    public static Item getItem() {

        final long now = System.currentTimeMillis();

        Item item = new Item();
        item.setItemName("mode test item name");
        item.setType(1);
        item.setBrandId(0);
        item.setDefaultImage("mode test default image");
        item.setExtraImage("mode test extra image");
        item.setDescription("Mode test item description.");
        item.setPrice(5.0f);
        item.setOnSale(1);
        item.setSaleTime(now + 1000 * 60 * 60);
        item.setSalePrice(2.0f);
        item.setSalePercent(60);
        item.setSku("573902");
        item.setSize("S,M,L");
        item.setColor("ffffff, ffff11");
        item.setProductLink("mode test product link");
        item.setStatus(Status.NORMAL.getCode());
        item.setViews(1);
        item.setSaves(1);
        item.setShares(1);
        item.setCtime(now);
        item.setUtime(now);

        return item;
    }
}
