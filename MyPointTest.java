package test;

import main.fr.ut2j.m1ice.ootesting.MyPoint;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MyPointTest {
	private final double x = 4;
	private final double y = 5;
	private MyPoint point1;
	private MyPoint point2;

	@BeforeEach
	void setUp() throws Exception {
		this.point1 = new MyPoint();
		this.point2 = new MyPoint(x,y); 
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testBuilder1() {
		assertNotNull(this.point1);
		assertEquals (0d, point1.getX(), 0.0001);
		assertEquals (0d, point1. getY(), 0.0001);
	}	
	
	@Test
	void testBuilder2() {
		assertNotNull(this.point2);
		assertEquals (x, point2.getX(), 0.0001);
		assertEquals (y, point2. getY(), 0.0001);
	}	
	
	@Test
	void testAccesseursX() {
		double testX = 15.84;
		this.point1.setX(testX);
		assertEquals (testX, this.point1.getX());
	}
	
	@Test
	void testAccesseursY() {
		double testY = 3.1415;
		this.point1.setY(testY);
		assertEquals (testY, this.point1.getY());
	}
	
	@Test
	void testAccesseursXNaN() {
		double testX = Double.NaN;
		this.point1.setX(testX);
		assertTrue (Double.isNaN(this.point1.getX()));
	}
	
	@Test
	void testAccesseursYNaN() {
		double testY = Double.NaN;
		this.point1.setY(testY);
		assertTrue (Double.isNaN(this.point1.getY()));
	}
	
	@Test
	void testBuilder3FromBuilder1() {
		MyPoint point3 = new MyPoint(this.point1);
		assertNotNull(point3);
		assertEquals (this.point1.getX(), point3.getX(), 0.0001);
		assertEquals (this.point1.getY(), point3.getY(), 0.0001);
	}	
	
	@Test
	void testBuilder3FromBuilder2() {
		MyPoint point3 = new MyPoint(this.point2);
		assertNotNull(point3);
		assertEquals (this.point2.getX(), point3.getX(), 0.0001);
		assertEquals (this.point2.getY(), point3.getY(), 0.0001);
	}	
	
	@Test
	void testScale() {
		double augm = 3;
		double redu = 0.5;
		double rien = 1;
		double nul = 0;
		
		assertEquals(this.point2.getX()*augm, point2.scale(augm).getX(),0.0001);
		assertEquals(this.point2.getY()*augm, point2.scale(augm).getY(),0.0001);
		
		assertEquals(this.point2.getX()*redu, point2.scale(redu).getX(),0.0001);
		assertEquals(this.point2.getY()*redu, point2.scale(redu).getY(),0.0001);
		
		assertEquals(this.point2.getX()*rien, point2.scale(rien).getX(),0.0001);
		assertEquals(this.point2.getY()*rien, point2.scale(rien).getY(),0.0001);
		
		assertEquals(this.point2.getX()*nul, point2.scale(nul).getX(),0.0001);
		assertEquals(this.point2.getY()*nul, point2.scale(nul).getY(),0.0001);
	}
	
	@Test
	void testHorizontalSymmetry1() {
		assertEquals(this.point2.getX(),this.point2.horizontalSymmetry(this.point1).getX(),0.0001);
		assertEquals(this.point2.getY()*-1,this.point2.horizontalSymmetry(this.point1).getY(),0.0001);
	}
	
	@Test
	void testHorizontalSymmetry2() {
		assertEquals(this.point2.getX(),this.point2.horizontalSymmetry(this.point2).getX(),0.0001);
		assertEquals(this.point2.getY(),this.point2.horizontalSymmetry(this.point2).getY(),0.0001);
	}
	
	@Test
	void testHorizontalSymmetry3() {
		MyPoint point3 = new MyPoint(10,15);
		assertEquals(this.point2.getX(),this.point2.horizontalSymmetry(point3).getX(),0.0001);
		assertEquals(2d * point3.getY() - this.point2.getY(),this.point2.horizontalSymmetry(point3).getY(),0.0001);
	}
	
	@Test
	void testHorizontalSymmetryException() {
		MyPoint point3 = null;
		assertThrows(IllegalArgumentException.class,()-> {this.point2.horizontalSymmetry(point3);});
	}
	
	@Test
	void testComputeAngle() {
		MyPoint point3 = new MyPoint(0,3);
		MyPoint point4 = new MyPoint(3,0);
		MyPoint point5 = new MyPoint(0,-3);
		assertEquals(0, this.point1.computeAngle(this.point1),0.0001);
	}
	
	
}
