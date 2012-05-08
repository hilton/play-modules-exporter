package controllers;

import models.Module;
import models.User;
import play.mvc.Controller;

import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

public class Application extends Controller {

    public static void index() {
		final List<Module> modules = Module.find("order by lower (fullname)").fetch();
        render(modules);
    }

    public static void users() {
        final List<Module> modules = Module.find("order by lower (fullname)").fetch();
        final SortedSet<User> users = setUsers(modules);
        render(users);
    }

    public static void yaml() {
		request.format = "txt";
		final List<Module> modules = Module.find("order by lower (fullname)").fetch();

        final SortedSet<User> users = setUsers(modules);

		render(modules, users);
    }

    public static void descriptions() {
        final List<Module> modules = Module.find("order by lower (fullname)").fetch();
        render(modules);
    }

    /**
     * Get a list of unique module author’s, with sanitised names taking the first entry when there’s more than one
     * and using sas’ IRL instead of his Twitter name.
     */
    private static SortedSet<User> setUsers(List<Module> modules)
    {
        final SortedSet<User> users = new TreeSet<User>();
        // Use 2 because there is already an admin user with ID=1
        long userId = 2L;
        for (Module module : modules) {
            final String name = module.author.split("(,| and |@)")[0].trim();
            final User user = new User(userId, name);
            module.user = user;
            final boolean added = users.add(user);
            if (added) {
                userId++;
            }
        }
        return users;
    }

}