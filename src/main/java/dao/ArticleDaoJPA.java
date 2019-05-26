package dao;

import entity.Article;
import entity.ArticleEntity;
import entity.NewArticle;
import io.vavr.collection.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Optional;

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
    public Optional<Article> get(long id) {
        return getEntity((id)).map(a -> new Article(a));
    }

    @Override
    public void save(NewArticle obj) {
        ArticleEntity articleEntity = new ArticleEntity(obj);
        em.getTransaction().begin();
        em.persist(articleEntity);
        em.getTransaction().commit();
    }

    @Override
    public void delete(long id) {
        getEntity(id).ifPresent(a -> {
            em.getTransaction().begin();
            em.remove(a);
            em.getTransaction().commit();
        });
    }

    @Override
    public void update(Article obj) {
        getEntity(obj.id).map(article -> {
            article.setTitle(obj.title);
            article.setContent(obj.content);
            return article;
        }).ifPresent(article -> {
            em.getTransaction().begin();
            em.persist(article);
            em.getTransaction().commit();
        });
    }

    private Optional<ArticleEntity> getEntity(long id) {
//        em.getTransaction().begin();
        try {
            return Optional.of((ArticleEntity) em
                    .createQuery("SELECT a FROM ArticleEntity a WHERE id = :id")
                    .setParameter("id",id)
                    .getSingleResult());
        } catch (Exception e){
            return Optional.empty();
        }
//        ArticleEntity ae = (ArticleEntity) em
//                .createQuery("SELECT ae FROM ArticleEntity ae WHERE id =" + id)
//                .getSingleResult();
        // rezugnujemy z transakcji - usuwamy begin i commit!
//        em.getTransaction().commit();
    }
}
