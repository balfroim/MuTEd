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
    public void testmergeWith() {
        // Arrange
        Tile t1 = new Tile(2);
        Tile t2 = new Tile(2);
        Tile t3 = new Tile(2);
        Tile t4 = new Tile(2);
               
        
        // Act
        t1.mergeWith(t2);
        t3.mergeWith(t1);
        t4.mergeWith(t4);
        
        // Assert
        assertTrue(t1.getValue() == 4);
        assertTrue(t3.getValue() == 2);
        assertTrue(t4.getValue() == 4);
        
        
    }

    
}
