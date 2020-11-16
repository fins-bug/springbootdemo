package work.finsbug.auth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @description 测试security的功能
 * @author fins
 * @date 2020/11/14
 **/
@Controller
@RequestMapping("test")
public class LoginController {

    @ResponseBody
    @GetMapping("/index")
    public String index() {
        return "index";
    }
}
