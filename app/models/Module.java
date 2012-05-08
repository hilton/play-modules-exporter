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

    public String getDescriptionClean() {
        String result = description.replaceAll("\\\\r\\\\n", "\n");
        result = result.replaceAll("\\\\\"", "\"");
        return result;
    }

    public String getDescriptionMarkdown() {
        String result = getDescriptionClean();

        // Line breaks
        result = result.replaceAll("\\\\\\\\\\n", "  \n");

        // Textile paragraphs
        result = result.replaceAll("(^|\\n)p(\\(note\\))?\\.\\s+", "$1");

        // Quote blocks
        result = result.replaceAll("(^|\\n)bq\\. (.*)\\n\\n", "$1> $2\n\n");

        // Code blocks
        result = result.replaceAll("(\\n)bc\\. (.*)\\n\\n", "$1    $2\n\n");
        result = result.replaceAll("(\\n)bc\\. (.*\\n)(.*\\n)?(.*\\n)?(.*\\n)?\\n", "$1    $2    $3    $4    $5\n\n");
        result = result.replaceAll("\\n    \\n", "\n\n");
        result = result.replaceAll("\\n\\n+", "\n\n");

        // Lists
        result = result.replaceAll("(^|\\n)# ", "$11. ");
        result = result.replaceAll(" ?: ?\\n(-|\\*) ", ":\n\n* ");
        result = result.replaceAll("(^|\\n)\\*\\* ", "$1  * ");
        result = result.replaceAll("(^|\\n)- ", "$1* ");

        // Headings
        result = result.replaceAll("(^|\\n)h1\\.\\s+", "$1# ");
        result = result.replaceAll("(^|\\n)h2\\.\\s+", "$1## ");
        result = result.replaceAll("(^|\\n)h3\\.\\s+", "$1### ");

        // Code
        result = result.replaceAll("@\\b([^@]+)@", "`$1`");

        // Emphasis
        result = result.replaceAll("([^\\*])\\*\\b([^\\*]+)\\*([^\\*])", "$1**$2**$3");

        // Links
        result = result.replaceAll("\"([^\"]+)\":(\\S+)", "[$1]($2)");

        result = result.replaceAll("\\n+$", "");

        return result;
    }
}
