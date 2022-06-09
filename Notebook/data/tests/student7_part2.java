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

    // Tile class

    @Test
    public void testTilecanMergeWith() {
        // Arrange
        Tile t = new Tile(2);
        Tile t2 = new Tile(2);
        
        // Act
        Boolean v = t.canMergeWith(t2);
        
        // Assert
        assertTrue(v == true);

    }

    @Test
    public void testTilecanMergeWith2() {
        // Arrange
        Tile t = new Tile(2);
        Tile t2 = new Tile(2);
        
        // Act
        t.setMerged(true);
        Boolean v = t.canMergeWith(t2);
        
        // Assert
        assertTrue(v == false);

    }

    @Test
    public void testTilecanMergeWith3() {
        // Arrange
        Tile t = new Tile(2);
        Tile t2 = new Tile(2);
        
        // Act
        t.setMerged(true);
        Boolean v = t.canMergeWith(null);
        
        // Assert
        assertTrue(v == false);

    }

    @Test
    public void testTileEquals() {
        // Arrange
        Tile t = new Tile(2);
        Tile t2 = new Tile(2);
        
        // Act
        Boolean v = t.equals(t2);
        
        // Assert
        assertTrue(v == true);

    }

    @Test
    public void testTileEquals2() {
        // Arrange
        Tile t = new Tile(2);
        Tile t2 = new Tile(2);
        
        // Act
        Boolean v = t.equals(null);
        
        // Assert
        assertTrue(v == false);

    }

    @Test
    public void testTileEquals3() {
        // Arrange
        Tile t = new Tile(2);
        Integer n = 16;
        
        // Act
        Boolean v = t.equals(n);
        
        // Assert
        assertTrue(v == false);

    }

    @Test
    public void testTilemergeWith() {
        // Arrange
        Tile t = new Tile(2);
        Tile t2 = new Tile(2);
        
        // Act
        Integer v = t.mergeWith(t2);
        
        // Assert
        assertTrue(t.getValue() == 4);

    }

    @Test
    public void testTilemergeWith2() {
        // Arrange
        Tile t = new Tile(2);
        Tile t2 = new Tile(2);
        
        // Act
        t.setMerged(true);
        Integer v = t.mergeWith(t2);
        
        // Assert
        assertTrue(v == -1);

    }

    @Test
    public void testTilemergeWith3() {
        // Arrange
        Tile t = new Tile(2);
        Tile t2 = new Tile(8);
        
        // Act
        Integer v = t.mergeWith(t2);
        
        // Assert
        assertTrue(v == -1);

    }


    // Grid class
    
    @Test
    public void testGridsetTile() {
        // Arrange
        Grid g = new Grid();
        Tile t = new Tile(2);
        
        // Act
        g.setTile(0, t);
        Tile t2 = g.getTile(0,0);
        
        // Assert
        assertTrue(t2.equals(t) == true);
        

    }


    @Test
    public void testGridsetTile2() {
        // Arrange
        Grid g = new Grid();
        Tile t = new Tile(4);

        // Act
        g.setTile(1, t);
        Tile t2 = g.getTile(1);
      
        
        // Assert
        assertTrue(t2.equals(t) == true);
         

    }

    
}
