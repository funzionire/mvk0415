/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.ejb.embeddable.EJBContainer;
import static jdk.nashorn.internal.objects.Global.instance;
import model.Place;
import model.StocksArticle;
import model.StocksUnit;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ANABEL
 */
public class SessionBeanStocksArticleTest {
    
    public SessionBeanStocksArticleTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    

    @Test
    public void testCompareMddWithCurrentDate() throws Exception {
        System.out.println("compareMddWithCurrentDate");
        String date = "26-03-2015";
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date mdd = sdf.parse(date);
        StocksUnit unit = new StocksUnit(5 , mdd, "abgelaufen");
        
        SessionBeanStocksArticleLocal sessionBeanStocksArticle = BeanFactory.getSessionBeanStocksArticle();
        
        int expResult = 0;
        int result = sessionBeanStocksArticle.compareMddWithCurrentDate(unit);
        assertEquals(expResult, result);

    }
    
}
