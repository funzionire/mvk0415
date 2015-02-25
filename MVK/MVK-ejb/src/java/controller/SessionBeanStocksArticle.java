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
    public StocksArticle createArticle1(Long idArt, String nameArt, String commentArt, int placesReferenceId) {
        em.setFlushMode(FlushModeType.AUTO);
        StocksArticle stocksArticle = new StocksArticle(idArt, nameArt, commentArt, placesReferenceId);
        em.persist(stocksArticle);
        stocksArticle = em.merge(stocksArticle);
        em.flush();
        return stocksArticle;
    }

    @Override
    public StocksArticle createArticle2(Long idArt, String nameArt, int placesReferenceId) {
        em.setFlushMode(FlushModeType.AUTO);
        StocksArticle stocksArticle = new StocksArticle(idArt, nameArt, placesReferenceId);
        em.persist(stocksArticle);
        stocksArticle = em.merge(stocksArticle);
        em.flush();
        return stocksArticle;
    }

    @Override
    public StocksArticle deleteArticle(Long idArt) {
        em.setFlushMode(FlushModeType.AUTO);
        StocksArticle stocksArticle = getArticle(idArt);
        em.remove(stocksArticle);
        em.flush();
        return stocksArticle;
    }

    @Override
    public StocksArticle changeArticle(Long idArt, String nameArt, String commentArt) {
        em.setFlushMode(FlushModeType.AUTO);
        StocksArticle stocksArticle = getArticle(idArt);
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
    public StocksArticle moveArticle(Long idArt, int newPlacesReferenceId) {
        em.setFlushMode(FlushModeType.AUTO);
        StocksArticle stocksArticle = getArticle(idArt);
        stocksArticle.setPlacesReferenceId(newPlacesReferenceId);
        em.persist(stocksArticle);
        stocksArticle = em.merge(stocksArticle);
        em.flush();
        return stocksArticle;
    }

    // TODO:
    private StocksArticle getArticle(Long idArt) {
        return null;
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
