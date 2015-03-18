package model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author Felix
 */
@Entity
public class StocksArticle implements Serializable {

    //Attribute

    private String nameArt;
    private String commentArt;
    private static final String defaultComment = "";
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long stocksArticleID;
    @ManyToOne
    private Place place;
    @OneToMany(cascade=CascadeType.REMOVE)
    private List<StocksUnit> stocksUnits;

    //Konstruktoren
    public StocksArticle() {
        //TODO
    }

    public StocksArticle(String nameArt, Place place, String commentArt) {
        this.nameArt = nameArt;
        this.commentArt = commentArt;
        this.place = place;
    }

    public StocksArticle(String nameArt, Place place) {
        this.nameArt = nameArt;
        this.commentArt = defaultComment;
        this.place = place;
    }

    //Getter und Setter
    public Long getStocksArticleID() {
        return stocksArticleID;
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

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }
    
    public List<StocksUnit> getStocksUnitList(){
        return stocksUnits;
    }
}
