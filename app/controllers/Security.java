package controllers;

import models.*;

public class Security extends Secure.Security {

	static boolean authenticate( String email , String password ){
		return User.connect(email,password) != null ;
	}//end method
	
	static void onDisconnected(){
		Application.index();
	}//end method
	
	static void onAuthenticated(){
		Admin.index();
	}//end method
	
	//authorization
	static boolean check( String profile ){
		if("admin".equals(profile)){
			return User.find("byEmail",connected()).<User>first().isAdmin;
		}//end if
		return false;
	}//end method
	
}//end klazz
