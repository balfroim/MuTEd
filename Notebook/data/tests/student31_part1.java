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
        Tile ob = new Tile(2);
        Tile ab = new Tile(2);
        Object bb = new Object();
        
        // Act
        boolean equals = ob.equals(ab);
        boolean nequals = ob.equals(bb);
        // Assert
        assertTrue(equals);
        assertFalse(nequals);
        assertFalse(ob == null);
        assertFalse(ab == null);
    }

  
}
