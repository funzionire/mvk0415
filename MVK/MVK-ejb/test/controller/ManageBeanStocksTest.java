/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import static com.sun.xml.ws.dump.LoggingDumpTube.Position.After;
import static com.sun.xml.ws.dump.LoggingDumpTube.Position.Before;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.ejb.embeddable.EJBContainer;
import model.StocksArticle;
import model.StocksUnit;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

/**
 *
 * @author ANABEL
 */
public class ManageBeanStocksTest {
    
  static ManageBeanStocksLocal manageBeanStocks;
   
    
    @BeforeClass
    public static void setUpClass() throws Exception{
        
        manageBeanStocks = new ManageBeanStocks();
    }
    
    
    
    @Test
    public void testProofeMdd() throws Exception {
        System.out.println("proofeMdd");
        
        StocksArticle article = new StocksArticle("milch", null , "");
        
        String date = "30-03-2015";
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date mdd = sdf.parse(date);
        StocksUnit unit = new StocksUnit(5 , mdd, "abgelaufen");
        
        article.getStocksUnitList().add(unit);
        
        
      
        int expResult = -1 ;
        int result = manageBeanStocks.proofeMdd(article);
        assertEquals(expResult, result);

    }
    
    @After
    public void tearDown() {
    }
    
}
