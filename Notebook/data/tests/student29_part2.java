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
        Tile v1 = new Tile(2);
        Tile v2 = new Tile(2);
        Tile v3 = new Tile(4);
        Integer value = 2;
        
        // Act
        boolean result1 = v1.equals(v2);
        boolean result2 = v2.equals(v1);
        boolean result3 = value.equals(v1);
        boolean result4 = v1.equals(value);
        boolean result5 = v1.equals(null);
        boolean result6 = v2.equals(v3);


        
        // Assert
        assertTrue(result1 == true);
        assertTrue(result2 == result1);
        assertTrue(result3 == false);
        assertTrue(result4 == false);
        assertTrue(result5 == false);
        assertTrue(result6 == false);

    }

    @Test
    public void testTileCanMergeWith() {
        // Arrange
        Tile v1 = new Tile(2);
        Tile v2 = new Tile(2);
        Tile v3 = new Tile(4);

        // Act
        boolean result1 = v1.canMergeWith(v2);
        boolean result2 = v1.canMergeWith(v3);
        boolean result3 = v1.canMergeWith(null);


        // Assert
        assertTrue(result1 == true);
        assertTrue(result2 == false);
        assertTrue(result3 == false);
    }

    @Test
    public void testTileMergeWith() {
        // Arrange
        Tile v1 = new Tile(2);
        Tile v2 = new Tile(2);
        Tile v3 = new Tile(4);

        // Act
        int result1 = v1.mergeWith(v2);
        int result2 = v1.mergeWith(v3);
        
        // Assert
        assertTrue(result1 == 4);
        assertTrue(result2 == -1);
        
    }

    @Test
    public void testTileGetValue() {
        // Arrange
        Tile v1 = new Tile(2);

        // Act
        int result1 = v1.getValue();
        
        // Assert
        assertTrue(result1 == 2);
    }

    @Test
    public void testTileIsMerged() {
        // Arrange
        Tile v1 = new Tile(4);
        Tile v2 = new Tile(2);
        Tile v3 = new Tile(2);
        v2.mergeWith(v3);
        

        // Act
        boolean result1 = v1.isMerged();
        boolean result2 = v2.isMerged();
        
        // Assert
        assertTrue(result1 == false);
        assertTrue(result2 == true);
    }

    @Test
    public void testTileToString() {
        // Arrange 
        Tile v1 = new Tile(2);

        // Act
        String result1 = v1.toString();
        
        // Assert
        assertTrue(result1.equals("2"));
    }

    @Test
    public void testGridGetTile() {
        // Arrange 
        Grid v1 = new Grid();

        // Act
        Tile result1 = v1.getTile(1);
        
        // Assert
        assertTrue(result1 == null);
    }

    @Test
    public void testGridSetTile() {
        // Arrange 
        Grid v1 = new Grid();
        Tile v2 = new Tile(2); 
        // Act
        v1.setTile(4, v2);
        
        // Assert
        assertTrue(v1.getTile(4) == v2);
    }
}
