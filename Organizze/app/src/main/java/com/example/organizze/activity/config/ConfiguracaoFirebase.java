package com.example.organizze.activity.config;

import com.google.firebase.auth.FirebaseAuth;

public class ConfiguracaoFirebase {

    private static FirebaseAuth auth;

    public static FirebaseAuth getFirebaseAutenticacao(){
        if ( auth == null) {
            auth = FirebaseAuth.getInstance();
        }
        return auth;
    }

}
