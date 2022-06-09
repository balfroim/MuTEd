package be.unamur.game2048;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

import be.unamur.game2048.controllers.*;
import be.unamur.game2048.helpers.*;
import be.unamur.game2048.models.*;

public class Test2048 {
    @Test
    public void testTileEquals() {
        for (int i = 0; i < 10000; i++) {
            // Arrange
            Tile tile1 = new Tile(i);
            Tile tile2 = new Tile(i);

            // Act

            // Assert
            assertTrue(tile1.equals(tile2));
        }
        Tile tile1 = new Tile(2);
        Tile tile2 = new Tile(4);
        assertTrue(!tile1.equals(tile2));
        assertTrue(!tile1.equals(null));
        assertTrue(!tile1.equals(true));
    }    
    @Test
    public void testTileMerge() {
        // Arrange
        Tile tile1 = new Tile(2);
        Tile tile2 = new Tile(2);
        Tile tile3 = new Tile(2);
        // Act
        assertTrue(4 == tile1.mergeWith(tile2));
        // Assert
        assertTrue(tile1.isMerged());
        assertTrue(!tile3.isMerged());
        assertTrue(tile1.getValue() == 4);

        Tile tile4 = new Tile(2);
        Tile tile5 = new Tile(2);
        Tile tile6 = new Tile(4);
        assertTrue(tile4.canMergeWith(tile5));
        assertTrue(!tile4.canMergeWith(tile6));
        assertTrue(tile4.mergeWith(tile6) == -1);

        tile5.setMerged(true);
        assertTrue(!tile4.canMergeWith(tile5));
        assertTrue(!tile4.canMergeWith(null));
    }

    @Test
    public void testTileToString() {
        Tile tile = new Tile(2);
        assertTrue(tile.toString().equals("2"));
    }
}
