/*
Komponente zur Verwaltung der Entity-Klasse "Household" und "Place",
regelt die dafür benötigten Datenbankzugriffe
*/
package controller;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import model.Household;
import model.AppUser;
import model.Place;
import model.StocksArticle;

@Stateless
public class SessionBeanHousehold implements SessionBeanHouseholdLocal{
    
    @PersistenceContext
    private EntityManager em;

//------------------------------------------------------------------------------
// Household
//------------------------------------------------------------------------------
    
    @Override
    public Household createHousehold(String name) throws MVKException{
        try{
            em.setFlushMode(FlushModeType.AUTO);
            Household household = new Household(name);
            em.persist(household);
            em.flush();
            return household;
        }catch(Exception e){
             throw new MVKException("Fehler beim Anlegen des Haushalts");
        }
    }

    @Override
    public void deleteHousehold(Household household) throws MVKException{
        try {
            em.setFlushMode(FlushModeType.AUTO);
            Household foundHousehold = em.find(Household.class, household.getHouseholdID());
            if(foundHousehold != null){
                em.remove(foundHousehold);
                em.flush();
            }
        } catch (Exception e) {
            throw new MVKException("Fehler beim Löschen des Haushalts.");
        }
    }

    @Override
    public Household changeHousehold(Household household, String newName) throws MVKException{
        try{
            em.setFlushMode(FlushModeType.AUTO);
            if (newName != null) {
                household.setName(newName);
            }
            household = em.merge(household);
            em.flush();
            return household;
        } catch (Exception e) {
            throw new MVKException("Fehler beim Ändern des Haushalts.");
        }
    }

    @Override
    public Household findHousehold(long longID) throws MVKException{
        try{
            if (longID == 0) {
                return null;
            }
            TypedQuery<Household> query = em.createNamedQuery("Household.findById", Household.class)
                    .setParameter("householdID", longID);
            if (query.getResultList().isEmpty()) {
                return null;
            }
            return query.getSingleResult();
        } catch (Exception e) {
            throw new MVKException("Haushalt nicht gefunden.");
        }
    }
    
    
//------------------------------------------------------------------------------
// Place    
//------------------------------------------------------------------------------
    
    @Override
    public Place createPlace(String name, Household household) throws MVKException{
        try{
            em.setFlushMode(FlushModeType.AUTO);
            Place place = new Place(name, household);
            em.persist(place);
            place = em.merge(place);
            em.flush();
            return place;
        } catch (Exception e) {
            throw new MVKException("Fehler beim Anlegen des Lagerortes.");
        }
    }

    @Override
    public void deletePlace(Place place) throws MVKException{
        try {
            em.setFlushMode(FlushModeType.AUTO);
            Household foundPlace = em.find(Household.class, place.getPlaceID());
            if(foundPlace != null){
                em.remove(foundPlace);
                em.getTransaction().commit();
                em.flush();
            }
        } catch (Exception e) {
            throw new MVKException("Fehler beim Löschen des Lagerortes.");
        }
    }

    @Override
    public Place findPlace(long longID) throws MVKException{
        try{
            if (longID == 0) {
                return null;
            }
            TypedQuery<Place> query = em.createNamedQuery("Place.findById", Place.class)
                    .setParameter("placeID", longID);
            if (query.getResultList().isEmpty()) {
                return null;
            }
            Place place = query.getSingleResult();
            em.refresh(place);
            return place;
        } catch (Exception e) {
            throw new MVKException("Lagerort nicht gefunden.");
        }
    }
    
//------------------------------------------------------------------------------
// Verknüpfung User_Household   
//------------------------------------------------------------------------------
    @Override
    public boolean addUserToHousehold(Household household, AppUser user) throws MVKException{
        try {
            em.setFlushMode(FlushModeType.AUTO);
            em.flush();
            if (user != null) {
                household.getAppUserList().add(user);
            }
            em.merge(household);
            em.merge(user);
            em.flush();
            
            return true;
        } catch (Exception e) {
            throw new MVKException("Fehler beim Hinzufügen eines Users zum Haushalt.");
        }
    }

    @Override
    public Household removeUserFromHousehold(Household household, AppUser user) throws MVKException{
        try {
            em.getEntityManagerFactory().getCache().evictAll();
            em.setFlushMode(FlushModeType.AUTO);
            if (user != null) {
                em.flush();
                household = em.find(Household.class, household.getHouseholdID());
                household.getAppUserList().remove(user);
                em.flush();
            }
            return household;
        } catch (Exception e) {
            throw new MVKException("Fehler beim Entfernen eines Users vom Haushalt.");
        }
    }
    
//------------------------------------------------------------------------------
// Verknüpfung Household_Place
//------------------------------------------------------------------------------
    @Override
    public boolean addPlaceToHousehold(Household household, Place place) throws MVKException{
        try {
            em.setFlushMode(FlushModeType.AUTO);
            if (place != null) {
                household.getPlaceList().add(place);
            }
            em.merge(household);
            em.flush();
            return true;
        } catch (Exception e) {
            throw new MVKException("Fehler Anlegen des Lagerortes.");
        }
    }


    
//------------------------------------------------------------------------------
// Verknüpfung Place_StocksArticle
//------------------------------------------------------------------------------
    @Override
    public boolean addStocksArticleToPlace(Place place, StocksArticle stocksArticle) throws MVKException{
        try {
            em.setFlushMode(FlushModeType.AUTO);
            if (stocksArticle != null) {
                place.getStocksArticleList().add(stocksArticle);
            }
            em.merge(place);
            em.flush();
            return true;
        } catch (Exception e) {
            throw new MVKException("Fehler beim Anlegen des Vorratsartikels.");
        }
    }

    @Override
    public boolean removeStocksArticleFromPlace(Place place, StocksArticle stocksArticle) throws MVKException{
        try {
            em.setFlushMode(FlushModeType.AUTO);
            StocksArticle findStocksArticle = em.find(StocksArticle.class, stocksArticle.getStocksArticleID());
            if (findStocksArticle != null) {
                Place findPlace = em.find(Place.class, place.getPlaceID());
                findPlace.getStocksArticleList().remove(findStocksArticle);
                em.remove(findStocksArticle);
                em.flush();
            }
            
            return true;
        } catch (Exception e) {
            throw new MVKException("Fehler beim Entfernen des Vorratsartikels.");
        }
    }

    
    @Override
    public List<Household> getHouseholdsForUser(AppUser user) throws MVKException{
        try{
            return em.createNamedQuery("Household.findByUser", Household.class).getResultList();
        } catch (Exception e) {
            throw new MVKException("Fehler beim Suchen der User zum Haushalts.");
        }
    }

}
