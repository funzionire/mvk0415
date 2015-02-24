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
    private int StocksArticleReferenceId;
    
    private String defaultComment = "";
    

    public StocksUnit() {

    }

    public StocksUnit(Long idSUnit, int menge, Date mdd, Long idForeignArtID, String commentSUnit, int StocksArticleReferenceId) {
        this.idSUnit = idSUnit;
        this.menge = menge;
        this.mdd = mdd;
        this.idForeignArtID = idForeignArtID;
        this.commentSUnit = commentSUnit;
    }
    
    public StocksUnit(Long idSUnit, int menge, Date mdd, Long idForeignArtID, int StocksArticleReferenceId) {
        this.idSUnit = idSUnit;
        this.menge = menge;
        this.mdd = mdd;
        this.idForeignArtID = idForeignArtID;
        this.commentSUnit = defaultComment;
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

    public int getStocksArticleReferenceId() {
        return StocksArticleReferenceId;
    }

    public void setStocksArticleReferenceId(int StocksArticleReferenceId) {
        this.StocksArticleReferenceId = StocksArticleReferenceId;
    }
    
    
}
