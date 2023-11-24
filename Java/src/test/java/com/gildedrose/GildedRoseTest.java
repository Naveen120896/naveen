package com.gildedrose;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class GildedRoseTest {

    static Stream<Arguments> testData() {
        return Stream.of(
                arguments(5, 10, 9, "Normal Item", "Normal Item Quality decreases"),
                arguments(5, 0, 0, "Normal Item", "Normal Item Quality never negative"),
                arguments(5, 10, 11, "Aged Brie", "Aged Brie Quality increases"),
                arguments(5, 50, 50, "Aged Brie", "Aged Brie Quality never more than 50"),
                arguments(15, 20, 21, "Backstage passes to a TAFKAL80ETC concert", "Backstage Pass Quality increases by 1"),
                arguments(10, 20, 22, "Backstage passes to a TAFKAL80ETC concert", "Backstage Pass Quality increases by 2 when SellIn less than 11"),
                arguments(5, 20, 23, "Backstage passes to a TAFKAL80ETC concert", "Backstage Pass Quality increases by 3 when SellIn less than 6"),
                arguments(0, 20, 0, "Backstage passes to a TAFKAL80ETC concert", "Backstage Pass Quality becomes zero after concert"),
                arguments(5, 80, 80, "Sulfuras, Hand of Ragnaros", "Sulfuras never changes")
        );
    }

    @ParameterizedTest
    @MethodSource("testData")
    void updateQuality(int sellIn, int quality, int expectedQuality, String itemName, String description) {
        Item[] items = {new Item(itemName, sellIn, quality)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(expectedQuality, app.items[0].quality, description);
    }
}

