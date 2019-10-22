/**
 * MyPoint's Test class.
 * Those tests verified behavior and functional results of the MyPoint methods.
 * They are designed to cover a maximum of the different instructions.
 */
package main.fr.ut2j.m1ice.ootesting;

import static java.lang.Math.atan;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

/**
 * @author Antoine Dupuy
 */
class MyPointTest {
	
	// Mock of random (setPoint)
	@Mock Random rand1;
	@Mock Random rand2;
	
	// Random angle (rotatePoint)
	Random randomDouble;
	Double randAngle;
	
	// Double infinity
	double max;
	double min;
	
	// Interface ITranslation mock
	ITranslation translation;
	
	// Base points
	MyPoint origin;
	MyPoint p;
	MyPoint positivInfinityPoint;
	MyPoint negativInfinityPoint;
	
	// RotatePoint point
	MyPoint pToRotate;
	MyPoint pRotateZero;
	
	// ComputeAngle points
	MyPoint pComputeAngle;
	MyPoint pComputeAngle2;
	MyPoint pComputeAngle3;
	
	// Copy operator points
	MyPoint pnull;
	MyPoint pp;
	MyPoint ppnull;
	
	// Horizontal symmetry point
	MyPoint psym;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		origin = new MyPoint();
		p = new MyPoint(4d, 4d);
		pComputeAngle = new MyPoint(4d, 5d);
		pComputeAngle2 = new MyPoint(3d, 4d);
		pComputeAngle3 = new MyPoint(5d, 4d);
		pRotateZero = new MyPoint(6d, 4d);
		pp = new MyPoint(p);
		ppnull = new MyPoint(pnull);
		psym = new MyPoint(8d, 4d);
		pToRotate = new MyPoint(1d, 0d);
		rand1 = mock(Random.class);
		rand2 = mock(Random.class);
		translation = mock(ITranslation.class);
		max = Double.POSITIVE_INFINITY;
		min = Double.NEGATIVE_INFINITY;
		negativInfinityPoint = new MyPoint(min, min);
		positivInfinityPoint = new MyPoint(max, max);
		randomDouble = new Random();
		randAngle = randomDouble.nextDouble();
	}
	
	/**
	 * Test method for {@link main.fr.ut2j.m1ice.ootesting.MyPoint#getX()}.
	 */
	@Test
	void testGetX() {
		assertEquals(0d, origin.getX());
	}
	
	/**
	 * Test method for {@link main.fr.ut2j.m1ice.ootesting.MyPoint#getX()}.
	 */
	@Test
	void testGetXPositivInfinity() {
		assertEquals(Double.POSITIVE_INFINITY, positivInfinityPoint.getX());
	}

	/**
	 * Test method for {@link main.fr.ut2j.m1ice.ootesting.MyPoint#getX()}.
	 */
	@Test
	void testGetXNegativInfinity() {
		assertEquals(Double.NEGATIVE_INFINITY, negativInfinityPoint.getX());
	}

	/**
	 * Test method for {@link main.fr.ut2j.m1ice.ootesting.MyPoint#getY()}.
	 */
	@Test
	void testGetY() {
		assertEquals(0d, origin.getY());
	}

	/**
	 * Test method for {@link main.fr.ut2j.m1ice.ootesting.MyPoint#getY()}.
	 */
	@Test
	void testGetYPositivInfinity() {
		assertEquals(Double.POSITIVE_INFINITY, positivInfinityPoint.getY());
	}

	/**
	 * Test method for {@link main.fr.ut2j.m1ice.ootesting.MyPoint#getY()}.
	 */
	@Test
	void testGetYNegativInfinity() {
		assertEquals(Double.NEGATIVE_INFINITY, negativInfinityPoint.getY());
	}

	
	/**
	 * Test method for {@link main.fr.ut2j.m1ice.ootesting.MyPoint#setX(double)}.
	 */
	@Test
	void testSetX() {
		p.setX(7d);
		assertEquals(7d, p.getX());
	}
	
	/**
	 * Test method for {@link main.fr.ut2j.m1ice.ootesting.MyPoint#setX(double)}.
	 */
	@Test
	void testSetXPositivInfinity() {
		p.setX(max);
		assertEquals(Double.POSITIVE_INFINITY, p.getX());
	}
	
	/**
	 * Test method for {@link main.fr.ut2j.m1ice.ootesting.MyPoint#setX(double)}.
	 */
	@Test
	void testSetXNegativInfinity() {
		p.setX(min);
		assertEquals(Double.NEGATIVE_INFINITY, p.getX());
	}

	/**
	 * Test method for {@link main.fr.ut2j.m1ice.ootesting.MyPoint#setX(double)}.
	 */
	@Test
	void testSetXNan() {
		double previousX = p.getX();
		p.setX(Math.sqrt(-5d));
		assertEquals(previousX, p.getX());
	}

	/**
	 * Test method for {@link main.fr.ut2j.m1ice.ootesting.MyPoint#setY(double)}.
	 */
	@Test
	void testSetY() {
		p.setY(5d);
		assertEquals(5d, p.getY());
	}
	
	/**
	 * Test method for {@link main.fr.ut2j.m1ice.ootesting.MyPoint#setY(double)}.
	 */
	@Test
	void testSetYPositivInfinity() {
		p.setY(max);
		assertEquals(Double.POSITIVE_INFINITY, p.getY());
	}

	/**
	 * Test method for {@link main.fr.ut2j.m1ice.ootesting.MyPoint#setY(double)}.
	 */
	@Test
	void testSetYNegativInfinity() {
		p.setY(min);
		assertEquals(Double.NEGATIVE_INFINITY, p.getY());
	}

	/**
	 * Test method for {@link main.fr.ut2j.m1ice.ootesting.MyPoint#setY(double)}.
	 */
	@Test
	void testSetYNan() {
		double previousY = p.getY();
		p.setY(Math.sqrt(-5d));
		assertEquals(previousY, p.getY());
	}

	/**
	 * Test method for {@link main.fr.ut2j.m1ice.ootesting.MyPoint#MyPoint()}.
	 */
	@Test
	void testMyPoint() {
		assertEquals(0d, origin.getX());
		assertEquals(0d, origin.getY());
	}
	
	/**
	 * Test method for {@link main.fr.ut2j.m1ice.ootesting.MyPoint#MyPoint()}.
	 */
	@Test
	void testMyPointPositivInfinity() {
		assertEquals(Double.POSITIVE_INFINITY, positivInfinityPoint.getX());
		assertEquals(Double.POSITIVE_INFINITY, positivInfinityPoint.getY());
		assertNotNull(positivInfinityPoint);
	}
	
	/**
	 * Test method for {@link main.fr.ut2j.m1ice.ootesting.MyPoint#MyPoint()}.
	 */
	@Test
	void testMyPointNegativInfinity() {
		assertEquals(Double.NEGATIVE_INFINITY, negativInfinityPoint.getX());
		assertEquals(Double.NEGATIVE_INFINITY, negativInfinityPoint.getY());
		assertNotNull(negativInfinityPoint);
	}

	/**
	 * Test method for {@link main.fr.ut2j.m1ice.ootesting.MyPoint#MyPoint(double, double)}.
	 */
	@Test
	void testMyPointDoubleDouble() {
		assertEquals(4d, p.getX());
		assertEquals(4d, p.getY());
	}

	/**
	 * Test method for {@link main.fr.ut2j.m1ice.ootesting.MyPoint#MyPoint(main.fr.ut2j.m1ice.ootesting.MyPoint)}.
	 */
	@Test
	void testMyPointMyPoint() {
		assertEquals(p.getX(), pp.getX());
		assertEquals(p.getY(), pp.getY());
	}
	
	/**
	 * Test method for {@link main.fr.ut2j.m1ice.ootesting.MyPoint#MyPoint(main.fr.ut2j.m1ice.ootesting.MyPoint)}.
	 */
	@Test
	void testMyPointMyPointNull() {
		assertEquals(0d, ppnull.getX());
		assertEquals(0d, ppnull.getY());
	}
	
	/**
	 * Test method for {@link main.fr.ut2j.m1ice.ootesting.MyPoint#scale(double)}.
	 */
	@Test
	void testScale() {
		MyPoint pprime = p.scale(0.5d);
		assertEquals(2d, pprime.getX());
		assertEquals(2d, pprime.getY());
	}
	
	/**
	 * Test method for {@link main.fr.ut2j.m1ice.ootesting.MyPoint#scale(double)}.
	 */
	@Test
	void testScalePositivInfinity() {
		MyPoint pprime = p.scale(max);
		assertEquals(Double.POSITIVE_INFINITY, pprime.getX());
		assertEquals(Double.POSITIVE_INFINITY, pprime.getY());
	}
	
	/**
	 * Test method for {@link main.fr.ut2j.m1ice.ootesting.MyPoint#scale(double)}.
	 */
	@Test
	void testScaleNegativInfinity() {
		MyPoint pprime = p.scale(min);
		assertEquals(Double.NEGATIVE_INFINITY, pprime.getX());
		assertEquals(Double.NEGATIVE_INFINITY, pprime.getY());
	}

	/**
	 * Test method for {@link main.fr.ut2j.m1ice.ootesting.MyPoint#horizontalSymmetry(main.fr.ut2j.m1ice.ootesting.MyPoint)}.
	 */
	@Test
	void testHorizontalSymmetry() {
		MyPoint newp = p.horizontalSymmetry(psym);
		assertEquals(newp.getX(), 12d);
		assertEquals(newp.getY(), p.getY());
	}
	
	/**
	 * Test method for {@link main.fr.ut2j.m1ice.ootesting.MyPoint#horizontalSymmetry(main.fr.ut2j.m1ice.ootesting.MyPoint)}.
	 */
	@Test
	void testHorizontalSymmetryPositivInfinity() {
		MyPoint newp = p.horizontalSymmetry(positivInfinityPoint);
		assertEquals(newp.getX(), Double.POSITIVE_INFINITY);
		assertEquals(newp.getY(), p.getY());
	}
	
	/**
	 * Test method for {@link main.fr.ut2j.m1ice.ootesting.MyPoint#horizontalSymmetry(main.fr.ut2j.m1ice.ootesting.MyPoint)}.
	 */
	@Test
	void testHorizontalSymmetryNegativInfinity() {
		MyPoint newp = p.horizontalSymmetry(negativInfinityPoint);
		assertEquals(newp.getX(), Double.NEGATIVE_INFINITY);
		assertEquals(newp.getY(), p.getY());
	}

	/**
	 * Test method for {@link main.fr.ut2j.m1ice.ootesting.MyPoint#horizontalSymmetry(main.fr.ut2j.m1ice.ootesting.MyPoint)}.
	 */
	@Test
	void testHorizontalSymmetryNullOrigin() {
	    Throwable exceptionPointNull = assertThrows(IllegalArgumentException.class, () ->  p.horizontalSymmetry(pnull));
	    assertEquals("Not null origin", exceptionPointNull.getMessage());
	    assertTrue(exceptionPointNull instanceof IllegalArgumentException);
	}

	/**
	 * Test method for {@link main.fr.ut2j.m1ice.ootesting.MyPoint#computeAngle(main.fr.ut2j.m1ice.ootesting.MyPoint)}.
	 */
	@Test
	void testComputeAngleNull() {
		double angle = p.computeAngle(pnull);
		assertEquals(angle, Double.NaN);
	}
	
	/**
	 * Test method for {@link main.fr.ut2j.m1ice.ootesting.MyPoint#computeAngle(main.fr.ut2j.m1ice.ootesting.MyPoint)}.
	 */
	@Test
	void testComputeAngleZero() {
		double angle = p.computeAngle(pRotateZero);
		assertEquals(angle, 0d);
	}
	
	/**
	 * Test method for {@link main.fr.ut2j.m1ice.ootesting.MyPoint#computeAngle(main.fr.ut2j.m1ice.ootesting.MyPoint)}.
	 */
	@Test
	void testComputeAnglePositivInfinity() {
		double angle = p.computeAngle(positivInfinityPoint);
		assertEquals(angle, Double.NaN);
	}
	
	/**
	 * Test method for {@link main.fr.ut2j.m1ice.ootesting.MyPoint#computeAngle(main.fr.ut2j.m1ice.ootesting.MyPoint)}.
	 */
	@Test
	void testComputeAngleNegativInfinity() {
		double angle = p.computeAngle(negativInfinityPoint);
		assertEquals(angle, Double.NaN);
	}

	/**
	 * Test method for {@link main.fr.ut2j.m1ice.ootesting.MyPoint#computeAngle(main.fr.ut2j.m1ice.ootesting.MyPoint)}.
	 */
	@Test
	void testComputeAngleSameCoordXAndGivenYSmallerOrEquals() {
		double angle = origin.computeAngle(origin);
		assertEquals(angle, Math.PI / 3d);
	}
	
	/**
	 * Test method for {@link main.fr.ut2j.m1ice.ootesting.MyPoint#computeAngle(main.fr.ut2j.m1ice.ootesting.MyPoint)}.
	 */
	@Test
	void testComputeAngleSameCoordXAndGivenYBigger() {
		double angle = pComputeAngle.computeAngle(p);
		assertEquals(angle, Math.PI * 2d - Math.PI / 3d);
	}

	/**
	 * Test method for {@link main.fr.ut2j.m1ice.ootesting.MyPoint#computeAngle(main.fr.ut2j.m1ice.ootesting.MyPoint)}.
	 */
	@Test
	void testComputeAngleBiggerCoordX() {
		double angle = pComputeAngle3.computeAngle(p);
		assertEquals(angle, Math.PI - atan(-(p.getY() - pComputeAngle3.getY()) / (p.getX() - pComputeAngle3.getX())));
	}

	/**
	 * Test method for {@link main.fr.ut2j.m1ice.ootesting.MyPoint#computeAngle(main.fr.ut2j.m1ice.ootesting.MyPoint)}.
	 */
	@Test
	void testComputeAngleSmallerCoordX() {
		double angle = pComputeAngle2.computeAngle(p);
		assertEquals(angle, atan((p.getY() - pComputeAngle2.getY()) / (p.getX() - pComputeAngle2.getX())));
	}

	/**
	 * Test method for {@link main.fr.ut2j.m1ice.ootesting.MyPoint#rotatePoint(main.fr.ut2j.m1ice.ootesting.MyPoint, double)}.
	 */
	@Test
	void testRotateNull() {
		MyPoint rotatedPoint = p.rotatePoint(null, Math.PI);
		assertEquals(rotatedPoint, null);
	}

	/**
	 * Test method for {@link main.fr.ut2j.m1ice.ootesting.MyPoint#rotatePoint(main.fr.ut2j.m1ice.ootesting.MyPoint, double)}.
	 */
	@Test
	void testRotatePositivInfinity() {
		MyPoint rotatedPoint = p.rotatePoint(positivInfinityPoint, randAngle);
		assertEquals(rotatedPoint.getX(), 0d);
		assertEquals(rotatedPoint.getY(), 0d);
	}
	
	/**
	 * Test method for {@link main.fr.ut2j.m1ice.ootesting.MyPoint#rotatePoint(main.fr.ut2j.m1ice.ootesting.MyPoint, double)}.
	 */
	@Test
	void testRotateNegativInfinity() {
		MyPoint rotatedPoint = p.rotatePoint(negativInfinityPoint, randAngle);
		assertEquals(rotatedPoint.getX(), 0d);
		assertEquals(rotatedPoint.getY(), 0d);
	}

	/**
	 * Test method for {@link main.fr.ut2j.m1ice.ootesting.MyPoint#rotatePoint(main.fr.ut2j.m1ice.ootesting.MyPoint, double)}.
	 */
	@Test
	void testRotatePointModulo2Pi() {
		MyPoint rotatedPoint = p.rotatePoint(origin, 4 * Math.PI);
		assertEquals(rotatedPoint.getX(), p.getX());
		assertEquals(rotatedPoint.getY(), p.getY());
	}
	
	/**
	 * Test method for {@link main.fr.ut2j.m1ice.ootesting.MyPoint#rotatePoint(main.fr.ut2j.m1ice.ootesting.MyPoint, double)}.
	 */
	@Test
	void testRotatePointModulo2PiNegative() {
		MyPoint rotatedPoint = p.rotatePoint(origin, -4 * Math.PI);
		assertEquals(rotatedPoint.getX(), p.getX());
		assertEquals(rotatedPoint.getY(), p.getY());
	}

	/**
	 * Test method for {@link main.fr.ut2j.m1ice.ootesting.MyPoint#rotatePoint(main.fr.ut2j.m1ice.ootesting.MyPoint, double)}.
	 */
	@Test
	void testRotatePi() {
		MyPoint rotatedPoint = pToRotate.rotatePoint(origin, Math.PI);
		assertEquals(rotatedPoint.getX(), -1d);
		assertEquals(rotatedPoint.getY(), 0d);
	}

	/**
	 * Test method for {@link main.fr.ut2j.m1ice.ootesting.MyPoint#rotatePoint(main.fr.ut2j.m1ice.ootesting.MyPoint, double)}.
	 */
	@Test
	void testRotatePiDividedByTwo() {
		MyPoint rotatedPoint = pToRotate.rotatePoint(origin, Math.PI / 2);
		assertEquals(rotatedPoint.getX(), 0d);
		assertEquals(rotatedPoint.getY(), 1d);
	}
	
	/**
	 * Test method for {@link main.fr.ut2j.m1ice.ootesting.MyPoint#rotatePoint(main.fr.ut2j.m1ice.ootesting.MyPoint, double)}.
	 */
	@Test
	void testRotateThreePiDividedByTwo() {
		MyPoint rotatedPoint = pToRotate.rotatePoint(origin, 3 * Math.PI / 2);
		assertEquals(rotatedPoint.getX(), 0d);
		assertEquals(rotatedPoint.getY(), -1d);
	}

	/**
	 * Test method for {@link main.fr.ut2j.m1ice.ootesting.MyPoint#rotatePoint(main.fr.ut2j.m1ice.ootesting.MyPoint, double)}.
	 */
	@Test
	void testRotateRandomPositivAngle() {
		double randAngle = Math.random() * 2 * Math.PI;
		MyPoint rotatedPoint = pToRotate.rotatePoint(origin, randAngle);
		assertEquals(rotatedPoint.getX(), Math.cos(randAngle) * (pToRotate.getX() - origin.getX()) - Math.sin(randAngle) * (pToRotate.getY() - origin.getY()) + origin.getX(), 0.0001);
		assertEquals(rotatedPoint.getY(), Math.sin(randAngle) * (pToRotate.getX() - origin.getX()) - Math.cos(randAngle) * (pToRotate.getY() - origin.getY()) + origin.getY(), 0.0001);
	}
	
	/**
	 * Test method for {@link main.fr.ut2j.m1ice.ootesting.MyPoint#rotatePoint(main.fr.ut2j.m1ice.ootesting.MyPoint, double)}.
	 */
	@Test
	void testRotateRandomNegativAngle() {
		double randAngle = - Math.random() * 2 * Math.PI;
		MyPoint rotatedPoint = pToRotate.rotatePoint(origin, randAngle);
		assertEquals(rotatedPoint.getX(), Math.cos(randAngle) * (pToRotate.getX() - origin.getX()) - Math.sin(randAngle) * (pToRotate.getY() - origin.getY()) + origin.getX(), 0.0001);
		assertEquals(rotatedPoint.getY(), Math.sin(randAngle) * (pToRotate.getX() - origin.getX()) - Math.cos(randAngle) * (pToRotate.getY() - origin.getY()) + origin.getY(), 0.0001);
	}

	/**
	 * Test method for {@link main.fr.ut2j.m1ice.ootesting.MyPoint#centralSymmetry(main.fr.ut2j.m1ice.ootesting.MyPoint)}.
	 */
	@Test
	void testCentralSymmetry() {
		MyPoint newp = pToRotate.centralSymmetry(origin);
		assertEquals(newp.getX(), -1d);
		assertEquals(newp.getY(), 0d);
	}
	
	@Test
	public void testCentralSymmetryNULL() {
	    Throwable exceptionPointNull = assertThrows(IllegalArgumentException.class, () ->  new MyPoint(10, 20).centralSymmetry(pnull));
	    assertEquals("Not null center", exceptionPointNull.getMessage());
	    assertTrue(exceptionPointNull instanceof IllegalArgumentException);
	}

	/**
	 * Test method for {@link main.fr.ut2j.m1ice.ootesting.MyPoint#getMiddlePoint(main.fr.ut2j.m1ice.ootesting.MyPoint)}.
	 */
	@Test
	void testGetMiddlePoint() {
		MyPoint middle = p.getMiddlePoint(psym);
		assertEquals(middle.getX(), 6d);
		assertEquals(middle.getY(), 4d);
	}

	/**
	 * Test method for {@link main.fr.ut2j.m1ice.ootesting.MyPoint#translate(double, double)}.
	 */
	@Test
	void testTranslateDoubleDouble() {
		p.translate(2d, 2d);
		assertEquals(p.getX(), 6d);
		assertEquals(p.getY(), 6d);
	}

	/**
	 * Test method for {@link main.fr.ut2j.m1ice.ootesting.MyPoint#setPoint(java.util.Random, java.util.Random)}.
	 */
	@Test
	void testSetPoint() {
		p.setPoint(rand1, rand2);
		verify(rand1).nextInt();
		verify(rand2).nextInt();
	}

	/**
	 * Test method for {@link main.fr.ut2j.m1ice.ootesting.MyPoint#translate(main.fr.ut2j.m1ice.ootesting.ITranslation)}.
	 */
	@Test
	void testTranslateITranslation() {
		p.translate(translation);
		verify(translation).getTx();
		verify(translation).getTy();
	}
	
	/**
	 * Test method for {@link main.fr.ut2j.m1ice.ootesting.MyPoint#translate(main.fr.ut2j.m1ice.ootesting.ITranslation)}.
	 */
	@Test
	void testTranslateITranslationNull() {
		double previousX = p.getX();
		double previousY = p.getY();
		p.translate(null);
		assertEquals(p.getX(), previousX);
		assertEquals(p.getY(), previousY);
	}

}
