package be.unamur.game2048;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Action;

import org.junit.Test;

import be.unamur.game2048.controllers.*;
import be.unamur.game2048.helpers.*;
import be.unamur.game2048.models.*;
import junit.framework.Assert;

public class Test2048 {

    //---Tile---


    @Test
    public void testTileEquals() {
        // Arrange
        Tile t1 = new Tile(5);
        Tile t2 = t1;
        Tile t3 = new Tile(10);
        Tile t4 = null;
        Grid g1 = new Grid();
        
        // Act
        boolean val1 = t1.equals(t2);
        boolean val2 = t1.equals(t3);
        boolean val3 = t1.equals(t4);
        boolean val4 = t1.equals(g1);

        // Assert
        assertTrue(val1);
        assertFalse(val2);
        assertFalse(val3);
        assertFalse(val4);

    }

    @Test
    public void testGetNearestPower2(){
        //Arrange
        Tile t1;
        Tile t2;
        Tile t3;
        Tile t4;
        Tile t5;


        //Act
        t1 = new Tile(3);
        t2 = new Tile(5);
        t3 = new Tile(127);
        t4 = new Tile(2048);
        t5 = new Tile(4097);
        

        //Assert
        assertTrue(t1.getValue()==4);
        assertTrue(t2.getValue()==4);
        assertTrue(t3.getValue()==128);
        assertTrue(t4.getValue()==2048);
        assertTrue(t5.getValue()==4096);
    }

    @Test
    public void testMergeWith(){

        //Arrange
        Tile t1 = new Tile(4);
        Tile t2 = new Tile(4);
        Tile t3 = new Tile(2);
        Tile t4 = new Tile(8);

        //Act
        int val1 = t1.mergeWith(t3);
        int val2 = t3.mergeWith(t1);
        int val3 = t1.mergeWith(t2);
        int val4 = t1.mergeWith(t2);
        int val5 = t1.mergeWith(t4);

        //Assert
        assertTrue(val1==-1);
        assertTrue(val2==-1);
        assertTrue(val3==8);
        assertTrue(t1.isMerged());
        assertTrue(val4==-1);
        assertTrue(val5==-1);
    }

    @Test
    public void testCanMergeWith(){
        //Arrange
        Tile t1 = new Tile(2);
        Tile t2 = new Tile(2);
        Tile t3 = new Tile(4);
        Tile t4 = new Tile(4);
        t4.setMerged(true);
        Tile t5 = null;

        //Act
        boolean val1 = t1.canMergeWith(t2);
        boolean val2 = t1.canMergeWith(t3);
        boolean val3 = t1.canMergeWith(t4);
        boolean val4 = t3.canMergeWith(t4);
        boolean val5 = t1.canMergeWith(t5);

        //Assert
        assertTrue(val1);
        assertTrue(!val2);
        assertTrue(!val3);
        assertTrue(!val4);
        assertTrue(!val5);
    }

    @Test
    public void testIsMerged(){
        //Arrange
        Tile t1 = new Tile(2);
        Tile t2 = new Tile(4);
        t2.setMerged(true);

        //Act
        boolean b1 = t1.isMerged();
        boolean b2 = t2.isMerged();

        //Assert
        assertFalse(b1);
        assertTrue(b2);

    }

    @Test
    public void testToString(){
        //Arrange
        Tile t1 = new Tile(2);
    

        //Act
        String s1 = t1.toString();

        //Assert
        assertTrue(Integer.parseInt(s1)== t1.getValue());
    }

    //---Grid---

    @Test
    public void testGetTile(){
        //Arrange
        Tile[][] tiles = new Tile[4][4];
        Tile t1 = new Tile(2);
        Tile t2 = new Tile(2);
        Tile t3 = new Tile(2);
        tiles [0][0] = t1;
        tiles [0][1] = t2;
        tiles [3][3] = t3;
        Grid g2 = new Grid(tiles);

        

        //Act
        
        Tile test2 = g2.getTile(0,0);
        Tile test3 = g2.getTile(0,1);

        //Assert
        assertTrue(test2.equals(t1));
        assertTrue(test3.equals(t2));
    }
}
