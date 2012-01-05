import org.junit.*;

import java.util.*;
import play.test.*;
import models.*;

public class CommentTest extends UnitTest {
	
	@Before
	public void setup(){
		Fixtures.deleteDatabase();
	}//end method
	

	@Test
	public void postComments(){
	
	    // Create a new user and save it
	    User bob = new User("bob@gmail.com", "secret", "Bob").save();
	    User tom = new User("tom@gmail.com", "secret", "Tom").save();
	    User jef = new User("jeffy@gmail.com", "secret", "Jeffy").save();
	 
	    // Create a new post
	    Post bobPost = new Post(bob, "My first post", "Hello world").save();
	 
	    // Post a first comment
	    new Comment(bobPost,tom, "Nice post").save();
	    new Comment(bobPost,jef, "I knew that !").save();
	 
	    // Retrieve all comments
	    List<Comment> bobPostComments = Comment.find("byPost", bobPost).fetch();
	 
	    // Tests
	    assertEquals(2, bobPostComments.size());
	 
	    Comment firstComment = bobPostComments.get(0);
	    assertNotNull(firstComment);
	    assertEquals(tom, firstComment.author);
	    assertEquals("Nice post", firstComment.content);
	    assertNotNull(firstComment.postedAt);
	 
	    Comment secondComment = bobPostComments.get(1);
	    assertNotNull(secondComment);
	    assertEquals(jef, secondComment.author);
	    assertEquals("I knew that !", secondComment.content);
	    assertNotNull(secondComment.postedAt);	
    
	}//end method
	
	@Test
	public void useTheCommentRelation(){
		
	    // Create a new user and save it
	    User bob = new User("bob@gmail.com", "secret", "Bob").save();
	    User tom = new User("tom@gmail.com", "secret", "Tom").save();
	    User jef = new User("jeffy@gmail.com", "secret", "Jeffy").save();
	 
	    // Create a new post
	    Post bobPost = new Post(bob, "My first post", "Hello world").save();
	 
	    // Post a first comment
	    bobPost.addComment(jef, "Nice post");
	    bobPost.addComment(tom, "I knew that !");
	 
	    // Count things
	    assertEquals(1, User.count());
	    assertEquals(1, Post.count());
	    assertEquals(2, Comment.count());
	 
	    // Retrieve Bob's post
	    bobPost = Post.find("byAuthor", bob).first();
	    assertNotNull(bobPost);
	 
	    // Navigate to comments
	    assertEquals(2, bobPost.comments.size());
	    assertEquals(jef, bobPost.comments.get(0).author);
	    
	    // Delete the post
	    bobPost.delete();
	    
	    // Check that all comments have been deleted
	    assertEquals(1, User.count());
	    assertEquals(0, Post.count());
	    assertEquals(0, Comment.count());
		
		
	}//end method
	
}//end klazz
