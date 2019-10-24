import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class MyPointTest {



    MyPoint myPoint1; //init with constructor 1
    MyPoint myPoint2; // init with constructor 2
    MyPoint myPoint3; // init with constructor 3
    private static final int X = 2;
    private static final int Y = 3;
    @BeforeEach
    void setUp() {
        myPoint1 = new MyPoint();
        myPoint2 = new MyPoint(X,Y);
        myPoint3 = new MyPoint(myPoint2);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void MyPoint1(){
        assertEquals( 0d , myPoint1.getX());
        assertEquals( 0d , myPoint1.getY());
    }

    @Test
    void MyPoint2(){
        assertEquals( X, myPoint2.getX());
        assertEquals( Y , myPoint2.getY());
    }

    @Test
    void MyPoint3(){
        assertEquals( X, myPoint3.getX());
        assertEquals( Y , myPoint3.getY());
        assertNotEquals(myPoint2 , myPoint3);
        assertNotNull(myPoint3);
    }

    @Test
    void MyPoint3Null(){
        MyPoint _point = new MyPoint(null);
        assertEquals(0 , _point.getX());
        assertEquals(0 , _point.getY());
        assertNotNull(_point);
    }

    @Test
    void getSetX() {
        myPoint1.setX(4);
        assertEquals(4 , myPoint1.getX());
    }

    @Test
    void getSetY() {
        myPoint2.setY(6);
        assertEquals(6 ,  myPoint2.getY());
    }

    @Test
    void getSetXNaN() {
        myPoint2.setX(Double.NaN);
        assertEquals(X, myPoint2.getX());
    }

    @Test
    void getSetYNaN() {
        myPoint2.setY(Double.NaN);
        assertEquals(Y, myPoint2.getY());
    }


    @Test
    void scale() {
        MyPoint scaled = myPoint2.scale(3);
        assertEquals(X*3 , scaled.getX());
        assertEquals(Y*3 , scaled.getY());
    }

    @Test
    void horizontalSymmetryWithOrigin() {
        MyPoint flipped = myPoint2.horizontalSymmetry(myPoint1);
        assertEquals(X , myPoint2.getX());
        assertEquals(-Y , flipped.getY());
    }

    @Test
     void horizontalSymmetryWithBottomLeft() {
        MyPoint flipped = myPoint2.horizontalSymmetry(new MyPoint(X-1 , Y-2)); //bottom left
        assertEquals(X , myPoint2.getX());
        assertEquals(-1 , flipped.getY());
    }

    @Test
     void horizontalSymmetryWithBottomRight() {
        MyPoint flipped = myPoint2.horizontalSymmetry(new MyPoint(X+1 , Y-2)); //bottom left
        assertEquals(X , myPoint2.getX());
        assertEquals(-1 , flipped.getY());
    }

    @Test
     void horizontalSymmetryWithUpsideLeft() {
        MyPoint flipped = myPoint2.horizontalSymmetry(new MyPoint(X-1 , Y+2)); //bottom left
        assertEquals(X , myPoint2.getX());
        assertEquals(7 , flipped.getY());
    }

     @Test
     void horizontalSymmetryWithUpsideRight() {
        MyPoint flipped = myPoint2.horizontalSymmetry(new MyPoint(X+4 , Y+2)); //bottom left
        assertEquals(X , myPoint2.getX());
        assertEquals(7 , flipped.getY());
    }

    @Test
    void horizontalSymmetryWithSamePoint() {
        MyPoint flipped = myPoint2.horizontalSymmetry(new MyPoint(X , Y)); //bottom left
        assertEquals(X , myPoint2.getX());
        assertEquals(Y, flipped.getY());
    }

    @Test
     void horizontalSymmetryWithNull() {
        assertThrows(IllegalArgumentException.class , () -> {
            myPoint2.horizontalSymmetry(null);}
        );
    }

    @Test
    void computeAngle() {
        //TODO
    }

    @Test
    void rotatePoint() {
        //TODO
    }


    @Test
    void centralSymmetryNull() {
        assertThrows(IllegalArgumentException.class , () -> {
            myPoint2.centralSymmetry(null);
        });
    }

    @Test
    void getMiddlePoint() {
        MyPoint middle = this.myPoint2.getMiddlePoint(this.myPoint1);
        assertEquals(1 , middle.getX());
        assertEquals(1.5, middle.getY());
    }

    @Test
    void getMiddlePointWithNull (){
        MyPoint nullPoint = null;
        assertThrows(IllegalArgumentException.class , () -> {
            myPoint2.getMiddlePoint(nullPoint);
        });

    }

    @Test
    void translate() {
        this.myPoint1.translate(7 , 12);
        assertEquals( 7 , myPoint1.getX());
        assertEquals( 12, myPoint1.getY());
    }

    @Test
    void setPoint() {
        Random r1 = new Random();
        Random r2 = new Random();
        this.myPoint1.setPoint(r1 , r2);
        assertNotEquals(0 , myPoint1.getX());
        assertNotEquals(0 , myPoint1.getY());
    }

    @Test
    void testTranslate() {
    }

    @Test public void testCentralSymmetryNULL ( ) {
        assertThrows(IllegalArgumentException.class , () -> {
            new MyPoint(1.0, 2.0).centralSymmetry(null);
        });
    }

    @Test
    void setPointWithMockito() {
        Random random1 = Mockito.mock(Random.class);
        Random random2 = Mockito.mock(Random.class);
        Mockito.when(random1.nextInt()).thenReturn(1);
        Mockito.when(random2.nextInt()).thenReturn(2);
        myPoint1.setPoint(random1, random2);
        assertEquals(1, myPoint1.getX());
        assertEquals(2, myPoint1.getY());
    }

    @Mock
    ITranslation translation;

    @Test
    void ITranslationWithMockito() {
        translation = mock(ITranslation.class);
        when(translation.getTx()).thenReturn(8);
        when(translation.getTy()).thenReturn(9);
        myPoint1.translate(translation);
        assertEquals(8, myPoint1.getX());
        assertEquals(9, myPoint1.getY());
    }
}