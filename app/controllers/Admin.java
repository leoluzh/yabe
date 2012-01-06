package controllers;

import play.*;
import play.mvc.*;
import models.*;

import java.util.*;

@With(Secure.class)
public class Admin extends Controller {

	@Before
	static void setConnectedUser(){
		if( Security.isConnected() ){
			User user = User.find("byEmail",Security.connected()).first();
			renderArgs.put("user",user.fullname);
		}//end method
	}//end method
	
	public static void index(){
		
	}//end method
	
}//end klazz
