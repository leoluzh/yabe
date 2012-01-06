package models;

import java.util.*;
import javax.persistence.*;

import play.data.validation.MaxSize;
import play.data.validation.Required;
import play.db.jpa.*;

@Entity
@Table(name="comment")
public class Comment extends Model {

	@Required
	@ManyToOne(fetch=FetchType.EAGER)
	public User author ;
	
	@Required
	@Temporal(TemporalType.TIMESTAMP)
	public Date postedAt ;
	
	@Required
	@MaxSize(10000)
	@Lob
	public String content ;
	
	@Required
	@ManyToOne
	public Post post;
	
	
	public Comment( Post post , User author , String content ){
		this.post = post ;
		this.author = author ;
		this.content = content ;
		this.postedAt = new Date();
	}//end konstructor
	
	
}//end klazz
