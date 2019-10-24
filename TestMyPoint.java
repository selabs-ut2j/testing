package main.fr.ut2j.m1ice.ootesting;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.Mockito;


import static java.lang.Math.PI;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;


@RunWith(MockitoJUnitRunner.class)
class TestMyPoint {
	private MyPoint instancePoint1;
	private MyPoint instancePoint2;
	private MyPoint instancePoint3;
	private final double x1 = 1;
	private final double y1 = 3;
	
	@BeforeEach
	void setUp() throws Exception {
		this.instancePoint1 = new MyPoint();
		this.instancePoint2 = new MyPoint(x1,y1);
		this.instancePoint3 = new MyPoint(instancePoint2);
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	
	@Test
	void testMyPoint1() {
		assertNotNull(this.instancePoint1);
		assertEquals(0d, this.instancePoint1.getX(), 0.0001);
	}
	
	@Test
	void testMyPoint2() {
		assertNotNull(this.instancePoint2);
		assertEquals(y1, this.instancePoint2.getY(), 0.0001);
		assertEquals(x1, this.instancePoint2.getX(), 0.0001);
	}
	
	@Test
	void testMyPoint2DoubleNaN1() {				
		  Assertions.assertThrows(IllegalArgumentException.class, () -> {
			  new MyPoint(Double.NaN, 6);
		  });		 
	}
	
	@Test
	void testMyPoint2DoubleNaN2() {				
		  Assertions.assertThrows(IllegalArgumentException.class, () -> {
			  new MyPoint(6, Double.NaN);
		  });		 
	}
	
	@Test
	void testMyPoint2DoubleNaN3() {				
		  Assertions.assertThrows(IllegalArgumentException.class, () -> {
			  new MyPoint(Double.NaN, Double.NaN);
		  });		 
	}
	
	@Test
	void testSetGetX() {
		final double newX = 1.2;
		this.instancePoint1.setX(newX);
		assertEquals(newX, this.instancePoint1.getX());
	}
	
	@Test
	void testSetX() {
		final double newX = Double.NaN ;
		this.instancePoint2.setX(newX);
		assertEquals(0d,this.instancePoint2.getX());
	}
	
	@Test
	void testSetY() {
		final double newY = Double.NaN ;
		this.instancePoint2.setY(newY);
		assertEquals(0d,this.instancePoint2.getY());
	}

	@Test
	void testSetGetY() {
		final double newY = 5;
		this.instancePoint2.setY(newY);
		assertEquals(newY, this.instancePoint2.getY());
	}
	
	@Test
	void testMyPoint3() {
		assertNotNull(this.instancePoint3);
		assertEquals(x1, instancePoint2.getX(),0.0001);
		assertEquals(y1, instancePoint2.getY(),0.0001);
	}
	
	@Test
	void testMyPoint3Null() {
		MyPoint instancePointTmp = new MyPoint(null);
		assertNotNull(instancePointTmp);
		assertEquals(0,instancePointTmp.getX(), 0.0001);
		assertEquals(0,instancePointTmp.getY(), 0.0001);
	}
	
	@Test
	void testScale() {
		final double s = 30;
		final double sx = x1*s;
		final double sy = y1*s;
		MyPoint instancePointScale = this.instancePoint2.scale(s);
		assertEquals(sx,instancePointScale.getX(),0.0001);
		assertEquals(sy,instancePointScale.getY(),0.0001);
	}
	
	@Test
	void testHorizontalSymmetry() {
		MyPoint pt = new MyPoint(2,4);
		MyPoint instancePointTmp = instancePoint2.horizontalSymmetry(pt);
		assertEquals(5, instancePointTmp.getY());
		
	}
	
	@Test
	void testHorizontalSymmetryNegative() {
		MyPoint pt1 = new MyPoint(-3,-4);
		MyPoint pt2 = new MyPoint(-1,-2);
		MyPoint ptTmp = pt1.horizontalSymmetry(pt2);
		assertEquals(0, ptTmp.getY());
	}
	
	@Test
	void testHorizontalSymmetryNull() {
		MyPoint pt1 = null;
		MyPoint pt2 = new MyPoint(-1,-2);
		  Assertions.assertThrows(IllegalArgumentException.class, () -> {
			  pt2.horizontalSymmetry(pt1);
		  });
	}
		
	@Test
	void testComputeAngleDessus() {
		MyPoint ptTmp = new MyPoint(0,1);
		double angle = ptTmp.computeAngle(instancePoint1);
		assertEquals(PI/2, angle);
	}

	@Test
	void testRotatePoint1() {
		MyPoint temp = instancePoint2.rotatePoint(null, 30);
		assertEquals(temp,null); 
		
	}
	
	@Test
	void testCentralSymmetryNull() {
		  Assertions.assertThrows(IllegalArgumentException.class, () -> {
			  this.instancePoint2.centralSymmetry(null);
		  });
	}

	@Test
	void testGetMiddlePoint() {
		MyPoint pt1 = new MyPoint(2,4);
		MyPoint pt = instancePoint2.getMiddlePoint(pt1);
		assertEquals(pt.getX(),(instancePoint2.getX()+pt1.getX())/2);
		assertEquals(pt.getY(),(instancePoint2.getY()+pt1.getY())/2);	
	}

	@Test
	void testTranslateDoubleDouble() {
		final double pt1 = instancePoint2.getX();
		final double pt2 = instancePoint2.getY();
		final double newX = 6;
		final double newY = 7;
		instancePoint2.translate(newX, newY);
		assertEquals(instancePoint2.getX(),pt1+newX);
		assertEquals(instancePoint2.getY(),pt2+newY);
	}

	@Test
	void testSetPoint() {
		Random rand1 = Mockito.mock(Random.class);
		Mockito.when((rand1).nextDouble()).thenReturn(3d);
		
		Random rand2 = Mockito.mock(Random.class);
		Mockito.when((rand2).nextDouble()).thenReturn(7d);
		
		this.instancePoint1.setPoint(rand1, rand2);
		
		assertEquals(3.0, this.instancePoint1.getX());
		assertEquals(7.0, this.instancePoint1.getY());
	}
	

	@Test
	void testTranslateITranslation() {
		ITranslation trans = Mockito.mock(ITranslation.class);
		Mockito.when(trans.getTx()).thenReturn(1);
		Mockito.when(trans.getTy()).thenReturn(1);
		this.instancePoint1.translate(trans);
		assertEquals(this.instancePoint1.getX(), 1.0);
		assertEquals(this.instancePoint1.getY(), 1.0);
	}

}
