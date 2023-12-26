package classes;
import interfaces.IStudentDashboard; 

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Scanner;

public class StudentDashboard extends JFrame implements ActionListener, IStudentDashboard   
{
    private JLabel welcomeLabel;
    private JPanel buttonPanel;
    private JButton myBookingsButton;
    private JButton bookNewButton;
    private JButton userInfoButton;
    private JButton logoutButton;
    private JPanel mainPanel;
    private JTable resourceTable;
    private String loggedInName; 
    private String loggedInID;
    private JButton bookButton;
    private JPanel myBookingsPanel; 
	private ImageIcon aiubImage; 	
	private JLabel aiubImagelabel ;

    public StudentDashboard(String loggedInName, String loggedInID) 
	{
        this.loggedInName = loggedInName;
        this.loggedInID = loggedInID;

        welcomeLabel = new JLabel("Welcome, Student: " + loggedInName + " (" + loggedInID + ")");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        buttonPanel = new JPanel();
        myBookingsPanel = new JPanel();
        myBookingsButton = new JButton("My Bookings");
        bookNewButton = new JButton("Book New Resource");
        userInfoButton = new JButton("User Info");
        logoutButton = new JButton("Log Out");
        mainPanel = new JPanel();
        resourceTable = new JTable();
        bookButton = new JButton("Book");
		aiubImage = new ImageIcon ("image\\Background2.jpg");
		aiubImagelabel = new JLabel(aiubImage);

        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(myBookingsButton);
        buttonPanel.add(bookNewButton);
        buttonPanel.add(userInfoButton);

        mainPanel.add(bookButton);
        bookButton.setVisible(false);

        welcomeLabel.setBounds(10, 10, 500, 30);
        buttonPanel.setBounds(30, 50, 500, 40);
        logoutButton.setBounds(550, 10, 100, 30);
		aiubImagelabel.setBounds(100, 100, aiubImage.getIconWidth(), aiubImage.getIconHeight());;
		
		logoutButton.setBackground( new Color(255, 255, 255) );
		myBookingsButton.setBackground( new Color(255, 255, 255) );
		bookNewButton.setBackground( new Color(255, 255, 255) );
		userInfoButton.setBackground( new Color(255, 255, 255) );
		buttonPanel.setBackground( new Color(51, 153, 255) ); 
		
        
		
		logoutButton.setFont(new Font("Arial", Font.BOLD, 16));
		buttonPanel.setFont(new Font("Arial", Font.BOLD, 16));

        add(welcomeLabel);
        add(buttonPanel);
        add(logoutButton);
        add(mainPanel);
		add(aiubImagelabel);

        setTitle("AIUB Resource Booking System - Student Dashboard");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);
		getContentPane().setBackground( new Color(51, 153, 255) );
		setIconImage(new ImageIcon("image\\logo.png").getImage());

        loadStudentResourcesData((DefaultTableModel) resourceTable.getModel());

        myBookingsButton.addActionListener(this);
        bookNewButton.addActionListener(this);
        userInfoButton.addActionListener(this);
        logoutButton.addActionListener(this);
        bookButton.addActionListener(this);
    }

    public void loadStudentResourcesData(DefaultTableModel model) 
	{ 
        try 
		{
            Scanner scanner = new Scanner(new FileReader("data\\student_resource_data.txt"));
            while (scanner.hasNextLine()) 
			{
                String line = scanner.nextLine();
                String[] fields = line.split(":");
                if (fields.length == 4) 
				{
                    model.addRow(fields);
                } else 
				{
                    JOptionPane.showMessageDialog(this, "Error loading resource. Invalid format in the file.");
                }
            }
        }  
		catch (IOException e) 
		{
            JOptionPane.showMessageDialog(this, "Error loading resource.");
        }
    } 



    public void actionPerformed(ActionEvent e) 
	{
        if (e.getSource() == myBookingsButton) 
		{
            showMyBookingsTable();
        } 
		else if (e.getSource() == bookNewButton) 
		{
            showBookNewPanel();
        } 
		else if (e.getSource() == userInfoButton) 
		{
            showUserInfo();
        } 
		else if (e.getSource() == logoutButton) 
		{
            logout();
        } 
		else if (e.getSource() == bookButton) 
		{
            bookResource(); 
        }
    }

    public void showBookNewPanel()  
	{
        String[] columns = {"Category", "Days", "Reference", "For"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        loadStudentResourcesData(model);
        resourceTable.setModel(model);
        JScrollPane resourceScrollPane = new JScrollPane(resourceTable);
        mainPanel.removeAll();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(resourceScrollPane, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.add(bookButton);
        bookButton.setVisible(true);
		bookButton.setBackground( new Color(255, 255, 255) );
		bookButton.setFont(new Font("Arial", Font.BOLD, 14));
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        mainPanel.setBounds(30, 90, 700, 400);
        getContentPane().removeAll();
        getContentPane().setLayout(null);
        getContentPane().add(welcomeLabel);
        getContentPane().add(this.buttonPanel);
        getContentPane().add(logoutButton);
        getContentPane().add(mainPanel);
        revalidate();
        repaint();
    }

    public void bookResource() 
	{
        int selectedRow = resourceTable.getSelectedRow();
        if (selectedRow != -1) 
		{
            String id = loggedInID;
            String category = resourceTable.getValueAt(selectedRow, 0).toString();
            String slot = resourceTable.getValueAt(selectedRow, 1).toString();
            String reference = resourceTable.getValueAt(selectedRow, 2).toString();
            String forValue = resourceTable.getValueAt(selectedRow, 3).toString();
            String bookedData = id + ":" + category + ":" + slot + ":" + reference + ":" + forValue;
            String bookedResourceFilePath = "data\\booked_student_resource_data.txt";
            String studentResourceFilePath = "data\\student_resource_data.txt";
            try (FileWriter bookedFileWriter = new FileWriter(bookedResourceFilePath, true);
            FileWriter studentResourceFileWriter = new FileWriter(studentResourceFilePath, true))  
			{
                bookedFileWriter.write(bookedData + System.lineSeparator());
                deleteResource();
                JOptionPane.showMessageDialog(null, "Resource booked successfully!", "Message", JOptionPane.PLAIN_MESSAGE);
            } 
			catch (IOException ex) 
			{ 
                JOptionPane.showMessageDialog(this, "Error booking resource.");
            }
        } 
		else 
		{ 
            JOptionPane.showMessageDialog(this, "Please select a row to book a resource.");
        }
    }

    public void deleteResource() 
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

    public void saveResourceDataToFile() 
	{
        File file = new File("data\\student_resource_data.txt");
        try {
			FileWriter fileWriter = new FileWriter(file);
            DefaultTableModel resourceModel = (DefaultTableModel) resourceTable.getModel();
            int rowCount = resourceModel.getRowCount();
            for (int i = 0; i < rowCount; i++) 
			{
                StringBuilder line = new StringBuilder();
                for (int j = 0; j < resourceModel.getColumnCount(); j++) 
				{
                    line.append(resourceModel.getValueAt(i, j));
                    if (j < resourceModel.getColumnCount() - 1) 
					{
                        line.append(":"); 
                    }
                }
                line.append(System.lineSeparator());
                fileWriter.write(line.toString());
            }
			fileWriter.close();
        } 
		catch (IOException e) 
		{ 
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error saving resource data.");
        }
    }

    public void showMyBookingsTable()  
	{
        String[] columns = {"ID", "Category", "Slot", "Reference", "For"};
        DefaultTableModel bookingsTableModel = new DefaultTableModel(columns, 0);
        loadMyBookingsData(bookingsTableModel, loggedInID);
        JTable bookingsTable = new JTable(bookingsTableModel);
        JScrollPane bookingsScrollPane = new JScrollPane(bookingsTable);
        myBookingsPanel = new JPanel();
        myBookingsPanel.setLayout(new BorderLayout());
        myBookingsPanel.add(bookingsScrollPane, BorderLayout.CENTER);
        myBookingsPanel.add(buttonPanel, BorderLayout.SOUTH);
        myBookingsPanel.setBounds(30, 90, 700, 400);
        getContentPane().removeAll();
        getContentPane().setLayout(null);
        getContentPane().add(welcomeLabel);
        getContentPane().add(this.buttonPanel);
        getContentPane().add(logoutButton);
        getContentPane().add(myBookingsPanel);
        revalidate();
        repaint();
    }

    public void loadMyBookingsData(DefaultTableModel model, String userID) 
	{
        try 
		{
			Scanner scanner = new Scanner(new FileReader("data\\booked_student_resource_data.txt")); 
            while (scanner.hasNextLine()) 
			{
                String line = scanner.nextLine();
                String[] fields = line.split(":");
                if (fields.length == 5 && userID.equals(fields[0])) 
				{
                    model.addRow(fields); 
                }
            }
        } catch (IOException e) 
		{
            e.printStackTrace(); 
        }
    }

    public void showUserInfo() 
	{
        new UserInfoFrame(loggedInID).setVisible(true);
    } 

    public void logout() 
	{
        this.setVisible(false); 
        new LoginFrame().setVisible(true);
    }

    public static void main(String[] args) 
	{  
        new StudentDashboard("Hello", "Test").setVisible(true); 
    }
}
