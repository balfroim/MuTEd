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
        Tile v1 = new Tile(67);
        
        // Act (fait avec le arrange ici.)
        // Assert : Vérifie que la valeur de la tuile
        assertTrue(v1.getValue() == 64);
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
        assertTrue(v2.getValue() == 8);
        assertTrue(v1.getValue() == 16);
    }

    @Test
    public void testSetMerge() {
        Tile v1 = new Tile(55);
        v1.setMerged(true);
        assertTrue(v1.isMerged() == true);
    }

}
