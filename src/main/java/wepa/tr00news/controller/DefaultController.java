package wepa.tr00news.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DefaultController {

    @RequestMapping("*")
    @ResponseBody
    public String handleDefault() {
        StringBuilder sb = new StringBuilder();
        sb.append("This is the water, and this is the well.</br>")
                .append("Drink full, and descend.<br/>")
                .append("The horse is the white of the eyes, and dark within.<br/>");

        for (int i = 0; i < 7; i ++) {
            sb.append("...<br/>Got a light?<br/>");
        }

        return sb.toString();
    }

}