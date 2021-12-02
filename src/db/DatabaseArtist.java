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
        statement = connection.createStatement();
        ResultSet resultSet;
        boolean flag;
        int id;

        try {
            resultSet = statement.executeQuery("SELECT * FROM artist");
            while (resultSet.next()) {
                printResultset(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        do{
            System.out.println("Enter ID of the artist you want to remove");
             id = Integer.parseInt(scanner.nextLine());
            try{
                findById(id);
                flag = false;
            }catch (SQLException e){
                System.out.println("Artist not found");
                flag = true;
            }
        }while(flag);
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement("DELETE FROM artist WHERE id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Artist with that ID not found");
        }
        System.out.println("Artist removed");
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
        int age = Integer.parseInt(scanner.nextLine());
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO artist(first_name, last_name,age) VALUES(?,?,?)");
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, age);
            preparedStatement.executeUpdate();
            System.out.println("Artist added successfully");

        } catch (Exception ignored) {
        }
        System.out.println("=================================");
    }

    public void updateArtist() throws SQLException {
        int artistID;
        boolean flag;
        showAllInArtist();

        do {
            System.out.println("Enter the id of the actor you want to update ");
            artistID = Integer.parseInt(scanner.nextLine());
            try {
                findById(artistID);
                flag = false;
            } catch (Exception e) {
                System.out.println("Artist not found try again");
                flag = true;
            }
        } while (flag);

        System.out.println("Enter first name: ");
        String firstName = scanner.nextLine();

        System.out.println("Enter last name: ");
        String lastName = scanner.nextLine();

        System.out.println("Enter age: ");
        int age = Integer.parseInt(scanner.nextLine());
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement("UPDATE artist SET first_name = ?, last_name = ?,age = ? WHERE id = ?");
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, age);
            preparedStatement.setInt(4,artistID);
            preparedStatement.executeUpdate();
            System.out.println("Artist updated");
        } catch (Exception e) {
            System.out.println("Artist not found");
        }
    }

    public void findById(int artistID) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM artist WHERE id = ?");
        preparedStatement.setInt(1, artistID);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (!resultSet.next())
            throw new SQLException();
    }


    public void findById() throws SQLException {
        System.out.println("Enter the ID of the artist you're searching :");
        int searchedId = Integer.parseInt(scanner.nextLine());

        String selectByID = "SELECT * FROM artist WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(selectByID);
        preparedStatement.setInt(1, searchedId);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            printResultset(resultSet);
        }
    }

    public void findByAge() throws SQLException {
        System.out.println("Enter age of the artist you are searching for: ");
        int searchedAge = Integer.parseInt(scanner.nextLine());
        String selectedByAge = "SELECT * FROM artist WHERE age = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(selectedByAge);
        preparedStatement.setInt(1, searchedAge);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            printResultset(resultSet);
        }
    }


    public void findByName() throws SQLException {
        System.out.println("Enter the name of the artist you are searching for: ");
        String searchedName = scanner.nextLine();

        String selectedByName = "SELECT * FROM artist WHERE first_name = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(selectedByName);
        preparedStatement.setString(1, searchedName);
        ResultSet resultSet = preparedStatement.executeQuery();
        System.out.println("========== Artists found ==========");
        while (resultSet.next()) {
            printResultset(resultSet);
        }
        System.out.println("===================================");
    }

    private void printResultset(ResultSet resultSet) throws SQLException {
        System.out.println("Artist ID:" + resultSet.getString("id") + " Name:" + "    " + resultSet.getString("first_name")
                + " " + resultSet.getString("last_name") + "    " + "with age: " + resultSet.getInt("age"));
    }
}
