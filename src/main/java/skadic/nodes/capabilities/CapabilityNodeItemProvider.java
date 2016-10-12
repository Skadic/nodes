package skadic.nodes.capabilities;

import net.minecraft.nbt.NBTBase;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

import javax.annotation.Nullable;

@SuppressWarnings("all")
public class CapabilityNodeItemProvider implements ICapabilitySerializable<NBTBase>{


    public CapabilityNodeItemProvider(){}

    @CapabilityInject(ICapabilityNodeItem.class)
    public static final Capability<ICapabilityNodeItem> ITEM_NODE_CAP = null;

    private ICapabilityNodeItem instance = ITEM_NODE_CAP.getDefaultInstance();


    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
        return capability == ITEM_NODE_CAP;
    }

    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        return capability == ITEM_NODE_CAP ? ITEM_NODE_CAP.<T> cast(this.instance) : null;
    }


    @Override
    public NBTBase serializeNBT() {
        return ITEM_NODE_CAP.getStorage().writeNBT(ITEM_NODE_CAP, instance, null);
    }

    @Override
    public void deserializeNBT(NBTBase nbt) {
        ITEM_NODE_CAP.getStorage().readNBT(ITEM_NODE_CAP, instance, null, nbt);
    }


}
