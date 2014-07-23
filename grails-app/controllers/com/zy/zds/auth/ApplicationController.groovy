package com.zy.zds.auth

import grails.transaction.Transactional

import static org.springframework.http.HttpStatus.*

@Transactional(readOnly = true)
class ApplicationController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Application.list(params), model: [applicationInstanceCount: Application.count()]
    }

    def show(Application applicationInstance) {
        respond applicationInstance
    }

    def create() {
        respond new Application(params)
    }

    @Transactional
    def save(Application applicationInstance) {
        if (applicationInstance == null) {
            notFound()
            return
        }

        if (applicationInstance.hasErrors()) {
            respond applicationInstance.errors, view: 'create'
            return
        }

        applicationInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'application.label', default: 'Application'), applicationInstance.id])
                redirect applicationInstance
            }
            '*' { respond applicationInstance, [status: CREATED] }
        }
    }

    def edit(Application applicationInstance) {
        respond applicationInstance
    }

    @Transactional
    def update(Application applicationInstance) {
        if (applicationInstance == null) {
            notFound()
            return
        }

        if (applicationInstance.hasErrors()) {
            respond applicationInstance.errors, view: 'edit'
            return
        }

        applicationInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Application.label', default: 'Application'), applicationInstance.id])
                redirect applicationInstance
            }
            '*' { respond applicationInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Application applicationInstance) {

        if (applicationInstance == null) {
            notFound()
            return
        }

        applicationInstance.delete flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Application.label', default: 'Application'), applicationInstance.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'application.label', default: 'Application'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
