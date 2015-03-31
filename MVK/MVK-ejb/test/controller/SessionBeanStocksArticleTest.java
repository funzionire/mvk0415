package controller;

import java.util.Date;
import model.StocksUnit;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;


/*Testklasse zum Test der Methode "CompareMddWithCurrentDate"
  aus der Klasse SessionBeanStocksArticle
  Mehrere Testfälle wurden definiert
*/
public class SessionBeanStocksArticleTest {
    static SessionBeanStocksArticleLocal beanArticle;
    static long oneDay = 86400000; //Millisekunden
    static long now = new Date().getTime();
    
    @BeforeClass
    public static void setUpClass() throws Exception{
        beanArticle = new SessionBeanStocksArticle();
    }
   
    
    //1.Testfall: Heutiges Datum
    @Test
    public void testCompareMddWithCurrentDate() throws Exception {
        Date today = new Date();
        StocksUnit unit = new StocksUnit (5, today , "heute abgelaufen");
        
        int expResult = -1;
        int result = beanArticle.compareMddWithCurrentDate(unit);
        assertEquals(expResult, result);
    }
    
    //2.Testfall: Datum von morgen
    @Test
    public void testCompareMddWithCurrentDate2() throws Exception{
        Date tomorrow = new Date();
        tomorrow.setTime( now + oneDay);
        StocksUnit unit = new StocksUnit (5, tomorrow, "läuft morgen ab");
        
        int expResult = 0;
        int result = beanArticle.compareMddWithCurrentDate(unit);
        assertEquals(expResult, result);
        
    }
    
    //3.Testfall: Datum von gestern
    @Test
    public void testCompareMddWithCurrentDate3() throws Exception{
        Date yesterday = new Date();
        yesterday.setTime(now - oneDay);
        StocksUnit unit = new StocksUnit (5, yesterday, "abgelaufen");
        
        int expResult = -1;
        int result = beanArticle.compareMddWithCurrentDate(unit);
        assertEquals(expResult, result);
        
    }
    
    //4.Testfall: Datum weit in der Zukunft
    @Test
    public void testCompareMddWithCurrentDate4() throws Exception{
        Date future = new Date();
        future.setTime(now + 10 * oneDay);
        StocksUnit unit = new StocksUnit (5, future, "haltbar");
        
        int expResult = 1;
        int result = beanArticle.compareMddWithCurrentDate(unit);
        assertEquals(expResult, result);
    }
    
    //5.Testfall: kein Datum angegeben
    @Test
    public void testCompareMddWithCurrentDate5() throws Exception{
        StocksUnit unit = new StocksUnit(5, null, "Datum fehlt");
        
        int expResult = 1;
        int result = beanArticle.compareMddWithCurrentDate(unit);
        assertEquals(expResult, result);
    }
}
