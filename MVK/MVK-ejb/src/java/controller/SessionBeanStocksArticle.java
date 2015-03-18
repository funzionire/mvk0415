/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import model.Household;
import model.Place;
import model.StocksArticle;
import model.StocksUnit;

/**
 *
 * @author Felix
 */
@Stateless(name = "SessionBeanArticle")
public class SessionBeanStocksArticle implements SessionBeanStocksArticleLocal {

    @PersistenceContext
    private EntityManager em;

    @Override
    public StocksArticle createStocksArticle(String nameArt, Place place, String commentArt) {
        try {
            if (place != null) {
                em.setFlushMode(FlushModeType.AUTO);
                StocksArticle stocksArticle = new StocksArticle(nameArt, place, commentArt);
                em.persist(stocksArticle);
                stocksArticle = em.merge(stocksArticle);
                em.flush();
                return stocksArticle;
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean deleteStocksArticle(StocksArticle stocksArticle) {
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
    public StocksArticle changeStocksArticle(StocksArticle stocksArticle, String nameArt, String commentArt) {
        em.setFlushMode(FlushModeType.AUTO);
        if (nameArt != null) {
            stocksArticle.setName(nameArt);
        }
        if (commentArt != null) {
            stocksArticle.setComment(commentArt);
        }
        stocksArticle = em.merge(stocksArticle);
        em.flush();
        return stocksArticle;
    }

    @Override
    public boolean moveStocksArticle(StocksArticle stocksArticle, Place newPlace) {
        try {
            em.setFlushMode(FlushModeType.AUTO);
            stocksArticle.setPlace(newPlace);
            em.persist(stocksArticle);
            stocksArticle = em.merge(stocksArticle);
            em.flush();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public StocksArticle findStocksArticle(long longID) {
        if (longID == 0) {
            return null;
        }
        TypedQuery<StocksArticle> query = em.createNamedQuery("StocksArticle.findById", StocksArticle.class)
                .setParameter("stocksArticleID", longID);
        if (query.getResultList().isEmpty()) {
            return null;
        }
        return query.getSingleResult();
    }

    @Override
    public StocksUnit createStocksUnit(int quantity, Date mdd, String commentSUnit) {
        em.setFlushMode(FlushModeType.AUTO);
        StocksUnit stocksUnit = new StocksUnit(quantity, mdd, commentSUnit);
        em.persist(stocksUnit);
        stocksUnit = em.merge(stocksUnit);
        em.flush();
        return stocksUnit;
    }

    @Override
    public StocksUnit changeStocksUnit(StocksUnit stocksUnit, Date mdd, String commentSUnit, int updateQuantity) {
        em.setFlushMode(FlushModeType.AUTO);
        if (mdd != null) {
            stocksUnit.setMdd(mdd);
        }
        if (commentSUnit != null) {
            stocksUnit.setCommentSUnit(commentSUnit);
        }
        //muss noch ausprobiert werden?? brauchen wir allerdings für moveStocksUnit!!
        if (updateQuantity == 0) {
            stocksUnit.setQuantity(updateQuantity);
        }
        stocksUnit = em.merge(stocksUnit);
        em.flush();
        return stocksUnit;
    }

    @Override
    public boolean deleteStocksUnit(StocksUnit stocksUnit) {
        try {
            em.setFlushMode(FlushModeType.AUTO);
            em.remove(stocksUnit);
            em.flush();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean addStocksUnitToStocksArticle(StocksArticle stocksArticle, StocksUnit stocksUnit) {
        try {
            em.setFlushMode(FlushModeType.AUTO);
            if (stocksUnit != null && stocksArticle != null) {
                stocksArticle.getStocksUnitList().add(stocksUnit);
            }
            stocksArticle = em.merge(stocksArticle);
            em.flush();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean removeStocksUnitFromStocksArticle(StocksArticle stocksArticle, StocksUnit stocksUnit) {
        try {
            em.setFlushMode(FlushModeType.AUTO);
            if (stocksUnit != null && stocksArticle != null) {
                stocksArticle.getStocksUnitList().remove(stocksUnit);
            }
            stocksArticle = em.merge(stocksArticle);
            em.flush();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean addStocksArticleToPlace(Place place, StocksArticle stocksArticle) {
        try {
            em.setFlushMode(FlushModeType.AUTO);
            if (place != null && stocksArticle != null) {
                place.getStocksArticleList().add(stocksArticle);
            }
            place = em.merge(place);
            em.flush();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean removeStocksArticleFromPlace(Place place, StocksArticle stocksArticle) {
        try {
            em.setFlushMode(FlushModeType.AUTO);
            if (place != null && stocksArticle != null) {
                place.getStocksArticleList().remove(stocksArticle);
            }
            place = em.merge(place);
            em.flush();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean changePlaceFromStocksArticle(Place place, StocksArticle stocksArticle) {
        try {
            em.setFlushMode(FlushModeType.AUTO);
            if (place != null && stocksArticle != null) {
                stocksArticle.setPlace(place);
                //???falls gewollt???
                removeStocksArticleFromPlace(place, stocksArticle);
            }
            place = em.merge(place);
            em.flush();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean changeQuantityOfStocksUnit(StocksUnit stocksUnit, int change) {
        try {
            //Soll es auch möglich sein eine Vorauswahl zu haben (z.B. 10 für 10 Eier mehr)
            stocksUnit.setQuantity(stocksUnit.getQuantity() + change);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
