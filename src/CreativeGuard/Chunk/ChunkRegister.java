package CreativeGuard.Chunk;
import me.nome.BlockStorage.ChunkContent;
import me.nome.BlockStorage.ChunkFile;
import me.nome.BlockStorage.ChunkFileStorage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import CreativeGuard.Directories;
import CreativeGuard.ErrorProtect;

public class ChunkRegister {
	private static Map<String,ChunkContent> chunks= new HashMap<String,ChunkContent>();
	
	public static ChunkContent get(int x, int z) {
		String chunkName = ChunkRegister.getChunkName(x, z);
		return chunks.get(chunkName);
	}
	
	public static ChunkContent getOrCreate(int x, int z) {
		String chunkName = ChunkRegister.getChunkName(x, z);
		if(chunks.get(chunkName)==null) {
			ChunkRegister.loadChunk(x, z);
		}
		return chunks.get(chunkName);
	}
	
	public static void loadChunk(int x, int z) {
		ChunkFile chunkFile= new ChunkFile(Directories.chunkDirectory,x,z);
		if(ChunkFileStorage.existsChunkFile(chunkFile)) {
			try {
				ChunkRegister.set(x, z, ChunkFileStorage.readChunk(chunkFile));
			} catch (ClassNotFoundException | IOException e) {
				ErrorProtect.HAS_FATAL_ERROR=true;
				e.printStackTrace();
			}
		} else {
			ChunkRegister.set(x, z, new ChunkContent(x,z));
		}
	}
	
	public static void unloadChunk(int x, int z) {
		ChunkFile chunkFile= new ChunkFile(Directories.chunkDirectory,x,z);
		if(ChunkRegister.get(x, z) != null) {
			try {
				ChunkFileStorage.writeChunk(chunkFile, ChunkRegister.get(x, z));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ChunkRegister.chunks.remove(ChunkRegister.getChunkName(x, z));
		}
		
	}
	
	
	public static void set(int x, int z,ChunkContent chunk) {
		ChunkRegister.chunks.put(ChunkRegister.getChunkName(x, z), chunk);
	}
	
	public static  void save(int x, int z) {
		ChunkFile cf = new ChunkFile(Directories.chunkDirectory, x,z);
		ChunkContent cc = ChunkRegister.get(x, z);
		try {
			ChunkFileStorage.writeChunk(cf, cc);
		} catch (IOException e) {
			ErrorProtect.HAS_FATAL_ERROR=true;
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static String getChunkName(int x, int z) {
		return x + "-" + z;
	}
	
	public static void saveAll() {
		for (Map.Entry<String, ChunkContent> set :
            chunks.entrySet()) {

          try {
        	if(set.getValue().getBlocks().size()>0) {
        		ChunkFileStorage.writeChunk(new ChunkFile(Directories.chunkDirectory, set.getValue().getX(),set.getValue().getZ()), set.getValue());
        	}
			
		} catch (IOException e) {
			ErrorProtect.HAS_FATAL_ERROR=true;
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       }
	}
	
	public static int getSize() {
		return chunks.size();
	}
}
