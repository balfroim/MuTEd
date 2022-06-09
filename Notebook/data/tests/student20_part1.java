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
        Tile t2 = new Tile(4);
        String s1 = "hi";
        
        // Act
        boolean v1 = t1.equals(t2);
        boolean v2 = t1.equals(s1);
        
        // Assert
        assertTrue(v1 == true);
        assertTrue(v2 == false);
    }

    @Test
    public void testTilecanMergeWith() {
        // Arrange
        Tile t1 = new Tile(2);
        Tile t2 = new Tile(4);
        Tile t3 = new Tile(2);

        // Act
        boolean v1 = t1.canMergeWith(t2);
        boolean v2 = t1.canMergeWith(t3); 
        boolean v3 = t1.canMergeWith(null);
        t1.mergeWith(t3);
        boolean v4 = t1.canMergeWith(t3);

        // Assert
        assertTrue(v1 == false);
        assertTrue(v2 == true);
        assertTrue(v3 == false);
        assertTrue(v4 == false);
    }

    @Test
    public void testTileMergeWith() {
        // Arrange
        Tile t1 = new Tile(2);
        Tile t2 = new Tile(4);

        // Act
        t1.mergeWith(t2);
        boolean v1 = t1.mergeWith(t2);
        
        // Assert
        assertTrue(t1.value == 128);
        assertTrue(t1.isMerged == true);
        assertTrue(v1 == -1);
    }

    @Test
    public void testGameControllerStartGame() {
        // Arrange
        GameController g1 = new GameController();

        // Act
        g1.startGame();
        
        // Assert
        assertTrue(g1.gamestate == GameState.running);
        assertTrue(g1.grid.getLength() == GameParams.sideLength);
        assertTrue(g1.grid.getTile(0) == 2);
    }

    @Test
    public void testGameControllerFillFirstEmptyTile() {
        // Arrange
        GameController g1 = new GameController();
        GameController g2 = new GameController();
        Grid gr1 = new Grid();

        // Act
        g1.startGame(gr1);
        boolean v1 = g1.fillFirstEmptyTile();
        
        // Assert
        assertTrue(v1 == true);
    }
    
}
