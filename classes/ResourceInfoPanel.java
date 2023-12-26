package classes;
import interfaces.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ResourceInfoPanel extends JPanel 
{
    private static final String TEACHER_FILE = "data\\teacher_resource_data.txt";
    private static final String STUDENT_FILE = "data\\student_resource_data.txt";
    private JTable resourceTable;

    public ResourceInfoPanel() 
	{
        setLayout(new BorderLayout());
        showResourceInfoPanel();
    }

    private void showResourceInfoPanel() 
	{
        JPanel resourceInfoPanel = new JPanel(new BorderLayout());
        resourceTable = new JTable();
        DefaultTableModel resourceModel = new DefaultTableModel();
        resourceModel.addColumn("Category");
        resourceModel.addColumn("Slot");
        resourceModel.addColumn("Reference");
        resourceModel.addColumn("For");

        loadResourceDataFromFile(STUDENT_FILE, resourceModel);
        loadResourceDataFromFile(TEACHER_FILE, resourceModel);

        resourceTable.setModel(resourceModel);
        JScrollPane resourceScrollPane = new JScrollPane(resourceTable);

        resourceInfoPanel.add(resourceScrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton addResourceButton = new JButton("Add");
        JButton editResourceButton = new JButton("Edit");
        JButton deleteResourceButton = new JButton("Delete");
		
		addResourceButton.setBackground( new Color(255, 255, 255) );
		editResourceButton.setBackground( new Color(255, 255, 255) );
        deleteResourceButton.setBackground( new Color(255, 255, 255) );
        
		
		addResourceButton.setFont(new Font("Arial", Font.BOLD, 14));
		editResourceButton.setFont(new Font("Arial", Font.BOLD, 14));
		deleteResourceButton.setFont(new Font("Arial", Font.BOLD, 14));

        buttonPanel.add(addResourceButton);
        buttonPanel.add(editResourceButton);
        buttonPanel.add(deleteResourceButton);
        resourceInfoPanel.add(buttonPanel, BorderLayout.SOUTH);

        this.add(resourceInfoPanel);
        addResourceButton.addActionListener(e -> addResource());
        editResourceButton.addActionListener(e -> editResource());
        deleteResourceButton.addActionListener(e -> deleteResource());
    }

    private void loadResourceDataFromFile(String fileName, DefaultTableModel resourceModel) 
	{
        try 
		{
            Scanner scanner = new Scanner(new File(fileName));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] fields = line.split(":");
                for (int i = 0; i < fields.length; i++) {
                    fields[i] = fields[i].trim();
                }

                resourceModel.addRow(fields);
            }
            scanner.close(); 
        } 
		catch (IOException ex) 
		{
            handleFileError("Error reading resource data from " + fileName);
        } 
    }

    private void addResource() 
	{
        JFrame addResourceFrame = new JFrame("Add Resource");
        addResourceFrame.setSize(370, 250);
        addResourceFrame.setLayout(null);

        JLabel categoryLabel = new JLabel("Category:");
        JTextField categoryField = new JTextField();
        categoryLabel.setBounds(10, 10, 80, 20);
        categoryField.setBounds(100, 10, 150, 20);

        JLabel timeLabel = new JLabel("Slot:");
        JTextField timeField = new JTextField();
        timeLabel.setBounds(10, 40, 80, 20);
        timeField.setBounds(100, 40, 150, 20);

        JLabel referenceLabel = new JLabel("Reference:");
        JTextField referenceField = new JTextField();
        referenceLabel.setBounds(10, 70, 80, 20);
        referenceField.setBounds(100, 70, 150, 20);

        JLabel forLabel = new JLabel("For:");
        forLabel.setBounds(10, 100, 80, 20);

        ButtonGroup forButtonGroup = new ButtonGroup();
        JRadioButton teacherRadioButton = new JRadioButton("Teacher");
        JRadioButton studentRadioButton = new JRadioButton("Student");
        JButton saveButton = new JButton("Save");

        forButtonGroup.add(teacherRadioButton);
        forButtonGroup.add(studentRadioButton);

        teacherRadioButton.setBounds(100, 100, 80, 20);
        studentRadioButton.setBounds(180, 100, 80, 20);
        saveButton.setBounds(10, 130, 80, 30);

        saveButton.addActionListener(e -> {
            String category = categoryField.getText();
            String time = timeField.getText();
            String reference = referenceField.getText();

            String forValue = "";
            if (teacherRadioButton.isSelected()) 
			{
                forValue = "Teacher";
            } 
			else if (studentRadioButton.isSelected()) {
                forValue = "Student";
            } 

            DefaultTableModel resourceModel = (DefaultTableModel) resourceTable.getModel();
            resourceModel.addRow(new Object[]{category, time, reference, forValue});
            saveResourceDataToFile();

            addResourceFrame.dispose();
        });

        addResourceFrame.add(categoryLabel);
        addResourceFrame.add(categoryField);
        addResourceFrame.add(timeLabel);
        addResourceFrame.add(timeField);
        addResourceFrame.add(referenceLabel);
        addResourceFrame.add(referenceField);
        addResourceFrame.add(forLabel);
        addResourceFrame.add(teacherRadioButton);
        addResourceFrame.add(studentRadioButton);
        addResourceFrame.add(saveButton);
        addResourceFrame.setLocationRelativeTo(null);
        addResourceFrame.setVisible(true);
    }

    private void editResource() 
	{
        int selectedRow = resourceTable.getSelectedRow();
        if (selectedRow != -1) 
		{
            ArrayList<String> resourceData = getSelectedResourceTableRow(selectedRow);
            String category = resourceData.get(0);
            String time = resourceData.get(1);
            String reference = resourceData.get(2);
            String forValue = resourceData.get(3);

            JFrame editResourceFrame = new JFrame("Edit Resource");
            editResourceFrame.setSize(370, 250);
            editResourceFrame.setLayout(null);

            JLabel categoryLabel = new JLabel("Category:");
            JTextField categoryField = new JTextField(category);
            categoryLabel.setBounds(10, 10, 80, 20);
            categoryField.setBounds(100, 10, 150, 20);

            JLabel timeLabel = new JLabel("Slot:");
            JTextField timeField = new JTextField(time);
            timeLabel.setBounds(10, 40, 80, 20);
            timeField.setBounds(100, 40, 150, 20);

            JLabel referenceLabel = new JLabel("Reference:");
            JTextField referenceField = new JTextField(reference);
            referenceLabel.setBounds(10, 70, 80, 20);
            referenceField.setBounds(100, 70, 150, 20);

            JLabel forLabel = new JLabel("For:");
            forLabel.setBounds(10, 100, 80, 20);

            ButtonGroup forButtonGroup = new ButtonGroup();
            JRadioButton teacherRadioButton = new JRadioButton("Teacher");
            JRadioButton studentRadioButton = new JRadioButton("Student");
            JButton saveButton = new JButton("Save");

            forButtonGroup.add(teacherRadioButton);
            forButtonGroup.add(studentRadioButton);

            switch (forValue) 
			{
                case "Teacher":
                    teacherRadioButton.setSelected(true);
                    break;
                case "Student":
                    studentRadioButton.setSelected(true);
                    break;
            }

            teacherRadioButton.setBounds(100, 100, 80, 20);
            studentRadioButton.setBounds(180, 100, 80, 20);
            saveButton.setBounds(10, 130, 80, 30);

            saveButton.addActionListener(e -> {
                String editedCategory = categoryField.getText();
                String editedTime = timeField.getText();
                String editedReference = referenceField.getText();

                String editedForValue = "";
                if (teacherRadioButton.isSelected()) 
				{
                    editedForValue = "Teacher";
                }
				else if (studentRadioButton.isSelected()) 
				{
                    editedForValue = "Student";
                } 

                updateSelectedResourceData(editedCategory, editedTime, editedReference, editedForValue);
                saveResourceDataToFile();

                editResourceFrame.dispose();
            });

            editResourceFrame.add(categoryLabel);
            editResourceFrame.add(categoryField);
            editResourceFrame.add(timeLabel);
            editResourceFrame.add(timeField);
            editResourceFrame.add(referenceLabel);
            editResourceFrame.add(referenceField);
            editResourceFrame.add(forLabel);
			editResourceFrame.add(teacherRadioButton);
            editResourceFrame.add(studentRadioButton); 
            editResourceFrame.add(saveButton);
            editResourceFrame.setLocationRelativeTo(null);
            editResourceFrame.setVisible(true);
        } 
		else 
		{ 
            JOptionPane.showMessageDialog(this, "Please select a resource to edit.");
        }
    }

    private void deleteResource() 
	{
        int selectedRow = resourceTable.getSelectedRow();
        if (selectedRow != -1) 
		{
            DefaultTableModel resourceModel = (DefaultTableModel) resourceTable.getModel();
            resourceModel.removeRow(selectedRow);
            saveResourceDataToFile();
        } 
		else 
		{
            JOptionPane.showMessageDialog(this, "Please select a resource to delete.");
        }
    }

    private ArrayList<String> getSelectedResourceTableRow(int selectedRow) 
	{
        ArrayList<String> resourceData = new ArrayList<>();
        DefaultTableModel model = (DefaultTableModel) resourceTable.getModel();
        for (int i = 0; i < model.getColumnCount(); i++) 
		{
            resourceData.add((String) model.getValueAt(selectedRow, i));
        }
        return resourceData;
    }

    private void updateSelectedResourceData(String editedCategory, String editedTime, String editedReference, String editedForValue) 
	{
        int selectedRow = resourceTable.getSelectedRow();
        if (selectedRow != -1) 
		{
            DefaultTableModel model = (DefaultTableModel) resourceTable.getModel();
            model.setValueAt(editedCategory, selectedRow, 0);
            model.setValueAt(editedTime, selectedRow, 1);
            model.setValueAt(editedReference, selectedRow, 2);
            model.setValueAt(editedForValue, selectedRow, 3);
        } 
		else 
		{
            JOptionPane.showMessageDialog(this, "Please select a resource to update.");
        }
    }

    private void saveResourceDataToFile() 
	{
        try 
		{
            DefaultTableModel resourceModel = (DefaultTableModel) resourceTable.getModel();
            clearFile(TEACHER_FILE);
            clearFile(STUDENT_FILE);

            int rowCount = resourceModel.getRowCount();

            for (int i = 0; i < rowCount; i++) {
                StringBuilder line = new StringBuilder();
                for (int j = 0; j < resourceModel.getColumnCount(); j++) {
                    line.append(resourceModel.getValueAt(i, j)).append(":");
                }
                line.append("\n");

                String forValue = (String) resourceModel.getValueAt(i, 3);
                saveToFile(line.toString(), forValue);
            }

            JOptionPane.showMessageDialog(null, "Resource data saved successfully.", "Message", JOptionPane.PLAIN_MESSAGE); 
        } 
		catch (IOException e) 
		{ 
            handleFileError("Error saving resource data.");
        }
    }

    private void clearFile(String fileName) throws IOException 
	{
        try (FileWriter fileWriter = new FileWriter(fileName)) 
		{ 
            
        } catch (IOException ioe) 
		{
            handleFileError("Error clearing file: " + fileName);
        } 
    }

    private void saveToFile(String data, String forValue) throws IOException 
	{
        String fileName = TEACHER_FILE;
        if ("Student".equals(forValue)) 
		{
            fileName = STUDENT_FILE;
        }

        try (FileWriter fileWriter = new FileWriter(fileName, true)) 
		{
            fileWriter.write(data);
        } catch (IOException ioe) 
		{ 
            handleFileError("Error writing to file: " + fileName);
        }
    }

    private void addRadioButtonToFrame(ButtonGroup buttonGroup, JRadioButton... buttons) {
        for (JRadioButton button : buttons) {
            buttonGroup.add(button);
        }
    }

    private void handleFileError(String errorMessage)  
	{ 
        JOptionPane.showMessageDialog(this, errorMessage);
    }
}
