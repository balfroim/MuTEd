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
    public ArrayList testTileEquals() {
        // Arrange
        Tile tile1 = new Tile(2);
        Tile tile2 = new Tile(2);
        Tile tile3 = new Tile(4);
        Tile tile4 = new Tile(4);
        
        // Act

        
        // Assert
        assertTrue(tile1.canMergeWith(tile2));
        assertFalse(tile1.canMergeWith(tile3));

        assertTrue(tile1.mergeWith(tile2) == 4);
        assertTrue(tile1.mergeWith(tile3) == -1);

        assertFalse(tile1.equals(tile2));
        assertTrue(tile1.equals(tile3));
        
        ArrayList<Tile> grid_tiles = new ArrayList<>();
        grid_tiles.add(tile1);
        grid_tiles.add(tile3);

        return grid_tiles;
    }

    public void test_grid(){
        grid_tiles = testTileEquals();
        Grid g = new Grid();


    }
}
