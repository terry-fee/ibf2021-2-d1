package ibf2021.d1;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args) {

        String defaultPath = "db";
        String path = "";

        if (null != args && args.length > 0) {
            path = args[0];
            File file = new File(args[0]);

            if (!file.exists())
                file.mkdirs();
        }
        else {
            
            path = defaultPath;
            File file = new File(path);

            if (!file.exists())
                file.mkdirs();
        }

        ShoppingCartDB handler = new ShoppingCartDB();
        handler.setPath(path);
        
        System.out.println("Please use only the following commands: ");
        System.out.println("  login <username>");
        System.out.println("  add <item>");
        System.out.println("  delete <position of item>");
        System.out.println("  list");
        System.out.println("  save");
        System.out.println("  users");
        System.out.println("  exit");
        System.out.println("Welcome to your shopping cart");

        Scanner scan = new Scanner(System.in);
        String command = scan.next();
        String arguments = scan.nextLine();

        if (!"add".equals(command) && !"delete".equals(command) &&
                !"list".equals(command) && !"exit".equals(command) &&
                !"login".equals(command) && "save".equals(command) &&
                !"users".equals(command)) {
            System.out.println("Invalid command, program will exit.");
            scan.close();
            return;
        }

        while (!"exit".equals(command)) {

            if ("login".equals(command)) {
                
                try {
                    handler.handleLogin(arguments);
                } catch (IOException ioe) {

                }
        
            }

            if ("add".equals(command)) {
                handler.handleAdd(command, arguments, scan);
                System.out.println("-- Item(s) added --");
            }

            if ("delete".equals(command)) {
                handler.handleDelete(command, arguments, scan);
                System.out.println("-- Item removed --");
            }
            
            if ("list".equals(command)) {
                handler.handleList(command);
                System.out.println("-- End of list --");
            }

            if ("save".equals(command)) {
                handler.handleSave();
            }

            if ("users".equals(command)) {
                handler.handleUsers();
                System.out.println("-- End of Users --");
            }

            command = scan.next();
            arguments = scan.nextLine();
        }
       
        scan.close();
        System.out.println("Program has ended.");
    }
}
