package com.csci3308.precrastinate;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.csci3308.precrastinate.Group;

import android.test.AndroidTestCase;

/**
 * Unit test cases for Group class
 * 
 * @author Matt Comerford
 * 
 *@version 1.0, 07/01/2014
 *@see 
 */
public class GroupTest extends AndroidTestCase {
	
	public GroupTest() {
		super();
	}

	/* (non-Javadoc)
	 * @see android.test.AndroidTestCase#setUp()
	 */
	@Before
	protected void setUp() throws Exception {
		super.setUp();
	}

	/* (non-Javadoc)
	 * @see android.test.AndroidTestCase#tearDown()
	 */
	@After
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	/**
	 * Test method for {@link com.csci3308.precrastinate.Group#Group()}.
	 */
	@Test
	public void testBlankGroup() {
		Group tester = new Group();
		
		assertEquals(tester.getName(), "");
		assertEquals(tester.getColor(), 5);
		
		assertNotSame(tester.getName(), " ");
		assertNotSame(tester.getColor(), 6);
		assertNotSame(tester.getColor(), 4);
		assertNotSame(tester.getColor(), 3);
		assertNotSame(tester.getColor(), 2);
		assertNotSame(tester.getColor(), 1);
		assertNotSame(tester.getColor(), 0);
	}

	/**
	 * Test method for {@link com.csci3308.precrastinate.Group#Group(java.lang.String, int)}.
	 */
	@Test
	public void testGroup() {
		Group tester = new Group("testGroup", 1);
		
		assertEquals(tester.getName(), "testGroup");
		assertEquals(tester.getColor(), 1);
		
		assertNotSame(tester.getName(), "fakeTestGroup");
		assertNotSame(tester.getColor(), 0);
		assertNotSame(tester.getColor(), 2);
	}

	/**
	 * Test method for {@link com.csci3308.precrastinate.Group#getName()}.
	 */
	@Test
	public void testGetName() {
		Group tester = new Group("testGroup", 1);

		assertEquals(tester.getName(), "testGroup");
		assertNotSame(tester.getName(), "TestGroup");
		assertNotSame(tester.getName(), "testgroup");
		
		tester = new Group();
		
		assertEquals(tester.getName(), "");
		
	}

	/**
	 * Test method for {@link com.csci3308.precrastinate.Group#getColor()}.
	 */
	@Test
	public void testGetColor() {
		Group tester = new Group("testGroup", 1);
		assertEquals("The color value of tester should be equal to 1.", tester.getColor(), 1);
		
		assertNotSame(tester.getColor(), 0);
		assertNotSame(tester.getColor(), 2);
		
		tester = new Group();
		
		assertEquals(tester.getColor(), 5);
		assertNotSame(tester.getColor(), 4);
		assertNotSame(tester.getColor(), 6);
	}
	
	/**
	 * Test method for {@link com.csci3308.precrastinate.Group#setName(java.lang.String)}.
	 */
	@Test
	public void testSetName() {
		Group tester = new Group("testGroup", 1);
		String newTestName = "newTestGroup";
		tester.setName(newTestName);
		
		assertEquals(tester.getName(), newTestName);
		assertNotSame(tester.getName(), "testGroup");
		
		tester = new Group();
		tester.setName("newGroupName");
		
		assertEquals(tester.getName(), "newGroupName");
	}

	/**
	 * Test method for {@link com.csci3308.precrastinate.Group#setColor(int)}.
	 */
	@Test
	public void testSetColor() {
		Group tester = new Group("testGroup", 1);
		int newTestColor = 5;
		tester.setColor(5);
		
		assertEquals("The new color value of tester should be equal to 5.", tester.getColor(), newTestColor);
		assertNotSame(tester.getColor(), 6);
		assertNotSame(tester.getColor(), 4);
		
		tester = new Group();
		tester.setColor(2);
		
		assertEquals("The new color value of tester should be equal to 2.", tester.getColor(), 2);
		assertNotSame(tester.getColor(), 1);
		assertNotSame(tester.getColor(), 3);
	}

}