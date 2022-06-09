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
         Tile test2 = new Tile(8);
         // Act
         boolean canBeMerge = test2.canMergeWith(test2);
         test2.canMergeWith(test1);
         test2.setMerged(true);   
         // Assert
         assertTrue(test1.getValue()>=0);
         //assertTrue(test1.)
         assertTrue(test1.isMerged()==false);
         assertTrue(canBeMerge==true);
         assertTrue(test2.isMerged()==true);
     }
    

    @Test 
    public void testTileMergeWith(){
        // Arrange
        Tile test1 = new Tile(4);
        Tile test2 = new Tile(8);
        
        
        //Act
        test1.setMerged(true);
        test2.setMerged(false);
        //Assert
        assertTrue(test1.mergeWith(test1)==-1);
        assertTrue(test2.mergeWith(test2)>0);
        assertTrue(test2.isMerged()==true);
    }


    @Test
    public void testCanMergeWith(){
        
        //Arrange
        Tile test1 = new Tile(4);
        Tile test2 = new Tile(8);
        Tile test3 = new Tile(8);
        //Act
        test1.setMerged(false);
        test2.setMerged(false);

        //Assert
        assertTrue(test3.canMergeWith(null)==false);
        assertTrue(test2.canMergeWith(test2)==true);
        assertTrue(test1.canMergeWith(test2)==false);


    }


  
}
