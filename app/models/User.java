package models;

import java.util.*;
import javax.persistence.*;

import play.db.jpa.*;

import play.data.validation.*;

@Entity
@Table(name="user")
public class User extends Model {

	@Email
	@Required
	public String email ;
	
	@Required
	public String password ;
	
	@Required
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
	
	public String toString(){
		return this.fullname ;
	}//end method
	
}//end klazz
