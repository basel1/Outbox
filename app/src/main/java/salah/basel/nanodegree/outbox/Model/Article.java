package salah.basel.nanodegree.outbox.Model;

import java.io.Serializable;

/**
 * Created by Basel on 31/07/2016.
 */
public class Article implements Serializable {
    String id;
    String head;
    String abstraact;
    String content;
    String photo;
    String tag_name;
    String writer_name;
    String art_time;
    String edition_number;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getAbstraact() {
        return abstraact;
    }

    public void setAbstraact(String abstraact) {
        this.abstraact = abstraact;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getTag_name() {
        return tag_name;
    }

    public void setTag_name(String tag_name) {
        this.tag_name = tag_name;
    }

    public String getWriter_name() {
        return writer_name;
    }

    public void setWriter_name(String writer_name) {
        this.writer_name = writer_name;
    }

    public String getArt_time() {
        return art_time;
    }

    public void setArt_time(String art_time) {
        this.art_time = art_time;
    }

    public String getEdition_number() {
        return edition_number;
    }

    public void setEdition_number(String edition_number) {
        this.edition_number = edition_number;
    }
}
