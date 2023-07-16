package net.orcinus.overweightfarming.events;

import com.google.common.collect.Lists;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.ParticleEngine;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.FallingBlockRenderer;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.common.util.MutableHashedLinkedMap;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.registries.RegistryObject;
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

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Mod.EventBusSubscriber(modid = OverweightFarming.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
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
                new ResourceLocation(OverweightFarming.MODID, "420"), (stack, world, entity, p_174628_) -> entity != null && StrawHatItem.is420(stack) ? 1.0F : 0.0F)
        );
    }

    @SubscribeEvent
    public static void buildCreativeModeTabContents(BuildCreativeModeTabContentsEvent event) {
        ResourceKey<CreativeModeTab> tabKey = event.getTabKey();
        MutableHashedLinkedMap<ItemStack, CreativeModeTab.TabVisibility> entries = event.getEntries();
        if (tabKey == CreativeModeTabs.BUILDING_BLOCKS) {
            put(entries, OFBlocks.WAXED_SEEDED_PEELED_MELON.get(), OFBlocks.WAXED_HALF_SEEDED_PEELED_MELON.get(), OFBlocks.WAXED_SEEDLESS_PEELED_MELON.get());
        }
        if (tabKey == CreativeModeTabs.NATURAL_BLOCKS) {
            entries.putAfter(new ItemStack(Items.ROSE_BUSH), new ItemStack(OFBlocks.ALLIUM_BUSH.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            entries.putBefore(new ItemStack(Items.MOSS_BLOCK), new ItemStack(OFBlocks.VEGETABLE_COMPOST.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
        if (tabKey == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            entries.putBefore(new ItemStack(Items.SADDLE), new ItemStack(OFItems.STRAW_HAT.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            entries.putAfter(new ItemStack(Items.WARPED_FUNGUS_ON_A_STICK), new ItemStack(OFItems.VEGETABLE_PEELS.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
        if (tabKey == CreativeModeTabs.FOOD_AND_DRINKS) {
            for (RegistryObject<Block> block : OFBlocks.BLOCKS.getEntries()) {
                if (OFBlocks.COMPAT.containsKey(block) && !OFBlocks.COMPAT.containsValue(OverweightFarming.MODID)) {
                    if (!ModList.get().isLoaded(OFBlocks.COMPAT.get(block))) {
                        continue;
                    }
                    put(entries, block.get());
                }
            }
            putAfter(entries, Items.PUMPKIN_PIE, OFBlocks.OVERWEIGHT_BEETROOT.get(), OFBlocks.OVERWEIGHT_CARROT.get(), OFBlocks.OVERWEIGHT_COCOA.get(), OFBlocks.OVERWEIGHT_POTATO.get(), OFBlocks.OVERWEIGHT_BAKED_POTATO.get(), OFBlocks.OVERWEIGHT_NETHER_WART.get(), OFBlocks.OVERWEIGHT_POISONOUS_POTATO.get(), OFBlocks.OVERWEIGHT_APPLE.get(), OFBlocks.OVERWEIGHT_GOLDEN_APPLE.get(), OFBlocks.SEEDED_PEELED_MELON.get(), OFBlocks.HALF_SEEDED_PEELED_MELON.get(), OFBlocks.SEEDLESS_PEELED_MELON.get());
            entries.putAfter(new ItemStack(Items.HONEY_BOTTLE), new ItemStack(OFItems.MELON_JUICE.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
    }

    private static void putAfter(MutableHashedLinkedMap<ItemStack, CreativeModeTab.TabVisibility> map, Item after, ItemLike... item) {
        List<ItemLike> stream = Lists.newArrayList(Arrays.stream(item).toList());
        Collections.reverse(stream);
        stream.forEach(blk -> map.putAfter(new ItemStack(after), new ItemStack(blk), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS));
    }

    private static void put(MutableHashedLinkedMap<ItemStack, CreativeModeTab.TabVisibility> map, ItemLike... item) {
        List<ItemLike> stream = Lists.newArrayList(Arrays.stream(item).toList());
        Collections.reverse(stream);
        stream.forEach(blk -> map.put(new ItemStack(blk), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS));
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
