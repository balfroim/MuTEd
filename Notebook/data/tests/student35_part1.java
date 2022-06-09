package be.unamur.game2048;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.Test.None;

import be.unamur.game2048.controllers.*;
import be.unamur.game2048.helpers.*;
import be.unamur.game2048.models.*;
//import jdk.internal.org.objectweb.asm.tree.analysis.Value;

public class Test2048 {

    @Test
    public void testTileEquals() {
        // Arrange

        Tile t1 = new Tile(5);
        Tile t2 = new Tile(10);
        Tile t3 = new Tile(15);
        Tile t4 = new Tile(15);
        Tile t6 = new Tile(2);
        Tile t5 = new Tile(4);
        Tile mile[][] = new Tile[10][10];
        Grid miles[][] = new Grid[10][10];
        
       

        
        
        // Act
        t1.mergeWith(t2);
        for(int i=0; i<10; i++){
            for (int j = 0; j<10; j++) {
                mile[i][j]= t1;
            }
        }
    
        
        
        
        
        // Assert
        assertFalse(t1.isMerged());
        assertTrue(t1.canMergeWith(t2));
        assertFalse(t1.canMergeWith(t2));
        assertTrue(t3.getValue()==15);
        assertFalse(t1.equals(t3));
        assertTrue(t3.equals(t4));
        assertTrue(t1.toString()=="5");
        
       



        
    }
}
