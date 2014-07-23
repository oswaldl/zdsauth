package com.zy.zds.com.zy.zds.ipmp;

import com.zy.zds.IZdsRequest;
import com.zy.zds.auth.*;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import com.zy.zds.crud.CrudService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by oswaldl on 7/16/2014.
 */
public class UserRequestImpl implements IZdsRequest {
    @Autowired
    private CrudService crudService;
    @Autowired
    private UserResourceService userResourceService;

    @Override
    public void executeRequest(HttpServletRequest request,HttpServletResponse response) {

        String username=request.getParameter("username");
        String appId=request.getParameter("appId");
        String connId=request.getParameter("connId");

        //获取链接并验证，感觉验证有点多余
        String url=getConn(crudService.getUser(username),crudService.getApp(appId),connId);
        if(url==null){
            //在我看来不可能出现null的情况
        }

        //创建请求获取响应对象HttpEntity



        //解析HttpEntity对象返回给前端

    }

    //通过user和app获取指向这个app的url
    private String getConn(User user,Application app,String connName){
        Connection conn=crudService.getConn(connName,app);
        if(validateApp(app,conn)&&validateUser(user,conn)){
            return conn.getServiceUrl();
        }
        return null;
    }

    //验证connId和appId的关系
    private boolean validateApp(Application app,Connection conn){
        return crudService.validateApplicationUser(app,conn);
    }

    //验证connId和user的关系
    private boolean validateUser(User user,Connection conn){
        List<Application> applications=crudService.getAllApplicationsByUser(user);
        boolean mark=false;
        for (Application application:applications){
            if(crudService.validateApplicationUser(application,conn)){
                mark=true;
            }
        }
        return mark;
    }

    //创建连接并获取返回数据
    private HttpEntity doGet(String url){
        DefaultHttpClient client = new DefaultHttpClient();
        HttpGet get = new HttpGet(url);
        HttpEntity entity=null;
        try{
            HttpResponse response = client.execute(get);
            if(response.getStatusLine().getStatusCode() == 200){
                entity = response.getEntity();
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            // 关闭连接,释放资源
            try {
                client.clearResponseInterceptors();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return entity;
    }

    private HttpEntity doPost(String url,Map<String,String> params){
        DefaultHttpClient client = new DefaultHttpClient();
        HttpPost httppost = new HttpPost(url);
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        for (Map.Entry<String, String> entry : params.entrySet()) {
//				System.out.println("key = " + entry.getKey() + " and value = "
//						+ entry.getValue());
            formparams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }
        HttpEntity entity=null;
        UrlEncodedFormEntity uefEntity;
        try{
            uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");
            httppost.setEntity(uefEntity);
            HttpResponse response = client.execute(httppost);
            if(response.getStatusLine().getStatusCode() == 200){
                entity = response.getEntity();
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            // 关闭连接,释放资源
            try {
                client.clearResponseInterceptors();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return entity;
    }

    private HttpEntity doPut(String url){
        DefaultHttpClient client = new DefaultHttpClient();
        HttpPut get = new HttpPut(url);
        HttpEntity entity=null;
        try{
            HttpResponse response = client.execute(get);
            if(response.getStatusLine().getStatusCode() == 200){
                entity = response.getEntity();
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            // 关闭连接,释放资源
            try {
                client.clearResponseInterceptors();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return entity;
    }

    private HttpEntity doDelete(String url){
        DefaultHttpClient client = new DefaultHttpClient();
        HttpDelete get = new HttpDelete(url);
        HttpEntity entity=null;
        try{
            HttpResponse response = client.execute(get);
            if(response.getStatusLine().getStatusCode() == 200){
                entity = response.getEntity();
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            // 关闭连接,释放资源
            try {
                client.clearResponseInterceptors();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return entity;
    }

}
