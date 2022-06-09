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
        Tile tile1 = new Tile(2);
        Tile tile2 = new Tile(2);
        Tile tile3 = new Tile(4);
        
        // Act

        
        // Assert
        assertTrue(tile1.canMergeWith(tile2));
        assertFalse(tile1.canMergeWith(tile3));

        assertTrue(tile1.mergeWith(tile2) == 4);
        assertTrue(tile1.mergeWith(tile3) == -1);



        
    }

    public void test_grid(){
        

    }
}
