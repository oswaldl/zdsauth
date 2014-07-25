package com.zy.zds.main

import com.zy.zds.auth.Application
import com.zy.zds.auth.ApplicationUser
import com.zy.zds.auth.Connection
import com.zy.zds.auth.User
import org.apache.http.HttpResponse
import org.apache.http.client.HttpClient
import org.apache.http.client.methods.HttpDelete
import org.apache.http.client.methods.HttpGet
import org.apache.http.client.methods.HttpPost
import org.apache.http.client.methods.HttpPut
import org.apache.http.entity.StringEntity
import org.apache.http.impl.client.DefaultHttpClient
import org.apache.http.util.EntityUtils
import org.codehaus.groovy.grails.web.util.WebUtils

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.ws.rs.DELETE
import javax.ws.rs.GET
import javax.ws.rs.POST
import javax.ws.rs.PUT
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.core.Response

import static org.grails.jaxrs.response.Responses.*

@Path('/api/request')
class SapResource {
    def springSecurityService

    //验证用户
    def checkUser(Application application,ApplicationUser applicationUser,Connection connection,HttpServletResponse response){
        //该用户无该app权限，返回401
        if(!applicationUser){
            response.sendError(401)
            return
        }
        //验证connection与application是否对应
        if(connection.application.id!=application.id){
            response.sendError(404)
            return
        }
    }

    @GET
    @Path('/{appID}/{connID}/{params}')
    Response getSap(
            @PathParam('appID') String appID,
            @PathParam('connID') String connID,
            @PathParam('params') String params) {
        Application application=Application.findByApplicationId(appID)
        Connection connection=Connection.findByConnName(connID)
        User user=springSecurityService.getCurrentUser()
        ApplicationUser applicationUser=ApplicationUser.findByApplicationAndUser(application,user)

        HttpServletRequest request=WebUtils.retrieveGrailsWebRequest().getCurrentRequest()
        HttpServletResponse response=WebUtils.retrieveGrailsWebRequest().getCurrentResponse()

        //验证用户信息
        checkUser(application,applicationUser,connection,response)

        //取头部数据用map保存
        Map<String,String> headers=new HashMap<String,String>()
        request.headerNames.each {
            headers.put(it,request.getHeader(it))
        }
        String string=doGet(connection.serviceUrl+params,headers)
        if(string==null){
            response.sendError(404)
        }
        ok string
    }

    //创建get方式连接并获取返回数据
    private String doGet(String url,Map<String,String> headers){
        HttpClient client = new DefaultHttpClient();
        HttpGet get = new HttpGet(url);
        println(url)
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            get.setHeader(entry.getKey(),entry.getValue())
        }
        get.setHeader("Content-Type","application/json;charset=UTF-8")
        String string=null;
        try{
            HttpResponse response = client.execute(get);
            if(response.getStatusLine().getStatusCode() == 200){
                string=EntityUtils.toString(response.getEntity(),"UTF-8");
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            // 关闭连接,释放资源
            try {
                get.abort();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return string;
    }

    @POST
    @Path('/{appID}/{connID}/{params}')
    Response postSap(
            @PathParam('appID') String appID,
            @PathParam('connID') String connID,
            @PathParam('params') String params ) {
        HttpServletRequest request=WebUtils.retrieveGrailsWebRequest().getCurrentRequest()
        HttpServletResponse response=WebUtils.retrieveGrailsWebRequest().getCurrentResponse()
        Application application=Application.findByApplicationId(appID)
        Connection connection=Connection.findByConnName(connID)
        User user=springSecurityService.getCurrentUser()
        ApplicationUser applicationUser=ApplicationUser.findByApplicationAndUser(application,user)
        //验证用户信息
        checkUser(application,applicationUser,connection,response)

        //取头部数据用map保存
        Map<String,String> headers=new HashMap<String,String>()
        request.headerNames.each {
            headers.put(it,request.getHeader(it))
        }
        headers.remove("content-length")
        headers.remove("host")
        String string=doPost(connection.serviceUrl,headers,request.JSON.toString())
        if(string==null){
            response.sendError(403)
        }
        ok string
    }

    //创建post方式连接并获取返回数据
    private String doPost(String url,Map<String,String> headers,String content){
        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);
        println(url)
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            post.setHeader(entry.getKey(),entry.getValue())
        }
        post.setHeader("Content-Type","application/json;charset=UTF-8")
        post.setEntity(new StringEntity(content))
        String string=null;
        try{
            HttpResponse response = client.execute(post);
            if(response.getStatusLine().getStatusCode() == 201){
                string=EntityUtils.toString(response.getEntity(),"UTF-8");
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            // 关闭连接,释放资源
            try {
                post.abort();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return string;
    }

    @PUT
    @Path('/{appID}/{connID}/{params}')
    Response putSap(
            @PathParam('appID') String appID,
            @PathParam('connID') String connID,
            @PathParam('params') String params ) {
        HttpServletRequest request=WebUtils.retrieveGrailsWebRequest().getCurrentRequest()
        HttpServletResponse response=WebUtils.retrieveGrailsWebRequest().getCurrentResponse()
        Application application=Application.findByApplicationId(appID)
        Connection connection=Connection.findByConnName(connID)
        User user=springSecurityService.getCurrentUser()
        ApplicationUser applicationUser=ApplicationUser.findByApplicationAndUser(application,user)
        //验证用户信息
        checkUser(application,applicationUser,connection,response)

        //取头部数据用map保存
        Map<String,String> headers=new HashMap<String,String>()
        request.headerNames.each {
            headers.put(it,request.getHeader(it))
        }
        headers.remove("content-length")
        headers.remove("host")
        String string=doPut(connection.serviceUrl+params,headers,request.JSON.toString())
        if(string==null){
            response.sendError(403)
        }
        ok string
    }

    //创建put方式连接并获取返回数据
    private String doPut(String url,Map<String,String> headers,String content){
        HttpClient client = new DefaultHttpClient();
        HttpPut put = new HttpPut(url);
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            put.setHeader(entry.getKey(),entry.getValue())
        }
        put.setHeader("Content-Type","application/json;charset=UTF-8")
        put.setEntity(new StringEntity(content))
        String string=null;
        try{
            HttpResponse response = client.execute(put);
            if(response.getStatusLine().getStatusCode() == 200){
                string=EntityUtils.toString(response.getEntity(),"UTF-8");
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            // 关闭连接,释放资源
            try {
                put.abort();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return string;
    }

    @DELETE
    @Path('/{appID}/{connID}/{params}')
    Response deleteSap(
            @PathParam('appID') String appID,
            @PathParam('connID') String connID,
            @PathParam('params') String params) {
        HttpServletRequest request=WebUtils.retrieveGrailsWebRequest().getCurrentRequest()
        HttpServletResponse response=WebUtils.retrieveGrailsWebRequest().getCurrentResponse()
        Application application=Application.findByApplicationId(appID)
        Connection connection=Connection.findByConnName(connID)
        User user=springSecurityService.getCurrentUser()
        ApplicationUser applicationUser=ApplicationUser.findByApplicationAndUser(application,user)
        //验证用户信息
        checkUser(application,applicationUser,connection,response)

        //取头部数据用map保存
        Map<String,String> headers=new HashMap<String,String>()
        request.headerNames.each {
            headers.put(it,request.getHeader(it))
        }
        headers.remove("content-length")
        headers.remove("host")
        int code=doDelete(connection.serviceUrl+params,headers)
        if(code==200){
            ok "ok"
        }else{
           response.sendError(404)
        }
    }

    //创建delete方式连接并获取返回数据
    private int doDelete(String url,Map<String,String> headers){
        HttpClient client = new DefaultHttpClient();
        HttpDelete delete = new HttpDelete(url);
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            delete.setHeader(entry.getKey(),entry.getValue())
        }
        delete.setHeader("Content-Type","application/json;charset=UTF-8")
        int code
        try{
            HttpResponse response = client.execute(delete);
            println(response.getStatusLine().getStatusCode())
            if(response.getStatusLine().getStatusCode() == 200){
                code=response.getStatusLine().getStatusCode();
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            // 关闭连接,释放资源
            try {
                delete.abort();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return code;
    }
}
