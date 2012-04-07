package controllers;

import models.Module;
import play.mvc.Controller;

import java.util.List;

public class Application extends Controller {

    public static void index() {
		final List<Module> modules = Module.find("order by lower (fullname)").fetch();
		render(modules);
    }

    public static void yaml() {
		request.format = "txt";
		final List<Module> modules = Module.find("order by lower (fullname)").fetch();
		render(modules);
    }

}