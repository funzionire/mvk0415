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
public class MKV_Exception extends Exception{

    public MKV_Exception() {
        super("Ein Fehler ist aufgetreten.");
    }

    public MKV_Exception(String message) {
        super(message);
    }
    
    
    
}
