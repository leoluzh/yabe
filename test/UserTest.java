import org.junit.*;

import java.util.*;
import play.test.*;
import models.*;

public class UserTest extends UnitTest {

	@Before
	public void setup(){
		Fixtures.deleteDatabase();
	}//end method
		
	
	@Test
	public void createAndRetrieve(){
		
		new User("foobar@foobar.com","foobar","foobar").save();
		User foobar = User.find("byEmail","foobar@foobar.com").first();
		
		//Testing
		assertNotNull(foobar);
		assertEquals("foobar",foobar.fullname);
		
	}//end method

	@Test
	public void tryToConnectAs(){
		
		new User("foobar@foobar.com","foobar","foobar").save();
		
		//Test
		assertNotNull(User.connect("foobar@foobar.com","foobar"));
		assertNull(User.connect("foobar@foobar.com","barfoo"));
		assertNull(User.connect("barfoo@barfoo.com","foobar"));
		
	}//end method
	
}//end klazz
