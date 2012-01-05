package models;

import java.util.*;
import javax.persistence.*;

import play.db.jpa.*;

@Entity
@Table(name="comment")
public class Comment extends Model {

	@ManyToOne(fetch=FetchType.EAGER)
	public User author ;
	
	@Temporal(TemporalType.TIMESTAMP)
	public Date postedAt ;
	
	@Lob
	public String content ;
	
	@ManyToOne
	public Post post;
	
	
	public Comment( Post post , User author , String content ){
		this.post = post ;
		this.author = author ;
		this.content = content ;
		this.postedAt = new Date();
	}//end konstructor
	
	
}//end klazz
