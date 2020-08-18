package com.beibei.utils;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: 备备
 * @Date:2020/8/18
 * @Description:com.beibei.utils
 * @Version:1.0
 */
public class StrUtils {
    /**
     * 生成创建元表的sql语句
     */
    @Test
    public void  test(){
        String sql="create table meta_table ( id int(255) ";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(sql);
        for (int i = 0; i < 60; i++) {
            stringBuilder.append(",field"+i+"  varchar(255)");
        }
        stringBuilder.append(");");

        System.out.println(stringBuilder.toString());
    }

    /**
     * 生成pojo的属性值
     */
    @Test
    public void  test1(){
        List<String> fields = new ArrayList();
        for (int i = 0; i < 60; i++) {
            fields.add("private String field"+i+';' );
        }

        for (String field : fields) {
            System.out.println(field);
        }
    }

}
