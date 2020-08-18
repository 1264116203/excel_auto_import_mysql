package com.beibei.contorller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: 备备
 * @Date:2020/8/18
 * @Description:com.beibei.contorller
 * @Version:1.0
 */
@Controller
public class PageController {

    @RequestMapping("{page}")
    public String getPage(@PathVariable String page){
        return page;
    }
}

