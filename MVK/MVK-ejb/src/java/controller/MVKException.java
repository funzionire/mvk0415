/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

/**
 *
 * @author Felix
 */
public class MVKException extends Exception{

    public MVKException() {
        super("Ein Fehler ist aufgetreten.");
    }

    public MVKException(String message) {
        super(message);
    }
    
    
    
}
