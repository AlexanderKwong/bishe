package org.kwong.bishe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2016/3/25.
 */
@Controller
@RequestMapping(value = "/home")
public class DefaultController {

    @RequestMapping(value = "/mnvies")
    public String defaultHomePage(){
        return "movie/index";
    }
}
