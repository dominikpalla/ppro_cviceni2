package cz.uhk.cviceni22.web;

import cz.uhk.cviceni22.model.Post;
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
import java.util.Random;

@Controller
public class DateController {

    private UserDAO dao;

    @Value("${eyeColors}")
    private List<String> eyeColors;

    @Value("${genders}")
    private List<String> genders;

    @Autowired
    public DateController(UserDAO dao) {
        this.dao = dao;
    }

    @GetMapping("/user/{id}")
    @ResponseBody
    public String userDetail(@PathVariable int id){
        return dao.getUserById(id).toString();
    }

    @GetMapping("/delete/{id}")
    @ResponseBody
    public String userDelete(@PathVariable int id){
        try{
            dao.deleteUser(id);
            return "User deleted.";
        }catch (Exception e){
            return "User does not exist.";
        }
    }

    @GetMapping("/posts")
    @ResponseBody
    public String posts(){
        Random r = new Random();

        User u = dao.getUserById(1);

        Post post = new Post();
        post.setText("Test " + String.valueOf(r.nextInt()));

        post.setUser(u);
        u.addPost(post);

        dao.saveUser(u);

        List<Post> posts = dao.getPostsByUser(1);

        String out = "";

        for(Post p : posts){
            out += p.getText() + "<br>";
        }

        return out;
    }

    @GetMapping("/")
    @ResponseBody
    public String allUsers(){
        String txt = "";

        List<User> users = dao.getAllUsers();

        for(User u : users){
            txt += u.toString() + "<br><br>";
        }

        return txt;
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
        try {
            User user = dao.getUserById(id);
            model.addAttribute("user", user);
            model.addAttribute("eyeColors", eyeColors);
            model.addAttribute("genders", genders);
            return "new";
        }catch (Exception e){
            return "error";
        }
    }

    @GetMapping("/process")
    public String processForm(@Valid @ModelAttribute("user") User user, BindingResult br, Model model){
        if(br.hasErrors()){
            model.addAttribute("eyeColors", eyeColors);
            model.addAttribute("genders", genders);
            return "new";
        }

        if(user.getId() == 0){
            //new
            dao.saveUser(user);
        }else{
            //update
            dao.updateUser(user);
        }

        return "result";
    }


    @InitBinder
    public void initBinder(WebDataBinder db){
        StringTrimmerEditor e = new StringTrimmerEditor(true);
        db.registerCustomEditor(String.class, e);
    }
}
