package com.zy.zds.main

import com.zy.zds.auth.Application
import com.zy.zds.auth.ApplicationUser
import com.zy.zds.auth.Connection
import com.zy.zds.auth.User
import org.apache.http.HttpEntity
import org.apache.http.HttpResponse
import org.apache.http.client.HttpClient
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.DefaultHttpClient
import org.apache.http.util.EntityUtils
import org.codehaus.groovy.grails.web.util.WebUtils

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.ws.rs.GET
import javax.ws.rs.POST
import javax.ws.rs.PUT
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.QueryParam
import javax.ws.rs.core.Response

import static org.grails.jaxrs.response.Responses.*

@Path('/api/request')
class SapResource {
    def springSecurityService

    @GET
    Response sapRepresentation(
            @QueryParam('username') String username,
            @QueryParam('appID') String appID,
            @QueryParam('connID') String connID) {
        Application application=Application.findByApplicationId(appID)
        User user=User.findByUsername(username)
        Connection connection=Connection.findByConnName(connID)

        ApplicationUser applicationUser=ApplicationUser.findByApplicationAndUser(application,user)
        User currentuser=springSecurityService.getCurrentUser()

        HttpServletRequest request=WebUtils.retrieveGrailsWebRequest().getCurrentRequest()
        HttpServletResponse response=WebUtils.retrieveGrailsWebRequest().getCurrentResponse()

        //验证是否为本人操作
        if(currentuser.username!=username){
            response.sendError(403)
            return
        }
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
        Map<String,String> headers=new HashMap<String,String>()
        request.headerNames.each {
            headers.put(it,request.getHeader(it))
        }
        String string=doGet(connection.serviceUrl,headers)
        ok string
    }

    //创建连接并获取返回数据
    private String doGet(String url,Map<String,String> headers){
        HttpClient client = new DefaultHttpClient();
        HttpGet get = new HttpGet(url);
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
}
