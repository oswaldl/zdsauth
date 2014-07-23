package com.zy.zds.auth

class DistributeController {
    def springSecurityService

    def index() {
        def user=springSecurityService.getCurrentUser()
        if(user){
            render "welcome ${user.username}"
        }else{
            render "welcome"
        }


    }

    def someRequest() {
        def user=springSecurityService.getCurrentUser()
        if(user){
            render "hello ${user.username}"
        }else{
            render "hello"
        }

    }
}
