package main.fr.ut2j.m1ice.ootesting;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)

class TestMyPoint {
	
	private static final double x = 3;
	private static final double y = 4;	
	MyPoint point1;
	MyPoint point2;
	MyPoint point3; //to see

	@BeforeEach
	void setUp() throws Exception {
		this.point1 = new MyPoint();
		this.point2 = new MyPoint(x,y);
		this.point3 = new MyPoint(this.point2);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testMyPoint() {
		assertNotNull(this.point1);
		assertEquals(0d, this.point1.getX(), 0.00001);
		assertEquals(0d, this.point1.getY(), 0.00001);
	}
 

	@Test
	void testMyPoint2() {
		assertNotNull(this.point2);
		assertEquals(x, this.point2.getX(), 0.00001);
		assertEquals(y, this.point2.getY(), 0.00001);
	}
	

	@Test
	void testMyPoint3() {
		assertNotNull(this.point3);
		assertEquals(x, this.point3.getX(), 0.00001);
		assertEquals(y, this.point3.getY(), 0.00001);
		assertNotEquals(this.point2, this.point3);
	}
	
	@Test
	void testSetterX() {
		double _x = 9.88;
		this.point1.setX(_x);
		assertEquals(_x, this.point1.getX());
	}
	
	@Test
	void testSetterY() {
		double _y = 22.2;
		this.point1.setY(_y);
		assertEquals(_y, this.point1.getY());
	}
	
	@Test
	void testSetterNanX() {
		double _NanX = Double.NaN;
		this.point1.setX(_NanX);
		assertTrue(Double.isNaN(this.point1.getX()));
	}
	
	@Test
	void testSetterNanY() {
		double _NanY = Double.NaN;
		this.point1.setY(_NanY);
		assertTrue(Double.isNaN(this.point1.getY()));
	}
	
	@Test
	void testScale() {
		MyPoint scale = this.point2.scale(2);
		assertEquals(x*3, scale.getX(), 0.00001);
		assertEquals(y*3, scale.getY(), 0.00001);
	}
	
	@Test
	void testHorizontalSymmetryP1() {
		assertEquals(this.point2.getX(), this.point2.horizontalSymmetry(this.point1).getX(), 0.00001);
		assertEquals(this.point2.getY()*-1, this.point2.horizontalSymmetry(this.point1).getY(), 0.00001);	
	}
	
	@Test
	void testHorizontalSymmetryP2() {
		assertEquals(this.point2.getX(), this.point2.horizontalSymmetry(this.point2).getX(), 0.00001);
		assertEquals(this.point2.getY(), this.point2.horizontalSymmetry(this.point2).getY(), 0.00001);	
	}
	
	@Test
	void testHorizontalSymmetryP3() {
		MyPoint point3 = new MyPoint(20,25);
		assertEquals(this.point2.getX(), this.point2.horizontalSymmetry(point3).getX(), 0.00001);
		assertEquals(2d * point3.getY() - this.point2.getY(), this.point2.horizontalSymmetry(point3).getY(), 0.00001);
	}
	
	@Test
	void testHorizontalSymmetryException() {
		MyPoint pointNull = null;
		assertThrows(IllegalArgumentException.class, ()->{this.point2.horizontalSymmetry(pointNull);});
		
	}
	
	@Test
	void testComputedAngle() {
		//TODO
	}

	@Test
	void testRotateAngle() {
		MyPoint point3 = this.point2.rotatePoint(this.point1, Math.PI/2);
		assertEquals(point3.getX(), this.point2.getX(), 0.00001);
		assertEquals(point3.getY(), -this.point2.getY(), 0.00001);
	}
	
	@Test
	void testCentralSymmetry() {
		MyPoint center = this.point2.centralSymmetry(this.point1);
		assertEquals(center.getX(), -this.point2.getX(), 0.00001);
		assertEquals(center.getY(), -this.point2.getY(), 0.00001);
	}
	
	@Test
	void testMiddlePoint() {
		MyPoint middle = this.point2.getMiddlePoint(this.point1);
		assertEquals(2, middle.getX(), 0.00001);
		assertEquals(2.5, middle.getY(), 0.00001);
	}
	
	@Test
	void testTranslate() {
		this.point1.translate(6, 11);
		assertEquals(6, this.point1.getX(), 0.0001);
		assertEquals(11, this.point1.getY(), 0.00001);
	}
	
	@Test
	void setPointMockito() {
		Random rng1 = Mockito.mock(Random.class);
		Random rng2 = Mockito.mock(Random.class);
		Mockito.when(rng1.nextInt()).thenReturn(1);
		Mockito.when(rng2.nextInt()).thenReturn(2);
		this.point1.setPoint(rng1, rng2);
		assertEquals(1, this.point1.getX());
		assertEquals(2, this.point1.getY());
	}
	
	@Test
	void testTranslationMockito() {
		ITranslation translation = Mockito.mock(ITranslation.class);
		Mockito.when(translation.getTx()).thenReturn(3);
		Mockito.when(translation.getTy()).thenReturn(4);
		this.point1.translate(translation);
		assertEquals(this.point1.getX(), 3);
		assertEquals(this.point1.getY(), 4);
	}

}
