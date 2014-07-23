package com.zy.zds.auth

/**
 * 链接类
 */
class Connection implements Serializable{

    String connName

    String serviceUrl

    String username

    String password

    boolean isSupportAnonymous

//    String requestMethod
//
//    String isAnonymous
//
//    String methodName
//
//    String parameters
//
//    String description

    static belongsTo = [application: Application]

    static constraints = {
        username nullable:true
        password nullable:true
    }

    static mapping = {
        id composite: ['connName', 'application']
    }
}
