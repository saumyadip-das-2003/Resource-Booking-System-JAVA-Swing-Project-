package interfaces;
import classes.*; 

public interface ILoginFrame
{
	void login();
	void openDashboard(String name, String id, String role);
	void openRegistrationFrame();
	void openAdminLoginFrame();
	void showCredits();
	void showHidePassword();
	
}