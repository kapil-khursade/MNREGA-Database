package Dashboard;

//This will provide all the dashboard services to navigate the db managemenet

import java.util.*;

public class dsahBoard {
	
	Scanner sc = new Scanner(System.in);
	
	public void welcomeMsg() {
//		provide first welcome message
		System.out.println("...Welcome To MNREGA Database Portal...");
	}
	
	public void accessAs() {
//		give option to acess as funBDO or funGPM
	    System.out.println("Type 1 TO access As Block Development Officer"+"\n"+"Type 2 To access As Gram Pancayat Member");
	    int acc = sc.nextInt();
	    if(acc==1) {
//	    	access as BDO
	    	
	    }else if(acc==2) {
//	    	access as GPM
	    	
	    }else {
//	    	Invalid choice
	    	System.out.println("Invalide Entry!!!");
	    	accessAs();
	    }
	}
	
	public void accescAsFunBDO() {
		
	}
}
