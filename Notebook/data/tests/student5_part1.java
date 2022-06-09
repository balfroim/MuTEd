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
    public void testTileGoodValue() {
        // Arrange
        Tile t1 = new Tile(2);
        
        // Act
        Integer value = t1.getValue();
        
        // Assert
        assertTrue(value == 2);

    }

    @Test
    public void testTileGoodMerged(){
        // Arrange
        Tile t1 = new Tile(2);
        
        // Act
        Boolean merged = t1.isMerged();

        // Assert
        assertTrue(merged == false);
    }

    @Test
    public void testTilecanMergedWithOK(){
        // Arrange
        Tile t1 = new Tile(2);
        Tile t2 = new Tile(2);
        
        // Act
        Boolean res = t1.canMergeWith(t2);
        
        // Assert
        assertTrue(res == true);
    }

    @Test
    public void testTilecanMergedWithKoValue(){
        // Arrange
        Tile t1 = new Tile(2);
        Tile t2 = new Tile(4);
        
        // Act
        Boolean res = t1.canMergeWith(t2);
        
        // Assert
        assertTrue(res == false);
    }

    @Test
    public void testTilecanMergedWithKoMerged(){
        // Arrange
        Tile t1 = new Tile(2);
        Tile t2 = new Tile(2);
        
        // Act
        t1.setMerged(true);
        Boolean res = t1.canMergeWith(t2);
        
        // Assert
        assertTrue(res == false);
    }

    @Test
    public void testTilecanMergedWithKoOtherNull(){
        // Arrange
        Tile t1 = new Tile(2);
        Tile t2 = new Tile(2);
        
        // Act
        Boolean res = t1.canMergeWith(null);
        
        // Assert
        assertTrue(res == false);
    }

    @Test
    public void testTileMergedWithKo(){
        // Arrange
        Tile t1 = new Tile(2);
        Tile t2 = new Tile(4);
        
        // Act
        Integer res = t1.mergeWith(t2);
        
        // Assert
        assertTrue(res == -1);
    }

    @Test
    public void testTileMergedWithValue(){
        // Arrange
        Tile t1 = new Tile(2);
        Tile t2 = new Tile(2);
        
        // Act
        Integer res = t1.mergeWith(t2);
        
        // Assert
        assertTrue(res == 4);
    }

    @Test
    public void testTileMergedWithMerged(){
        // Arrange
        Tile t1 = new Tile(2);
        Tile t2 = new Tile(2);
        
        // Act
        Boolean res = t1.canMergeWith(t2);

        // Assert
        assertTrue(res == true);
    }

    @Test
    public void testTileSetMerged(){
        // Arrange
        Tile t1 = new Tile(2);
        
        // Act
        t1.setMerged(true);
        Boolean res = t1.isMerged();

        // Assert
        assertTrue(res == true);
    }

    @Test
    public void testTileEqualsKoOtherNull(){
        // Arrange
        Tile t1 = new Tile(2);
        Tile t2 = new Tile(2);
        
        // Act
        Boolean res = t1.equals(null);
        
        // Assert
        assertTrue(res == false);
    }

    @Test
    public void testTileEqualsKoDiffClass(){
        // Arrange
        Tile t1 = new Tile(2);
        Tile[][] t2 = new Tile[1][1];
        t2[0][0] = t1;
        Grid g1 = new Grid(t2);
        
        // Act
        Boolean res = t1.equals(g1);
        
        // Assert
        assertTrue(res == false);
    }

    @Test
    public void testTileEqualsKoDiffValue(){
        // Arrange
        Tile t1 = new Tile(2);
        Tile t2 = new Tile(16);
        
        // Act
        Boolean res = t1.equals(t2);
        
        // Assert
        assertTrue(res == false);
    }

    @Test
    public void testTileEqualsOk(){
        // Arrange
        Tile t1 = new Tile(2);
        Tile t2 = new Tile(2);
        
        // Act
        Boolean res = t1.equals(t2);
        
        // Assert
        assertTrue(res == true);
    }

    @Test
    public void testGridGetTileRowCol(){
        //Arrange
        Tile t1 = new Tile(2);
        Tile[][] t2 = new Tile[1][1];
        t2[0][0] = t1;
        Grid g1 = new Grid(t2);

        //Act
        Tile res = g1.getTile(0,0);

        //Assert
        assertTrue(res == t1);
    }

    @Test
    public void testGridGetTilePos(){
        //Arrange
        Tile t1 = new Tile(2);
        Tile[][] t2 = new Tile[1][1];
        t2[0][0] = t1;
        Grid g1 = new Grid(t2);

        //Act
        Tile res = g1.getTile(0);

        //Assert
        assertTrue(res == t1);
    }

    @Test
    public void testGridSetTile(){
        //Arrange
        Tile t1 = new Tile(2);
        Tile t3 = new Tile(2);
        Tile[][] t2 = new Tile[2][2];
        t2[0][0] = t1;
        Grid g1 = new Grid(t2);

        //Act
        g1.setTile(5,t3);
        Tile res = g1.getTile(1,1);

        //Assert
        assertTrue(res == t3);
    }

    @Test
    public void testGridGetLength(){
        //Arrange
        Tile t1 = new Tile(2);
        Tile[][] t2 = new Tile[2][2];
        t2[0][0] = t1;
        Grid g1 = new Grid(t2);

        //Act
        Integer res = g1.getLength();

        //Assert
        assertTrue(res == 2);
    }
}
