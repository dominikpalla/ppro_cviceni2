package cz.uhk.cviceni22;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

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
    public String processForm(@Valid @ModelAttribute("user") User user, BindingResult br, Model model){

        if(br.hasErrors()){
            model.addAttribute("eyeColors", eyeColors);
            model.addAttribute("genders", genders);
            return "index";
        }else{
            return "result";
        }
    }

    @InitBinder
    public void initBinder(WebDataBinder db){
        StringTrimmerEditor e = new StringTrimmerEditor(true);
        db.registerCustomEditor(String.class, e);
    }

    @ResponseBody
    @GetMapping("/product/{id}")
    public String pathVariableTest(@PathVariable("id") int id){
        return "ID: " + String.valueOf(id);
    }
}
