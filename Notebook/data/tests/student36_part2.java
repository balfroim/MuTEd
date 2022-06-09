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

    // Tests Tile
    @Test
    public void testTileGetValue() {
        // Arrange
        Tile v1 = new Tile(2);
        Tile v2 = new Tile(2);
        
        // Act
        boolean check = v1.getValue() == v2.getValue();
        
        // Assert
        assertTrue(check == true);
        // On check que la valeur d'une tuile est bien une puissance de 2 et différent de 0
        assertTrue(v1.getValue() != 0);
        assertTrue(v2.getValue() != 0);
        assertTrue(v1.getValue() % 2 == 0);
        assertTrue(v2.getValue() % 2 == 0);
    }

    @Test
    public void testTileEquals() {
        // Arrange
        Tile v1 = new Tile(2);
        Grid g1 = new Grid();
        Tile v2 = new Tile(2);
        
        // Act
        boolean check = v1.equals(v2);
        boolean check2 = v1.equals(g1);
        
        // Assert
        assertTrue(check == true);
        assertTrue(check2 == false);
    }

    @Test
    public void testTileIsMerged() {
        // Arrange
        Tile v1 = new Tile(2);
        Tile v2 = new Tile(2);
        
        // Act
        v1.mergeWith(v2);
        boolean check = v1.isMerged();
        
        // Assert
        assertTrue(check == true);
    }

    @Test
    public void testTileCanMergeWith() {
        // Arrange
        Tile v1 = new Tile(2);
        Tile v2 = new Tile(4);
        Tile v3 = new Tile(2);
        
        // Act
        boolean check = v1.canMergeWith(v3);
        boolean check2 = v1.canMergeWith(v2);
        
        // Assert
        assertTrue(check == true);
        assertTrue(check2 == false);
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
        
        // Assert
        assertTrue(check == true);
    }

    // Tests Tile pas su continuer
    /*@Test
    public void testGridSetTile() {
        // Arrange
        Tile v1 = new Tile(2);
        Grid g1 = new Grid();

        // Act
        
        // Assert
    }
    @Test
    public void testGridGetRow() {
        // Arrange
        Tile v1 = new Tile(2);
        Grid g1 = new Grid();

        // Act
        int val1 = g1.getRow();
        System.out.println(val1.getValue());
        
        // Assert
       // assertTrue(val1.getClass() == int);
    }*/
}
