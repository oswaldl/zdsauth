package com.zy.zds.auth

import com.zy.zds.message.Message
import grails.converters.XML


class MainController {

    def springSecurityService

    def userResourceService


    def index() {
    }

    def user(Long id){
        userResourceService.afterLogin(request,response)
        render User.get(id) as XML
    }

    def getXML(){
        response.setHeader("Content-disposition", "attachment; filename=application.xml" )
        response.contentType = "application/xml"

        def out = response.outputStream
        InputStream inputStream = new FileInputStream("C:/Users/ACER/Desktop/周大生/applicationContext.xml")
        byte[] buffer = new byte[1024]
        int i = -1
        while ((i = inputStream.read(buffer)) != -1) {
            out.write(buffer, 0, i)
        }
        out.flush()
        out.close()
        inputStream.close()

    }
}
