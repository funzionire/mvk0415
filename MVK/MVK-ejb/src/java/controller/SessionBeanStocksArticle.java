/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;
import javax.persistence.PersistenceContext;
import model.Place;
import model.StocksArticle;

/**
 *
 * @author Felix
 */
@Stateless(name = "SessionBeanArticle")
public class SessionBeanStocksArticle implements SessionBeanStocksArticleLocal {

    @PersistenceContext
    private EntityManager em;

    @Override
    public StocksArticle createArticle1(String nameArt, String commentArt, Place newPlace) {
        em.setFlushMode(FlushModeType.AUTO);
        StocksArticle stocksArticle = new StocksArticle(nameArt, newPlace, commentArt);
        em.persist(stocksArticle);
        stocksArticle = em.merge(stocksArticle);
        em.flush();
        return stocksArticle;
    }

    @Override
    public StocksArticle createArticle2(String nameArt, Place newPlace) {
        em.setFlushMode(FlushModeType.AUTO);
        StocksArticle stocksArticle = new StocksArticle(nameArt, newPlace);
        em.persist(stocksArticle);
        stocksArticle = em.merge(stocksArticle);
        em.flush();
        return stocksArticle;
    }

    @Override
    public StocksArticle deleteArticle(long stocksArticleID) {
        em.setFlushMode(FlushModeType.AUTO);
        StocksArticle stocksArticle = em.find(StocksArticle.class, stocksArticleID);
        em.remove(stocksArticle);
        em.flush();
        return stocksArticle;
    }

    //??? soll hier auch Place verändert werden können? --> move gibt es ja bereits schon!
    @Override
    public StocksArticle changeArticle(long stocksArticleID, String nameArt, String commentArt) {
        em.setFlushMode(FlushModeType.AUTO);
        StocksArticle stocksArticle = em.find(StocksArticle.class, stocksArticleID);
        if (nameArt != null) {
            stocksArticle.setName(nameArt);
        }
        if (commentArt != null) {
            stocksArticle.setComment(commentArt);
        }
        em.persist(stocksArticle);
        stocksArticle = em.merge(stocksArticle);
        em.flush();
        return stocksArticle;
    }

    @Override
    public StocksArticle moveArticle(long stocksArticleID, Place newPlace) {
        em.setFlushMode(FlushModeType.AUTO);
        StocksArticle stocksArticle = em.find(StocksArticle.class, stocksArticleID);
        stocksArticle.setPlace(newPlace);
        em.persist(stocksArticle);
        stocksArticle = em.merge(stocksArticle);
        em.flush();
        return stocksArticle;
    }

    /*unnötig
     private StocksArticle getArticle(long stocksArticleID) {
     return null;
     }
     // Add business logic below. (Right-click in editor and choose
     // "Insert Code > Add Business Method")
     */
}
