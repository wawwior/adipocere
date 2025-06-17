
package me.wawwior.adipocere;

import me.wawwior.adipocere.block.CoffinBlock;
import me.wawwior.adipocere.block.TombstoneBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class AdipocereBlocks {

  public static final Block COFFIN = register(
    // TODO: Settings
    new CoffinBlock(AbstractBlock.Settings.create()),
    "coffin",
    true
  );

  public static final Block TOMBSTONE = register(
    // TODO: Settings
    new TombstoneBlock(AbstractBlock.Settings.create()),
    "tombstone",
    true
  );

  public static Block register(Block block, String name, boolean shouldRegisterItem) {

		// Register the block and its item.
		Identifier id = new Identifier(Adipocere.MOD_ID, name);

		if (shouldRegisterItem) {
			BlockItem blockItem = new BlockItem(block, new Item.Settings());
			Registry.register(Registries.ITEM, id, blockItem);
		}

		return Registry.register(Registries.BLOCK, id, block);
	}

	public static final void initialize() {}

}
