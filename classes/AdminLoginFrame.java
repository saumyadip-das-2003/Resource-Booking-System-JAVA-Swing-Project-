 package classes;
import interfaces.*;

import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class AdminLoginFrame extends JFrame implements ActionListener 
{ 
    private JTextField idField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton backButton;
	private JLabel universityLabel;
    private JLabel systemLabel; 
	private JLabel logolabel ;
	private JLabel aiubImagelabel ;
	private ImageIcon logo; 
	private ImageIcon aiubImage; 
       

    public AdminLoginFrame() 
	{ 
        // Instantiations
        JLabel idLabel = new JLabel("Id:");
        JLabel passwordLabel = new JLabel("Password:");
        idField = new JTextField();
        passwordField = new JPasswordField();
        loginButton = new JButton("Login");
        backButton = new JButton("Back");
		universityLabel = new JLabel("American International University- Bangladesh");
        universityLabel.setFont(new Font("Arial", Font.BOLD, 16));
		systemLabel = new JLabel("Resource Booking System");
        systemLabel.setFont(new Font("Arial", Font.BOLD, 16));
		logo = new ImageIcon ("image\\logo.png");
		aiubImage = new ImageIcon ("image\\Background1.jpg");
		logolabel = new JLabel(logo);
		aiubImagelabel = new JLabel(aiubImage);

        // Set bounds for components
        universityLabel.setBounds(120, 10, 500, 20);
        systemLabel.setBounds(200, 30, 300, 20);
        idLabel.setBounds(50, 80, 100, 30);
        idField.setBounds(160, 80, 150, 30);
        passwordLabel.setBounds(50, 130, 100, 30);
        passwordField.setBounds(160, 130, 150, 30);
        loginButton.setBounds(50, 230, 80, 30);
        backButton.setBounds(140, 230, 80, 30);
		
		loginButton.setBackground( new Color(255, 255, 255) );
        backButton.setBackground( new Color(255, 255, 255) );
		universityLabel.setForeground(new Color(0, 0, 0));
		systemLabel.setForeground(new Color(0, 0, 0));  
		logolabel.setBounds(15, 2, logo.getIconWidth(), logo.getIconHeight());
		aiubImagelabel.setBounds(4, 300, aiubImage.getIconWidth(), aiubImage.getIconHeight());
		
		idLabel.setFont(new Font("Arial", Font.BOLD, 16));
		passwordLabel.setFont(new Font("Arial", Font.BOLD, 16));
		loginButton.setFont(new Font("Arial", Font.BOLD, 14));
		backButton.setFont(new Font("Arial", Font.BOLD, 14));
		

        // Add components to the frame
        add(universityLabel);
		add(systemLabel);
		add(idLabel);
        add(passwordLabel);
        add(idField);
        add(passwordField);
        add(loginButton);
        add(backButton);
		add(logolabel);
		add(aiubImagelabel);

        // Frame properties
        setTitle("Admin Login");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);
		getContentPane().setBackground( new Color(51, 153, 255) );
		setIconImage(logo.getImage());

        // Add action listeners to buttons
        loginButton.addActionListener(this);
        backButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) 
	{ 
        if (e.getSource() == loginButton) 
		{ 
            login();
        } else if (e.getSource() == backButton) 
		{ 
            goBackToLogin();
        }
    }

    private void login() 
	{ 
        String id = idField.getText();
        String username = idField.getText();
        String password = new String(passwordField.getPassword());
	    
        try 
		{
			Scanner scanner = new Scanner(new File("data\\admin_data.txt")); 
            while (scanner.hasNextLine()) 
			{
                String line = scanner.nextLine();
                String[] fields = line.split(":");
                if (fields.length == 3) {
                    String storedId = fields[0].trim();
                    String storedUsername = fields[1].trim();
                    String storedPassword = fields[2].trim();
	    
                    if (id.equals(storedId) && username.equals(storedUsername) && password.equals(storedPassword)) 
					{
                        openAdminFrame();  
                        return;
                    }
                }
            }
            JOptionPane.showMessageDialog(this, "Invalid credentials. Please try again.");
        } 
		catch (IOException ioe) 
		{ 
            JOptionPane.showMessageDialog(this, "Error reading admin credentials.");
        }
    }

    private void openAdminFrame() 
	{ 
        this.setVisible(false);
        AdminFrame adminFrame = new AdminFrame();
        adminFrame.setVisible(true); 
    }

    private void goBackToLogin() 
	{
        this.setVisible(false);
        new LoginFrame().setVisible(true);
    } 

    public static void main(String[] args) 
	{
        new AdminLoginFrame().setVisible(true);
    } 
}
