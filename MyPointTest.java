import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)

public class MyPointTest {

    @Mock
    MyPoint point1;
    MyPoint point2;
    MyPoint pointNull;
    Random r1;
    Random r2;
    ITranslation translate;

    @BeforeEach
    public void setUp() throws Exception {
        point1 = new MyPoint(2,4);
        point2 = new MyPoint();
        pointNull = new MyPoint();

    }

    @Test
    public void testMyPoint1(){
        MyPoint result = new MyPoint();
        assertEquals(0d, result.getX());
        assertEquals(0d, result.getY()); //Add test null et nan...
    }

    @Test
    public void testMyPoint2(){
        MyPoint result = new MyPoint(2,4);
        assertEquals(2, result.getX());
        assertEquals(4, result.getY()); //Add test null et nan...
    }

    @Test
    public  void testMyPoint3(){
        MyPoint point4 = new MyPoint(point1); //Add test null
    }

    @Test
    public void testsetXgetx() {
        MyPoint result = new MyPoint();
        MyPoint result1 = new MyPoint();
        MyPoint result2 = new MyPoint();

        result.setX(5.4);
        result1.setX(-5);
        result2.setX(2048);

        assertEquals(5.4, result.getX());
        assertEquals(-5, result1.getX());
        assertEquals(2048, result2.getX());
    }

    @Test
    public void testsetYgety() {
        MyPoint result = new MyPoint();
        MyPoint result1 = new MyPoint();
        MyPoint result2 = new MyPoint();

        result.setY(5.4);
        result1.setY(-5);
        result2.setY(2048);

        assertEquals(5.4, result.getY());
        assertEquals(-5, result1.getY());
        assertEquals(2048, result2.getY());
    }

    @Test
    public void testsetXdoubleNan() {
        MyPoint result = new MyPoint();

        result.setX(4.5);
        double nan;
        nan = (Math.sqrt(-42));
        result.setX(nan);

        assertEquals(4.5, result.getX());

    }

    @Test
    public void testsetYdoubleNan() {
        MyPoint result = new MyPoint();

        result.setY(4.5);
        double nan;
        nan = (Math.sqrt(-42));
        result.setY(nan);

        assertEquals(4.5, result.getY());
    }

    @Test
    public void TestScale() {

        MyPoint p = new MyPoint();
        MyPoint p12 = new MyPoint(12,5);
        MyPoint p123 = new MyPoint(12,5);

        MyPoint pClone = p.scale(0);
        MyPoint p12Clone = p12.scale(2);
        MyPoint p123Clone = p123.scale(-1);


        assertTrue(pClone.getX() == 0);
        assertTrue(pClone.getY() == 0);
        assertTrue(p12Clone.getX() == 12*2);
        assertTrue(p12Clone.getY() == 5*2);
        assertTrue(p123Clone.getX() == 12*-1);
        assertTrue(p123Clone.getY() == 5*-1);



    }

    @Test
    public void testHorizontalSymmetry() {

        MyPoint p = new MyPoint();
        MyPoint p1 = new MyPoint();
        MyPoint p2 = new MyPoint();
        MyPoint p5 = new MyPoint(-1,5);
        MyPoint p3 = new MyPoint();

        p2 	=  p1.horizontalSymmetry(p1);
        p3 	=  p5.horizontalSymmetry(p5);



        assertTrue(p2.getY() == p1.getY() && p2.getX() == p1.getX());
        assertTrue(p3.getY() == 5 && p3.getX() == -1);

    }

    @Test
    public void testHorizontaSymetryNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            point1 = point1.horizontalSymmetry(null);
        });
    }

    @Test
    public void testCentralSymmetryNULL() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new MyPoint(10, 20).centralSymmetry(null);
        });
    }


    @RunWith(MockitoJUnitRunner.class)
    public void testouille(){

    }

    @Test
    public void computeAngle() {
        //To do
    }

    @Test
    public void rotatePoint() {
        //To do
    }

    @Test
    public void centralSymmetry() {
        //To do
    }

    @Test
    public void getMiddlePoint() {
        //To do
    }

    @Test
    public void translate() {
        //To do
    }

    @Test
    public void testSetPoint() {
        r1 = mock(Random.class);
        r2 = mock(Random.class);
        when(r1.nextDouble()).thenReturn(2d);
        when(r2.nextDouble()).thenReturn(4d);
        point2.setPoint(r1, r2);
        assertEquals(2d, point2.getX());
        assertEquals(4d, point2.getY());
    }

    // Test de la méthode translate(translation) avec l'utilisation de mock afin de gérer les retours des méthodes getX() et getY()
    @Test
    public void testTranslate() {
        translate = mock(ITranslation.class);
        when(translate.getTx()).thenReturn(2d);
        when(translate.getTy()).thenReturn(4d);
        point2.translate(translate);
        assertEquals(4d, point2.getX());
        assertEquals(8d, point2.getY());
    }
}