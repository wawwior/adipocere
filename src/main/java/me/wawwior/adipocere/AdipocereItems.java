package me.wawwior.adipocere;

import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class AdipocereItems {

  public static final Item GRAVE_WAX = register(
    new Item(new Item.Settings()),
    "grave_wax"
  );
  
  public static Item register(Item item, String name) {

		// Create the identifier for the item.
		Identifier id = new Identifier(Adipocere.MOD_ID, name);

		// Register the item.
		return Registry.register(Registries.ITEM, id, item);
	}  

	@SuppressWarnings("EmptyMethod")
    public static void initialize() {}
}
