package com.mode.service;

import javax.xml.ws.WebFault;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mode.base.Response;
import com.mode.entity.HttpResponseEntity;
import com.mode.entity.Item;

import org.hamcrest.CoreMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Lei on 3/21/16.
 */
@Service
public class ItemServiceTest extends BaseService {

    public void getItemService(HttpResponseEntity entity, Item item) throws
            Exception {

        /* Get item api result check */
        Response res = parseResponseBody(entity.getBody());
        // Check the status code
        successResponseStatusCodeCheck(entity.getStatus());
        successResponseMessageCheck(res.getCode(), res.getMessage());

        /* Get the result item */
        Item resultItem = om.convertValue(res.getPayload(), Item.class);

        /* Check the result item equal to the item */
        assertThat(resultItem.toString(), CoreMatchers.equalTo(item.toString()));
    }

    public static void listItemsService(HttpResponseEntity entity) throws
            Exception {
        /* Get item api result check */

        // Check the status code
        successResponseStatusCodeCheck(entity.getStatus());
    }
}
