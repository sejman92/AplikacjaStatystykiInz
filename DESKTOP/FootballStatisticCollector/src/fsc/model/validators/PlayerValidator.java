/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fsc.model.validators;

/**
 *
 * @author Gruby
 */
public class PlayerValidator {
    
    private static PlayerValidator instance;
    private final int NAME_AND_SURNAME_LENGTH = 2;
   
    private final String FIELD_EMPTY = " nie może być puste";    
    private final String NAME_TOO_SHORT = "Imię musi mieć minimum " + NAME_AND_SURNAME_LENGTH +" znaki";
    private final String SURNAME_TOO_SHORT = "Nazwisko musi mieć minimum " + NAME_AND_SURNAME_LENGTH +" znaki";
    
    private PlayerValidator(){
        
    }
    
    public static PlayerValidator getInstance(){
        if(instance == null){
            instance = new PlayerValidator();
        }
        
        return instance;
    }
    
    public String nameValidator(String name){
        if(name.equals(""))
            return "Imię" + FIELD_EMPTY;
        if(name.length() < NAME_AND_SURNAME_LENGTH)
            return NAME_TOO_SHORT;
        return null;
    }
    
    public String surnameValidator(String surname){
        if(surname.equals(""))
            return "Naziwsko" + FIELD_EMPTY;
        if(surname.length() < NAME_AND_SURNAME_LENGTH)
            return SURNAME_TOO_SHORT;
        return null;
    }
}
