package com.daniel.personalblog;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@Controller
public class BlogController {

    private final ArticleService articleService;

    public BlogController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping({"","/","/home"})
    public String homeView(Model model) { //model aus MVC - enthält Daten, die von der Ansicht dargestellt werden// (=model view controller)
        List<Article> articleList = articleService.findAll();
        model.addAttribute("articleList", articleList);
        return "home"; //Name unserer html Datei
    }

    @GetMapping("/article/{id}")
    public String articleDetail(@PathVariable Long id, Model model) {
        Article article = articleService.findById(id);
        model.addAttribute("article", article);
        return "article";
    }

    @GetMapping("/admin")
    public String adminView(Model model, Long id) {
        List<Article> articleList = articleService.findAll();
        model.addAttribute("articleList", articleList);
        return "admin-view";
    }

    @GetMapping("/edit/{id}") //+ postMapper
    public String editArticle(@PathVariable Long id, Model model) {
        Article article = articleService.findById(id);

        model.addAttribute("article", article);
        model.addAttribute("id", id);


        return "update-article";
    }

    @PostMapping("/edit/{id}")
    public String postEditedArticle(@PathVariable Long id, @ModelAttribute Article article) {
        articleService.editById(id, article);
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

    @PostMapping("/admin/delete/{id}")
    public String deleteArticle(@PathVariable Long id){
        articleService.deleteById(id);
        return "redirect:/admin";
    }
}




