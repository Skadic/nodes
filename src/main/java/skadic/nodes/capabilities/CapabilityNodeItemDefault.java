package skadic.nodes.capabilities;


import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import skadic.nodes.tileentities.TileEntityNodeNexus;

import java.util.ArrayList;

public class CapabilityNodeItemDefault implements ICapabilityNodeItem {

    boolean hasNode = false;
    boolean export = true;
    TileEntityNodeNexus nexus;
    ArrayList<Item> itemFilter = new ArrayList<Item>();
    boolean filter;
    int priority = 0;

    public ArrayList<TileEntity> searchTileEntities(){
        if(nexus != null)
            return nexus.getTileEntities();
        return null;
    }

    @Override
    public void addToItemFilter(Item item) {
        if(!itemFilter.contains(item) && itemFilter.size() <= 8)
            itemFilter.add(item);
    }

    @Override
    public Item[] getFilteredItems() {
        Item[] items = new Item[itemFilter.size()];

        for (Item item : itemFilter) {
            int index = itemFilter.indexOf(item);
            items[index] = item;
        }

        return items;
    }


    @Override
    public boolean isItemFiltered(Item item) {
        return itemFilter.contains(item);
    }

    @Override
    public void removeFromItemFilter(Item item) {
        if(itemFilter.contains(item))
            itemFilter.remove(item);
    }

    @Override
    public boolean setHasFilter(boolean hasFilter) {
        if(filter != hasFilter){
            filter = hasFilter;
            return true;
        }
        return false;
    }

    @Override
    public boolean getHasFilter() {
        return filter;
    }

    @Override
    public void setNexus(TileEntityNodeNexus te) {
        if(nexus == null) nexus = te;
    }

    @Override
    public TileEntityNodeNexus getNexus() {
        return nexus;
    }


    @Override
    public void setExport(boolean export) {
        this.export = export;
    }

    @Override
    public boolean getExport() {
        return export;
    }


    @Override
    public boolean setHasNode(boolean hasNode) {
        if(this.hasNode != hasNode) {
            this.hasNode = hasNode;
            return true;
        }
        return false;
    }

    @Override
    public boolean hasNode() {
        return hasNode;
    }


    @Override
    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public int getPriority() {
        return priority;
    }
}
