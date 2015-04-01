/*
Komponente zur Verwaltung der Klassen "SessionBeanStocksArticle" und "SessionBeanHousehold"
Die Datenbankzugriffe werden abstrahiert, wodurch ein Abstraktionsgrad innerhalb der Methoden entsteht.

Hier befindet sich die Anwendungslogik der Vorrats- und Bestandsverwaltung.

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

@Stateless
public class ManageBeanStocks implements ManageBeanStocksLocal {

    SessionBeanStocksArticleLocal sessionBeanStocksArticle = getSessionBeanStocksArticle();
    SessionBeanHouseholdLocal sessionBeanHousehold = getSessionBeanHousehold();

//------------------------------------------------------------------------------
// StocksArticle
//------------------------------------------------------------------------------ 
    @Override
    public StocksArticle addStocksArticle(String nameArt, Place place, String commentArt) throws MVKException{
        StocksArticle stocksArticle = sessionBeanStocksArticle.createStocksArticle(nameArt, place, commentArt);
        sessionBeanHousehold.addStocksArticleToPlace(place, stocksArticle);
        return stocksArticle;
    }

    @Override
    public boolean removeStocksArticle(StocksArticle stocksArticle) throws MVKException{
        Place place = stocksArticle.getPlace();
        return sessionBeanHousehold.removeStocksArticleFromPlace(place, stocksArticle);
    }

    @Override
    public StocksArticle findStocksArticle(String stringID) throws MVKException{
        long longID = Long.parseLong(stringID);
        return sessionBeanStocksArticle.findStocksArticle(longID);
    }

    @Override
    public StocksUnit findStocksUnit(String stringID) throws MVKException{
        long longID = Long.parseLong(stringID);
        return sessionBeanStocksArticle.findStocksUnit(longID);
    }
    
//    @Override
//    public boolean moveStocksArticle(StocksArticle stocksArticle, Place newPlace) throws MVKException{
//        boolean haveToCreateNewStocksArticle = true;
//        StocksArticle holdStocksArticle = null;
//
//        for (StocksArticle stocksArticle1 : newPlace.getStocksArticleList()) {
//            if (stocksArticle1.getName().equals(stocksArticle.getName())) {
//                haveToCreateNewStocksArticle = false;
//                holdStocksArticle = stocksArticle1;
//                break;
//            }
//        }
//        if (haveToCreateNewStocksArticle) {
//            stocksArticle.setPlace(newPlace);
//        } else {
//            for (StocksUnit stocksUnit : stocksArticle.getStocksUnitList()) {
//                sessionBeanStocksArticle.addStocksUnitToStocksArticle(holdStocksArticle, stocksUnit);
//            }
//        }
//        return true;
//    }

    @Override
    public int proofeMdd(StocksArticle stocksArticle) throws MVKException{
        if (stocksArticle.getStocksUnitList() == null){
            return 1;
        }
        int result = 1;
        for (StocksUnit unit : stocksArticle.getStocksUnitList()){
                if (sessionBeanStocksArticle.compareMddWithCurrentDate(unit) == -1){
                    return -1;
                }
                if (sessionBeanStocksArticle.compareMddWithCurrentDate(unit) == 0 ){
                    result = 0;
                }
        }
        if (result == 0){
            return 0;
        }
        return 1;
    }

    @Override
    public int sumUpQuantity(StocksArticle stocksArticle) throws MVKException {
        if (stocksArticle.getStocksUnitList() == null){
            return 0;
        }
        int result = 0;
        for (StocksUnit unit : stocksArticle.getStocksUnitList()){
                result = result + unit.getQuantity();
        }
        return result;
    }
    
    
    
//------------------------------------------------------------------------------    
// StocksUnit   
//------------------------------------------------------------------------------
    @Override
    public StocksUnit addStocksUnit(StocksArticle stocksArticle, String quantity, String mdd, String commentSUnit) throws MVKException{
        int intQuantity = Integer.parseInt(quantity);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dateMDD = null;
        try{    
            if (!mdd.equals("")){
                dateMDD = sdf.parse(mdd);
            }
        }catch(Exception e){
            throw new MVKException("Fehler beim Anlegen eines Vorratsartikels.");
        }
        if(intQuantity > 0){
            StocksUnit stocksUnit = sessionBeanStocksArticle.createStocksUnit(intQuantity, dateMDD, commentSUnit);
            sessionBeanStocksArticle.addStocksUnitToStocksArticle(stocksArticle, stocksUnit);
            return stocksUnit;
        }
        return null;
    }

    @Override
    public boolean removeStocksUnit(StocksUnit stocksUnit) throws MVKException{
        sessionBeanStocksArticle.deleteStocksUnit(stocksUnit);
        return true;
    }

//    @Override
//    public boolean moveStocksUnit(StocksUnit stocksUnit, Place newPlace, int newQuantity) throws MVKException{
//        boolean haveToCreateNewStocksArticle = true;
//
//        for (StocksArticle stocksArticle : newPlace.getStocksArticleList()) {
//            if (stocksArticle.getName().equals(stocksUnit.getStocksArticle().getName())) {
//                haveToCreateNewStocksArticle = false;
//                break;
//            }
//        }
//        if (haveToCreateNewStocksArticle) {
//            StocksArticle newStocksArticle = sessionBeanStocksArticle.createStocksArticle(stocksUnit.getStocksArticle().getName(), newPlace, stocksUnit.getStocksArticle().getComment());
//            sessionBeanStocksArticle.addStocksArticleToPlace(newPlace, newStocksArticle);
//        }
//        sessionBeanStocksArticle.createStocksUnit(newQuantity, stocksUnit.getMdd(), stocksUnit.getCommentSUnit());
//        sessionBeanStocksArticle.addStocksUnitToStocksArticle(stocksUnit.getStocksArticle(), stocksUnit);
//         
//        if (stocksUnit.getQuantity() == newQuantity) {
//            sessionBeanStocksArticle.deleteStocksUnit(stocksUnit);
//        }
//        if (stocksUnit.getQuantity() > newQuantity) {
//            int updateQuantity = stocksUnit.getQuantity() - newQuantity;
//            sessionBeanStocksArticle.changeStocksUnit(stocksUnit, stocksUnit.getMdd(), stocksUnit.getCommentSUnit(), updateQuantity);
//        }
//        return true;
//    }
    
    @Override
    public StocksUnit raiseQuantityOfStocksUnit(StocksUnit stocksUnit) throws MVKException{
        return sessionBeanStocksArticle.changeQuantityOfStocksUnit(stocksUnit, 1);
    }

    @Override
    public StocksUnit reduceQuantityOfStocksUnit(StocksUnit stocksUnit) throws MVKException{
        if(stocksUnit.getQuantity() > 1){
            return sessionBeanStocksArticle.changeQuantityOfStocksUnit(stocksUnit, -1);
        }
        if(stocksUnit.getQuantity() == 1){
            sessionBeanStocksArticle.deleteStocksUnit(stocksUnit);
        }
        return null;
    }
    
    
}
