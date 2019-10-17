package main.fr.ut2j.m1ice.ootesting;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith (MockitoJUnitRunner.class)
class MyPointTest {

	private MyPoint point;

	@BeforeEach
	void setUp() throws Exception {
		this.point = new MyPoint();
	}

	@Test
	void testConstructor2() {

		Double x = new Double(1), y = new Double(0);
		MyPoint testedValue = new MyPoint(x, y);
		assertEquals(1d, testedValue.getX(), 0.0001);
		assertEquals(0d, testedValue.getY(), 0.0001);

	}

	@Test
	void testConstructor1() {
		MyPoint testedValue = new MyPoint();
		assertEquals(0d, testedValue.getX(), 0.0001);
		assertEquals(0d, testedValue.getY(), 0.0001);
	}

	

	@Test
	void testConstructor3() {
		double x = 2.3, y = 5.3;
		MyPoint point = new MyPoint(x, y);

		MyPoint pointTest = new MyPoint(point);

		assertEquals(x, pointTest.getX(), 0.0001);
		assertEquals(y, pointTest.getY(), 0.0001);
		assertNotSame(point, pointTest);
	}

	@Test
	void testConstructor3Null() {
		MyPoint pointTest = new MyPoint(null);

		assertEquals(0d, pointTest.getX(), 0.0001);
		assertEquals(0d, pointTest.getY(), 0.0001);
		assertNotSame(point, pointTest);
	}

	@Test
	void testGetX() {
		MyPoint point = new MyPoint(5.2, 2.3);
		assertEquals(5.2, point.getX(), 0.0001);
	}

	@Test
	void testSetX() {
		MyPoint point = new MyPoint();
		point.setX(5d);
		assertEquals(5d, point.getX(), 0.0001);
	}

	@Test
	void testGetY() {
		MyPoint point = new MyPoint(5.2, 2.3);
		assertEquals(2.3, point.getY(), 0.0001);
	}

	@Test
	void testSetY() {
		MyPoint point = new MyPoint();
		point.setY(5d);
		assertEquals(5d, point.getY(), 0.0001);
	}

	@Test
	void testSetXNaN() {
		MyPoint point = new MyPoint();
		point.setX(Double.NaN);
		assertFalse(Double.isNaN(point.getX()));
	}

	@Test
	void testSetYNaN() {
		MyPoint point = new MyPoint();
		point.setY(Double.NaN);
		assertFalse(Double.isNaN(point.getY()));
	}

	@Test
	void testScale1() {
		double x = 2.3, y = 5.3;
		MyPoint point = new MyPoint(x, y);

		MyPoint resultPoint = point.scale(5d);

		assertEquals(x * 5d, resultPoint.getX(), 0.0001);
		assertEquals(y * 5d, resultPoint.getY(), 0.0001);

	}	

	void testHorizontalSymmetryException() {
		assertThrows(IllegalArgumentException.class, () -> {
			point.horizontalSymmetry(null);
		});

	}

	@Test
	void testHorizontalSymmetry1() {
		MyPoint origin = new MyPoint(0d, 0d);
		MyPoint resultPoint = point.horizontalSymmetry(origin);

		assertEquals(0d, resultPoint.getX());
		assertEquals(0d, resultPoint.getY());
	}

	@Test
	void testHorizontalSymmetry2() {
		MyPoint testPoint = new MyPoint(3.2, 5d);
		MyPoint origin = new MyPoint(10d, 2d);
		MyPoint resultPoint = testPoint.horizontalSymmetry(origin);

		assertEquals(3.2, resultPoint.getX());
		assertEquals(15d, resultPoint.getY());
	}

	
	@Test
	void testComputeAngleNull() {
		assertTrue(Double.isNaN(point.computeAngle(null)));
	}
	
	@Test
	void testComputeAngle1() {
		MyPoint point1 = new MyPoint(2d,2d);
		Double res;
		
		res =point.computeAngle(point1);
		
		assertEquals(45.0,res,0.1);
	}
	
	@Test
	void testComputeAngle2() {
		MyPoint point1 = new MyPoint();
		Double res;
		
		res =point.computeAngle(point1);
		
		assertEquals(0.0,res,0.1);
	}
	
	@Test
	void testComputeAngle3() {
		MyPoint point1 = new MyPoint(0d,5d);
		Double res;
		
		res =point.computeAngle(point1);
		
		assertEquals(90.0,res,0.1);
	}
	
	@Test
	void testComputeAngle4() {
		MyPoint point1 = new MyPoint(0d,-1);
		Double res;
		
		res = point.computeAngle(point1);
		
		assertEquals(270.0,res,0.1);
	}
	
	@Test
	void testRotatePointNull() {
		assertEquals(null, point.rotatePoint(null, 0d));
	}
	
	@Test
	void testRotatePoint1() {
		point.rotatePoint(point, -1d);
		
		assertEquals(0d, point.getX());
		assertEquals(0d, point.getY());
	}
	
	@Test
	void testRotatePoint2() {
		point.rotatePoint(point, 5d);
		
		assertEquals(0d, point.getX());
		assertEquals(0d, point.getY());
	}
	
	
	@Test
	void testCentralSymmetryNULL() {
		assertThrows(IllegalArgumentException.class, () -> {
			new MyPoint(10, 10).centralSymmetry(null);
		});
	}

	@Test
	void testCentralSymmetry1() {
		point.centralSymmetry(new MyPoint(0d,5d));
		
		assertEquals(0d, point.getX());
		assertEquals(0d, point.getY());
	}
	
	@Test
	void testGetMiddlePoint1() {
		MyPoint res = point.getMiddlePoint(new MyPoint());
		
		assertEquals(0d, res.getX());
		assertEquals(0d, res.getY());
	}
	
	@Test
	void testGetMiddlePoint2() {
		MyPoint res = point.getMiddlePoint(new MyPoint(5d,2d));
		
		assertEquals(2.5, res.getX(),0.1);
		assertEquals(1d, res.getY());
	}
	
	
	@Test
	void testTranslate1() {
		point.translate(0d,5d);
		assertEquals(0d, point.getX());
		assertEquals(5d, point.getY());
	}
	
	@Test
	void testTranslateInterface() {
		
		ITranslation trans = Mockito.mock(ITranslation.class);
		Mockito.when(trans.getTx()).thenReturn(5d);
		Mockito.when(trans.getTy()).thenReturn(1d);
		
		point.translate(trans);
		
		assertEquals(5d, point.getX());
		assertEquals(1d, point.getY());
	}
	
	@Test
	void testTranslateNULL() {
		point.translate(null);
		
		assertEquals(0d, point.getX());
		assertEquals(0d, point.getY());
	}
	
	@Test
	void testSetPoint1() { //TODO
		Random random = Mockito.mock(Random.class);
		Mockito.when((random).nextDouble()).thenReturn(0d);
		
		Random random2 = Mockito.mock(Random.class);
		Mockito.when((random2).nextDouble()).thenReturn(5d);

		point.setPoint(random, random2);
		
		assertEquals(0d, point.getX());
		assertEquals(5d, point.getY());
	}	
	
}
