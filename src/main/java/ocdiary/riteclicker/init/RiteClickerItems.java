package ocdiary.riteclicker.init;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;
import ocdiary.riteclicker.items.ItemBase;

public class RiteClickerItems {

    public static ItemBase pebs = new ItemBase("pebs");

    public static void register(IForgeRegistry<Item> registry) {
        registry.registerAll(
                pebs
        );
    }

    public static void registerModels() {
        pebs.registerItemModel();
    }

}
