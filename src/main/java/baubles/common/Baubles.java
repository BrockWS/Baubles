package baubles.common;

import net.minecraft.block.Block;
import net.minecraft.item.Item;

import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLModLoadingContext;
import net.minecraftforge.registries.ObjectHolder;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import baubles.api.cap.BaubleItem;
import baubles.api.cap.BaublesCapabilities.CapabilityBaubles;
import baubles.api.cap.BaublesCapabilities.CapabilityItemBaubleStorage;
import baubles.api.cap.BaublesContainer;
import baubles.api.cap.IBaublesItemHandler;
import baubles.client.ClientProxy;
import baubles.common.event.CommandBaubles;
import baubles.common.items.ItemRing;
import baubles.common.network.PacketHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Baubles.MODID)
@Mod.EventBusSubscriber(modid = Baubles.MODID)
public class Baubles {

    public static final String MODID = "baubles";
    public static final String MODNAME = "Baubles";
    public static final String VERSION = "1.5.2";
    public static final int GUI = 0;
    public static final Logger log = LogManager.getLogger(MODID.toUpperCase());

    public static Baubles INSTANCE;
    public static CommonProxy PROXY;

    @ObjectHolder(Baubles.MODID + ":ring")
    public static final Item RING = null;

    public Baubles() {
        Baubles.INSTANCE = this;
        Baubles.PROXY = DistExecutor.runForDist(() -> ClientProxy::new, () -> CommonProxy::new);
        FMLModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLModLoadingContext.get().getModEventBus().addListener(this::registerCommands);
        FMLModLoadingContext.get().getModEventBus().addListener(this::registerBlocks);
        FMLModLoadingContext.get().getModEventBus().addListener(this::registerItems);

        Baubles.PROXY.registerEventHandlers();
    }

    public void setup(FMLCommonSetupEvent event) {
        CapabilityManager.INSTANCE.register(
                IBaublesItemHandler.class,
                new CapabilityBaubles<>(),
                BaublesContainer::new);

        CapabilityManager.INSTANCE.register(
                IBauble.class,
                new CapabilityItemBaubleStorage(),
                () -> new BaubleItem(BaubleType.TRINKET));

        Baubles.PROXY.init();
        PacketHandler.init();
    }

    public void registerCommands(FMLServerStartingEvent event) {
        CommandBaubles.register(event.getCommandDispatcher());
    }

    public void registerBlocks(RegistryEvent.Register<Block> event) {
        Baubles.log.info("Blocks---------------------------------------");
    }

    public void registerItems(RegistryEvent.Register<Item> event) {
        Baubles.log.info("Registering Demo Bauble-----------------------");
        GameRegistry.findRegistry(Item.class).register(new ItemRing());
    }

/*
	@SidedProxy(clientSide = "baubles.client.ClientProxy", serverSide = "baubles.common.CommonProxy")
	public static CommonProxy proxy;

	@Instance(value=Baubles.MODID)

	public File modDir;


	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		modDir = event.getModConfigurationDirectory();

		try {
			Config.initialize(event.getSuggestedConfigurationFile());
		} catch (Exception e) {
			Baubles.log.error("BAUBLES has a problem loading it's configuration");
		} finally {
			if (Config.config!=null) Config.save();
		}

		proxy.registerEventHandlers();
		PacketHandler.init();

		Config.save();
	}

	@EventHandler
	public void init(FMLInitializationEvent evt) {
		NetworkRegistry.INSTANCE.registerGuiHandler(instance, proxy);
		proxy.init();
	}

	@EventHandler
	public void serverLoad(FMLServerStartingEvent event)
	{
		event.registerServerCommand(new CommandBaubles());
	}*/
}
