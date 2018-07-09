package com.vimbug;

import com.vimbug.translation.OrderConvert;
import com.vimbug.translation.OrderInfo;
import com.vimbug.translation.OrderInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class TranslateTest {

    public static final Map<String, Map<String, String>> CACHE = new HashMap<>(32);


    static {
        Map<String, String> paystate = new HashMap<>();
        paystate.put("1", "未支付");
        paystate.put("2", "已支付");
        Map<String, String> noticestate = new HashMap<>();
        noticestate.put("1", "未读");
        noticestate.put("2", "已读");
        CACHE.put("paystate", paystate);
        CACHE.put("noticestate", noticestate);
    }

    @Test
    public void testOrderInfo2OrderInfoVO() {
        OrderInfo orderInfo = new OrderInfo("123456789", "1", new BigDecimal("2135.678"), new Date());
        Map<String, String> format = new HashMap<>();
        format.put("paystate", "paystateDesc");
        OrderInfoVo translateOrderInfoVo = OrderConvert.INSTANCE.translate(orderInfo, format, CACHE);
        log.info("orderInfo->{}", orderInfo);
        log.info("translateOrderInfoVo->{}", translateOrderInfoVo);
    }

}
