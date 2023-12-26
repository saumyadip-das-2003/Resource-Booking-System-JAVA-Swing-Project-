package interfaces;
import classes.*; 

public interface IRegistrationFrame
{
	boolean checkAllInputs();
	boolean saveData();
	boolean isIdAlreadyExists(String id);
	void goBackToLogin();
	void showHidePassword();
	
}