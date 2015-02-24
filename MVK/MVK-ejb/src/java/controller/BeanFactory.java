/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author Felix
 */
public class BeanFactory {
    public static SessionBeanHouseholdLocal getSessionBeanHousehold()
    {
        SessionBeanHouseholdLocal bean = null;
        try
        {
            InitialContext ctx = new InitialContext();
            bean = (SessionBeanHouseholdLocal) ctx.lookup("java:global/EnterpriseApplication/EnterpriseApplication-ejb/SessionBean!controller.SessionBeanHouseholdLocal");
        }
        catch (NamingException ex)
        {
            ex.printStackTrace();
        }
        return bean;
    }
    
    public static SessionBeanStocksArticleLocal getSessionBeanStocksArticle()
    {
        SessionBeanStocksArticleLocal bean = null;
        try
        {
            InitialContext ctx = new InitialContext();
            bean = (SessionBeanStocksArticleLocal) ctx.lookup("java:global/EnterpriseApplication/EnterpriseApplication-ejb/SessionBean!controller.SessionBeanStocksArticleLocal");
        }
        catch (NamingException ex)
        {
            ex.printStackTrace();
        }
        return bean;
    }
    
    public static SessionBeanUserLocal getSessionBeanUser()
    {
        SessionBeanUserLocal bean = null;
        try
        {
            InitialContext ctx = new InitialContext();
            bean = (SessionBeanUserLocal) ctx.lookup("java:global/EnterpriseApplication/EnterpriseApplication-ejb/SessionBean!controller.SessionBeanUserLocal");
        }
        catch (NamingException ex)
        {
            ex.printStackTrace();
        }
        return bean;
    }
}
