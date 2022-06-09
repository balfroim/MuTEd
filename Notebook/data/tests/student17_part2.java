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
        Tile bloc1 = new Tile(4);
        Tile bloc3 = new Tile(8);
        Tile bloc4 = new Tile(8);
        Tile bloc5 = new Tile(16);
        Grid grid= new Grid(new Tile[4][4]);

        
        // Act
        boolean t1= bloc1.canMergeWith(bloc3);
        boolean t4= bloc3.canMergeWith(bloc4);
        int t3= bloc3.mergeWith(bloc4);
        grid.setTile(4, bloc1);
        grid.setTile(6, bloc3);
        grid.setTile(1, bloc4);
        boolean t5= bloc3.canMergeWith(bloc5);


        // Assert
        assertTrue(t1 == false );
        assertTrue(t4 == true );
        assertTrue(t3 == 16);
        assertTrue(grid.getTile(4) == bloc1);
        assertTrue(t5 == false );
    }
}
