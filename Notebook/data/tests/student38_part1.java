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
        for (int i = 0; i < 10000; i++) {
            // Arrange
            Tile tile1 = new Tile(i);
            Tile tile2 = new Tile(i);

            // Act

            // Assert
            assertTrue(tile1.equals(tile2));
        }
    }
}
