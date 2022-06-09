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
        
        // Act
        t1.equals(t2);
        
        // Assert
        assertTrue(t1.equals(t2));
        //assertFalse(t2.equals(t1));
    }

    public void TileToString(){
        // Arrange
        Tile t3 = new Tile(4);

        // Act
        t3.toString();

        // Assert
        assertTrue((t3.toString() instanceof java.lang.String));
    }

}
