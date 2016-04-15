package com.mode.service;

import com.mode.config.Response;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * Created by Administrator on 2016/3/17.
 */
public interface MultiSelectService {

    public Response createMultiSelect(Integer userId, MultipartHttpServletRequest mRequest);

    public Response deleteMultiSelect(Integer id);

    public Response fetchMultiSelect(Integer id);

    public Response getMultiSelectList(Integer userId, Integer limit, Integer offset);

    public Response updateMultiSelect(Integer id, Integer userId, MultipartHttpServletRequest mRequest);
}
