package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
import java.util.Date;

/**
 * Details of a single module release.
 */
@Entity
public class ModuleRelease extends Model {

	public String frameworkMatch;
	public boolean isDefault;
	public Date publishedDate;
	public String version;


	@Override
	public String toString() {
		return version;
	}
//	@ManyToOne
//	@JoinColumn(name="module_id")
//	public Module module;
}
