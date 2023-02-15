package CreativeGuard;

import java.io.File;

public class Directories {

	public final static String pluginsDirectory = "plugins";
	public final static String pluginDirectory = Directories.pluginsDirectory + File.separator +  Main.PLUGIN_NAME;
	public final static String chunkDirectory = Directories.pluginDirectory + File.separator + "chunks";
	public final static String localeDirectory = pluginDirectory + File.separator + "locales";
	
	public static void createDirectoriesIfNotExists() {
		File pd= new File(pluginDirectory);
		if(!pd.exists()) {
			pd.mkdirs();
		}
		File cd= new File(chunkDirectory);
		if(!cd.exists()) {
			cd.mkdir();
		}
		
		File ld = new File(localeDirectory);
		if(!ld.exists()) {
			ld.mkdir();
		}
	}
}
