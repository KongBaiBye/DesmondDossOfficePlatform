package com.yxq.desmonddossofficeplatform.controller;

import com.alibaba.fastjson.JSON;
import com.alipay.easysdk.factory.Factory;
import com.alipay.easysdk.kernel.util.ResponseChecker;
import com.alipay.easysdk.payment.common.models.AlipayTradeQueryResponse;
import com.alipay.easysdk.payment.facetoface.models.AlipayTradePrecreateResponse;
import com.yxq.desmonddossofficeplatform.dao.PayDao;
import com.yxq.desmonddossofficeplatform.utils.ResultData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Objects;

/**
 * @Author yxq
 * @Date 2023/2/25 14:22
 */
@Api(tags="支付")
@Slf4j
@RestController
@RequestMapping("pay")
@RequiredArgsConstructor
public class PayController {
    private final PayDao payDao;

    /**
     * 支付
     *
     * @return {@link String}
     */
    @PostMapping("aliPay")
    @ApiOperation("订单支付")
    public ResultData pay(String id) {
        //查询订单信息
        Map<String, Object> payMap = payDao.selectOutPatientLogs(Integer.valueOf(id));
        try {
            // 2. 发起API调用（以创建当面付收款二维码为例）
            Integer subject = (Integer) payMap.get("id");

            String totalMoney = Objects.toString(payMap.get("cost"));
            log.info("发起支付,订单号{},订单的总金额{}", id, totalMoney);
            // 参数： subject: 商品信息 outTradeNo: 订单编号 totalAmount: 支付总金额
            AlipayTradePrecreateResponse response = Factory.Payment.FaceToFace().preCreate(String.valueOf(subject),id,totalMoney);
            // 3. 处理响应或异常
            if (ResponseChecker.success(response)) {
                log.info("支付API调用成功");
                Map<String, Map<String, String>> map = JSON.parseObject(response.getHttpBody(), Map.class);
                Map<String, String> s = map.get("alipay_trade_precreate_response");
                System.out.println(s);
                return ResultData.success(s);
            } else {
                log.error("调用失败，原因：" + response.toString());
            }
        } catch (Exception e) {
            log.error("调用遭遇异常，原因：" + e.getMessage());
            throw new RuntimeException(e.getMessage(), e);
        }
        return ResultData.fail();
    }

}
