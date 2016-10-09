package skadic.nodes.gui;

import skadic.nodes.capabilities.CapabilityNodeItemProvider;
import skadic.nodes.capabilities.ICapabilityNodeItem;
import skadic.nodes.container.ContainerNodeNexus;
import skadic.nodes.Nodes;
import skadic.nodes.networking.NodeItemMessage;
import skadic.nodes.tileentities.TileEntityNodeNexus;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by eti22 on 06.04.2016.
 */
public class GuiNodeNexus extends GuiContainer{

    ArrayList<TileEntity> tileEntities;
    TileEntity currentTileEntity;
    TileEntityNodeNexus te;
    ContainerNodeNexus container;
    boolean filter = false;



    public GuiNodeNexus(TileEntityNodeNexus te, IInventory playerInv) {
        super(new ContainerNodeNexus(playerInv, te));

        this.xSize = 175;
        this.ySize = 166;

        this.tileEntities = te.getTileEntities();
        this.te = te;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        drawDefaultBackground();

        int x = (this.width - xSize) / 2;
        int y = (this.height - ySize) / 2;

        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        if(currentTileEntity == null) {
            this.mc.renderEngine.bindTexture(new ResourceLocation(Nodes.MODID, "textures/gui/node_controller.png"));
        }else if(!filter){
            this.mc.renderEngine.bindTexture(new ResourceLocation(Nodes.MODID, "textures/gui/node_controller_options.png"));
        }else{
            this.mc.renderEngine.bindTexture(new ResourceLocation(Nodes.MODID, "textures/gui/node_controller_filter.png"));
        }

        drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {

        int x = xSize / 2;
        int y = ySize / 2;
        if (currentTileEntity == null) {
            drawCenteredString(fontRendererObj, "Node Controller", x, 10, 0x555555);
        } else if (!filter){
            drawCenteredString(fontRendererObj, currentTileEntity.getBlockType().getLocalizedName(), x , 10, 0x555555);
            this.itemRender.renderItemAndEffectIntoGUI(new ItemStack(Item.getItemFromBlock(currentTileEntity.getBlockType())), 7, 7);
        }else{
            drawTexturedModalRect(x - 32, y - ySize / 2 + 20, xSize , ySize , 64, 16);
        }

        super.drawGuiContainerForegroundLayer(mouseX, mouseY);
    }

    @Override
    public void initGui() {
        super.initGui();
        updateGui();
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        if(button.isMouseOver()) {
            if (currentTileEntity == null) {
                currentTileEntity = tileEntities.get(buttonList.indexOf(button));
                updateGui();
            } else {

                switch (buttonList.indexOf(button)) {
                    case 0: {
                        currentTileEntity = null;
                        updateGui();
                        break;
                    }

                    case 1: {
                        ICapabilityNodeItem itemCap = currentTileEntity.getCapability(CapabilityNodeItemProvider.ITEM_NODE_CAP, null);
                        itemCap.setExport(!itemCap.getExport());
                        Nodes.SNW.sendToServer(new NodeItemMessage(currentTileEntity.getPos(), itemCap.hasNode(), itemCap.getExport()));
                        updateGui();
                    }

                    case 2: {
                        filter = true;
                        updateGui();
                    }
                }
            }
        }
    }

    private void updateGui(){
        buttonList.clear();
        if(currentTileEntity == null) {
            this.inventorySlots.inventorySlots.clear();
            for (TileEntity tileEntity : tileEntities) {
                int index = tileEntities.indexOf(tileEntity);
                String name = tileEntity.getBlockType().getLocalizedName();
                BlockPos pos = tileEntity.getPos();

                buttonList.add(new GuiButton(index, (this.width - xSize) / 2 + 6, (this.height - ySize) / 2 + 23 + index * 20, 153, 20, name + " X:" + pos.getX() + ", Y:" + pos.getY() + ", Z:" + pos.getZ()));
            }
        }else if(!filter){
            this.inventorySlots.inventorySlots.clear();

            ICapabilityNodeItem itemCap = currentTileEntity.getCapability(CapabilityNodeItemProvider.ITEM_NODE_CAP, null);

            buttonList.add(new GuiButton(0, guiLeft + 115, guiTop + 60, 53, 20, "Back"));
            buttonList.add(new GuiButton(1, guiLeft + 115, guiTop + 35, 53, 20, itemCap.getExport() ? "Export" : "Import"));
            buttonList.add(new GuiButton(2, guiLeft + 115, guiTop + 10, 53, 20, "Filter"));

            ((ContainerNodeNexus) this.inventorySlots).initSlots();
        }else{


        }
    }



    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }


}
