package ocdiary.riteclicker.proxy;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import ocdiary.riteclicker.RiteClicker;

public class ClientProxy extends CommonProxy{
    @Override
    public void preInit() {
        super.preInit();
    }

    @Override
    public void init() {
        super.init();
    }

    @Override
    public void postInit() {
        super.postInit();
    }

    @Override
    public void registerItemRenderer(Item item, int meta, String id) {
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(RiteClicker.MODID+ ":" + id, "inventory"));
    }
}
