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
    public void testTileGetValue() {
        // Arrange
        Tile v1 = new Tile(2);
        Tile v2 = new Tile(4);
        
        // Act
        boolean check = v1.getValue() == v2.getValue();
        boolean check2 = v1.getValue() == v1.getValue();
        
        // Assert
        assertTrue(check2 == true);
    }

    @Test
    public void testTileEquals() {
        // Arrange
        Tile v1 = new Tile(2);
        Grid g1 = new Grid();
        Tile v2 = new Tile(2);
        
        // Act
        boolean check = v1.equals(g1);
        boolean check2 = v1.equals(v2);
        
        // Assert
        assertTrue(check2 == true);
    }

    @Test
    public void testTileIsMerged() {
        // Arrange
        Tile v1 = new Tile(2);
        Tile v2 = new Tile(2);
        
        // Act
        boolean check = v1.isMerged();
        v1.mergeWith(v2);
        boolean check2 = v1.isMerged();
        
        // Assert
        //assertTrue(check == true);
        assertTrue(check2 == true);
    }

    @Test
    public void testTileCanMergeWith() {
        // Arrange
        Tile v1 = new Tile(2);
        Tile v2 = new Tile(4);
        Tile v3 = new Tile(2);
        
        // Act
        boolean check = v1.canMergeWith(v2);
        boolean check2 = v1.canMergeWith(v3);
        
        // Assert
        //assertTrue(check == true);
        assertTrue(check2 == true);
    }

    @Test
    public void testTileMergeWith() {
        // Arrange
        Tile v1 = new Tile(2);
        Tile v2 = new Tile(2);
        Tile v3 = new Tile(4);

        // Act
        v1.mergeWith(v2);
        boolean check = v1.isMerged();
        boolean check2 = v3.isMerged();
        
        // Assert
        assertTrue(check == true);
        //assertTrue(check2 == true);
    }
    //Pas fini
    /*
    @Test
    public void testTileSetMerged() {
        // Arrange
        Tile v1 = new Tile(2);
        Tile v2 = new Tile(2);
        Tile v3 = new Tile(4);

        // Act
        boolean check = v1.setMerged();
        boolean check2 = v3.isMerged();
        
        // Assert
        assertTrue(check == true);
        //assertTrue(check2 == true);
    }*/
}
