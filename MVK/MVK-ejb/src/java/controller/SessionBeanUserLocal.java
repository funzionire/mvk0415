/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.ejb.Local;
import model.User;

/**
 *
 * @author Felix
 */
@Local
public interface SessionBeanUserLocal {
    
    public User createUser(String name,String email, String password);
    
    public User login(String email, String password);
    
    public User deleteUser (Long userID);
    
    public User changeUser (Long userID, String name, String email, String password);

}
