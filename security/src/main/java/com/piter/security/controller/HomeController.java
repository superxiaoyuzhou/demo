package com.piter.security.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    /**
     * 添加 @EnableGlobalMethodSecurity(securedEnabled = true,prePostEnabled = true)
     * 设置只有拥有 USER角色 能够访问
     * @return
     */
//    @PreAuthorize("hasAuthority('ROLE_USER')")
    @PreAuthorize("hasRole('USER')")
//    @Secured("ROLE_USER")
    @RequestMapping("/user/**")
    public String user() {
        return "user";
    }
    @RequestMapping("/admin/**")
    public String admin() {
        return "admin";
    }
}
