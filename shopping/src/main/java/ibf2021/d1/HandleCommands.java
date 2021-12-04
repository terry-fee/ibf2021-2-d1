package ibf2021.d1;

import java.util.Scanner;

public class HandleCommands 
{
    private Cart cart;

    public HandleCommands() {
        cart = new Cart();
    }

    public Cart getCart() {
        return cart;
    }

    public void handleAdd(String command, String arguments, Scanner scan) {
        
        while ("add".equals(command)) {
            System.out.println("Arguments here are " + arguments);
            String [] strArray = arguments.trim().split(",");

            while ("".equals(arguments.trim())) {
                System.out.println("Please add an item " +
                        "or items separted with a comma");
                System.out.println("The arguments were " + arguments);
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

            System.out.println("List " + cart.getCart());
            break;
        }
    }

    public void handleList(String command){

        for (int i = 0; i < cart.getCart().size(); i++)
            System.out.println("" + (i+1) + "." + cart.getCart().get(i));
    }
}
