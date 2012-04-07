import org.h2.tools.Server;
import play.Logger;
import play.jobs.Job;
import play.jobs.OnApplicationStart;

import java.sql.SQLException;

/**
 * Nasty hack to work-around H2 Console mysteriously not starting.
 */
@OnApplicationStart
public class Bootstrap extends Job {

	public void doJob() {
		try {
			Server.createWebServer().start();
			Logger.info("H2 Console started");
		}
		catch (SQLException e) {
			Logger.error("Could not start H2 Console", e);
		}
	}

}