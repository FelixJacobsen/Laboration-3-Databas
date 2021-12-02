package db;

import java.sql.*;
import java.util.Scanner;


public class DatabaseArtist {
    private static Connection connection;
    private static Statement statement;
    private static final Scanner scanner = new Scanner(System.in);
    static {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/laboration3", "root", "123");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTable() {
        String sql = "CREATE TABLE artist(\n" +
                "id INT NOT NULL AUTO_INCREMENT,\n" +
                "first_name VARCHAR(255) NOT NULL,\n" +
                "last_name VARCHAR(255) NOT NULL,\n" +
                "age INT NOT NULL,\n" +
                "PRIMARY KEY(id)) ";
        try {
            Statement statement = connection.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void deleteFromArtist() throws SQLException {
        ResultSet resultSet;
        statement = connection.createStatement();

        try {
            resultSet = statement.executeQuery("SELECT * FROM artist");
            while (resultSet.next()) {
                System.out.println(resultSet.getString("id") + " " + resultSet.getString("first_name") + " " + resultSet.getString("last_name") + " " + resultSet.getInt("age"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Enter ID of the artist you want to remove");
        int id = scanner.nextInt();
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement("DELETE FROM artist WHERE id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            System.out.println("Artist removed");
        } catch (SQLException e) {
            System.out.println("Artist with that ID not found");
        }
        System.out.println("=================================");
    }

    public void showAllInArtist() throws SQLException {
        statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM artist");

        while (resultSet.next()) {
           printResultset(resultSet);
        }
        System.out.println("======================================================");
    }


    public void addArtist() {
        System.out.println("Enter first name: ");
        String firstName = scanner.nextLine();
        System.out.println("Enter last name: ");
        String lastName = scanner.nextLine();
        System.out.println("Enter age: ");
        int age = scanner.nextInt();

        try{
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO artist(first_name, last_name,age) VALUES(?,?,?)" );
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, age);
            preparedStatement.executeUpdate();
            System.out.println("Artist added successfully");

        }catch (Exception e){

        }
        System.out.println("=================================");

    }

    public void updateArtist() throws SQLException {
        System.out.println("Enter first name: ");
        String firstName = scanner.nextLine();
        System.out.println("Enter last name: ");
        String lastName = scanner.nextLine();
        System.out.println("Enter age: ");
        int age = scanner.nextInt();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE artist SET first_name = ?, last_name = ?,age = ? ");
            preparedStatement.setString(1,firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3,age);
            preparedStatement.executeUpdate();
        System.out.println("Artist updated");

    }
    public void findById() throws SQLException {
        System.out.println("Enter the ID of the artist you're searching for:");
        int searchedId = scanner.nextInt();

        String selectByID = "SELECT * FROM artist WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(selectByID);
        preparedStatement.setInt(1,searchedId);
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            printResultset(resultSet);
        }
    }

    public void findByAge() throws SQLException {
        System.out.println("Enter age of the artist you are searching for: ");
        int searchedAge = scanner.nextInt();

        String selectedByAge = "SELECT * FROM artist WHERE age = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(selectedByAge);
        preparedStatement.setInt(1,searchedAge);
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            printResultset(resultSet);
        }

    }



    public void findByName() throws SQLException {
        System.out.println("Enter the name of the artist you are searching for: ");
        String searchedName = scanner.nextLine();

        String selectedByName = "SELECT * FROM artist WHERE first_name = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(selectedByName);
        preparedStatement.setString(1,searchedName);
        ResultSet resultSet = preparedStatement.executeQuery();
        System.out.println("========== Artists found ==========");
        while(resultSet.next()){
            printResultset(resultSet);
        }
        System.out.println("===================================");
    }

    private void printResultset(ResultSet resultSet) throws SQLException {
        System.out.println("Artist ID:" + resultSet.getString("id") + " Name:" + "    " + resultSet.getString("first_name")
                + " " + resultSet.getString("last_name") + "    " + "with age: " + resultSet.getInt("age"));
    }






}
