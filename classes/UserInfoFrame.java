package classes;
import interfaces.IUserInfoFrame; 

import java.lang.*;
import javax.swing.*;
import javax.swing.ImageIcon;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class UserInfoFrame extends JFrame implements ActionListener, IUserInfoFrame  
{
    private JLabel nameLabel;
    private JLabel idLabel;
    private JLabel roleLabel;
    private JLabel departmentLabel;
    private JButton closeButton; 
    private String loggedInID;

    public UserInfoFrame(String loggedInID) 
	{
        this.loggedInID = loggedInID;

        // Object creation
        nameLabel = new JLabel("Name: ");
        idLabel = new JLabel("ID: " + loggedInID);
        roleLabel = new JLabel("Role: ");
        departmentLabel = new JLabel("Department: ");
        closeButton = new JButton("Close");

        // Set bounds
        nameLabel.setBounds(50, 20, 200, 30);
        idLabel.setBounds(50, 60, 200, 30);
        roleLabel.setBounds(50, 100, 200, 30);
        departmentLabel.setBounds(50, 140, 200, 30);
        closeButton.setBounds(90, 175, 80, 25);
		
		closeButton.setBackground( new Color(255, 255, 255) );
		
		idLabel.setFont(new Font("Arial", Font.BOLD, 16));
		nameLabel.setFont(new Font("Arial", Font.BOLD, 16));
		roleLabel.setFont(new Font("Arial", Font.BOLD, 16));
		departmentLabel.setFont(new Font("Arial", Font.BOLD, 16));
		closeButton.setFont(new Font("Arial", Font.BOLD, 14)); 

        // Adding components to the frame
        add(nameLabel);
        add(idLabel);
        add(roleLabel);
        add(departmentLabel);
        add(closeButton);

        // Frame properties
        setTitle("User Info:");
        setSize(250, 250); 
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);
		setIconImage(new ImageIcon("image\\logo.png").getImage());

        // Adding ActionListeners      
        closeButton.addActionListener(this);

        // Loading user data
        loadUserData(); 
    }

    public void loadUserData() 
	{ 
        File file = new File("data\\user_data.txt");

        try 
		{
			Scanner scanner = new Scanner(new FileReader(file)); 
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] fields = line.split(":");
                if (fields.length == 5 && loggedInID.equals(fields[1].trim())) 
				{
                    nameLabel.setText("Name: " + fields[0].trim());
                    roleLabel.setText("Role: " + fields[3].trim());
                    departmentLabel.setText("Department: " + fields[4].trim());
                    return; 
                }
            }
            JOptionPane.showMessageDialog(this, "User information not found.");
            this.dispose(); 
        } 
		catch (IOException ioe) 
		{ 
            JOptionPane.showMessageDialog(this, "Error reading user data.");
        }
    }

    public void actionPerformed(ActionEvent e) 
	{
        if (e.getSource() == closeButton) 
		{
            this.dispose(); 
        }
    }
}
