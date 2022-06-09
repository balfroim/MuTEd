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
        Tile test_tile = new Tile(8);
        Tile same_tile = new Tile(8);
        Tile different_tile = new Tile(2048);
        Tile unvalid_tile = new Tile(7); 
        // Act
        
        
        // Assert
        assertTrue(test_tile.equals(same_tile)); // equal case
        
        
        assertTrue(!(test_tile.equals(different_tile))); // not equal case
        assertTrue((test_tile.equals(unvalid_tile)));


    }
    @Test
    public void testTile(){
        Tile test_tile = new Tile(2);
        Tile invalid_tile = new Tile(3123);
        
        // Act

        // Assert

        assertTrue(test_tile.getValue() == 2 );
        assertTrue(((invalid_tile.getValue()) % 2 == 0));

    }
    @Test
    public void testTileCanMergeWith(){
        // Arrange
        Tile test_tile = new Tile(2);
        Tile mergeable_tile = new Tile(2);

        Tile different_tile = new Tile(8);
        Tile already_merged_tile = new Tile(2);


        // Act
        already_merged_tile.setMerged(true);

        // Assert
        assertTrue(test_tile.canMergeWith(mergeable_tile));
        assertTrue(!(test_tile.canMergeWith(different_tile)));
        assertTrue(!(test_tile.canMergeWith(already_merged_tile)));

        assertTrue(!test_tile.canMergeWith(null)); // null case

    }
    public void TestMergeWith(){
        // Arrange
        Tile test_tile = new Tile(2);
        Tile mergeable_tile = new Tile(2);
        Tile unmergeable_tile = new Tile(8);

        // Act

        // Assert
        assertTrue(test_tile.mergeWith(mergeable_tile) == (test_tile.getValue() + mergeable_tile.getValue()));

        assertTrue(test_tile.mergeWith(unmergeable_tile) == -1);
    }
}
