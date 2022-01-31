package ua.site.controllers.intro;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class FirstController {

    @GetMapping("/hello")
    public String helloPage(HttpServletRequest request, Model model) {
        String name = request.getParameter("name");

        model.addAttribute("message", "Hello, " + name);

        return "/intro/first/hello";
    }

    @GetMapping("/goodbye")
    public String goodbyePage(@RequestParam(value = "name", required = false) String name) {
        System.out.println("Bye, " + name);
        return "/intro/first/goodbye";
    }

    @GetMapping("/calculator")
    public String calculatorPage(@RequestParam(value = "a") String a,
                                 @RequestParam(value = "b") String b,
                                 @RequestParam(value = "action") String action,
                                 Model model) {


        int aInt = Integer.parseInt(a);
        int bInt = Integer.parseInt(b);
        String answer = "";
        switch (action) {
            case "multiplication" -> {
               answer = String.valueOf(aInt * bInt);
            }
            case "addition" -> {
                answer = String.valueOf(aInt + bInt);
            }
            case "subtraction" -> {
                answer = String.valueOf(aInt - bInt);
            }
            case "division" -> {
                answer = String.valueOf(aInt / bInt);
            }
            default -> answer = "Inccorect action!";
        }
        model.addAttribute("answer", answer);

        return "/intro/first/calculator";
    }
}
