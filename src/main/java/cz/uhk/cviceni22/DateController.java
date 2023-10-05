package cz.uhk.cviceni22;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class DateController {

    @Value("${eyeColors}")
    private List<String> eyeColors;

    @Value("${genders}")
    private List<String> genders;

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("user", new User());
        model.addAttribute("eyeColors", eyeColors);
        model.addAttribute("genders", genders);
        return "index";
    }

    @GetMapping("/process")
    public String processForm(@ModelAttribute("user") User user){
        return "result";
    }

    @ResponseBody
    @GetMapping("/product/{id}")
    public String pathVariableTest(@PathVariable("id") int id){
        return "ID: " + String.valueOf(id);
    }
}
