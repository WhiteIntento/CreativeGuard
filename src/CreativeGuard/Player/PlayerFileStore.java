package CreativeGuard.Player;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;

import CreativeGuard.Main;

public class PlayerFileStore{
	

	public static String getDirectory() {
		
		return "plugins" + File.separator + Main.PLUGIN_NAME + File.separator + "player_data" + File.separator;
	}
	
	public static String getDirectory(String uuid) {
		return PlayerFileStore.getDirectory() + uuid;
	}
	
	public static void initDirectory() {
		File f = new File(PlayerFileStore.getDirectory());
		if(!f.isDirectory()) {
			f.mkdirs();
		}
	}
	
	public static String getPlayerFile(String uuid, String filename){
		return PlayerFileStore.getDirectory() + uuid + File.separator +  filename +".json";
	}
	
	public static Gamemodes get(String uuid, String filename) throws IOException {
		File f=new File(PlayerFileStore.getPlayerFile(uuid,filename));
		if(!f.exists()) {
			Gamemodes gm = new Gamemodes();
			PlayerFileStore.save(uuid, filename, gm);
			return gm;
		} else {
			FileReader fr = new FileReader(f);
			String data="";
			int i;
			while((i=fr.read())!=-1) {
				data+=(char) i;
			}
			fr.close();
			Gson gson = new Gson();
			SerializeGamemodes sg=gson.fromJson(data, SerializeGamemodes.class);
			return Gamemodes.unserialize(sg);
		}
		
	}
	
	public static void save(String uuid,String filename,Object data) throws IOException {
		Gson gson = new Gson();
		String d = gson.toJson(data);
		File dir=new File(PlayerFileStore.getDirectory(uuid));
		File file =new File(PlayerFileStore.getPlayerFile(uuid, filename));
		if(!dir.exists()) {
			dir.mkdirs();
			file.createNewFile();
		}
		FileWriter fw= new FileWriter(file);
		fw.write(d);
		fw.close();
	}
}
