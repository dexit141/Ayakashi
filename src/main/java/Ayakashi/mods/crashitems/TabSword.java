package Ayakashi.mods.crashitems;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTException;

import java.util.List;

public class TabSword extends CreativeTabs {
    ItemStack empty;

    public TabSword() {
        super(14, "SwordTab");
        this.empty = new ItemStack(Blocks.air);
    }

    public void displayAllReleventItems(List<ItemStack> itemList) {
        String[] arrstring = new String[]{"{Unbreakable:1,ench:[{id:16,lvl:1000}]}", "{Unbreakable:1,ench:[{id:21,lvl:1000}]}", "{Unbreakable:1,ench:[{id:16,lvl:1000},{id:21,lvl:1000}]}", "{Unbreakable:1,ench:[{id:19,lvl:5}]}", "{Unbreakable:1,ench:[{id:19,lvl:1000}]}"};

        for (String NBT : arrstring) {
            ItemStack diamond_sword = new ItemStack(Items.diamond_sword);
            ItemStack golden_sword = new ItemStack(Items.golden_sword);
            ItemStack iron_sword = new ItemStack(Items.iron_sword);
            ItemStack stone_sword = new ItemStack(Items.stone_sword);
            ItemStack wooden_sword = new ItemStack(Items.wooden_sword);
            ItemStack stick = new ItemStack(Items.stick);
            ItemStack[] arritemStack = new ItemStack[]{diamond_sword, golden_sword, iron_sword, stone_sword, wooden_sword, stick};

            for (ItemStack stack : arritemStack) {
                try {
                    stack.setTagCompound(JsonToNBT.getTagFromJson(NBT));
                } catch (NBTException ignored) {
                }

                itemList.add(stack);
            }

            itemList.add(this.empty);
            itemList.add(this.empty);
            itemList.add(this.empty);
        }

    }

    public String getTranslatedTabLabel() {
        return "Swords";
    }

    public ItemStack getIconItemStack() {
        return new ItemStack(Items.diamond_sword);
    }

    public Item getTabIconItem() {
        return null;
    }
}
