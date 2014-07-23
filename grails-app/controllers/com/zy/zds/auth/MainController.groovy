package com.zy.zds.auth

import com.zy.zds.IZdsRequest
import com.zy.zds.RequestProxy
import com.zy.zds.com.zy.zds.ipmp.UserRequestImpl
import grails.converters.XML
import org.codehaus.groovy.grails.commons.GrailsApplication


class MainController {

    def springSecurityService

    def userResourceService

    GrailsApplication grailsApplication

    def index() {
        RequestProxy proxy =new RequestProxy();
        IZdsRequest userRequest = (IZdsRequest)proxy.bind(new UserRequestImpl())

        grailsApplication.mainContext.autowireCapableBeanFactory.autowireBean(userRequest)
        grailsApplication.mainContext.autowireCapableBeanFactory.autowireBean(proxy)
        userRequest.executeRequest(request,response)
    }

    def user(Long id){
        userResourceService.afterLogin(request,response)
        render User.get(id) as XML
    }
}
