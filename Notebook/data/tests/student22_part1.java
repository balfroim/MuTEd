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
        Tile t1 = new Tile(8);
        Tile t2 = new Tile(8);
        Tile t3 = new Tile(2);
        Tile t4 = new Tile();
        int i = 4;
        // Act
        boolean e1 = t1.equals(t2); 
        boolean e2 = t1.equals(t3);
        boolean e3 = t1.equals(t4);
        boolean e4 = t1.equals(i);
        // Assert
        assertTrue(e1 == True);
        assertTrue(e2 == False);
        assertTrue(e3 == False);
        assertTrue(e4 == False);
    }
}
