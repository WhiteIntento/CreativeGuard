package CreativeGuard.Utils;

import java.util.List;

public class FindUtil {

	public static boolean existsInList(String search, List<String> list) {
		for(String e : list) {
			if(e.charAt(0) == "@".charAt(0)) {
				if(e.endsWith(search)) {
					return true;
				}
			}
			if(e.equalsIgnoreCase(search)) {
				return true;
			}
		}
		return false;
	}
}
