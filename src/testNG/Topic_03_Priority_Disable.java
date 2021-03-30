package testNG;

import org.testng.annotations.Test;

public class Topic_03_Priority_Disable {

//	@Test(priority = 1)
//	public void Create_User() {
//
//	}
//
//	@Test(priority = 5)
//	public void Delete_User() {
//
//	}
//
//	@Test(priority = 3)
//	public void Edit_User() {
//
//	}
//
//	@Test(priority = 4)
//	public void Move_User() {
//
//	}
//
//	@Test(priority = 2)
//	public void View_User() {
//
//	}
	@Test(groups = "user management", enabled = true, description = "Ticket ID-Create User")
	public void User_01_Create_User() {
		
	}
	
	@Test(enabled = false)
	public void User_05_Delete_User() {
		
	}
	
	@Test
	public void User_03_Edit_User() {
		
	}
	
	@Test
	public void User_04_Move_User() {
		
	}
	
	@Test
	public void User_02_View_User() {
		
	}

}
