package main.fr.ut2j.m1ice.ootesting;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MyPointTest {
	
	MyPoint myPointA;
	MyPoint myPointB;
	private Double x ;
	private Double y;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		myPointA = new MyPoint();
		
		this.x = 30d;
		this.y = 30d;
		myPointB = new MyPoint(x,y);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testMyPoint() {
		assertEquals(0d,this.myPointA.getY(),0.001);
		assertEquals(0d,this.myPointA.getX(),0.001);
		assertNotNull(myPointA);
	}

	@Test
	void testMyPointDoubleDouble() {
		assertEquals(y,this.myPointB.getY(),0.001);
		assertEquals(x,this.myPointB.getX(),0.001);
		assertNotNull(myPointB);
	}


	@Test
	void testSetX() {
		myPointB.setX(25);
		
	}

	@Test
	void testSetY() {
		fail("Not yet implemented");
	}

	@Test
	void testGetX() {
		fail("Not yet implemented");
	}

	@Test
	void testGetY() {
		fail("Not yet implemented");
	}

	@Test
	void testScale() {
		fail("Not yet implemented");
	}

	@Test
	void testHorizontalSymmetry() {
		fail("Not yet implemented");
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
