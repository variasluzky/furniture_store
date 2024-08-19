package com.ltp.furniture_store.web;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class HomeController {

    @GetMapping("/")
    public RedirectView redirectToFrontend() {
        return new RedirectView("http://localhost:4200");
    }
}

