package skadic.nodes.capabilities;

import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import skadic.nodes.tileentities.TileEntityNodeNexus;

import java.util.ArrayList;

/**
 * Created by eti22 on 05.10.2016.
 */
public interface ICapabilityNodeItem {

    String CAPABILITY_NODE_ITEM_NAME = "nodeItemCapability";



    boolean setHasNode(boolean hasNode);

    boolean hasNode();

    void setExport(boolean export);

    boolean getExport();

    void setPriority(int priority);

    int getPriority();

    void setNexus(TileEntityNodeNexus te);

    /**
     * Sets "hasFilter" to the specified value
     *
     * @return Returns if the value has changed
     */
    boolean setHasFilter(boolean hasFilter);

    /**
     * @return Returns if this TileEntity has a filter
     */
    boolean getHasFilter();

    /**
     * Adds an item to the filter
     *
     * @param item The item that should be added to the filter
     */
    void addToItemFilter(Item item);

    /**
     * Removes an item from the filter
     *
     * @param item The item that should be removed from the filter
     */
    void removeFromItemFilter(Item item);

    /**
     * Returns an array of items, containing all currently filtered items
     *
     * @return Returns the array of filtered items
     */
    Item[] getFilteredItems();

    /**
     * Checks if the specified item is being filtered
     *
     * @param item
     * @return Returns true if this item is filtered
     */
    boolean isItemFiltered(Item item);

    TileEntityNodeNexus getNexus();

    ArrayList<TileEntity> searchTileEntities();


}
