package models;

import java.util.*;

import javax.persistence.*;

import play.db.jpa.*;

@Entity
@Table(name="post")
public class Post extends Model {

	public String title ;
	
	@Temporal(TemporalType.TIMESTAMP)	
	public Date postedAt ;
	
	@Lob
	public String content ;
	
	@ManyToOne(fetch=FetchType.EAGER)
	public User author;

	
	@OneToMany(mappedBy="post",cascade=CascadeType.ALL)
	public List<Comment> comments;
	
	public Post( User author , 
			String title , 
			String content ){
		this.author = author ;
		this.title = title ;
		this.content = content ;
		this.postedAt = new Date();		
		this.comments = new ArrayList<Comment>();
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
    	return Post.find("postedAt < ? order by postedAt asc",postedAt).first();
    }//end method
		
}//end klazz
