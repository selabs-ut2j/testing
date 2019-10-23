package testfiles;

import static java.lang.Math.atan;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)

/*
 * Unity tests of MyPoint class
 */
class MyPointTest {
	MyPoint P1, P2, P2a, P2b, P2c, P3, PNull, PXFalse, PYFalse;
	
	@Mock
	Random random1, random2;
	
	@Mock
	ITranslation translation;
	
	@BeforeEach
	void setUp() throws Exception {
		// Initialize all points for testing
		P1 = new MyPoint();
		P2 = new MyPoint(1d,1d);
		P2a = new MyPoint(1d,-1d);
		P2b = new MyPoint(-1d,1d);
		P2c = new MyPoint(-1d,-1d);
		P3 = new MyPoint(P2);
		PNull = new MyPoint(null);
		PXFalse = new MyPoint(0.0/0.0,1d);
		PYFalse = new MyPoint(1d,0.0/0.0);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	/*
	 * Test of constructor 1 : not initialize, default : (0,0)
	 */
	@Test
	void testMyPoint1() {
		assertEquals(0d, P1.getX(), 0.0001);
		assertEquals(0d, P1.getY(), 0.0001);
	}
	
	/*
	 * Test of second constructor : initialize MyPoint(1d,1d), (1d,-1d), (-1d,1d), (-1d,-1d)
	 */
	@Test
	void testMyPoint2() {
		assertEquals(1d, P2.getX(), 0.0001);
		assertEquals(1d, P2.getY(), 0.0001);
	}
	
	@Test
	void testMyPoint2a() {
		assertEquals(1d, P2a.getX(), 0.0001);
		assertEquals(-1d, P2a.getY(), 0.0001);
	}
	
	@Test
	void testMyPoint2b() {
		assertEquals(-1d, P2b.getX(), 0.0001);
		assertEquals(1d, P2b.getY(), 0.0001);
	}
	
	@Test
	void testMyPoint2c() {
		assertEquals(-1d, P2c.getX(), 0.0001);
		assertEquals(-1d, P2c.getY(), 0.0001);
	}
	
	/*
	 * Test constructor 2: x not double
	 */
	@Test
	void testMyPoint2XFalse() {
		assertEquals(0.0, PXFalse.getX(), 0.001);
		assertEquals(0.0, PXFalse.getY(), 0.001);
	}
	
	/*
	 * Test constructor 2: y not double
	 */
	@Test
	void testMyPoint2YFalse() {
		assertEquals(0.0, PYFalse.getX(), 0.001);
		assertEquals(0.0, PYFalse.getY(), 0.001);
		
	}
	
	/*
	 * Test of constructor 3: object is not null
	 */
	@Test
	void testMyPoint3() {
		assertEquals(1d, P3.getX(), 0.0001);
		assertEquals(1d, P3.getY(), 0.0001);
	}
	
	/*
	 * Test of constructor 3: object is null
	 */
	@Test
	void testMyPoint3Null() {
		assertEquals(0.0, PNull.getX(), 0.0001);
		assertEquals(0.0, PNull.getY(), 0.0001);
	}
	
	/*
	 * Test methods getX() and setX()
	 */
	@Test
	void testGetX() {
		assertEquals(0.0, P1.getX(), 0.0001);
		P1.setX(2.0);
		assertEquals(2.0,P1.getX(), 0.0001);
	}
	
	/*
	 * Test methods getY() and setY()
	 */
	@Test
	void testGetY() {
		assertEquals(0.0, P1.getY(), 0.0001);
		P1.setY(2.0);
		assertEquals(2.0,P1.getY(), 0.0001);
	}
	
	/*
	 * Test methods setX(): Double.NaN value
	 */
	@Test
	void testSetXNotDouble() {
		assertEquals(0.0, P1.getX());
		P1.setX(Double.NaN);
		assertEquals(0.0, P1.getX());
	}
	
	/*
	 * Test methods setY(): Double.NaN value
	 */
	@Test
	void testSetYNotDouble() {
		assertEquals(0.0, P1.getY());
		P1.setY(Double.NaN);
		assertEquals(0.0, P1.getY());
	}
	
	/*
	 * Test method scale(): MyPoint(1d,1d), (1d,-1d), (-1d,1d), (-1d,-1d)
	 */
	@Test
	void testScaleP2() {
		P2 = P2.scale(3.0);
		assertEquals(3.0, P2.getX(), 0.0001);
		assertEquals(3.0, P2.getY(), 0.0001);
	}
	
	@Test
	void testScaleP2a() {
		P2a = P2a.scale(3.0);
		assertEquals(3.0, P2a.getX(), 0.0001);
		assertEquals(-3.0, P2a.getY(), 0.0001);
	}
	
	@Test
	void testScaleP2b() {
		P2b = P2b.scale(3.0);
		assertEquals(-3.0, P2b.getX(), 0.0001);
		assertEquals(3.0, P2b.getY(), 0.0001);
	}
	
	@Test
	void testScaleP2c() {
		P2c = P2c.scale(3.0);
		assertEquals(-3.0, P2c.getX(), 0.0001);
		assertEquals(-3.0, P2c.getY(), 0.0001);
	}
	
	
	/*
	 * Test method scale(): illegal parameter (not a double)
	 */
	@Test
	void testScaleNotDouble() {
		P2 = P2.scale(0.0/0.0);
		assertEquals(1.0,P2.getX(), 0.0001);
		assertEquals(1.0,P2.getY(), 0.0001);
	}
	
	/*
	 * Test method horizontalSymmetry: test of exception
	 */
	@Test
	void testHorizontalSymmetryException() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
            P1 = P1.horizontalSymmetry(null);
        });
	}
	
	/*
	 * Test method horizontalSymmetry: MyPoint(1d,1d), (1d,-1d), (-1d,1d), (-1d,-1d)
	 */
	@Test
	void testHorizontalSymmetryP2() {
		P2.horizontalSymmetry(P1);
		assertEquals(1d, P2.getX(), 0.0001);
		assertEquals(1d, P2.getY(), 0.0001);
	}
	
	@Test
	void testHorizontalSymmetryP2a() {
		P2a.horizontalSymmetry(P1);
		assertEquals(1d, P2a.getX(), 0.0001);
		assertEquals(-1d, P2a.getY(), 0.0001);
	}
	
	@Test
	void testHorizontalSymmetryP2b() {
		P2b.horizontalSymmetry(P1);
		assertEquals(-1d, P2b.getX(), 0.0001);
		assertEquals(1d, P2b.getY(), 0.0001);
	}
	
	@Test
	void testHorizontalSymmetryP2c() {
		P2c.horizontalSymmetry(P1);
		assertEquals(-1d, P2c.getX(), 0.0001);
		assertEquals(-1d, P2c.getY(), 0.0001);
	}
	
	/*
	 * Test method centralSymmetry 
	 */
	@Test
	void testCentralSymmetryP2() {
		// TO DO
		P2 = P2.centralSymmetry(P1);
		assertEquals(1d, P2.getX(), 0.0001);
        assertEquals(1d, P2.getY(), 0.0001);
	}
	
	@Test
	void testCentralSymmetryP2a() {
		// TO DO
		P2a = P2a.centralSymmetry(P1);
		assertEquals(1d, P2a.getX(), 0.0001);
        assertEquals(-1d, P2a.getY(), 0.0001);
	}
	
	@Test
	void testCentralSymmetryP2b() {
		// TO DO
		P2b = P2b.centralSymmetry(P1);
		assertEquals(-1d, P2b.getX(), 0.0001);
        assertEquals(1d, P2b.getY(), 0.0001);
	}
	
	@Test
	void testCentralSymmetryP2c() {
		// TO DO
		P2c = P2c.centralSymmetry(P1);
		assertEquals(-1d, P2c.getX(), 0.0001);
        assertEquals(-1d, P2c.getY(), 0.0001);
	}
	
	/*
	 * Test method centralSymmetry: MyPoint is null
	 */
	@Test 
	public void testCentralSymmetryNULL() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
	    P2.centralSymmetry(null);
		});
	}
	
	/*
	 * Test method getMiddlePoint: MyPoint is not null
	 */
	@Test
	void testGetMiddlePoint() {
		P1 = P1.getMiddlePoint(P2);
		assertEquals(0.5, P1.getX(), 0.0001);
		assertEquals(0.5,P1.getY(), 0.0001);
	}
	
	/*
	 * Test of method getMiddlePoint: exception
	 */
	@Test
	void testGetMiddlePointException() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
            P1 = P1.getMiddlePoint(null);
        });
	}
	
	/*
	 *  Test method rotatePoint(): MyPoint is null
	 */
    @Test
    public void testRotatePointNull() {
        P1 = P1.rotatePoint(null, 2.7);
        assertNull(P1);
    }

    /*
     *  Test method rotatePoint(): Double.NaN value in parameter
     */
    @Test
    public void testRotatePointNotDouble() {
        assertEquals(P2, P2.rotatePoint(P1, Double.NaN));
    }
    
    /*
     *  Test method computeAngle(): case x2 and y2 == 0
     */
    @Test
    public void testComputeAngleCase1() {
        P1.setX(1d);
        P1.setY(1d);
        double angle = P1.computeAngle(P2);
        assertEquals( Math.PI / 3d, angle);
    }
    
    /*
     *  Test method computeAngle(): case y2 < 0d
     */
    @Test
    public void testComputeAngleCase2() {
        P1.setX(1d);
        P1.setY(2d);
        double angle = P1.computeAngle(P2);
        assertEquals((Math.PI * 2d)- Math.PI / 3d, angle);
    }
    
    /*
     *  Test method computeAngle(): case x2 < 0d
     */
    @Test
    public void testComputeAngleCase3() {
        P1.setX(2d);
        P1.setY(1d);
        double angle = P1.computeAngle(P2);
        assertEquals(Math.PI - atan(0d / -1d), angle);
    }
    
    /*
     *  Test method computeAngle(): case x2 > 0d
     */
    @Test
    public void testComputeAngleCase4() {
        P1.setX(0d);
        P1.setY(1d);
        double angle = P1.computeAngle(P2);
        assertEquals(atan(0d / 1d), angle);
    }
    

    /*
     *  Test method computeAngle(): MyPoint() is null
     */
    @Test
    public void testComputeAngleNull() {
        //double angle = P2.computeAngle(null);
        assertEquals(Math.toDegrees(P2.computeAngle(null)), Double.NaN);
    }
    
	/*
	 * Test method setPoint() with a mock
	 */
	@Test
	void testSetPoint() {
		random1 = mock(Random.class);
		random2 = mock(Random.class);
		when(random1.nextDouble()).thenReturn(1.0);
		when(random2.nextDouble()).thenReturn(1.0);
		
		P1.setPoint(random1, random2);
		assertEquals(1.0, P1.getX(), 0.0001);
		assertEquals(1.0, P1.getY(), 0.0001);
	}
	
	/*
	 * Test method translate() with a mock: param is not null
	 */
	@Test
	void testTranslate() {
		translation = mock(ITranslation.class);
		when(translation.getTx()).thenReturn(1d);
		when(translation.getTy()).thenReturn(1d);
		
		P1.translate(translation);
		assertEquals(1d, P1.getX(), 0.0001);
		assertEquals(1d, P1.getY(), 0.0001);
	}
	
	/*
	 * Test method translate(): param is null
	 */
	@Test
	public void testTranslateITranslationNull() {
		P2.translate(null);
		assertEquals(1d,P2.getX(),0.00001);
		assertEquals(1d,P2.getY(),0.00001);
	}
	
	
	
	
}
