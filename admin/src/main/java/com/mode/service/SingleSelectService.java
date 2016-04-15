package com.mode.service;

import com.mode.config.Response;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * Created by Administrator on 2016/3/7.
 */
public interface SingleSelectService {

    public Response createSingleSelect(Integer userId, MultipartHttpServletRequest mRequest);

    public Response fetchSingleSelect(Integer id);

    public Response deleteSingleSelect(Integer id);

    public Response getSingleSelectList(Integer userId, Integer limit, Integer offset);

    public Response updateSingleSelect(Integer id, Integer userId, MultipartHttpServletRequest mRequest);

}
