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
        //La valeur de la tuile doit être une puissance de 2.
        // Arrange
        Tile v1 = new Tile(9);
        
        // Act (fait avec le arrange ici.)
        // Assert : Vérifie que la valeur de la tuile
        assertTrue(v1.getValue() == 8);
    }

    @Test
    public void testGetValue() {
        Tile v1 = new Tile(64);
        assertTrue(v1.getValue() == 64);
    }

    @Test
    public void testIsMerged() {
        Tile v1 = new Tile(16);
        v1.setMerged(true);

        assertTrue(v1.isMerged() == true);

        v1.setMerged(false);
        assertTrue(v1.isMerged() == false);
    }

    @Test
    public void testCannotMergeWith() {
        //Arrange
        Tile v1 = new Tile(56);
        Tile v2 = new Tile(99);
        Tile v3 = new Tile(5);
        Tile v4 = new Tile(5);
        Tile v5 = new Tile(46);
        Tile v6 = new Tile(7);
        Tile v8 = new Tile(55);
        Tile v9 = new Tile(20);

        //Act
        v5.isMerged();
        v9.isMerged();

        //Assert
        //Différents cas de merge qui ne marchent pas
        assertTrue(v1.canMergeWith(v2) == false);
        assertTrue(v3.canMergeWith(v4));
        assertTrue(v5.canMergeWith(v6) == false);
        assertTrue(v8.canMergeWith(v9) == false);

    }

    /*
    @Test
    public void testCanMerge() {
        Tile v1 = new Tile(8);
        Tile v2 = new Tile(null);
        assertTrue(v2.canMergeWith(v2) == false);
    }
    */
    

    @Test
    public void testMergeWithSomethingMerged() {
        Tile v1 = new Tile(67);
        Tile v2 = new Tile(67);

        //Act
        v2.setMerged(true);
        v1.canMergeWith(v2);

        assertTrue(v1.mergeWith(v2) == -1);
    }

    @Test
    public void testMergeWithSomethingNotTheSameValue() {
        Tile v1 = new Tile(2);
        Tile v2 = new Tile(4);

        v1.canMergeWith(v2);

        assertTrue(v1.mergeWith(v2) == -1);
    }

    @Test
    public void testMergeWith() {
        Tile v1 = new Tile(8);
        Tile v2 = new Tile(8);
        int result;
        result = v1.mergeWith(v2);

        assertTrue(v1.isMerged() == true);
        assertTrue(result == 16);
        //assertTrue(result == v1.getValue() + v2.getValue());
        assertTrue(v2.getValue() == 8);
        assertTrue(v1.getValue() == 16);
    }

    @Test
    public void testSetMerge() {
        Tile v1 = new Tile(55);
        v1.setMerged(true);
        assertTrue(v1.isMerged() == true);
    }

    @Test
    public void testToString() {
        Tile v1 = new Tile(8);
        assertTrue(v1.toString() != "");
    }

    @Test
    public void testEqualsWithNull() {
        Tile v1 = new Tile(8);
        Object v2 = null;

        assertTrue(v1.equals(v2) == false);
    }

    @Test
    public void testEqualsWithDifferentClasses() {
        Tile v1 = new Tile(8);
        Object v2 = new Object();

        assertTrue(v1.equals(v2) == false);
    }

    @Test
    public void testEquals() {
        Tile v1 = new Tile(8);
        Tile v2 = new Tile(16);
        Tile v3 = new Tile(8);

        assertTrue(v1.equals(v2) == false);
        assertTrue(v1.equals(v3) == true);
    }

    //Grid tests : 
    /*
    @Test
    public void testGetTile() {
        Grid v1 = new Grid();
        assertTrue(v1.getTile(1,1) == Grid[1][1]);
    }
    */

}
