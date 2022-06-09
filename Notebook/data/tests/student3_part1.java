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
        Tile x1 = new Tile(2);
        Tile x2 = new Tile(2);
        Integer i1 = 2;
        Tile x3 = null;
        
        // Act
        boolean testEquals = x1.equals(x2);
        boolean testEquals2 = x1.equals(i1);
        boolean testEquals3 = x1.equals(x3);
        
        // Assert
        assertTrue(testEquals == true);
        assertTrue(testEquals2 == false);
        assertTrue(testEquals3 == false);
    }

    @Test 
    public void testTileCanMergeWith() {

        // Arrange
        Tile x1 = new Tile(2);
        Tile x2 = new Tile(2);

        x1.setMerged(true);

        // Act
        boolean testMerged = x1.canMergeWith(x2);

        // Assert
        assertTrue(testMerged == false);
    }

    @Test
    public void testTileMergedWith(){

        // Arrange
        Tile x1 = new Tile(2);
        Tile x2 = new Tile(4);

        // Act
        int testMergedWith = x1.mergeWith(x2);

        // Assert
        assertTrue(testMergedWith == -1);
    }

    @Test
    public void testTileIsMerged(){

        // Arrange
        Tile x1 = new Tile(2);

        // Act
        boolean testIsMerged = x1.isMerged();

        // Assert
        assertTrue(testIsMerged == false);
    }

    @Test
    public void testTileSetMerged(){

        // Arrange
        Tile x1 = new Tile(2);

        // Act
        x1.setMerged(true);

        // Assert
        assertTrue(x1.isMerged());
    }

    @Test
    public void testGridGetTile(){

        // Arrange
        Grid g1 = new Grid();
        Tile t1 = new Tile(2);
        g1.setTile(0, t1);

        // Act
        Tile x1 = g1.getTile(0,0);

        // Assert
        assertTrue(x1.equals(t1) == true);
    }

    @Test
    public void testGridGetTile2(){

        // Arrange
        Grid g1 = new Grid();
        Tile t1 = new Tile(2);
        g1.setTile(0, t1);

        // Act
        Tile x1 = g1.getTile(0);

        // Assert
        assertTrue(x1.equals(t1) == true);
    }


}
