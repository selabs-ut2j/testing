package main.fr.ut2j.m1ice.ootesting;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class MyPointTest {
	
	MyPoint myPointA; // MyPoint with the empty constructor
	MyPoint myPointB; // MyPoint with the double,double constructor
	MyPoint myPointCp; // needed to copy other MyPoint
	MyPoint myPointScaled; // needed to created a MyPoint who has scaled
	MyPoint myPointNan; // needed to create a myPoint who has nan
	MyPoint myPointSymetry;
	
	private Double x ;
	private Double y;
	private Double scale;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		this.x = 30d;
		this.y = 30d;
		this.scale = 50d;
		
		myPointA = new MyPoint();
		myPointB = new MyPoint(x,y);
		myPointNan = new MyPoint(Double.NaN,3);
		myPointCp = null;
		myPointScaled = null;
		
		
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	/*
	 * This method test the empty constructor
	 */
	@Test
	void testMyPoint() {
		assertEquals(0d,this.myPointA.getY(),0.001);
		assertEquals(0d,this.myPointA.getX(),0.001);
		assertNotNull(myPointA);
	}
	
	/*
	 * this method test the double , double constructor
	 */
	@Test
	void testMyPointDoubleDouble() {
		assertEquals(y,this.myPointB.getY(),0.001);
		assertEquals(x,this.myPointB.getX(),0.001);
		assertNotNull(myPointB);
	}
	
	@Test
	void testMyPointIsNan() {
		System.out.println(this.myPointNan.getX());
		assertNotNull(myPointNan);
		assertEquals(this.myPointNan.getX(),0d,0.001);
	}
	
	/*
	 * this method test the copy constructor
	 */
	@Test
	void testMyPointMyPoint() {
		myPointCp = new MyPoint(myPointB);
		assertEquals(myPointCp.getY(),this.myPointB.getY(),30);
		assertEquals(myPointCp.getX(),this.myPointB.getY(),30);
		assertNotNull(myPointCp);
	}


	/*
	 * this method test the setter and getter of x
	 */
	@Test
	void testSetGetX() {
		final double xNew = 3;
		this.myPointA.setX(xNew);
		assertEquals(xNew,this.myPointA.getX());
	}
	
	/*
	 * this method test the setter nan of x
	 */
	@Test
	void testSetNanX() {
		final double xNew = Double.NaN;
		this.myPointA.setX(xNew);
		assertEquals(0d,this.myPointA.getX());
	}
	
	/*
	 * this method test the setter nan of y
	 */
	@Test
	void testSetNanY() {
		final double yNew = Double.NaN;
		this.myPointA.setX(yNew);
		assertEquals(0d,this.myPointA.getX());
	}

	/*
	 * this method test the setter and getter of y
	 */
	@Test
	void testSetGetY() {
		final double yNew = 3;
		this.myPointA.setY(yNew);
		assertEquals(yNew,this.myPointA.getY());
	}
	
	

	@Test
	void testScale() {
		myPointScaled = myPointB.scale(this.scale);
		assertEquals(myPointB.getX()*scale,myPointScaled.getX(),30);
		assertEquals(myPointB.getY()*scale,myPointScaled.getY(),30);
		assertNotNull(myPointScaled);
	}

	@Test
	void testHorizontalSymmetry() {
		org.junit.jupiter.api.Assertions.assertThrows(IllegalArgumentException.class, () ->{
			myPointSymetry = myPointA.horizontalSymmetry(null); 
		});
	}

	@Test 
	public void testCentralSymmetryNULL ( ) {
		assertThrows(IllegalArgumentException.class,() -> {
			new MyPoint(10,20).centralSymmetry(null) ;
		});
	}
	

}
