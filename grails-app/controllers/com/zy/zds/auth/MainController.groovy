package com.zy.zds.auth

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import grails.converters.XML
import org.apache.commons.fileupload.FileItem
import org.apache.commons.fileupload.disk.DiskFileItemFactory
import org.apache.commons.fileupload.servlet.ServletFileUpload


class MainController {

    def springSecurityService

    def userResourceService


    def index() {
        String taskNumber="001"
        def content = g.include(controller:'main', action:'storeVisitResult',params:[taskNumber:taskNumber])
        def contentStr="<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />"+content.readAsString()
        byte[] b1 = contentStr.getBytes("utf-8")
        File file=new File("C:/"+taskNumber+".html")
        FileOutputStream out = new FileOutputStream(file, false)
        out<<b1
        out.flush()
        out.close()
    }

    def storeVisitResult(){
        String jsonStr=StoreVisitResult.findByTaskNumber(params.taskNumber).jsonStr
        Gson son = new Gson();
        List<List> tasks = son.fromJson(jsonStr, new TypeToken<List<List>>(){}.getType());
        [tasks:tasks]
    }

    /**
     * 上传文件
     * @return
     */
    def uploadFile(){
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        if(isMultipart){
            String realPath = this.getServletContext().getRealPath("upload");
            File dir = new File(realPath);
            if(!dir.exists()){
                dir.mkdir();
            }
            DiskFileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setHeaderEncoding("utf-8");
            try {
                List<FileItem> items = upload.parseRequest(request);
                for(FileItem item : items){
                    if(item.isFormField()){ //username="username"
                        String name = item.getFieldName();
                        String value = item.getString("utf-8");

                        System.out.println(name + " = " + value);
                    } else { //文件
                        String name = item.getName();
                        item.write(new File(dir, System.currentTimeMillis() + name.substring(name.lastIndexOf("."))));

                    }

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
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
