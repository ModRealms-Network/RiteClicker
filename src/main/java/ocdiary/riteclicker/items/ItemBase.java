package ocdiary.riteclicker.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import ocdiary.riteclicker.RiteClicker;

public class ItemBase extends Item {

    protected String name;

    public ItemBase(String name){
        this.name = name;
        setUnlocalizedName(name);
        setRegistryName(name);
        this.setCreativeTab(CreativeTabs.MISC);
    }

    public void registerItemModel(){
        RiteClicker.proxy.registerItemRenderer(this, 0, name);
    }

}
