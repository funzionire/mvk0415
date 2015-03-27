/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import static controller.BeanFactory.getSessionBeanHousehold;
import static controller.BeanFactory.getSessionBeanStocksArticle;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.ejb.Stateless;
import model.Place;
import model.StocksArticle;
import model.StocksUnit;

/**
 *
 * @author Felix
 */
@Stateless
public class ManageBeanStocks implements ManageBeanStocksLocal {

    SessionBeanStocksArticleLocal sessionBeanStocksArticle = getSessionBeanStocksArticle();
    SessionBeanHouseholdLocal sessionBeanHousehold = getSessionBeanHousehold();

//------------------------------------------------------------------------------
    //-->StocksArticle
//------------------------------------------------------------------------------ 
    @Override
    public StocksArticle addStocksArticle(String nameArt, Place place, String commentArt) {
        //muss noch überprüft werden--> Kommentar(ob das so funzelt)
        try {
            StocksArticle stocksArticle = sessionBeanStocksArticle.createStocksArticle(nameArt, place, commentArt);
            sessionBeanHousehold.addStocksArticleToPlace(place, stocksArticle);
            return stocksArticle;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean removeStocksArticle(StocksArticle stocksArticle) {
        try {
            Place place = stocksArticle.getPlace();
            sessionBeanHousehold.removeStocksArticleFromPlace(place, stocksArticle);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public StocksArticle findStocksArticle(String stringID) {
        //Datentyp-Umwandlung
        long longID = Long.parseLong(stringID);
        return sessionBeanStocksArticle.findStocksArticle(longID);
    }

    @Override
    public StocksUnit findStocksUnit(String stringID) {
        long longID = Long.parseLong(stringID);
        return sessionBeanStocksArticle.findStocksUnit(longID);
    }
    
    
    
        @Override
    public boolean moveStocksArticle(StocksArticle stocksArticle, Place newPlace) {
        try {
            boolean haveToCreateNewStocksArticle = true;
            StocksArticle holdStocksArticle = null;

            for (StocksArticle stocksArticle1 : newPlace.getStocksArticleList()) {
                if (stocksArticle1.getName().equals(stocksArticle.getName())) {
                    haveToCreateNewStocksArticle = false;
                    holdStocksArticle = stocksArticle1;
                    break;
                }
            }
            if (haveToCreateNewStocksArticle) {
                stocksArticle.setPlace(newPlace);
            } else {
                for (StocksUnit stocksUnit : stocksArticle.getStocksUnitList()) {
                    sessionBeanStocksArticle.addStocksUnitToStocksArticle(holdStocksArticle, stocksUnit);
                }
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public int proofeMdd(StocksArticle stocksArticle) {
        System.out.println("Hilfe" + stocksArticle.getName());
        if (stocksArticle.getStocksUnitList() == null){
            return 1;
        }
        int result;
        for (StocksUnit unit : stocksArticle.getStocksUnitList()){
            if (unit.getMdd() != null){
                result = sessionBeanStocksArticle.compareMddWithCurrentDate(unit);
                if (result == -1 || result == 0)
                    return result;
            }
        }
        return 1;
    }
    
    
    
//------------------------------------------------------------------------------    
    //-->StocksUnit   
//------------------------------------------------------------------------------
    @Override
    public StocksUnit addStocksUnit(StocksArticle stocksArticle, String quantity, String mdd, String commentSUnit) {
        try {
            //Datentyp-Umwandlungen
            int intQuantity = Integer.parseInt(quantity);
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                Date dateMDD = null;
                try{    
                     dateMDD = sdf.parse(mdd);
                }catch(Exception e){
                    e.printStackTrace();
                }
            //fliegt er so schon raus in den catch also wenn quantity >0?
            if(intQuantity > 0){
            StocksUnit stocksUnit = sessionBeanStocksArticle.createStocksUnit(intQuantity, dateMDD, commentSUnit);
            sessionBeanStocksArticle.addStocksUnitToStocksArticle(stocksArticle, stocksUnit);
            return stocksUnit;
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean removeStocksUnit(StocksUnit stocksUnit) {
        try {
            sessionBeanStocksArticle.deleteStocksUnit(stocksUnit);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean moveStocksUnit(StocksUnit stocksUnit, Place newPlace, int newQuantity) {
        //toDO hier muss noch durchittariert werden ob es schon eine gleichen StocksArticle gibt!
        //für die Kontrolle, ob ein neuer Article in dem neuen Platz angelegt werden muss;
        try {
            boolean haveToCreateNewStocksArticle = true;

            for (StocksArticle stocksArticle : newPlace.getStocksArticleList()) {
                if (stocksArticle.getName().equals(stocksUnit.getStocksArticle().getName())) {
                    haveToCreateNewStocksArticle = false;
                    break;
                }
            }
            if (haveToCreateNewStocksArticle) {
                StocksArticle newStocksArticle = sessionBeanStocksArticle.createStocksArticle(stocksUnit.getStocksArticle().getName(), newPlace, stocksUnit.getStocksArticle().getComment());
                sessionBeanStocksArticle.addStocksArticleToPlace(newPlace, newStocksArticle);
            }
            sessionBeanStocksArticle.createStocksUnit(newQuantity, stocksUnit.getMdd(), stocksUnit.getCommentSUnit());
            sessionBeanStocksArticle.addStocksUnitToStocksArticle(stocksUnit.getStocksArticle(), stocksUnit);

            //notwendige Änderungen an der bestehenden StocksUnit
            if (stocksUnit.getQuantity() == newQuantity) {
                sessionBeanStocksArticle.deleteStocksUnit(stocksUnit);
            }
            if (stocksUnit.getQuantity() > newQuantity) {
                int updateQuantity = stocksUnit.getQuantity() - newQuantity;
                sessionBeanStocksArticle.changeStocksUnit(stocksUnit, stocksUnit.getMdd(), stocksUnit.getCommentSUnit(), updateQuantity);
            } else {
                //toDo --> error abfangen!
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
        //-->Veränderungen an der Quantity eines StocksUnit
    
    @Override
    public StocksUnit raiseQuantityOfStocksUnit(StocksUnit stocksUnit) {
        try{
            //Soll es auch möglich sein eine Vorauswahl zu haben (z.B. 10 für 10 Eier mehr
            return sessionBeanStocksArticle.changeQuantityOfStocksUnit(stocksUnit, 1);
        } catch (Exception e) {
            return null;   
        }
    }

    @Override
    public StocksUnit reduceQuantityOfStocksUnit(StocksUnit stocksUnit) {
        try{
            if(stocksUnit.getQuantity() > 1){
               return sessionBeanStocksArticle.changeQuantityOfStocksUnit(stocksUnit, -1);
            }
            if(stocksUnit.getQuantity() == 1){
                //???Frage stellen: wirklich löschen??
               sessionBeanStocksArticle.deleteStocksUnit(stocksUnit);
            }
            return null;
        } catch (Exception e) {
            return null;   
        }
    }
    
    
}
