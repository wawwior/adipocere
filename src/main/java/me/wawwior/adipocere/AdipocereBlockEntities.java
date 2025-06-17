package me.wawwior.adipocere;

import me.wawwior.adipocere.block.entity.CoffinBlockEntity;
import me.wawwior.adipocere.block.entity.TombstoneBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class AdipocereBlockEntities {

  public static final BlockEntityType<CoffinBlockEntity> COFFIN = register("coffin", CoffinBlockEntity::new, AdipocereBlocks.COFFIN);

  public static final BlockEntityType<TombstoneBlockEntity> TOMBSTONE = register("tombstone", TombstoneBlockEntity::new, AdipocereBlocks.TOMBSTONE);
  
  private static <T extends BlockEntity> BlockEntityType<T> register(String name, FabricBlockEntityTypeBuilder.Factory<? extends T> entityFactory, Block... blocks) {

  	Identifier id = Identifier.of(Adipocere.MOD_ID, name);

  	return Registry.register(Registries.BLOCK_ENTITY_TYPE, id, FabricBlockEntityTypeBuilder.<T>create(entityFactory, blocks).build());
  }

	@SuppressWarnings("EmptyMethod")
    public static void initialize() {}
}
