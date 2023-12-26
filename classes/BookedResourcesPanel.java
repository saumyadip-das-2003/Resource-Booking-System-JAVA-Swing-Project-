package classes;
import interfaces.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class BookedResourcesPanel extends JPanel 
{
    private JTable bookedResourcesTable;

    public BookedResourcesPanel() 
	{
        setLayout(new BorderLayout());
        showBookedResourcesPanel(); 
    }

    private void showBookedResourcesPanel() 
	{
        JPanel bookedResourcesPanel = new JPanel(new BorderLayout());

        bookedResourcesTable = new JTable();
        DefaultTableModel bookedResourcesModel = new DefaultTableModel();
        bookedResourcesModel.addColumn("ID");
        bookedResourcesModel.addColumn("Category");
        bookedResourcesModel.addColumn("Slot");
        bookedResourcesModel.addColumn("Reference");
        bookedResourcesModel.addColumn("For");

        loadBookedResourcesDataFromFile("data\\booked_student_resource_data.txt", bookedResourcesModel);
        loadBookedResourcesDataFromFile("data\\booked_teacher_resource_data.txt", bookedResourcesModel);

        bookedResourcesTable.setModel(bookedResourcesModel);
        JScrollPane bookedResourcesScrollPane = new JScrollPane(bookedResourcesTable);

        bookedResourcesPanel.add(bookedResourcesScrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton clearBookingButton = new JButton("Clear Booking");
		
		clearBookingButton.setBackground( new Color(255, 255, 255) );

		clearBookingButton.setFont(new Font("Arial", Font.BOLD, 14));
		

        buttonPanel.add(clearBookingButton);
        bookedResourcesPanel.add(buttonPanel, BorderLayout.SOUTH);

        this.add(bookedResourcesPanel);
        clearBookingButton.addActionListener(e -> clearBooking());
    }

    private void loadBookedResourcesDataFromFile(String fileName, DefaultTableModel bookedResourcesModel) 
	{
        try 
		{
            Scanner scanner = new Scanner(new File(fileName));
            while (scanner.hasNextLine()) 
			{
                String line = scanner.nextLine();
                String[] fields = line.split(":");
                bookedResourcesModel.addRow(fields);
            }
            scanner.close();
        } 
		catch (IOException ex) 
		{
            handleFileError("Error reading booked resources data from " + fileName);
        }
    }

    private void clearBooking() 
	{
        int selectedRow = bookedResourcesTable.getSelectedRow();
        if (selectedRow != -1) 
		{ 
            DefaultTableModel bookedResourcesModel = (DefaultTableModel) bookedResourcesTable.getModel();
            String forValue = (String) bookedResourcesModel.getValueAt(selectedRow, 4);
            String fileName = determineFileNameFromTable(forValue);
	    
            int rowIndexInModel = bookedResourcesTable.convertRowIndexToModel(selectedRow);
	    
            bookedResourcesModel.removeRow(selectedRow); 
            deleteEntryFromFile(fileName, rowIndexInModel);
        } 
		else 
		{ 
            JOptionPane.showMessageDialog(this, "Please select a row to clear booking.");
        }
    }


    private void deleteEntryFromFile(String fileName, int rowIndex) {
        try {
            File inputFile = new File(fileName);
            Scanner scanner = new Scanner(inputFile);
            ArrayList<String> lines = new ArrayList<>();
    
            while (scanner.hasNextLine()) 
			{
                lines.add(scanner.nextLine());
            }
            scanner.close();
            if (lines.isEmpty()) 
			{
				
            } 
			else 
			{
                if (rowIndex >= 0 && rowIndex < lines.size())
					{
                    lines.remove(rowIndex); 
                    try (FileWriter writer = new FileWriter(fileName)) 
					{
                        for (int i = 0; i < lines.size(); i++) 
						{
                            writer.write(lines.get(i) + System.lineSeparator());
                        } 
                    }
                } else {
                    handleFileError("Invalid row index: " + rowIndex);
                }
            }
        } catch (IOException ex) {
            handleFileError("Error deleting entry from file: " + fileName);
        }
    } 

    private void WriteToFile(String fileName, String content) throws IOException 
	{
        try (FileWriter writer = new FileWriter(fileName)) 
		{
            writer.write(content); 
        }
    }

    private String determineFileNameFromTable(String forValue) 
	{
        String fileName = "data\\booked_teacher_resource_data.txt";
        if ("Student".equals(forValue)) 
		{
            fileName = "data\\booked_student_resource_data.txt";
        } 
        return fileName;
    }

    private void handleFileError(String errorMessage) 
	{ 
        JOptionPane.showMessageDialog(this, errorMessage);
    }
}
