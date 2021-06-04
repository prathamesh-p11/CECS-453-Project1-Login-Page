package com.example.project1;

import java.util.HashMap;
import java.util.Map;

public class Data {

    // A hashmap data structure for holding usernames and passwords pair
    HashMap <String, String> hmCredentials;

    public Data(){

        hmCredentials = new HashMap<>();

        // Adding some items into the hashmap table
        hmCredentials.put("AJ", "CoolDude1");
        hmCredentials.put("test", "1234");
    }

    // This method adds a new username and password to the hashmap
    public void AddCredential(String username, String password){
        hmCredentials.put(username, password);
    }

    // This method checks if username exists in the hashmap
    public Boolean CheckUsername(String username){
        Boolean  retval = true;
        if(!hmCredentials.containsKey(username))
            retval = false;
        return retval;
    }

    // This method checks a username and password combination is correct!
    public Boolean CheckCredentials(String username, String Password){
        Boolean  retval = true;
        //compare stored password with password entered by user for the corresponding username
        String pwd = hmCredentials.get(username);
        if(!pwd.equals(Password))
            retval = false;
        return retval;
    }

}
