package com.daniel.personalblog;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

@Service
public class ArticleService {
    private static final Logger logger = LoggerFactory.getLogger(ArticleService.class);

    private final ObjectMapper objectMapper;
    private ArrayList<Article> articles;
    private File articlesFile;


    public ArticleService(ObjectMapper objectMapper) throws IOException {
        objectMapper.registerModule(new JavaTimeModule());

        Resource resource = new ClassPathResource("articles/articles.JSON");
        articlesFile = resource.getFile();
        articles = objectMapper.readValue(articlesFile, new TypeReference<ArrayList<Article>>() {});

        this.objectMapper = objectMapper;
    }

    public ArrayList<Article> findAll() {
        return articles;
    }

    public Article findByIndex(int idx) {
        return articles.get(idx);
    }

    public void editByIndex(int idx, Article newArticle) {
        try {
            articles.set(idx, newArticle);
            objectMapper.writeValue(articlesFile, articles);
        }
        catch(IOException e) {
            logger.error("Error trying to edit article: {}", idx, e);
        }
    }

    public void addArticle(Article article) {
        try {
            articles.add(article);
            objectMapper.writeValue(articlesFile, articles);
        }
        catch(IOException e) {
            logger.error("Error trying to add article: {}", article, e);
        }
    }

    public void deleteByIndex(int idx) {
        try {
            articles.remove(idx);
            objectMapper.writeValue(articlesFile, articles);
        }
        catch(IOException e) {
            logger.error("Error trying to delete article {}", idx, e);
        }
    }
}
