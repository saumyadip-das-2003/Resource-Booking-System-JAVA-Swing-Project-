package classes;
import interfaces.*; 

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;
import java.io.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class AdminInfoPanel extends JPanel 
{
    private JTable adminTable;

    public AdminInfoPanel() 
	{
        setLayout(new BorderLayout());
        showAdminInfoPanel();
    }

    private void showAdminInfoPanel() 
	{
        JPanel adminInfoPanel = new JPanel(new BorderLayout());
        adminTable = new JTable();
        DefaultTableModel adminModel = new DefaultTableModel();
        adminModel.addColumn("ID");
        adminModel.addColumn("Name");
        adminModel.addColumn("Password");

        try 
		{
            Scanner scanner = new Scanner(new File("data\\admin_data.txt"));
            while (scanner.hasNextLine()) 
			{
                String line = scanner.nextLine();
                String[] fields = line.split(":");
                adminModel.addRow(fields);
            }
        } 
		catch (IOException ex) 
		{ 
            JOptionPane.showMessageDialog(this, "Error reading admin data.");
        }

        adminTable.setModel(adminModel);
        JScrollPane adminScrollPane = new JScrollPane(adminTable);
        adminInfoPanel.add(adminScrollPane, BorderLayout.CENTER);

        JPanel adminButtonPanel = new JPanel();
        JButton addAdminButton = new JButton("Add");
        JButton editAdminButton = new JButton("Edit");
        JButton deleteAdminButton = new JButton("Delete");
		
		addAdminButton.setBackground( new Color(255, 255, 255) );
		editAdminButton.setBackground( new Color(255, 255, 255) );
        deleteAdminButton.setBackground( new Color(255, 255, 255) );
        
		
		addAdminButton.setFont(new Font("Arial", Font.BOLD, 14));
		editAdminButton.setFont(new Font("Arial", Font.BOLD, 14));
		deleteAdminButton.setFont(new Font("Arial", Font.BOLD, 14));

        adminButtonPanel.add(addAdminButton);
        adminButtonPanel.add(editAdminButton);
        adminButtonPanel.add(deleteAdminButton);
        adminInfoPanel.add(adminButtonPanel, BorderLayout.SOUTH);

        this.add(adminInfoPanel);
        addAdminButton.addActionListener(e -> addAdmin());
        editAdminButton.addActionListener(e -> editAdmin());
        deleteAdminButton.addActionListener(e -> deleteAdmin());
    }

    private void addAdmin() 
	{
        JFrame addAdminFrame = new JFrame("Add Admin");
        addAdminFrame.setSize(300, 200);
        addAdminFrame.setLayout(null);

        JLabel idLabel = new JLabel("ID:");
        JTextField idField = new JTextField();

        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField();

        JLabel passwordLabel = new JLabel("Password:");
        JTextField passwordField = new JTextField();

        JButton saveButton = new JButton("Save");

        saveButton.addActionListener(e -> {
            String id = idField.getText();
            String name = nameField.getText();
            String password = passwordField.getText();

            DefaultTableModel adminModel = (DefaultTableModel) adminTable.getModel();
            adminModel.addRow(new Object[]{id, name, password});
            saveAdminDataToFile();

            addAdminFrame.dispose();
        });

        idLabel.setBounds(10, 10, 80, 20);
        idField.setBounds(100, 10, 150, 20);
        nameLabel.setBounds(10, 40, 80, 20);
        nameField.setBounds(100, 40, 150, 20);
        passwordLabel.setBounds(10, 70, 80, 20);
        passwordField.setBounds(100, 70, 150, 20);
        saveButton.setBounds(10, 100, 80, 30);

        addAdminFrame.add(idLabel);
        addAdminFrame.add(idField);
        addAdminFrame.add(nameLabel);
        addAdminFrame.add(nameField);
        addAdminFrame.add(passwordLabel);
        addAdminFrame.add(passwordField);
        addAdminFrame.add(saveButton);
        addAdminFrame.setLocationRelativeTo(null);
        addAdminFrame.setVisible(true);
    }

    private void editAdmin() 
	{
        int selectedRow = adminTable.getSelectedRow();
        if (selectedRow != -1) 
		{
            ArrayList<String> adminData = getSelectedAdminTableRow(selectedRow);
            String id = adminData.get(0);
            String name = adminData.get(1);
            String password = adminData.get(2);

            JFrame editAdminFrame = new JFrame("Edit Admin");
            editAdminFrame.setSize(300, 200);
            editAdminFrame.setLayout(null);

            JLabel idLabel = new JLabel("ID:");
            JTextField idField = new JTextField(id);

            JLabel nameLabel = new JLabel("Name:");
            JTextField nameField = new JTextField(name);

            JLabel passwordLabel = new JLabel("Password:");
            JTextField passwordField = new JTextField(password);

            JButton saveButton = new JButton("Save");

            saveButton.addActionListener(e -> {
                String editedId = idField.getText();
                String editedName = nameField.getText();
                String editedPassword = passwordField.getText();

                updateSelectedAdminData(editedId, editedName, editedPassword);
                saveAdminDataToFile();

                editAdminFrame.dispose();
            });

            idLabel.setBounds(10, 10, 80, 20);
            idField.setBounds(100, 10, 150, 20);
            nameLabel.setBounds(10, 40, 80, 20);
            nameField.setBounds(100, 40, 150, 20);
            passwordLabel.setBounds(10, 70, 80, 20);
            passwordField.setBounds(100, 70, 150, 20);
            saveButton.setBounds(10, 100, 80, 30);

            editAdminFrame.add(idLabel);
            editAdminFrame.add(idField);
            editAdminFrame.add(nameLabel);
            editAdminFrame.add(nameField);
            editAdminFrame.add(passwordLabel);
            editAdminFrame.add(passwordField);
            editAdminFrame.add(saveButton);
            editAdminFrame.setLocationRelativeTo(null);
            editAdminFrame.setVisible(true);
        } 
		else 
		{
            JOptionPane.showMessageDialog(this, "Please select an admin to edit.");
        }
    }

    private void deleteAdmin() 
	{
        int selectedRow = adminTable.getSelectedRow();
        if (selectedRow != -1) 
		{
            DefaultTableModel adminModel = (DefaultTableModel) adminTable.getModel();
            adminModel.removeRow(selectedRow);
            saveAdminDataToFile();
        } 
		else 
		{
            JOptionPane.showMessageDialog(this, "Please select an admin to delete.");
        }
    }

    private void saveAdminDataToFile()
	{
    File file = new File("data\\admin_data.txt");

    try 
	{
		FileWriter fileWriter = new FileWriter(file, false); 
        DefaultTableModel adminModel = (DefaultTableModel) adminTable.getModel();
        int rowCount = adminModel.getRowCount();
        for (int i = 0; i < rowCount; i++) 
		{
            StringBuilder line = new StringBuilder();
            for (int j = 0; j < adminModel.getColumnCount(); j++) 
			{
                line.append(adminModel.getValueAt(i, j)).append(":");
            }
            if (line.length() > 0) 
			{
                line.setLength(line.length() - 1);
            }
            fileWriter.write(line.toString() + System.lineSeparator());  
        }
		fileWriter.close();
    } 
	catch (IOException e) 
	{ 
        JOptionPane.showMessageDialog(this, "Error saving admin data.");
    }
}


    private ArrayList<String> getSelectedAdminTableRow(int selectedRow) 
	{
        ArrayList<String> adminData = new ArrayList<>();
        DefaultTableModel model = (DefaultTableModel) adminTable.getModel();
        for (int i = 0; i < model.getColumnCount(); i++) 
		{
            adminData.add((String) model.getValueAt(selectedRow, i));
        } 
        return adminData;
    }

    private void updateSelectedAdminData(String editedId, String editedName, String editedPassword)
	{ 
        int selectedRow = adminTable.getSelectedRow();
        if (selectedRow != -1) 
		{ 
            DefaultTableModel model = (DefaultTableModel) adminTable.getModel();
            model.setValueAt(editedId, selectedRow, 0);
            model.setValueAt(editedName, selectedRow, 1);
            model.setValueAt(editedPassword, selectedRow, 2);
        } 
		else 
		{ 
            JOptionPane.showMessageDialog(this, "Please select an admin to update.");
        }
    }
}
