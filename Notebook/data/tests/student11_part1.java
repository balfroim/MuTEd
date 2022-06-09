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
    public void testTileMergeWith() {
        // Arrange
        Tile t1 = new Tile(2);
        Tile t2 = new Tile(2);
        Tile t3 = new Tile(4);
        Tile t4 = new Tile(8);

        // Act
        int value1 = t1.mergeWith(t2);
        int value2 = t3.mergeWith(null);
        int value3 = t3.mergeWith(t1);
        int value4 = t3.mergeWith(t4);

        // Assert
        assertTrue(value1 == 4);
        assertTrue(value2 == -1);
        assertTrue(value3 == -1);
        assertTrue(value4 == -1);
    }

    @Test
    public void testTileEquals() {
        // Arrange
        Tile t1 = new Tile(2);
        Tile t2 = new Tile(2);
        Tile t3 = new Tile(4);

        // Act
        boolean value1 = t1.equals(t2);
        boolean value2 = t1.equals(t3);

        // Assert
        assertTrue(value1);
        assertFalse(value2);
    }

    @Test
    public void testGridSetTile() {
        // Arrange
        Grid g = new Grid();
        Tile t = new Tile(2);

        // Act
        g.setTile(2, t);


        // Assert
        //assertTrue();
    }
}
