/*
Eigene Exception Klasse
*/
package controller;

public class MVKException extends Exception{

    public MVKException() {
        super("Ein Fehler ist aufgetreten.");
    }

    public MVKException(String message) {
        super(message);
    }
    
    
    
}
