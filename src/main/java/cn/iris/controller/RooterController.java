package cn.iris.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Iris 2022/1/8
 */
@Controller
public class RooterController {

    @RequestMapping({"/","/index"})
    public String index() {
        return "index";
    }

    @RequestMapping({"/tologin"})
    public String toLogin() {
        return "views/login";
    }

    @RequestMapping({"level1/{id}"})
    public String Level1Login(@PathVariable("") int id) {
        return "views/level1/"+id;
    }

    @RequestMapping({"level2/{id}"})
    public String Level2Login(@PathVariable("") int id) {
        return "views/level2/"+id;
    }

    @RequestMapping({"level3/{id}"})
    public String Level3Login(@PathVariable("") int id) {
        return "views/level3/"+id;
    }

}
