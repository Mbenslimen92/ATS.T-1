package de.tritux.db.authentication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Service;
@Service
public class UserAuthentication {

    public static boolean authenticate(String mail, String password) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_benz", "root", "root")) {
            String selectQuery = "SELECT COUNT(*) FROM User WHERE mail = ? AND password = ?";
            try (PreparedStatement statement = connection.prepareStatement(selectQuery)) {
                statement.setString(1, mail);
                statement.setString(2, password);

                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        int count = resultSet.getInt(1);
                        return count > 0;
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Une erreur s'est produite lors de l'authentification : " + e.getMessage());
        }

        return false;
    }
}