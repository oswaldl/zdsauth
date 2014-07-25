package com.zy.zds.auth

import org.codehaus.groovy.grails.web.util.WebUtils

import javax.ws.rs.PUT
import javax.ws.rs.QueryParam

import static org.grails.jaxrs.response.Responses.*

import javax.ws.rs.Consumes
import javax.ws.rs.GET
import javax.ws.rs.Produces
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.POST
import javax.ws.rs.core.Response

@Path('/api/user')
@Consumes(['application/xml','application/json'])
@Produces(['application/xml','application/json'])
class UserCollectionResource {

    def springSecurityService
    def userResourceService

    @POST
    Response create(User dto) {
        created userResourceService.create(dto)
    }

    @GET
    Response readAll() {
        ok userResourceService.readAll()
    }

    @Path('/{id}')
    UserResource getResource(@PathParam('id') Long id) {
        new UserResource(userResourceService: userResourceService, id:id)
    }

    /**
     *
     * @param appID
     * @param username
     * @param appUser
     * @return
     */
    @PUT
    @Path('/{appID}/user')
    Response requestUser(
            @PathParam('appID') String appID,
            @QueryParam('username') String username,
            ApplicationUser appUser){
        Application application=Application.findByApplicationId(appID)
        User user=User.findByUsername(username)
        ApplicationUser applicationUser=ApplicationUser.findByApplicationAndUser(application,user)
        User currentuser=springSecurityService.getCurrentUser()
        //非本人操作，返回403
        if(currentuser.username!=username){
            WebUtils.retrieveGrailsWebRequest().getCurrentResponse().sendError(403)
            return
        }
        //该用户无该app权限，返回401request/StoreVisit/StoreVisit/1
        if(!applicationUser){
            WebUtils.retrieveGrailsWebRequest().getCurrentResponse().sendError(401)
            return
        }
        //更新用户APP相关信息
        if(applicationUser.status==0){
            //未注册的情况
            applicationUser.status=1
            applicationUser.registeredOn=new Date()
        }
        applicationUser.lastAccessedOn=new Date()
        if(appUser.deviceType){
            applicationUser.deviceType=appUser.deviceType
        }
        if(appUser.OSVersion){
            applicationUser.OSVersion=appUser.OSVersion
        }
        ok applicationUser.save(failOnError: true)
    }
}
