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
        Tile v1 = new Tile(8);
        Tile v2 = new Tile(8);
        Tile v3 = new Tile(25);
        Tile v4 = new Tile(8);
        Tile v5 = new Tile(15);

        Grid grid1 = new Grid();

        // Act
        v3.setMerged(true);
        
        
        // Assert

        assertTrue(v1.toString().equals("8"));
        assertTrue(v4.mergeWith(v3) == -1);
        assertTrue(v4.mergeWith(v5) == -1);
        assertTrue(v4.mergeWith(v1) == 16);

        assertTrue(v1.equals(v2) == true);
        assertTrue(v1.equals(null) == false);
        assertTrue(v1.equals(grid1) == false);
        assertTrue(v1.equals(v5) == false);

        assertTrue(v2.canMergeWith(v1) == true);
        assertTrue(v3.canMergeWith(v1) == false);
        assertTrue(v1.canMergeWith(null) == false);
        assertTrue(v2.canMergeWith(v5) == false);


        
        

        assertTrue(v4.getValue() == 16);
        assertTrue(v3.getValue() == 32);

        assertTrue(v1.isMerged() == false);
        assertTrue(v4.isMerged() == true);
        
    } 
}
