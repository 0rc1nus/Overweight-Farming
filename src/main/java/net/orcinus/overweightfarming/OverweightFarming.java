package net.orcinus.overweightfarming;

import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.orcinus.overweightfarming.config.OFConfig;
import net.orcinus.overweightfarming.init.OFBlockEntityTypes;
import net.orcinus.overweightfarming.init.OFBlocks;
import net.orcinus.overweightfarming.init.OFEntityTypes;
import net.orcinus.overweightfarming.init.OFItems;
import net.orcinus.overweightfarming.init.OFParticleTypes;
import net.orcinus.overweightfarming.init.OFTreeDecoratorTypes;
import net.orcinus.overweightfarming.init.OFVanillaIntegration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(OverweightFarming.MODID)
public class OverweightFarming {
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MODID = "overweight_farming";

    public OverweightFarming(IEventBus modEventBus, ModContainer modContainer) {
        OFBlocks.BLOCKS.register(modEventBus);
        OFBlockEntityTypes.BLOCK_ENTITY_TYPES.register(modEventBus);
        OFEntityTypes.ENTITY_TYPES.register(modEventBus);
        OFItems.ITEMS.register(modEventBus);
        OFParticleTypes.PARTICLE_TYPES.register(modEventBus);
        OFTreeDecoratorTypes.TREE_DECORATORS.register(modEventBus);

        modEventBus.addListener(this::commonSetup);

        modContainer.registerConfig(ModConfig.Type.COMMON, OFConfig.COMMON);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(OFVanillaIntegration::init);
    }

    public static ResourceLocation id(String name) {
        return ResourceLocation.fromNamespaceAndPath(MODID, name);
    }

}
