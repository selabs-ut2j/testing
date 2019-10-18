package main.fr.ut2j.m1ice.ootesting;
import static org.mockito.Mockito.*;


import java.util.Random;

import static org.junit.Assert.*;


import org.junit.Before;

import org.junit.Test;
import org.mockito.Mockito;

public class MyPointTest {
	
	private MyPoint point;
	private MyPoint pointTR;
	private MyPoint pointBL;
	private MyPoint pointTL;
	private MyPoint pointBR;
	
	@Before
	public void setUp() throws Exception {
		point = new MyPoint ();
		pointTR = new MyPoint (1,2);
		pointBL = new MyPoint (-1,-2);
		pointTL = new MyPoint (-1,2);
		pointBR = new MyPoint (1,-2);	
	}
	
	//Teste des constructeurs
	@Test
	public void testMyPoint() {
		assertEquals(point.getX(), 0,0.00001);
		assertEquals(point.getY(), 0,0.00001);
	}

	@Test
	public void testMyPointDoubleDoubleTR() {
		assertEquals(pointTR.getX(), 1,0.00001);
		assertEquals(pointTR.getY(), 2,0.00001);	
	}
	
	@Test
	public void testMyPointDoubleDoubleBL() {
		assertEquals(pointBL.getX(), -1,0.00001);
		assertEquals(pointBL.getY(), -2,0.00001);	
	}
	
	@Test
	public void testMyPointDoubleDoubleTL() {
		assertEquals(pointTL.getX(), -1,0.00001);
		assertEquals(pointTL.getY(), 2,0.00001);	
	}
	
	@Test
	public void testMyPointDoubleDoubleBR() {
		assertEquals(pointBR.getX(), 1,0.00001);
		assertEquals(pointBR.getY(), -2,0.00001);	
	}
	
	@Test
	public void testMyPointDoubleNaNDouble() {
		MyPoint pointNaN = new MyPoint(Math.sqrt(-1),4);
		assertEquals(pointNaN.getX(), 0,0.00001);
		assertEquals(pointNaN.getY(), 0,0.00001);	
	}
	
	@Test
	public void testMyPointDoubleDoubleNaN() {
		MyPoint pointNaN = new MyPoint(4,Math.sqrt(-1));
		assertEquals(pointNaN.getX(), 0,0.00001);
		assertEquals(pointNaN.getY(), 0,0.00001);	
	}

	@Test
	public void testMyPointMyPoint() {
		MyPoint pointCopy = new MyPoint (pointTR);
		assertEquals(pointCopy.getX(), 1,0.00001);
		assertEquals(pointCopy.getY(), 2,0.00001);
	}
	
	@Test
	public void testMyPointMyPointNull() {
		MyPoint pointCopy = new MyPoint (null);
		assertEquals(pointCopy.getX(), 0,0.00001);
		assertEquals(pointCopy.getY(), 0,0.00001);
	}

	//Teste des Setters
	@Test
	public void testSetXPositive() {
		point.setX(4);
		assertEquals(point.getX(), 4,0.00001);
	}
	
	@Test
	public void testSetXNegative() {
		point.setX(-1);
		assertEquals(point.getX(), -1,0.00001);
	}
	
	@Test
	public void testSetYPositive() {
		point.setY(3);
		assertEquals(point.getY(), 3,0.00001);
	}
	
	@Test
	public void testSetYNegative() {
		point.setY(-3);
		assertEquals(point.getY(), -3,0.00001);
	}
	
	@Test
	public void testSetXDoubleNaN() {
		point.setX(Math.sqrt(-1));
		assertEquals(point.getX(), 0,0.00001);
	}

	@Test
	public void testSetYDoubleNaN() {
		point.setY(Math.sqrt(-1));
		assertEquals(point.getY(), 0,0.00001);
	}

	//Teste de la méthode Scale
	@Test
	public void testScaleTR() {
		pointTR = pointTR.scale(2);
		assertEquals(pointTR.getX(), 2,0.00001);
		assertEquals(pointTR.getY(), 4,0.00001);	
	}
	
	@Test
	public void testScaleBL() {
		pointBL = pointBL.scale(2);
		assertEquals(pointBL.getX(), -2,0.00001);
		assertEquals(pointBL.getY(), -4,0.00001);	
	}
	
	@Test
	public void testScaleTL() {
		pointTL = pointTL.scale(2);
		assertEquals(pointTL.getX(), -2,0.00001);
		assertEquals(pointTL.getY(), 4,0.00001);	
	}
	
	@Test
	public void testScaleBR() {
		pointBR = pointBR.scale(2);
		assertEquals(pointBR.getX(), 2,0.00001);
		assertEquals(pointBR.getY(), -4,0.00001);	
	}

	@Test
	public void testScaleZero() {
		pointTR = pointTR.scale(0);
		assertEquals(pointTR.getX(), 0,0.00001);
		assertEquals(pointTR.getY(), 0,0.00001);		
	}
	
	@Test
	public void testScaleDoubleNaN() {
		pointTR = pointTR.scale(Math.sqrt(-1));
		assertEquals(pointTR.getX(), 1,0.00001); 
		assertEquals(pointTR.getY(), 2,0.00001);		
	}
	
	//Teste de horizontalSymetrie pour les 4 points de graphes
	//Suivant un point qui est l'origine
	//D'après les propriétés mathématiques cela parmche pour tous les cas
	@Test
	public void testHorizontalSymmetryTR() {
		pointTR = pointTR.horizontalSymmetry(point);
		assertEquals(pointTR.getX(), 1,0.00001);
		assertEquals(pointTR.getY(), -2,0.00001);
	}
	
	@Test
	public void testHorizontalSymmetryBL() {
		pointBL = pointBL.horizontalSymmetry(point);
		assertEquals(pointBL.getX(), -1,0.00001);
		assertEquals(pointBL.getY(), 2,0.00001);
	}
	
	@Test
	public void testHorizontalSymmetryTL() {
		pointTL = pointTL.horizontalSymmetry(point);
		assertEquals(pointTL.getX(), -1,0.00001);
		assertEquals(pointTL.getY(), -2,0.00001);
	}
	
	@Test
	public void testHorizontalSymmetryBR() {
		pointBR = pointBR.horizontalSymmetry(point);
		assertEquals(pointBR.getX(), 1,0.00001);
		assertEquals(pointBR.getY(), 2,0.00001);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testHorizontalSymmetryAssertion() {
		pointTR = pointTR.horizontalSymmetry(null);
	}
	
	//Teste de la fonction ComputeAngle en choisissant de tester la fonction avec les angles principaux (0,45,90,180,225,-90,-45)
	//Teste aussi pour la valeur null passée en paramètre
	@Test
	public void testComputeAngle() {
		MyPoint point = new MyPoint(1,2);
		assertEquals(Math.toDegrees(pointTR.computeAngle(point)), 90,0.00001);
	}
	
	@Test
	public void testComputeAngle0() {
		MyPoint point = new MyPoint(2,2);
		assertEquals(Math.toDegrees(pointTR.computeAngle(point)), 0,0.00001);
	}
	
	@Test
	public void testComputeAngle45() {
		MyPoint point = new MyPoint(2,3);
		assertEquals(Math.toDegrees(pointTR.computeAngle(point)), 45,0.00001);
	}
	
	@Test
	public void testComputeAngleChangeDivisionWithMultiplication() {
		MyPoint ref = new MyPoint(7,8);
		MyPoint point = new MyPoint(12,10);
		assertEquals(Math.toDegrees(ref.computeAngle(point)),21.8,0.1);
	}
	
	@Test
	public void testComputeAngleChangeDivisionWithMultiplication2() {
		MyPoint ref = new MyPoint(7,8);
		MyPoint point = new MyPoint(-12,10);
		assertEquals(Math.toDegrees(ref.computeAngle(point)),173.9,0.1);
	}
	
	@Test
	public void testComputeAngle90() {
		MyPoint point = new MyPoint(1,3);
		assertEquals(Math.toDegrees(pointTR.computeAngle(point)), 90,0.00001);
	}
	
	@Test
	public void testComputeAngle135() {
		MyPoint point = new MyPoint(0,3);
		assertEquals(Math.toDegrees(pointTR.computeAngle(point)), 135,0.00001);
	}
	
	@Test
	public void testComputeAngle180() {
		MyPoint point = new MyPoint(0,2);
		assertEquals(Math.toDegrees(pointTR.computeAngle(point)), 180,0.00001);
	}
	
	@Test
	public void testComputeAngle225() {
		MyPoint point = new MyPoint(0,1);
		assertEquals(Math.toDegrees(pointTR.computeAngle(point)), -135,0.00001);
	}
	
	@Test
	public void testComputeAngle270() {
		MyPoint point = new MyPoint(1,1);
		assertEquals(Math.toDegrees(pointTR.computeAngle(point)), -90,0.00001);
	}

	@Test
	public void testComputeAngle315() {
		MyPoint point = new MyPoint(2,1);
		assertEquals(Math.toDegrees(pointTR.computeAngle(point)), -45,0.00001);
	}
	
	
	@Test
	public void testComputeAngleNull() {
		MyPoint point = null;
		MyPoint point2 = new MyPoint(1,4);
		assertEquals(Math.toDegrees(point2.computeAngle(point)), Double.NaN,0.00001);
	}
	
	//Teste de la fonction RotatePoint en choisissant de tester la fonction avec les angles principaux (0,45,90,180,225,-90,-45)
	//Teste aussi pour la valeur null passée en paramètre
	@Test
	public void testRotatePointNegatif() {
		MyPoint pivot = new MyPoint(0,0);
		MyPoint newPoint = pointTR.rotatePoint(pivot, Math.toRadians(-84));
		assertEquals (newPoint.getX(),2,0.00001);
		assertEquals (newPoint.getY(),-1,0.00001);
	}
	
	@Test
	public void testRotatePointNegatifBis() {
		MyPoint pivot = new MyPoint(0,0);
		MyPoint newPoint = pointTR.rotatePoint(pivot, Math.toRadians(-17));
		assertEquals (newPoint.getX(),2,0.00001);
		assertEquals (newPoint.getY(),2,0.00001);
	}
	
	@Test
	public void testRotatePoint0() {
		MyPoint pivot = new MyPoint(0,0);
		MyPoint newPoint = pointTR.rotatePoint(pivot, Math.toRadians(0));
		assertEquals (newPoint.getX(),1,0.00001);
		assertEquals (newPoint.getY(),2,0.00001);
	}
	
	@Test
	public void testRotatePoint45() {
		MyPoint pivot = new MyPoint(1,1);
		MyPoint newPoint = pointTR.rotatePoint(pivot, Math.toRadians(45));
		assertEquals (newPoint.getX(),0,0.00001);
		assertEquals (newPoint.getY(),2,0.00001);
	}
	
	@Test
	public void testRotatePoint90() {
		MyPoint pivot = new MyPoint(1,1);
		MyPoint newPoint = pointTR.rotatePoint(pivot, Math.toRadians(90));
		assertEquals (newPoint.getX(),0,0.00001);
		assertEquals (newPoint.getY(),1,0.00001);
	}
	
	@Test
	public void testRotatePoint135() {
		MyPoint pivot = new MyPoint(1,1);
		MyPoint newPoint = pointTR.rotatePoint(pivot, Math.toRadians(135));
		assertEquals (newPoint.getX(),0,0.00001);
		assertEquals (newPoint.getY(),0,0.00001);
	}
	
	@Test
	public void testRotatePoint180() {
		MyPoint pivot = new MyPoint(1,1);
		MyPoint newPoint = pointTR.rotatePoint(pivot, Math.toRadians(180));
		assertEquals (newPoint.getX(),1,0.00001);
		assertEquals (newPoint.getY(),0,0.00001);
	}
	
	@Test
	public void testRotatePoint225() {
		MyPoint pivot = new MyPoint(1,1);
		MyPoint newPoint = pointTR.rotatePoint(pivot, Math.toRadians(225));
		assertEquals (newPoint.getX(),2,0.00001);
		assertEquals (newPoint.getY(),0,0.00001);
	}

	@Test
	public void testRotatePoint270() {
		MyPoint pivot = new MyPoint(1,1);
		MyPoint newPoint = pointTR.rotatePoint(pivot, Math.toRadians(-90));
		assertEquals (newPoint.getX(),2,0.00001);
		assertEquals (newPoint.getY(),1,0.00001);
	}
	
	
	@Test
	public void testRotatePoint315() {
		MyPoint pivot = new MyPoint(1,1);
		MyPoint newPoint = pointTR.rotatePoint(pivot, Math.toRadians(-45));
		assertEquals (newPoint.getX(),2,0.00001);
		assertEquals (newPoint.getY(),2,0.00001);
	}
	
	@Test
	public void testRotatePointNegative315() {
		MyPoint pivot = new MyPoint(-1,-1);
		MyPoint newPoint = pointBL.rotatePoint(pivot, Math.toRadians(90));
		assertEquals (newPoint.getX(),0,0.00001);
		assertEquals (newPoint.getY(),-1,0.00001);
	}
	
	@Test
	public void testRotatePointNull() {
		MyPoint newPoint = pointTR.rotatePoint(null, Math.toRadians(0));
		assertEquals (newPoint,null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testRotatePointDoubleNaN() {
		MyPoint pivot = new MyPoint(-1,-1);
		pointBL.rotatePoint(pivot, Math.toRadians(Math.sqrt(-1)));
	}
	
	//Teste de la fonction centralSymmetry
	@Test
	public void testCentralSymmetryTR() {
		MyPoint pivot = new MyPoint(0,0);
		MyPoint newPoint = pointTR.centralSymmetry(pivot);
		assertEquals (newPoint.getX(),-1,0.00001);
		assertEquals (newPoint.getY(),-2,0.00001);
	}
	
	@Test
	public void testCentralSymmetryBL() {
		MyPoint pivot = new MyPoint(0,0);
		MyPoint newPoint = pointBL.centralSymmetry(pivot);
		assertEquals (newPoint.getX(),1,0.00001);
		assertEquals (newPoint.getY(),2,0.00001);
	}
	
	@Test
	public void testCentralSymmetryBR() {
		MyPoint pivot = new MyPoint(0,0);
		MyPoint newPoint = pointBR.centralSymmetry(pivot);
		assertEquals (newPoint.getX(),-1,0.00001);
		assertEquals (newPoint.getY(),2,0.00001);
	}
	
	@Test
	public void testCentralSymmetryTL() {
		MyPoint pivot = new MyPoint(0,0);
		MyPoint newPoint = pointTL.centralSymmetry(pivot);
		assertEquals (newPoint.getX(),1,0.00001);
		assertEquals (newPoint.getY(),-2,0.00001);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testCentralSymmetryNULL () {
		pointTR.centralSymmetry(null);
	}
	
	//Teste de la fonction getMiddlePoint
	@Test
	public void testGetMiddlePointTR() {	
		MyPoint pivot = new MyPoint(-1,0);
		MyPoint newPoint = pointTR.getMiddlePoint(pivot);
		assertEquals (newPoint.getX(),0,0.00001);
		assertEquals (newPoint.getY(),1,0.00001);
	}
	
	@Test
	public void testGetMiddlePointBL() {
		MyPoint pivot = new MyPoint(1,0);
		MyPoint newPoint = pointBL.getMiddlePoint(pivot);
		assertEquals (newPoint.getX(),0,0.00001);
		assertEquals (newPoint.getY(),-1,0.00001);
	}
	
	@Test
	public void testGetMiddlePointBR() {
		MyPoint pivot = new MyPoint(-1,0);
		MyPoint newPoint = pointBR.getMiddlePoint(pivot);
		assertEquals (newPoint.getX(),0,0.00001);
		assertEquals (newPoint.getY(),-1,0.00001);
	}
	
	@Test
	public void testGetMiddlePointTL() {
		MyPoint pivot = new MyPoint(1,0);
		MyPoint newPoint = pointTL.getMiddlePoint(pivot);
		assertEquals (newPoint.getX(),0,0.00001);
		assertEquals (newPoint.getY(),1,0.00001);
	}
	
	@Test
	public void testGetMiddlePointDoubleDivision() {
		MyPoint pivot = new MyPoint(5,8);
		MyPoint newPoint = pointTL.getMiddlePoint(pivot);
		assertEquals (newPoint.getX(),2,0.00001);
		assertEquals (newPoint.getY(),5,0.00001);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testGetMiddlePointNull() {
		pointTL.getMiddlePoint(null);
	}
	
	//Teste de la fonction Translate pour les points du graphe et pour les valeurs Double.NaN passées en paramètres 
	@Test
	public void testTranslateDoubleDoubleTR() {
		pointTR.translate(1,1);
		assertEquals (pointTR.getX(),2,0.00001);
		assertEquals (pointTR.getY(),3,0.00001);
	}
	
	@Test
	public void testTranslateDoubleDoubleBL() {
		pointBL.translate(1,1);
		assertEquals (pointBL.getX(),0,0.00001);
		assertEquals (pointBL.getY(),-1,0.00001);
	}
	
	@Test
	public void testTranslateDoubleDoubleTL() {
		pointTL.translate(1,1);
		assertEquals (pointTL.getX(),0,0.00001);
		assertEquals (pointTL.getY(),3,0.00001);
	}
	
	@Test
	public void testTranslateDoubleDoubleBR() {
		pointBR.translate(1,1);
		assertEquals (pointBR.getX(),2,0.00001);
		assertEquals (pointBR.getY(),-1,0.00001);
	}
	
	@Test
	public void testTranslateDoubleNaNDouble() {
		pointTR.translate(Math.sqrt(-1),1);
		assertEquals (pointTR.getX(),1,0.00001);
		assertEquals (pointTR.getY(),2,0.00001);
	}
	
	@Test
	public void testTranslateDoubleDoubleNaN() {
		pointTR.translate(1,Math.sqrt(-1));
		assertEquals (pointTR.getX(),1,0.00001);
		assertEquals (pointTR.getY(),2,0.00001);
	}
	
	@Test
	public void testTranslateDoubleNaNDoubleNaN() {
		pointTR.translate(Math.sqrt(-1),Math.sqrt(-1));
		assertEquals (pointTR.getX(),1,0.00001);
		assertEquals (pointTR.getY(),2,0.00001);
	}

	//Teste de la fonction setPoint à l'aide de Mockito pour la fonction nextInt pour les 4 points du graphe
	@Test
	public void testSetPointTR() {
		Random randomNumberMock = Mockito.mock(Random.class);
    	when(randomNumberMock.nextInt()).thenReturn(5);
    	Random randomNumberMock2 = Mockito.mock(Random.class);
    	when(randomNumberMock2.nextInt()).thenReturn(6);
    	pointTR.setPoint(randomNumberMock, randomNumberMock2);
    	assertEquals (pointTR.getX(),5,0.00001);
		assertEquals (pointTR.getY(),6,0.00001);
	}
	
	@Test
	public void testSetPointBL() {
		Random randomNumberMock = Mockito.mock(Random.class);
    	when(randomNumberMock.nextInt()).thenReturn(5);
    	Random randomNumberMock2 = Mockito.mock(Random.class);
    	when(randomNumberMock2.nextInt()).thenReturn(6);
    	pointBL.setPoint(randomNumberMock, randomNumberMock2);
    	assertEquals (pointBL.getX(),5,0.00001);
		assertEquals (pointBL.getY(),6,0.00001);
	}
	
	@Test
	public void testSetPointTL() {
		Random randomNumberMock = Mockito.mock(Random.class);
    	when(randomNumberMock.nextInt()).thenReturn(5);
    	Random randomNumberMock2 = Mockito.mock(Random.class);
    	when(randomNumberMock2.nextInt()).thenReturn(6);
    	pointTL.setPoint(randomNumberMock, randomNumberMock2);
    	assertEquals (pointTL.getX(),5,0.00001);
		assertEquals (pointTL.getY(),6,0.00001);
	}
	
	@Test
	public void testSetPointBR() {
		Random randomNumberMock = Mockito.mock(Random.class);
    	when(randomNumberMock.nextInt()).thenReturn(5);
    	Random randomNumberMock2 = Mockito.mock(Random.class);
    	when(randomNumberMock2.nextInt()).thenReturn(6);
    	pointBR.setPoint(randomNumberMock, randomNumberMock2);
    	assertEquals (pointBR.getX(),5,0.00001);
		assertEquals (pointBR.getY(),6,0.00001);
	}
	
	//Teste de l'interface avec Mockito
	@Test
	public void testTranslateITranslation() {
		ITranslation mockService = mock(ITranslation.class);
		when(mockService.getTx()).thenReturn(3);
		when(mockService.getTy()).thenReturn(3);
		pointTR.translate(mockService);
		assertEquals (pointTR.getX(),4,0.00001);
		assertEquals (pointTR.getY(),5,0.00001);
	}
	
	@Test
	public void testTranslateITranslationNull() {
		pointTR.translate(null);
		assertEquals (pointTR.getX(),1,0.00001);
		assertEquals (pointTR.getY(),2,0.00001);
	}

}
