package CreativeGuard.Listeners;

import org.bukkit.Chunk;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.event.world.ChunkUnloadEvent;

import CreativeGuard.Directories;
import CreativeGuard.Chunk.ChunkRegister;
import me.nome.BlockStorage.ChunkFile;
import me.nome.BlockStorage.ChunkFileStorage;

public class ChunkListener implements Listener {
    @EventHandler
    public void onChunkLoad(ChunkLoadEvent event) {
       Chunk chunk = event.getChunk();
       int x=chunk.getX();
       int z = chunk.getZ();
       if(ChunkFileStorage.existsChunkFile(new ChunkFile(Directories.chunkDirectory,x,z))) {
    	   ChunkRegister.loadChunk(x, z);
       }
       
    }
    
    @EventHandler
    public void onChunkUnload(ChunkUnloadEvent event) {
    	Chunk chunk = event.getChunk();
        int x=chunk.getX();
        int z = chunk.getZ();
        ChunkRegister.unloadChunk(x, z);
    }
}