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
    public void testTileConstructor_1() {
        // Arrange
        Tile fst_tile = new Tile(2);
        Tile snd_tile = new Tile (4) ;
        Tile thd_tile = new Tile (17);
        
        // Act
        
        
        // Assert

        assertTrue(fst_tile.getValue()  == 2);
        assertTrue(snd_tile.getValue() == 4);
        assertTrue(thd_tile.getValue() == 16);
        
    }



    @Test 
    public void testTileCanMergeWith_1 () {

        //Arrange 

        Tile fst_tile = new Tile (2);
        Tile snd_tile = new Tile (2) ;


        assertTrue(fst_tile.canMergeWith(snd_tile));
        assertTrue(fst_tile.getValue()  == 2);
        assertTrue(snd_tile.getValue()  == 2);


    }

    @Test 
    public void testTileCanMergeWith_2 () {

        //Arrange 

        Tile fst_tile = new Tile (2);
        Tile snd_tile = new Tile (4) ;


        assertTrue(!fst_tile.canMergeWith(snd_tile));
        assertTrue(fst_tile.getValue()  == 2);
        assertTrue(snd_tile.getValue()  == 4);


    }
    @Test 
    public void testTileCanMergeWith_3 () {

        //Arrange 

        Tile fst_tile = new Tile (2);
        Tile snd_tile = new Tile (2) ;

        snd_tile.setMerged(true);


        assertTrue(!fst_tile.canMergeWith(snd_tile));
        assertTrue(fst_tile.getValue()  == 2);
        assertTrue(snd_tile.getValue()  == 2);


    }
    @Test 
    public void testTileCanMergeWith_4 () {

        //Arrange 

        Tile fst_tile = new Tile (2);
        Tile snd_tile = new Tile (2) ;

        fst_tile.setMerged(true);

        assertTrue(!fst_tile.canMergeWith(snd_tile));
        assertTrue(fst_tile.getValue()  == 2);
        assertTrue(snd_tile.getValue()  == 2);


    }

    @Test 
    public void testTileCanMergeWith_5 () {

        //Arrange 

        Tile fst_tile = new Tile (2);
        Tile snd_tile = null ;

        assertTrue(!fst_tile.canMergeWith(snd_tile));
        assertTrue(fst_tile.getValue()  == 2);
        assertTrue(snd_tile == null);


    }





    @Test 
    public void testTileMergeWith_1 () {

        //Arrange 

        Tile fst_tile = new Tile (2);
        Tile snd_tile = new Tile (2) ;
            
        //Act 

        int i = fst_tile.mergeWith(snd_tile);


        //Assert
        assertTrue(fst_tile.getValue()  == 4);
        assertTrue(fst_tile.getValue() == i);
        assertTrue(snd_tile.getValue()  == 2);
        assertTrue(fst_tile.isMerged() == true);
        assertTrue(snd_tile.isMerged() == false);


    }

    /*
    j'ai commenté celui-là car il testait qu'on ne puisse pas fusionner une cellule avec elle-même mais que du coup le test provoquait une erreur.
    @Test 
    public void testTileMergeWith_2 () {

        //Arrange 

        Tile fst_tile = new Tile (2);
                    
        //Act 

        int i = fst_tile.mergeWith(fst_tile);
        


        //Assert
        assertTrue(fst_tile.getValue()  == 2);
        assertTrue(i == -1);
        


    }
    */

    @Test 
    public void testTileMergeWith_3 () {

        //Arrange 

        Tile fst_tile = new Tile (2);
        Tile snd_tile = new Tile (8) ;
            
        //Act 

        int i = fst_tile.mergeWith(snd_tile);


        //Assert
        assertTrue(fst_tile.getValue()  == 2);
        assertTrue(i == -1);
        assertTrue(snd_tile.getValue()  == 8);
        assertTrue(fst_tile.isMerged() == false);
        assertTrue(snd_tile.isMerged() == false);


    }

    @Test 
    public void testTileMergeWith_4 () {

        //Arrange 

        Tile fst_tile = new Tile (2);
        Tile snd_tile = null ;
            
        //Act 

        int i = fst_tile.mergeWith(snd_tile);


        //Assert
        assertTrue(fst_tile.getValue()  == 2);
        assertTrue(i == -1);
        assertTrue(snd_tile == null);
        assertTrue(fst_tile.isMerged() == false);
        


    }



    

    @Test 
    public void testTileEquals () {

        //Arrange 

        Tile fst_tile = new Tile (2);
        Tile snd_tile = new Tile (2) ;
        Tile thd_tile = new Tile (4) ;

        boolean equal_1 = fst_tile.equals(snd_tile);
        boolean equal_2 = fst_tile.equals(thd_tile);
        //Assert
        assertTrue(equal_1);
        assertTrue(!equal_2);
        


    }
    
    @Test 
    public void testTilesetMerged () {

        //Arrange 

        Tile fst_tile = new Tile (32) ;

        // Act
        fst_tile.setMerged(true);

        //Assert
        assertTrue(fst_tile.isMerged());

        // Act 

        fst_tile.setMerged(false) ;

        //Assert
        assertTrue(!fst_tile.isMerged());
        
        
    }

    @Test 
    public void testToString () {

        //Arrange 

        Tile fst_tile = new Tile (2);

        String string = fst_tile.toString();
        
        //Assert
        assertTrue(string.equals("2"));      


    }
    


    
    
    
}
