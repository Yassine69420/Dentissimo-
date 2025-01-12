package org.example.Repositories.Implementation;

import org.example.Exceptions.AuthException;
import org.example.Exceptions.DAOException;
import org.example.Repositories.Interfaces.ISecretaireDAO;
import org.example.MODELS.Secretaire;

public class SecretaireDAO implements ISecretaireDAO {
    private static final String PATH = "src/main/resources/Secretaires.csv";
    @Override
    public void update(Secretaire secretaire) throws DAOException {

    }

    @Override
    public Secretaire getByEmailandPass(String email, String password) throws AuthException {
        return null;
    }
}
