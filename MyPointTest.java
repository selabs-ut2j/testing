import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

class MyPointTest {
	
	private MyPoint point;
	private MyPoint pointNeutre;
	private MyPoint pointPositif;
	private MyPoint pointNegatif;
	private MyPoint pointPositifNegatif;
	private MyPoint pointNegatifPositif;
	private MyPoint pointCopie;
	private MyPoint pointNaN;
	private MyPoint pointNull;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		point = new MyPoint();
		pointNeutre = new MyPoint(0,0);
		pointPositif = new MyPoint(1,2);
		pointNegatif = new MyPoint(-1,-2);
		pointPositifNegatif = new MyPoint(1,-2);
		pointNegatifPositif = new MyPoint(-1,2);
		pointNaN = new MyPoint(Math.sqrt(-1), Math.sqrt(-1));
	}

	@AfterEach
	void tearDown() throws Exception {
		point = null;
		pointNeutre = null;
		pointPositif = null;
		pointNegatif = null;
		pointPositifNegatif = null;
		pointNegatifPositif = null;
	}

	@Test
	void testMyPoint() {
		assertEquals(point.getX(), 0);
		assertEquals(point.getY(), 0);
	}

	@Test
	void testMyPointDoubleDoubleNeutre() {
		assertEquals(pointNeutre.getX(), 0);
		assertEquals(pointNeutre.getY(), 0);
	} 

	@Test
	void testMyPointDoubleDoublePositif() {
		assertEquals(pointPositif.getX(), 1);
		assertEquals(pointPositif.getY(), 2);
	} 
	
	@Test
	void testMyPointDoubleDoubleNegatif() {
		assertEquals(pointNegatif.getX(), -1);
		assertEquals(pointNegatif.getY(), -2);
	} 
	
	@Test
	void testMyPointDoubleDoublePositifNegatif() {
		assertEquals(pointPositifNegatif.getX(), 1);
		assertEquals(pointPositifNegatif.getY(), -2);
	} 
	
	@Test
	void testMyPointDoubleDoubleNegatifPositif() {
		assertEquals(pointNegatifPositif.getX(), -1);
		assertEquals(pointNegatifPositif.getY(), 2);
	} 
	
	@Test
	void testMyPointDoubleDoubleNaN() {
		assertEquals(pointNaN.getX(), 0);
		assertEquals(pointNaN.getY(), 0);
	} 
	
	@Test
	void testMyPointDoubleDoubleNaN2() {
		pointNaN = new MyPoint(1, Math.sqrt(-1));
		assertEquals(pointNaN.getX(), 0);
		assertEquals(pointNaN.getY(), 0);
	} 
	
	@Test
	void testMyPointMyPoint() {
		pointCopie = new MyPoint(pointPositif);
		assertEquals(pointCopie.getX(), 1);
		assertEquals(pointCopie.getY(), 2);
	}
	
	@Test
	void testMyPointMyPointNull() {
		pointNull = new MyPoint(null);
		pointCopie = new MyPoint(pointNull);
		assertEquals(pointCopie.getX(), 0);
		assertEquals(pointCopie.getY(), 0);
	}

	@Test
	void testSetX() {
		point.setX(8);
		assertEquals(point.getX(), 8);
	}
	
	@Test
	void testSetXNaN() {
		pointNeutre.setX(Math.sqrt(-1));
		assertEquals(pointNeutre.getX(), 0);
	}

	@Test
	void testSetY() {
		point.setY(8);
		assertEquals(point.getY(), 8);
	}

	@Test
	void testSetYNaN() {
		pointNeutre.setY(Math.sqrt(-1));
		assertEquals(pointNeutre.getY(), 0);
	}
	
	@Test
	void testScalePositif() {
		pointPositif = pointPositif.scale(3);
		assertEquals(pointPositif.getX(), 3);
		assertEquals(pointPositif.getY(), 6);
	}
	
	@Test
	void testScaleNegatif() {
		pointNegatif = pointNegatif.scale(3);
		assertEquals(pointNegatif.getX(), -3);
		assertEquals(pointNegatif.getY(), -6);
	}	
	
	@Test
	void testScalePositifNegatif() {
		pointPositifNegatif = pointPositifNegatif.scale(3);
		assertEquals(pointPositifNegatif.getX(), 3);
		assertEquals(pointPositifNegatif.getY(), -6);
	}
	
	@Test
	void testScaleNegatifPositif() {
		pointNegatifPositif = pointNegatifPositif.scale(3);
		assertEquals(pointNegatifPositif.getX(), -3);
		assertEquals(pointNegatifPositif.getY(), 6);
	}
	
	@Test
	void testScaleNeutre() {
		pointNeutre = pointNeutre.scale(3);
		assertEquals(pointNeutre.getX(), 0);
		assertEquals(pointNeutre.getY(), 0);
	}	
	
	@Test
	void testScaleNaN() {
		point = point.scale(Math.sqrt(-1));
		assertEquals(point.getX(), 0);
		assertEquals(point.getY(), 0);
	}
	
	@Test
	void testHorizontalSymmetryPositif() {
		pointPositif = pointPositif.horizontalSymmetry(point);
		assertEquals(pointPositif.getX(), -1);
		assertEquals(pointPositif.getY(), 2);
	}
	
	@Test
	void testHorizontalSymmetryNegatif() {
		pointNegatif = pointNegatif.horizontalSymmetry(point);
		assertEquals(pointNegatif.getX(), 1);
		assertEquals(pointNegatif.getY(), -2);
	}
	
	@Test
	void testHorizontalSymmetryPositifNegatif() {
		pointPositifNegatif = pointPositifNegatif.horizontalSymmetry(point);
		assertEquals(pointPositifNegatif.getX(), -1);
		assertEquals(pointPositifNegatif.getY(), -2);
	}
	
	@Test
	void testHorizontalSymmetryNegatifPositif() {
		pointNegatifPositif = pointNegatifPositif.horizontalSymmetry(point);
		assertEquals(pointNegatifPositif.getX(), 1);
		assertEquals(pointNegatifPositif.getY(), 2);
	}
	
	@Test
	void testHorizontalSymmetryNeutre() {
		pointNeutre = pointNeutre.horizontalSymmetry(point);
		assertEquals(pointNeutre.getX(), 0);
		assertEquals(pointNeutre.getY(), 0);
	}

	@Test
	void testHorizontalSymmetryAssertion() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			pointNeutre = pointNeutre.horizontalSymmetry(null);
		});
	}
	
	@Test 
	void testComputeAngle() { 
		MyPoint pointComputeAngle = new MyPoint(1,2);
		assertEquals(pointPositif.computeAngle(pointComputeAngle), 0);
		MyPoint pointComputeAngleBis = new MyPoint(2,2);
		assertEquals(pointPositif.computeAngle(pointComputeAngleBis), 0);
		MyPoint pointComputeAngle90 = new MyPoint(1,3);
		assertEquals(pointPositif.computeAngle(pointComputeAngle90), 90);
		MyPoint pointComputeAngle180 = new MyPoint(0,2);
		assertEquals(pointPositif.computeAngle(pointComputeAngle180), 180);
		MyPoint pointComputeAngle260 = new MyPoint(1,1);
		assertEquals(pointPositif.computeAngle(pointComputeAngle260), 270);
	}
	  
//	@Test 
//	void testRotatePoint() { 
//		 fail("Not yet implemented"); 
//	}
//	 
//	@Test 
//	void testCentralSymmetry() { 
//		fail("Not yet implemented"); 
//	}
	 

	@Test 
	public void testCentralSymmetryNULL() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new MyPoint(10,20).centralSymmetry(null);
		});
	}
	
//	@Test
//	void testGetMiddlePoint() {
//		fail("Not yet implemented");
//	}

	@Test
	void testTranslateDoubleDoubleNeutre() {
		pointNeutre.translate(3,3);
		assertEquals(pointNeutre.getX(), 3);
		assertEquals(pointNeutre.getY(), 3);
	} 

	@Test
	void testTranslateDoubleDoublePositif() {
		pointPositif.translate(3,3);
		assertEquals(pointPositif.getX(), 4);
		assertEquals(pointPositif.getY(), 5);
	} 
	
	@Test
	void testTranslateDoubleDoubleNegatif() {
		pointNegatif.translate(3,3);
		assertEquals(pointNegatif.getX(), 2);
		assertEquals(pointNegatif.getY(), 1);
	} 
	
	@Test
	void testTranslateDoubleDoublePositifNegatif() {
		pointPositifNegatif.translate(3,3);
		assertEquals(pointPositifNegatif.getX(), 4);
		assertEquals(pointPositifNegatif.getY(), 1);
	} 
	
	@Test
	void testTranslateDoubleDoubleNegatifPositif() {
		pointNegatifPositif.translate(3,3);
		assertEquals(pointNegatifPositif.getX(), 2);
		assertEquals(pointNegatifPositif.getY(), 5);
	} 
	
	@Test
	void testTranslateDoubleDoubleNaN() {
		point.translate(Double.NaN,Double.NaN);
		assertEquals(point.getX(), 0);
		assertEquals(point.getY(), 0);
	} 
	
	@Test
	void testTranslateDoubleDoubleNaN2() {
		point.translate(3,Double.NaN);
		assertEquals(point.getX(), 0);
		assertEquals(point.getY(), 0);
	} 
	
//	@Test
//	void testSetPoint() {
//		fail("Not yet implemented"); 
//	}
//
//	@Test
//	void testITranslation() {
//		fail("Not yet implemented"); 
//	}

}
