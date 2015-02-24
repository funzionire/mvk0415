package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author Felix
 */
public class StocksArticle implements Serializable {

    private Long idArt;
    private String nameArt;
    private String commentArt;
    private int placesReferenceId;
    
    private String defaultComment = "";

    public StocksArticle() {

    }

    public StocksArticle(Long idArt, String nameArt, String commentArt, int placesReferenceId) {
        this.idArt = idArt;
        this.nameArt = nameArt;
        this.commentArt = commentArt;
        this.placesReferenceId = placesReferenceId;
    }
    
    public StocksArticle(Long idArt, String nameArt, int placesReferenceId) {
        this.idArt = idArt;
        this.nameArt = nameArt;
        this.commentArt = defaultComment;
        this.placesReferenceId = placesReferenceId;
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
    
    public int getPlacesReferenceId() {
        return placesReferenceId;
    }

    public void setPlacesReferenceId(int placesReferenceId) {
        this.placesReferenceId = placesReferenceId;
    }
}
