package main.fr.ut2j.m1ice.ootesting;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Objects;
import java.util.Random;

@RunWith(MockitoJUnitRunner.class)

class MyPointTest {
    private MyPoint p;

    @BeforeEach
    void setUp() {
        p = new MyPoint(1,1);
    }

    @Test
    void testMyPoint1(){
        MyPoint p1 = new MyPoint();
        assertEquals(0,p1.getX());
        assertEquals(0,p1.getY());
        p1.setX(4);
        p1.setY(5);
        assertEquals(4,p1.getX());
        assertEquals(5,p1.getY());
    }

    @Test
    void testMyPoint2(){
        MyPoint p2 = new MyPoint(1,3);
        assertEquals(1,p2.getX());
        assertEquals(3,p2.getY());
        p2.setX(4);
        p2.setY(5);
        assertEquals(4,p2.getX());
        assertEquals(5,p2.getY());
    }

    @Test
    void testMyPoint2NaN(){
        MyPoint p2 = new MyPoint(Double.NaN,Double.NaN);
        assertEquals(0,p2.getX());
        assertEquals(0,p2.getY());
    }

    @Test
    void testSetNaN(){
        p.setX(Double.NaN);
        p.setY(Double.NaN);
        assertFalse(Double.isNaN(p.getX()));
        assertFalse(Double.isNaN(p.getY()));
    }

    @Test
    void testMyPoint3Null(){
        MyPoint p3 = new MyPoint(null);
        assertEquals(0,p3.getX());
        assertEquals(0,p3.getY());
        p3.setX(4);
        p3.setY(5);
        assertEquals(4,p3.getX());
        assertEquals(5,p3.getY());
    }

    @Test
    void testMyPoint3(){
        MyPoint p3 = new MyPoint(new MyPoint(1,2));
        assertEquals(1,p3.getX());
        assertEquals(2,p3.getY());
        p3.setX(4);
        p3.setY(5);
        assertEquals(4,p3.getX());
        assertEquals(5,p3.getY());
    }

    @Test
    void testScale() {
        MyPoint p4 = p.scale(2);
        assertEquals(2,p4.getX());
        assertEquals(2,p4.getY());
    }

    @Test
    void testHorizontalSymmetry() {
        MyPoint p4 = p.horizontalSymmetry(new MyPoint(0,0));
        assertEquals(1,p4.getX());
        assertEquals(-1,p4.getY());
    }

    @Test
    void testHorizontalSymmetryNull(){
        assertThrows(IllegalArgumentException.class, () -> {
                p.horizontalSymmetry(null);
        });
    }

    @Test
    void testComputeAngleSameX(){
        assertEquals(Math.PI/3,p.computeAngle(new MyPoint(1,1)));
    }

    @Test
    void testComputeAngle(){
        assertEquals(Math.PI/3,p.computeAngle(new MyPoint(1,1)));
    }

    @Test
    void testRotatePointPositive(){
        MyPoint p4 = p.rotatePoint(new MyPoint(0,0),Math.PI/2);
        assertEquals(-1,p4.getX());
        assertEquals(1,p4.getY());
    }

    @Test
    void testRotatePointNegative(){
        MyPoint p4 = p.rotatePoint(new MyPoint(0,0),-(Math.PI/2));
        assertEquals(1,p4.getX());
        assertEquals(-1,p4.getY());
    }

    @Test
    void testRotatePointZero(){
        MyPoint p4 = p.rotatePoint(new MyPoint(0,0),0);
        assertEquals(1,p4.getX());
        assertEquals(1,p4.getY());
    }

    @Test
    void testRotatePointPI(){
        MyPoint p4 = p.rotatePoint(new MyPoint(0,0), Math.PI);
        assertEquals(-1,p4.getX());
        assertEquals(-1,p4.getY());
    }

    @Test
    void testRotatePointRandom(){
        MyPoint p4 = p.rotatePoint(new MyPoint(0,0), Math.PI/5);
        assertEquals(0.22123174208247431,p4.getX());
        assertEquals(1.3968022466674206,p4.getY());
    }

    @Test
    void testRotatePointNull() {
        MyPoint p4 = p.rotatePoint(null, 1.5707963267949);
        assertTrue(Objects.isNull(p4));
    }

    @Test
    void testTranslateSuccess(){
        p.translate(1,2);
        assertEquals(2,p.getX());
        assertEquals(3,p.getY());
    }

    @Test
    void testTranslateFail(){
        p.translate(Double.NaN,Double.NaN);
        assertEquals(1,p.getX());
        assertEquals(1,p.getY());
    }

    @Test
    void testGetMiddlePoint(){
        MyPoint p4 = p.getMiddlePoint(new MyPoint(3,3));
        assertEquals(2,p4.getX());
        assertEquals(2,p4.getY());
    }

    @Test
    void testCentralSymmetryNULL ( ) {
        assertThrows(IllegalArgumentException.class, () -> {
            new MyPoint(1.0,2.0).centralSymmetry(null);
        });
    }

    @Test
    void testCentralSymmetry( ) {
        MyPoint p4 = p.centralSymmetry(new MyPoint(0,0));
        assertEquals(-1,p4.getX());
        assertEquals(-1,p4.getY());
    }

    @Mock
    Random random1;

    @Mock
    Random random2;

    @Test
    void testSetPoint(){
        random1 = mock(Random.class);
        random2 = mock(Random.class);
        when(random1.nextInt()).thenReturn(1);
        when(random2.nextInt()).thenReturn(2);
        p.setPoint(random1, random2);
        assertEquals(1d, p.getX());
        assertEquals(2d, p.getY());
    }

    @Mock
    ITranslation t;

    @Test
    void testTranslateVector(){
        t = mock(ITranslation.class);
        when(t.getTx()).thenReturn(2);
        when(t.getTy()).thenReturn(2);
        p.translate(t);
        assertEquals(3,p.getX());
        assertEquals(3,p.getY());
    }
}