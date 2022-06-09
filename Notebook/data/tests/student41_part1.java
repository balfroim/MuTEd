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
    public void initTile(){
        Tile tuile1 = new Tile(2);
        Tile tuile2 = new Tile(4);

        assertTrue(tuile1.getValue() == 2);
        assertTrue(tuile2 != null);
        assertTrue(tuile1.isMerged() == false);
        assertTrue(tuile1.toString().equals("2"));
        assertTrue(tuile1.mergeWith(tuile2) == -1);
    }

    @Test
    public void testTileEquals() {
        // Arrange
        Tile tuile1 = new Tile(2);
        Tile tuile2 = new Tile(4);

        // Act
        
        
        // Assert
        assertTrue(!tuile1.equals(tuile2));
        assertTrue(!((tuile1.getValue()) == (tuile2.getValue())));
    }

    @Test
    public void testisMerged(){
        Tile tuile = new Tile(2);

        assertTrue(!tuile.isMerged());
    }
    @Test
    public void testcanMergeWith(){
        Tile tuile1 = new Tile(2);
        Tile tuile2 = new Tile(4);

        assertTrue(!tuile1.canMergeWith(tuile2));
    }

            
    @Test
    public void mergeWith(){
        Tile tuile1 = new Tile(2);
        Tile tuile2 = new Tile(2);

        assertTrue(tuile1.mergeWith(tuile2) == 4);
    }

    @Test
    public void intiGrid(){
        Tile[][] bonjourTuile = new Tile[2][2];
        Tile aurevoirTuile = new Tile(2);

        Grid myFuckingGridNword = new Grid(bonjourTuile);      

        myFuckingGridNword.setTile(1, aurevoirTuile);

        assertTrue(myFuckingGridNword.getTile(1,1) == null);
        assertTrue(myFuckingGridNword.getTile(1).getValue() == 2);
        
    }
}
