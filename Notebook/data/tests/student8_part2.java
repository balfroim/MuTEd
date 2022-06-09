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

    // + Add init avec un nombre pas multiple de 2

    @Test
    public void testTileInit() {
        // Arrange
        Tile tile1 = new Tile(2);
        // Act
        
        // Assert
        assertTrue(tile1.getValue() == 2);
    }

    @Test
    public void testTileInit1() {
        // Arrange
        Tile tile1 = new Tile(5);
        // Act
        
        // Assert
        assertTrue(tile1.getValue() == 4);
    }

    @Test
    public void testTileEquals() {
        // Arrange
        Tile tile1 = new Tile(2);
        Tile tile2 = new Tile(2);
        // Act
        

        // Assert
        assertTrue(tile1.equals(tile2));
    }

    @Test
    public void testTileCanMerge() {
        // Arrange
        Tile tile1 = new Tile(2);
        Tile tile2 = new Tile(2);
        // Act
        
        // Assert
        assertTrue(tile1.canMergeWith(tile2));
        assertTrue(tile2.canMergeWith(tile1));
    }

    @Test
    public void testTileCantMerge() {
        // Arrange
        Tile tile1 = new Tile(2);
        Tile tile2 = new Tile(4);
        // Act

        // Assert
        assertTrue(!tile1.canMergeWith(tile2));
    }

    @Test
    public void testTileSetMerged() {
        // Arrange
        Tile tile1 = new Tile(2);
        // Act
        tile1.setMerged(true);
        // Assert
        assertTrue(tile1.isMerged());
    }

    @Test
    public void testTileCantMerge2() {
        // Arrange
        Tile tile1 = new Tile(2);
        Tile tile2 = new Tile(4);
        // Act
        tile1.setMerged(true);
        // Assert
        assertTrue(!tile1.canMergeWith(tile2));
    }

    @Test
    public void testTileCantMergeNull() {
        // Arrange
        Tile tile1 = new Tile(2);
        Tile tile2 = null;
        // Act
        
        // Assert
        assertTrue(!tile1.canMergeWith(tile2));
        assertTrue(tile1.mergeWith(tile2) == -1);
    }

    @Test
    public void testTileMerge() {
        // Arrange
        Tile tile1 = new Tile(2);
        Tile tile2 = new Tile(2);
        // Act
        assertTrue(tile2.canMergeWith(tile1));
        int merged = tile1.mergeWith(tile2);
        // Assert
        assertTrue(merged == 4);
        assertTrue(tile1.isMerged());
    }
    
    @Test
    public void testGridInit() {
        // Arrange
        Grid grid1 = new Grid();
        // Act
        int length = grid1.getLength();
        // Assert
        assertTrue(length == GameParams.sideLength);
        assertTrue(length == 4);
    }

    @Test
    public void testGridGetTile() {
        // Arrange
        Tile[][] tiles = new Tile[5][5];
        tiles[2][2] = new Tile(8);
        // Act
        Grid grid1 = new Grid(tiles);
        Tile tile1 = grid1.getTile(2,2);
        // Assert
        assertTrue(tile1 == tiles[2][2]);
    }

    @Test
    public void testGridGetRow() {
        // Arrange
        Tile[][] tiles = new Tile[5][5];
        tiles[2][2] = new Tile(8);
        // Act
        Grid grid1 = new Grid(tiles);
        // Assert
        assertArrayEquals(grid1.getRow(2), tiles[2]);
    }

    //@Test
    //public void testGridGetCol() {
        // Arrange
    //    Tile[][] tiles = new Tile[5][5];
    //    tiles[2][2] = new Tile(8);
        // Act
    //    Grid grid1 = new Grid(tiles);
        // Assert
    //    for (int i = 0; i < tiles.length; i++) {
    //        assertEquals(grid1.getCol(2)[i], tiles[i][2]);
    //    }
    //}

    @Test
    public void testGridSetTile() {
        // Arrange
        Grid grid1 = new Grid();
        Tile tile1 = new Tile(2);
        // Act
        grid1.setTile(3,tile1);
        Tile tile2 = grid1.getTile(3); 
        // Assert
        assertTrue(tile2 == tile1);
    }

    @Test
    public void testGridClearMerged() {
        // Arrange
        Grid grid1 = new Grid();
        Tile tile1 = new Tile(8);
        Tile tile2 = new Tile(8);
        Tile tile3 = new Tile(8);
        // Act
        tile1.setMerged(true);
        grid1.setTile(3,tile1);
        tile2.setMerged(true);
        grid1.setTile(6,tile2);
        grid1.clearMerged();
        // Assert
        assertTrue(!grid1.getTile(3).isMerged());
        assertTrue(!grid1.getTile(6).isMerged());

        for (int i = 0; i < GameParams.sideLength; i++) {
            for (int j = 0; j < GameParams.sideLength; j++) {
                if (grid1.getTile(i,j) != null)
                    assertTrue(grid1.getTile(i,j).canMergeWith(tile3));
            }
        }
    }
}
