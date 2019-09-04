package com.example.tkmybatis.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import javax.xml.bind.annotation.XmlEnumValue;
import java.util.Date;

@Data
public class User2 {

    private String sex;

    @JSONField(format = "yyyy-MM-dd")
    private String date1;

    @JSONField(format = "yyyy-MM-dd")
    private Date date2;
}
