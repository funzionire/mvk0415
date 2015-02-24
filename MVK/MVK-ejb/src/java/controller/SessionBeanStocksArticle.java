/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.ejb.Stateless;
import model.StocksArticle;

/**
 *
 * @author Felix
 */
@Stateless
public class SessionBeanStocksArticle implements SessionBeanStocksArticleLocal {

    @Override
    public StocksArticle createArticle1(Long idArt, String nameArt, String commentArt, int placesReferenceId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public StocksArticle createArticle2(Long idArt, String nameArt, int placesReferenceId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public StocksArticle deleteArticle(Long idArt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public StocksArticle changeArticle(Long idArt, String nameArt, String commentArt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public StocksArticle moveArticle(Long idArt, int newPlacesReferenceId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
