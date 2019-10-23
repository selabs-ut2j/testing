import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Random;

import static java.lang.Math.atan;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

// Utilisation de Mockito afin de simuler des échanges de messages entre ma classe testée et des opérations externes utilisées
@RunWith(MockitoJUnitRunner.class)

public class MyPointTest {

    MyPoint point;

    // Attributs nécessaires pour tester les méthodes setPoint(random1, random2) et translate(translation)
    @Mock
    Random random;
    Random random2;
    ITranslation translate;

    // Création d'un point de base
    @Before
    public void setUp() throws Exception{
        point = new MyPoint(2d,4d);
    }

    // Test constructeur 1, sans paramètres
    @Test
    public void testMyPoint(){
        MyPoint point = new MyPoint();
        assertEquals(0d, point.getX());
        assertEquals(0d, point.getY());
    }

    // Test constructeur 2 avec x et y doubles
    @Test
    public void testMyPointConstructeur2(){
        MyPoint point = new MyPoint(2.5,4.6);
        assertEquals(2.5, point.getX());
        assertEquals(4.6, point.getY());
    }

    // Test constructeur 2 avec x qui n'est pas un double et y double
    @Test
    public void testMyPointConstructeur2Bis(){
        MyPoint point = new MyPoint(0.0/0.0,4.6);
        assertEquals(0d, point.getX());
        assertEquals(0d, point.getY());
    }

    // Test constructeur 2 avec x double mais y qui n'est pas un double
    @Test
    public void testMyPointConstructeur2YNaN(){
        MyPoint point = new MyPoint(2d,0.0/0.0);
        assertEquals(0d, point.getX());
        assertEquals(0d, point.getY());
    }

    // Test constructeur 2 avec x et y qui ne sont pas des doubles
    @Test
    public void testMyPointConstructeur2Nan(){
        MyPoint point = new MyPoint(0.0/0.0,0.0/0.0);
        assertEquals(0d, point.getX());
        assertEquals(0d, point.getY());
    }

    // Test constructeur 3 avec paramètre null
    @Test
    public void testMyPointConstructeur3(){
        MyPoint point = null;
        MyPoint pointFinal = new MyPoint(null);
        assertEquals(0d, pointFinal.getX());
        assertEquals(0d, pointFinal.getY());
    }

    // Test constructeur 3 avecpoint passer en paramètre
    @Test
    public void testMyPointConstructeur3Bis(){
        MyPoint point = new MyPoint(2.5,4.6);
        MyPoint pointFinal = new MyPoint(point);
        assertEquals(2.5, point.getX());
        assertEquals(4.6, point.getY());
    }


    @After
    public void tearDown() {
    }

    // Test des accesseurs en écriture avec un double passé en paramètre
    @Test
    public void testSetX() {
        point.setX(3.9);
        assertEquals(3.9, point.getX());
    }

    @Test
    public void testSetY() {
        point.setY(4.7);
        assertEquals(4.7, point.getY());
    }

    // Test des accesseurs en écriture avec une valeur Double.NaN passé en paramètre
    @Test
    public void testSetXDoubleNan() {
        point.setX(Math.sqrt(-1));
        assertEquals(2d, point.getX());
    }

    @Test
    public void testSetYDoubleNaN() {
        point.setY(Math.sqrt(-1));
        assertEquals(4d, point.getY());
    }

    // Test des accesseurs en lecture
    @Test
    public void testGetX() {
        assertEquals(2d, point.getX());
    }

    @Test
    public void testGetY() {
        assertEquals(4d, point.getY());
    }

    // Test de la méthode scale()
    @Test
    public void testScale() {
        point = point.scale(2d);
        assertEquals(4d, point.getX());
        assertEquals(8d, point.getY());
    }

    // Test de la méthode scale() avec un Double NaN passé en paramètre
    @Test
    public void testScaleNaN() {
        point = point.scale(0.0/0.0);
        assertEquals(2d, point.getX());
        assertEquals(4d, point.getY());
    }

    // Test de la méthode horizontalSymmetry()
    @Test
    public void testHorizontalSymmetry() {
        MyPoint pointTemps = new MyPoint(2d, 2d);
        point = point.horizontalSymmetry(pointTemps);
        assertEquals(2d, point.getX());
        assertEquals(4d, point.getY());
    }

    // Test de la méthode horizontalSymmetry() avec un point null passé en paramètre
    @Test(expected = IllegalArgumentException.class)
    public void testHorizontalSymmetryExpected() {
        point = point.horizontalSymmetry(null);
        Assert.fail();
    }

    // Test de la méthode computeAngle(), but : tester la première condition
    @Test
    public void testComputeAngle() {
        MyPoint pointTemps = new MyPoint(2d, 2d);
        double angle = point.computeAngle(pointTemps);
        assertEquals(5.235987755982989, angle);
    }

    // Test de la méthode computeAngle(), but : tester la deuxième condition avec x2 < 0d
    @Test
    public void testComputeAngle2() {
        point.setX(4d);
        point.setY(4d);
        MyPoint pointTemps = new MyPoint(2d, 2d);
        double angle = point.computeAngle(pointTemps);
        assertEquals(Math.PI - atan(2 / -2), angle);
    }

    // Test de la méthode computeAngle(), but : tester la deuxième condition avec x2 > 0d
    @Test
    public void testComputeAngle3() {
        point.setX(0d);
        point.setY(4d);
        MyPoint pointTemps = new MyPoint(2d, 2d);
        double angle = point.computeAngle(pointTemps);
        assertEquals(atan(-2d / 2d), angle);
    }

    // Test de la méthode computeAngle() avec un point null passé en paramètre
    @Test
    public void testComputeAngle3NULL() {
        double angle = point.computeAngle(null);
        assertEquals(Double.NaN, angle);
    }

    // Test de la méthode rotatePoint() avec un point null passé en paramètre
    @Test
    public void testRotatePointNull() {
        point = point.rotatePoint(null, 2.7);
        assertNull(point);
    }

    // Test de la méthode rotatePoint() avec un Double NaN passé en paramètre
    @Test
    public void testRotatePointNaN() {
        MyPoint pointTemp = new MyPoint();
        assertEquals(point, point.rotatePoint(pointTemp, Double.NaN));
    }

    // Test de la méthode rotatePoint()
    @Test
    public void testRotatePoint2() {
        MyPoint pointTemp = new MyPoint();
        assertEquals(2d, point.rotatePoint(pointTemp, 0).getX());
        assertEquals(4d, point.rotatePoint(pointTemp, 0).getY());
    }

    // Test de la méthode rotatePoint() avec Math.PI/2 passé en paramètre
    @Test
    public void testRotatePoint3() {
        MyPoint pointTemp = new MyPoint();
        assertEquals(-4d, point.rotatePoint(pointTemp, Math.PI / 2).getX());
        assertEquals(2d, point.rotatePoint(pointTemp, Math.PI / 2).getY());
    }

    // Test de la méthode rotatePoint() avec -(Math.PI/2) passé en paramètre
    @Test
    public void testRotatePoint4() {
        MyPoint pointTemp = new MyPoint();
        assertEquals(4d, point.rotatePoint(pointTemp, -(Math.PI / 2)).getX());
        assertEquals(-2d, point.rotatePoint(pointTemp, -(Math.PI / 2)).getY());
    }

    // Test de la méthode rotatePoint() avec Math.PI passé en paramètre
    @Test
    public void testRotatePoint5() {
        MyPoint pointTemp = new MyPoint();
        assertEquals(-2d, point.rotatePoint(pointTemp, Math.PI).getX());
        assertEquals(-4d, point.rotatePoint(pointTemp, Math.PI).getY());
    }


    // Test de la méthode rotatePoint() avec 100 passé en paramètre
    @Test
    public void testRotatePoint6() {
        MyPoint pointTemp = new MyPoint();
        assertEquals(Math.cos(100 % (2 * Math.PI)) * 2d - Math.sin(100 % (2 * Math.PI)) * 4d, point.rotatePoint(pointTemp, 100).getX());
        assertEquals(Math.sin(100 % (2 * Math.PI)) * 2d + Math.cos(100 % (2 * Math.PI)) * 4d, point.rotatePoint(pointTemp, 100).getY());
    }


    // Test de la méthode centralSymmetry()
    @Test
    public void testCentralSymmetry1() {
        MyPoint pointTemp = new MyPoint(12.8, 25.6);
        assertEquals(12.8, pointTemp.centralSymmetry(point).getX(), 0.0001);
        assertEquals(25.6, pointTemp.centralSymmetry(point).getY(), 0.0001);
    }

    // Test de la méthode centralSymmetry() avec un point null passé en paramètre
    @Test(expected = IllegalArgumentException.class)
    public void testCentralSymmetryNULL ( ) {
        new MyPoint(1.0,2.0).centralSymmetry(null);
        Assert.fail();
    }

    // Test de la méthode getMiddlePoint()
    @Test
    public void testGetMiddlePoint() {
        MyPoint pointTemps = new MyPoint(2d, 2d);
        MyPoint pt = point.getMiddlePoint(pointTemps);
        assertEquals(2d, pt.getX());
        assertEquals(3d, pt.getY());
    }

    // Test de la méthode getMiddlePoint() avec un point null passé en paramètre
    @Test(expected = IllegalArgumentException.class)
    public void testGetMiddlePointNULL() {
        point.getMiddlePoint(null);
        Assert.fail();
    }

    // Test de la méthode translate(double, double) avec x et y doubles
    @Test
    public void translate() {
        point.translate(6d,8d);
        assertEquals(8d, point.getX());
        assertEquals(12d, point.getY());
    }

    // Test de la méthode translate() avec x qui n'est pas un double et y double
    @Test
    public void translate2() {
        point.translate(0.0/0.0,4d);
        assertEquals(2d, point.getX());
        assertEquals(4d, point.getY());
    }

    // Test de la méthode translate() avec x double et y qui n'est pas un double
    @Test
    public void translate3() {
        point.translate(2d,0.0/0.0);
        assertEquals(2d, point.getX());
        assertEquals(4d, point.getY());
    }

    // Test de la méthode translate() avec x et y qui ne sont pas des doubles
    @Test
    public void translate4() {
        point.translate(0.0/0.0,0.0/0.0);
        assertEquals(2d, point.getX());
        assertEquals(4d, point.getY());
    }

    // Test de la méthode setPoint() avec l'utilisation de mock afin de gérer les retours de la méthode nextDouble()
    @Test
    public void testSetPoint() {
        random = mock(Random.class);
        random2 = mock(Random.class);
        when(random.nextDouble()).thenReturn(2d);
        when(random2.nextDouble()).thenReturn(4d);
        point.setPoint(random, random2);
        assertEquals(2d, point.getX());
        assertEquals(4d, point.getY());
    }

    // Test de la méthode translate(translation) avec l'utilisation de mock afin de gérer les retours des méthodes getX() et getY()
    @Test
    public void testTranslate() {
        translate = mock(ITranslation.class);
        when(translate.getTx()).thenReturn(2d);
        when(translate.getTy()).thenReturn(4d);
        point.translate(translate);
        assertEquals(4d, point.getX());
        assertEquals(8d, point.getY());
    }
}