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
        Tile tile1 =  new Tile(2);
        Tile tile2 =  new Tile(2);
        Tile tile3 =  new Tile(4);
        
        // Act
        
        
        // Assert
        assertTrue(tile1.equals(tile2));
        assertFalse(tile2.equals(tile3));
    }
    @Test
    public void testGetValue(){
        Tile tile1 =  new Tile(4);
        
        int val;

        val = tile1.getValue();

        assertTrue(val == 4);

    }
    @Test
    public void testCanMergedValue(){
        Tile tile1 =  new Tile(4);
        Tile tile2 =  new Tile(8);
        Tile tile3 =  new Tile(4);
        
        assertFalse(tile1.canMergeWith(tile2));
        assertTrue(tile1.canMergeWith(tile3));
    }

    @Test
    public void testMergedValue(){
        Tile tile1 =  new Tile(4);
        Tile tile2 =  new Tile(4);
        Tile tile3 =  new Tile(8);

        tile1.mergeWith(tile2);
        tile1.mergeWith(tile3);
        int val;
        val = tile1.getValue();

        assertTrue(val == 8);

    }
    @Test
     public void testsetMergedValue(){
        Tile tile1 =  new Tile(4);
        Tile tile2 =  new Tile(4);
        Tile tile3 =  new Tile(8);

        tile1.mergeWith(tile2);
        tile2.mergeWith(tile3);
        boolean val;
        boolean val2;
        val = tile1.isMerged();
        val2 = tile2.isMerged();

        assertTrue(val);
        assertFalse(val2);

    }


}
