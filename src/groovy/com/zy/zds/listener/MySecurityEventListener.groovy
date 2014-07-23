package com.zy.zds.listener

/**
 * Created by oswaldl on 7/19/2014.
 */
import org.springframework.context.ApplicationListener
import org.springframework.security.authentication.event. AuthenticationSuccessEvent
import org.codehaus.groovy.grails.commons.GrailsApplication
import org.springframework.beans.factory.annotation.Autowired;
import com.zy.zds.auth.User
import com.zy.zds.auth.UserResourceService

class MySecurityEventListener
        implements ApplicationListener<AuthenticationSuccessEvent> {
    @Autowired
    private UserResourceService userResourceService
    void onApplicationEvent(AuthenticationSuccessEvent event) {
        userResourceService.readAll().each{
            println "user: "+it
        }
        println "hello "+userResourceService
    }
}
