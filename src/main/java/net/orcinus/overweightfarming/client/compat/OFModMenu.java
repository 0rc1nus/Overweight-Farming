package net.orcinus.overweightfarming.client.compat;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import eu.midnightdust.core.MidnightLibClient;
import eu.midnightdust.core.config.MidnightLibConfig;
import eu.midnightdust.lib.config.MidnightConfig;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import java.util.HashMap;
import java.util.Map;

@Environment(EnvType.CLIENT)
public class OFModMenu implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> MidnightLibConfig.getScreen(parent,"overweightfarming");
    }

    @Override
    public Map<String, ConfigScreenFactory<?>> getProvidedConfigScreenFactories() {
        HashMap<String, ConfigScreenFactory<?>> map = new HashMap<>();
        MidnightConfig.configClass.forEach((modid, cClass) -> {
                    if (!MidnightLibClient.hiddenMods.contains(modid))
                        map.put(modid, parent -> MidnightConfig.getScreen(parent, modid));
                }
        );
        return map;
    }
}