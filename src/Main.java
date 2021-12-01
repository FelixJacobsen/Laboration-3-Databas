import java.sql.*;
import java.util.Scanner;


public class Main {
    static Connection connection;
     Scanner scanner = new Scanner(System.in);
    static Statement statement;

    static {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/laboration3", "root", "123");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void createTable(){
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

    public static void main(String[] args) throws SQLException {
        String choice;
        Main main = new Main();
       main.deleteFromArtist();
    }



    private void deleteFromArtist() throws SQLException {
        ResultSet resultSet;
        statement = connection.createStatement();

        try {
            resultSet = statement.executeQuery("SELECT * FROM artist");
            while(resultSet.next()){
                System.out.println(resultSet.getString("id") + " "  + resultSet.getString("first_name") + " " + resultSet.getString("last_name") + " " + resultSet.getInt("age"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Enter ID of the artist you want to remove");
        int id = scanner.nextInt();
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement("DELETE FROM artist WHERE id = ?");
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
            System.out.println("Artist removed");
        } catch (SQLException e) {
            System.out.println("Artist with that ID not found");
        }
    }

    private void showAllInArtist() throws SQLException {
      statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery("SELECT * FROM artist");

      while(resultSet.next()){
          System.out.println(resultSet.getString("id") + " "  + resultSet.getString("first_name") + " " + resultSet.getString("last_name") + " " + resultSet.getInt("age"));
      }
    }




    private void addArtist() throws SQLException {
        String firstName;
        String lastName;
        int age;


        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO artist(first_name, last_name,age)\n" +
                                                                                    "VALUES(?,?,?)");
        preparedStatement.setString(1,firstName = scanner.nextLine());
        preparedStatement.setString(2,lastName = scanner.nextLine());
        preparedStatement.setInt(3, age = scanner.nextInt());
        preparedStatement.executeUpdate();

    }





}
