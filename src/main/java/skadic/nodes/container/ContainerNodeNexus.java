package skadic.nodes.container;

import skadic.nodes.tileentities.TileEntityNodeNexus;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

/**
 * Created by eti22 on 08.04.2016.
 */
public class ContainerNodeNexus extends Container {

    private IInventory playerInv;
    private TileEntityNodeNexus te;

    public ContainerNodeNexus(IInventory playerInv, TileEntityNodeNexus te) {
        this.playerInv = playerInv;
        this.te = te;

    }

    public void initSlots(){

        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 9; x++) {
                this.addSlotToContainer(new Slot(playerInv, x + y * 9 + 9, x * 18 + 8, y * 18 + 84));
            }
        }

        for (int x = 0; x < 9; x++) {
            this.addSlotToContainer(new Slot(playerInv, x, 8 + x * 18, 142));
        }

    }

    public void removeSlot(int index){
        this.inventorySlots.remove(index);
    }

    public void addSlot(Slot slot){
        this.addSlotToContainer(slot);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {

        ItemStack previous = null;
        Slot slot =  this.inventorySlots.get(index);

        if (slot != null && slot.getHasStack()) {
            ItemStack current = slot.getStack();
            previous = current.copy();

            if (index < 9) {
                if (!this.mergeItemStack(current, 9, 35, true))
                    return null;
            } else {
                if (!this.mergeItemStack(current, 0, 9, false))
                    return null;
            }


            if (current.stackSize == 0)
                slot.putStack(null);
            else
                slot.onSlotChanged();

            if (current.stackSize == previous.stackSize)
                return null;
            slot.onPickupFromSlot(playerIn, current);
        }
        return previous;

    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return true;
    }
}
