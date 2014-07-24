//import com.zy.zds.auth.Requestmap

import com.zy.zds.auth.Application
import com.zy.zds.auth.ApplicationUser
import com.zy.zds.auth.Connection
import com.zy.zds.auth.Role
import com.zy.zds.auth.User
import com.zy.zds.auth.UserRole

class BootStrap {

    def init = { servletContext ->
        def user1 = User.findByUsername('mike') ?: new User(
                username: 'mike',
                chineseName: '迈克',
                password: 'mike',
                enabled: true).save(failOnError: true)
        def user2 = User.findByUsername('liuyx') ?: new User(
                username: 'liuyx',
                chineseName: 'liuyuxiong',
                password: 'liuyx',
                enabled: true).save(failOnError: true)
        def user3 = User.findByUsername('os') ?: new User(
                username: 'os',
                chineseName: 'oswaldl',
                password: 'os',
                enabled: true).save(failOnError: true)


        //角色
        def userRole = Role.findByAuthority('ROLE_USER')
        if (!userRole) {
            userRole = new Role(authority: 'ROLE_USER', name: 'UserRole' )
            userRole.save(failOnError: true)
        }

        def adminRole = Role.findByAuthority('ROLE_ADMIN')
        if (!adminRole) {
            adminRole = new Role(authority: 'ROLE_ADMIN', name: 'AdminRole')
            adminRole.save(failOnError: true)
        }

        if (!user1.authorities.contains(adminRole)) {
            UserRole.create user1, adminRole
        }
        if (!user2.authorities.contains(userRole)) {
            UserRole.create user2, userRole
        }
        if (!user3.authorities.contains(adminRole)) {
            UserRole.create user3, adminRole
        }

        Application application=new Application(applicationId:"StoreVisit",isSupportAnonymous:false);
        application.save(failOnError: true)
        Connection connection=new Connection(connName:"StoreVisit",serviceUrl:"http://zhongyitech.chinacloudapp.cn/zdsspd/api/store/",username:"",password:"",isSupportAnonymous:false,application:application)
        connection.save(failOnError: true)
        ApplicationUser applicationUser=new ApplicationUser(user:user3,application:application)
        applicationUser.save(failOnError: true)
    }
    def destroy = {
    }
}
