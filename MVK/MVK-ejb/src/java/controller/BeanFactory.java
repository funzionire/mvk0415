/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author Felix
 */
public class BeanFactory {

    private static final Logger LOG = Logger.getLogger(BeanFactory.class.getName());

    public static SessionBeanHouseholdLocal getSessionBeanHousehold() {
        SessionBeanHouseholdLocal bean = null;
        try {
            InitialContext ctx = new InitialContext();
            bean = (SessionBeanHouseholdLocal) ctx.lookup("java:global/MVK/MVK-ejb/SessionBeanHousehold!controller.SessionBeanHouseholdLocal");
        } catch (NamingException ex) {
            ex.printStackTrace();
        }
        return bean;
    }

    public static SessionBeanStocksArticleLocal getSessionBeanStocksArticle() {
        SessionBeanStocksArticleLocal bean = null;
        try {
            InitialContext ctx = new InitialContext();
            bean = (SessionBeanStocksArticleLocal) ctx.lookup("java:global/MVK/MVK-ejb/SessionBeanArticle!controller.SessionBeanStocksArticleLocal");
        } catch (NamingException ex) {
            ex.printStackTrace();
        }
        return bean;
    }

    public static SessionBeanUserLocal getSessionBeanUser() {
        SessionBeanUserLocal bean = null;
        try {
            InitialContext ctx = new InitialContext();
            bean = (SessionBeanUserLocal) ctx.lookup("java:global/MVK/MVK-ejb/SessionBeanUser!controller.SessionBeanUserLocal");
        } catch (NamingException ex) {
            ex.printStackTrace();
        }
        return bean;
    }

}
