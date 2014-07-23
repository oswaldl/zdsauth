package com.zy.zds.auth

class AuthenticationToken {

    String username
    String token
    static constraints = {
    }

    def beforeInsert() {
        AuthenticationToken.findAllByUsername(username).each {
            it.delete(failOnError:true)
        }
    }

}
