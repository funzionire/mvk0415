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
    private long idArt = 0;
    @OneToOne
    private String nameArt;
    private String commentArt;
    //hier kam eine Fehlermeldung ohne @OneToOne
    @OneToOne
    private Place place;
    private static final String defaultComment = "";

    @OneToMany
    private List<StocksUnit> stocksUnits;

    //ID brauchen wir nicht im Konstruktor mitgeben aber erzeugen schon oder?
    public StocksArticle(String nameArt, String commentArt, Place place) {
        this.nameArt = nameArt;
        this.commentArt = commentArt;
        this.place = place;
        this.idArt = idArt++;
    }

    public StocksArticle(String nameArt, Place place) {
        this.nameArt = nameArt;
        this.commentArt = defaultComment;
        this.place = place;
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

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    //TODO
    public boolean createStocksUnit(int quantity, Date mdd, String commentSUnit) {
        return stocksUnits.add(new StocksUnit(quantity, mdd, commentSUnit));
    }

}
