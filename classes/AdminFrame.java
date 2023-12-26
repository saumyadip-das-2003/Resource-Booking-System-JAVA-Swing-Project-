package classes;
import interfaces.IAdminFrame; 

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminFrame extends JFrame implements ActionListener, IAdminFrame 
{ 
    private JButton userInfoButton;
    private JButton adminInfoButton;
    private JButton resourcesInfo;
    private JButton bookedResourcesButton; 
    private JButton logoutButton;
    private JPanel mainPanel;
    private JPanel selectedPanel;
    private JTable table;
    private JTable adminTable; 
    private JTable resourceTable;
    private final UserInfoPanel userInfoPanel = new UserInfoPanel();
    private final AdminInfoPanel adminInfoPanel = new AdminInfoPanel();
    private final ResourceInfoPanel resourceInfoPanel = new ResourceInfoPanel();
    private final BookedResourcesPanel bookedResourcesPanel = new BookedResourcesPanel(); 
    

    public AdminFrame() {
        // Instantiations
        userInfoButton = new JButton("User Info");
        adminInfoButton = new JButton("Admin Info");
        resourcesInfo = new JButton("Resources Info");
        bookedResourcesButton = new JButton("Booked Resources"); 
        logoutButton = new JButton("Logout");
        mainPanel = new JPanel();
		

        // Set bounds for components
        userInfoButton.setBounds(40, 20, 110, 30);
        adminInfoButton.setBounds(160, 20, 140, 30);
        resourcesInfo.setBounds(310, 20, 160, 30);
        bookedResourcesButton.setBounds(480, 20, 170, 30); 
        logoutButton.setBounds(50, 500, 120, 30);
        mainPanel.setBounds(50, 100, 700, 300);
		
		

        // Add components to the frame
        add(userInfoButton);
        add(adminInfoButton);
        add(resourcesInfo);
        add(bookedResourcesButton); 
        add(logoutButton);
        add(mainPanel);
		
		
		userInfoButton.setBackground( new Color(255, 255, 255) );
		adminInfoButton.setBackground( new Color(255, 255, 255) );
        resourcesInfo.setBackground( new Color(255, 255, 255) );
        bookedResourcesButton.setBackground( new Color(255, 255, 255) );
        logoutButton.setBackground( new Color(255, 255, 255) );
        
		userInfoButton.setFont(new Font("Arial", Font.BOLD, 14));
		adminInfoButton.setFont(new Font("Arial", Font.BOLD, 14));
		resourcesInfo.setFont(new Font("Arial", Font.BOLD, 14));
		bookedResourcesButton.setFont(new Font("Arial", Font.BOLD, 14));
		logoutButton.setFont(new Font("Arial", Font.BOLD, 14));
		
        // Frame properties
        setTitle("Admin Dashboard");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);
		getContentPane().setBackground( new Color(51, 153, 255) );
		setIconImage(new ImageIcon("image\\logo.png").getImage());

        // panel 
        mainPanel.setLayout(new CardLayout());
        mainPanel.add(userInfoPanel, "UserInfoPanel");
        mainPanel.add(adminInfoPanel, "AdminInfoPanel");
        mainPanel.add(resourceInfoPanel, "ResourceInfoPanel");
        mainPanel.add(bookedResourcesPanel, "BookedResourcesPanel"); 

        // Add action listeners to buttons
        userInfoButton.addActionListener(this);
        adminInfoButton.addActionListener(this);
        resourcesInfo.addActionListener(this);
        bookedResourcesButton.addActionListener(this); 
        logoutButton.addActionListener(this);

        // default panel 
        selectedPanel = userInfoPanel; 
    }

    public void showUserInfoPanel() 
	{
        CardLayout cardLayout = (CardLayout) mainPanel.getLayout();
        cardLayout.show(mainPanel, "UserInfoPanel");
        selectedPanel = userInfoPanel;
        revalidate();
        repaint();
    }

    public void showAdminInfoPanel() 
	{
        CardLayout cardLayout = (CardLayout) mainPanel.getLayout();
        cardLayout.show(mainPanel, "AdminInfoPanel");
        selectedPanel = adminInfoPanel;
        revalidate();
        repaint();
    }

    public void showResourceInfoPanel() 
	{
        CardLayout cardLayout = (CardLayout) mainPanel.getLayout();
        cardLayout.show(mainPanel, "ResourceInfoPanel");
        selectedPanel = resourceInfoPanel;
        revalidate();
        repaint();
    }

    public void showBookedResourcesPanel() 
	{
        CardLayout cardLayout = (CardLayout) mainPanel.getLayout();
        cardLayout.show(mainPanel, "BookedResourcesPanel"); 
        selectedPanel = bookedResourcesPanel; 
        revalidate();
        repaint();
    }

    public void goBackToLogin() 
	{
        this.setVisible(false);
        LoginFrame lf = new LoginFrame();
        lf.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) 
	{
        if (e.getSource() == userInfoButton) 
		{
            showUserInfoPanel();
        } 
		else if (e.getSource() == adminInfoButton) 
		{
            showAdminInfoPanel();
        } 
		else if (e.getSource() == resourcesInfo) 
		{
            showResourceInfoPanel();
        } 
		else if (e.getSource() == bookedResourcesButton) 
		{
            showBookedResourcesPanel(); 
        } 
		else if (e.getSource() == logoutButton) {
            goBackToLogin(); 
        }
    }

    public JPanel getSelectedPanel() 
	{
        return selectedPanel; 
    }

    public static void main(String[] args) 
	{
        AdminFrame adminFrame = new AdminFrame();
        adminFrame.setVisible(true); 
     
    }
}
