package net.silentchaos512.gear;

import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.silentchaos512.gear.client.ColorHandlers;
import net.silentchaos512.gear.client.KeyTracker;
import net.silentchaos512.gear.client.event.ExtraBlockBreakHandler;
import net.silentchaos512.gear.client.event.TooltipHandler;
import net.silentchaos512.gear.client.models.ToolHeadModel;
import net.silentchaos512.gear.client.models.ToolModel;
import net.silentchaos512.gear.client.renderer.TEISREquipment;
import net.silentchaos512.gear.init.ModItems;
import net.silentchaos512.lib.registry.SRegistry;

public class ClientProxy extends CommonProxy {

    @Override
    public void preInit(SRegistry registry, FMLPreInitializationEvent event) {
        super.preInit(registry, event);
        registry.clientPreInit(event);

        MinecraftForge.EVENT_BUS.register(KeyTracker.INSTANCE);
        MinecraftForge.EVENT_BUS.register(TooltipHandler.INSTANCE);

        ModelLoaderRegistry.registerLoader(ToolHeadModel.Loader.INSTANCE);
        ModelLoaderRegistry.registerLoader(ToolModel.Loader.INSTANCE);
    }

    @Override
    public void init(SRegistry registry, FMLInitializationEvent event) {
        super.init(registry, event);
        registry.clientInit(event);

        MinecraftForge.EVENT_BUS.register(ExtraBlockBreakHandler.INSTANCE);

        ColorHandlers.init();
        ModItems.toolClasses.values().forEach(item -> ((Item) item).setTileEntityItemStackRenderer(TEISREquipment.INSTANCE));
    }

    @Override
    public void postInit(SRegistry registry, FMLPostInitializationEvent event) {
        super.postInit(registry, event);
        registry.clientPostInit(event);
    }
}
