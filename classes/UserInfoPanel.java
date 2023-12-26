package classes;
import interfaces.*;
 
import javax.swing.*; 
import javax.swing.table.DefaultTableModel; 
import java.awt.*;
import java.io.File;
import java.io.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class UserInfoPanel extends JPanel 
{ 
    private JTable table;

    public UserInfoPanel() 
	{ 
        setLayout(new BorderLayout());
        showUserInfoPanel();
    }

    private void showUserInfoPanel() 
	{
        JPanel userInfoPanel = new JPanel(new BorderLayout());
        table = new JTable();
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Name");
        model.addColumn("ID");
        model.addColumn("Password");
        model.addColumn("Role");
        model.addColumn("Department");

        try 
		{
            Scanner scanner = new Scanner(new File("data\\user_data.txt"));
            while (scanner.hasNextLine()) 
			{
                String line = scanner.nextLine();
                String[] fields = line.split(":");
                model.addRow(fields);
            }
        } 
		catch (IOException ex) 
		{
            JOptionPane.showMessageDialog(this, "Error reading user data.");
        }

        table.setModel(model);
        JScrollPane scrollPane = new JScrollPane(table);
        userInfoPanel.add(scrollPane, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        JButton addUserButton = new JButton("Add");
        JButton editUserButton = new JButton("Edit");
        JButton deleteUserButton = new JButton("Delete");
		
		addUserButton.setBackground( new Color(255, 255, 255) );
		editUserButton.setBackground( new Color(255, 255, 255) );
        deleteUserButton.setBackground( new Color(255, 255, 255) );
        
		
		addUserButton.setFont(new Font("Arial", Font.BOLD, 14));
		editUserButton.setFont(new Font("Arial", Font.BOLD, 14));
		deleteUserButton.setFont(new Font("Arial", Font.BOLD, 14));

        buttonPanel.add(addUserButton);
        buttonPanel.add(editUserButton);
        buttonPanel.add(deleteUserButton);
        userInfoPanel.add(buttonPanel, BorderLayout.SOUTH);
		
        this.add(userInfoPanel);
        addUserButton.addActionListener(e -> addUser());
        editUserButton.addActionListener(e -> editUser());
        deleteUserButton.addActionListener(e -> deleteUser());
    }

    private void addUser() 
	{
        JFrame addFrame = new JFrame("Add User");
        addFrame.setSize(300, 300);
        addFrame.setLayout(null);

        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField();

        JLabel idLabel = new JLabel("ID:");
        JTextField idField = new JTextField();

        JLabel passwordLabel = new JLabel("Password:");
        JTextField passwordField = new JTextField();

        JLabel roleLabel = new JLabel("Role:");
        JTextField roleField = new JTextField();

        JLabel departmentLabel = new JLabel("Department:");
        JTextField departmentField = new JTextField();

        JButton saveButton = new JButton("Save");

        saveButton.addActionListener(e -> {
            String name = nameField.getText();
            String id = idField.getText();
            String password = passwordField.getText();
            String role = roleField.getText();
            String department = departmentField.getText();

            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.addRow(new Object[]{name, id, password, role, department});

            saveUserDataToFile();
            addFrame.dispose();
        }); 

        nameLabel.setBounds(10, 10, 80, 20);
        nameField.setBounds(100, 10, 150, 20);
        idLabel.setBounds(10, 40, 80, 20);
        idField.setBounds(100, 40, 150, 20);
        passwordLabel.setBounds(10, 70, 80, 20);
        passwordField.setBounds(100, 70, 150, 20);
        roleLabel.setBounds(10, 100, 80, 20);
        roleField.setBounds(100, 100, 150, 20);
        departmentLabel.setBounds(10, 130, 80, 20);
        departmentField.setBounds(100, 130, 150, 20);
        saveButton.setBounds(10, 160, 80, 30);

        addFrame.add(nameLabel);
        addFrame.add(nameField);
        addFrame.add(idLabel);
        addFrame.add(idField);
        addFrame.add(passwordLabel);
        addFrame.add(passwordField);
        addFrame.add(roleLabel);
        addFrame.add(roleField);
        addFrame.add(departmentLabel);
        addFrame.add(departmentField);
        addFrame.add(saveButton);
        addFrame.setLocationRelativeTo(null);
        addFrame.setVisible(true);
    }

    private void editUser() 
	{
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) 
		{
            ArrayList<String> userData = getSelectedTableRow(selectedRow);
            String name = userData.get(0);
            String id = userData.get(1);
            String password = userData.get(2);
            String role = userData.get(3);
            String department = userData.get(4);

            JFrame editFrame = new JFrame("Edit User");
            editFrame.setSize(300, 300);
            editFrame.setLayout(null);

            JLabel nameLabel = new JLabel("Name:");
            JTextField nameField = new JTextField(name);

            JLabel idLabel = new JLabel("ID:");
            JTextField idField = new JTextField(id);

            JLabel passwordLabel = new JLabel("Password:");
            JTextField passwordField = new JTextField(password);

            JLabel roleLabel = new JLabel("Role:");
            JTextField roleField = new JTextField(role);

            JLabel departmentLabel = new JLabel("Department:");
            JTextField departmentField = new JTextField(department);

            JButton saveButton = new JButton("Save");
            saveButton.addActionListener(e -> {
                String editedName = nameField.getText();
                String editedId = idField.getText();
                String editedPassword = passwordField.getText();
                String editedRole = roleField.getText();
                String editedDepartment = departmentField.getText();

                updateSelectedUserData(editedName, editedId, editedPassword, editedRole, editedDepartment);
                saveUserDataToFile();

                editFrame.dispose();
            });

            nameLabel.setBounds(10, 10, 80, 20);
            nameField.setBounds(100, 10, 150, 20);
            idLabel.setBounds(10, 40, 80, 20);
            idField.setBounds(100, 40, 150, 20);
            passwordLabel.setBounds(10, 70, 80, 20);
            passwordField.setBounds(100, 70, 150, 20);
            roleLabel.setBounds(10, 100, 80, 20);
            roleField.setBounds(100, 100, 150, 20);
            departmentLabel.setBounds(10, 130, 80, 20);
            departmentField.setBounds(100, 130, 150, 20);
            saveButton.setBounds(10, 160, 80, 30);

            editFrame.add(nameLabel);
            editFrame.add(nameField);
            editFrame.add(idLabel);
            editFrame.add(idField);
            editFrame.add(passwordLabel);
            editFrame.add(passwordField);
            editFrame.add(roleLabel);
            editFrame.add(roleField);
            editFrame.add(departmentLabel);
            editFrame.add(departmentField);
            editFrame.add(saveButton);
            editFrame.setLocationRelativeTo(null);
            editFrame.setVisible(true);
        } 
		else  
		{
            JOptionPane.showMessageDialog(this, "Please select a user to edit.");
        }
    }

    private void deleteUser() 
	{
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) 
		{
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.removeRow(selectedRow);
            saveUserDataToFile();
        } 
		else 
		{ 
            JOptionPane.showMessageDialog(this, "Please select a user to delete.");
        }
    }

    private ArrayList<String> getSelectedTableRow(int selectedRow) 
	{
        ArrayList<String> userData = new ArrayList<>();
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        for (int i = 0; i < model.getColumnCount(); i++) 
		{
            userData.add((String) model.getValueAt(selectedRow, i));
        } 
        return userData;
    }

    private void saveUserDataToFile() 
	    {
        try 
	    {
	    	FileWriter fileWriter = new FileWriter("data\\user_data.txt", false); 
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            int rowCount = model.getRowCount();
	    
            for (int i = 0; i < rowCount; i++) 
	    	{
                StringBuilder line = new StringBuilder();
                for (int j = 0; j < model.getColumnCount(); j++) 
	    		{
                    line.append(model.getValueAt(i, j)).append(":");
                }
                line.append("\n");
                fileWriter.write(line.toString());
            }
			fileWriter.close(); 
        } catch (IOException e) 
	    {
            JOptionPane.showMessageDialog(this, "Error saving user data.");
        } 
    } 


    private void updateSelectedUserData(String editedName, String editedId, String editedPassword, String editedRole, String editedDepartment) 
	{
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) 
		{
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setValueAt(editedName, selectedRow, 0);
            model.setValueAt(editedId, selectedRow, 1);
            model.setValueAt(editedPassword, selectedRow, 2);
            model.setValueAt(editedRole, selectedRow, 3);
            model.setValueAt(editedDepartment, selectedRow, 4); 
        } 
		else 
		{
            JOptionPane.showMessageDialog(this, "Please select a user to update.");
        } 
    }
}
