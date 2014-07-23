package com.zy.zds.auth

class Acl {
    def securityService

    Role role

    App app

    Date dateCreated
    String createdBy
    def beforeInsert = {
        createdBy = securityService?.currentAuthenticatedUsername()
    }

    static constraints = {
    }
}
