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
import javax.servlet.RequestDispatcher;
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
        RequestDispatcher rd = req.getRequestDispatcher("add_article.jsp");
        rd.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action =  req.getParameter("action");
        switch (action){
            case "add":
                String title =  req.getParameter("title");
                String content = req.getParameter("content");
                repo.addArticle(new NewArticle(content, title));
                break;
        }
    }
}