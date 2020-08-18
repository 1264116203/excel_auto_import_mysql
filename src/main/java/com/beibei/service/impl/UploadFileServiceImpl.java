package com.beibei.service.impl;

import com.beibei.mapper.UserMapper;
import com.beibei.pojo.MetaTable;
import com.beibei.service.UploadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: 备备
 * @Date:2020/8/17
 * @Description:com.beibei.excel_auto_import_mysql.service
 * @Version:1.0
 */
@Service
public class UploadFileServiceImpl implements UploadFileService {

    //    上传文件的保存路径
    private String path = "/Users/beibei/Documents/upload/";
    //    上传文件后新的文件名
    private String updatedFileName;
    private String updatedFileAllName;
    private String allPath;
    @Autowired
    private UserMapper mapper;

    /**
     * 上传文件
     *
     * @param file
     * @return
     * @throws IOException
     */
    @Override
    public void fileUpload(MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
        String[] split = originalFilename.split("\\.");
        updatedFileName = split[0] + System.currentTimeMillis();
        updatedFileAllName = split[0] + System.currentTimeMillis() + "." + split[1];
        allPath = path + updatedFileAllName;
        file.transferTo(new File(allPath));
    }


    /**
     * 将所有字段导入到meta_table元表中
     */
    @Override
    public void importToMetaTable() {
        mapper.deleteAll("meta_table");
        mapper.autoImportField(updatedFileAllName,"meta_table");
    }

    /**
     * 将所有字段导入到新创建的表中
     */
    @Override
    public void importToNewTable() {
        mapper.autoImportField(updatedFileAllName,updatedFileName);
    }
    /**
     * 查询Sheet1所有字段并封装到List
     *
     * @return
     */
    @Override
    public List<String> queryAllField() {
//        使用arraylist接受数据
        List<String> fields = new ArrayList<>();
//        获取查询元表的首行作为新表的字段
        MetaTable metaTable = mapper.queryAllField();
//        将MetaTable对象转换为字符串并以逗号分割
        String[] allField = metaTable.toString().split(",");
//        遍历转换后的数组
        for (String s : allField) {
            String s1 = s.split("=")[1].trim()
                    .replaceAll("\\(", "（")
                    .replaceAll("\\)", "）")
                    .replaceAll("\\n", "")
                    .replaceAll(" ", "_")
                    .replaceAll("null", "")
                    .replaceAll("-", "_");
            fields.add(s1);
        }
        fields.add(0,updatedFileName);
        return fields;
    }

    /**
     * 通过查询的字段创建新的表
     */
    @Override
    public void createNewTable() {
        List<String> allField = this.queryAllField();
        //删除 最后一个 ） 元素
        allField.remove(allField.size()-1);
        mapper.createNewTable(allField);
    }

    @Override
    public void deleteFirst() {
        mapper.deleteFirst(updatedFileName);
    }

}
