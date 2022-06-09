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
    public void testTileEqualsValid() {
        // Arrange
        Tile expected = new Tile(2);
        Tile current = new Tile(2);

        // Assert
        assertTrue(expected.equals(current));
        assertTrue(expected.getValue() == 2);
        assertTrue(current.getValue() == 2);
    }

    @Test
    public void testTileEqualsNumber() {
        // Arrange
        Tile tile = new Tile(2);

        // Assert
        assertTrue(!tile.equals(2));
        assertTrue(tile.getValue() == 2);
    }

    @Test
    public void testTileEqualsSame() {
        // Arrange
        Tile tile = new Tile(2);

        // Assert
        assertTrue(tile.equals(tile));
        assertTrue(tile.getValue() == 2);
    }

    @Test
    public void testTileEqualsNull() {
        // Arrange
        Tile tile = new Tile(2);

        // Assert
        assertTrue(!tile.equals(null));
        assertTrue(tile.getValue() == 2);
    }

    @Test
    public void testTileEqualsValidMaxValue() {
        // Arrange
        Tile expected = new Tile(Integer.MAX_VALUE);
        Tile current = new Tile(Integer.MAX_VALUE);

        // Assert
        assertTrue(expected.equals(current));
        assertTrue(expected.getValue() == Integer.MAX_VALUE);
        assertTrue(current.getValue() == Integer.MAX_VALUE);
    }

    @Test
    public void testTileSetMergedTrue() {
        Tile tile = new Tile(2);

        assertTrue(!tile.isMerged());
        assertTrue(tile.getValue() == 2);
        
        tile.setMerged(true);

        assertTrue(tile.getValue() == 2);
        assertTrue(tile.isMerged());
    }

    @Test
    public void testTileSetMergedFalse() {
        Tile tile = new Tile(2);

        assertTrue(!tile.isMerged());
        assertTrue(tile.getValue() == 2);

        tile.setMerged(true);

        assertTrue(tile.isMerged());
        assertTrue(tile.getValue() == 2);
        
        tile.setMerged(false);

        assertTrue(tile.getValue() == 2);
        assertTrue(!tile.isMerged());
    }

    @Test
    public void testTileCanMergeWithValid() {
        Tile tile = new Tile(2);
        Tile other = new Tile(2);

        assertTrue(!tile.isMerged());
        assertTrue(!other.isMerged());

        assertTrue(tile.canMergeWith(other));
        assertTrue(other.canMergeWith(tile));
        assertTrue(tile.getValue() == 2);
        assertTrue(other.getValue() == 2);
        assertTrue(!tile.isMerged());
        assertTrue(!other.isMerged());
    }

    @Test
    public void testTileCanMergeWithOneMergedSameValue() {
        Tile tile = new Tile(2);
        Tile other = new Tile(2);

        tile.setMerged(true);

        assertTrue(!tile.canMergeWith(other));
        assertTrue(!other.canMergeWith(tile));

        assertTrue(tile.getValue() == 2);
        assertTrue(other.getValue() == 2);

        assertTrue(tile.isMerged());
        assertTrue(!other.isMerged());
    }

    @Test
    public void testTileCanMergeWithOneMergedDifferentValue() {
        Tile tile = new Tile(2);
        Tile other = new Tile(4);

        tile.setMerged(true);

        assertTrue(!tile.canMergeWith(other));
        assertTrue(!other.canMergeWith(tile));

        assertTrue(tile.getValue() == 2);
        assertTrue(other.getValue() == 4);

        assertTrue(tile.isMerged());
        assertTrue(!other.isMerged());
    }

    @Test
    public void testTileCanMergeWithNotMergedDifferentValue() {
        Tile tile = new Tile(2);
        Tile other = new Tile(4);

        assertTrue(!tile.canMergeWith(other));
        assertTrue(!other.canMergeWith(tile));

        assertTrue(tile.getValue() == 2);
        assertTrue(other.getValue() == 4);

        assertTrue(!tile.isMerged());
        assertTrue(!other.isMerged());
    }

    @Test
    public void testTileCanMergeWithTwoMergedDifferentValue() {
        Tile tile = new Tile(2);
        Tile other = new Tile(4);

        tile.setMerged(true);
        other.setMerged(true);

        assertTrue(!tile.canMergeWith(other));
        assertTrue(!other.canMergeWith(tile));

        assertTrue(tile.getValue() == 2);
        assertTrue(other.getValue() == 4);

        assertTrue(tile.isMerged());
        assertTrue(other.isMerged());
    }

    @Test
    public void testTileMergeWithSameNumber() {
        Tile tile = new Tile(2);
        Tile other = new Tile(2);

        assertTrue(tile.mergeWith(other) == 4);
        assertTrue(tile.getValue() == 4);
        assertTrue(!tile.canMergeWith(other));
        assertTrue(!other.canMergeWith(tile));
        assertTrue(tile.isMerged());
        assertTrue(!other.isMerged());
    }

    @Test
    public void testTileMergeWithDifferentNumber() {
        Tile tile = new Tile(2);
        Tile other = new Tile(4);

        assertTrue(tile.mergeWith(other) == -1);
        assertTrue(tile.getValue() == 2);
        assertTrue(other.getValue() == 4);
        assertTrue(!tile.canMergeWith(other));
        assertTrue(!other.canMergeWith(tile));
        assertTrue(!tile.isMerged());
        assertTrue(!other.isMerged());
    }

    @Test
    public void testTileMergeWithNull() {
        Tile tile = new Tile(2);

        assertTrue(tile.mergeWith(null) == -1);

        assertTrue(!tile.isMerged());
    }

    @Test
    public void testTileNotPower2() {
        Tile tile = new Tile(7);

        assertTrue(tile.getValue() == 8);
        assertTrue(!tile.isMerged());
    }

    @Test
    public void testTilePower2() {
        Tile tile = new Tile(2);

        assertTrue(tile.getValue() == 2);
        assertTrue(!tile.isMerged());
    }

    @Test
    public void testTileToString() {
        Tile tile = new Tile(2);

        assertTrue("2".equals(tile.toString()));
        
        assertTrue(tile.getValue() == 2);
        assertTrue(!tile.isMerged());
    }
}
