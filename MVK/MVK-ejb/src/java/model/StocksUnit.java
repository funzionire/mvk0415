package model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author Felix
 */
@Entity
public class StocksUnit implements Serializable{
    @Id
    @GeneratedValue
    private long idSUnit;
    @OneToOne
    private int quantity;
    private Date mdd;
    private String commentSUnit;
    private static final String defaultComment = "";
    private static final int defaultQuantity = 1;
    private static final Date defaultDate = null;
    private StocksArticle stocksArticle;
    
    //TODO Konstrutoren für keine Menge (=1) und keine Menge + kein Datum und kein Datum
    
    public StocksUnit(int quantity, Date mdd, String commentSUnit) {
        this.quantity = quantity;
        this.mdd = mdd;
        this.commentSUnit = commentSUnit;
    }
    
    public StocksUnit(int quantity, Date mdd) {
        this.quantity = quantity;
        this.mdd = mdd;
        this.commentSUnit = defaultComment;
    }
    

    public Long getIdSUnit() {
        return idSUnit;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public int quantityPlusOne(){
        return ++quantity;
    }

     public int quantityMinnusOne(){
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
