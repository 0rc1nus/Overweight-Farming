package net.orcinus.overweightfarming;

import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
import net.orcinus.overweightfarming.config.OFConfig;
import net.orcinus.overweightfarming.events.MiscEvents;
import net.orcinus.overweightfarming.events.MobEvents;
import net.orcinus.overweightfarming.init.OFBlockEntityTypes;
import net.orcinus.overweightfarming.init.OFBlocks;
import net.orcinus.overweightfarming.init.OFEntityTypes;
import net.orcinus.overweightfarming.init.OFItems;
import net.orcinus.overweightfarming.init.OFParticleTypes;
import net.orcinus.overweightfarming.init.OFTreeDecoratorTypes;
import net.orcinus.overweightfarming.init.OFVanillaIntegration;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(OverweightFarming.MODID)
public class OverweightFarming {
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MODID = "overweight_farming";

    public OverweightFarming() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::setup);

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, OFConfig.COMMON);

        OFBlocks.BLOCKS.register(modEventBus);
        OFBlockEntityTypes.BLOCK_ENTITY_TYPES.register(modEventBus);
        OFEntityTypes.ENTITY_TYPES.register(modEventBus);
        OFItems.ITEMS.register(modEventBus);
        OFParticleTypes.PARTICLE_TYPES.register(modEventBus);
        OFTreeDecoratorTypes.TREE_DECORATORS.register(modEventBus);

        IEventBus eventBus = MinecraftForge.EVENT_BUS;
        eventBus.register(this);
        eventBus.register(new MobEvents());
        eventBus.register(new MiscEvents());
    }

    private void setup(final FMLCommonSetupEvent event) {
        OFVanillaIntegration.init();
    }

}
