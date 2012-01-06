package models;

import java.util.*;

import javax.persistence.*;

import play.data.validation.*;
import play.db.jpa.*;

@Entity
@Table(name="post")
public class Post extends Model {

	@Required
	public String title ;
	
	@Required
	@Temporal(TemporalType.TIMESTAMP)	
	public Date postedAt ;
	
	@Required
	@MaxSize(10000)
	@Lob
	public String content ;
	
	@Required
	@ManyToOne(fetch=FetchType.EAGER)
	public User author;

	
	@OneToMany(mappedBy="post",cascade=CascadeType.ALL)
	public List<Comment> comments;
	
	@ManyToMany(cascade=CascadeType.PERSIST)
	public Set<Tag> tags;
	
	public Post( User author , 
			String title , 
			String content ){
		this.author = author ;
		this.title = title ;
		this.content = content ;
		this.postedAt = new Date();		
		this.comments = new ArrayList<Comment>();
		this.tags = new TreeSet<Tag>();
	}//end konstructor
	
	
	public Post addComment( User author , String content ){
		Comment commnet = new Comment( this , author , content ).save();
		this.comments.add(commnet);
		return this;
	}//end method
	
    public Post previous(){
    	return Post.find("postedAt < ? order by postedAt desc",postedAt).first();
    }//end method
    
    public Post next(){
    	return Post.find("postedAt > ? order by postedAt asc",postedAt).first();
    }//end method
		
    public Post tagItWith(String name){
    	tags.add(Tag.findOrCreateByName(name));
    	return this;
    }//end method
    
    public static List<Post> findTaggedWith(String tag){
    	return Post.find("SELECT DISTINCT p FROM Post p JOIN p.tags as t where t.name = ? ", tag).fetch();
    }//end method
    
    public String toString(){
    	return this.title ;
    }//end method
    
}//end klazz
