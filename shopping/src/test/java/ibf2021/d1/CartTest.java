package ibf2021.d1;

import static org.junit.Assert.assertTrue;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CartTest 
{
    private Cart cart;

    @Before
    public void init() {
        cart = new Cart();
    }

    @After
    public void clean() {
        cart = null;
    }

    @Test
    public void testNumberOfItemsInCart() {
        cart.addItem("durian"); 
        cart.addItem("mango");
        cart.addItem("papaya");
        assertTrue(cart.getCart().size() == 3);
        System.out.println("Assertion size = 3");
    }

    @Test
    public void testAddItem() {
        cart.addItem("kiwifruit");
        assertTrue(cart.isItemInCart("kiwifruit"));
        System.out.println("kiwifruit in cart");
    }

    @Test
    public void testIsItemInCart() {
        cart.addItem("durian"); 
        cart.addItem("mango");
        assertTrue(cart.listCart().contains("durian"));
        System.out.println("durian in cart");
    }

    @Test 
    public void testDeleteItem() {
        Cart cart = new Cart();
        cart.addItem("durian"); 
        cart.addItem("mango");
        cart.deleteItem(1);
        assertTrue(cart.getCart().size() == 1);
        System.out.println("Assertion size = 1 after delete");
    }

    @Test
    public void testCreateDir() {
        File file = new File("db");

        if (!file.exists())
            file.mkdirs();
        else {
            file.delete();
            file.mkdirs();
        }
            

        System.out.println("Directory created");
    }

    @Test
    public void testCreateFile() {

        File file = new File("filename12.txt");

        try {

            if (!file.exists())
                file.createNewFile();
            else {
                file.delete();
                file.createNewFile();
            }

        } catch (IOException e) { }
           
        try (BufferedWriter writer = new BufferedWriter(
            new FileWriter(file, true))) {
            
            writer.write("kaboom.db");
            writer.newLine();
            writer.write("Apple"); 
            writer.newLine();
            writer.write("Orange"); 
            writer.newLine();
            
        } catch (IOException ioe) { }

        System.out.println("New file created");
    }

}
