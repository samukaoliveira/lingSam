package com.samukaoliveira.lingSam.controllers;

import com.samukaoliveira.lingSam.services.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final DashboardService dashboardService;

    public HomeController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/")
    public String home(Model model) {

        model.addAttribute("dashboard",
                dashboardService.obterResumo());

        return "home";
    }

}
