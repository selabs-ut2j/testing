/**
 * 
 */
package main.fr.ut2j.m1ice.ootesting.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.fr.ut2j.m1ice.ootesting.ITranslation;
import main.fr.ut2j.m1ice.ootesting.MyPoint;

import org.mockito.Mockito.*;


/**
 * @author valo
 *
 */
class MyPointTest {

	private MyPoint point;
	MyPoint point2;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		this.point = new MyPoint();
		this.point2 = new MyPoint(2, 12);
		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	}

	// Test du constructeur d'un point MyPoint sans paramètres
	@Test
	void testMyPoint1() {
		assertNotNull(this.point);
		assertEquals(0, this.point.getX(), 0.0001);
		assertEquals(0, this.point.getY(), 0.0001);
	}

	// Test du constructeur d'un point MyPoint avec paramètres
	@Test
	void testMyPoint2() {
		assertNotNull(this.point2);
		assertEquals(2, this.point2.getX(), 0.0001);
		assertEquals(12, this.point2.getY(), 0.0001);
	}
	
	@Test
	void testMyPoint2DoubleNanException1() {
		assertThrows(IllegalArgumentException.class, () -> {
			new MyPoint(Double.NaN, 0);
		  });
	}
	
	@Test
	void testMyPoint2DoubleNanException2() {
		assertThrows(IllegalArgumentException.class, () -> {
			new MyPoint(0, Double.NaN);
		  });
	}

	@Test
	void testMyPoint3() {
		MyPoint pointTmp = new MyPoint(this.point2);
		assertNotNull(pointTmp);
		assertEquals(2, pointTmp.getX(), 0.0001);
		assertEquals(12, pointTmp.getY(), 0.0001);
	}
	
	@Test
	void testMyPoint3Null() {
		MyPoint pointTmp = new MyPoint(null);
		assertNotNull(pointTmp);
		assertEquals(0, pointTmp.getX(), 0.0001);
		assertEquals(0, pointTmp.getY(), 0.0001);
	}

	@Test
	void testAccesseurX() {
		double tmp = 2.111225412;
		this.point.setX(tmp);
		assertEquals(2.111225412, this.point.getX(), 0.0001);
	}

	@Test
	void testAccesseurY() {
		double tmp = 2.111225412;
		this.point.setY(tmp);
		assertEquals(2.111225412, this.point.getY(), 0.0001);
	}

	@Test
	void testAccesseurXDoubleNan() {
		double tmp = Double.NaN;
		this.point.setX(tmp);
		assertEquals(0, this.point.getX(), 0.0001);
	}

	@Test
	void testAccesseurYDoubleNan() {
		double tmp = Double.NaN;
		this.point.setY(tmp);
		assertEquals(0, this.point.getY(), 0.0001);
	}
	
	@Test
	void testScale() {
		MyPoint pt = this.point2.scale(2);
		assertEquals(this.point2.getX() * 2, pt.getX(), 0.0001);
		assertEquals(this.point2.getY() * 2, pt.getY(), 0.0001);
	}
	
	@Test
	void testHorizontalSymmetryNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			this.point2.horizontalSymmetry(null);
		  });
	}
	
	@Test
	void testHorizontalSymmetry() {
		MyPoint pt = this.point2.horizontalSymmetry(this.point);
		assertEquals(-12,pt.getY());
	}

	@Test
	void testHorizontalSymmetryNegative() {
		MyPoint pt1 = new MyPoint(-3,-4);
		MyPoint pt2 = new MyPoint(-1,-2);
		MyPoint ptTmp = pt1.horizontalSymmetry(pt2);
		assertEquals(0, ptTmp.getY());
	}

	@Test
	void testComputeAngle() {
		fail("Not yet implemented");
	}

	@Test
	void testRotatePoint1() {
		MyPoint temp = point2.rotatePoint(null, 30);
		assertEquals(temp,null); 
	}

	@Test
	void testCentralSymmetryNull() {
		  Assertions.assertThrows(IllegalArgumentException.class, () -> {
			  this.point2.centralSymmetry(null);
		  });
	}

	@Test
	void testGetMiddlePoint() {
		MyPoint pt1 = new MyPoint(2,4);
		MyPoint pt = point2.getMiddlePoint(pt1);
		assertEquals(pt.getX(),(point2.getX()+pt1.getX())/2);
		assertEquals(pt.getY(),(point2.getY()+pt1.getY())/2);	
	}

	@Test
	void testTranslateDoubleDouble() {
		point2.translate(6, 7);
		assertEquals(8,point2.getX());
		assertEquals(19,point2.getY());
	}

	@Test
	void testSetPoint() {
		Random rd1 = new Random(1);
		Random rd2 = new Random(1);
		point.setPoint(rd1, rd2);
		assertEquals(-1155869325,point.getX());
		assertEquals(-1155869325,point.getY());
	}

	@Test
	void testTranslateITranslation() {
		ITranslation ptTranslate = new MyPoint(2,3);
		this.point.translate(ptTranslate);
		assertEquals(2,point.getX());
		assertEquals(3,point.getY());
	}
	
	@Test
	void testTranslateITranslationNull() {
		this.point.translate(null);
		assertEquals(0,point.getX());
		assertEquals(0,point.getY());
	}
	
	

}
