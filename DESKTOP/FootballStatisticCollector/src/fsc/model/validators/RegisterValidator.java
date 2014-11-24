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
public class RegisterValidator {
    
    private static RegisterValidator instance;
    public final int LOGIN_AND_PASSWORD_LENGTH = 4;
    public final int NAME_AND_SURNAME_LENGTH = 2;
   
    private final String FIELD_EMPTY = "Pole nie może być puste";    
    private final String LOGIN_TOO_SHORT = "Login musi mieć minimum " + LOGIN_AND_PASSWORD_LENGTH +" znaki";
    private final String NAME_TOO_SHORT = "Imię musi mieć minimum " + NAME_AND_SURNAME_LENGTH +" znaki";
    private final String SURNAME_TOO_SHORT = "Nazwisko musi mieć minimum " + NAME_AND_SURNAME_LENGTH +" znaki";
    private final String PASSWORD_TOO_SHORT = "Hasło musi mieć minimum " + LOGIN_AND_PASSWORD_LENGTH +" znaki";
    private final String PASSWORDS_DO_NOT_MATCH = "Hasła różnią się";
    
    private RegisterValidator(){
        
    }
    
    public static RegisterValidator getInstance(){
        if(instance == null){
            instance = new RegisterValidator();
        }
        
        return instance;
    }
    
    public String loginValidator(String login){
        if(login.equals(""))
            return FIELD_EMPTY;
        if(login.length() < LOGIN_AND_PASSWORD_LENGTH)
            return LOGIN_TOO_SHORT;
        return null;
    }
    
    public String nameValidator(String name){
        if(name.equals(""))
            return FIELD_EMPTY;
        if(name.length() < NAME_AND_SURNAME_LENGTH)
            return NAME_TOO_SHORT;
        return null;
    }
    
    public String surnameValidator(String surname){
        if(surname.equals(""))
            return FIELD_EMPTY;
        if(surname.length() < NAME_AND_SURNAME_LENGTH)
            return SURNAME_TOO_SHORT;
        return null;
    }
    
    public String passwordValidator(String login){
        if(login.equals(""))
            return FIELD_EMPTY;
        if(login.length() < LOGIN_AND_PASSWORD_LENGTH)
            return PASSWORD_TOO_SHORT;
        return null;
    }
    
    public String passwordsCompareValidator(String password, String repeatPassword){
        if(repeatPassword.equals(""))
            return FIELD_EMPTY;
        if(!repeatPassword.equals(password))
            return PASSWORDS_DO_NOT_MATCH;
        return null;      
    }
}