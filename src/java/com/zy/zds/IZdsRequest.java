package com.zy.zds;

import com.zy.zds.auth.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by oswaldl on 7/16/2014.
 */
public interface IZdsRequest {
    void executeRequest(HttpServletRequest request,HttpServletResponse response);
}
