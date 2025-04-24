package net.orcinus.overweightfarming.events;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.ParticleEngine;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.FallingBlockRenderer;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.orcinus.overweightfarming.OverweightFarming;
import net.orcinus.overweightfarming.client.models.StrawHatModel;
import net.orcinus.overweightfarming.client.particles.MelonFallProvider;
import net.orcinus.overweightfarming.client.particles.MelonHangProvider;
import net.orcinus.overweightfarming.client.particles.MelonLandProvider;
import net.orcinus.overweightfarming.init.OFBlocks;
import net.orcinus.overweightfarming.init.OFEntityTypes;
import net.orcinus.overweightfarming.init.OFItems;
import net.orcinus.overweightfarming.init.OFModelLayers;
import net.orcinus.overweightfarming.init.OFParticleTypes;
import net.orcinus.overweightfarming.items.StrawHatItem;

@EventBusSubscriber(modid = OverweightFarming.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEvents {

    @SubscribeEvent
    public static void onClientSetup(final FMLClientSetupEvent event) {
        ItemBlockRenderTypes.setRenderLayer(OFBlocks.OVERWEIGHT_BEETROOT_STEM.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(OFBlocks.OVERWEIGHT_CARROT_STEM.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(OFBlocks.OVERWEIGHT_POTATO_STEM.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(OFBlocks.OVERWEIGHT_GINGER_STEM.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(OFBlocks.ALLIUM_BUSH.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(OFBlocks.OVERWEIGHT_CABBAGE_STEM.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(OFBlocks.OVERWEIGHT_NETHER_WART_STEM.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(OFBlocks.OVERWEIGHT_APPLE_STEM.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(OFBlocks.OVERWEIGHT_GOLDEN_APPLE_STEM.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(OFBlocks.POTTED_OVERWEIGHT_BEETROOT.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(OFBlocks.POTTED_OVERWEIGHT_POTATO.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(OFBlocks.POTTED_OVERWEIGHT_POISONOUS_POTATO.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(OFBlocks.POTTED_OVERWEIGHT_APPLE.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(OFBlocks.POTTED_OVERWEIGHT_GOLDEN_APPLE.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(OFBlocks.POTTED_OVERWEIGHT_CARROT.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(OFBlocks.POTTED_OVERWEIGHT_COCOA.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(OFBlocks.POTTED_OVERWEIGHT_CABBAGE.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(OFBlocks.POTTED_OVERWEIGHT_ONION.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(OFBlocks.POTTED_OVERWEIGHT_NETHER_WART.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(OFBlocks.POTTED_OVERWEIGHT_GINGER.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(OFBlocks.POTTED_OVERWEIGHT_KIWI.get(), RenderType.cutout());
        event.enqueueWork(() -> ItemProperties.register(OFItems.STRAW_HAT.get(),
                OverweightFarming.id("420"), (stack, world, entity, p_174628_) -> entity != null && StrawHatItem.is420(stack) ? 1.0F : 0.0F)
        );
    }

    @SubscribeEvent
    public static void buildCreativeModeTabContents(BuildCreativeModeTabContentsEvent event) {
        ResourceKey<CreativeModeTab> tabKey = event.getTabKey();
        if (tabKey == CreativeModeTabs.NATURAL_BLOCKS) {
            event.insertAfter(new ItemStack(Blocks.MELON), new ItemStack(OFBlocks.SEEDED_PEELED_MELON.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(new ItemStack(OFBlocks.SEEDED_PEELED_MELON.get()), new ItemStack(OFBlocks.HALF_SEEDED_PEELED_MELON.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(new ItemStack(OFBlocks.HALF_SEEDED_PEELED_MELON.get()), new ItemStack(OFBlocks.SEEDLESS_PEELED_MELON.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(new ItemStack(OFBlocks.SEEDLESS_PEELED_MELON.get()), new ItemStack(OFBlocks.WAXED_SEEDED_PEELED_MELON.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(new ItemStack(OFBlocks.WAXED_SEEDED_PEELED_MELON.get()), new ItemStack(OFBlocks.WAXED_HALF_SEEDED_PEELED_MELON.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(new ItemStack(OFBlocks.WAXED_HALF_SEEDED_PEELED_MELON.get()), new ItemStack(OFBlocks.WAXED_SEEDLESS_PEELED_MELON.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);

            event.insertBefore(new ItemStack(Items.MOSS_BLOCK), new ItemStack(OFBlocks.VEGETABLE_COMPOST.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);

            event.insertAfter(new ItemStack(Items.JACK_O_LANTERN), new ItemStack(OFBlocks.OVERWEIGHT_BEETROOT.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(new ItemStack(OFBlocks.OVERWEIGHT_BEETROOT.get()), new ItemStack(OFBlocks.PEELED_OVERWEIGHT_BEETROOT.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(new ItemStack(OFBlocks.PEELED_OVERWEIGHT_BEETROOT.get()), new ItemStack(OFBlocks.OVERWEIGHT_CARROT.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(new ItemStack(OFBlocks.OVERWEIGHT_CARROT.get()), new ItemStack(OFBlocks.PEELED_OVERWEIGHT_CARROT.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(new ItemStack(OFBlocks.PEELED_OVERWEIGHT_CARROT.get()), new ItemStack(OFBlocks.OVERWEIGHT_COCOA.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(new ItemStack(OFBlocks.OVERWEIGHT_COCOA.get()), new ItemStack(OFBlocks.PEELED_OVERWEIGHT_COCOA.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(new ItemStack(OFBlocks.PEELED_OVERWEIGHT_COCOA.get()), new ItemStack(OFBlocks.OVERWEIGHT_POTATO.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(new ItemStack(OFBlocks.OVERWEIGHT_POTATO.get()), new ItemStack(OFBlocks.PEELED_OVERWEIGHT_POTATO.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(new ItemStack(OFBlocks.PEELED_OVERWEIGHT_POTATO.get()), new ItemStack(OFBlocks.OVERWEIGHT_BAKED_POTATO.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(new ItemStack(OFBlocks.OVERWEIGHT_BAKED_POTATO.get()), new ItemStack(OFBlocks.OVERWEIGHT_POISONOUS_POTATO.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(new ItemStack(OFBlocks.OVERWEIGHT_POISONOUS_POTATO.get()), new ItemStack(OFBlocks.OVERWEIGHT_APPLE.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(new ItemStack(OFBlocks.OVERWEIGHT_APPLE.get()), new ItemStack(OFBlocks.OVERWEIGHT_GOLDEN_APPLE.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(new ItemStack(OFBlocks.OVERWEIGHT_GOLDEN_APPLE.get()), new ItemStack(OFBlocks.OVERWEIGHT_NETHER_WART.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);

            for (DeferredHolder<Block, ? extends Block> block : OFBlocks.BLOCKS.getEntries()) {
                if (OFBlocks.COMPAT.containsKey(block) && ModList.get().isLoaded(OFBlocks.COMPAT.get(block))) {
                    event.insertAfter(new ItemStack(OFBlocks.SEEDLESS_PEELED_MELON.get()), new ItemStack(block.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
                }
            }
        }
        if (tabKey == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            event.insertBefore(new ItemStack(Items.SADDLE), new ItemStack(OFItems.STRAW_HAT), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(new ItemStack(Items.WARPED_FUNGUS_ON_A_STICK), new ItemStack(OFItems.VEGETABLE_PEELS.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
        if (tabKey == CreativeModeTabs.FOOD_AND_DRINKS) {
            event.insertAfter(new ItemStack(Items.HONEY_BOTTLE), new ItemStack(OFItems.MELON_JUICE.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
    }

    @SubscribeEvent
    public static void registerEntityLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(OFModelLayers.STRAW_HAT, StrawHatModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerRenderer(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(OFEntityTypes.OVERWEIGHT_APPLE_FALLING_BLOCK.get(), FallingBlockRenderer::new);
    }

    @SubscribeEvent
    public static void registerParticleFactories(RegisterParticleProvidersEvent event) {
        Minecraft minecraft = Minecraft.getInstance();
        ParticleEngine particleEngine = minecraft.particleEngine;
        particleEngine.register(OFParticleTypes.DRIPPING_MELON.get(), MelonHangProvider::new);
        particleEngine.register(OFParticleTypes.FALLING_MELON.get(), MelonFallProvider::new);
        particleEngine.register(OFParticleTypes.LANDING_MELON.get(), MelonLandProvider::new);
    }

}
