package main.fr.ut2j.m1ice.ootesting;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import main.fr.ut2j.m1ice.ootesting.MyPoint; 

@RunWith(MockitoJUnitRunner.class)
class MyPointTest {
	
	MyPoint TestConstructeur1;
	MyPoint TestConstructeur2;
	MyPoint TestConstructeur3;
	MyPoint TestConstructeurNaN;
	MyPoint TestConstructeurPointNull;
	MyPoint TestScale;
	MyPoint TestSymetry;
	MyPoint TestPointNegatif;

	@BeforeEach
	void setUp() throws Exception {
		TestConstructeur1 = new MyPoint();
		TestConstructeur2 = new MyPoint(2, 2);
		TestPointNegatif = new MyPoint(-3, -7);
	}

	@AfterEach
	void tearDown() throws Exception {
		TestConstructeur1 = null;
		TestConstructeur2 = null;
	}
	
	
	// Tests de get, inutile car fonction native
	@Test
	void testMyPoint() {
		assertEquals(0d, TestConstructeur1.getX(), 0.00001);
		assertEquals(0d, TestConstructeur1.getY(), 0.00001);
	}


	@Test
	void testMyPointDoubleDouble() {
		assertEquals(2d, TestConstructeur2.getX(), 0.00001);
		assertEquals(2d, TestConstructeur2.getY(), 0.00001);
	}
	
	@Test
	void testMyPointDoubleDoubleIsNan() {
		TestConstructeurNaN = new MyPoint(Math.sqrt(-1), Math.sqrt(-1));
		assertEquals(0, TestConstructeurNaN.getX(), 0.00001);
		assertEquals(0, TestConstructeurNaN.getY(), 0.00001);
	}

	@Test
	void testMyPointMyPoint() {
		TestConstructeur3 = new MyPoint(TestConstructeur2);
		assertEquals(TestConstructeur2.getX(), TestConstructeur3.getX(), 0.00001);
		assertEquals(TestConstructeur2.getY(), TestConstructeur3.getY(), 0.00001);
	}
	
	@Test
	void testMyPointMyPointNull() {
		TestConstructeur3 = new MyPoint(TestConstructeurPointNull);
		assertEquals(0d, TestConstructeur3.getX(), 0.00001);
		assertEquals(0d, TestConstructeur3.getY(), 0.00001);
	}
	
	@Test
	void testMyPointMyPointWithNaNValues() {
		TestConstructeurNaN = new MyPoint(Math.sqrt(-1), Math.sqrt(-1));
		TestConstructeur3 = new MyPoint(TestConstructeurNaN);
		assertEquals(0d, TestConstructeur3.getX(), 0.00001);
		assertEquals(0d, TestConstructeur3.getY(), 0.00001);
	}
	
	@Test
	void testSetX() {
		TestConstructeur2.setX(8);
		assertEquals(8d, TestConstructeur2.getX(), 0.00001);
	}
	

	@Test
	void testSetY() {
		TestConstructeur2.setY(8);
		assertEquals(8d, TestConstructeur2.getY(), 0.00001);
	}

	@Test
	void testGetX() {
		assertNotEquals(Double.NaN , TestConstructeur2.getX(), 0.00001);
	}

	@Test
	void testGetY() {
		assertNotEquals(Double.NaN , TestConstructeur2.getY(), 0.00001);
	}

	@Test
	void testScalePositive() {
		TestScale = new MyPoint(TestConstructeur2);
		TestScale = TestScale.scale(3);
		assertEquals((TestConstructeur2.getX() * 3), TestScale.getX(), 0.00001);
		assertEquals((TestConstructeur2.getY() * 3), TestScale.getY(), 0.00001);
	}
	
	@Test
	void testScaleNegative() {
		TestScale = new MyPoint(TestConstructeur2);
		TestScale = TestScale.scale(-5);
		assertEquals((TestConstructeur2.getX() * (-5)), TestScale.getX(), 0.00001);
		assertEquals((TestConstructeur2.getY() * (-5)), TestScale.getY(), 0.00001);
	}
	
	@Test
	void testScaleZero() {
		TestScale = new MyPoint(TestConstructeur2);
		TestScale = TestScale.scale(0);
		assertEquals(0, TestScale.getX(), 0.00001);
		assertEquals(0, TestScale.getY(), 0.00001);
	}
	
	@Test
	void testScaleWithNan() {
		TestScale = new MyPoint(TestConstructeur2);
		TestScale = TestScale.scale(Math.sqrt(-1));
		assertEquals(TestConstructeur2.getX(), TestScale.getX(), 0.00001);
		assertEquals(TestConstructeur2.getY(), TestScale.getY(), 0.00001);
	}

	@Test
	void testHorizontalSymmetry() {
		TestSymetry = new MyPoint(TestConstructeur2);
		MyPoint NewTestSymetry = TestSymetry.horizontalSymmetry(TestConstructeur2);
		assertEquals(TestSymetry.getX(), NewTestSymetry.getX(), 0.00001);
		assertEquals(TestConstructeur2.getY() - (TestSymetry.getY() - TestConstructeur2.getY()), NewTestSymetry.getY(), 0.00001);
	}
	
	@Test
	void testHorizontalSymmetryNegative() {
		TestSymetry = new MyPoint(TestConstructeur2);
		MyPoint NewTestSymetry = TestSymetry.horizontalSymmetry(TestPointNegatif);
		assertEquals(TestSymetry.getX(), NewTestSymetry.getX(), 0.00001);
		assertEquals(TestPointNegatif.getY() - (TestSymetry.getY() - TestPointNegatif.getY()), NewTestSymetry.getY(), 0.00001);
	}
	
	@Test
	void testIllegalArgumentException() {
	  Assertions.assertThrows(IllegalArgumentException.class, () -> {
	    MyPoint NewPointToRaiseException = TestConstructeur2.horizontalSymmetry(TestConstructeurPointNull);
	  });
	 
	}
	
	@Test
	void testComputeAngle() {
		//fail("Not yet implemented");
	}

	@Test
	void testRotatePoint() {
		//fail("Not yet implemented");
	}

	@Test
	void testCentralSymmetry() {

	}

	@Test
	void testGetMiddlePoint() {
		TestConstructeur2 = TestConstructeur2.getMiddlePoint(TestConstructeur1);
		assertEquals(TestConstructeur2.getX(), 1d, 0.00001);
		assertEquals(TestConstructeur2.getY(), 1d, 0.00001);
	}

	@Test
	void testTranslateDoubleDouble() {
		TestConstructeur3 = new MyPoint(TestConstructeur2);
		TestConstructeur3.translate(5d, 3d);
		assertEquals(TestConstructeur2.getX() + 5, TestConstructeur3.getX(), 0.00001);
		assertEquals(TestConstructeur2.getY() + 3, TestConstructeur3.getY(), 0.00001);
	}
	
	@Test
	void testTranslateDoubleDoubleWithNaN() {
		TestConstructeur3 = new MyPoint(TestConstructeur2);
		TestConstructeur3.translate(6d, Math.sqrt(-1));
		assertEquals(TestConstructeur2.getX(), TestConstructeur3.getX(), 0.00001);
		assertEquals(TestConstructeur2.getY(), TestConstructeur3.getY(), 0.00001);
		TestConstructeur3.translate(Math.sqrt(-1), 6d);
		assertEquals(TestConstructeur2.getX(), TestConstructeur3.getX(), 0.00001);
		assertEquals(TestConstructeur2.getY(), TestConstructeur3.getY(), 0.00001);
	}

	@Test
    void testSetPoint() {
        Random rand1 = Mockito.mock(Random.class);
        Mockito.when((rand1).nextDouble()).thenReturn(3.0);
        
        Random rand2 = Mockito.mock(Random.class);
        Mockito.when((rand2).nextDouble()).thenReturn(7.0);
        
        this.TestConstructeur1.setPoint(rand1, rand2);
        
        assertEquals(3.0, this.TestConstructeur1.getX());
        assertEquals(7.0, this.TestConstructeur1.getY());
    }
    
    @Test
    void testITranslation() {
        ITranslation trans = Mockito.mock(ITranslation.class);
        Mockito.when(trans.getTx()).thenReturn(1);
        Mockito.when(trans.getTy()).thenReturn(1);
        this.TestConstructeur1.translate(trans);
        assertEquals(this.TestConstructeur1.getX(), 1.0);
        assertEquals(this.TestConstructeur1.getY(), 1.0);
    }

	@Test public void testCentralSymmetryNULL ( ) {
		
	    assertThrows(IllegalArgumentException.class, () -> {new MyPoint(1, 2).centralSymmetry(null);});
	}
}
