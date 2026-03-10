package com.daniel.personalblog;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;



@Controller
public class BlogController {

    private final ArticleService articleService;

    public BlogController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping({"","/","/home"})
    public String homeView(Model model) { //model aus MVC - enthält Daten, die von der Ansicht dargestellt werden// (=model view controller)
        ArrayList<Article> articleList = articleService.findAll();
        model.addAttribute("articleList", articleList);
        return "home"; //Name unserer html Datei
    }

    @GetMapping("/article/{idx}")
    public String articleDetail(@PathVariable int idx, Model model) {
        Article article = articleService.findByIndex(idx);
        model.addAttribute("article", article);
        return "article";
    }

    @GetMapping("/admin")
    public String adminView(Model model, Integer idx) {
        ArrayList<Article> articleList = articleService.findAll();
        model.addAttribute("articleList", articleList);
        return "admin-view";
    }

    @GetMapping("/edit/{idx}") //+ postMapper
    public String editArticle(@PathVariable int idx, Model model) {
        Article article = articleService.findByIndex(idx);

        model.addAttribute("article", article);
        model.addAttribute("idx", idx);


        return "update-article";
    }

    @PostMapping("/edit/{idx}")
    public String postEditedArticle(@PathVariable int idx , @ModelAttribute Article article) {
        articleService.editByIndex(idx, article);
        return "redirect:/admin";
    }

    @GetMapping("/new") //postMapper
    public String createNewArticle(Model model) {
        Article newArticle = new Article();
        model.addAttribute("newArticle", newArticle);

        return "create-article";
    }

    @PostMapping("/new")
     public String postNewArticle(@ModelAttribute Article article){
        articleService.addArticle(article);
        return "redirect:/admin";
    }

    @PostMapping("/admin/delete/{idx}")
    public String deleteArticle(@PathVariable int idx){
        articleService.deleteByIndex(idx);
        return "redirect:/admin";
    }

}




