package com.beibei.contorller;

import com.beibei.service.UploadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


/**
 * @author: 备备
 * @Date:2020/8/17
 * @Description:com.beibei.excel_auto_import_mysql.contorller.impl
 * @Version:1.0
 */
@Controller
@RequestMapping("/file")
public class MainController {
    //    注入service
    @Autowired
    private UploadFileService service;


    /**
     * 上传文件并导入到数据库
     *
     * @param file
     * @return
     * @throws IOException
     */
    @PostMapping("/fileUploadController")
    public String fileUploadAndImport(MultipartFile file) {
        String state = null;
        try {
//            文件上传
            service.fileUpload(file);
//            文件导入到元表
            service.importToMetaTable();
//            根据从元表查询的第一条数据获取的字段信息创建新表
            service.createNewTable();
//            再将文件导入到新建的表中
            service.importToNewTable();
//            删除新建表中第一行多余的数据
            service.deleteFirst();
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "ok1";
    }
}
