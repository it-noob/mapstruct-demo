package com.vimbug.translation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderInfoVo {

    private String orderNo;

    private String paystate;

    private String amount;

    private Date createTime;

    private String paystateDesc;

    private String createTimeFormat;

}
