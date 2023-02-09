package CreativeGuard.Material;

import java.util.HashMap;
import java.util.Map;

public class MaterialUtils {

	public static Map<String,MaterialInterface> createMap(Class<? extends Enum<?>> e){
		Map<String,MaterialInterface> map = new HashMap<String,MaterialInterface>();
		for(Enum<?> enumi : e.getEnumConstants()) {
			map.put(enumi.name(), (MaterialInterface) enumi);
		}
		return map;
	}
}
