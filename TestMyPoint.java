import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestMyPoint {

	MyPoint point, point2, point3, pointnull, pointScale1, pointScale2, pointScale3, pointScale4, pointScale5, pointHorizontale;
	
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
		fail("Not yet implemented");
	}

	@Test
	void testRotatePoint() {
		fail("Not yet implemented");
	}

	@Test
	void testCentralSymmetry() {
		fail("Not yet implemented");
	}

	@Test
	void testGetMiddlePoint() {
		fail("Not yet implemented");
	}

	@Test
	void testTranslateDoubleDouble() {
		fail("Not yet implemented");
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
