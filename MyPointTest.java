import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestMyPoint {

	MyPoint point, point2, point3, pointnull, pointScale1, pointScale2, pointScale3, pointScale4, pointScale5, pointHorizontale, pointCentral, rotatePointNeg, rotatePoint, rotatePointNull, pointMiddle, pointTranslate, pointTranslateXNull, pointTranslateYNull;
	Double computeX0, computeYNeg, computeXNeg, computeXPos;
	@BeforeEach
	void setUp() throws Exception {
		point   	= new MyPoint();
		point2 		= new MyPoint(1d,2d);
		point3  	= new MyPoint(point2);
		pointnull 	= new MyPoint(null);
		pointScale1 = point2.scale(2d);
		pointScale2 = point2.scale(2.5);
		pointScale3 = point2.scale(-1);
		pointScale4 = point2.scale(0);
		pointScale5 = point2.scale(Double.NaN);
		pointHorizontale = new MyPoint(5d, 5d);
		computeX0 = point2.computeAngle(point2);
		computeYNeg = point2.computeAngle(new MyPoint(1d, 1d));
		computeXNeg = point2.computeAngle(new MyPoint(0d, 2d));
		computeXPos = point2.computeAngle(new MyPoint(2d, 2d));
		rotatePoint = point2.rotatePoint(point2, 0);
		rotatePointNeg = point2.rotatePoint(point2, -90d);
		rotatePointNull = point2.rotatePoint(null, 180d);
		pointCentral = point2.centralSymmetry(new MyPoint(2, 3));
		pointMiddle = point2.getMiddlePoint(new MyPoint(3d, 4d));		
	}

	@AfterEach
	void tearDown() throws Exception {
		point   	= null;
		point2  	= null;
		point3  	= null;
		pointnull	= null;
		pointScale1 = null;
		pointScale2 = null;
		pointScale3 = null;
		pointScale4 = null;
		pointScale5 = null;
		pointHorizontale = null;
		rotatePoint = null;
	}

	@Test
	void testMyPoint() {
		assertEquals (0d, point . getX (), 0.0001);
		assertEquals (0d, point . getY (), 0.0001);
	}

	@Test
	void testMyPointDoubleDouble() {
		assertEquals (1d, point2 . getX (), 0.0001);
		assertEquals (2d, point2 . getY (), 0.0001);
	}

	@Test
	void testMyPointMyPoint() {
		assertEquals (1d, point3 . getX (), 0.0001);
		assertEquals (2d, point3 . getY (), 0.0001);
		
		assertEquals (0d, pointnull . getX (), 0.0001);
		assertEquals (0d, pointnull . getY (), 0.0001);
	}

	@Test
	void testGetSetX() {
		assertEquals (0d, point . getX (), 0.0001);
		point.setX(1d);
		assertEquals (1d, point . getX (), 0.0001);
		
		point.setX(-1d);
		assertEquals (-1d, point . getX (), 0.0001);
		
		point.setX(Double.NaN);
		assertTrue(Double.isNaN(point.getX()));
	}

	@Test
	void testGetSetY() {
		assertEquals (0d, point . getY (), 0.0001);
		point.setY(2d);
		assertEquals (2d, point . getY (), 0.0001);
		
		point.setY(-2d);
		assertEquals (-2d, point . getY (), 0.0001);
		
		point.setY(Double.NaN);
		assertTrue(Double.isNaN(point.getY()));
	}
/*
	@Test
	void testGetX() {
		assertEquals (1d, point2 . getX (), 0.0001);
		
	}

	@Test
	void testGetY() {
		assertEquals (2d, point2 . getY (), 0.0001);
	}
*/
	@Test
	void testScale() {
		assertEquals (2d , pointScale1 . getX (), 0.0001);
		assertEquals (4d , pointScale1 . getY (), 0.0001);
		
		assertEquals (2.5, pointScale2 . getX (), 0.0001);
		assertEquals (5d , pointScale2 . getY (), 0.0001);
		
		assertEquals (-1d, pointScale3 . getX (), 0.0001);
		assertEquals (-2d, pointScale3 . getY (), 0.0001);
		
		assertEquals (0d , pointScale4 . getX (), 0.0001);
		assertEquals (0d , pointScale4 . getY (), 0.0001);
		
		assertTrue (Double.isNaN(pointScale5 . getX ()));
		assertTrue (Double.isNaN(pointScale5 . getY ()));
	}

	@Test
	void testHorizontalSymmetry() {
		assertThrows(IllegalArgumentException.class, () -> {
			point2.horizontalSymmetry(null);
		});
		assertEquals(1d, point2.horizontalSymmetry(point2).getX(), 0.0001);
		assertEquals(9d, point2.horizontalSymmetry(pointHorizontale).getX(), 0.0001);

	}

	@Test
	void testComputeAngle() {
		assertEquals(Double.NaN, point2.computeAngle(null), 0.0001);
		assertEquals(1.047, computeX0, 0.001);
		assertEquals(5.23, computeYNeg, 0.01);
		assertEquals(3.14, computeXNeg, 0.01);
		assertEquals(0, computeXPos, 0.01);
		assertNotNull(point2.computeAngle(pointHorizontale));
	}

	@Test
	void testRotatePoint() {
		assertEquals(1, rotatePoint.getX(),0.0001);
		assertEquals(2d, rotatePoint.getY(), 0.0001);
		
		assertNotNull(rotatePointNeg);
		assertNull(rotatePointNull);
	}

	@Test
	void testCentralSymmetry() {
		assertThrows(IllegalArgumentException.class, () -> {
			new MyPoint ( 1d , 2d ) . centralSymmetry ( null ) ;		
		});
		assertEquals(1, pointCentral.getX(), 0.0001);
		assertEquals(2, pointCentral.getY(), 0.0001);

		//fail("Not yet implemented");
	}

	@Test
	void testGetMiddlePoint() {
		assertEquals(2, pointMiddle.getX(),0.0001);
		assertEquals(3, pointMiddle.getY(),0.0001);
	}

	@Test
	void testTranslateDoubleDouble() {
		point2.translate(2d, 2d);
		assertEquals(3d, point2.getX(), 0.0001);
		assertEquals(4d, point2.getY(), 0.0001);
		
		// Test si y est null
		point2.translate(1d, Double.NaN);
		assertEquals(3d, point2.getX(), 0.0001);
		assertEquals(4d, point2.getY(), 0.0001);

		//Test si x est null
		point2.translate(Double.NaN, 1d);
		assertEquals(3d, point2.getX(), 0.0001);
		assertEquals(4d, point2.getY(), 0.0001);
		
		point2.translate(Double.NaN, Double.NaN);
		assertEquals(3d, point2.getX(), 0.0001);
		assertEquals(4d, point2.getY(), 0.0001);
	}

	@Test
	void testSetPoint() {
		fail("Not yet implemented");
	}

	@Test
	void testTranslateITranslation() {
		fail("Not yet implemented");
	}
	
	

}
