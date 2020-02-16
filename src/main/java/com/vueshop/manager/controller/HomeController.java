package com.vueshop.manager.controller;

import com.vueshop.manager.controller.http.response.MenuInfoResponse;
import com.vueshop.manager.controller.http.response.base.HttpResponse;
import com.vueshop.manager.controller.http.response.base.Meta;
import com.vueshop.manager.dao.dto.AuthInfoDto;
import com.vueshop.manager.service.HomeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * 项目名称:com.examples.demo.mapper
 * 描述:
 * 创建人:ryw
 * 创建时间:2018/5/23
 */
@RestController
@RequestMapping("api/private/v1/")
@Slf4j
public class HomeController extends BaseController {

    @Autowired
    private HomeService homeService;


    @RequestMapping("menus")
    @ResponseBody
    public HttpResponse<List<MenuInfoResponse>> getMenus(HttpServletRequest request) {
        HttpResponse<List<MenuInfoResponse>> httpResponse = new HttpResponse<>(new Meta());

        AuthInfoDto authInfoDto = loginAuthorizationCheck(request);
        if (authInfoDto == null) {
            httpResponse.getMeta().setMsg("未登录");
            httpResponse.getMeta().setStatus(400);
            return httpResponse;
        }

        List<MenuInfoResponse> menuInfoResponses = homeService.queryMenuInfoAll();

        httpResponse.setData(menuInfoResponses);
        httpResponse.getMeta().setMsg("获取菜单列表成功");
        httpResponse.getMeta().setStatus(200);
        return httpResponse;
    }


}
