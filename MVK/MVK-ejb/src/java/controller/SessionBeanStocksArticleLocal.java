/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.ejb.Local;
import model.Place;
import model.StocksArticle;

/**
 *
 * @author Felix
 */
@Local
public interface SessionBeanStocksArticleLocal {
    
    public StocksArticle createArticle1(String nameArt, String commentArt, Place newPlace);
    
    public StocksArticle createArticle2 (String nameArt, Place newPlace);
    
    public StocksArticle deleteArticle (Long idArt);
    
    public StocksArticle changeArticle (Long idArt, String nameArt, String commentArt);
    
    public StocksArticle moveArticle (Long idArt, Place newPlace);
    
    
}   
