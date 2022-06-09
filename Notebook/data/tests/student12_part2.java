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
        Tile tile1 = new Tile(4);
        Tile tile2 = null;
        Tile tile3 = new Tile(4);
        Tile tile4 = new Tile(6);
        Tile tile5 = new Tile(8);
        int integer1 = 4;

        
        // Act
       
        // Assert


        ///test sur canMergeWith

        assertTrue(tile1.canMergeWith(tile2)==false);
        //erreur trouvé Quand title 2 est null ne renvoie pas false
        //assertTrue(tile2.canMergeWith(tile1)==false);
        assertTrue(tile1.canMergeWith(tile4)==false);
        //qu'est ce que la fonction est censé renvoyer si ça ne marche pas?
        assertTrue(tile1.canMergeWith(tile3)==true);




        ///test sur mergeWith

        assertTrue(tile1.mergeWith(tile3)==8);
        assertTrue(tile1.mergeWith(tile4)==-1);
        assertTrue(tile4.mergeWith(tile1)==-1);
        assertTrue(tile1.mergeWith(tile2)==-1);
        //erreur lié à celle dans canMergeWith
        //assertTrue(tile2.mergeWith(tile1)==-1);


        Tile t1 = new Tile(4);
        Tile t3 = new Tile(4);
        ///test sur equals
        assertTrue(t1.equals(t3) == true);
        assertTrue(t1.equals(tile5) == false);
        assertTrue(tile1.equals(tile2) == false);
        assertTrue(tile1.equals(integer1) == false);
        //assertTrue(tile1.equals(tile4) == false);





       
    }
}


