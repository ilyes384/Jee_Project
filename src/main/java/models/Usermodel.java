package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Usermodel {
	private int id;
	private String username;
	private String password;
	private String name ; 
	private int age ; 
	private String email ; 
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	public boolean LogUser(String enteredUsername, String enteredPassword) {
       // Connexion a la base de données
		String url = "jdbc:mysql://localhost:3306/db";
        String user = "root";
        String password = "0000";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            // récupérer les informations de l'utilisateur
            String query = "SELECT * FROM utilisateur WHERE username = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, enteredUsername);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                   
                    if (resultSet.next()) {
                    	// remplir l'objet par les autres attributs de la base de données 
                        this.setId(resultSet.getInt("id"));
                        this.setName(resultSet.getString("name"));
                        this.setAge(resultSet.getInt("age"));
                        this.setEmail(resultSet.getString("email"));
                        
                        String storedPassword = resultSet.getString("password");
                        return storedPassword.equals(enteredPassword);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false ; 
}
	}