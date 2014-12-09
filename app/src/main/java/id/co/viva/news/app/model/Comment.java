package id.co.viva.news.app.model;

/**
 * Created by reza on 03/12/14.
 */
public class Comment {

    private String id;
    private String article_id;
    private String username;
    private String parent_id;
    private String comment_text;
    private String app_id;
    private String submitted_date;
    private String status;

    public String getArticle_id() {
        return article_id;
    }

    public void setArticle_id(String article_id) {
        this.article_id = article_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }

    public String getComment_text() {
        return comment_text;
    }

    public void setComment_text(String comment_text) {
        this.comment_text = comment_text;
    }

    public String getApp_id() {
        return app_id;
    }

    public void setApp_id(String app_id) {
        this.app_id = app_id;
    }

    public String getSubmitted_date() {
        return submitted_date;
    }

    public void setSubmitted_date(String submitted_date) {
        this.submitted_date = submitted_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Comment(String id, String article_id, String name, String parent_id,
                   String comment_text, String app_id, String submitted_date, String status) {
        this.id = id;
        this.article_id = article_id;
        this.username = name;
        this.parent_id = parent_id;
        this.comment_text = comment_text;
        this.app_id = app_id;
        this.submitted_date = submitted_date;
        this.status = status;
    }

}
