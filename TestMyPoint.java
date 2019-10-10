//Made by Thomas COLETTE
package main.fr.ut2j.m1ice.ootesting;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Random;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestMyPoint 
{
	//Default Point(0, 0)
	private MyPoint point;
	
	@BeforeEach
	void setUp() throws Exception 
	{
		point = new MyPoint();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	//Test if a new point is set with 0 values
	//A bit useless (only affectations)
	@Test
	void testMyPoint() 
	{
		//MyPoint point = new MyPoint();
		assertEquals (0d, point.getX(), 0.0001);
		assertEquals (0d, point.getY(), 0.0001);
	}

	//Test if a new point set by doubles has the correct values
	//A bit useless (only affectations)
	@Test
	void testMyPointDoubleDouble() 
	{
		MyPoint point = new MyPoint(5.0d, 3.2d);
		assertEquals (5.0d, point.getX(), 0.0001);
		assertEquals (3.2d, point.getY(), 0.0001);
	}

	//Test if a new point created by an existing point has the correct values
	@Test
	void testMyPointMyPoint() 
	{
		MyPoint point = new MyPoint(5.0d, 3.2d);
		MyPoint testPoint = new MyPoint(point);
		assertEquals (testPoint.getX(), point.getX(), 0.0001);
		assertEquals (testPoint.getY(), point.getY(), 0.0001);
	}
	
	//Test if a new point created with a null point is set to (0,0)
	@Test
	void testMyPointNullMyPoint() 
	{
		MyPoint testPoint = new MyPoint(null);
		assertEquals (testPoint.getX(), 0d, 0.0001);
		assertEquals (testPoint.getY(), 0d, 0.0001);
	}

	//Test if the x getter and setter work properly
	@Test
	void testGetSetX() 
	{
		//MyPoint point = new MyPoint();
		double expectedX = 6.7d;
		point.setX(expectedX);
		assertEquals(expectedX, point.getX(), 0.0001);
	}

	//Test if the y getter and setter work properly
	@Test
	void testGetSetY() 
	{
		//MyPoint point = new MyPoint();
		double expectedY = 5.4d;
		point.setY(expectedY);
		assertEquals(expectedY, point.getY(), 0.0001);
	}
	
	//Test if setting a NaN value to x doesn't change the current value
	@Test
	void testSetXNaN() 
	{
		//MyPoint point = new MyPoint();
		point.setX(Double.NaN);
		assertEquals(0, point.getX(), 0.0001);
	}
	
	//Test if setting a NaN value to y doesn't change the current value
	@Test
	void testSetYNaN() 
	{
		//MyPoint point = new MyPoint();
		point.setY(Double.NaN);
		assertEquals(0, point.getY(), 0.0001);
	}


	//Test if the scale method multiplies the point coordinated by a factor
	@Test
	void testScale() 
	{
		MyPoint point = new MyPoint(4.0d, 5.0d);
		double scaleFactor = 2.0d;
		point = point.scale(scaleFactor);
		assertEquals (8d, point.getX(), 0.0001);
		assertEquals (10d, point.getY(), 0.0001);
	}

	//Test if an exception is launched when the HorizontalSymmetry parameter is null
	@Test
	void testHorizontalSymmetryNull() 
	{
		assertThrows(IllegalArgumentException.class, () -> point.horizontalSymmetry(null));
	}
	
	//Test if the HorizontalSymmetry method works properly
	@Test
	void testHorizontalSymmetry() 
	{
		MyPoint result;
		MyPoint toFlip = new MyPoint(1.0d, 1.0d);
		result = toFlip.horizontalSymmetry(point);
		assertEquals (1d, result.getX(), 0.0001);
		assertEquals (-1d, result.getY(), 0.0001);				
	}
	
	//Test ComputeAngle if X coordinate equals 0
	@Test public void testComputeAngleXZero()
	{
		MyPoint testPoint = new MyPoint(0d, 1d);
		double actual = point.computeAngle(testPoint);
		assertEquals(Math.PI / 2d, actual, 0.0001);
	}
	
	//Test ComputeAngle if X coordinate equals 0 and y coordinate is negative
	@Test public void testComputeAngleXZeroYNegative()
	{
		MyPoint testPoint = new MyPoint(0, -1);
		double actual = point.computeAngle(testPoint);
		assertEquals(3d * (Math.PI / 2d), actual, 0.0001);
	}
	
	//Test ComputeAngle if X coordinate is negative
	@Test public void testComputeAngleXNegative()
	{
		MyPoint testPoint = new MyPoint(-1, 0);
		double actual = point.computeAngle(testPoint);
		assertEquals(Math.PI, actual, 0.0001);
	}
	
	//Test ComputeAngle if X coordinate is positive
	@Test public void testComputeAngleXPositive()
	{
		MyPoint testPoint = new MyPoint(1, 0);
		double actual = point.computeAngle(testPoint);
		assertEquals(0d, actual, 0.0001);
	}
	
	//Test ComputeAngle if parameter is null
	@Test public void testComputeAngleNull()
	{
		double actual = point.computeAngle(null);
		assertEquals(Double.NaN, actual, 0.0001);
	}
	
	//Test RotatePoint behaviour if gravity point is null
	@Test public void testRotatePointNull()
	{
		MyPoint actual;
		actual = point.rotatePoint(null, 2.0d);
		assertEquals(null, actual);
	}
	
	@Test public void testRotatePointZero()
	{
		MyPoint rotate = new MyPoint(1d, 0d);
		MyPoint actual = rotate.rotatePoint(point, 0);
		assertEquals(actual.getX(), 1d, 0.0001);
		assertEquals(actual.getY(), 0d, 0.0001);
	}
	
	//Test RotatePoint if angle equals PI
	@Test public void testRotatePointPI()
	{
		MyPoint rotate = new MyPoint(1d, 0d);
		MyPoint actual = rotate.rotatePoint(point, Math.PI);
		assertEquals(actual.getX(), -1d, 0.0001);
		assertEquals(actual.getY(), 0d, 0.0001);
	}
	
	//Test RotatePoint if angle equals PI/2
	@Test public void testRotatePointHalfPI()
	{
		MyPoint rotate = new MyPoint(1d, 0d);
		MyPoint actual = rotate.rotatePoint(point, Math.PI/2);
		assertEquals(actual.getX(), 0d, 0.0001);
		assertEquals(actual.getY(), 1d, 0.0001);
	}
	
	//Test RotatePoint if angle equals 3PI/2
	@Test public void testRotatePoint3PIOver2()
	{
		MyPoint rotate = new MyPoint(1d, 0d);
		MyPoint actual = rotate.rotatePoint(point, 3*Math.PI/2);
		assertEquals(actual.getX(), 0d, 0.0001);
		assertEquals(actual.getY(), -1d, 0.0001);
	}
	
	@Test public void testRotatePointMinusPIOver6()
	{
		MyPoint rotate = new MyPoint(1d, 0d);
		MyPoint actual = rotate.rotatePoint(point, -Math.PI/6);
		assertEquals(actual.getX(), Math.sqrt(3)/2, 0.0001);
		assertEquals(actual.getY(), -1/2d, 0.0001);
	}	
	
	@Test public void testRotatePointNaNAngle()
	{
		MyPoint rotate = new MyPoint(1d, 0d);
		MyPoint actual = rotate.rotatePoint(point, Double.NaN);
		assertEquals(actual.getX(), 1d, 0.0001);
		assertEquals(actual.getY(), 0d, 0.0001);
	}	
	
	@Test public void testCentralSymmetry() 
	{
		MyPoint rotate = new MyPoint(1.0d, 0.0d);
		MyPoint actual = rotate.centralSymmetry(point);
		assertEquals(actual.getX(), -1d, 0.0001);
		assertEquals(actual.getY(), 0d, 0.0001);
	}
	
	@Test public void testCentralSymmetryNull() 
	{
		assertThrows(IllegalArgumentException.class, () -> point.centralSymmetry(null));
	}
	
	@Test public void testGetMiddlePoint()
	{
		MyPoint second = new MyPoint(2.0d, 0.0d);
		MyPoint actual = point.getMiddlePoint(second);
		assertEquals(actual.getX(), 1d, 0.0001);
		assertEquals(actual.getY(), 0d, 0.0001);
	}
	
	@Test public void testGetMiddlePointNull()
	{
		assertThrows(IllegalArgumentException.class, () -> point.getMiddlePoint(null));
	}
	
	@Test public void testTranslateNaN()
	{
		point.translate(Double.NaN, 1.0d);
		assertEquals(point.getX(), 0d, 0.0001);
		assertEquals(point.getY(), 0d, 0.0001);
	}
	
	@Test public void testTranslateNaNNaN()
	{
		point.translate(Double.NaN, Double.NaN);
		assertEquals(point.getX(), 0d, 0.0001);
		assertEquals(point.getY(), 0d, 0.0001);
	}
	
	@Test public void testTranslate()
	{
		point.translate(1.0d, 1.0d);
		assertEquals(point.getX(), 1d, 0.0001);
		assertEquals(point.getY(), 1d, 0.0001);
	}
	
	@Test public void testSetPointRandomRandom()
	{
		Random r1 = mock(Random.class);
		Random r2 = mock(Random.class);
		MyPoint randomPoint = new MyPoint();
		randomPoint.setPoint(r1, r2);
		verify(r1).nextInt();
		verify(r2).nextInt();
	}
	
	@Test public void testTranslationITranslationNull()
	{
		point.translate(null);
		assertEquals(point.getX(), 0d, 0.0001);
		assertEquals(point.getY(), 0d, 0.0001);
	}
	
	@Test public void testTranslationITranslation()
	{
		ITranslation it = mock(ITranslation.class);
		point.translate(it);
		verify(it).getTx();
		verify(it).getTy();
	}

}
