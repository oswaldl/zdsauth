package com.zy.zds;

import com.zy.zds.auth.UserResourceService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
/**
 * Created by oswaldl on 7/23/2014.
 */
public class RequestProxy implements InvocationHandler{
    private Object target;
    @Autowired
    private UserResourceService userResourceService;

    public Object bind(Object target) {
        this.target = target;
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //登录的验证、操作记录、相关状态信息记录
        userResourceService.afterLogin((HttpServletRequest)args[0], (HttpServletResponse)args[1]);

        Object result = method.invoke(target, args);
        System.out.println("You can do something here after process your business");
        return result;
    }
}
