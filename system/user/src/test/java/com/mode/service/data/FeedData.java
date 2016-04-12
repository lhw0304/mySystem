package com.mode.service.data;

import com.mode.base.Status;
import com.mode.entity.Feed;

/**
 * Created by Lei on 4/7/16.
 */
public class FeedData {

    public static Feed getFeed() {
        final long now = System.currentTimeMillis();

        Feed feed = new Feed();
        feed.setCtime(now);
        feed.setUtime(now);
        feed.setStatus(Status.NORMAL.getCode());
        feed.setAuthorId(12);
        feed.setContent("{}");
        feed.setDefaultImage("http://img.cde.whatsmode.com/images/1.jpg");
        feed.setDescription("This the fashion of the world.");
        feed.setTitle("Sale now!");
        feed.setType("article");
        feed.setSection("Fashion");
        feed.setComments(10);
        feed.setLikes(10);
        feed.setSaves(10);
        feed.setShares(10);

        return feed;
    }
}
