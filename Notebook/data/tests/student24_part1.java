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
    public void testTileInit1() {
        // Arrange
        Tile tuile1 = new Tile(2);
        // Act

        // Assert
        assertTrue(tuile1.getValue() == 2);
    }

    @Test
    public void testTileInit2() {
        // Arrange
        Tile tuile1 = new Tile(2);
        // Act

        // Assert
        assertTrue(tuile1.isMerged() == false);
    }

    @Test
    public void testTileCanMerge1() {
        // Arrange
        Tile tuile1 = new Tile(2);
        Tile tuile2 = new Tile(2);

        // Act

        // Assert
        assertTrue(tuile1.canMergeWith(tuile2) == true);
    }

    @Test
    public void testTileCanMerge2() {
        // Arrange
        Tile tuile1 = new Tile(2);
        Tile tuile2 = new Tile(4);
        // Act

        // Assert
        assertTrue(tuile1.canMergeWith(tuile2) == false);
    }

    @Test
    public void testTileMerge1() {
        // Arrange
        Tile tuile1 = new Tile(2);
        Tile tuile2 = new Tile(2);

        // Act

        // Assert
        assertTrue(tuile1.mergeWith(tuile2) == 4);
        assertTrue(tuile1.isMerged() == true);
    }

    @Test
    public void testTileMerge2() {
    // Arrange
    Tile tuile1 = new Tile(2);
    Tile tuile2 = new Tile(4);

    // Act

    // Assert
    assertTrue(tuile1.mergeWith(tuile2) == -1);
    assertTrue(tuile1.isMerged() == false);
}

    @Test
    public void testTileToString() {
        // Arrange
        Tile tuile1 = new Tile(2);
        String valeurTuile;

        // Act
        valeurTuile = tuile1.toString();
        // Assert
        assertTrue(valeurTuile.equals("2"));
    }

    @Test
    public void testTileEquals1() {
        // Arrange
        Tile tuile1 = new Tile(2);
        Tile tuile2 = new Tile(2);

        // Act

        // Assert
        assertTrue(tuile1.equals(tuile2) == true);
    }

    @Test
    public void testTileEquals2() {
        // Arrange
        Tile tuile1 = new Tile(2);
        Tile tuile2 = new Tile(4);

        // Act

        // Assert
        assertTrue(tuile1.equals(tuile2) == false);
    }

    @Test
    public void testTileEquals3() {
        // Arrange
        Tile tuile1 = new Tile(2);
        Integer tuile2 = 2;

        // Act

        // Assert
        assertTrue(tuile1.equals(tuile2) == false);
    }


    
}
