import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/** 
 * Cloud class manages a sorted list of strings
 * and persists the data to a local file (Cloudtxt.txt)
 * 
 * Features:
 * - Loads data from a file on startup
 * - Keeps list sorted alphabetically (ignore case)
 * - Saves changes automatically after add/update/remove
 */
public class Cloud {

    private ArrayList<String> list = new ArrayList<>(); // Stores all values in memory
    private final String FILE_Name = "Cloudtxt.txt"; // File used for persistent storage
    /** 
     * Constructor automatically loads existing data from file 
     * when object is created
     */
    public Cloud(){ 
        loadFromFile();
    }
    /**
     * Reads all lines from Cloudtxt.txt
     * and loads them into the ArrayList
     */
    private void loadFromFile(){
        File file = new File(FILE_Name);
        if (!file.exists()) return;

        try (Scanner fileScanner = new Scanner(file)){
            while (fileScanner.hasNextLine()){
                list.add(fileScanner.nextLine());
            }
        } catch (IOException e){
                System.out.println("Error saving file");
            }
    }
    /**
     * Saves the current list to Cloudtxt.txt
     * Overwrites existing file contents.
     */
    private void saveToFile(){
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_Name))){
            for (String s : list) {
                writer.println(s);
                } 
            } catch (IOException e){
                System.out.println("Error saving file");
            }
        }

    /**
     * Return all stores values/
     */
    public ArrayList<String> getAll(){
        return list;
    }
    /**
     * Returns the index of a given value
     * Return index if found, otherwise -1
     */
    public int getIndexOf(String value){
        return list.indexOf(value);
    }
    /** 
     * Adds a value to the list in alphabetical order (case insensitive)
     * Automatically saves changes to file
     */
    public boolean add(String value){
        if (list.isEmpty()){
            list.add(value);
            saveToFile();
            return true;
        }
        for(int i = 0; i < list.size();i++){
            if (value.compareToIgnoreCase(list.get(i)) < 0){
                list.add(i,value);
                saveToFile();
                return true;
            }
        }
        list.add(value);
        saveToFile();
        return true;
    }
    /**
     * Removes a value from the list
     * Return true if removed, false if not found
     */
    public boolean remove(String value){//remove item with its index
        int index = list.indexOf(value);

        if (index != -1){
            list.remove(index);
            saveToFile();
            return true;
        }
        return false;
    }
    /**
     * Updates an existing value
     * Replaced oldValue with newValue and keeps list sorted
     */
    public boolean update(String oldValue, String newValue){
        int index = list.indexOf(oldValue);

        if (index != -1){
            list.remove(index);
            add(newValue);
            saveToFile();
            return true;
        } 
        return false;
    }    
    /**
     * Prints all values to console
     */
    public void print(){
        for (String s : list){
            System.out.println(s);
        }
    }

}
