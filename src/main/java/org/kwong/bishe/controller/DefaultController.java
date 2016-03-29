package org.kwong.bishe.controller;

import org.kwong.bishe.common.util.AnalysisFile4Movie;
import org.kwong.bishe.domain.*;
import org.kwong.bishe.persistence.client.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/3/25.
 */
@Controller
@RequestMapping(value = "/home")
public class DefaultController {

    @Autowired
    UserMapper userMapper;
    @Autowired
    TypeMapper typeMapper;
    @Autowired
    MovieMapper MovieMapper;
    @Autowired
    RatingMapper ratingMapper;
    @Autowired
    MovieTypeMapper movieTypeMapper;

    @RequestMapping(value = "/movies")
    public String defaultHomePage(){
        return "movie/index";
    }
    @RequestMapping(value = "/preview")
    public String previewPage(){
        return "movie/preview";
    }

    @ResponseBody
    @RequestMapping(value = "/test" ,method = RequestMethod.POST)
    public Object test() {
        Map<String,Object> params = new HashMap<>();

        params.put("id", "00000873-f59f-11e5-895a-a55ee5623c05");
        List<Rating> ratingList = ratingMapper.queryOne(params);
        System.out.println(ratingList);
        return 1;
    }
}
