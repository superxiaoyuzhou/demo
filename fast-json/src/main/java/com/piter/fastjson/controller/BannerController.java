package com.piter.fastjson.controller;

import com.piter.fastjson.model.BannerAddDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * fastJson自定义序列化反序列化
 *
 * @author Piter
 */
@RestController
@RequestMapping(value = "/fastJson")
@Slf4j
public class BannerController {

    /**
     * 金额格式自定义序列化反序列化格式
     * @param bannerAddDTO
     * @return
     */
    @PostMapping(value = "/test")
    public BannerAddDTO add(@Valid @RequestBody(required = false) BannerAddDTO bannerAddDTO){
        log.info(bannerAddDTO.toString());
        return bannerAddDTO;
    }

}
