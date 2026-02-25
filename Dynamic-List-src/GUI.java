import java.awt.*; 
import javax.swing.*;

/** 
 * GUI Class
 * 
 * Provides a Swing-based interface for interacting with the Cloud data system.
 * Allows users to:
 * - Add items
 * - Remove items
 * - Search items
 */

public class GUI extends JFrame{
    // Backend data manager
    private Cloud cloud;

    // Model that holds list data for the JList
    private DefaultListModel<String> listModel;

    // Visual list component displayed in center
    private JList<String> visualList;

    /**
     * Constructor 
     * Initializes window settings and builds UI components
     */
    public GUI(){
        cloud = new Cloud();
        setTitle("Dynamic List");
        setSize(1080,1080);
        setLocationRelativeTo(null);
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   
        createListModel();
        createTopPanel();
        createListDisplay();
        
        setVisible(true);
    }
    /**
     * Initializes the list model and loads data from Cloud
     */
    private void createListModel(){
        listModel = new DefaultListModel<>();
        refreshList();
    }
    /**
     * Creates the top panel containing:
     * - Welcome label
     * - Add / Remove / Search buttons
     */
    private void createTopPanel(){
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel,BoxLayout.Y_AXIS));
        topPanel.setBackground(new Color(245,245,245));
        topPanel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        getContentPane().setBackground(Color.WHITE);

        // Header label
        JLabel label = new JLabel("Welcome to the Ever Changing List",JLabel.CENTER);
        label.setFont(new Font("Arial", Font.PLAIN, 40));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Button row container
        JPanel buttonRow = new JPanel(new FlowLayout());

        JButton addButton = new JButton("Add");
        JButton removeButton = new JButton("Remove");
        JButton searchButton = new JButton("Search");

        Dimension size = new Dimension(150,50);
        addButton.setPreferredSize(size);
        removeButton.setPreferredSize(size);
        searchButton.setPreferredSize(size);

        Font font = new Font("Arial",Font.BOLD, 18);
        addButton.setFont(font);
        removeButton.setFont(font);
        searchButton.setFont(font);

        // Prevent button focus outline
        addButton.setFocusable(false);
        removeButton.setFocusable(false);
        searchButton.setFocusable(false);

        // Connect buttons to backened logic
        addButton.addActionListener(e -> addItem());
        removeButton.addActionListener(e -> removeItem());
        searchButton.addActionListener(e -> searchItem());

        buttonRow.add(addButton);
        buttonRow.add(removeButton);
        buttonRow.add(searchButton);

        topPanel.add(label);
        topPanel.add(buttonRow);

        add(topPanel,BorderLayout.NORTH);
    }

    /**
     * Creates the center list display 
     * Uses DefaultListModel for dynamic updates
     */
    private void createListDisplay(){
        visualList = new JList<>(listModel);
        visualList.setFont(new Font("Arial",Font.PLAIN, 30));
        add(visualList, BorderLayout.CENTER);
        
    }

    /**
     * Handles adding a new value
     */
    private void addItem(){
        String value = JOptionPane.showInputDialog(this,"Enter value to add");
        
            if (value != null && !value.isEmpty()){
                if (cloud.add(value)){
                    refreshList();
                    JOptionPane.showMessageDialog(
                        this,
                         value + " has been added");
                } else {
                    JOptionPane.showMessageDialog(this,
                        value + " is already in list");
                }
            }
    }

    /** 
     * Handles removing a value
     */
    private void removeItem(){
        String value = JOptionPane.showInputDialog(this,"Enter value to remove");
        
            if (value != null && !value.isEmpty()){
                if (cloud.remove(value) ){
                refreshList();
                JOptionPane.showMessageDialog(
                this, 
                    value + " removed "
                );
                } else {
                    JOptionPane.showMessageDialog(
                        this,
                        value + " not found to remove ");
                } 
            }
    }

    /**
     * Handles searching for a value
     */
    private void searchItem(){
        String value = JOptionPane.showInputDialog(this,"Enter value to search");

            if (value != null && !value.isEmpty()){
                int index = cloud.getIndexOf(value);

                if (index != -1 ){
                    JOptionPane.showMessageDialog(
                    this, 
                        value + " found at index " + index
                    );
                } else {
                    JOptionPane.showMessageDialog(
                        this,
                        value + " not found "
                    );
                } 
            }
    }
    
    /**
     * Reloads all items from Cloud into the list model
     * Keeps the GUI synchronized with backend data
     */
    private void refreshList(){
        listModel.clear();
        for (String item : cloud.getAll()){
            listModel.addElement(item);
        }
    }
}
