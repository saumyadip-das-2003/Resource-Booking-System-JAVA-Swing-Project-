package classes;
import interfaces.ILoginFrame;

import java.lang.*;
import javax.swing.*;
import javax.swing.ImageIcon;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener; 
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class LoginFrame extends JFrame implements ActionListener, ILoginFrame  
{
    private JTextField userIdField;
    private JPasswordField passwordField;
    private ButtonGroup roleGroup;
    private JRadioButton teacherRadioButton;
    private JRadioButton studentRadioButton;
    private JToggleButton showHideButton;
    private JButton loginButton;
    private JButton registerButton;
    private JButton exitButton;
    private JButton adminButton;
    private JButton creditsButton;
    private JButton backButton;
    private JLabel idLabel;
    private JLabel universityLabel;
    private JLabel systemLabel;
    private JLabel passwordLabel;
    private JLabel roleLabel;
    private boolean showPassword = false;
	private JLabel logolabel ;
	private JLabel aiubImagelabel ;
	private ImageIcon logo; 
	private ImageIcon aiubImage; 
       

    public LoginFrame() 
	{
        // object creation 
        universityLabel = new JLabel("American International University- Bangladesh");
        universityLabel.setFont(new Font("Arial", Font.BOLD, 16));
        systemLabel = new JLabel("Resource Booking System");
        systemLabel.setFont(new Font("Arial", Font.BOLD, 16));
        idLabel = new JLabel("ID:");
        userIdField = new JTextField();
        passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();
        passwordField.setEchoChar('*');
        roleLabel = new JLabel("Role:");
        roleGroup = new ButtonGroup();
        teacherRadioButton = new JRadioButton("Teacher");
        studentRadioButton = new JRadioButton("Student");
        roleGroup.add(teacherRadioButton);
        roleGroup.add(studentRadioButton);
        loginButton = new JButton("Login");
        registerButton = new JButton("Register");
        exitButton = new JButton("Exit");
        adminButton = new JButton("Admin");
        creditsButton = new JButton("Credits");
        backButton = new JButton("Back to Intro");
        showHideButton = new JToggleButton("Show");
		logo = new ImageIcon ("image\\logo.png");
		aiubImage = new ImageIcon ("image\\Background1.jpg");
		logolabel = new JLabel(logo);
		aiubImagelabel = new JLabel(aiubImage);
		
		

        // Set bounds
        universityLabel.setBounds(120, 10, 500, 20);
        systemLabel.setBounds(200, 30, 300, 20);
        idLabel.setBounds(50, 80, 100, 30);
        userIdField.setBounds(160, 80, 150, 30);
        passwordLabel.setBounds(50, 130, 100, 30);
        passwordField.setBounds(160, 130, 150, 30);
        roleLabel.setBounds(50, 180, 100, 30);
        teacherRadioButton.setBounds(160, 180, 80, 30);
        studentRadioButton.setBounds(250, 180, 80, 30);
        loginButton.setBounds(50, 230, 80, 30);
        registerButton.setBounds(140, 230, 120, 30);
        exitButton.setBounds(270, 230, 80, 30);
        adminButton.setBounds(50, 500, 80, 30);
        creditsButton.setBounds(140, 500, 100, 30);
        backButton.setBounds(250, 500, 130, 30);
        showHideButton.setBounds(320, 130, 70, 30);
		logolabel.setBounds(15, 2, logo.getIconWidth(), logo.getIconHeight());
		aiubImagelabel.setBounds(4, 300, aiubImage.getIconWidth(), aiubImage.getIconHeight());
		
		loginButton.setBackground( new Color(255, 255, 255) );
		registerButton.setBackground( new Color(255, 255, 255) );
        exitButton.setBackground( new Color(255, 255, 255) );
        adminButton.setBackground( new Color(255, 255, 255) );
        creditsButton.setBackground( new Color(255, 255, 255) );
        backButton.setBackground( new Color(255, 255, 255) );
        showHideButton.setBackground( new Color(255, 255, 255) );
		teacherRadioButton.setBackground( new Color(51, 153, 255) );
        studentRadioButton.setBackground( new Color(51, 153, 255) );
		universityLabel.setForeground(new Color(0, 0, 0));
		systemLabel.setForeground(new Color(0, 0, 0)); 
		
		idLabel.setFont(new Font("Arial", Font.BOLD, 16));
		passwordLabel.setFont(new Font("Arial", Font.BOLD, 16));
		roleLabel.setFont(new Font("Arial", Font.BOLD, 16));
		loginButton.setFont(new Font("Arial", Font.BOLD, 14));
		registerButton.setFont(new Font("Arial", Font.BOLD, 14));
		exitButton.setFont(new Font("Arial", Font.BOLD, 14));
		adminButton.setFont(new Font("Arial", Font.BOLD, 14));
		creditsButton.setFont(new Font("Arial", Font.BOLD, 14));
		backButton.setFont(new Font("Arial", Font.BOLD, 14));

     	add(universityLabel);
		add(systemLabel);
		add(idLabel);
        add(userIdField);
        add(passwordLabel);
        add(passwordField);
        add(roleLabel);
        add(teacherRadioButton);
        add(studentRadioButton);
        add(loginButton);
        add(registerButton);
        add(exitButton);
        add(adminButton);
        add(creditsButton);
        add(backButton);
        add(showHideButton);
        add(logolabel);
		add(aiubImagelabel);

        // Frame properties
		setTitle("AIUB Resource Booking System- Login");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground( new Color(51, 153, 255) );
        setLocationRelativeTo(null);
		setIconImage(logo.getImage());

        // Setting default role
        teacherRadioButton.setSelected(true);

        // Adding ActionListeners
        loginButton.addActionListener(this);
        registerButton.addActionListener(this);
        exitButton.addActionListener(this);
        adminButton.addActionListener(this);
        creditsButton.addActionListener(this);
        backButton.addActionListener(this);
        showHideButton.addActionListener(this);
    }
	
	

    public void actionPerformed(ActionEvent e) 
	{
        if (e.getSource() == loginButton) 
		{
            login();
        } 
		else if (e.getSource() == registerButton) 
		{
            openRegistrationFrame();
        } 
		else if (e.getSource() == exitButton) 
		{
            System.exit(0);
        } 
		else if (e.getSource() == adminButton) 
		{
            openAdminLoginFrame();  
        } 
		else if (e.getSource() == creditsButton) 
		{
            showCredits();
        } 
		else if (e.getSource() == showHideButton) 
		{
            showHidePassword();
        }else if (e.getSource() == backButton) 
		{
            backToIntro();
        }
    }

    public void login() 
	{
        String id = userIdField.getText();
        String password = new String(passwordField.getPassword());
        String role = teacherRadioButton.isSelected() ? "Teacher" : "Student";

        try 
		{
            Scanner scanner = new Scanner(new File("data\\user_data.txt"));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] fields = line.split(":");
                if (fields.length == 5) {
                    String storedName = fields[0].trim();
                    String storedID = fields[1].trim();
                    String storedPassword = fields[2].trim(); 
                    String storedRole = fields[3].trim();
                    String storedDepartment = fields[4].trim();

                    if (storedRole.equalsIgnoreCase(role) && id.equals(storedID) && password.equals(storedPassword)) 
                    {
                        openDashboard(storedName, storedID, storedRole);
                        return;
                    } 

                }
            }
            JOptionPane.showMessageDialog(this, "Invalid credentials. Please try again.");
        } 
		catch (IOException ioe) 
		{
            JOptionPane.showMessageDialog(this, "Error reading user data.");
        }
    }

    public void openDashboard(String name, String id, String role) 
	{
        if ("Teacher".equals(role)) 
		{
            new TeacherDashboard(name, id).setVisible(true);
        } 
		else if ("Student".equals(role)) 
		{
            new StudentDashboard(name, id).setVisible(true);
        }
        this.setVisible(false);
    }


    public void openRegistrationFrame() 
	{
        new RegistrationFrame().setVisible(true);
        this.setVisible(false);
    }

    public void openAdminLoginFrame()  
	{
        new AdminLoginFrame().setVisible(true);
        this.setVisible(false);
    }

    public void showCredits() 
	{
		String message = "Saumyadip Das\nNiloy Paul\nTonmoy Sarkar\nSabbir Hasan Tanim";
        JOptionPane.showMessageDialog(null, message, "Credit", JOptionPane.PLAIN_MESSAGE); 
    }

    public void showHidePassword() 
	{
        if (showPassword) 
		{
            passwordField.setEchoChar('*');
        } else 
		{ 
            passwordField.setEchoChar((char) 0);
        }
        showPassword = !showPassword; 
    } 
	
	public void backToIntro()
	{
		new IntroFrame().setVisible(true); 
		this.setVisible(false); 
	}
	
	public static void main(String[] args) 
	{
        new LoginFrame().setVisible(true);       
    }
}
