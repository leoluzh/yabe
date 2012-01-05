import play.*;
import play.jobs.*;
import play.test.*;

import models.*;


@OnApplicationStart
public class Bootstrap extends Job {

	public void doJob(){
		System.out.println("User count:" + User.count());
		if(User.count()==0){
			Fixtures.loadModels("initial-data.yml");
		}//end if
		
	}//end method
	
}//end klazz
