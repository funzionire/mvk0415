/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import static com.sun.xml.ws.dump.LoggingDumpTube.Position.After;
import static com.sun.xml.ws.dump.LoggingDumpTube.Position.Before;
import javax.ejb.embeddable.EJBContainer;
import model.StocksArticle;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ANABEL
 */
public class ManageBeanStocksTest {
    
    public ManageBeanStocksTest() {
    }
    
    
    @Before
    public void setUp() {
         
    }
    
    
    
    @Test
    public void testProofeMdd() throws Exception {
        System.out.println("proofeMdd");
        ManageBeanStocksLocal manageBeanStocks = BeanFactory.getManageBeanStocks();
        StocksArticle article = new StocksArticle("milch", null , "");
        manageBeanStocks.addStocksUnit(article, "5" , "27-03-2015" , "");
      
        boolean expResult = true;
        boolean result = manageBeanStocks.proofeMdd(article);
        assertEquals(expResult, result);

    }
    
    @After
    public void tearDown() {
    }
    
}
