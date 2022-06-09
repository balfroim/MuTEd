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
        Tile t1 = new Tile(4);
        Tile t2 = new Tile(4);
        Tile t3 = null;
        int t4 = 2;
        // Act
        
        
        // Assert
        assertTrue(t1.equals(t2) == true);
        assertTrue(t1.equals(t3) == false);
        assertTrue(t1.equals(t4) == false);
    }

    @Test
    public void testTilecanMergeWith() {
        // Arrange
        Tile t1 = new Tile(4);
        Tile t2 = new Tile(4);
        Tile t3 = null;
        Tile t4 = new Tile(2);
        Tile t5 = new Tile(2);
        //Act
        t4.mergeWith(t5);
        //Assert
        assertTrue(t1.canMergeWith(t2) == true);
        assertTrue(t1.canMergeWith(t3) == false);
        assertTrue(t4.canMergeWith(t5) == false);
    }

    @Test
    public void testTilemergeWith() {
        //Arrange
        Tile t1 = new Tile(4);
        Tile t2 = new Tile(4);
        Tile t3 = new Tile(2);
        Tile t4 = null;

        //Act
        
        //Assert
        assertTrue(t1.mergeWith(t2) == 8);
        assertTrue(t3.mergeWith(t4) == -1);
    }
    @Test 
    public void testTileisMerged() {
        //Arrange
        Tile t1 = new Tile(4);
        Tile t2 = new Tile(2);
        Tile t3 = new Tile(2);

        //Act
        t2.mergeWith(t3);
        //Assert
        assertTrue(t1.isMerged() == false);
        assertTrue(t2.isMerged() == true);
    }
}
