package model;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Felix
 */
public class StocksUnit implements Serializable{
    
    private Long idSUnit;
    private int menge;
    private Date mdd;
    private Long idForeignArtID;
    private String commentSUnit;
    

    public StocksUnit() {

    }

    public StocksUnit(Long idSUnit, String commentSUnit, int menge, Date mdd, Long idForeignArtID) {
        this.idSUnit = idSUnit;
        this.menge = menge;
        this.mdd = mdd;
        this.idForeignArtID = idForeignArtID;
        this.commentSUnit = commentSUnit;
    }

    public Long getIdSUnit() {
        return idSUnit;
    }

    public int getMenge() {
        return menge;
    }

    public void setMenge(int menge) {
        this.menge = menge;
    }

    public Date getMdd() {
        return mdd;
    }

    public void setMdd(Date mdd) {
        this.mdd = mdd;
    }

    public Long getIdForeignArtID() {
        return idForeignArtID;
    }

    public void setIdForeignArtID(Long idForeignArtID) {
        this.idForeignArtID = idForeignArtID;
    }

    public void setIdSUnit(Long idSUnit) {
        this.idSUnit = idSUnit;
    }
    
    public String getCommentSUnit() {
        return commentSUnit;
    }

    public void setCommentSUnit(String commentSUnit) {
        this.commentSUnit = commentSUnit;
    }
}
