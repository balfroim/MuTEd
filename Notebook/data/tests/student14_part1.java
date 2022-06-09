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
    public void testTileEquals1() {
        // Arrange
        Tile t1 = new Tile(4);
        Tile t2 = new Tile(2);
        // Act
        t1.equals(t2);
        
        // Assert
        assertFalse(t1.equals(t2));
    }
    @Test
    public void testTileEquals2() {
        // Arrange
        Tile t1 = new Tile(2);
        Tile t2 = new Tile(2);
        // Act
        t1.equals(t2);
        
        // Assert
        assertTrue(t1.equals(t2));
    }
    @Test
    public void testGetValue() {
        // Arrange
        Tile t1 = new Tile(2);
        Integer res;
        // Act
        res=t1.getValue();
        
        // Assert
        assertTrue(res==2);
    }
    @Test
    public void testCanMergeWith1() {
        // Arrange
        Tile t1 = new Tile(256);
        Tile t2 = new Tile(256);
        // Act
        t1.canMergeWith(t2);
        // Assert
        assertTrue(t1.canMergeWith(t2));
    }
    @Test
    public void testCanMergeWith2() {
        // Arrange
        Tile t1 = new Tile(256);
        Tile t2 = new Tile(1024);
        // Act
        t1.canMergeWith(t2);
        // Assert
        assertFalse(t1.canMergeWith(t2));
    }
}
