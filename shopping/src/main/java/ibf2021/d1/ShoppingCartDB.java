package ibf2021.d1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Paths;
import java.util.Scanner;

public class ShoppingCartDB 
{
    private Cart cart;
    private boolean login = false;
    private String userName = "";
    private String path = "";

    public ShoppingCartDB() {
        cart = new Cart();
    }

    public Cart getCart() {
        return cart;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void handleAdd(String command, String arguments, Scanner scan) {
        
        while ("add".equals(command)) {
            String [] strArray = arguments.trim().split(",");

            while ("".equals(arguments.trim())) {
                System.out.println("Please add an item " +
                        "or items separted with a comma");
                command = scan.next();
                arguments = scan.nextLine();
                arguments.trim().split(",");
            }

            strArray = arguments.trim().split(",");

            for (int i = 0; i < strArray.length; i++) {
                
                if (cart.getCart().contains(strArray[i].trim())) {
                    System.out.println("You have " + 
                            strArray[i] + " in your cart");
                    continue;
                }

                cart.addItem(strArray[i].trim());
                System.out.println(strArray[i] + " added to the cart");
            }

            break;
        }
    }

    /* public void handleAdd2(String command, String arguments, Scanner scan) {
        String [] strArray = arguments.trim().split(",");

        for (int i = 0; i < strArray.length; i++) {
                
            if (cart.getCart().contains(strArray[i].trim())) {
                System.out.println("You have " + 
                        strArray[i] + " in your cart");
                continue;
            }

            cart.addItem(strArray[i].trim());
            System.out.println(strArray[i] + " added to the cart");
        }
    } */

    public void handleDelete(String command, String arguments, Scanner scan) {
        
        while ("delete".equals(command)) {

            int position = -1;

            while (position == -1) {

                try {
                    position = Integer.parseInt(arguments.trim());
                } catch (NumberFormatException nfe) {
                    System.out.println("Please provide a number");
                }

                if (!(position < 0) && position < cart.numberOfItemsInCart()) {
                    String item = cart.deleteItem(position);
                    System.out.println(item + " removed from cart");
                    break;
                }

                System.out.println("Incorrect item index");
                position = -1;
                command = scan.next();
                arguments = scan.nextLine();
            }

            break;
        }
    }

    public void handleList(String command){

        while ("list".equals(command)) {

            if (cart.numberOfItemsInCart() == 0) 
                System.out.println("Your cart is empty");
            
            for (int i = 0; i < cart.getCart().size(); i++)
                System.out.println("" + (i+1) + "." + cart.getCart().get(i));

            break;
        }
    }

    public void handleLogin(String arguments) 
            throws IOException {

        userName = arguments.trim();
        
        if (!"".equals(userName))
            login = true;
        else
            return;

        String fileName = path + "/" + userName + ".db";
        File file = new File(fileName);

        if (!file.exists()) {
            file.createNewFile();

            try (BufferedWriter writer = 
                new BufferedWriter(new FileWriter(fileName, true))) {
                writer.write(fileName); 
                writer.newLine();
            } 
        } else {

            try (Reader reader = new FileReader(fileName)) {
                BufferedReader br = new BufferedReader(reader);
                String line = "";
    
                while (null != (line = br.readLine())) {

                    if (line != null && line.contains(".db")) 
                        continue;

                    cart.addItem(line);
                } 
            }
        }
    }

    public void handleSave() {
        
        if (login == false) {
            System.out.println("Need to log in first before saving cart!");
            return;
        }

        String fileName = path + "/" + userName + ".db";

        try {
            try (BufferedWriter writer = 
                    new BufferedWriter(new FileWriter(fileName, true))) {
                
                for (String s : cart.getCart()) {
                    writer.write(s); 
                    writer.newLine();
                }
            } 
        } catch (IOException ioe) {
            System.out.println("Oops..cannot write to file.");
        }

        System.out.println("Items saved in file.");
    }

    public void handleUsers() {
        File file = Paths.get("db").toFile();
        
        for (File f: file.listFiles()) {
            String[] fileNames = f.getName().split("\\.");
            System.out.println(fileNames[0]);
        }
    }
}
