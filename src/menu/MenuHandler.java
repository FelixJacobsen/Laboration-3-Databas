package menu;
import db.DatabaseArtist;
import java.sql.SQLException;
import java.util.Scanner;

public class MenuHandler {
    Scanner scanner = new Scanner(System.in);
    DatabaseArtist databaseArtist = new DatabaseArtist();

    public void menuPrinter() {
        System.out.println("0. Exit program");
        System.out.println("1. Add into Artist");
        System.out.println("2. Delete from Artist");
        System.out.println("3. Update Artist");
        System.out.println("4. Show all in Artist");
        System.out.println("5. Find artist");
    }

    public void menuPicker() throws SQLException {
        boolean flag = true;
        while (flag) {
            menuPrinter();
            String choice = scanner.nextLine();
            switch (choice) {
                case "0" -> {
                    System.out.println("Ending program....");
                    flag = false;
                }
                case "1" -> databaseArtist.addArtist();
                case "2" -> databaseArtist.deleteFromArtist();
                case "3" -> databaseArtist.updateArtist();
                case "4" -> databaseArtist.showAllInArtist();
                case "5" -> findArtist();
                default -> System.out.println("Invalid choice, try again");
            }
        }
    }

    private void findArtist() throws SQLException {
        boolean flag = true;
        String choice;
        while (flag) {
            findArtistMenu();
            choice = scanner.nextLine();
            switch (choice) {
                case "0" -> flag = false;
                case "1" -> databaseArtist.findById();
                case "2" -> databaseArtist.findByAge();
                case "3" -> databaseArtist.findByName();
                default -> System.out.println("Not a valid option try again");
            }
        }
    }

    private void findArtistMenu() {
        System.out.println("""
                
                0. Return
                1. Find artist by ID
                2. Find artist by age
                3. Find artist by name""");
    }
}
