package com.zy.zds.auth


/**
 * application
 */
class Application {
    String applicationId

    boolean isSupportAnonymous

    static constraints = {
        applicationId blank: false, unique: true
    }
}
