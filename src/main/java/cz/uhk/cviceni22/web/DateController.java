package cz.uhk.cviceni22.web;

import cz.uhk.cviceni22.model.User;
import cz.uhk.cviceni22.model.UserDAO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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

    private UserDAO dao;

    @Autowired
    public DateController(UserDAO dao){
        this.dao = dao;
    }

    @Value("${eyeColors}")
    private List<String> eyeColors;

    @Value("${genders}")
    private List<String> genders;

    @GetMapping("/all")
    @ResponseBody
    public String all(){
        String text = "";

        for(User u : dao.getAllUsers()){
            text += u.toString() + "<br><br>";
        }

        return text;
    }

    @GetMapping("/user/{id}")
    @ResponseBody
    public String user(@PathVariable int id){

        try {
            User u = dao.getUserById(id);
            return u.toString();
        }catch (Exception e){
            return "Uživatel neexistuje.";
        }
    }

    @GetMapping("/deluser/{id}")
    @ResponseBody
    public String deletUser(@PathVariable int id){

        try {
            dao.deleteUser(id);
            return "Uživatel byl smazán.";
        }catch (Exception e){
            return "Uživatel neexistuje.";
        }
    }

    @GetMapping("/new")
    public String newUser(Model model){
        model.addAttribute("user", new User());
        model.addAttribute("eyeColors", eyeColors);
        model.addAttribute("genders", genders);
        return "new";
    }

    @GetMapping("/update/{id}")
    public String updateUser(Model model, @PathVariable int id){
        User u = dao.getUserById(id);
        model.addAttribute("user", u);
        model.addAttribute("eyeColors", eyeColors);
        model.addAttribute("genders", genders);
        model.addAttribute("new", 0);
        return "update";
    }

    @GetMapping("/processNew")
    public String processNewForm(@Valid @ModelAttribute("user") User user, BindingResult br, Model model){

        if(br.hasErrors()){
            model.addAttribute("eyeColors", eyeColors);
            model.addAttribute("genders", genders);
            return "new";
        }else{
            // uložení User objektu
            dao.saveUser(user);
            return "result";
        }
    }

    @GetMapping("/processUpdate")
    public String processUpdateForm(@Valid @ModelAttribute("user") User user, BindingResult br, Model model){

        System.out.println(model.getAttribute("new"));

        if(br.hasErrors()){
            model.addAttribute("eyeColors", eyeColors);
            model.addAttribute("genders", genders);
            return "update";
        }else{
            // uložení User objektu
            dao.updateUser(user);
            return "result";
        }
    }

    @InitBinder
    public void initBinder(WebDataBinder db){
        StringTrimmerEditor e = new StringTrimmerEditor(true);
        db.registerCustomEditor(String.class, e);
    }
}
