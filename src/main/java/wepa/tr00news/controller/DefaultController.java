package wepa.tr00news.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DefaultController {

    @RequestMapping("*")
    @ResponseBody
    public String handleDefault() {
        return "This is the water, and this is the well.<br/>" +
                "Drink full, and descend.<br/>" +
                "The horse is the white of the eyes, and dark within.<br/>" +
                "...<br/>" +
                "Got a light?<br/>" +
                "...<br/>" +
                "Got a light?<br/>";
    }

}