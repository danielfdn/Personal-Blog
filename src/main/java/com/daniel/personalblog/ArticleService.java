package com.daniel.personalblog;


import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ArticleService {
    private ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public List<Article> findAll() {
        return articleRepository.findAll();
    }

    public Article findById(Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    public void addArticle(Article article) {
        articleRepository.save(article);
    }

    public void editById(Long id, Article newArticle) {
        newArticle.setId(id);
        articleRepository.save(newArticle);
    }

    public void deleteById(Long id) {
        articleRepository.deleteById(id);
    }
}
