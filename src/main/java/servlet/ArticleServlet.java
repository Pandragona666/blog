package servlet;
import dao.ArticleDaoJPA;
import entity.Article;
import entity.ArticleEntity;
import entity.NewArticle;
import io.vavr.collection.List;
import service.ArticleRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/article")
public class ArticleServlet extends HttpServlet {

    private ArticleRepository repo;


    @Override
    public void init() throws ServletException {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("blog");
        repo = new ArticleRepository((new ArticleDaoJPA(factory.createEntityManager())));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        repo.addArticle(new NewArticle("Przykładowy tekst", "Arytuł nr 1"));
        repo.addArticle(new NewArticle("Przykładowy tekst. Przykładowy tekst. ", "Arytuł nr 2"));
        List<Article> articleList = repo.getAll();
        PrintWriter out = resp.getWriter();
        for (Article a: articleList){
            out.println(a.title);
            out.println(a.content);
            out.println(a.created);
        }
    }
}
