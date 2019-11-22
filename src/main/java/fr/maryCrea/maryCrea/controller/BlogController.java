package fr.maryCrea.maryCrea.controller;

import fr.maryCrea.maryCrea.entity.Article;
import fr.maryCrea.maryCrea.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class BlogController {
    @Autowired
    private ArticleRepository repository;

    @GetMapping("/blog")
    public String mesArticles(Model out){
        out.addAttribute("articles", repository.findAll());
        return "blog";
    }

    @GetMapping("/monarticle")
    public String monarticle(Model out,@RequestParam(name = "id",defaultValue = "1")Long id){
        Article article = new Article();
        if (id != null) {
            Optional<Article> optionalArticle = repository.findById(id);
            if (optionalArticle.isPresent()) {
                article = optionalArticle.get();
            }
        }
        out.addAttribute("article", article);
        return "monarticle";
    }
}
