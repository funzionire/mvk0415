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
    private int PlacesReferenceId;
    
    private String defaultComment = "";

    public StocksArticle() {

    }

    public StocksArticle(Long idArt, String nameArt, String commentArt, int PlacesReferenceId) {
        this.idArt = idArt;
        this.nameArt = nameArt;
        this.commentArt = commentArt;
        this.PlacesReferenceId = PlacesReferenceId;
    }
    
    public StocksArticle(Long idArt, String nameArt, int PlacesReferenceId) {
        this.idArt = idArt;
        this.nameArt = nameArt;
        this.commentArt = defaultComment;
        this.PlacesReferenceId = PlacesReferenceId;
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
        return PlacesReferenceId;
    }

    public void setPlacesReferenceId(int PlacesReferenceId) {
        this.PlacesReferenceId = PlacesReferenceId;
    }
}
