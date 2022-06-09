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
        Tile t3 = null;
        Tile t4 = new Tile(256);
        // Act
        t1.canMergeWith(t2);
        t1.mergeWith(t2);
        t4.canMergeWith(t3);
        t1.canMergeWith(t4);
        // Assert
        assertFalse(t1.canMergeWith(t2));
        assertFalse(t4.canMergeWith(t3));
        assertFalse(t1.canMergeWith(t4));
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
    @Test
    public void testMergeWith1() {
        // Arrange
        Tile t1 = new Tile(256);
        Tile t2 = new Tile(1024);
        int res;
        // Act
        res=t1.mergeWith(t2);
        // Assert
        assertTrue(res==-1);
    }
    @Test
    public void testMergeWith2() {
        // Arrange
        Tile t1 = new Tile(1024);
        Tile t2 = new Tile(1024);
        int res;
        // Act
        res=t1.mergeWith(t2);
        // Assert
        assertTrue(res==2048);
    }      
    @Test
    public void testIsMerged() {
        // Arrange
        Tile t1 = new Tile(8);
        Tile t2 = new Tile(8);
        // Act
        t1.mergeWith(t2);
        // Assert
        assertTrue(t1.isMerged()==true);
        assertTrue(t2.isMerged()==false);
    }

}
