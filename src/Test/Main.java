package Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import CreativeGuard.Directories;
import CreativeGuard.Chunk.ChunkRegister;
import CreativeGuard.Material.FallingMaterial;
import CreativeGuard.Material.MaterialInterface;
import CreativeGuard.Material.MaterialUtils;
import CreativeGuard.Player.EntityPlayerPlace;
import CreativeGuard.Player.Gamemodes;
import CreativeGuard.Player.PlayerFileStore;
import CreativeGuard.Utils.FindUtil;
import me.nome.BlockStorage.ChunkContent;



public class Main {
  public static void main(String[] args) throws IOException {
	  EntityPlayerPlace epp = new EntityPlayerPlace("MINECRART");
	  epp.place();
	  epp.place();
	  epp.place();
	  epp.place();
	  epp.place();
	  System.out.print(epp.canPlace());
  }
}
