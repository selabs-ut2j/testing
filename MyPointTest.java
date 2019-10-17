package main.fr.ut2j.m1ice.ootesting;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
class MyPointTest {

	private MyPoint point;
	private double newX;
	private double newY;

	@BeforeEach
	void setUp() throws Exception {
		point = new MyPoint();
		newX = Math.random();
		newY = Math.random();
	}

	@Test
	void testMyPoint1() {
		assertEquals(0d, point.getX(), 0.0001);
		assertEquals(0d, point.getY(), 0.0001);
	}
	
	@Test
	void testMyPoint2() {
		point.setX(newX);
		point.setY(newY);
		assertEquals(newX, point.getX(), 0.0001);
		assertEquals(newY, point.getY(), 0.0001);
	}
	
	@Test
	void testAccessorX() {
		point.setX(newX);
		assertEquals(newX, point.getX(), 0.0001);
	}

	@Test
	void testAccessorY() {
		point.setY(newY);
		assertEquals(newY, point.getY(), 0.0001);
	}
	
	@Test
	void testAccessorXdoubleNaN() {
		
		double oldX = point.getX();
		double newXnan = Double.NaN;
		point.setX(newXnan);
		boolean isNan = Double.isNaN(point.getX());
		assertFalse(isNan);
		assertEquals(oldX, point.getX(), 0.0001);
	}
	
	@Test
	void testAccessorYdoubleNaN() {
		
		double oldY = point.getY();
		double newYnull = Double.NaN;
		point.setY(newYnull);
		boolean isNan = Double.isNaN(point.getY());
		assertFalse(isNan);
		assertEquals(oldY, point.getY(), 0.0001);
	}
	
	@Test
	void testMyPoint3() {
		point.setX(newX);
		point.setY(newY);
		MyPoint newPoint = new MyPoint(point);
		assertEquals(point.getX(), newPoint.getX(), 0.0001);
		assertEquals(point.getY(), newPoint.getY(), 0.0001);
		assertNotSame(newPoint, point);
	}
	
	@Test
	void testMyPoint3null() {
		MyPoint point = null;
		MyPoint newPoint = new MyPoint(point);
		assertEquals(0d, newPoint.getX(), 0.0001);
		assertEquals(0d, newPoint.getY(), 0.0001);
		assertNotSame(newPoint, point);
	}
	
	@Test
	void testScale() {
		point.setX(newX);
		point.setY(newY);
		double scaleValue = Math.random();
		MyPoint newPoint = point.scale(scaleValue);
		assertEquals(point.getX() * scaleValue, newPoint.getX(), 0.0001);
		assertEquals(point.getY() * scaleValue, newPoint.getY(), 0.0001);
		assertNotSame(newPoint, point);
	}
	
	@Test
	void testHorizontalSymmetry() {
		point.setX(newX);
		point.setY(newY);
		MyPoint origin = new MyPoint(Math.random(),Math.random());
		MyPoint newPoint = point.horizontalSymmetry(origin);
		double originYcomputed = Math.abs(newPoint.getY() + point.getY())/2;
		assertEquals(originYcomputed, origin.getY(), 0.00001);
	}
	
	@Test
	void testHorizontalSymmetryNull() {
		point.setX(newX);
		point.setY(newY);
		MyPoint origin = null;
		assertThrows(IllegalArgumentException.class, () -> {
			@SuppressWarnings("unused")
			MyPoint newPoint = point.horizontalSymmetry(origin);
		});
	}

	@Test
	void testComputeAngle() {
		point.setX(newX);
		point.setY(newY);
		MyPoint origin = new MyPoint(Math.random(),Math.random());
		double angle = point.computeAngle(origin);
		assertTrue(angle >= 0 && angle <= 360);
	}

	@Test
	void testComputeAngleNullPoint() {
		point.setX(newX);
		point.setY(newY);
		MyPoint origin = null;
		double angle = point.computeAngle(origin);
		boolean isNan = Double.isNaN(angle);
		assertTrue(isNan);
		assertEquals(Double.NaN, angle, 0.0);
	}
	
	@Test
	void testRotatePointGravityNull() {
		point.setX(newX);
		point.setY(newY);
		double theta = Math.toRadians(90);
		MyPoint gravityC = null;
		MyPoint ptRotated = point.rotatePoint(gravityC, theta);
		assertNull(ptRotated);		
	}
	
	@Test
	void testRotatePointThetaNull() {
		point.setX(newX);
		point.setY(newY);
		double theta = Double.NaN;
		MyPoint gravityC = new MyPoint(Math.random(),Math.random());
		MyPoint ptRotated = point.rotatePoint(gravityC, theta);
		assertNull(ptRotated);		
	}

	@Test
	void testRotatePointThetaNegative450Degrees() {
		point.setX(10);
		point.setY(10);
		double theta = Math.toRadians(-450);
		MyPoint gravityC = new MyPoint(1,1);
		MyPoint ptRotated = point.rotatePoint(gravityC, theta);
		assertEquals(10, ptRotated.getX(), 0.0001);
		assertEquals(-8, ptRotated.getY(), 0.0001);
		assertNotSame(ptRotated, point);		
	}	
	
	@Test
	void testRotatePointThetaNegative90Degrees() {
		point.setX(10);
		point.setY(10);
		double theta = Math.toRadians(-90);
		MyPoint gravityC = new MyPoint(1,1);
		MyPoint ptRotated = point.rotatePoint(gravityC, theta);
		assertEquals(10 , ptRotated.getX(), 0.0001);
		assertEquals(-8 , ptRotated.getY(), 0.0001);
		assertNotSame(ptRotated, point);
	}	
	
	@Test
	void testRotatePointTheta0Degrees() {
		point.setX(10);
		point.setY(10);
		double theta = Math.toRadians(0);
		MyPoint gravityC = new MyPoint(Math.random(),Math.random());
		MyPoint ptRotated = point.rotatePoint(gravityC, theta);
		assertEquals(point.getX() , ptRotated.getX(), 0.0001);
		assertEquals(point.getY() , ptRotated.getY(), 0.0001);
		assertNotSame(ptRotated, point);
	}	

	@Test
	void testRotatePointTheta90Degrees() {
		point.setX(10);
		point.setY(10);
		double theta = Math.toRadians(90);
		MyPoint gravityC = new MyPoint(1,1);
		MyPoint ptRotated = point.rotatePoint(gravityC, theta);
		assertEquals(-8 , ptRotated.getX(), 0.0001);
		assertEquals(10 , ptRotated.getY(), 0.0001);
		assertNotSame(ptRotated, point);
	}	

	@Test
	void testRotatePointTheta180Degrees() {
		point.setX(10);
		point.setY(10);
		double theta = Math.toRadians(180);
		MyPoint gravityC = new MyPoint(1,1);
		MyPoint ptRotated = point.rotatePoint(gravityC, theta);
		assertEquals(-8 , ptRotated.getX(), 0.0001);
		assertEquals(-8 , ptRotated.getY(), 0.0001);
		assertNotSame(ptRotated, point);		
	}		

	@Test
	void testRotatePointTheta270Degrees() {
		point.setX(10);
		point.setY(10);
		double theta = Math.toRadians(270);
		MyPoint gravityC = new MyPoint(1,1);
		MyPoint ptRotated = point.rotatePoint(gravityC, theta);
		assertEquals(10 , ptRotated.getX(), 0.0001);
		assertEquals(-8 , ptRotated.getY(), 0.0001);
		assertNotSame(ptRotated, point);		
	}		
	
	@Test
	void testRotatePointTheta360Degrees() {
		point.setX(10);
		point.setY(10);
		double theta = Math.toRadians(360);
		MyPoint gravityC = new MyPoint(1,1);
		MyPoint ptRotated = point.rotatePoint(gravityC, theta);
		assertEquals(point.getX() , ptRotated.getX(), 0.0001);
		assertEquals(point.getY() , ptRotated.getY(), 0.0001);
		assertNotSame(ptRotated, point);		
	}	

	@Test
	void testRotatePointTheta450Degrees() {
		point.setX(10);
		point.setY(10);
		double theta = Math.toRadians(450);
		MyPoint gravityC = new MyPoint(1,1);
		MyPoint ptRotated = point.rotatePoint(gravityC, theta);
		assertEquals(-8, ptRotated.getX(), 0.0001);
		assertEquals(10 , ptRotated.getY(), 0.0001);
		assertNotSame(ptRotated, point);		
	}
	
	@Test
	void testCentralSymmetry() {
		point.setX(10);
		point.setY(10);
		MyPoint centre = new MyPoint(1,1);
		MyPoint ptRotated = point.centralSymmetry(centre);
		assertEquals(-8 , ptRotated.getX(), 0.0001);
		assertEquals(-8 , ptRotated.getY(), 0.0001);
		assertNotSame(ptRotated, point);		
	}
	
	
	@Test
	public void testCentralSymmetryNULL ( ) {
		assertThrows(IllegalArgumentException.class, () -> {
			new MyPoint(10,20).centralSymmetry(null);
		});
	}
	
	@Test
	void testGetMiddlePoint() {
		point.setX(newX);
		point.setY(newY);
		MyPoint newPoint = new MyPoint(Math.random(),Math.random());
		MyPoint middlePoint = point.getMiddlePoint(newPoint);
		assertEquals(point.getX() + newPoint.getX(), middlePoint.getX() * 2, 0.0001);
		assertEquals(point.getY() + newPoint.getY(), middlePoint.getY() * 2, 0.0001);
	}
	
	@Test
	void testTranslate() {
		point.translate(newX, newY);
		assertEquals(point.getX(), newX, 0.00001);
		assertEquals(point.getY(), newY, 0.00001);
	}
	
	@Test
	void testTranslateXNull() {
		point.translate(Double.NaN, newY);
		assertNotEquals(point.getX(), Double.NaN, 0.00001);
		assertNotEquals(point.getY(), newY, 0.00001);
	}
	
	@Test
	void testTranslateYNull() {
		point.translate(newX, Double.NaN);
		assertNotEquals(point.getX(), newX, 0.00001);
		assertNotEquals(point.getY(), Double.NaN, 0.00001);
	}
	
	@Test
	void testSetPoint() {
		 Random random1 = Mockito.mock(Random.class);
		 Random random2 = Mockito.mock(Random.class);
		 Mockito.when(random1.nextDouble()).thenReturn(2d);
		 Mockito.when(random2.nextDouble()).thenReturn(3d);
		 point.setPoint(random1, random2);
		 assertEquals(2d, point.getX(), 0.0001);
		 assertEquals(3d, point.getY(), 0.0001);
	}
	
	@Test
	void testITranslate() {
		ITranslation translation = Mockito.mock(ITranslation.class);
		Mockito.when(translation.getTx()).thenReturn(5d);
		Mockito.when(translation.getTy()).thenReturn(5d);
		point.translate(translation);
		assertEquals(5d, point.getX(), 0.0001);
		assertEquals(5d, point.getY(), 0.0001);
	}
	
	@Test
	void testITranslateNULL() {
		ITranslation translation = null;
		point.translate(translation);
		assertEquals(0d, point.getX(), 0.0001);
		assertEquals(0d, point.getY(), 0.0001);
	}
}