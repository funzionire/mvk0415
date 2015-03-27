/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import static controller.BeanFactory.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import model.StocksUnit;
import org.junit.Test;
import static org.junit.Assert.*;
import controller.SessionBeanStocksArticleLocal;
import org.junit.BeforeClass;

/**
 *
 * @author ANABEL
 */
public class SessionBeanStocksArticleTest {
    static SessionBeanStocksArticleLocal beanArticle;
   
    
    @BeforeClass
    public static void setUpClass() throws Exception{
        
        beanArticle = new SessionBeanStocksArticle();
    }
   
    
    @Test
    public void testCompareMddWithCurrentDate() throws Exception {
        System.out.println("compareMddWithCurrentDate");
        
        
        String date = "29-03-2015";
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date mdd = sdf.parse(date);
        StocksUnit unit = new StocksUnit(5 , mdd, "abgelaufen");
        
        int expResult = 0;
        int result = beanArticle.compareMddWithCurrentDate(unit);
        assertEquals(expResult, result);
        
    }
    
}
