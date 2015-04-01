/*
Entity-Klasse "Place" stellt die Entity Place - Lagerort dar 
und das damit verbundene Place Objekt in der Datenbank.
*/
package model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;


@Entity
@NamedQueries(
        {
            @NamedQuery(name = "Place.findById", query = "SELECT place FROM Place place WHERE place.placeID = :placeID")
        })
public class Place implements Serializable {

    //Attribute
    private String name;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long placeID;
    @ManyToOne
    private Household household;
    @OneToMany(cascade=CascadeType.ALL)
    private List<StocksArticle> stocksArticles;

    //Konstruktoren
    public Place() {
    }

    public Place(String name, Household household) {
        this.name = name;
        this.household = household;
    }

    //Getter und Setter
    public long getPlaceID() {
        return placeID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Household getHousehold() {
        return household;
    }

    public void setHousehold(Household household) {
        this.household = household;
    }
    
    public List<StocksArticle> getStocksArticleList(){
        return stocksArticles;
    }
}
