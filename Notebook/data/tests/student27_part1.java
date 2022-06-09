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
        // Arrange
        Tile tile = new Tile(3);
        Tile tile2 = new Tile(3);
        Grid grid = new Grid(new Tile[1][1]);
        // Act

        
        // Assert
        assertTrue(tile.equals(tile2));
        assertFalse(tile.equals(grid));
        assertFalse(tile.equals(null));
        
    }

    @Test
    public void testCanMergeWith(){
        //Arrange
        Tile tile = new Tile(3);
        Tile tile2 = new Tile(3);
        Tile tile3 = new Tile(1);

        // Act
        tile.setMerged(false);
        tile2.setMerged(false);

        // Assert
        assertTrue(tile.canMergeWith(tile2));
        assertFalse(tile.canMergeWith(tile3));
        tile2.setMerged(true);
        assertFalse(tile.canMergeWith(tile2));
        assertFalse(tile.canMergeWith(null));
    }

    @Test
    public void testGetValue(){
        //Arrange
        Tile tile = new Tile(1);
        Tile tile2 = new Tile(2);

        // Assert
        assertTrue(tile.getValue() == 1);
        assertTrue(tile2.getValue() == 2);

    }
}
