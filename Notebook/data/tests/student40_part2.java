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
        Tile a = new Tile(256);
        Tile b = null;

        char c = 'c';
        
        Tile d = new Tile(4);
        Tile e = new Tile(256);
        
        // Act
        
        
        // Assert
        assertTrue(a.equals(b) == false);
        assertTrue(a.equals(c) == false);
        assertTrue(a.equals(d) == false);
        assertTrue(a.equals(e) == true);
        
    }

    @Test
    public void testTileGetValue() {
        // Arrange
        Tile a = new Tile(256);
        
        // Assert
        assertTrue(a.getValue() == 256);
    }

    @Test
    public void testTileIsMerged() {
        // Arrange
        Tile a = new Tile(256);
        Tile b = new Tile(256);
        
        
        // Act
        a.setMerged(false);
        b.setMerged(true);
        
        // Assert
        assertTrue(a.isMerged() == false);
        assertTrue(b.isMerged() == true);
        
    }

    @Test
    public void testTilecanMergeWith() {
        // Arrange

        Tile a = new Tile(256);
        Tile b = null;

        Tile c = new Tile(256);
        Tile d = new Tile(256);
        Tile d2 = new Tile(256);

        Tile e = new Tile(2);
        Tile f = new Tile(256);

        Tile g = new Tile(256);
        Tile h = new Tile(256);

        // Act
        c.setMerged(true);
        d2.setMerged(true);
        
        // Assert
        assertTrue(a.canMergeWith(b) == false);
        assertTrue(c.canMergeWith(d) == false);
        assertTrue(d.canMergeWith(c) == false);
        assertTrue(d2.canMergeWith(c) == false);
        assertTrue(e.canMergeWith(f) == false);
        assertTrue(g.canMergeWith(h) == true);
        
    }

    @Test
    public void testTilemergeWith() {
        // Arrange
        Tile a = new Tile(256);
        Tile b = null;

        Tile c = new Tile(256);
        Tile d = new Tile(256);

        // Assert
        assertTrue(a.mergeWith(b) == -1);
        assertTrue(c.mergeWith(d) == (256 * 2));
    }
}
