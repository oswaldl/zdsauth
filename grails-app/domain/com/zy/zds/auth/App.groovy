package com.zy.zds.auth


class App {
    def securityService

    //Connection connection

    String mobileApp

    String name

    String state

    int parentid

    String mobileActivity

    String isAnonymous

    String description

    Date dateCreated
    Date lastUpdated
    String lastUpdatedBy
    String createdBy

    def beforeInsert = {
        createdBy = securityService?.currentAuthenticatedUsername()
    }
    def beforeUpdate() {
        lastUpdatedBy = securityService?.currentAuthenticatedUsername()
    }



    static constraints = {
    }
}
