package service;

import dao.Dao;
import entity.Article;
import entity.NewArticle;
import io.vavr.collection.List;

import java.util.Optional;

public class ArticleRepository {

    private Dao<Article, NewArticle> dao;

    public ArticleRepository(Dao<Article, NewArticle> dao) {
        this.dao = dao;
    }

    public void addArticle(NewArticle newArticle) {
        dao.save(newArticle);
    }

    public List<Article> getAll() {
        return dao.getAll();
    }

    public int size(){
        return dao.size();
    }

    public Optional<Article> get(long id) {
        return dao.get(id);
    }

    public void remove(long id) {
        dao.delete(id);
    }

    public void changeTitle(long id, String title){
        dao.get(id)
                .map(old -> new Article(old.id, title, old.content, old.created))
                .ifPresent(dao::update);
    }

}
