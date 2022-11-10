package funBDO;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import Beans.*;

import com.masai.util.*;

public class functionsdbo {

	public static Scanner sc = new Scanner(System.in);
	
//	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
//for insertin bdo
//		System.out.println("Inserting BDO");
//		
//		System.out.println("Enter BDO name");
//		String nam = sc.next();
//		System.out.println("Enter BDO username");
//		String user = sc.next();
//		System.out.println("Enter BDO password");
//		String pass = sc.next();
//		
//		BDObean bdo1 = new BDObean(null, nam, user, pass);
//		
//		insertBDO(bdo1);

		
//for loging in bdo account
//      System.out.println("BDO LOGIN PORTAL");
//      System.out.println("ENTER YOUR USERNAME");
//      String user = sc.next();
//      System.out.println("ENTER YOUR PASSWORD");
//      String pass = sc.next();
//      loginBDO(user, pass);
		
//for creating project
//		System.out.println("Create Project");
//		System.out.println("Enter Project Name");
//		String nam = sc.next();
//		System.out.println("Enter Project total cost");
//		int cost = sc.nextInt();
//		System.out.println("Enter Project Wage Per Empployee");
//		int wage = sc.nextInt();
//		System.out.println("Enter Project No of Empployee Required");
//		int empReq = sc.nextInt();
//		System.out.println("Enter Project Date Of Start in YYYY-MM-DD");
//		long dos = sc.nextLong();
//		Date doss = new Date(dos);
//		System.out.println("Enter Project Date Of End in YYYY-MM-DD");
//		long doe = sc.nextLong();
//		Date does = new Date(doe);
//		
//		PROJECTbean pro1 = new PROJECTbean(null, nam, cost, cost, wage, empReq, doss, does, null);	
//
//		createProject(pro1);
		
//Viewing project list
//		System.out.println(viewProjectList());
		
//Adding new Gramp Pancayat member
//		System.out.println("Creating new Gram Pancahyat Memeber Account");
//		System.out.println("Enter Name");
//		String name = sc.next();
//		System.out.println("Enter Username");
//		String user = sc.next();
//		System.out.println("Enter Password");
//		String pass = sc.next();
//	
//		GPMbean gpm1 = new GPMbean(null, name, user, pass, null, null);
//		insertGPM(gpm1);
		
//viewing list of gpm
		
//		List<GPMbean> gpmList = viewGPMList();
//		
//		for (GPMbean gpMbean : gpmList) {
//			System.out.println(gpMbean);
//		}
		
//Allocating project
//		projAandGpm();
//	}
	
//inserting the bdo account
	public static void insertBDO(BDObean bdo1) {
		
		try (Connection conn = DButil.getConnection()){
			
			PreparedStatement inBDO = conn.prepareStatement("INSERT INTO bdoDB(bdoName, bdoUsername, bdoPassword) VALUES(?, ?, ?)");
			
			inBDO.setString(1, bdo1.getName());
			inBDO.setString(2, bdo1.getUsername());
			inBDO.setString(3, bdo1.getPassword());
			
			int out = inBDO.executeUpdate();
			
			if(out>0)System.out.println("NEW Block Development Officer Account Crated");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	
//Login into the bdo account
	public static void loginBDO(String user, String pass) {
		
		try(Connection conn = DButil.getConnection()) {
			
			PreparedStatement logBDO = conn.prepareStatement("SELECT * FROM bdoDB WHERE bdoUsername=? AND bdoPassword=?");
			logBDO.setString(1, user);
			logBDO.setString(2, pass);
			ResultSet bdoAcc = logBDO.executeQuery();
			
			if(bdoAcc.next()) {
				String bdoID = bdoAcc.getString("bdoID");
				String bdoName = bdoAcc.getNString("bdoName");
				System.out.println("\n"+"Log in Sucessfull "+bdoName);
				System.out.println("Your account ID is "+bdoID);
			}else {
				System.out.println("Invalid Username Or Password");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	
	
//Creating the projects	
	public static void createProject(PROJECTbean pro1) {
		
		try(Connection conn = DButil.getConnection()) {
			
			String qur = "INSERT INTO projectDB(proName, totalCost, balanceCost, WagePerEmp, employeeRequired, dateOfStart, dateOfEnd) VALUES(?, ?, ?, ?, ?, ?, ?)";
			 
			PreparedStatement crtPro = conn.prepareStatement(qur);
			crtPro.setString(1, pro1.getProName());
			crtPro.setInt(2, pro1.getTotalCost());
			crtPro.setInt(3, pro1.getTotalCost());
			crtPro.setInt(4, pro1.getWagePerEmp());
			crtPro.setInt(5, pro1.getEmployeeRequired());
			crtPro.setDate(6, pro1.getDateOfStrat());
			crtPro.setDate(7, pro1.getDateOfEnd());
			
		    int out	 = crtPro.executeUpdate();
		    
		    if(out>0) {
		    	System.out.println(pro1.getProName()+" Project Created");
		    }else {
		    	System.out.println("Project unable to create...");
		    }
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	
	
//View the list of the project
	public static List<PROJECTbean> viewProjectList() {
		
		List<PROJECTbean> proList = new ArrayList<PROJECTbean>();
		
		try(Connection conn = DButil.getConnection()) {
			
			PreparedStatement viwProLi = conn.prepareStatement("SELECT * FROM projectDB");
			
			ResultSet proj = viwProLi.executeQuery();
			
			while(proj.next()) {
				
				String proID = proj.getString("proID");
				String name = proj.getString("proName");
				int cost = proj.getInt("totalcost");
				int bal = proj.getInt("balanceCost");
				int wag = proj.getInt("WagePerEmp");
                int empReq = proj.getInt("employeeRequired");
                Date dos = proj.getDate("dateOfStart");
                Date doe = proj.getDate("dateOfEnd");
                String status = proj.getString("status");
				
                PROJECTbean pro = new PROJECTbean(proID, name, cost, cost, wag, empReq, dos, doe, status);
                
                proList.add(pro);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
		
		return proList;
	}
	
//Creating garm panchayat member
	public static void insertGPM(GPMbean gpm1) {
		
		try(Connection conn = DButil.getConnection()) {
			
			PreparedStatement inGPM = conn.prepareStatement("INSERT INTO gpmDB(gpmName, gpmUsername, gpmPassword) VALUES(?, ?, ?)");
			
			inGPM.setString(1, gpm1.getGpmName());
			inGPM.setString(2, gpm1.getGpmUsername());
			inGPM.setString(3, gpm1.getGpmPassword());
			
			int out = inGPM.executeUpdate();
			
			if(out>0)System.out.println("\n"+"New Gram Panchayat Member Account Added");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	
//Viewing the gram pancayat member list
	public static List<GPMbean> viewGPMList(){
		
		List<GPMbean> gpmList = new ArrayList<GPMbean>();
		
		try(Connection conn = DButil.getConnection()) {
			
			PreparedStatement viwGpm = conn.prepareStatement("SELECT * FROM gpmDB");
			
			ResultSet gpm = viwGpm.executeQuery();
			
			while(gpm.next()) {
				
				String gpmID = gpm.getString("gpmID");
				String gpmName = gpm.getString("gpmName");
				String gpmUser = gpm.getString("gpmUsername");
				String gpmpass = gpm.getString("gpmPassword");
				String bdo = gpm.getNString("bdoSupervise");
				String pro = gpm.getString("proAllot");
				
				GPMbean gpm1 = new GPMbean(gpmID, gpmName, gpmUser, gpmpass, bdo, pro);
				
				gpmList.add(gpm1);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
		return gpmList;
	}
	
//Alloting the project. It will display project ID and name ti choose from.
	public static void projAandGpm() {
		
//		Displaying the project available and not finshed
		try(Connection conn = DButil.getConnection()) {
			
			System.out.println("This projects are avialbe to allot who have budget and either work in progress or not started.");
			
			PreparedStatement proAc = conn.prepareStatement("SELECT proID, proName FROM projectDB WHERE balanceCost>0 AND NOT status='DONE'");
			ResultSet proj = proAc.executeQuery();
			System.out.println("ProjID  ProjName");
			System.out.println("------------------");
			while(proj.next()) {
				System.out.println(proj.getString(1)+"  "+proj.getString(2));
			}
			
			System.out.println("\n"+"This are the Gram Panchayat Members whome project Not alloted.");
			PreparedStatement gpmAc = conn.prepareStatement("SELECT gpmID, gpmName FROM gpmDB WHERE proAllot IS NULL");
			ResultSet gpm =  gpmAc.executeQuery();
			System.out.println("gpmID   gpmName");
			System.out.println("--------------------");
			
			while(gpm.next()) {
				System.out.println(gpm.getString(1)+" "+gpm.getString(2));
			}
//			alloting the project
			allotProToGpm();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	
//Actually alloting the project	
	public static void allotProToGpm() {
		
		try(Connection conn = DButil.getConnection()) {
			System.out.println("\n"+"Allot the project from above options using Ids");
			System.out.println("Enter the Project ID");
			String proID = sc.next();
			System.out.println("Enter the Gram Panachayt Member ID");
			String gpmID = sc.next();
			PreparedStatement allotpro = conn.prepareStatement("UPDATE gpmDB SET proAllot=? WHERE gpmID=?");
			allotpro.setString(2, gpmID);
			allotpro.setString(1, proID);
			
			int out = allotpro.executeUpdate();
			
			if(out>0)System.out.println("Project Alloted Sucessfully");
			else System.out.println("Invalid project id or gpm Id");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	
}
