package skadic.nodes.capabilities;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import skadic.nodes.utils.TagRef;


/**
 * Created by eti22 on 05.10.2016.
 */
public class CapabilityNodeItemStorage implements Capability.IStorage<ICapabilityNodeItem>{



    @Override
    public NBTBase writeNBT(Capability<ICapabilityNodeItem> capability, ICapabilityNodeItem instance, EnumFacing side) {

        NBTTagList tagList = new NBTTagList();

        NBTTagCompound valueTag = new NBTTagCompound();


        valueTag.setBoolean(TagRef.HAS_NODE, instance.hasNode());
        valueTag.setBoolean(TagRef.IS_EXPORTING, instance.getExport());
        valueTag.setBoolean(TagRef.HAS_FILTER, instance.getHasFilter());
        valueTag.setInteger(TagRef.PRIORITY, instance.getPriority());

        tagList.appendTag(valueTag);

        for (Item item : instance.getFilteredItems()) {
            NBTTagCompound tag = new NBTTagCompound();
            new ItemStack(item).writeToNBT(tag);
            tagList.appendTag(tag);
        }

        return tagList;
    }


    @Override
    public void readNBT(Capability<ICapabilityNodeItem> capability, ICapabilityNodeItem instance, EnumFacing side, NBTBase nbt) {

        NBTTagList tagList = (NBTTagList) nbt;

        NBTTagCompound valueTag = tagList.getCompoundTagAt(0);
        tagList.removeTag(0);

        instance.setHasNode(valueTag.getBoolean(TagRef.HAS_NODE));
        instance.setExport(valueTag.getBoolean(TagRef.IS_EXPORTING));
        instance.setHasFilter(valueTag.getBoolean(TagRef.HAS_FILTER));
        instance.setPriority(valueTag.getInteger(TagRef.PRIORITY));

        while (!tagList.hasNoTags()) {
            NBTTagCompound tag = tagList.getCompoundTagAt(0);
            instance.addToItemFilter(Item.getByNameOrId(tag.getString("id")));
            tagList.removeTag(0);
        }


    }
}
