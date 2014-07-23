package com.zy.zds.crud

import com.zy.zds.auth.Acl
import com.zy.zds.auth.App
import com.zy.zds.auth.Application
import com.zy.zds.auth.ApplicationUser
import com.zy.zds.auth.Connection
import com.zy.zds.auth.User
import com.zy.zds.auth.UserRole
import grails.transaction.Transactional

@Transactional
class CrudService {

    def serviceMethod() {


    }


    def User getUser(String username){
        User user=User.findByUsername(username)
        return user
    }

    def Application getApp(String appID){
        return Application.findByApplicationId(appID)
    }

    def Connection getConn(String connName,Application application){
        return Connection.findByConnNameAndApplication(connName,application)
    }

    def boolean hasAuthority(User user,Application app){
        def applicationUser=ApplicationUser.findAllByUserAndApplication(user,app)
        boolean mark=false
        if(applicationUser){
            mark=true
        }
        return mark
    }

    def List getAllApplicationsByUser(User user){
        return ApplicationUser.findAllByUser(user).collect {it.application}
    }

    def boolean validateApplicationUser(Application app,Connection conn){
        boolean mark=false;
        if(conn.application.id==app.id){
            mark=true;
        }
        return mark;
    }

}
