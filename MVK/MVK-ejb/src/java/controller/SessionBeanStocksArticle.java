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
    public StocksArticle createArticle(String nameArt, Place newPlace, String commentArt) {
        em.setFlushMode(FlushModeType.AUTO);
        StocksArticle stocksArticle = new StocksArticle(nameArt, newPlace, commentArt);
        em.persist(stocksArticle);
        stocksArticle = em.merge(stocksArticle);
        em.flush();
        return stocksArticle;
    }

    @Override
    public StocksArticle createArticle(String nameArt, Place newPlace) {
        em.setFlushMode(FlushModeType.AUTO);
        StocksArticle stocksArticle = new StocksArticle(nameArt, newPlace);
        em.persist(stocksArticle);
        stocksArticle = em.merge(stocksArticle);
        em.flush();
        return stocksArticle;
    }

    @Override
    public boolean deleteArticle(StocksArticle stocksArticle) {
        try {
            em.setFlushMode(FlushModeType.AUTO);
            em.remove(stocksArticle);
            em.flush();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //??? soll hier auch Place verändert werden können? --> move gibt es ja bereits schon!
    @Override
    public StocksArticle changeArticle(StocksArticle stocksArticle, String nameArt, String commentArt) {
        em.setFlushMode(FlushModeType.AUTO);
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
    public StocksArticle changeArticle(StocksArticle stocksArticle, String nameArt) {
        em.setFlushMode(FlushModeType.AUTO);
        stocksArticle.setName(nameArt);
        em.persist(stocksArticle);
        stocksArticle = em.merge(stocksArticle);
        em.flush();
        return stocksArticle;
    }

    @Override
    public StocksArticle changeArticleComment(StocksArticle stocksArticle, String commentArt) {
        em.setFlushMode(FlushModeType.AUTO);
        stocksArticle.setComment(commentArt);
        em.persist(stocksArticle);
        stocksArticle = em.merge(stocksArticle);
        em.flush();
        return stocksArticle;
    }

    @Override
    public boolean moveArticle(StocksArticle stocksArticle, Place newPlace) {
        try{
        em.setFlushMode(FlushModeType.AUTO);
        stocksArticle.setPlace(newPlace);
        em.persist(stocksArticle);
        stocksArticle = em.merge(stocksArticle);
        em.flush();
        return true;
        }
        catch (Exception e){
            return false;
        }
    }


     
}
