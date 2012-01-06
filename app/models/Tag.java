package models;

import play.data.validation.Required;
import play.db.jpa.*;
import java.util.*;
import javax.persistence.*;

@Entity
@Table(name="tag")
public class Tag extends Model implements Comparable<Tag>{

	@Required
	String name ;
	
	public Tag(String name){
		this.name = name ;
	}//end konstructor
	
	@Override
	public String toString(){
		return name;
	}//end method
	
	public int compareTo( Tag other ){
		return name.compareTo(other.name);
	}//end method

	public static Tag findOrCreateByName(String name){
		Tag tag = Tag.find("byName",name).first();
		if( tag == null ){
			tag = new Tag(name);
		}//end if
		return tag;
	}//end method
	
	public static List<Map> getCloud(){
		List<Map> result = Tag.find("SELECT new map(t.name as tag , count(p.id) as pound ) from Post p JOIN p.tags as t  GROUP BY t.name ORDER BY t.name ").fetch();
		return result;
	}//end method
	
}//end klazz
