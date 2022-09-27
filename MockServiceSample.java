package com.kakao.payorder.order.service;

import com.kakao.payorder.common.dto.CollectLogReqDto;
import com.kakao.payorder.common.dto.ResponseBody;
import com.kakao.payorder.feign.CollectLogFeign;
import com.kakao.payorder.order.dto.OrderDto;
import com.kakao.payorder.order.entity.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OrderServiceTest {

    private static final Logger logger = LoggerFactory.getLogger(OrderServiceTest.class);

    @Autowired
    private OrderService orderService;

    @Autowired
    private CollectLogFeign collectLogFeign;

    @Test
    public void testAddOrderProcess() throws Exception {
        long userId = 1;
        long menuId = 1;

        OrderDto param = new OrderDto(userId,menuId);
        Order info = orderService.addOrderProcess(param);
        logger.info("created order number : {}",info.getId());
    }

    @Test
    public void testSendCollectLog() throws Exception {
        long userId = 1;
        long menuId = 2;
        int price = 3500;
        CollectLogReqDto reqInfo = new CollectLogReqDto(userId, menuId, price);
        ResponseBody body = collectLogFeign.sendCollectLog(reqInfo);

        logger.info("feign result code : {}",body.getResultCode());
        logger.info("feign result message : {}",body.getMessage());
    }
}
