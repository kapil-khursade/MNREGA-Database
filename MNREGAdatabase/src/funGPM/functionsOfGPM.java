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
//		System.out.println("Employees List");
//		List<EMPLOYEEbean> empList =viewEmployeeList();
//		
//		for (EMPLOYEEbean employeEbean : empList) {
//			System.out.println(employeEbean);
//		}
		
//Assign project to employee;	
//		list of project available
//		aviaProjAndEmp();
		
		
//Viewing the employee list and days of works
//		showproOption();
		
	}
	


//Login into gram pamchayat member
	public static boolean loginGPM(String user, String pass) {
		
		boolean flag = false;
		
		try(Connection conn = DButil.getConnection()) {
			
			PreparedStatement loggpm = conn.prepareStatement("SELECT* FROM gpmDB WHERE gpmUsername=? AND gpmPassword=?");
		
			loggpm.setString(1, user);
			loggpm.setString(2, pass);
			
			ResultSet gpm = loggpm.executeQuery();
			
			if(gpm.next()) {
				System.out.println("\n"+"Welcome Gram Panchayat Member "+gpm.getString("gpmName"));
				System.out.println("Your ID is "+gpm.getString("gpmID"));
				flag = true;
			}else {
				System.out.println("Invalide Username OR Password");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
		return flag;
	}
	
//Create Employee
	public static void createEmployee(EMPLOYEEbean emp1) {
		
		try(Connection conn = DButil.getConnection()) {
			
			PreparedStatement insEmp = conn.prepareStatement("INSERT INTO employeeDB(empName, gpmSuervise) VALUES (?, ?)");
			
			insEmp.setString(1, emp1.getEmpName());
			insEmp.setString(2, emp1.getEmpID());
			
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
//			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return employeeList;
	}
	
	
//Allotnig the project to employee, the project is not finished, employee required and have balance budget. Also selecting the employee 
//which is unemployeed now. Updating the employee also. Giving them wage and num of days work.
	public static void aviaProjAndEmp() {
		try(Connection conn = DButil.getConnection()) {
			
			PreparedStatement proAvl = conn.prepareStatement("SELECT proID, proName FROM projectDB WHERE balanceCost>0 AND employeeRequired>0 AND NOT status='done'");
			
			ResultSet proj = proAvl.executeQuery();
			
			System.out.println("Following are the active or upcoming project which have budget and employees Required");
			System.out.println("ProjID   ProjectName");
			System.out.println("-----------------------");
			while(proj.next()) {
				System.out.println(proj.getString(1)+"  "+proj.getString(2));
			}
			
			System.out.println("\n"+"Select project ID to Allot the employees");
			String proCurr = sc.next();
			empAvlAndAllotPro(proCurr);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	
	public static void empAvlAndAllotPro(String proCurr) {
		
		try(Connection conn = DButil.getConnection()) {
			
			PreparedStatement empAvl = conn.prepareStatement("SELECT empID, empName FROM employeeDB WHERE status='unemployeed'");
			
		   ResultSet emp = empAvl.executeQuery();
		   
		   System.out.println("\n"+"This are the unemployeed members");
		   System.out.println("empID   empName");
		   System.out.println("------------------");
		   while(emp.next()) {
			   System.out.println(emp.getString(1)+"  "+emp.getString(2));
		   }
			
		   System.out.println("Type the employee ID you want to allot to project "+proCurr);
		   String empCurr = sc.next();
		   allotingProject(proCurr, empCurr);
		   
		} catch (SQLException e) {
			// TODO: handle exception
//			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
	}



	public static void allotingProject(String proCurr, String empCurr) {
		// TODO Auto-generated method stub
		
		try(Connection conn = DButil.getConnection()) {
			
			int wage = 0;
			int days = 0;
			
			PreparedStatement detailOfPro = 
					conn.prepareStatement("SELECT wagePerEmp, DATEDIFF(dateOfEnd, dateOfStart) FROM projectDB WHERE proID=?");
			
			detailOfPro.setString(1, proCurr);
			ResultSet pro = detailOfPro.executeQuery();
			if(pro.next()) {
				wage = pro.getInt(1);
				days = pro.getInt(2);
			}
			
			PreparedStatement allot = 
			conn.prepareStatement("UPDATE employeeDB SET proWorking=?, status='employeed', wageEarned=?, numOfDaysWork=? WHERE empID=?");
			
		    allot.setString(1, proCurr);
		    allot.setInt(2, wage);
		    allot.setInt(3, days);
		    allot.setString(4, empCurr);
		    
		    int outEmp = allot.executeUpdate();
		    if(outEmp>0)System.out.println("\n"+"Employee "+empCurr+" alloted to project "+proCurr);
		    else System.out.println("Invalid employee or project");
			
			PreparedStatement updateProject = 
			conn.prepareStatement("UPDATE projectDB SET balanceCost=(balanceCost-wagePerEmp), employeeRequired=employeeRequired-1 WHERE proID=?");
			
			updateProject.setString(1, proCurr);
			int proOut = updateProject.executeUpdate();
			if(proOut>0)System.out.println("Project database updated updated");
			else System.out.println("Invalide project"+"\n");
			
			
			System.out.println("Type 1 to allot another project and 0 to Exit");
			int opt = sc.nextInt();
			if(opt==1) {
				//Assign project to employee;	
//				list of project available
				aviaProjAndEmp();
			}else if(opt==0) {
				System.out.println("Done..");
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	
	
//viewing list of employee and and nuber of days worked
	//list of employee working on project and their salary	
//	options of project
	public static void showproOption() {
		
try(Connection conn = DButil.getConnection()) {
			
			System.out.println("This projects are avialbe.");
			
			PreparedStatement proAc = conn.prepareStatement("SELECT proID, proName FROM projectDB");
			ResultSet proj = proAc.executeQuery();
			System.out.println("ProjID  ProjName");
			System.out.println("------------------");
			while(proj.next()) {
				System.out.println(proj.getString(1)+"  "+proj.getString(2));
			}

			System.out.println("\n"+"Enter the Project Id");
			String proCurr = sc.next();
			listOfempProSal(proCurr);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	
	
	public static void listOfempProSal(String proCurr) {
		try(Connection conn = DButil.getConnection()) {
			
			PreparedStatement proAc =
			conn.prepareStatement(" SELECT employeeDB.empID, employeeDB.empName, employeeDB.numOfDaysWork  FROM employeeDB CROSS JOIN projectDB WHERE projectDB.proID=employeeDB.proWorking AND projectDB.proID=?");
			proAc.setString(1, proCurr);
			ResultSet proj = proAc.executeQuery();
			System.out.println("Following are the employee working on project id "+proCurr+"\n");
			System.out.println("EmpID  empName   NumberOfDayWorked");
			System.out.println("-----------------------------");
			while(proj.next()) {
				System.out.println(proj.getString(1)+"  "+proj.getString(2)+"  "+proj.getString(3));
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	
	
}
