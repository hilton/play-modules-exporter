package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.Lob;

/**
 * Play 1.x module details, from http://www.playframework.org/modules
 */
@Entity
public class Module extends Model {

	public String name;
	public String fullname;
	public String author;
	public String authorId;

	@Lob
	public String description;

	public String homepage;
	public String password;

}
