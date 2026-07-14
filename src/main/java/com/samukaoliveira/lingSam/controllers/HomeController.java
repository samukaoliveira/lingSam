package com.samukaoliveira.lingSam.controllers;

import com.samukaoliveira.lingSam.services.DashboardService;
import com.samukaoliveira.lingSam.services.LicaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private final DashboardService dashboardService;

    @Autowired
    private final LicaoService licaoService;

    public HomeController(DashboardService dashboardService, LicaoService licaoService) {

        this.dashboardService = dashboardService;
        this.licaoService = licaoService;
    }

    @GetMapping("/")
    public String home(Model model) {

        model.addAttribute("dashboard",
                dashboardService.obterResumo());

        model.addAttribute(
                "licoesRecentes",
                licaoService.listarRecentes(6));

        return "home";
    }

}
