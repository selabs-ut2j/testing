package main.fr.ut2j.m1ice.ootesting;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;

class MyPointTest {

	MyPoint pointOrigine;
	MyPoint pointNE;
	MyPoint pointNW;
	MyPoint pointSW;
	MyPoint pointSE;
	
	
	
	@BeforeEach
	void setUp() throws Exception {
		//Mise en place de mes points, avec les cas cardinaux
		pointOrigine = new MyPoint();
		pointNE = new MyPoint(1,1);
		pointNW = new MyPoint(-1,1);
		pointSW = new MyPoint(-1,-1);
		pointSE = new MyPoint(1,-1);
		
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	
	/*
	 * All tests about contructor 
	 */
	
	@Test
	void testMyPointDoubleDouble0() {
		assertEquals (0d, pointOrigine.getX(), 0.0001);
		assertEquals (0d, pointOrigine.getY(), 0.0001);
	}
	
	@Test
	void testMyPointDoubleDouble1() {
		assertEquals (1d, pointNE.getX(), 0.0001);
		assertEquals (1d, pointNE.getY(), 0.0001);
	}
	@Test
	void testMyPointDoubleDouble2() {
		assertEquals (-1d, pointNW.getX(), 0.0001);
		assertEquals (1d, pointNW.getY(), 0.0001);
	}
	@Test
	void testMyPointDoubleDouble3() {
		assertEquals (-1d, pointSW.getX(), 0.0001);
		assertEquals (-1d, pointSW.getY(), 0.0001);
	}
	@Test
	void testMyPointDoubleDouble4() {
		assertEquals (1d, pointSE.getX(), 0.0001);
		assertEquals (-1d, pointSE.getY(), 0.0001);
	}
	
	@Test
	void testMyPointMyPoint() {
		MyPoint pointCopy = new MyPoint(pointNE);
		assertEquals (1d, pointCopy.getX(), 0.0001);
		assertEquals (1d, pointCopy.getY(), 0.0001);
	}
	
 
	/*
	 * Test of Getter and Setters
	 */

	@Test
	void testGetSetX() {
		pointOrigine.setX(1d);
		assertEquals (1d, pointOrigine.getX(), 0.0001);
	}

	@Test
	void testGetSetY() {
		pointOrigine.setY(1d);
		assertEquals (1d, pointOrigine.getY(), 0.0001);
	}
	
	@Test
	void testGetSetX2() {
		pointOrigine.setX(-1d);
		assertEquals (-1d, pointOrigine.getX(), 0.0001);
	}

	@Test
	void testGetSetY2() {
		pointOrigine.setY(-1d);
		assertEquals (-1d, pointOrigine.getY(), 0.0001);
	}

	
	@Test
	void testSetXNaN() {
		pointOrigine.setX(0d/0d);
		assertNotEquals(Double.NaN, pointOrigine.getX(), 0.00001);
	}
	
	@Test
	void testSetYNaN() {
		pointOrigine.setY(0d/0d);
		assertNotEquals(Double.NaN, pointOrigine.getY(), 0.00001);
	}
 


	/*
	 * Test function Scale
	 */
	
	@Test
	void testScaleNE() {
		MyPoint mp = pointNE.scale(2d);
		assertEquals (2d, mp.getX(), 0.0001);
		assertEquals (2d, mp.getY(), 0.0001);
	}
	@Test
	void testScaleNW() {
		MyPoint mp = pointNW.scale(2d);
		assertEquals (-2d, mp.getX(), 0.0001);
		assertEquals (2d, mp.getY(), 0.0001);
	}
	@Test
	void testScaleSW() {
		MyPoint mp = pointSW.scale(2d);
		assertEquals (-2d, mp.getX(), 0.0001);
		assertEquals (-2d, mp.getY(), 0.0001);
	}
	@Test
	void testScaleSE() {
		MyPoint mp = pointSE.scale(2d);
		assertEquals (2d, mp.getX(), 0.0001);
		assertEquals (-2d, mp.getY(), 0.0001);
	}
	
	@Test
	void testScalOrigine() {
		MyPoint mp = pointOrigine.scale(2d);
		assertEquals (0d, mp.getX(), 0.0001);
		assertEquals (0d, mp.getY(), 0.0001);
	}
	
	@Test
	void testScalenotModification() {
		MyPoint mp = pointOrigine.scale(0d/0d);
		assertEquals (0, mp.getX(), 0.0001);
		assertEquals (0, mp.getY(), 0.0001);
	}
	
	/*
	 * Test function HorizontalSymetry from origine 0,0
	 */
	
	@Test
	void testHorizontalSymmetryNEfromOrigine() {
		MyPoint horizontal = pointNE.horizontalSymmetry(new MyPoint());
		assertEquals(1d , horizontal.getX(),0.00001);
		assertEquals(-1d , horizontal.getY(),0.00001);
	}
	@Test
	void testHorizontalSymmetryNWfromOrigine() {
		MyPoint horizontal = pointNW.horizontalSymmetry(new MyPoint());
		assertEquals(-1d , horizontal.getX(),0.00001);
		assertEquals(-1d , horizontal.getY(),0.00001);
	}
	@Test
	void testHorizontalSymmetrySWfromOrigine() {
		MyPoint horizontal = pointSW.horizontalSymmetry(new MyPoint());
		assertEquals(-1d , horizontal.getX(),0.00001);
		assertEquals(1d , horizontal.getY(),0.00001);
	}
	@Test
	void testHorizontalSymmetrySEfromOrigine() {
		MyPoint horizontal = pointSE.horizontalSymmetry(new MyPoint());
		assertEquals(1d , horizontal.getX(),0.00001);
		assertEquals(1d , horizontal.getY(),0.00001);
	}
	@Test
	void testHorizontalSymmetryOriginefromOrigine() {
		MyPoint horizontal = pointOrigine.horizontalSymmetry(new MyPoint());
		assertEquals(0d , horizontal.getX(),0.00001);
		assertEquals(0d , horizontal.getY(),0.00001);
	}
	@Test
	void testHorizontalSymmetryOriginefromNull() {
		
	    assertThrows(IllegalArgumentException.class, () -> {
	    	MyPoint horizontal = pointOrigine.horizontalSymmetry(null);
	    });
	}
	
	/*
	 * Test function ComputeAngle
	 * On part de l'origine et on test les 4 axes + les 4 angles entres les axes
	 */
	@Test
	void testComputeAngle() {
		assertEquals(0d,Math.toDegrees(pointOrigine.computeAngle(new MyPoint(0,0))),0.0001);
	}
	@Test
	void testComputeAngle2() {
		assertEquals(45d,Math.toDegrees(pointOrigine.computeAngle(pointNE)),0.0001);
	}
	@Test
	void testComputeAngle3() {
		assertEquals(90d,Math.toDegrees(pointOrigine.computeAngle(new MyPoint(0,1))),0.0001);
	}
	@Test
	void testComputeAngle4() {
		assertEquals(135d,Math.toDegrees(pointOrigine.computeAngle(pointNW)),0.0001);
	}
	@Test
	void testComputeAngle5() {
		assertEquals(180d,Math.toDegrees(pointOrigine.computeAngle(new MyPoint(-1,0))),0.0001);
	}
	@Test
	void testComputeAngle6() {
		assertEquals(-135d,Math.toDegrees(pointOrigine.computeAngle(pointSW)),0.0001);
	}
	@Test
	void testComputeAngle7() {
		assertEquals(-90d,Math.toDegrees(pointOrigine.computeAngle(new MyPoint(0,-1))),0.0001);
	}
	@Test
	void testComputeAngle8() {
		assertEquals(-45d,Math.toDegrees(pointOrigine.computeAngle(pointSE)),0.0001);
	}
	@Test
	void testComputeAngleifNull() {
		assertEquals(Double.NaN,Math.toDegrees(pointOrigine.computeAngle(null)),0.0001);
	}
	
	/*
	 * Test function GetMiddlePoint
	 * On verifie le milieu entre nos 4 points et l'origine et on verifie que le milieu entre 1 point et lui même return lui même
	 */
	@Test
	void testGetMiddlePoint1() {
		MyPoint p = new MyPoint(0.5d,0.5d);
		assertEquals(p.getX(),(pointOrigine.getMiddlePoint(pointNE)).getX());
		assertEquals(p.getY(),(pointOrigine.getMiddlePoint(pointNE)).getY());
	}
	@Test
	void testGetMiddlePoint2() {
		MyPoint p = new MyPoint(-0.5d,0.5d);
		assertEquals(p.getX(),(pointOrigine.getMiddlePoint(pointNW)).getX());
		assertEquals(p.getY(),(pointOrigine.getMiddlePoint(pointNW)).getY());
	}
	@Test
	void testGetMiddlePoint3() {
		MyPoint p = new MyPoint(-0.5d,-0.5d);
		assertEquals(p.getX(),(pointOrigine.getMiddlePoint(pointSW)).getX());
		assertEquals(p.getY(),(pointOrigine.getMiddlePoint(pointSW)).getY());
	}
	@Test
	void testGetMiddlePoint4() {
		MyPoint p = new MyPoint(0.5d,-0.5d);
		assertEquals(p.getX(),(pointOrigine.getMiddlePoint(pointSE)).getX());
		assertEquals(p.getY(),(pointOrigine.getMiddlePoint(pointSE)).getY());
	}
	@Test
	void testGetMiddlePoint5() {
		MyPoint p = new MyPoint(0d,0d);
		assertEquals(p.getX(),(pointOrigine.getMiddlePoint(new MyPoint(0,0))).getX());
		assertEquals(p.getY(),(pointOrigine.getMiddlePoint(new MyPoint(0,0))).getY());
	}
	@Test
	void testGetMiddlePoint6() {
	    assertThrows(IllegalArgumentException.class, () -> {
	    	MyPoint middle = pointOrigine.getMiddlePoint(null);
	    });
	}

	/*
	 * Test function HorizontalSymetry
	 * On utilise en point à l'origine que l'on translate pour arrivé à nos 4 cardinaux 
	 */
	@Test
	void testTranslateDoubleDouble() {
		MyPoint po = new MyPoint();
		po.translate(1, 0);
		assertEquals(1d,po.getX(),0.00001);
		assertEquals(0d,po.getY(),0.00001);
	}
	@Test
	void testTranslateDoubleDouble2() {
		MyPoint po = new MyPoint();
		po.translate(1, 1);
		assertEquals(1d,po.getX(),0.00001);
		assertEquals(1d,po.getY(),0.00001);
	}
	@Test
	void testTranslateDoubleDouble3() {
		MyPoint po = new MyPoint();
		po.translate(-1, 1);
		assertEquals(-1d,po.getX(),0.00001);
		assertEquals(1d,po.getY(),0.00001);
	}
	@Test
	void testTranslateDoubleDouble4() {
		MyPoint po = new MyPoint();
		po.translate(-1, 0);
		assertEquals(-1d,po.getX(),0.00001);
		assertEquals(0d,po.getY(),0.00001);
	}
	
	@Test
	void testTranslateDoubleDouble5() {
		MyPoint po = new MyPoint();
		po.translate(-1, -1);
		assertEquals(-1d,po.getX(),0.00001);
		assertEquals(-1d,po.getY(),0.00001);
	}
	@Test
	void testTranslateDoubleDouble6() {
		MyPoint po = new MyPoint();
		po.translate(0, -1);
		assertEquals(0d,po.getX(),0.00001);
		assertEquals(-1d,po.getY(),0.00001);
	}
	@Test
	void testTranslateDoubleDouble7() {
		MyPoint po = new MyPoint();
		po.translate(1, -1);
		assertEquals(1d,po.getX(),0.00001);
		assertEquals(-1d,po.getY(),0.00001);
	}
	@Test
	void testTranslateDoubleDouble8() {
		MyPoint po = new MyPoint();
	    assertThrows(IllegalArgumentException.class, () -> {
	    	po.translate(Double.NaN,1);
	    });
	}
	@Test
	void testTranslateDoubleDouble9() {
		MyPoint po = new MyPoint();
	    assertThrows(IllegalArgumentException.class, () -> {
	    	po.translate(1,Double.NaN);
	    });
	}
	@Test
	void testTranslateDoubleDouble10() {
		MyPoint po = new MyPoint();
	    assertThrows(IllegalArgumentException.class, () -> {
	    	po.translate(Double.NaN,Double.NaN);
	    });
	}
	
	/*
	 * Test function RotatePoint
	 * Utilisation de la fonction avec les 4 valeurs type en trigonometrie
	 */
	
	@Test
	void testRotatePoint() {
		MyPoint po = pointNE.rotatePoint(pointOrigine, Math.PI);
		assertEquals(-1d,po.getX(),0.00001);
		assertEquals(-1d,po.getY(),0.00001);
	}
	@Test
	void testRotatePoint2() {
		MyPoint po = pointNE.rotatePoint(pointOrigine, Math.PI*2);
		assertEquals(1d,po.getX(),0.00001);
		assertEquals(1d,po.getY(),0.00001);
	}
	@Test
	void testRotatePoint3() {
		MyPoint po = pointNE.rotatePoint(pointOrigine, Math.PI/2);
		assertEquals(-1d,po.getX(),0.00001);
		assertEquals(1d,po.getY(),0.00001);
	}
	@Test
	void testRotatePoint4() {
		MyPoint po = pointNE.rotatePoint(pointOrigine, Math.PI*3/2);
		assertEquals(1d,po.getX(),0.00001);
		assertEquals(-1d,po.getY(),0.00001);
	}
	@Test
	void testRotatePoint5() {
	    assertThrows(IllegalArgumentException.class, () -> {
	    	MyPoint po = pointNE.rotatePoint(null, Math.PI*3/2);
	    });
	}
	@Test
	void testRotatePoint6() {
	    assertThrows(IllegalArgumentException.class, () -> {
	    	MyPoint po = pointNE.rotatePoint(pointOrigine,0d/0d);
	    });
	}

	/*
	 * Test function CentralSymetry
	 * On teste que nos nouveau point sont bien les opposé par symetrie de nos 4 cardinaux
	 */

	@Test
	void testCentralSymmetry() {
		MyPoint po = pointNE.centralSymmetry(pointOrigine);
		assertEquals(-1d,po.getX(),0.00001);
		assertEquals(-1d,po.getY(),0.00001);
	}
	@Test
	void testCentralSymmetry2() {
		MyPoint po = pointNW.centralSymmetry(pointOrigine);
		assertEquals(1d,po.getX(),0.00001);
		assertEquals(-1d,po.getY(),0.00001);
	}
	@Test
	void testCentralSymmetry3() {
		MyPoint po = pointSW.centralSymmetry(pointOrigine);
		assertEquals(1d,po.getX(),0.00001);
		assertEquals(1d,po.getY(),0.00001);
	}
	@Test
	void testCentralSymmetry4() {
		MyPoint po = pointSE.centralSymmetry(pointOrigine);
		assertEquals(-1d,po.getX(),0.00001);
		assertEquals(1d,po.getY(),0.00001);
	}
	@Test
	void testCentralSymmetry5() {
	    assertThrows(IllegalArgumentException.class, () -> {
	    	MyPoint po = pointSE.centralSymmetry(null);
	    });
	}
	
	//Test du setPoint avec la simulation de Random via Mockito
	@Test
	void testSetPoint() {
		 Random randomMockito = Mockito.mock(Random.class);
		 when(randomMockito.nextInt()).thenReturn(5);
		 Random randomMockito2 = Mockito.mock(Random.class);
		 when(randomMockito2.nextInt()).thenReturn(6);
		 //MyPoint po = new MyPoint();
		 pointNE.setPoint(randomMockito, randomMockito2);
		 assertEquals(5d,pointNE.getX(),0.00001);
		 assertEquals(6d,pointNE.getY(),0.00001);	 
	}
	
	//Tests de la fonction ITranslation avec Mockito
	//Test egalement pour le cas null
	@Test
	void testTranslateITranslation() {
		ITranslation it = Mockito.mock(ITranslation.class);
		when(it.getTx()).thenReturn(4);
		when(it.getTy()).thenReturn(3);
		 pointOrigine.translate(it);
		 assertEquals(4d,pointOrigine.getX(),0.00001);
		 assertEquals(3d,pointOrigine.getY(),0.00001);
	}
	@Test
	void testTranslateITranslationIfNull() {
		ITranslation it =null;
		 pointOrigine.translate(it);
		 assertEquals(0d,pointOrigine.getX(),0.00001);
		 assertEquals(0d,pointOrigine.getY(),0.00001);
	}
}
