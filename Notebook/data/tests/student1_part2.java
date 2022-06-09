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
        Tile t1 = new Tile(2);
        Tile t2 = new Tile(2);
        Tile t3 = new Tile(3);

        
        // Act
        
        
        // Assert
        assertTrue(t1.equals(t2));
        assertFalse(t1.equals(t3));
        assertFalse(t2.equals(t3));
    }

    @Test
    public void getValue() {
        // Arrange
        Tile t1 = new Tile(2);
        Tile t2 = new Tile(2);
        Tile t3 = new Tile(3);

        
        // Act
        
        
        // Assert
        assertEquals(t1.getValue(), t2.getValue());
        assertEquals(t1.getValue(), 2);
        assertEquals(t2.getValue(), 2);
        assertEquals(t3.getValue(), 4);
        assertEquals(t1.getValue(), t2.getValue());
        assertFalse(t1.getValue() == t3.getValue());
        assertFalse(t2.getValue() == t3.getValue());
    }

    
    @Test
    public void testTileToString() {
        // Arrange
        Tile t1 = new Tile(2);
        Tile t2 = new Tile(2);
        Tile t3 = new Tile(4);


        
        // Act
        
        
        // Assert
        assertEquals(t1.toString(), t2.toString());
        assertEquals(t1.toString(), "2");
        assertEquals(t2.toString(), "2");
        assertEquals(t3.toString(), "4");
        assertFalse(t1.toString().equals(t3.toString()));
    }

    @Test
    public void testCanMergeWith() {
        // Arrange
        Tile t1 = new Tile(2);
        Tile t2 = new Tile(2);
        Tile t3 = new Tile(4);
        Tile t4 = new Tile(2);


        
        // Act
        t4.setMerged(true);
        
        // Assert
        assertTrue(t1.canMergeWith(t2));
        assertFalse(t1.canMergeWith(t3));
        assertFalse(t2.canMergeWith(t3));
        assertFalse(t1.canMergeWith(t4));
        assertFalse(t2.canMergeWith(t4));
    }

    @Test
    public void testMergeWith() {
        // Arrange
        Tile t1 = new Tile(4);
        Tile t2 = new Tile(4);
        Tile t3 = new Tile(2);
               
        // Act
        t1.mergeWith(t2);
        t3.mergeWith(t2);
        
        // Assert
        assertEquals(t1.getValue(), 8);
        assertTrue(t1.isMerged());
        assertFalse(t3.isMerged());
        assertEquals(t3.getValue(), 2);
    }
}


