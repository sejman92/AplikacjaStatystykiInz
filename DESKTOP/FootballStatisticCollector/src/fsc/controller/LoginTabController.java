/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fsc.controller;

import fsc.model.User;
import fsc.model.validators.RegisterValidator;
//import com.google.common.base.Charsets;
//import com.google.common.hash.Hashing;

/**
 *
 * @author Gruby
 */
public class LoginTabController {
    
    private static LoginTabController instance;
    private MainController mc;
    private DatabaseManager dbm;
    private RegisterValidator rv;
    
    private final String LACK_OF_USER_IN_DATABASE = "Brak użytkownika w bazie danych";
    private final String BAD_PASSWORD = "Blędne hasło";
    
    private LoginTabController(MainController mc){
        this.mc=mc;
        dbm = DatabaseManager.getInstance();
        rv = RegisterValidator.getInstance();
    }
    
    public static LoginTabController getInstance(MainController mc){
        if(instance == null){
            instance = new LoginTabController(mc);
        }
        
        return instance;
    }
    
    public boolean registerFieldsAreCorrect(){
        mc.loginRegisterWarningLb.setText(rv.loginValidator(mc.loginRegisterTF.getText()));
        if(mc.loginRegisterWarningLb.getText() != null)
            return false;
        
        mc.nameRegisterWarningLb.setText(rv.nameValidator(mc.nameRegisterTF.getText()));
        if(mc.nameRegisterWarningLb.getText() != null)
            return false;
        
        mc.surnameRegisterWarningLb.setText(rv.surnameValidator(mc.surnameRegisterTF.getText()));
        if(mc.surnameRegisterWarningLb.getText() != null)
            return false;
        
        mc.passwordRegisterWarningLb.setText(rv.passwordValidator(mc.passwordRegisterPF.getText()));
        if(mc.passwordRegisterWarningLb.getText() != null)
            return false;
        
        mc.repeatPasswordRegisterWarningLb.setText(rv.passwordsCompareValidator(mc.passwordRegisterPF.getText(), mc.repeatPasswordRegisterPF.getText())); 
        if(mc.repeatPasswordRegisterWarningLb.getText() != null)
            return false;
        
        return true;
    }
    
    public void loginBtClick(){
        mc.loginLoginWarningLb.setText(null);
        mc.passwordLoginWarningLb.setText(null);

        String login = mc.loginLoginTF.getText();
        String password = mc.passwordLoginPF.getText();
        
        if(login.equals("")){
            mc.loginLoginWarningLb.setText(LACK_OF_USER_IN_DATABASE);
            return;
        }
        
        User user = dbm.getUser(login);
        
        if(user == null){
            mc.loginLoginWarningLb.setText(LACK_OF_USER_IN_DATABASE);
            return;
        }
        
        if(password.equals("") || !user.getPassword().equals(password)){
            mc.passwordLoginWarningLb.setText(BAD_PASSWORD);
            return;
        }
        
        dbm.saveEntityElement(user);
        mc.owner = user;
    }
    
    public void registerBtClick(){
        if(registerFieldsAreCorrect()){
            User user = new User();
            user.setLogin(mc.loginRegisterTF.getText());
            user.setName(mc.nameRegisterTF.getText());
            user.setSurname(mc.surnameRegisterTF.getText());
            user.setPassword(mc.passwordRegisterPF.getText());
            dbm.saveEntityElement(user);
            System.out.println("Stworzono usera");
            //user.setPassword(Hashing.sha256().hashString(mc.nameRegisterTF.getText(), Charsets.UTF_8).toString());
        }
    }
}
