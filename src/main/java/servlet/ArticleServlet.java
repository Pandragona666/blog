package servlet;

import dao.ArticleDaoJPA;
import entity.Article;
import entity.NewArticle;
import helper.Encoding;
import helper.Paginator;
import helper.Parse;
import service.ArticleRepository;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = "/article")
public class ArticleServlet extends HttpServlet {

    public static final String ACTION_VIEW_ALL = "viewAll";
    public static final String ACTION_VIEW = "view";
    public static final String ACTION_DELETE = "delete";
    public static final String ACTION_ADD = "add";
    public static final String ACTION = "action";
    public static final String INDEX = "index";
    public static final String CHANGE_TITLE = "update";
    public static final String UPDATE = "update";
    public static final String ACTION_VIEW_ALL_SHORT_LIST = "viewShortList";
    public static final int PAGE_SIZE = 5;
    private ArticleRepository repo;
    private Paginator paginator;


    @Override
    public void init() throws ServletException {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("blog");
        repo = new ArticleRepository((new ArticleDaoJPA(factory.createEntityManager())));
        paginator = new Paginator();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter(ACTION);
        switch (action) {
            case ACTION_VIEW_ALL: {
                req.setAttribute("articles", repo.getAll().asJava());
                RequestDispatcher rd = req.getRequestDispatcher("view_articles.jsp");
                rd.forward(req, resp);
            }
            break;
            case ACTION_VIEW_ALL_SHORT_LIST: {
                List<Article> articlesList = repo.getAll().asJava();
                paginator.configure(PAGE_SIZE, articlesList.size());
                preparePagination(req);
                req.setAttribute("page", paginator.getPage());
                req.setAttribute("articles", articlesList.stream().skip(paginator.getFrom()).limit(paginator.getTo()-paginator.getFrom()+1).collect(Collectors.toList()));
                RequestDispatcher rd = req.getRequestDispatcher("view_articles_part.jsp");
                rd.forward(req, resp);
            }
            break;
            case ACTION_VIEW: {
                Parse.parseLong(req.getParameter("id"))
                        .ifPresent(id -> repo.get(id)
                                .ifPresent(a -> req.setAttribute("article", a)));
                RequestDispatcher rd = req.getRequestDispatcher("view_article.jsp");
                rd.forward(req, resp);
            }
            break;
            case ACTION_DELETE: {
//                long id = Long.parseLong(req.getParameter("id"));
//                repo.remove(id);
                Parse.parseLong(req.getParameter("id")).ifPresent(id -> repo.remove(id));
                req.setAttribute("articles", repo.getAll().asJava());
                resp.sendRedirect("article?" + ACTION + "=" + ACTION_VIEW_ALL);
            }
            break;
            case ACTION_ADD: {
                RequestDispatcher rd = req.getRequestDispatcher("add_article.jsp");
                rd.forward(req, resp);
            }
            break;
            case INDEX: {
                RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
                rd.forward(req, resp);
            }
            break;
            case CHANGE_TITLE: {
                Parse.parseLong(req.getParameter("id"))
                        .ifPresent(id -> repo.get(id)
                        .ifPresent(a -> {
                            req.setAttribute("article", a);
                        }));
                RequestDispatcher rd = req.getRequestDispatcher("update_article.jsp");
                rd.forward(req,resp);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter(ACTION);
        switch (action) {
            case ACTION_ADD: {
                String title = Encoding.encode(req.getParameter("title"));
                String content = Encoding.encode(req.getParameter("content"));
                repo.addArticle(new NewArticle(content, title));
                resp.sendRedirect("article?" +  ACTION + "=" + ACTION_VIEW_ALL);
            }
            break;
            case UPDATE: {
                String title = Encoding.encode(req.getParameter("title"));
                Parse.parseLong(req.getParameter("id"))
                        .ifPresent(id -> repo.changeTitle(id, title));
                resp.sendRedirect("article?" +  ACTION + "=" + ACTION_VIEW_ALL);
            }
        }
    }

    private void preparePagination(HttpServletRequest req) {

        String direction = req.getParameter("page");
        if ("prev".equals(direction)) {
            paginator.prevPage();
        }
        if ("next".equals(direction)) {
            paginator.nextPage();
        }
        if ("first".equals(direction)) {
            paginator.startPage();
        }
        if ("last".equals(direction)) {
            paginator.lastPage();
        }


    }
}
