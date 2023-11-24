package com.gildedrose;

enum ItemType {
    AGED_BRIE,
    BACKSTAGE_PASS,
    SULFURAS,
    NORMAL_ITEM
}

class GildedRose {
    Item[] items;
    public GildedRose(Item[] items) {
        this.items = items;
    }

    private ItemType getItemType(Item item) {
        switch (item.name) {
            case "Aged Brie":
                return ItemType.AGED_BRIE;
            case "Backstage passes to a TAFKAL80ETC concert":
                return ItemType.BACKSTAGE_PASS;
            case "Sulfuras, Hand of Ragnaros":
                return ItemType.SULFURAS;
            default:
                return ItemType.NORMAL_ITEM;
        }
    }

    public void updateQuality() {
        for (Item item : items) {
            ItemType itemType = getItemType(item);

            switch (itemType) {
                case AGED_BRIE:
                    updateAgedBrie(item);
                    break;
                case BACKSTAGE_PASS:
                    updateBackstagePass(item);
                    break;
                case SULFURAS:
                    break;
                case NORMAL_ITEM:
                    updateNormalItem(item);
                    break;
            }
        }
    }

    private void updateAgedBrie(Item item) {
        if (item.quality < 50) {
            item.quality++;
        }
        item.sellIn--;
        if (item.sellIn < 0 && item.quality < 50) {
            item.quality++;
        }
    }

    private void updateBackstagePass(Item item) {
        if (item.quality < 50) {
            item.quality++;
            if (item.sellIn < 11 && item.quality < 50) {
                item.quality++;
            }
            if (item.sellIn < 6 && item.quality < 50) {
                item.quality++;
            }
        }
        item.sellIn--;
        if (item.sellIn < 0) {
            item.quality = 0;
        }
    }

    private void updateNormalItem(Item item) {
        if (item.quality > 0) {
            item.quality--;
            if (item.sellIn < 0 && item.quality > 0) {
                item.quality--;
            }
        }
        item.sellIn--;
    }
    
}
