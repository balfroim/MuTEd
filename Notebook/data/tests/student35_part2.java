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
        Tile t4 = new Tile(4);
        Tile mile[][] = new Tile[10][10];
        
        

        // Act
        t1.mergeWith(t2);
        for(int i=0; i<10; i++){
            for (int j = 0; j<10; j++) {
                mile[i][j]= t1;
            }
        };
        
        // Assert
     
        
        assertFalse(t1.equals(t3));
        assertFalse(t3.equals(t4));
       
        
       



        
    }

    @Test
    public void testTileMerged() {
        // Arrange

        Tile t1 = new Tile(5);
        Tile t2 = new Tile(10);
    
        

        // Act
        t1.mergeWith(t2);
       
        
        // Assert
        assertFalse(t1.isMerged());
        assertFalse(t1.canMergeWith(t2));
        assertFalse(t1.canMergeWith(t2));
        
       
        
       



        
    }
    @Test
    public void testGridd() {
        // Arrange
        Grid mygrid = new Grid();
      
        Tile t1 = new Tile(2);
        
       
    
        

        // Act


        for (int i=0; i<10; i++){
            
            mygrid.setTile(i,t1);
            
            
        };
       
       
        
        // Assert
        assertFalse(mygrid.getTile(0,2).equals(null));
        assertFalse(mygrid.getTile(2).equals(true));
        
       



        
    }
}
