/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.Date;
import javax.ejb.Local;
import model.Place;
import model.StocksArticle;
import model.StocksUnit;

/**
 *
 * @author Felix
 */
@Local
public interface SessionBeanStocksArticleLocal {

    public StocksArticle createArticle(String nameArt, Place newPlace, String commentArt);
    //|->soll ein stocksArticle-Objekt erzeugen (mit Kommentar) und in der Datenbank abspreichern

    public StocksArticle createArticle(String nameArt, Place newPlace);
    //|->soll ein stocksArticle-Objekt erzeugen (ohne Kommentar) und in der Datenbank abspreichern

    public boolean removeArticle(StocksArticle stocksArticle);
    //|->soll ein stocksArticle-Objekt löschen

    public StocksArticle changeArticle(StocksArticle stocksArticle, String nameArt, String commentArt);
    //|->soll die Name und KOmmentar eines article-Objekts verändern

    public StocksArticle changeArticle(StocksArticle stocksArticle, String nameArt);
    //|->soll Name eines article-Objekts verändern

    public StocksArticle changeArticleComment(StocksArticle stocksArticle, String commentArt);
    //|->soll Kommentar eines article-Objekts verändern

    public boolean moveArticle(StocksArticle stocksArticle, Place newPlace);
    //|->soll ein article-Objekt einem andere Platz zuordnen (das place-Attribut verändern)

//-->TODO (in dieser bean?) addUnit, removeUnit, changeUnit, moveUnit,
    public StocksUnit addUnit(int quantity);

    public StocksUnit addUnit(int quantity, Date mdd);

    public StocksUnit addUnit(int quantity, String commentSUnit);

    public StocksUnit addUnit(int quantity, Date mdd, String commentSUnit);

    public StocksUnit addUnit(Date mdd);

    public StocksUnit addUnit(Date mdd, String commentSUnit);

    public StocksUnit addUnit(String commentSUnit);

    //fragt sich ob man das so will.../notwendig (Abfrage == null?) -->Ausprobieren
    public StocksUnit changeUnit(int quantity);

    public StocksUnit changeUnit(int quantity, Date mdd);

    public StocksUnit changeUnit(int quantity, String commentSUnit);

    public StocksUnit changeUnit(int quantity, Date mdd, String commentSUnit);

    public StocksUnit changeUnit(Date mdd);

    public StocksUnit changeUnit(Date mdd, String commentSUnit);

    public StocksUnit changeUnit(String commentSUnit);

    public StocksUnit removeUnit(StocksUnit stocksUnit);

    public StocksUnit moveUnit(StocksUnit stocksUnit, Place newPlace);

}
