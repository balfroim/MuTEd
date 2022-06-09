package be.unamur.game2048;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

import be.unamur.game2048.controllers.*;
import be.unamur.game2048.helpers.*;
import be.unamur.game2048.models.*;
import jdk.jfr.Timestamp;

public class Test2048 {

    @Test
    public void testTileEquals1() {
        // Arrange
        Tile v1 = new Tile(8);
        Tile v2 = new Tile(8);
        boolean res;
        // Act
        res= v1.equals(v2);
        
        // Assert
        assertTrue(res);
    }

    @Test
    public void testTileEquals2() {
        // Arrange
        Tile v1 = new Tile(4);
        Tile v2 = new Tile(8);
        boolean res;
        // Act
        res= v1.equals(v2);
        
        // Assert
        assertFalse(res);
    }
    
    @Test 
    public void test_getValue(){
        // Arrange
        Tile v = new Tile(8);
        Integer res;    
        //Act
        res=v.getValue();
        //Assert
        assertTrue(res==8);
    }

    @Test 
    public void test_canMergewith1(){
        // Arrange
        Tile v1 = new Tile(8);
        Tile v2 = new Tile(8);
        boolean res;
        //Act
        res=v1.canMergeWith(v2);
        //Assert
        assertTrue(res);
    }
    @Test 
    public void test_canMergewith2(){
        // Arrange
        Tile v1 = new Tile(2);
        Tile v2 = new Tile(8);
        boolean res;
        //Act
        res=v1.canMergeWith(v2);
        //Assert
        assertFalse(res);
    }

    @Test
    public void test_mergeWith1(){
        // Arrange
        Tile v1 = new Tile(2);
        Tile v2 = new Tile(8);
        int res;
        //Act
        res = v1.mergeWith(v2);
        //Assert
        assertTrue(res==-1);
    }

    @Test
    public void test_mergeWith2(){
        // Arrange
        Tile v1 = new Tile(8);
        Tile v2 = new Tile(8);
        int res;
        //Act
        res = v1.mergeWith(v2);
        //Assert
        assertTrue(res == 16);
    }
}
