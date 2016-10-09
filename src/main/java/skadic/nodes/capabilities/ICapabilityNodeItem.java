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

    void setNexus(TileEntityNodeNexus te);

    void addToItemFilter(Item item);

    void removeFromItemFilter(Item item);

    Item[] getFilteredItems();

    boolean isItemFiltered(Item item);

    TileEntityNodeNexus getNexus();

    ArrayList<TileEntity> searchTileEntities();


}
