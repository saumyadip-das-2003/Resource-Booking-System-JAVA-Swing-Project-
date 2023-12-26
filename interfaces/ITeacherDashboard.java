package interfaces;
import classes.*; 

import javax.swing.table.DefaultTableModel;

public interface ITeacherDashboard
{
	void loadTeacherResourcesData(DefaultTableModel model);
	void showBookNewPanel();
	void bookResource();
	void deleteResource();
	void saveResourceDataToFile();
	void showMyBookingsTable();
	void loadMyBookingsData(DefaultTableModel model, String userID);
	void showUserInfo();
	void logout();
	
}