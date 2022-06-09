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
         Tile t3 = new Tile(2);
         String s1 = "hi";
        
         // Act
         boolean v1 = t1.equals(t2);
         boolean v2 = t1.equals(t3);
         boolean v3 = t1.equals(s1);
        
         // Assert
         assertTrue(!v1);
         assertTrue(v2);
         assertTrue(!v3);
     }

    @Test
     public void testTileGetValue() {
         // Arrange
         Tile t1 = new Tile(2);
        
         // Act
         int v1 = t1.getValue();
        
         // Assert
         assertTrue(v1 == 2);
     }

    @Test
     public void testTileIsMerged() {
         // Arrange
         Tile t1 = new Tile(2);
         Tile t2 = new Tile(2);
         Tile t3 = new Tile(4);
        
         // Act
         t1.mergeWith(t2);
         t2.mergeWith(t3);
        
         // Assert
         assertTrue(t1.isMerged() == true);
         assertTrue(t2.isMerged() == false);
     }

     @Test
     public void testTileSetMerged() {
         // Arrange
         Tile t1 = new Tile(2);
         Tile t2 = new Tile(2);
        
         // Act
         t1.setMerged(false);
         t2.setMerged(true);
        
         // Assert
         assertTrue(t1.isMerged() == false);
         assertTrue(t2.isMerged() == true);
     }

     @Test
     public void testTileToString() {
         // Arrange
         Tile t1 = new Tile(2);
        
         // Act
         String v1 = t1.toString();
        
         // Assert
         assertTrue(v1.equals("2"));
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
         Tile t3 = new Tile(2);

         // Act
         t1.mergeWith(t3);
         int v1 = t1.mergeWith(t3);
        
         // Assert
         assertTrue(t1.getValue() == 4);
         assertTrue(t1.isMerged() == true);
         assertTrue(v1 == -1);
     }

     @Test
     public void testGameControllerStartGame() {
         // Arrange
         GameController g1 = new GameController();

         // Act
         g1.startGame();
        
         // Assert
         assertTrue(g1.getGamestate() == GameState.running);
         assertTrue(g1.getGrid().getLength() == GameParams.sideLength);
         assertTrue(g1.getGrid().getTile(0) != null);
         //assertTrue(g1.getGrid().getTile(0).getValue() == 2);

    }

    @Test
    public void testGameControllerGetGrid() {
        // Arrange
        GameController g1 = new GameController();
        Grid gr1 = new Grid();

        // Act
        g1.startGame();
        
        // Assert
        assertTrue(g1.getGrid().getClass() == gr1.getClass());
    }

    @Test
    public void testGameControllerResetData() {
        // Arrange
        GameController g1 = new GameController();

        // Act
        g1.startGame();
        
        // Assert
    }

    @Test
    public void testGameControllerFillFirstEmptyTile() {
        // Arrange
        GameController g1 = new GameController();

        // Act
        g1.startGame();
        boolean v1 = g1.fillFirstEmptyTile();
        
        // Assert
        assertTrue(v1 == true);
        //assertTrue(g1.getGrid().getTile(1) != null)
    }
    
}
