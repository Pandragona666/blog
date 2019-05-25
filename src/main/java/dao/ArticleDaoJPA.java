package dao;

import entity.Article;
import entity.ArticleEntity;
import entity.NewArticle;
import io.vavr.collection.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class ArticleDaoJPA implements Dao<Article, NewArticle> {

    private EntityManager em;

    public ArticleDaoJPA(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Article> getAll() {
        em.getTransaction().begin();
        Query query = em.createQuery("from ArticleEntity ");
        List<Article> result = List.ofAll(query.getResultStream().map(e -> new Article((ArticleEntity) e)));
        em.getTransaction().commit();
        return result;
    }

    @Override
    public Article get(long id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void save(NewArticle obj) {
        ArticleEntity articleEntity = new ArticleEntity(obj);
        em.getTransaction().begin();
        em.persist(articleEntity);
        em.getTransaction().commit();

    }

    @Override
    public void delete(Article obj) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void update(Article obj) {
        throw new UnsupportedOperationException();
    }
}
