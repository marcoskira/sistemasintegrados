package controller;


/* ----- IMPORTS --- */

import view.LoginView;




public class LoginController {
    private LoginView loginView;

    public LoginController(){
        this.loginView = new LoginView();
    }


    public void startApplication(){
        this.loginView.start();
    }

}
