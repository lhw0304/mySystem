package com.mode.service.data;

import com.mode.base.Status;
import com.mode.entity.Task;

/**
 * Created by Lei on 4/8/16.
 */
public class TaskData {

    public static Task getTask() {

        final long now = System.currentTimeMillis();

        Task task = new Task();
        task.setCtime(now);
        task.setUtime(now);
        task.setAction("read");
        task.setCountryCode("US");
        task.setDescription("Read three articles.");
        task.setEndTime(now + (1000 * 60 * 60));
        task.setGroup("daily");
        task.setName("Read Articles!");
        task.setObjectType("article");
        task.setPriority(100);
        task.setRule("[\n" +
                "  {\n" +
                "    \"goal\": 3,\n" +
                "    \"credit\": 10,\n" +
                "    \"action\": \"view\",\n" +
                "    \"objectType\": \"article\",\n" +
                "    \"objectId\": 0,\n" +
                "    \"order\": 1,\n" +
                "    \"type\": \"R\"\n" +
                "  }\n" +
                "]");
        task.setStartTime(now - (1000 * 60 * 60));
        task.setStatus(Status.NORMAL.getCode());
        task.setTips("Read three articles and get 10 credits");
        task.setType("daily");

        return task;
    }
}
