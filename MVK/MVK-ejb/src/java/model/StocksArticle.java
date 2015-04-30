/*
Entity Klasse "StocksArticle" stellt die Entity StocksArticle - Vorratsartikel 
und das damit verbunden StocksArticle Objekt in der Datenbank dar.
*/
package model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

@Entity
@NamedQueries(
        {
            @NamedQuery(name = "StocksArticle.findById", query = "SELECT StocksArticle FROM StocksArticle stocksArticle WHERE stocksArticle.stocksArticleID = :stocksArticleID")
        })
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
    @OneToMany(cascade=CascadeType.ALL)
    private List<StocksUnit> stocksUnits;

    //Konstruktoren
    public StocksArticle() {
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
