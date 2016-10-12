package skadic.nodes.tileentities;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import skadic.nodes.capabilities.CapabilityNodeItemProvider;
import skadic.nodes.capabilities.ICapabilityNodeItem;

import java.util.ArrayList;


/**
 * Created by eti22 on 20.03.2016.
 */
public class TileEntityNodeNexus extends TileEntity implements ITickable {
    private int interval;
    private int radius;
    private long time;

    ArrayList<TileEntity> tileEntities = new ArrayList<TileEntity>();

    public TileEntityNodeNexus() {
        interval = 1;
        radius = 5;
        time = System.nanoTime() / 1000000; // in milliseconds
    }

    @Override
    public void update() {
        if (!this.getWorld().isRemote) {
            if ((System.nanoTime() / 1000000 - time) >= 1000 * interval) {
                time = System.nanoTime() / 1000000;




            }
        }
    }

    private void pulse(){
        searchTileEntities();

        for (TileEntity te : tileEntities) {
            ICapabilityNodeItem itemCap = te.getCapability(CapabilityNodeItemProvider.ITEM_NODE_CAP, null);
        }
    }

    public synchronized ArrayList<TileEntity> getTileEntities() {
        return searchTileEntities();
    }

    private ArrayList<TileEntity> searchTileEntities() {
        BlockPos pos = this.getPos();

        ArrayList<TileEntity> tempExistant = new ArrayList<TileEntity>();


        //Liest alle geeigneten tile Entities aus der Umgebung in tempExistant ein
        for (int x = pos.getX() - radius; x < pos.getX() + radius; x++)
            for (int y = pos.getY() - radius; y < pos.getY() + radius; y++)
                for (int z = pos.getZ() - radius; z < pos.getZ() + radius; z++) {
                    BlockPos cur = new BlockPos(x, y, z);

                    if (worldObj.getTileEntity(cur) != null) {
                        TileEntity te = worldObj.getTileEntity(cur);
                        if (te.hasCapability(CapabilityNodeItemProvider.ITEM_NODE_CAP, null)) {
                            ICapabilityNodeItem itemCap = te.getCapability(CapabilityNodeItemProvider.ITEM_NODE_CAP, null);
                            itemCap.setNexus(this);
                            if (itemCap.hasNode())
                                tempExistant.add(worldObj.getTileEntity(cur));
                        }
                    }
                }

        ArrayList<TileEntity> temp = new ArrayList<TileEntity>();
        for (TileEntity te : tileEntities) {
            if (!tempExistant.contains(te)) {
                temp.add(te);
            }
        }

        tileEntities.removeAll(temp);
        tempExistant.removeAll(tileEntities);
        tileEntities.addAll(tempExistant);

        return tileEntities;
    }

    public ArrayList<TileEntity> getImportingTileEntities() {
        searchTileEntities();
        ArrayList<TileEntity> importingTE = new ArrayList<TileEntity>();
        for (TileEntity te : tileEntities) {
            ICapabilityNodeItem itemCap = te.getCapability(CapabilityNodeItemProvider.ITEM_NODE_CAP, null);
            if (!itemCap.getExport()) importingTE.add(te);
        }
        return importingTE;
    }

    public ArrayList<TileEntity> getExportingTileEntities() {
        searchTileEntities();
        ArrayList<TileEntity> exportingTE = new ArrayList<TileEntity>();
        for (TileEntity te : tileEntities) {
            ICapabilityNodeItem itemCap = te.getCapability(CapabilityNodeItemProvider.ITEM_NODE_CAP, null);
            if (itemCap.getExport()) exportingTE.add(te);
        }
        return exportingTE;
    }

    public final ArrayList<TileEntity> getTileEntitiesImportingItem(Item item){
        return null;
    }


}




