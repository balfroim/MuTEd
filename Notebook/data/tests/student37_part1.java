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
        Grid g1 = new Grid();
        // Act
        // Assert
        assertTrue(t1.equals(t2));
        assertFalse(t1.equals(t3));
        assertFalse(t1.equals(null));
        assertFalse(t1.equals(g1));
    }

    @Test
    public void testToString(){
        Tile t1 = new Tile(2);
        Tile t2 = new Tile(4);
        
        String t1_to_string = t1.toString();
        String t2_to_string = t2.toString();

        assertTrue(t1_to_string.equals("2"));
        assertTrue(t2_to_string.equals("4"));
    }

    @Test
    public void testGetValue(){
        Tile t1 = new Tile(2);
        Tile t2 = new Tile(4);

        assertTrue(t1.getValue() == 2);
        assertTrue(t2.getValue() == 4);
    }

    @Test
    public void testCanMergeWith(){
        Tile t1 = new Tile(2);
        Tile t2 = new Tile(2);
        Tile t3 = new Tile(2);
        Tile t4 = new Tile(2);
        Tile t5 = new Tile(4);

        t1.mergeWith(t2);

        assertTrue(t3.canMergeWith(t4));
        assertFalse(t3.canMergeWith(t5));
        assertFalse(t3.canMergeWith(null));
        assertFalse(t1.canMergeWith(t4));
        assertFalse(t4.canMergeWith(t1));
    }

    @Test
    public void testMerge(){
        Tile t1 = new Tile(2);
        Tile t2 = new Tile(2);
        
        int result = t1.mergeWith(t2);

        assertTrue(result == 4);
        assertTrue(t1.getValue() == 4);
        assertTrue(t2.getValue() == 2);
        assertTrue(t1.isMerged());
    }

    @Test
    public void testMergeNull(){
        Tile t1 = new Tile(2);
        
        int result = t1.mergeWith(null);

        assertTrue(result == -1);
        assertTrue(t1.getValue() == 2);
        assertTrue(t1.isMerged() == false);
    }

    @Test
    public void testMergeDifferentValues(){
        Tile t1 = new Tile(2);
        Tile t2 = new Tile(4);

        int result = t1.mergeWith(t2);

        assertTrue(result == -1);
        assertTrue(t1.isMerged() == false);
        assertTrue(t2.isMerged() == false);
        assertTrue(t1.getValue() == 2);
        assertTrue(t2.getValue() == 4);
    }

    @Test
    public void testMergeAlreadyMerged(){
        Tile t1 = new Tile(2);
        Tile t2 = new Tile(2);
        

        t1.setMerged(true);
        int result = t1.mergeWith(t2);

        assertTrue(result == -1);
        assertTrue(t1.isMerged());
        assertTrue(t2.isMerged() == false);
    }

    @Test
    public void testMergedTrue(){
        Tile t1 = new Tile(2);

        t1.setMerged(true);

        assertTrue(t1.isMerged() == true);
    }

    @Test
    public void testMergedFalse(){
        Tile t1 = new Tile(2);

        t1.setMerged(false);

        assertTrue(t1.isMerged() == false);
    }

    @Test
    public void testNearestPower2(){
        Tile t1 = new Tile(2);
        Tile t2 = new Tile(7);
        Tile t3 = new Tile(2040);
        Tile t4 = new Tile(2049);

        assertTrue(t1.getValue() == 2);
        assertTrue(t2.getValue() == 8);
        assertTrue(t3.getValue() == 2048);
        assertTrue(t4.getValue() == 2048);
    }

    @Test
    public void testGameStateInitial(){
        GameController gc = new GameController();
        assertTrue(gc.getGamestate() == GameState.start);
    }

    @Test
    public void testGameStateRunning(){
        GameController gc = new GameController();
        
        gc.startGame();

        assertTrue(gc.getGamestate() == GameState.running);
    }

    @Test
    public void testInitialHighScore(){
        GameController gc = new GameController();
        
        gc.startGame();

        assertTrue(gc.getHighestScore() == 0);
    }

    @Test
    public void testInitialScore(){
        GameController gc = new GameController();
        
        gc.startGame();

        assertTrue(gc.getScore() == 0);
    }

}
