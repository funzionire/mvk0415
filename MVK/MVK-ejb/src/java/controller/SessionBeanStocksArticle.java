/*
Komponente zur Verwaltung der Entity-Klassen "StocksArticle" und "StocksUnit",
regelt die dafür benötigten Datenbankzugriffe
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

@Stateless(name = "SessionBeanArticle")
public class SessionBeanStocksArticle implements SessionBeanStocksArticleLocal {

    @PersistenceContext
    private EntityManager em;

//------------------------------------------------------------------------------
    //-->StocksArticle
//------------------------------------------------------------------------------
    
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
    public void deleteStocksArticle(StocksArticle stocksArticle) {
        try {
            em.setFlushMode(FlushModeType.AUTO);
            System.out.println(stocksArticle.getStocksArticleID());
            Household foundStocksArticle = em.find(Household.class, stocksArticle.getStocksArticleID());
            if(foundStocksArticle != null){
                System.out.println("success");
                em.remove(foundStocksArticle);
                em.getTransaction().commit();
                em.flush();
            }else{
                System.out.println("error");
            }
        } catch (Exception e) {
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
    
//------------------------------------------------------------------------------
    //-->StocksUnit
//------------------------------------------------------------------------------
    
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
    public void deleteStocksUnit(StocksUnit stocksUnit) {
        try {
            em.getEntityManagerFactory().getCache().evictAll();
            em.setFlushMode(FlushModeType.AUTO);
            System.out.println(stocksUnit.getStocksUnitID());
            StocksUnit foundStocksUnit = em.find(StocksUnit.class, stocksUnit.getStocksUnitID());
            if(foundStocksUnit != null){
                System.out.println("success");
                em.remove(foundStocksUnit);
                em.flush();
            }else{
                System.out.println("error");
            }
        } catch (Exception e) {
        }
    }
    
    @Override
    public StocksUnit findStocksUnit(long longID) {
        if (longID == 0) {
            return null;
        }
        TypedQuery<StocksUnit> query = em.createNamedQuery("StocksUnit.findById", StocksUnit.class)
                .setParameter("stocksUnitID", longID);
        if (query.getResultList().isEmpty()) {
            return null;
        }
        return query.getSingleResult();
    }

    
//------------------------------------------------------------------------------
    //-->Beziehungen
//------------------------------------------------------------------------------

    @Override
    public boolean addStocksUnitToStocksArticle(StocksArticle stocksArticle, StocksUnit stocksUnit) {
        try {
            em.setFlushMode(FlushModeType.AUTO);
            if (stocksUnit != null && stocksArticle != null) {
                stocksArticle.getStocksUnitList().add(stocksUnit);
            }
            em.merge(stocksArticle);
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
    public StocksUnit changeQuantityOfStocksUnit(StocksUnit stocksUnit, int change) {
        try {
            em.setFlushMode(FlushModeType.AUTO);
            stocksUnit.setQuantity(stocksUnit.getQuantity() + change);
            stocksUnit = em.merge(stocksUnit);
            em.flush();
            return stocksUnit;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public int compareMddWithCurrentDate(StocksUnit stocksUnit) {
        Date currentDate = new Date();
        Date mdd = stocksUnit.getMdd();
        //Ohne Mdd wird der Artikel behandelt als wäre er haltbar
        if (mdd == null){
            return 1;
        }
        long dreiTage = 259200000 ; //Millisekunden
        long difference = mdd.getTime() - currentDate.getTime();
        
        //nicht haltbar
        if ( difference <= 0 ){
            return -1 ;
        }
        //läuft bald ab
        else if ( difference < dreiTage){
            return 0;
        }
        //haltbar
        else return 1;
    }
}
