package ocdiary.riteclicker.handlers;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import ocdiary.riteclicker.RiteClicker;


@Mod.EventBusSubscriber
public class EventHandler {

    private static boolean shouldWork = true;
    private static boolean sneaky = RiteClicker.sneakOn;
    public static String sound = RiteClicker.soundLoc;
    private static Item drop = (Item) Item.REGISTRY.getObject(new ResourceLocation(RiteClicker.dropName));
    private static String aBlocks = RiteClicker.blockName;
    private static String worldTypes = RiteClicker.blockedWorlds;


    @SubscribeEvent
    public static void confChange(ConfigChangedEvent.OnConfigChangedEvent e) {
        if (e.getModID().equals("riteclicker")) {
            RiteClicker.config();
        }
    }

    @SubscribeEvent
    public static void onJoin(PlayerEvent.PlayerLoggedInEvent e) {
        String world = e.player.world.getWorldType().getName();
        if (worldTypes.contains(world)) {
            shouldWork = false;
        } else {
            shouldWork = true;
        }
    }

    @SubscribeEvent
    public static void onPRiteClick(PlayerInteractEvent.RightClickBlock e)
    {
        EntityPlayer p = e.getEntityPlayer();
        ResourceLocation loc = new ResourceLocation(sound);
        SoundEvent sound = new SoundEvent(loc);
        Block targetBlock = e.getWorld().getBlockState(e.getPos()).getBlock();


        if (shouldWork) {
            if (sneaky)
            {
                if ((p.getHeldItem(EnumHand.MAIN_HAND).isEmpty()) && (p.isSneaking()))
                {
                    if (aBlocks.contains(targetBlock.getRegistryName().toString())) {
                        if (e.getWorld().isRemote)
                        {
                            p.swingArm(EnumHand.MAIN_HAND);
                            p.world.playSound(p, p.posX, p.posY, p.posZ, sound, SoundCategory.BLOCKS, 20.0F, 1.0F);
                        }
                        else if ((!e.getWorld().isRemote) && (e.getHand() == EnumHand.MAIN_HAND))
                        {
                            p.dropItem(new ItemStack(drop, 1, 0), false);
                        }
                    }
                }
            }

            else if (p.getHeldItem(EnumHand.MAIN_HAND).isEmpty())
            {
                if (aBlocks.contains(targetBlock.getRegistryName().toString())) {
                    if (e.getWorld().isRemote)
                    {
                        p.swingArm(EnumHand.MAIN_HAND);
                        p.world.playSound(p, p.posX, p.posY, p.posZ, sound, SoundCategory.BLOCKS, 20.0F, 1.0F);
                    }
                    else if ((!e.getWorld().isRemote) && (e.getHand() == EnumHand.MAIN_HAND))
                    {
                        p.dropItem(new ItemStack(drop, 1, 0), false);
                    }
                }
            }
        }
    }
}
