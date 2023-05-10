package com.yxq.desmonddossofficeplatform.controller;

import com.yxq.desmonddossofficeplatform.service.impl.QRServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Api(tags="二维码")
@RestController
@RequiredArgsConstructor
@RequestMapping("qrcode")
public class QRController {
    private final QRServiceImpl qrService;

    /**
     * 生成二维码图片放入响应流中
     * @param content 文字内容
     * @param servletResponse
     * @throws IOException
     */
    @ApiOperation("生成二维码放到响应流中")
    @GetMapping
    public void generateV3(String content, HttpServletResponse servletResponse) throws IOException {
        qrService.generateStream(content, servletResponse);
    }

    /**
     * 生成base64图片
     * @param content 文字内容
     * @return 图片的base64编码
     */
    @ApiOperation("生成base64图片")
    @PostMapping ("base64")
    public String generateV3(String content) {
        return qrService.generateBase64(content);
    }

}
