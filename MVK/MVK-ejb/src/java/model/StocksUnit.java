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
    private int quantity;
    private String commentSUnit;
    private static final String defaultComment = "";
    private static final int defaultQuantity = 1;
    private static final Date defaultDate = null;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long StocksUnitID;
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

    //Konstruktor M
    public StocksUnit(int quantity) {
        this.quantity = quantity;
        this.mdd = defaultDate;
        this.commentSUnit = defaultComment;
    }

    //Konstruktor M D
    public StocksUnit(int quantity, Date mdd) {
        this.quantity = quantity;
        this.mdd = mdd;
        this.commentSUnit = defaultComment;
    }

    //Konstruktor M C

    public StocksUnit(int quantity, String commentSUnit) {
        this.quantity = quantity;
        this.mdd = defaultDate;
        this.commentSUnit = commentSUnit;
    }

    //Konstruktor M D C

    public StocksUnit(int quantity, Date mdd, String commentSUnit) {
        this.quantity = quantity;
        this.mdd = mdd;
        this.commentSUnit = commentSUnit;
    }

    //Konstruktor D

    public StocksUnit(Date mdd) {
        this.quantity = defaultQuantity;
        this.mdd = mdd;
        this.commentSUnit = defaultComment;
    }

    //Konstruktor D C

    public StocksUnit(Date mdd, String commentSUnit) {
        this.quantity = defaultQuantity;
        this.mdd = mdd;
        this.commentSUnit = commentSUnit;
    }

    //Konstruktor C

    public StocksUnit(String commentSUnit) {
        this.quantity = defaultQuantity;
        this.mdd = defaultDate;
        this.commentSUnit = commentSUnit;
    }

    public Long getStocksUnitID() {
        return StocksUnitID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int quantityPlusOne() {
        return ++quantity;
    }

    public int quantityMinusOne() {
        return --quantity;
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
