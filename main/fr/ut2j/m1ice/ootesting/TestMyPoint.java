package main.fr.ut2j.m1ice.ootesting;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;


import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(org.mockito.runners.MockitoJUnitRunner.class)

class TestMyPoint {
    private MyPoint myPoint0;
    private MyPoint myPoint3264;

    @BeforeEach
    void setUp() {
        myPoint0 = new MyPoint();
        myPoint3264 = new MyPoint(3.2, 6.4);
    }

    @Test
    void testMyPoint1() {
        MyPoint point = new MyPoint();
        assertEquals(0, point.getX(), 0.0001);
        assertEquals(0, point.getY(), 0.0001);
    }

    @Test
    void testMyPoint2() {
        MyPoint point = new MyPoint(Double.MAX_VALUE, Double.MAX_VALUE);
        assertEquals(Double.MAX_VALUE, point.getX(), 0.0001);
        assertEquals(Double.MAX_VALUE, point.getY(), 0.0001);
    }

    @Test
    void testMyPoint2Nan() {
        MyPoint point = new MyPoint(Double.NaN, Double.NaN);
        assertEquals(0, point.getX(), 0.0001);
        assertEquals(0, point.getY(), 0.0001);
    }

    @Test
    void testMyPoint3() {
        MyPoint point = new MyPoint(myPoint3264);
        assertEquals(3.2, point.getX(), 0.0001);
        assertEquals(6.4, point.getY(), 0.0001);
    }

    @Test
    void testMyPoint3Null() {
        MyPoint point = new MyPoint(null);
        assertEquals(0, point.getX(), 0.0001);
        assertEquals(0, point.getY(), 0.0001);
    }

    @Test
    void testSetX1() {
        myPoint0.setX(1);
        assertEquals(1, myPoint0.getX(), 0.0001);
    }

    @Test
    void testSetX2() {
        myPoint0.setX(Double.MIN_VALUE);
        assertEquals(Double.MIN_VALUE, myPoint0.getX(), 0.0001);
    }

    @Test
    void testSetX3() {
        myPoint0.setX(Double.MAX_VALUE);
        assertEquals(Double.MAX_VALUE, myPoint0.getX(), 0.0001);
    }

    @Test
    void testSetXNaN() {
        myPoint3264.setX(Double.NaN);
        assertEquals(3.2, myPoint3264.getX(), 0.0001);
    }

    @Test
    void testSetY1() {
        myPoint0.setY(1);
        assertEquals(1, myPoint0.getY(), 0.0001);
    }

    @Test
    void testSetY2() {
        myPoint0.setY(Double.MIN_VALUE);
        assertEquals(Double.MIN_VALUE, myPoint0.getY(), 0.0001);
    }

    @Test
    void testSetY3() {
        myPoint0.setY(Double.MAX_VALUE);
        assertEquals(Double.MAX_VALUE, myPoint0.getY(), 0.0001);
    }

    @Test
    void testSetYNaN() {
        myPoint3264.setY(Double.NaN);
        assertEquals(6.4, myPoint3264.getY(), 0.0001);
    }

    @Test
    void testScale1() {
        MyPoint point = myPoint3264.scale(0);
        assertEquals(0, point.getX(), 0.0001);
        assertEquals(0, point.getY(), 0.0001);
    }

    @Test
    void testScale2() {
        MyPoint point = myPoint3264.scale(Double.MAX_VALUE);
        assertEquals(Double.POSITIVE_INFINITY, point.getX(), 0.0001);
        assertEquals(Double.POSITIVE_INFINITY, point.getY(), 0.0001);
    }

    @Test
    void testScale3() {
        MyPoint point = myPoint3264.scale(Double.MIN_VALUE);
        assertEquals(Double.MIN_VALUE, point.getX(), 0.0001);
        assertEquals(Double.MIN_VALUE, point.getY(), 0.0001);
    }

    @Test
    void testScale4() {
        MyPoint point = myPoint3264.scale(2);
        assertEquals(myPoint3264.getX() * 2, point.getX(), 0.0001);
        assertEquals(myPoint3264.getY() * 2, point.getY(), 0.0001);
    }

    @Test
    void testScaleNan() {
        MyPoint point = myPoint3264.scale(Double.NaN);
        assertEquals(3.2, point.getX(), 0.0001);
        assertEquals(6.4, point.getY(), 0.0001);
    }

    @Test
    void testHorizontalSymmetryNull() {
        assertThrows(IllegalArgumentException.class, () -> myPoint0.horizontalSymmetry(null));
    }

    @Test
    void testHorizontalSymmetry1() {
        MyPoint point = myPoint0.horizontalSymmetry(myPoint0);
        assertEquals(0, point.getX());
        assertEquals(0, point.getY());
    }

    @Test
    void testHorizontalSymmetry2() {
        MyPoint point = myPoint3264.horizontalSymmetry(myPoint0);
        assertEquals(3.2, point.getX());
        assertEquals(-6.4, point.getY());
    }

    @Test
    void testComputeAngleNull() {
        assertEquals(Double.NaN, myPoint3264.computeAngle(null));
    }

    @Test
    void testComputeAngle1() {
        assertEquals(Math.PI / 3, myPoint0.computeAngle(myPoint0));
    }

    @Test
    void testComputeAngle2() {
        MyPoint point = new MyPoint(0, 3);
        assertEquals(Math.PI * 2 - Math.PI / 3, point.computeAngle(myPoint0));
    }

    @Test
    void testComputeAngle3() {
        assertEquals(Math.atan(myPoint3264.getY() / myPoint3264.getX()), myPoint0.computeAngle(myPoint3264));
    }

    @Test
    void testComputeAngle4() {
        MyPoint point = myPoint3264.scale(-2);
        assertEquals(Math.PI - Math.atan(-point.getY() / point.getX()), myPoint3264.computeAngle(point));
    }

    @Test
    void testRotatePointNull() {
        assertNull(myPoint3264.rotatePoint(null, 0));
    }

    @Test
    void testRotatePointNaN() {
        assertEquals(myPoint3264, myPoint3264.rotatePoint(myPoint0, Double.NaN));
    }

    @Test
    void testRotatePoint2() {
        assertEquals(3.2, myPoint3264.rotatePoint(myPoint0, 0).getX());
        assertEquals(6.4, myPoint3264.rotatePoint(myPoint0, 0).getY());
    }

    @Test
    void testRotatePoint3() {
        assertEquals(-6.4, myPoint3264.rotatePoint(myPoint0, Math.PI / 2).getX());
        assertEquals(3.2, myPoint3264.rotatePoint(myPoint0, Math.PI / 2).getY());
    }

    @Test
    void testRotatePoint4() {
        assertEquals(6.4, myPoint3264.rotatePoint(myPoint0, -(Math.PI / 2)).getX());
        assertEquals(-3.2, myPoint3264.rotatePoint(myPoint0, -(Math.PI / 2)).getY());
    }

    @Test
    void testRotatePoint5() {
        assertEquals(-3.2, myPoint3264.rotatePoint(myPoint0, Math.PI).getX());
        assertEquals(-6.4, myPoint3264.rotatePoint(myPoint0, Math.PI).getY());
    }


    @Test
    void testRotatePoint6() {
        assertEquals(Math.cos(100 % (2 * Math.PI)) * 3.2 - Math.sin(100 % (2 * Math.PI)) * 6.4, myPoint3264.rotatePoint(myPoint0, 100).getX());
        assertEquals(Math.sin(100 % (2 * Math.PI)) * 3.2 + Math.cos(100 % (2 * Math.PI)) * 6.4, myPoint3264.rotatePoint(myPoint0, 100).getY());
    }


    @Test
    void testCentralSymmetryNull() {
        assertThrows(IllegalArgumentException.class, () -> myPoint0.centralSymmetry(null));
    }

    @Test
    void testCentralSymmetry1() {
        MyPoint point = new MyPoint(12.8, 25.6);
        assertEquals(-6.4, point.centralSymmetry(myPoint3264).getX(), 0.0001);
        assertEquals(-19.2, point.centralSymmetry(myPoint3264).getY(), 0.0001);
    }

    @Test
    void testGetMiddlePointNull() {
        assertNull(myPoint0.getMiddlePoint(null));
    }

    @Test
    void testGetMiddlePoint() {
        assertEquals(1.6, myPoint0.getMiddlePoint(myPoint3264).getX());
        assertEquals(3.2, myPoint0.getMiddlePoint(myPoint3264).getY());
    }

    @Test
    void testTranslateNaN1() {
        MyPoint point = new MyPoint(myPoint3264);
        point.translate(Double.NaN, 3.2);
        assertEquals(myPoint3264.getX(), point.getX());
        assertEquals(myPoint3264.getY(), point.getY());
    }

    @Test
    void testTranslateNaN2() {
        MyPoint point = new MyPoint(myPoint3264);
        point.translate(1.6, Double.NaN);
        assertEquals(myPoint3264.getX(), point.getX());
        assertEquals(myPoint3264.getY(), point.getY());
    }

    @Test
    void testTranslateNaN3() {
        MyPoint point = new MyPoint(myPoint3264);
        point.translate(Double.NaN, Double.NaN);
        assertEquals(myPoint3264.getX(), point.getX());
        assertEquals(myPoint3264.getY(), point.getY());
    }

    @Test
    void testTranslateNull() {
        myPoint0.translate(null);
        assertEquals(0, myPoint0.getX());
        assertEquals(0, myPoint0.getY());
    }

    @Test
    void testTranslate1() {
        myPoint3264.translate(3.2, -3.2);
        assertEquals(6.4, myPoint3264.getX());
        assertEquals(3.2, myPoint3264.getY());
    }

    @Test
    void testTranslate2() {
        myPoint3264.translate(Double.MAX_VALUE, Double.MAX_VALUE);
        assertEquals(Double.MAX_VALUE, myPoint3264.getX());
        assertEquals(Double.MAX_VALUE, myPoint3264.getY());
    }

    @Test
    void testTranslate3() {
        myPoint0.translate(Double.MIN_VALUE, Double.MIN_VALUE);
        assertEquals(Double.MIN_VALUE, myPoint0.getX());
        assertEquals(Double.MIN_VALUE, myPoint0.getY());
    }

    @Test
    void testTranslate4() {
        ITranslation translation = new ITranslation() {
            @Override
            public double getTx() {
                return 3.2;
            }

            @Override
            public double getTy() {
                return 6.4;
            }
        };
        myPoint3264.translate(translation);
        assertEquals(6.4, myPoint3264.getX());
        assertEquals(12.8, myPoint3264.getY());
    }

    @Test
    void testSetPoint1() {
        Random random = new Random();
        random.setSeed(2);
        myPoint0.setPoint(random, random);
        assertEquals(0.73114693, myPoint0.getX(), 0.00000001);
        assertEquals(0.90144762, myPoint0.getY(), 0.00000001);
    }

    @Mock
    Random random1;
    @Mock
    Random random2;

    @Test
    void testSetPointMockito() {
        random1 = mock(Random.class);
        random2 = mock(Random.class);
        when(random1.nextDouble()).thenReturn(1d);
        when(random2.nextDouble()).thenReturn(2d);
        myPoint0.setPoint(random1, random2);
        assertEquals(1d, myPoint0.getX());
        assertEquals(2d, myPoint0.getY());
    }

    @Mock
    ITranslation translation;

    @Test
    void testITranslation() {
        translation = mock(ITranslation.class);
        when(translation.getTx()).thenReturn(-1.6);
        when(translation.getTy()).thenReturn(-3.2);
        myPoint3264.translate(translation);
        assertEquals(1.6, myPoint3264.getX());
        assertEquals(3.2, myPoint3264.getY());
    }

}