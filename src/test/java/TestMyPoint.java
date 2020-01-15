
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import testing.ITranslation;
import testing.MyPoint;

import java.util.Random;


@RunWith(MockitoJUnitRunner.class)
class TestMyPoint {



    MyPoint mp1;
    MyPoint mp2;

    @BeforeEach
    void setUp() {
        mp1 = new MyPoint();
        mp2 = new MyPoint(5,10);
    }

    @AfterEach
    void tearDown() {

    }

    @Test
    public void getSetX(){
        mp1.setX(1);
        Assertions.assertEquals(1,mp1.getX());
    }

    @Test
    public void getSetY(){
        mp1.setY(2);
        Assertions.assertEquals(2,mp1.getY());
    }

    @Test
    public void getSetXNan(){
        mp1.setY(Double.NaN);
        Assertions.assertEquals(0,mp1.getX());
    }

    @Test
    public void getSetYNan(){
        mp1.setY(Double.NaN);
        Assertions.assertEquals(0,mp1.getY());
    }


    @Test
    public void myPointConstructor1(){
        Assertions.assertEquals(0,mp1.getX());
        Assertions.assertEquals(0,mp1.getY());
    }

    @Test
    public void myPointConstructor2(){
        Assertions.assertEquals(5,mp2.getX());
        Assertions.assertEquals(10,mp2.getY());
    }

    @Test
    public void myPointConstructor3(){
        MyPoint mpConst3 = new MyPoint(mp2);
        Assertions.assertEquals(5,mpConst3.getX());
        Assertions.assertEquals(10,mpConst3.getY());
    }

    @Test
    public void myPointConstructor3Null(){
        MyPoint mp4 = new MyPoint(null);
        Assertions.assertEquals(0,mp4.getX());
        Assertions.assertEquals(0,mp4.getY());
    }
    @Test
    public void scale(){
        MyPoint newPoint = mp2.scale(3);
        Assertions.assertEquals(15,newPoint.getX());
        Assertions.assertEquals(30,newPoint.getY());
    }

    @Test
    public void horizontalSymmetryCalcul(){
        MyPoint origin = new MyPoint(1,1);
        MyPoint newPoint = mp2.horizontalSymmetry(origin);
        Assertions.assertEquals(5,newPoint.getX());
        Assertions.assertEquals(-8,newPoint.getY());
    }

    @Test
    public void horizontalSymmetryNull(){
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> { mp2.horizontalSymmetry(null);  });
    }


    @Test
    public void computeAngle(){
        MyPoint mp3 = new MyPoint(5,3);
        Assertions.assertEquals(270,mp2.computeAngle(mp3));
    }

    @Test
    public void computeAngleNaN() {
        Assertions.assertEquals(Double.NaN,mp2.computeAngle(null));
    }

    @Test
    public void rotatePointNull(){
        Assertions.assertEquals(null,mp2.rotatePoint(null,100));
    }

    @Test
    public void rotatePointNaN(){
        MyPoint mp3 = new MyPoint(10,5);
        Assertions.assertEquals(null,mp2.rotatePoint(mp3,Double.NaN));
    }

    @Test
    public void rotatePointPositif(){
        MyPoint mp3 = new MyPoint(-9,-1);
        MyPoint newMyPoint = mp2.rotatePoint(mp3,Math.toRadians(90));
        Assertions.assertEquals(-28,newMyPoint.getX());
        Assertions.assertEquals(5,newMyPoint.getY());
    }

    @Test
    public void rotatePointNegatif(){
        MyPoint mp3 = new MyPoint(-9,-1);
        MyPoint newMyPoint = mp2.rotatePoint(mp3,Math.toRadians(-90));
        Assertions.assertEquals(10,newMyPoint.getX());
        Assertions.assertEquals(-23,newMyPoint.getY());
    }
    @Test
    public void rotatePoint0(){
        MyPoint mp3 = new MyPoint(-9,-1);
        MyPoint newMyPoint = mp2.rotatePoint(mp3,Math.toRadians(0));
        Assertions.assertEquals(5,newMyPoint.getX());
        Assertions.assertEquals(10,newMyPoint.getY());
    }
    @Test
    public void rotatePoint450(){
        MyPoint mp3 = new MyPoint(-9,-1);
        MyPoint newMyPoint = mp2.rotatePoint(mp3,Math.toRadians(450));
        Assertions.assertEquals(-28,newMyPoint.getX());
        Assertions.assertEquals(5,newMyPoint.getY());
    }

    @Test
    public void rotatePointOther180(){
        MyPoint mp3 = new MyPoint(-9,-1);
        MyPoint newMyPoint = mp2.rotatePoint(mp3,Math.toRadians(180));
        Assertions.assertEquals(-23,newMyPoint.getX());
        Assertions.assertEquals(-28,newMyPoint.getY());
    }

    @Test
    public void rotatePointOther(){
        MyPoint mp3 = new MyPoint(-9,-1);
        MyPoint newMyPoint = mp2.rotatePoint(mp3,Math.toRadians(250));
        Assertions.assertEquals(4.0658,newMyPoint.getX(),0.0001);
        Assertions.assertEquals(-28.6540,newMyPoint.getY(),0.0001);
    }

    @Test
    public void centralSymmetryNull(){
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> { mp2.centralSymmetry(null);  });
    }

    @Test
    public void testCentralSymmetryNULL(){
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> {
                    new MyPoint(10,20).centralSymmetry(null);
                });
    }

    @Test
    public void centralSymmetry(){
        MyPoint center = new MyPoint(2,3);
        MyPoint newMyPoint = mp2.centralSymmetry(center);
        Assertions.assertEquals(-1,newMyPoint.getX());
        Assertions.assertEquals(-6,newMyPoint.getY());
    }


    @Test
    public void getMiddlePoint(){
        MyPoint mp3 = new MyPoint(2,3);
        MyPoint newMyPoint = mp2.getMiddlePoint(mp3);
        Assertions.assertEquals(3.5,newMyPoint.getX());
        Assertions.assertEquals(6.5,newMyPoint.getY());
    }


    @Test
    public void translate(){
        mp2.translate(3,4);
        Assertions.assertEquals(8,mp2.getX());
        Assertions.assertEquals(14,mp2.getY());
    }


    @Test
    public void translateXNaN(){
        mp2.translate(Double.NaN,4);
        Assertions.assertEquals(5,mp2.getX());
        Assertions.assertEquals(10,mp2.getY());
    }

    @Test
    public void translateYNaN(){
        mp2.translate(3,Double.NaN);
        Assertions.assertEquals(5,mp2.getX());
        Assertions.assertEquals(10,mp2.getY());
    }

    @Test
    public void setPoint(){
        Random random1 = Mockito.mock(Random.class);
        Random random2 = Mockito.mock(Random.class);
        Mockito.when(random1.nextInt()).thenReturn(7);
        Mockito.when(random2.nextInt()).thenReturn(9);
        mp2.setPoint(random1,random2);
        Assertions.assertEquals(7, mp2.getX());
        Assertions.assertEquals(9, mp2.getY());

    }

    @Test
    void testITranslateNull() {
        mp2.translate(null);
        Assertions.assertEquals(5, mp2.getX());
        Assertions.assertEquals(10, mp2.getY());
    }

    @Test
    void testITranslate() {
        ITranslation newMyPoint = Mockito.mock(ITranslation.class);
        Mockito.when(newMyPoint.getTx()).thenReturn(7);
        Mockito.when(newMyPoint.getTy()).thenReturn(9);
        mp2.translate(newMyPoint);
        Assertions.assertEquals(12, mp2.getX());
        Assertions.assertEquals(19, mp2.getY());
    }

}