import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Random;

import org.junit.Before;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.mockito.Mockito;

class TestMyPoint {
	
	
	/**
	 * Utilisation du setUp afin d'instancier l'objet avant tout type de test
	 * */
	@Before
	void setUp() throws Exception {
		//
	}
	
	/**
	 * Les tests suivants répondent à la partie de Test unitaire pour Java avec JUnit
	 * */
	@Test
	void testConstructor1() {
		MyPoint point = new MyPoint();
		assertEquals(0d, point.getX (), 0.0001);
		assertEquals(0d, point.getY (), 0.0001);
	}
	
	@Test
	void testConstructor2() {
		MyPoint point = new MyPoint(0d,0d);
		assertEquals(0d, point.getX (), 0.0001);
		assertEquals(0d, point.getY (), 0.0001);
	}
	
	@Test
	public void testSetX1() throws Exception {
		MyPoint point = new MyPoint(0d,0d);
		point.setX(1d);
		assertEquals(1d, point.getX (), 0.0001);
	}
	
	@Test
	public void testSetY1() throws Exception {
		MyPoint point = new MyPoint(0d,0d);
		point.setY(1d);
		assertEquals(1d, point.getY (), 0.0001);
	}
	
	@Test
	public void testSetX2() throws Exception {
		MyPoint point = new MyPoint(0d,0d);
		point.setX(Double.NaN);
		assertEquals(0d, point.getX (), 0.0001);
		assertNotNull(point);
	}
	
	@Test
	public void testConstructor3() throws Exception {
		MyPoint point = new MyPoint(3d,5d);
		MyPoint pointTest = new MyPoint(point); 
		assertEquals(3d, pointTest.getX (), 0.0001);
		assertEquals(5d, pointTest.getY (), 0.0001);
		assertNotSame(pointTest, point);
	}
	
	@Test
	public void testConstructor3Null() throws Exception {
		MyPoint point = null;
		MyPoint pointTest = new MyPoint(point); 
		assertEquals(0d, pointTest.getX (), 0.0001);
		assertEquals(0d, pointTest.getY (), 0.0001);
		assertNotSame(pointTest, point);
	}
	
	@Test
	public void testScale1() throws Exception {
		MyPoint point = new MyPoint(1d,1d);
		MyPoint point1 = point.scale(3d);
		assertEquals(point.getX()*3d, point1.getX (), 0.0001);
		assertEquals(point.getY()*3d, point1.getY (), 0.0001);
		assertNotSame(point1, point);;
	}
	
	@Test
	public void testHorizontalSymmetry1() throws Exception {
		MyPoint point = new MyPoint();
		MyPoint oriPoint = new MyPoint(2d,2d);
		MyPoint finalPoint = point.horizontalSymmetry(oriPoint);
		assertEquals(2d * oriPoint.getY() - 0d, finalPoint.getY(), 0.0001);
		assertEquals(finalPoint.getX(), finalPoint.getX(), 0.0001);
	}
	
	@Test
	public void testHorizontalSymmetryExcep() throws IllegalArgumentException {
		MyPoint point = new MyPoint();
		MyPoint oriPoint = null;
		assertThrows(IllegalArgumentException.class, () -> { 
			point.horizontalSymmetry(oriPoint);
		});
	}
	
	@Test
	public void testCentralSymmetry1() {
		MyPoint point = new MyPoint();
		MyPoint oriPoint = new MyPoint(5d,5d); 
		assertThrows(IllegalArgumentException.class, () ->{
			MyPoint testPoint = point.centralSymmetry(null);
		});
		assertNotNull(point.centralSymmetry(oriPoint));
	}
	
	@Test
	public void testCentralSymmetryNULL () throws IllegalArgumentException {
	    assertThrows(IllegalArgumentException.class, () -> {
	    	new MyPoint(10,20).centralSymmetry(null);
	    });
	}
	
	@Test
	public void testSetPointRandom() {
		Random r1 = Mockito.mock(Random.class);
		Random r2 = Mockito.mock(Random.class);
		
		Mockito.when(r1.nextDouble()).thenReturn(1d);
		Mockito.when(r2.nextDouble()).thenReturn(2d);
		
		MyPoint point = new MyPoint();
		point.setPoint(r1, r2);
		assertEquals(1d, point.getX(),0.0001);
		assertEquals(2d, point.getY(),0.0001);
	}
	
	@Test
	public void testTranslationPositions() {
		ITranslation t1 = Mockito.mock(ITranslation.class);
		
		Mockito.when(t1.getTx()).thenReturn(1d);
		Mockito.when(t1.getTy()).thenReturn(1d);
		
		MyPoint point = new MyPoint(1d,1d);
		point.translate(t1);
		
		assertEquals(2d, point.getX(),0.0001);
		assertEquals(2d, point.getY(),0.0001);
	}
	
	@Test
	public void testTranslationNAN() {
		ITranslation t1 = Mockito.mock(ITranslation.class);
		
		Mockito.when(t1.getTx()).thenReturn(Double.NaN);
		Mockito.when(t1.getTy()).thenReturn(Double.NaN);
		
		MyPoint point = new MyPoint(2d,2d);
		point.translate(t1);
		
		assertEquals(2d, point.getX(),0.0001);
		assertEquals(2d, point.getY(),0.0001);
	}
	
	@Test
	public void testTranslationNULL() {		
		MyPoint point = new MyPoint(1d,1d);
		point.translate(null);
		
		assertEquals(1d, point.getX(),0.0001);
		assertEquals(1d, point.getY(),0.0001);
	}
	
	@Test
	public void testGetMiddlePoint1() {
		MyPoint point = new MyPoint(7d,4d);
		MyPoint secondPoint = new MyPoint(5d,8d);
		MyPoint testPoint = point.getMiddlePoint(secondPoint);
		
		assertEquals(6d, testPoint.getX(),0.0001);
		assertEquals(6d, testPoint.getY(),0.0001);
	}
	
	@Test
	public void testComputeAngleNAN() {
		MyPoint nullPoint = null;
		MyPoint point = new MyPoint(1d,1d);
		double value = 0d;
		value = point.computeAngle(nullPoint);
		
		assertEquals(Double.NaN, value);
	}
	
	@Test
	public void testComputeAngleDoubleValue() {
		MyPoint point = new MyPoint(1d,3d);
		double value = 0d;
		value = point.computeAngle(new MyPoint(2d,4d));
		
		assertNotEquals(Double.NaN, value);
		assertEquals(45d, value, 0.0001);
	}
	
	@Test
	public void testRotatePointNULL() {
		MyPoint point = new MyPoint(1d,1d);
		MyPoint newPoint = point.rotatePoint(null, 4d);
		
		assertNull(newPoint);
	}
	
	@Test
	public void testRotatePointSameValue() {
		MyPoint point = new MyPoint();
		MyPoint newPoint = point.rotatePoint(point, 4d);
		
		assertEquals(newPoint.getX(), point.getX(), 0.0001);
		assertEquals(newPoint.getY(), point.getY(), 0.0001);
	}
}
