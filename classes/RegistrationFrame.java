package classes;
import interfaces.IRegistrationFrame; 

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class RegistrationFrame extends JFrame implements ActionListener, IRegistrationFrame  
{  
	private JLabel nameLabel;
	private JLabel idLabel;
	private JLabel passwordLabel;
	private JLabel roleLabel;
	private JLabel departmentLabel;
	private JTextField nameField;
    private JTextField idField;
    private JPasswordField passwordField;
    private ButtonGroup roleGroup;
    private JRadioButton teacherRadioButton;
    private JRadioButton studentRadioButton;
    private JComboBox<String> departmentComboBox;
    private JButton registerButton;
    private JButton backButton;
    private JButton showHideButton;
    private boolean showPassword = false;
	private JLabel aiubImagelabel ;
	private ImageIcon aiubImage; 
	

    public RegistrationFrame() 
	{
        // object creation
		nameLabel = new JLabel("Name:");
        idLabel = new JLabel("ID:");
        passwordLabel = new JLabel("Password:");
        roleLabel = new JLabel("Role:");
        departmentLabel = new JLabel("Department:");
        nameField = new JTextField();
        idField = new JTextField();
        passwordField = new JPasswordField();
        roleGroup = new ButtonGroup();
        teacherRadioButton = new JRadioButton("Teacher");
        studentRadioButton = new JRadioButton("Student");
        teacherRadioButton.setSelected(true);
        String[] departments = {"FST", "FE", "FBA", "FASS"};
        departmentComboBox = new JComboBox<>(departments);
        registerButton = new JButton("Register");
        backButton = new JButton("Back");
        showHideButton = new JButton("Show");
		aiubImage = new ImageIcon ("image\\Background1.jpg");
        aiubImagelabel = new JLabel(aiubImage);
		
        // Set bounds for components
        nameLabel.setBounds(50, 50, 100, 30);
        nameField.setBounds(160, 50, 150, 30);
        idLabel.setBounds(50, 100, 100, 30);
        idField.setBounds(160, 100, 150, 30);
        passwordLabel.setBounds(50, 150, 100, 30);
        passwordField.setBounds(160, 150, 150, 30);
        roleLabel.setBounds(50, 200, 100, 30);
        roleGroup.add(teacherRadioButton);
        roleGroup.add(studentRadioButton);
        teacherRadioButton.setBounds(160, 200, 80, 30);
        studentRadioButton.setBounds(250, 200, 80, 30);
        departmentLabel.setBounds(50, 250, 100, 30);
        departmentComboBox.setBounds(160, 250, 150, 30);
        registerButton.setBounds(50, 300, 100, 30);
        backButton.setBounds(160, 300, 80, 30);
        showHideButton.setBounds(320, 150, 80, 30);
		aiubImagelabel.setBounds(4, 340, aiubImage.getIconWidth(), aiubImage.getIconHeight());
		
		registerButton.setBackground( new Color(255, 255, 255) );
		backButton.setBackground( new Color(255, 255, 255) );
        showHideButton.setBackground( new Color(255, 255, 255) );
		teacherRadioButton.setBackground( new Color(51, 153, 255) );
        studentRadioButton.setBackground( new Color(51, 153, 255) );
        departmentComboBox.setBackground( new Color(255, 255, 255) );
		nameLabel.setForeground(new Color(0, 0, 0));
		idLabel.setForeground(new Color(0, 0, 0)); 
		roleLabel.setForeground(new Color(0, 0, 0)); 
		passwordLabel.setForeground(new Color(0, 0, 0)); 
		departmentLabel.setForeground(new Color(0, 0, 0));  
		
		nameLabel.setFont(new Font("Arial", Font.BOLD, 16));
		idLabel.setFont(new Font("Arial", Font.BOLD, 16));
		passwordLabel.setFont(new Font("Arial", Font.BOLD, 16));
		roleLabel.setFont(new Font("Arial", Font.BOLD, 16));
		departmentLabel.setFont(new Font("Arial", Font.BOLD, 16));
		registerButton.setFont(new Font("Arial", Font.BOLD, 14));
		backButton.setFont(new Font("Arial", Font.BOLD, 14));
		showHideButton.setFont(new Font("Arial", Font.BOLD, 14));
		

        // Add components to the frame
        add(nameLabel);
        add(nameField);
        add(idLabel);
        add(idField);
        add(passwordLabel);
        add(passwordField);
        add(roleLabel);
        add(teacherRadioButton);
        add(studentRadioButton);
        add(departmentLabel);
        add(departmentComboBox);
        add(registerButton);
        add(backButton);
        add(showHideButton);
		add(aiubImagelabel);

        // Set frame properties
        setTitle("AIUB Resource Booking System- Registration");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground( new Color(51, 153, 255) );
        setLocationRelativeTo(null);
		setIconImage(new ImageIcon("image\\logo.png").getImage());

        // Adding ActionListeners
        registerButton.addActionListener(this);
        backButton.addActionListener(this);
        showHideButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) 
	{
        if (e.getSource() == registerButton) 
		{
            if (checkAllInputs()) 
			{
                if (saveData()) 
				{
                    JOptionPane.showMessageDialog(this, "Data saved successfully!");
                    goBackToLogin();
                } 
				else 
				{
                    JOptionPane.showMessageDialog(this, "Error saving data!");
                }
            } 
			else 
			{
                JOptionPane.showMessageDialog(this, "Please fill in all fields.");
            }
        } 
		else if (e.getSource() == backButton) 
		{
            goBackToLogin();
        } 
		else if (e.getSource() == showHideButton) 
		{
            showHidePassword(); 
        }
    }

    public boolean checkAllInputs() 
	{
        String name = nameField.getText().trim();
        String id = idField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();
        String role = teacherRadioButton.isSelected() ? "Teacher" : "Student";
        String department = (String) departmentComboBox.getSelectedItem();
        return !name.isEmpty() && !id.isEmpty() && !password.isEmpty() && !role.isEmpty() && !department.isEmpty();
    } 

    public boolean saveData() 
	{
        if (checkAllInputs()) {
            String id = idField.getText().trim();

            if (isIdAlreadyExists(id)) 
			{
                JOptionPane.showMessageDialog(this, "User with the same ID already exists. Please choose a different ID.");
                return false; 
            }

            String name = nameField.getText();
            String password = new String(passwordField.getPassword());
            String role = teacherRadioButton.isSelected() ? "Teacher" : "Student";
            String department = (String) departmentComboBox.getSelectedItem();
            String userInfo = name + ":" + id + ":" + password + ":" + role + ":" + department;

            try 
			{
                FileWriter writer = new FileWriter("data\\user_data.txt", true);
                writer.write(userInfo + System.lineSeparator());
                writer.close();
                return true;
            } 
			catch (IOException ioe) 
			{
                JOptionPane.showMessageDialog(this, "Error saving data! Please try again.");
                return false; 
            }
        }
        return false;
    }

    public boolean isIdAlreadyExists(String id) 
	{
        try 
		{
            Scanner scanner = new Scanner(new File("data\\user_data.txt"));
            while (scanner.hasNextLine()) 
			{
                String line = scanner.nextLine();
                String[] fields = line.split(":");
                if (fields.length > 1 && id.equals(fields[1].trim())) 
				{
                    return true;
                } 
            }
        } catch (IOException ex) 
		{ 
            JOptionPane.showMessageDialog(this, "Error reading user data.");
        }
        return false;
    }

    public void goBackToLogin() 
	{ 
        this.setVisible(false);
        LoginFrame loginFrame = new LoginFrame();
        loginFrame.setVisible(true);
    }

    public void showHidePassword() 
	{ 
        if (showPassword) {
            passwordField.setEchoChar('*');
            showHideButton.setText("Show");
        } 
		else  
		{ 
            passwordField.setEchoChar((char) 0);
            showHideButton.setText("Hide");
        }
        showPassword = !showPassword;
    }
	
	public static void main(String [] args)
	{
		new RegistrationFrame().setVisible(true);
	}
}
