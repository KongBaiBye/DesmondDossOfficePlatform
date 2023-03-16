package com.yxq.desmonddossofficeplatform.controller;

import cn.hutool.extra.servlet.ServletUtil;
import com.wf.captcha.ArithmeticCaptcha;
import com.yxq.desmonddossofficeplatform.utils.RedisUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author yxq
 * @Date 2023/2/22 20:21
 */
@Api(tags = "验证码")
@Controller
@RequestMapping("captcha")
public class CaptchaController {
    @GetMapping("ca")
    @ApiOperation("获取验证码")
    public void captcha(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //获取用户ip地址
        String ip = ServletUtil.getClientIP(req, null);
        //获取验证码
        ArithmeticCaptcha captcha = new ArithmeticCaptcha();
        String code = captcha.text();
        //放进redis中
        RedisUtils.setString("captcha" + ip, code, 300);
        //使swaggerAPI文档可以生成图片
        resp.setContentType("application/image");
        captcha.out(resp.getOutputStream());
    }
}
