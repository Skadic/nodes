package skadic.nodes.crafting;

import skadic.nodes.blocks.BlockRegister;
import skadic.nodes.items.ItemRegister;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by eti22 on 23.03.2016.
 */
public class CraftingRegister {


    public CraftingRegister() {
        initShapedCraftingRecipes();
        initShapelessCraftingRecipes();
        initSmeltingRecipes();
    }

    public void initShapedCraftingRecipes(){
        ItemStack
                iron,
                redstone,
                blockRedstone,
                enderpearl,
                node,
                bucket,
                lapis,
                chest,
                gold,
                diamond,
                blockIron

                        ;

        iron = new ItemStack(Items.IRON_INGOT);
        redstone = new ItemStack(Items.REDSTONE);
        blockRedstone = new ItemStack(Blocks.REDSTONE_BLOCK);
        enderpearl = new ItemStack(Items.ENDER_PEARL);
        node = new ItemStack(ItemRegister.itemNode);
        bucket = new ItemStack(Items.BUCKET);
        lapis = new ItemStack(Items.DYE,1,4);
        chest = new ItemStack(Blocks.CHEST);
        gold = new ItemStack(Items.GOLD_INGOT);
        diamond = new ItemStack(Items.DIAMOND);
        blockIron = new ItemStack(Blocks.IRON_BLOCK);


        GameRegistry.addShapedRecipe(new ItemStack(ItemRegister.itemNode),
                "IBI",
                "RER",
                "IBI",
                'I', iron,
                'B', blockRedstone,
                'R', redstone,
                'E', enderpearl
        );

        GameRegistry.addShapedRecipe(new ItemStack(ItemRegister.itemNodeItem),
                "RCR",
                "INI",
                "RCR",
                'R',redstone,
                'C',chest,
                'I',iron,
                'N',node
        );

        GameRegistry.addShapedRecipe(new ItemStack(ItemRegister.itemNodeFluid),
                "LBL",
                "INI",
                "LBL",
                'L',lapis,
                'B',bucket,
                'I',iron,
                'N',node
        );

        GameRegistry.addShapedRecipe(new ItemStack(ItemRegister.itemNodeEnergy),
                "GRG",
                "INI",
                "GRG",
                'R',blockRedstone,
                'G',gold,
                'I',iron,
                'N',node
        );

        GameRegistry.addShapedRecipe(new ItemStack(BlockRegister.blockNodeController),
                "DED",
                "RIR",
                "DED",
                'D',diamond,
                'E',enderpearl,
                'R',blockRedstone,
                'I',blockIron
                );

        GameRegistry.addShapedRecipe(new ItemStack(ItemRegister.itemNodeWrench),
                "RER",
                " I ",
                " I ",
                'R',redstone,
                'E',enderpearl,
                'I',iron
                );
    }

    public void initShapelessCraftingRecipes(){


    }

    public void initSmeltingRecipes(){

    }



}
