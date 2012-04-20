package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import java.util.List;

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

	//		@OneToMany(mappedBy = "module")
	@OneToMany
	@JoinColumn(name = "module_id", referencedColumnName = "id")
	public List<ModuleRelease> releases;

    @Transient
    public User user;
}
