package com.zy.zds.auth

import grails.transaction.Transactional

import static org.springframework.http.HttpStatus.*

@Transactional(readOnly = true)
class ApplicationUserController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

//    def index(Integer max) {
//        params.max = Math.min(max ?: 10, 100)
//        respond ApplicationUser.list(params), model: [applicationUserInstanceCount: ApplicationUser.count()]
//    }

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        def application=Application.get(params.applicationId)
        if(!application){
            application=Application.findAll().get(0)
        }
        def applicationUserInstanceList=ApplicationUser.findAllByApplication(application)
        [applicationUserInstanceList:applicationUserInstanceList,applicationUserInstanceCount: ApplicationUser.countByApplication(application,params),applicationId:application.id]
    }

    def show(ApplicationUser applicationUserInstance) {
        respond applicationUserInstance
    }

    def create() {
        respond new ApplicationUser(params)
    }

    @Transactional
    def save(ApplicationUser applicationUserInstance) {
        if (applicationUserInstance == null) {
            notFound()
            return
        }

        if (applicationUserInstance.hasErrors()) {
            respond applicationUserInstance.errors, view: 'create'
            return
        }

        applicationUserInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'applicationUser.label', default: 'ApplicationUser'), applicationUserInstance.id])
                redirect applicationUserInstance
            }
            '*' { respond applicationUserInstance, [status: CREATED] }
        }
    }

    def edit(ApplicationUser applicationUserInstance) {
        respond applicationUserInstance
    }

    @Transactional
    def update(ApplicationUser applicationUserInstance) {
        if (applicationUserInstance == null) {
            notFound()
            return
        }

        if (applicationUserInstance.hasErrors()) {
            respond applicationUserInstance.errors, view: 'edit'
            return
        }

        applicationUserInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'ApplicationUser.label', default: 'ApplicationUser'), applicationUserInstance.id])
                redirect applicationUserInstance
            }
            '*' { respond applicationUserInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(ApplicationUser applicationUserInstance) {

        if (applicationUserInstance == null) {
            notFound()
            return
        }

        applicationUserInstance.delete flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'ApplicationUser.label', default: 'ApplicationUser'), applicationUserInstance.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'applicationUser.label', default: 'ApplicationUser'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
