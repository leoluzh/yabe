package controllers;

import play.*;
import play.mvc.*;
import play.data.validation.*;
import play.libs.Codec;
import play.libs.Images;
import play.cache.*;
import java.util.*;

import models.*;

public class Application extends Controller {

    @Before
    static void addDefaults() {
        renderArgs.put("blogTitle", Play.configuration.getProperty("blog.title"));
        renderArgs.put("blogBaseline", Play.configuration.getProperty("blog.baseline"));
    }//end method
	
	
    public static void index() {
    	Post frontPost = Post.find("order by postedAt desc").first();
    	List<Post> olderPosts = Post.find("order by postedAt desc").from(1).fetch(10);
        render(frontPost,olderPosts);
    }//end method
    
    public static void show(Long id){
    	Post post = Post.findById(id);
    	String randomId = Codec.UUID();
    	render(post,randomId);
    }//end method
    
    public static void postComment( 
    		Long postId , 
    		@Required(message="Author is required") 
    		String author , 
    		@Required(message="A message is required")
    		String content ,
    		@Required(message="Please type the code")
    		String code , 
    		String randomId ){
    	Post post = Post.findById(postId);
    	validation.equals(code,Cache.get(randomId)).message("Invalid code. Please type it again.");
    	if(validation.hasErrors()){
    		render("Application/show.html",post);
    	}//end if
    	User user = User.find("byName",author).first();
    	post.addComment(user, content);
    	flash.success("Thanks for posting %s",user.fullname);
    	Cache.delete(randomId);
    	show(postId);
    }//end method
        
    public static void captcha(String id){
    	Images.Captcha captcha = Images.captcha();
    	String code = captcha.getText("#E4EAFD");
    	Cache.set(id,code,"10mn");
    	renderBinary(captcha);
    }//end method
    
    public static void listTagged(String tag){
    	List<Post> posts = Post.findTaggedWith(tag);
    	render(tag,posts);
    }//end method

}//end klazz