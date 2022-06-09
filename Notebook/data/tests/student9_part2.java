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
    //@Test
    public void testTileEquals() {
        // Arrange
        Tile t1 = new Tile(4);
        Tile t2 = new Tile(4);

        // Act
        t1.canMergeWith(t2);
        t2.canMergeWith(t1); 
        
        // Assert
        assertTrue(t1.canMergeWith(t2));
        assertTrue(t2.canMergeWith(t1));
    } 

    //@Test
    //public void testTileMergedWith() {
        // Arrange
        //Tile t1 = new Tile(4);
        //Tile t2 = new Tile(4);
        
        //t1.mergeWith(t2);
        //t2.mergeWith(t1);

        //assertTrue(t1.mergeWith(t2));
        //assertTrue(t2.mergeWith(t1));
    }
}

