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
        Tile t1 = new Tile(2);
        Tile t2 = new Tile(2);
        Tile t3 = new Tile(4);
        Tile t4 = new Tile(2);
        
        // Act
        t1.mergeWith(t2);
        
        // Assert
        assertTrue(t1.equals(t3));
        assertTrue(t2.equals(t4));
    }

    @Test
    public void testTileWhenAlreadyMerged() {
        // Arrange
        Tile t1 = new Tile(2);
        Tile t2 = new Tile(2);
        Tile t3 = new Tile(4);
        Tile t4 = new Tile(2);
        Tile t5 = new Tile(7);
        
        // Act
        t1.setMerged(true);
        int i = t1.mergeWith(t2);
        int j = t3.mergeWith(t4);
        
        // Assert
        assertTrue(i == -1);
        assertTrue(j == -1);
        assertTrue(t5.getValue() == 8);
        assertTrue(t5.mergeWith(null) == -1);
    }
}
