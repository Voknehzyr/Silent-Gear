package net.silentchaos512.gear.item;

import net.minecraft.item.ItemStack;
import net.silentchaos512.gear.network.KeyPressOnItemPacket;

public interface ICycleItem {
    void onCycleKeyPress(ItemStack stack, KeyPressOnItemPacket.Type direction);
}
