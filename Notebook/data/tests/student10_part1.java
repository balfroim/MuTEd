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
        
        
        // Act
        
        
        // Assert

        //Tile


        //CHECK SI INTEGER EST CONTENU
        // Java code to show implementation of
        // Guava's Ints.contains() method

        int[] arr = {2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048, 4096};
        


        for(int i = 2; i < 5096; i++){
            Tile tile1 = new Tile(i);
            int target = tile1.getValue();
            boolean present = true;

            for(int j = 0; j < 12; j++){
                if (target == arr[j]){
                    present = true;
                    break;}
                present = false;
            }
            assertTrue(present);
            assertTrue(tile1.isMerged() == false); 
            ;
        } 
        
    }
}

