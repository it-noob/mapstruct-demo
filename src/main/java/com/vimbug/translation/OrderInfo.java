package com.vimbug.translation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderInfo {

    private String orderNo;

    private String paystate;

    private BigDecimal amount;

    private Date createTime;
}
