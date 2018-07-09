package com.vimbug.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonVo {
    private String name;
    private Integer age;
    private Date birth;//与po对象属性名不一致
    private float wallet;
    private String birthformat;//通过po对象的某一属性扩展
}
