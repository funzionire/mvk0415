package model;

import java.io.Serializable;

/**
 *
 * @author Felix
 */
public class StocksArticle implements Serializable {

    private Long idArt;
    private String nameArt;
    private String commentArt;

    public StocksArticle() {

    }

    public StocksArticle(Long idArt, String nameArt, String commentArt) {
        this.idArt = idArt;
        this.nameArt = nameArt;
        this.commentArt = commentArt;
    }

    public Long getId() {
        return idArt;
    }

    public void setId(Long idArt) {
        this.idArt = idArt;
    }

    public String getName() {
        return nameArt;
    }

    public void setName(String nameArt) {
        this.nameArt = nameArt;
    }
    
    public String getComment() {
        return commentArt;
    }

    public void setComment(String commentArt) {
        this.commentArt = commentArt;
    }
    
    
}
