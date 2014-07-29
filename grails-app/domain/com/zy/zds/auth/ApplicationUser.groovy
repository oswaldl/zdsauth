package com.zy.zds.auth

class ApplicationUser {
    User user
    Application application

    String deviceType
    String OSVersion
    int status=0
    Date registeredOn
    Date lastAccessedOn

    String customAttribute1
    String customAttribute2
    String customAttribute3
    String customAttribute4

    String appid
    String userid
    String channelid
    String requestid

    static constraints = {
        deviceType nullable:true
        OSVersion (nullable:true)
        registeredOn nullable:true
        lastAccessedOn nullable:true

        appid nullable:true
        userid nullable:true
        channelid nullable:true
        requestid nullable:true

        customAttribute1 nullable:true
        customAttribute2 nullable:true
        customAttribute3 nullable:true
        customAttribute4 nullable:true

    }
}
