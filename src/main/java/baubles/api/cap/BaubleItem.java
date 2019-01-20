package baubles.api.cap;

import net.minecraft.item.ItemStack;

import baubles.api.BaubleType;
import baubles.api.IBauble;

public class BaubleItem implements IBauble {
    private BaubleType baubleType;

    public BaubleItem(BaubleType type) {
        baubleType = type;
    }

    @Override
    public BaubleType getBaubleType(ItemStack itemstack) {
        return baubleType;
    }
}
