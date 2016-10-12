package skadic.nodes.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import org.lwjgl.opengl.GL11;
import skadic.nodes.Nodes;
import skadic.nodes.capabilities.CapabilityNodeItemProvider;
import skadic.nodes.capabilities.ICapabilityNodeItem;
import skadic.nodes.gui.components.TexturedButton;
import skadic.nodes.networking.SNodeItemMessage;
import skadic.nodes.tileentities.TileEntityNodeNexus;

import java.io.IOException;
import java.util.ArrayList;


public class GuiNodeNexus extends GuiScreen{

    private ArrayList<TileEntity> tileEntities;
    private TileEntity currentTileEntity;
    private TileEntityNodeNexus te;
    private int xSize, ySizeMenu, ySizeOptions;
    private int page;


    public GuiNodeNexus(TileEntityNodeNexus te) {
        super();

        this.xSize = 176;
        this.ySizeMenu = 166;
        this.ySizeOptions = 118;
        this.page = 1;

        this.te = te;
        this.tileEntities = te.getTileEntities();
    }

    @Override
    public synchronized void drawScreen( int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();

        int x = (this.width - xSize) / 2;
        int xMid = this.width / 2;
        int yMid = this.height / 2;

        int y;

        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        if(currentTileEntity == null) {
            this.mc.renderEngine.bindTexture(new ResourceLocation(Nodes.MODID, "textures/gui/node_controller.png"));
            y = (this.height - ySizeMenu) / 2;
            drawTexturedModalRect(x, y, 0, 0, xSize, ySizeMenu);
        }else{
            this.mc.renderEngine.bindTexture(new ResourceLocation(Nodes.MODID, "textures/gui/node_controller_options.png"));
            y = (this.height - ySizeOptions) / 2;
            drawTexturedModalRect(x, y, 0, 0, xSize, ySizeOptions);
        }

        if (currentTileEntity == null) {
            drawCenteredString(fontRendererObj, "Node Controller", xMid, y + 10, 0x555555);
            drawCenteredString(fontRendererObj, String.format("%d / %d", page, (int) Math.ceil(tileEntities.size() / 6d)), xMid, (this.height + ySizeMenu) / 2 - 18, 0x555555);
        }else{
            drawCenteredString(fontRendererObj, currentTileEntity.getBlockType().getLocalizedName(), xMid, y + 10, 0x555555);
            this.itemRender.renderItemAndEffectIntoGUI(new ItemStack(Item.getItemFromBlock(currentTileEntity.getBlockType())), x + 7, y + 7);
            drawCenteredString(fontRendererObj, currentTileEntity.getCapability(CapabilityNodeItemProvider.ITEM_NODE_CAP, null).getPriority() + "", (this.width - xSize) / 2 + 140, (this.height - ySizeOptions) / 2 + 40, 0x555555);
        }

        super.drawScreen(mouseX, mouseY, partialTicks);
    }


    @Override
    public void initGui() {
        updateGui();
    }

    @Override
    protected synchronized void actionPerformed(GuiButton button) throws IOException {
        if(button.isMouseOver()) {
            if (currentTileEntity == null) {
                if(buttonList.indexOf(button) < buttonList.size() - 2) {
                    currentTileEntity = tileEntities.get(buttonList.indexOf(button) * page);
                }else if(buttonList.indexOf(button) == buttonList.size() - 2) {
                    page--;
                }else if(buttonList.indexOf(button) == buttonList.size() - 1) {
                    page++;
                }
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
                        Nodes.SNW.sendToServer(new SNodeItemMessage(currentTileEntity.getPos(), itemCap.hasNode(), itemCap.getExport(), itemCap.getPriority()));
                        button.displayString = itemCap.getExport() ? "Export" : "Import";
                        break;
                    }
                    case 2:{
                        ICapabilityNodeItem itemCap = currentTileEntity.getCapability(CapabilityNodeItemProvider.ITEM_NODE_CAP, null);
                        itemCap.setPriority(itemCap.getPriority() - 1);
                        Nodes.SNW.sendToServer(new SNodeItemMessage(currentTileEntity.getPos(), itemCap.hasNode(), itemCap.getExport(), itemCap.getPriority()));
                        break;
                    }
                    case 3:{
                        ICapabilityNodeItem itemCap = currentTileEntity.getCapability(CapabilityNodeItemProvider.ITEM_NODE_CAP, null);
                        itemCap.setPriority(itemCap.getPriority() + 1);
                        Nodes.SNW.sendToServer(new SNodeItemMessage(currentTileEntity.getPos(), itemCap.hasNode(), itemCap.getExport(), itemCap.getPriority()));
                        break;
                    }

                }
            }
        }
    }

    private synchronized void updateGui(){
        buttonList.clear();
        if(currentTileEntity == null) {
            tileEntities = te.getTileEntities();

            for (int i = 0; i < Math.min(tileEntities.size() - (page - 1) * 6, 6); i++) {

                TileEntity tileEntity = tileEntities.get(i + (page - 1) * 6);
                String name = tileEntity.getBlockType().getLocalizedName();
                BlockPos pos = tileEntity.getPos();

                buttonList.add(new TexturedButton(i, (this.width - xSize) / 2 + 6, (this.height - ySizeMenu) / 2 + i * 20 + 22 , 163, 20, name + " X:" + pos.getX() + ", Y:" + pos.getY() + ", Z:" + pos.getZ(), new ResourceLocation(Nodes.MODID, "textures/gui/node_controller.png"), 0, 167));
            }


            buttonList.add(new TexturedButton(buttonList.size(), (this.width - xSize) / 2 + 6, (this.height + ySizeMenu) / 2 - 22, 51, 16, "<", new ResourceLocation(Nodes.MODID, "textures/gui/node_controller.png"), 176, 0));
            if(page == 1)buttonList.get(buttonList.size() - 1).visible = false;

            buttonList.add(new TexturedButton(buttonList.size(), (this.width + xSize) / 2 - 56, (this.height + ySizeMenu) / 2 - 22,51, 16, ">", new ResourceLocation(Nodes.MODID, "textures/gui/node_controller.png"), 176, 0));
            if(tileEntities.size() <= page * 6)buttonList.get(buttonList.size() - 1).visible = false;


        }else{
            ICapabilityNodeItem itemCap = currentTileEntity.getCapability(CapabilityNodeItemProvider.ITEM_NODE_CAP, null);

            buttonList.add(new GuiButton(0, (this.width - xSize) / 2 + 115, (this.height - ySizeOptions) / 2 + 80, 53, 20, "Back"));
            buttonList.add(new GuiButton(1, (this.width - xSize) / 2 + 115, (this.height - ySizeOptions) / 2 + 55, 53, 20, itemCap.getExport() ? "Export" : "Import"));

            buttonList.add(new TexturedButton(2, (this.width - xSize) / 2 + 115, (this.height - ySizeOptions) / 2 + 40, 10, 10, "<", new ResourceLocation(Nodes.MODID, "textures/gui/node_controller_options.png"), 176, 0));
            buttonList.add(new TexturedButton(3, (this.width - xSize) / 2 + 157, (this.height - ySizeOptions) / 2 + 40, 10, 10, ">", new ResourceLocation(Nodes.MODID, "textures/gui/node_controller_options.png"), 176, 0));
        }
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

}
