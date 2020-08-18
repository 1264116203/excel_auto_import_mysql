package com.beibei.mapper;

import com.beibei.pojo.MetaTable;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Mapper
@Repository
public interface UserMapper {

    //  将文件的所有字段导入Sheet1表
    void autoImportField( String updatedFileAllName,String newTableName);

    // 查询Sheet1所有字段
    MetaTable queryAllField();

    //    通过查询的字段创建新的表
    void createNewTable(List<String> allFields);

    //删除所有数据
    void deleteAll(String tableName);

    //删除第一条数据
    void deleteFirst(String tableName);

    //删除最后一条数据
//    void deleteLast(String tableName);

    //查询数据行数
    int getCount(String tableName);
}