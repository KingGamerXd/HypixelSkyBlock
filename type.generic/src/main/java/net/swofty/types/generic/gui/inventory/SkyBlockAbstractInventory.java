package net.swofty.types.generic.gui.inventory;

import net.kyori.adventure.text.Component;
import net.minestom.server.entity.Player;
import net.minestom.server.event.inventory.InventoryCloseEvent;
import net.minestom.server.event.inventory.InventoryPreClickEvent;
import net.minestom.server.inventory.Inventory;
import net.minestom.server.inventory.InventoryType;
import net.minestom.server.item.ItemStack;
import net.minestom.server.network.packet.server.play.CloseWindowPacket;
import net.minestom.server.network.packet.server.play.OpenWindowPacket;
import net.swofty.commons.StringUtility;
import net.swofty.types.generic.gui.inventory.actions.RefreshAction;
import net.swofty.types.generic.gui.inventory.actions.RefreshSlotAction;
import net.swofty.types.generic.user.SkyBlockPlayer;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public abstract class SkyBlockAbstractInventory extends Inventory {
    public static final Map<UUID, SkyBlockAbstractInventory> GUI_MAP = new ConcurrentHashMap<>();

    private final Set<String> states = new HashSet<>();
    private Map<Integer, List<GUIItem>> items = new ConcurrentHashMap<>();
    private final Map<String, UpdateLoop> activeLoops = new ConcurrentHashMap<>();
    private boolean hasFinishedLoading = false;

    protected SkyBlockPlayer owner;

    public SkyBlockAbstractInventory(@NotNull InventoryType inventoryType) {
        super(inventoryType, Component.empty());
    }

    @Override
    public boolean addViewer(@NotNull Player player) {
        if (!this.viewers.add(player)) {
            return false;
        }

        // Handle previous inventory
        SkyBlockAbstractInventory previousInventory = GUI_MAP.get(player.getUuid());
        if (previousInventory != null) {
            if (!previousInventory.hasFinishedLoading) {
                player.sendMessage(Component.text("§cPlease wait before doing this!"));
                return false;
            }

            previousInventory.onClose(new InventoryCloseEvent(previousInventory, player, false), CloseReason.SERVER_EXITED);
            previousInventory.stopAllLoops();
            GUI_MAP.remove(player.getUuid());

            player.sendPacket(new CloseWindowPacket(previousInventory.getWindowId()));
        }

        this.owner = (SkyBlockPlayer) player;
        GUI_MAP.put(player.getUuid(), this);

        // Send packets and update
        player.sendPacket(new OpenWindowPacket(
                this.getWindowId(),
                this.getInventoryType().getWindowType(),
                this.getTitle()
        ));
        this.update(player);

        Thread.startVirtualThread(() -> {
            // Handle opening
            handleOpen((SkyBlockPlayer) player);
            hasFinishedLoading = true;
        });

        return true;
    }

    @Override
    public boolean removeViewer(@NotNull Player player) {
        CloseReason reason = player.didCloseInventory() ? CloseReason.PLAYER_EXITED : CloseReason.SERVER_EXITED;
        onClose(new InventoryCloseEvent(this, player, false), reason);
        stopAllLoops();
        GUI_MAP.remove(player.getUuid());

        return super.removeViewer(player);
    }

    protected void stopAllLoops() {
        activeLoops.values().forEach(UpdateLoop::stop);
        activeLoops.clear();
    }

    public abstract void handleOpen(SkyBlockPlayer player);

    public abstract void onClose(InventoryCloseEvent event, CloseReason reason);

    public abstract void onBottomClick(InventoryPreClickEvent event);

    public abstract void onSuddenQuit(SkyBlockPlayer player);

    public abstract boolean allowHotkeying();

    public void open(SkyBlockPlayer player) {
        player.openInventory(this);
    }

    // Fill methods
    protected void fill(ItemStack item) {
        fill(item, 0, this.getInventoryType().getSize() - 1);
    }

    protected void fill(ItemStack item, int cornerSlot1, int cornerSlot2) {
        fill(item, cornerSlot1, cornerSlot2, false);
    }

    protected void fill(ItemStack item, int cornerSlot1, int cornerSlot2, boolean overwrite) {
        if (cornerSlot1 < 0 || cornerSlot1 >= getInventoryType().getSize()) {
            throw new IllegalArgumentException("cornerSlot1 is out of bounds");
        }
        if (cornerSlot2 < 0 || cornerSlot2 >= getInventoryType().getSize()) {
            throw new IllegalArgumentException("cornerSlot2 is out of bounds");
        }

        int startRow = Math.min(cornerSlot1 / 9, cornerSlot2 / 9);
        int endRow = Math.max(cornerSlot1 / 9, cornerSlot2 / 9);
        int startCol = Math.min(cornerSlot1 % 9, cornerSlot2 % 9);
        int endCol = Math.max(cornerSlot1 % 9, cornerSlot2 % 9);

        for (int row = startRow; row <= endRow; row++) {
            for (int col = startCol; col <= endCol; col++) {
                int slot = row * 9 + col;

                boolean slotOccupied = !getItemsInSlot(slot).isEmpty();
                if (slotOccupied && !overwrite) {
                    continue;
                }

                attachItem(GUIItem.builder(slot)
                        .item(item)
                        .build());
            }
        }
        doAction(new RefreshAction());
    }

    protected void fillWithState(ItemStack item, String state, int cornerSlot1, int cornerSlot2) {
        if (cornerSlot1 < 0 || cornerSlot1 >= getInventoryType().getSize()) {
            throw new IllegalArgumentException("cornerSlot1 is out of bounds");
        }
        if (cornerSlot2 < 0 || cornerSlot2 >= getInventoryType().getSize()) {
            throw new IllegalArgumentException("cornerSlot2 is out of bounds");
        }

        int startRow = Math.min(cornerSlot1 / 9, cornerSlot2 / 9);
        int endRow = Math.max(cornerSlot1 / 9, cornerSlot2 / 9);
        int startCol = Math.min(cornerSlot1 % 9, cornerSlot2 % 9);
        int endCol = Math.max(cornerSlot1 % 9, cornerSlot2 % 9);

        for (int row = startRow; row <= endRow; row++) {
            for (int col = startCol; col <= endCol; col++) {
                int slot = row * 9 + col;
                attachItem(GUIItem.builder(slot)
                        .item(item)
                        .requireState(state)
                        .build());
            }
        }
        doAction(new RefreshAction());
    }

    protected void fillWithStates(ItemStack item, String[] states, int cornerSlot1, int cornerSlot2) {
        if (cornerSlot1 < 0 || cornerSlot1 >= getInventoryType().getSize()) {
            throw new IllegalArgumentException("cornerSlot1 is out of bounds");
        }
        if (cornerSlot2 < 0 || cornerSlot2 >= getInventoryType().getSize()) {
            throw new IllegalArgumentException("cornerSlot2 is out of bounds");
        }

        int startRow = Math.min(cornerSlot1 / 9, cornerSlot2 / 9);
        int endRow = Math.max(cornerSlot1 / 9, cornerSlot2 / 9);
        int startCol = Math.min(cornerSlot1 % 9, cornerSlot2 % 9);
        int endCol = Math.max(cornerSlot1 % 9, cornerSlot2 % 9);

        for (int row = startRow; row <= endRow; row++) {
            for (int col = startCol; col <= endCol; col++) {
                int slot = row * 9 + col;
                attachItem(GUIItem.builder(slot)
                        .item(item)
                        .requireStates(states)
                        .build());
            }
        }
        doAction(new RefreshAction());
    }

    protected void fillWithAnyStates(ItemStack item, String[] states, int cornerSlot1, int cornerSlot2) {
        if (cornerSlot1 < 0 || cornerSlot1 >= getInventoryType().getSize()) {
            throw new IllegalArgumentException("cornerSlot1 is out of bounds");
        }
        if (cornerSlot2 < 0 || cornerSlot2 >= getInventoryType().getSize()) {
            throw new IllegalArgumentException("cornerSlot2 is out of bounds");
        }

        int startRow = Math.min(cornerSlot1 / 9, cornerSlot2 / 9);
        int endRow = Math.max(cornerSlot1 / 9, cornerSlot2 / 9);
        int startCol = Math.min(cornerSlot1 % 9, cornerSlot2 % 9);
        int endCol = Math.max(cornerSlot1 % 9, cornerSlot2 % 9);

        for (int row = startRow; row <= endRow; row++) {
            for (int col = startCol; col <= endCol; col++) {
                int slot = row * 9 + col;
                attachItem(GUIItem.builder(slot)
                        .item(item)
                        .requireAnyState(states)
                        .build());
            }
        }
        doAction(new RefreshAction());
    }

    protected void border(ItemStack item) {
        border(item, 0, getInventoryType().getSize() - 1);
    }

    protected void border(ItemStack item, int cornerSlot1, int cornerSlot2) {
        border(item, cornerSlot1, cornerSlot2, true);
    }

    protected void border(ItemStack item, int cornerSlot1, int cornerSlot2, boolean overwrite) {
        if (cornerSlot1 < 0 || cornerSlot1 >= getSize()) {
            throw new IllegalArgumentException("Corner 1 is out of bounds");
        }
        if (cornerSlot2 < 0 || cornerSlot2 >= getSize()) {
            throw new IllegalArgumentException("Corner 2 is out of bounds");
        }

        int minSlot = Math.min(cornerSlot1, cornerSlot2);
        int maxSlot = Math.max(cornerSlot1, cornerSlot2);

        int minRow = minSlot / 9;
        int maxRow = maxSlot / 9;
        int minCol = minSlot % 9;
        int maxCol = maxSlot % 9;

        for (int row = minRow; row <= maxRow; row++) {
            for (int col = minCol; col <= maxCol; col++) {
                boolean isTopOrBottomRow = row == minRow || row == maxRow;
                boolean isLeftOrRightCol = col == minCol || col == maxCol;

                if (isTopOrBottomRow || isLeftOrRightCol) {
                    int slot = row * 9 + col;

                    boolean occupied = !getItemsInSlot(slot).isEmpty();
                    if (occupied && !overwrite) {
                        continue;
                    }
                    attachItem(GUIItem.builder(slot)
                            .item(item)
                            .build());
                }
            }
        }
        doAction(new RefreshAction());
    }

    protected void borderWithState(ItemStack item, String state, int cornerSlot1, int cornerSlot2) {
        if (cornerSlot1 < 0 || cornerSlot1 >= getSize()) {
            throw new IllegalArgumentException("Corner 1 is out of bounds");
        }
        if (cornerSlot2 < 0 || cornerSlot2 >= getSize()) {
            throw new IllegalArgumentException("Corner 2 is out of bounds");
        }

        int minSlot = Math.min(cornerSlot1, cornerSlot2);
        int maxSlot = Math.max(cornerSlot1, cornerSlot2);

        int minRow = minSlot / 9;
        int maxRow = maxSlot / 9;
        int minCol = minSlot % 9;
        int maxCol = maxSlot % 9;

        for (int row = minRow; row <= maxRow; row++) {
            for (int col = minCol; col <= maxCol; col++) {
                boolean isTopOrBottomRow = row == minRow || row == maxRow;
                boolean isLeftOrRightCol = col == minCol || col == maxCol;

                if (isTopOrBottomRow || isLeftOrRightCol) {
                    int slot = row * 9 + col;
                    attachItem(GUIItem.builder(slot)
                            .item(item)
                            .requireState(state)
                            .build());
                }
            }
        }

        doAction(new RefreshAction());
    }

    protected void borderWithStates(ItemStack item, String[] states, int cornerSlot1, int cornerSlot2) {
        if (cornerSlot1 < 0 || cornerSlot1 >= getSize()) {
            throw new IllegalArgumentException("Corner 1 is out of bounds");
        }
        if (cornerSlot2 < 0 || cornerSlot2 >= getSize()) {
            throw new IllegalArgumentException("Corner 2 is out of bounds");
        }

        int minSlot = Math.min(cornerSlot1, cornerSlot2);
        int maxSlot = Math.max(cornerSlot1, cornerSlot2);

        int minRow = minSlot / 9;
        int maxRow = maxSlot / 9;
        int minCol = minSlot % 9;
        int maxCol = maxSlot % 9;

        for (int row = minRow; row <= maxRow; row++) {
            for (int col = minCol; col <= maxCol; col++) {
                boolean isTopOrBottomRow = row == minRow || row == maxRow;
                boolean isLeftOrRightCol = col == minCol || col == maxCol;

                if (isTopOrBottomRow || isLeftOrRightCol) {
                    int slot = row * 9 + col;
                    attachItem(GUIItem.builder(slot)
                            .item(item)
                            .requireStates(states)
                            .build());
                }
            }
        }

        doAction(new RefreshAction());
    }

    protected void borderWithAnyStates(ItemStack item, String[] states, int cornerSlot1, int cornerSlot2) {
        if (cornerSlot1 < 0 || cornerSlot1 >= getSize()) {
            throw new IllegalArgumentException("Corner 1 is out of bounds");
        }
        if (cornerSlot2 < 0 || cornerSlot2 >= getSize()) {
            throw new IllegalArgumentException("Corner 2 is out of bounds");
        }

        int minSlot = Math.min(cornerSlot1, cornerSlot2);
        int maxSlot = Math.max(cornerSlot1, cornerSlot2);

        int minRow = minSlot / 9;
        int maxRow = maxSlot / 9;
        int minCol = minSlot % 9;
        int maxCol = maxSlot % 9;

        for (int row = minRow; row <= maxRow; row++) {
            for (int col = minCol; col <= maxCol; col++) {
                boolean isTopOrBottomRow = row == minRow || row == maxRow;
                boolean isLeftOrRightCol = col == minCol || col == maxCol;

                if (isTopOrBottomRow || isLeftOrRightCol) {
                    int slot = row * 9 + col;
                    attachItem(GUIItem.builder(slot)
                            .item(item)
                            .requireAnyState(states)
                            .build());
                }
            }
        }

        doAction(new RefreshAction());
    }


    // State Management
    public boolean hasState(String state) {
        return states.contains(state);
    }

    public Set<String> getStates() {
        return new HashSet<>(states);
    }

    public void addStateInternal(String state) {
        states.add(state);
        doAction(new RefreshAction());
    }

    public void removeStateInternal(String state) {
        states.remove(state);
        doAction(new RefreshAction());
    }

    protected void removeAllItems(int slot) {
        Map<Integer, List<GUIItem>> newItems = new HashMap<>();

        for (Map.Entry<Integer, List<GUIItem>> entry : items.entrySet()) {
            if (entry.getKey() != slot) {
                newItems.put(entry.getKey(), entry.getValue());
            }
        }

        items = newItems;
        doAction(new RefreshSlotAction(slot));
    }

    // Action System
    protected void doAction(GUIAction action) {
        Thread.startVirtualThread(() -> {
            action.execute(this);
        });
    }

    // Item Management
    public void attachItem(GUIItem item) {
        item.setAttachedTimestamp(System.nanoTime());
        items.computeIfAbsent(item.getSlot(), k -> Collections.synchronizedList(new ArrayList<>())).add(item);
        doAction(new RefreshSlotAction(item.getSlot()));
    }

    protected void refreshSlot(int slot) {
        List<GUIItem> slotItems = items.getOrDefault(slot, List.of());
        ItemStack toDisplay = ItemStack.AIR;

        // Find the first visible item for this slot
        for (GUIItem item : slotItems) {
            if (item.isVisible(states)) {
                toDisplay = item.getItem();
                break;
            }
        }

        setItemStack(slot, toDisplay);
    }


    public List<GUIItem> getItemsInSlot(int slot) {
        return Collections.synchronizedList(items.getOrDefault(slot, List.of()));
    }

    // Loop Management
    protected void startLoop(String id, int tickInterval, Runnable task) {
        UpdateLoop loop = new UpdateLoop(id, tickInterval, task);
        activeLoops.put(id, loop);
        loop.start();
    }

    protected void stopLoop(String id) {
        UpdateLoop loop = activeLoops.remove(id);
        if (loop != null) {
            loop.stop();
        }
    }

    public void replaceItemWithId(String id, ItemStack newItem) {
        for (List<GUIItem> items : this.items.values()) {
            for (GUIItem item : items) {
                if (item.getId() != null && item.getId().equals(id)) {
                    item.setItem(newItem);
                }
            }
        }
        doAction(new RefreshAction());
    }

    public enum CloseReason {
        SIGN_OPENED,
        PLAYER_EXITED,
        SERVER_EXITED
    }

    public @NotNull String getTitleAsString() {
        return StringUtility.getTextFromComponent(getTitle());
    }
}