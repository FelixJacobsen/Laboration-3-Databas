package menu;

import java.sql.SQLException;

public class MenuHandler {
    public void menuPrinter(){
        System.out.println("0. Exit program");
        System.out.println("1. Add into Artist");
        System.out.println("2. Delete from Artist");
        System.out.println("3. Update Artist");
        System.out.println("4. Show all in Artist");
        System.out.println("5. Find by ID in Artist");
    }

    public void menuPicker(String choice) throws SQLException {
        boolean flag = true;
        while(flag){
            switch (choice) {
                case "0" -> {
                    System.out.println("Ending program");
                    flag = false;
                }
              //  case "1" -> addArtist();
              //  case "2" -> deleteFromArtist();
                // case "3" -> updateArtist();
               // case "4" -> showAllInArtist();
                // case "5" -> findById();
                default -> System.out.println("Invalid choice, try again");
            }
        }
    }

}
