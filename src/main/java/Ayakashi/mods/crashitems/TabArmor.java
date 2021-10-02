package Ayakashi.mods.crashitems;

import Ayakashi.helpers.ItemStackHelper;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTException;

import java.util.List;

public class TabArmor extends CreativeTabs {
    public TabArmor() {
        super(13, "ArmorTab");
    }

    public void displayAllReleventItems(List<ItemStack> itemList) {
        String[] arrstring = new String[]{"{Unbreakable:1,ench:[{id:0,lvl:1000}]}", "{AttributeModifiers:[{AttributeName:\"generic.knockbackResistance\",Name:\"generic.knockbackResistance\",Amount:1,Operation:0,UUIDLeast:722576,UUIDMost:658559,Slot:\"head\"}],Unbreakable:1,ench:[{id:0,lvl:1000}]}", "{Unbreakable:1,ench:[{id:0,lvl:1000},{id:7,lvl:1000}]}", "{AttributeModifiers:[{AttributeName:\"generic.knockbackResistance\",Name:\"generic.knockbackResistance\",Amount:1,Operation:0,UUIDLeast:859071,UUIDMost:670308}],Unbreakable:1,ench:[{id:0,lvl:1000},{id:7,lvl:1000}]}"};

        for (String NBT : arrstring) {
            ItemStack diamond_helmet = new ItemStack(Items.diamond_helmet);
            ItemStack diamond_chestplate = new ItemStack(Items.diamond_chestplate);
            ItemStack diamond_leggings = new ItemStack(Items.diamond_leggings);
            ItemStack diamond_boots = new ItemStack(Items.diamond_boots);
            ItemStack[] arritemStack = new ItemStack[]{diamond_helmet, diamond_chestplate, diamond_leggings, diamond_boots};

            for (ItemStack stack : arritemStack) {
                try {
                    stack.setTagCompound(JsonToNBT.getTagFromJson(NBT));
                } catch (NBTException ignored) {
                }

                itemList.add(stack);
            }

            ItemStackHelper.fillEmpty(itemList);
        }

    }

    public ItemStack getIconItemStack() {
        return new ItemStack(Items.diamond_chestplate);
    }

    public String getTranslatedTabLabel() {
        return "Armor";
    }

    public Item getTabIconItem() {
        return null;
    }
}
