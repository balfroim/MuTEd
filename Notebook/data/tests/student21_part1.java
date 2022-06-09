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
        Tile test1 = new Tile(4);
        Tile test2 = new Tile(6);
        // Act
        boolean cannotBeMerge = test2.canMergeWith(test2);
        test2.canMergeWith(test1);
        //test2.setMerged()=true;
        
        // Assert
        assertTrue(test1.isMerged()==false);
        assertTrue(cannotBeMerge==false);
        assertTrue(test2.isMerged()==false);
    }



@Test
public void GridEquals(){

    //Arrange
    Grid test1 = new Grid();
    // Act
    // Assert 
    assertTrue(test1.getLength()>=0);

}

}
