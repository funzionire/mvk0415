/*
Entity-Klasse "StocksUnit" stellt die Entity StocksUnit - Vorratseinheit
und das damit verbundene StocksUnit Objekt in der Datenbank dar.
*/
package model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;


@Entity
@NamedQueries(
        {
            @NamedQuery(name = "StocksUnit.findById", query = "SELECT StocksUnit FROM StocksUnit stocksUnit WHERE stocksUnit.stocksUnitID = :stocksUnitID")
        })
public class StocksUnit implements Serializable {

    //Attribute
    private int quantity;
    private String commentSUnit;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long stocksUnitID;
    @Temporal(TemporalType.DATE)
    private Date mdd;
    @ManyToOne
    private StocksArticle stocksArticle;

    //Konstruktoren
    public StocksUnit() {

    }

    public StocksUnit(int quantity, Date mdd, String commentSUnit) {
        this.quantity = quantity;
        this.mdd = mdd;
        this.commentSUnit = commentSUnit;
    }

    //Getter und Setter
    public Long getStocksUnitID() {
        return stocksUnitID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getMdd() {
        return mdd;
    }

    public void setMdd(Date mdd) {
        this.mdd = mdd;
    }

    public String getCommentSUnit() {
        return commentSUnit;
    }

    public void setCommentSUnit(String commentSUnit) {
        this.commentSUnit = commentSUnit;
    }

    public StocksArticle getStocksArticle() {
        return stocksArticle;
    }

    public void setStocksArticle(StocksArticle stocksArticle) {
        this.stocksArticle = stocksArticle;
    }
}
