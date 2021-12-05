package ibf2021.d1;

import static org.junit.Assert.assertTrue;

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

}
