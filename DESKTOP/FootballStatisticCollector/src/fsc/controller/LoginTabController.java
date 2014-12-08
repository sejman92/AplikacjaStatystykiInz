/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fsc.controller;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import fsc.model.User;
import fsc.model.validators.RegisterValidator;

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
    private final String LOGIN_SUCCESS = "Zalogowano";
    private final String LOGOUT_SUCCESS = "Wylogowano";
    private final String REGISTER_SUCCESS = "Utworzono konto";
    private final String LOGIN_BT_TEXT_1 = "Zaloguj";
    private final String LOGIN_BT_TEXT_2 = "Wyloguj";
    
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
        mc.infoLoginLb.setText(null);
        mc.infoRegisterLb.setText(null);
        mc.loginLoginWarningLb.setText(null);
        mc.passwordLoginWarningLb.setText(null);
        
        if(mc.owner != null){
            mc.owner = null;        
            disableButtonsWhenLoggedIn(false);
            return;
        }

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
        
        if(password.equals("") || !user.getPassword().equals(transform(Hashing.sha256().hashString(password, Charsets.UTF_8).toString()))){
            mc.passwordLoginWarningLb.setText(BAD_PASSWORD);
            return;
        }
        
        dbm.saveEntityElement(user);
        mc.owner = user;
        disableButtonsWhenLoggedIn(true);
    }
    
    public void registerBtClick(){
        mc.infoLoginLb.setText(null);
        mc.infoRegisterLb.setText(null);
        if(registerFieldsAreCorrect() && dbm.getUser(mc.loginRegisterTF.getText()) == null){
            User user = new User();
            user.setLogin(mc.loginRegisterTF.getText());
            user.setName(mc.nameRegisterTF.getText());
            user.setSurname(mc.surnameRegisterTF.getText());        
            user.setPassword(transform(Hashing.sha256().hashString(mc.passwordRegisterPF.getText(), Charsets.UTF_8).toString()));
            
            dbm.saveEntityElement(user);
            mc.infoRegisterLb.setText(REGISTER_SUCCESS);
                    }else if(dbm.getUser(mc.loginRegisterTF.getText()) != null){
            mc.infoRegisterLb.setText("Login jest zajęty");
        }
    }
    
    private void disableButtonsWhenLoggedIn(boolean isLoggedIn){
        mc.teamsManagerTab.setDisable(!isLoggedIn);
        mc.analizeTab.setDisable(!isLoggedIn);
        mc.loginLoginTF.setDisable(isLoggedIn);
        mc.passwordLoginPF.setDisable(isLoggedIn);
        mc.loginRegisterTF.setDisable(isLoggedIn);
        mc.nameRegisterTF.setDisable(isLoggedIn);
        mc.surnameRegisterTF.setDisable(isLoggedIn);
        mc.passwordRegisterPF.setDisable(isLoggedIn);
        mc.repeatPasswordRegisterPF.setDisable(isLoggedIn);
        mc.registerBt.setDisable(isLoggedIn);
        if(isLoggedIn){
            mc.loginBt.setText(LOGIN_BT_TEXT_2);
            mc.infoLoginLb.setText(LOGIN_SUCCESS);
        }else{
            mc.loginBt.setText(LOGIN_BT_TEXT_1);
            mc.infoLoginLb.setText(LOGOUT_SUCCESS);
            mc.beginMatchTab.setDisable(true);
        }
    }
    
    public String transform(String sha){
        if(sha.length()<=50)
            return sha;
        
        String result = ""; 
        
        for(int i=0;i<50;i++){
            result+=sha.charAt(i);
        }
        
        return result;
    }
}
