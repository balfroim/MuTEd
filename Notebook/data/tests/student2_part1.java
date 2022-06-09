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
    public void estTileEquals() {
        // Test for the tile .
        // Arrange
       Tile inputValue1 = new Tile(9);
       Tile inputValue2 = new Tile (6);
       Tile inputValue3 = new Tile (9);

       int getValue1, getValue2;
      
        
        // Act
       getValue1 = inputValue1.mergeWith(inputValue2);
       getValue2 = inputValue3.mergeWith(inputValue1);
        
        // Assert
        assertTrue(getValue1==(-1));
        assertTrue(getValue2 == 18);
        
    };

 
}
