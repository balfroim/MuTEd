package be.unamur.game2048;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import be.unamur.game2048.controllers.*;
import be.unamur.game2048.helpers.*;
import be.unamur.game2048.models.*;

public class Test2048 {

    /* TILE */

    //TODO should test assert in constructor

    @Test
    public void testTileConstructorOk() {
        Tile t = new Tile(1);
        assertEquals(t.getValue(), 1);
        assertEquals(t.isMerged(), false);
    }

    @Test
    public void testTileConstructorOkBoxedInt() {
        Tile t = new Tile(new Integer(1));
        assertEquals(t.getValue(), 1);
        assertEquals(t.isMerged(), false);
    }

    @Test
    public void testTileConstructorNegativeValue() {
        Tile t = new Tile(-1);
        assertEquals(t.getValue(), 1);
        assertEquals(t.isMerged(), false);
    }

    @Test
    public void testTileCanMergeWithNull() {
        Tile t = new Tile(1);
        assertFalse(t.canMergeWith(null));
    }

    @Test
    public void testTileCanMergeWithTrueFalse() {
        Tile t1 = new Tile(1);
        t1.setMerged(true);
        Tile t2 = new Tile(2);
        assertFalse(t1.canMergeWith(t2));
        assertFalse(t2.canMergeWith(t1));
    }

    @Test
    public void testTileCanMergeWithFalseTrue() {
        Tile t1 = new Tile(1);
        Tile t2 = new Tile(2);
        t2.setMerged(true);
        assertFalse(t1.canMergeWith(t2));
        assertFalse(t2.canMergeWith(t1));
    }

    @Test
    public void testTileCanMergeWithTrueTrue() {
        Tile t1 = new Tile(1);
        t1.setMerged(true);
        Tile t2 = new Tile(2);
        t2.setMerged(true);
        assertFalse(t1.canMergeWith(t2));
        assertFalse(t2.canMergeWith(t1));
    }

    @Test
    public void testTileCanMergeWithFalseFalseDiffValue() {
        Tile t1 = new Tile(1);
        Tile t2 = new Tile(2);
        assertFalse(t1.canMergeWith(t2));
        assertFalse(t2.canMergeWith(t1));
    }

    @Test
    public void testTileCanMergeWithFalseFalseSameValue() {
        Tile t1 = new Tile(1);
        Tile t2 = new Tile(1);
        assertTrue(t1.canMergeWith(t2));
        assertTrue(t2.canMergeWith(t1));
    }

    /* Should pass but...
    @Test
    public void testTileCanMergeWithItself() {
        Tile t1 = new Tile(1);
        assertFalse(t1.canMergeWith(t1));
    }
    */

    //TODO test Tile.mergeWith with each test of Tile.canMergeWith ?

    @Test
    public void testTileMergeWithOk() {
        Tile t1 = new Tile(1);
        Tile t2 = new Tile(1);
        int val = t1.mergeWith(t2);
        assertEquals(val, 2);
        assertEquals(t1.getValue(), 2);
        assertTrue(t1.isMerged());
        assertFalse(t2.isMerged());
    }

    @Test
    public void testTileMergeWithKo() {
        Tile t1 = new Tile(1);
        Tile t2 = new Tile(2);
        int val = t1.mergeWith(t2);
        assertEquals(val, -1);
        assertEquals(t1.getValue(), 1);
        assertFalse(t1.isMerged());
        assertFalse(t2.isMerged());
    }

    @Test
    public void testTileSetMergedTrue() {
        Tile t1 = new Tile(1);
        t1.setMerged(true);
        assertTrue(t1.isMerged());
    }

    @Test
    public void testTileSetMergedFalse() {
        Tile t1 = new Tile(1);
        t1.setMerged(false);
        assertFalse(t1.isMerged());
    }

    @Test
    public void testTileToString() {
        Tile t1 = new Tile(1);
        assertEquals(t1.toString(), "1");
    }

    @Test
    public void testTileEqualsNull() {
        Tile t1 = new Tile(1);
        assertNotEquals(t1, null);
    }

    @Test
    public void testTileEqualsDiffClasses() {
        Tile t1 = new Tile(1);
        String s = "Test";
        assertNotEquals(t1, s);
    }

    @Test
    public void testTileEqualsDiffValues() {
        Tile t1 = new Tile(1);
        Tile t2 = new Tile(2);
        assertNotEquals(t1, t2);
        assertNotEquals(t2, t1);
    }

    @Test
    public void testTileEqualsItself() {
        Tile t1 = new Tile(1);
        assertEquals(t1, t1);
    }    

}
