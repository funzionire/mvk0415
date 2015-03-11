package model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author Felix
 */
@Entity
public class StocksUnit implements Serializable {

    //Attribute

    private int quantity;
    private String commentSUnit;
    private static final String defaultComment = "";
    private static final int defaultQuantity = 1;
    private static final Date defaultDate = null;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long stocksUnitID;
    @Temporal(TemporalType.DATE)
    private Date mdd;
    @ManyToOne
    private StocksArticle stocksArticle;

    //Konstruktoren in Reihenfolge quantity, mdd, commentSUnit
    //Konstruktor alles default
    public StocksUnit() {
        this.quantity = defaultQuantity;
        this.mdd = defaultDate;
        this.commentSUnit = defaultComment;
    }

    //Konstruktor M D C
    public StocksUnit(int quantity, Date mdd, String commentSUnit) {
        this.quantity = quantity;
        this.mdd = mdd;
        this.commentSUnit = commentSUnit;
    }

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

}
