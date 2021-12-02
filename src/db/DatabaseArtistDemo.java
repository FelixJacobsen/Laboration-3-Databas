package db;

import menu.MenuHandler;

import java.sql.SQLException;


public class DatabaseArtistDemo {
   static MenuHandler menuHandler = new MenuHandler();
   static DatabaseArtist databaseArtist = new DatabaseArtist();
    public static void main(String[] args) throws SQLException {
        // Kör raden nedan om det är första gången som programmet körs
        // databaseArtist.createTable();
        menuHandler.menuPicker();

    }
}
