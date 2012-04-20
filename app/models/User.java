package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * A named user, not part of the original model; used as a convenience for generating the new model.
 */
public class User extends Model implements Comparable<User> {

	public String name;

    public User(long id, final String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public int compareTo(User user)
    {
        if (this.name == null) {
            throw new IllegalStateException("this user’s name is null");
        }
        if (user == null) {
            throw new IllegalArgumentException("null user");
        }
        return this.name.toLowerCase().compareTo(user.name.toLowerCase());
    }
}
