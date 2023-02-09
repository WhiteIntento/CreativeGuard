package CreativeGuard.Utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import java.util.Base64;

public class ItemStackSerializer {

	
	// Serialize an ItemStack object
		public static byte[] serialize(ItemStack itemStack) {
		    try (ByteArrayOutputStream b = new ByteArrayOutputStream(); BukkitObjectOutputStream o = new BukkitObjectOutputStream(b)) {
		        o.writeObject(itemStack);
		        return b.toByteArray();
		    } catch (IOException e) {
		        throw new IllegalArgumentException("Failed to serialize item stack", e);
		    }
		}

		// Deserialize an ItemStack object
		public static ItemStack deserialize(byte[] data) {
		    try (ByteArrayInputStream b = new ByteArrayInputStream(data); BukkitObjectInputStream o = new BukkitObjectInputStream(b)) {
		        return (ItemStack) o.readObject();
		    } catch (IOException | ClassNotFoundException e) {
		        throw new IllegalArgumentException("Failed to deserialize item stack", e);
		    }
		}
		
		public static String toBase64(byte[] bytes) {
			return Base64.getEncoder().encodeToString(bytes);
		}
		
		public static byte[] fromBase64(String base64) {
			return Base64.getDecoder().decode(base64);
		}
		
		public static String serializeToBase64(ItemStack itemstack) {
			return ItemStackSerializer.toBase64(ItemStackSerializer.serialize(itemstack));
		}
		
		public static ItemStack deserializeFromBase64(String base64) {
			return ItemStackSerializer.deserialize(ItemStackSerializer.fromBase64(base64));
		}
}
