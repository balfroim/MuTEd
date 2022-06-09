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
        Object ob = new Object();
        // Act
        boolean equals = tile1.equals(tile2);
        boolean equals1 = tile1.equals(ob);
        // Assert
        assertTrue(equals);
        assertFalse(equals1);
        assertFalse(tile2 == null);
        assertFalse(ob == null);
    }

}
