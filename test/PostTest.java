import org.junit.*;
import java.util.*;
import play.test.*;
import models.*;

public class PostTest extends UnitTest {

	@Before
	public void setup(){
		Fixtures.deleteDatabase();
	}//end method
	
	@Test
	public void create(){
		
		User user = new User("userpost@userpost.com","post","userpost").save();
		new Post(user,"My first post","Hello World!").save();
		
		//Test
		assertEquals(1,Post.count());
		
		List<Post> posts = Post.find("byAuthor",user).fetch();
		
		//Test
		assertEquals(1,posts.size());
		Post post = posts.get(0);
		assertNotNull(post);
		assertEquals(user,post.author);
		assertEquals("My first post",post.title);
		assertEquals("Hello World!",post.content);
		assertNotNull(post.postedAt);
		
		
	}//end method
	
}//end klazz
