package devopsTP.TP1;
import static java.lang.Math.atan;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Mockito.*;

import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;

import devopsTP.TP1.MyPoint;

/**
 * @author Vincent CIZEY
 *
 */
class MyPointTest {
	
	@Mock Random rand1;
	@Mock Random rand2;

	private MyPoint point1;
	
	@BeforeEach
	public void setup() throws Exception {
		point1 = new MyPoint(3, 3);
		rand1 = Mockito.mock(Random.class);
		rand2 = Mockito.mock(Random.class);
	}
	
	@After
	public void tearDown() throws Exception{
	}
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Test
	public void testMyPoint1() {
		MyPoint point = new MyPoint();
		assertEquals(0d, point.getX(), 0.0001);
		assertEquals(0d, point.getY(), 0.0001);
	}
	
	@Test
	public void testMyPoint2() {
		MyPoint point = new MyPoint(1, 2);
		assertEquals(1d, point.getX(), 0.0001);
		assertEquals(2d, point.getY(), 0.0001);
	}
// Q1.2 Le test porte sur les get X Y et non sur les constructeurs.
	
	
	@Test
	public void testGetSetX() {
		MyPoint point = new MyPoint();
		point.setX(1);
		assertEquals(1d, point.getX(), 0.0001);

	}
	
	@Test
	public void testGetSetY() {
		MyPoint point = new MyPoint(0d, 0d);
		point.setY(1d);
		assertEquals(1d, point.getY(), 0.0001);
	}
	
	@Test
	public void testSetX() {
		MyPoint point = new MyPoint(1, 2);
		point.setX(Double.NaN);
		assertEquals(1d, point.getX(), 0.0001);
	}
	
	@Test
	public void testSetY() {
		MyPoint point = new MyPoint(1, 2);
		point.setY(Double.NaN);
		assertEquals(2d, point.getY(), 0.0001);
	}
	
	@Test
	public void testMyPoint3() {
		MyPoint pointTested = new MyPoint(point1);
		assertEquals(3d, pointTested.getX(), 0.0001);
		assertEquals(3d, pointTested.getY(), 0.0001);
	}
	
	@Test
	public void testMyPoint3_2() {
		point1 = null;
		MyPoint pointTested = new MyPoint(point1);
		assertEquals(0d, pointTested.getX(), 0.0001);
		assertEquals(0d, pointTested.getY(), 0.0001);
	}

	@Test
	public void testScale() {
		MyPoint pointTested = point1.scale(3);
		assertEquals(9d, pointTested.getX(), 0.0001);
		assertEquals(9d, pointTested.getY(), 0.0001);
	}
	
	@Test
	public void testHorizontalSymmetryBasicValues() {
		MyPoint pointOrigin = new MyPoint(1, 1);
		MyPoint pointTested = point1.horizontalSymmetry(pointOrigin);
		assertEquals(-1d, pointTested.getY(), 0.0001);
		assertEquals(3d, pointTested.getX(), 0.0001);
	}
	
	@Test
	public void testComputeAngleLittleBasicAngle() {
		MyPoint pointOrigin = new MyPoint(5, 5);
		double angle = point1.computeAngle(pointOrigin);
		assertEquals(Math.PI * atan(1), angle, 0.0001);
	}
	
	@Test
	public void testComputeAngleGreatBasicAngle() {
		MyPoint pointOrigin = new MyPoint(1,5);
		double angle = point1.computeAngle(pointOrigin);
		assertEquals(Math.PI * atan(1), angle, 0.0001);
	}
	
	@Test
	public void testComputeAngleFlatHorizontaly() {
		MyPoint pointOrigin = new MyPoint(5, 3);
		double angle = point1.computeAngle(pointOrigin);
		assertEquals(Math.PI, angle, 0.0001);
	}
	
	@Test
	public void testComputeAngleFlatVerticaly() {
		MyPoint pointOrigin = new MyPoint(3, 5);
		double angle = point1.computeAngle(pointOrigin);
		assertEquals((Math.PI / 2d), angle, 0.0001);
	}
	
//	@Test
//	public void shouldTestExceptionMessage() throws
//	IndexOutOfBoundsException {
//		MyPoint pointTested = point1.horizontalSymmetry(null);
//		thrown.expect(IllegalArgumentException.class);
//		thrown.expectMessage(new IllegalArgumentException().getMessage());
//		pointTested.getY();
//	}
	/*
	@Test
	void testExpectedExceptionSymetry() {
	 
	  Assertions.assertThrows(IllegalArgumentException.class, () -> {
		  point1.horizontalSymmetry(null);
	  });
	 
	}
	*/
	/**
	 * Translates the point using a given vector.
	 * @param translation The translation vector. If null, nothing is performed.
	 */
	@Test
	void testSetPoint() {
		point1.setPoint(rand1, rand2);
		Mockito.verify(rand1).nextInt();
		Mockito.verify(rand2).nextInt();
	}
}
