package org.example.Repositories.Interfaces;

import org.example.Exceptions.AuthException;

public interface IAUTH <T> {

    // Retrieve an entity by its ID
    T getByEmailandPass(String email, String password) throws AuthException;

    // Update an entity

}


