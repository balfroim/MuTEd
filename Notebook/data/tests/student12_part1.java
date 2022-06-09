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

        
        // Act
       
        // Assert


        ///canMergeWith
        assertTrue(tile1.canMergeWith(tile2)==false);
        //erreur trouvé Qd title 2 est null ne renvoie pas false
        //assertTrue(tile2.canMergeWith(tile1)==false);
        assertTrue(tile1.canMergeWith(tile4)==false);
        //qu'est ce que la fonction est censé renvoyer si ça ne marche pas?
        assertTrue(tile1.canMergeWith(tile3)==true);

        ///mergeWith






       
    }
}


