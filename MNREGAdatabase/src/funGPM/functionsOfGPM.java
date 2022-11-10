package funGPM;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.masai.util.*;

import Beans.EMPLOYEEbean;

public class functionsOfGPM {
	
	public static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
//Login into Gram Panchyat Member account
//		System.out.println("Login into the Gram Panchyat Member portal");
//		System.out.println("Enter Username");
//		String user = sc.next();
//		System.out.println("Enter Password");
//		String pass = sc.next();
//		
//		loginGPM(user, pass);
		
		
//Creating the new employee
//		System.out.println("Adding the new employee");
//		System.out.println("Enter the name of employee");
//		String name = sc.next();
//		
//		EMPLOYEEbean emp1 = new EMPLOYEEbean(null, name, null, 0, 0, null, null);
//		createEmployee(emp1);
		
//View the list of employee
		System.out.println("Employees List");
		List<EMPLOYEEbean> empList =viewEmployeeList();
		
		for (EMPLOYEEbean employeEbean : empList) {
			System.out.println(employeEbean);
		}
	}
	


//Login into gram pamchayat member
	public static void loginGPM(String user, String pass) {
		
		try(Connection conn = DButil.getConnection()) {
			
			PreparedStatement loggpm = conn.prepareStatement("SELECT* FROM gpmDB WHERE gpmUsername=? AND gpmPassword=?");
		
			loggpm.setString(1, user);
			loggpm.setString(2, pass);
			
			ResultSet gpm = loggpm.executeQuery();
			
			if(gpm.next()) {
				System.out.println("\n"+"Welcome Gram Panchayat Member "+gpm.getString("gpmName"));
				System.out.println("Your ID is "+gpm.getString("gpmID"));
			}else {
				System.out.println("Invalide Username OR Password");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	
//Create Employee
	public static void createEmployee(EMPLOYEEbean emp1) {
		
		try(Connection conn = DButil.getConnection()) {
			
			PreparedStatement insEmp = conn.prepareStatement("INSERT INTO employeeDB(empName) VALUES (?)");
			
			insEmp.setString(1, emp1.getEmpName());
			
	     	int out =	insEmp.executeUpdate();
	     	
	     	if(out>0)System.out.println("New Employee Added");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
	}
	
	
//View THe list of the employee
	public static List<EMPLOYEEbean> viewEmployeeList() {
		
		List<EMPLOYEEbean> employeeList = new ArrayList<EMPLOYEEbean>();
		
		try(Connection conn = DButil.getConnection()) {
			
			PreparedStatement viewEmp = conn.prepareStatement("SELECT * FROM employeeDB");
			
			ResultSet empList = viewEmp.executeQuery();
			
			while(empList.next()) {
				
				String empID = empList.getNString("empID");
				String empName = empList.getNString("empName");
				String status = empList.getNString("status");
				String gpmSup = empList.getNString("gpmSuervise");
				int wage = empList.getInt("wageEarned");
				int day = empList.getInt("numOfDaysWork");
				String gpm = empList.getString("gpmSuervise");
				String proj = empList.getString("proWorking");
                
				EMPLOYEEbean emp = new EMPLOYEEbean(empID, empName, status, wage, day, gpmSup, proj);
				employeeList.add(emp);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return employeeList;
	}
	
}
