package model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author Felix
 */
@Entity
public class StocksArticle implements Serializable {
    @Id
    @GeneratedValue
    private long idArt;
    @OneToOne
    private String nameArt;
    private String commentArt;
    private static final String defaultComment = "";
    private Place place;
    @OneToMany
    private List<StocksUnit> stocksUnits;
    
    
    
    public StocksArticle(Place place, String nameArt, String commentArt) {
        this.nameArt = nameArt;
        this.commentArt = commentArt;
        this.place=place;
    }
    
    public StocksArticle(Place place, String nameArt) {
        this.nameArt = nameArt;
        this.commentArt = defaultComment;
        this.place=place;
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
    
    //TODO
    public boolean createStocksUnit(int quantity, Date mdd, String commentSUnit){
        return stocksUnits.add(new StocksUnit(quantity, mdd, commentSUnit));
    }

}
