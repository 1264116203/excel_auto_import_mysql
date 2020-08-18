package com.beibei.service;

import com.beibei.pojo.MetaTable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @author: 备备
 * @Date:2020/8/17
 * @Description:com.beibei.excel_auto_import_mysql.service
 * @Version:1.0
 */
public interface UploadFileService {
    void fileUpload(MultipartFile file) throws IOException;

    void importToMetaTable();

    void importToNewTable();

    List<String> queryAllField();

    void createNewTable();

    //删除第一条数据
    void deleteFirst();
}
