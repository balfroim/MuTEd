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

        // Act
        
        
        // Assert
        assertTrue(t1.equals(t2) == true);
        assertTrue(t1.equals(t3) == false);
    }

    @Test
    public void testTilecanMergeWith() {
        // Arrange
        Tile t1 = new Tile(4);
        Tile t2 = new Tile(4);
        
        //Act

        //Assert
        assertTrue(t1.canMergeWith(t2) == true);
    }

    @Test
    public void testTilemergeWith() {
        //Arrange
        Tile t1 = new Tile(4);
        Tile t2 = new Tile(4);
        Tile t3 = new Tile(2);

        //Act
        t1.mergeWith(t2);
        t2.mergeWith(t3);

        //Assert
        assertTrue(t1.getValue() == 8);
        assertTrue(t2.getValue() == 4);
    }
}
