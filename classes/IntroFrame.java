package classes; 
import interfaces.*;

import java.lang.*;
import javax.swing.*;
import javax.swing.ImageIcon;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IntroFrame extends JFrame implements ActionListener
{
	private JButton getStartedButton; 
	private JLabel imagelabel ;
	private ImageIcon introImage; 

    public IntroFrame() 
	{
		getStartedButton = new JButton("Get Started");
		introImage = new ImageIcon ("image\\introimage.jpg"); 
		imagelabel = new JLabel(introImage);		
		
		getStartedButton.setBounds(430, 525, 120, 30);  
		imagelabel.setBounds(50, 40, introImage.getIconWidth(), introImage.getIconHeight());
		
		getStartedButton.setBackground( new Color(255, 255, 255) );
		getStartedButton.setFont(new Font("Arial", Font.BOLD, 14));
		
		add(getStartedButton); 
		add(imagelabel); 
		
		setTitle("AIUB Resource Booking System");
		setSize(600, 600);
		setLayout(null);  
		setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setBackground( new Color(51, 153, 255) );
		setIconImage(new ImageIcon("image\\logo.png").getImage());  
		
		getStartedButton.addActionListener(this);  
       
    }
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == getStartedButton)
		{
			openLoginFrame();  
		}
	}

    private void openLoginFrame() 
	{
        new LoginFrame().setVisible(true);
        this.setVisible(false); 		
    }
	
	public static void main(String[] args) 
	{
        new IntroFrame().setVisible(true); 
    }
}
