import java.util.Scanner;
/**
 * Administrator class
 * 
 * Acts as the console interface for interacting with the Cloud system
 * Allows user to:
 * - Search items
 * - Add items
 * - Remove items
 * - Update items
 * - Print all stored items
 */
public class Administrator{
    /**
     * Entry point of the applicaiton
     * Creates Cloud instance and starts user interaction loop
     */    
    public static void main(String[] args) {
        Cloud cloud = new Cloud();
        Scanner scanner = new Scanner(System.in);
        Action(cloud,scanner);
    }
    /**
     * Main application loop
     * Continously prompts user for operations
     */
    static void Action(Cloud cloud , Scanner scanner) {
        
        while (true) {
            System.out.println("Search:1 , Add:2 , Update:3 , Remove:4 , Print:5");
            int operation = scanner.nextInt();
            scanner.nextLine(); // Clear newline from buffer

            if (operation == 1) {
                System.out.println("");
                Search(cloud,scanner);
            } else if (operation == 2) {
                System.out.println("");
                Add(cloud,scanner);
            } else if (operation == 3) {
                System.out.println("");
                Update(cloud,scanner);
            } else if (operation == 4) {
                System.out.println("");
                Remove(cloud,scanner);
            } else if (operation == 5) {
                Print(cloud);
            } else {
                System.out.println(".()");
                scanner.close();
            }
        }
    }
    /**
     * Searches for an item and prints its index
     */
    static void Search(Cloud cloud,Scanner scanner){
        System.out.println("Item to Find");
        String item = scanner.nextLine();
        int index = cloud.getIndexOf(item);
        System.out.println(index);
    }
    /**
     * Prompts user to add a new item
     */
    static void Add(Cloud cloud,Scanner scanner){
        System.out.println("Item to Add");
        String item = scanner.nextLine();
        cloud.add(item);
        System.out.println();
    }
    /**
     * Prompts user to remove an item
     */
    static void Remove(Cloud cloud,Scanner scanner){
        System.out.println("Item to Remove");
        String item = scanner.nextLine();
        cloud.remove(item);
        System.out.println();
    }
    /**
     * Prompts user to update an existing item. 
     */
    static void Update(Cloud cloud,Scanner scanner){
        System.out.println("Item to Replace");
        String item = scanner.nextLine();
        System.out.println("Replace with");
        String item1 = scanner.nextLine();
        cloud.update(item,item1);
    }
    /**
     * Prints all stored items.
     */
    static void Print(Cloud cloud){
        cloud.print();
    }
}
