package counselormgmtsystem;

//abstract because dont want to randomly create a user object
protected abstract class User {
    protected String ID;
    protected String username;
    protected String password;
    protected String fullName;
    protected String status;


    //constructor
    public User(String ID, String username, String password, String fullName, String status) {
        this.ID = ID;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.status = status;
    }

    // getter & setter
    String getID(String ID) {
        return ID;
    }

    String setID(String ID) {
        this.ID = ID;
    }

    String getUsername(String username) {
        return username;
    }

    String setUsername(String username) {
        this.username = username;
    }    
    
    String getPassword(String password) {
        return password;
    }

    String setpassword(String password) {
        this.password = password;
    }    
    
    String getfullName(String fullName) {
        return fullName;
    }

    String setFullName(String fullName) {
        this.fullName = fullName;
    }    
    
    String getStatus(String status) {
        return status;
    }

    String setstatus(String status) {
        this.status = status;
    }    
    
}
