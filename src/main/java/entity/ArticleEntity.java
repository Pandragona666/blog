package entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class ArticleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private long id;
    private String content;

    private String title;

    private LocalDateTime created;

    public ArticleEntity() {
    }

    public ArticleEntity(String content) {
        this.content = content;
        this.created = LocalDateTime.now();
    }

    public ArticleEntity(NewArticle newArticle) {
        this.content = newArticle.content;
        this.title = newArticle.title;
        this.created = LocalDateTime.now();
    }

    public ArticleEntity(Article article) {
        this.id = article.id;
        this.content = article.content;
        this.title = article.title;
        this.created = article.created;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }
}
