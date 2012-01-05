package models;

import java.util.*;
import javax.persistence.*;

import play.db.jpa.*;

@Entity
@Table(name="user")
public class User extends Model {

	public String email ;
	public String password ;
	public String fullname ;
	public boolean isAdmin ;
	
	@Temporal(TemporalType.TIMESTAMP)
	public Date createdAt;
	
	public User( String email , String password , String fullname ){
		this.email = email ;
		this.password = password ;
		this.fullname = fullname ;
		this.createdAt = new Date();
	}//end konstructor
	
	
	public static User connect( String email , String password ){
		return find("byEmailAndPassword",email,password).first();
	}//end method
	
}//end klazz
