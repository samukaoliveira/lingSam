package com.samukaoliveira.lingSam.controllers;

import com.samukaoliveira.lingSam.models.Licao;
import com.samukaoliveira.lingSam.services.LicaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/licoes")
public class LicaoController {

    private final LicaoService service;

    @GetMapping
    public String listar(Model model){

        model.addAttribute("licoes", service.listar());

        return "licoes/lista";

    }

    @GetMapping("/nova")
    public String nova(Model model){

        model.addAttribute("licao", new Licao());

        return "licoes/form";

    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Licao licao){

        service.salvar(licao);

        return "redirect:/licoes";

    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id,
                         Model model){

        model.addAttribute("licao",
                service.buscarPorId(id));

        return "licoes/form";

    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id){

        service.excluir(id);

        return "redirect:/licoes";

    }

    @GetMapping("/{id}")
    public String visualizar(@PathVariable Long id, Model model) {

        Licao licao = service.buscarPorId(id);

        model.addAttribute("licao", licao);

        model.addAttribute("palavras",
                licao.getTextoOriginal().split("\\s+"));

        return "licoes/view";
    }

}