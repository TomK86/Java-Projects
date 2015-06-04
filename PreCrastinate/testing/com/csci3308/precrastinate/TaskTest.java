package com.csci3308.precrastinate;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.csci3308.precrastinate.Task;

import android.test.AndroidTestCase;

/**
 * @author Matt Comerford
 *
 *@version 1.0, 07/01/2014
 */
public class TaskTest extends AndroidTestCase {

	public TaskTest() {
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
	 * Test method for {@link com.csci3308.precrastinate.Task#Task(java.lang.String, long, float, int, boolean)}.
	 */
	@Test
	public void testTaskStringLongFloatIntBoolean() {
		Task tester = new Task("testTitle", 20010101, (float) 1.0, 1, true);
		
		assertEquals(tester.getTaskTitle(), "testTitle");
		assertNotSame(tester.getTaskTitle(), "");
		
		assertEquals(tester.getDueDateAsLong(), 20010101);
		assertNotSame(tester.getDueDateAsLong(), 00000000);
		
		assertEquals(tester.getDueDateAsString(), "1 / 1 / 2001");
		assertNotSame(tester.getDueDateAsString(), "1/1/2001");
		assertNotSame(tester.getDueDateAsString(), "01 / 01 / 2001");
		
		assertEquals(tester.getPriority(), (float) 1.0);
		assertNotSame(tester.getPriority(), (float) 1.1);
		assertNotSame(tester.getPriority(), (float) 0.9);
		assertNotSame(tester.getPriority(), 1);
		
		assertEquals(tester.getGroupNum(), 1);
		assertNotSame(tester.getGroupNum(), 0);
		assertNotSame(tester.getGroupNum(), 2);
		
		assertEquals(tester.getCompleted(), true);
		assertNotSame(tester.getCompleted(), false);
	}

	/**
	 * Test method for {@link com.csci3308.precrastinate.Task#Task(java.lang.String, java.lang.String, java.lang.String, java.lang.String, float, int, boolean)}.
	 */
	@Test
	public void testTaskStringStringStringStringFloatIntBoolean() {
		Task tester = new Task("testTitle", "01", "01", "2001", (float) 1.0, 1, true);
		
		assertEquals(tester.getTaskTitle(), "testTitle");
		assertNotSame(tester.getTaskTitle(), "");
		
		assertEquals(tester.getDueDateAsLong(), 20010101);
		assertNotSame(tester.getDueDateAsLong(), 00000000);
		
		assertEquals(tester.getDueDateAsString(), "01 / 01 / 2001");
		assertNotSame(tester.getDueDateAsString(), "01/01/2001");
		assertNotSame(tester.getDueDateAsString(), "1 / 1 / 2001");
		
		assertEquals(tester.getPriority(), (float) 1.0);
		assertNotSame(tester.getPriority(), (float) 1.1);
		assertNotSame(tester.getPriority(), (float) 0.9);
		assertNotSame(tester.getPriority(), 1);
		
		assertEquals(tester.getGroupNum(), 1);
		assertNotSame(tester.getGroupNum(), 0);
		assertNotSame(tester.getGroupNum(), 2);
		
		assertEquals(tester.getCompleted(), true);
		assertNotSame(tester.getCompleted(), false);
	}

	/**
	 * Test method for {@link com.csci3308.precrastinate.Task#Task(java.lang.String, java.lang.String, float, int, boolean)}.
	 */
	@Test
	public void testTaskStringStringFloatIntBoolean() {
		Task tester = new Task("testTitle", "01 / 01 / 2001", (float) 1.0, 1, true);
		
		assertEquals(tester.getTaskTitle(), "testTitle");
		assertNotSame(tester.getTaskTitle(), "");
		
		assertEquals(tester.getDueDateAsLong(), 20010101);
		assertNotSame(tester.getDueDateAsLong(), 00000000);
		
		assertEquals(tester.getDueDateAsString(), "01 / 01 / 2001");
		assertNotSame(tester.getDueDateAsString(), "01/01/2001");
		assertNotSame(tester.getDueDateAsString(), "1 / 1 / 2001");
		
		assertEquals(tester.getPriority(), (float) 1.0);
		assertNotSame(tester.getPriority(), (float) 1.1);
		assertNotSame(tester.getPriority(), (float) 0.9);
		assertNotSame(tester.getPriority(), 1);
		
		assertEquals(tester.getGroupNum(), 1);
		assertNotSame(tester.getGroupNum(), 0);
		assertNotSame(tester.getGroupNum(), 2);
		
		assertEquals(tester.getCompleted(), true);
		assertNotSame(tester.getCompleted(), false);
	}

	/**
	 * Test method for {@link com.csci3308.precrastinate.Task#Task(java.lang.String, int, int, int, float, int, boolean)}.
	 */
	@Test
	public void testTaskStringIntIntIntFloatIntBoolean() {
		Task tester = new Task("testTitle", 01, 01, 2001, (float) 1.0, 1, true);
		
		assertEquals(tester.getTaskTitle(), "testTitle");
		assertNotSame(tester.getTaskTitle(), "");
		
		assertEquals(tester.getDueDateAsLong(), 20010101);
		assertNotSame(tester.getDueDateAsLong(), 00000000);
		
		assertEquals(tester.getDueDateAsString(), "1 / 1 / 2001");
		assertNotSame(tester.getDueDateAsString(), "1/1/2001");
		assertNotSame(tester.getDueDateAsString(), "01 / 01 / 2001");
		
		assertEquals(tester.getPriority(), (float) 1.0);
		assertNotSame(tester.getPriority(), (float) 1.1);
		assertNotSame(tester.getPriority(), (float) 0.9);
		assertNotSame(tester.getPriority(), 1);
		
		assertEquals(tester.getGroupNum(), 1);
		assertNotSame(tester.getGroupNum(), 0);
		assertNotSame(tester.getGroupNum(), 2);
		
		assertEquals(tester.getCompleted(), true);
		assertNotSame(tester.getCompleted(), false);
	}

	/**
	 * Test method for {@link com.csci3308.precrastinate.Task#Task()}.
	 */
	@Test
	public void testTask() {
		Task tester = new Task();
		
		assertEquals(tester.getTaskTitle(), "");
		assertNotSame(tester.getTaskTitle(), " ");
		
		assertEquals(tester.getDueDateAsLong(), 0);
		assertNotSame(tester.getDueDateAsLong(), 00000000);
		
		assertEquals(tester.getDueDateAsString(), " /  / ");
		assertNotSame(tester.getDueDateAsString(), "//");
		assertNotSame(tester.getDueDateAsString(), "");
		
		assertEquals(tester.getPriority(), (float) 0);
		assertNotSame(tester.getPriority(), (float) 0.0001);
		
		assertEquals(tester.getGroupNum(), 0);
		assertNotSame(tester.getGroupNum(), 1);
		
		assertEquals(tester.getCompleted(), false);
		assertNotSame(tester.getCompleted(), true);
	}

	/**
	 * Test method for {@link com.csci3308.precrastinate.Task#getTaskTitle()}.
	 */
	@Test
	public void testGetTaskTitle() {
		Task tester = new Task();
		tester.setTaskTitle("testTitle");
		
		assertEquals(tester.getTaskTitle(), "testTitle");
		assertNotSame(tester.getTaskTitle(), "testtitle");
		assertNotSame(tester.getTaskTitle(), "TESTTITLE");
		assertNotSame(tester.getTaskTitle(), "TestTitle");
	}

	/**
	 * Test method for {@link com.csci3308.precrastinate.Task#getDueDateAsLong()}.
	 */
	@Test
	public void testGetDueDateAsLong() {
		Task tester = new Task();
		
		tester.setDueDateFromString("01", "01", "2001");
		
		assertEquals(tester.getDueDateAsLong(), 20010101);
		assertNotSame(tester.getDueDateAsLong(), 200111);
		assertNotSame(tester.getDueDateAsLong(), "01 / 01 / 2001");
		
		tester.setDueDateFromString("02 / 02 / 2222");
		
		assertEquals(tester.getDueDateAsLong(), 22220202);
		assertNotSame(tester.getDueDateAsLong(), 222222);
		assertNotSame(tester.getDueDateAsLong(), "02 / 02 / 2222");
		
		tester.setDueDateFromInt(03, 03, 3333);
		
		assertEquals(tester.getDueDateAsLong(), 33330303);
		assertNotSame(tester.getDueDateAsLong(), 333333);
		assertNotSame(tester.getDueDateAsLong(), "03 / 03 / 3333");
		
		tester.setDueDateFromLong(44440404);
		
		assertEquals(tester.getDueDateAsLong(), 44440404);
		assertNotSame(tester.getDueDateAsLong(), 444444);
		assertNotSame(tester.getDueDateAsLong(), "04 / 04 / 4444");
	}

	/**
	 * Test method for {@link com.csci3308.precrastinate.Task#getDueDateAsString()}.
	 */
	@Test
	public void testGetDueDateAsString() {
		Task tester = new Task();
		
		tester.setDueDateFromString("01", "01", "2001");
		
		assertEquals(tester.getDueDateAsString(), "01 / 01 / 2001");
		assertNotSame(tester.getDueDateAsString(), "01/01/2001");
		assertNotSame(tester.getDueDateAsString(), "1 / 1 / 2001");
		
		tester.setDueDateFromString("02 / 02 / 2222");
		
		assertEquals(tester.getDueDateAsString(), "02 / 02 / 2222");
		assertNotSame(tester.getDueDateAsString(), "02/02/2222");
		assertNotSame(tester.getDueDateAsString(), "2 / 2 / 2222");
		
		tester.setDueDateFromInt(03, 03, 3333);
		
		assertEquals(tester.getDueDateAsString(), "3 / 3 / 3333");
		assertNotSame(tester.getDueDateAsString(), "3/3/3333");
		assertNotSame(tester.getDueDateAsString(), "03 / 03 / 3333");
		
		tester.setDueDateFromLong(44440404);
		
		assertEquals(tester.getDueDateAsString(), "4 / 4 / 4444");
		assertNotSame(tester.getDueDateAsString(), "4/4/4444");
		assertNotSame(tester.getDueDateAsString(), "04 / 04 / 4444");
	}

	/**
	 * Test method for {@link com.csci3308.precrastinate.Task#getPriority()}.
	 */
	@Test
	public void testGetPriority() {
		Task tester = new Task();
		
		tester.setPriority((float) 2.5);
		
		assertEquals(tester.getPriority(), (float) 2.5);
		assertNotSame(tester.getPriority(), (float) 2.6);
		assertNotSame(tester.getPriority(), (float) 2.7);
		assertNotSame(tester.getPriority(), (float) 0.0);
		assertNotSame(tester.getPriority(), (float) 10000000000.0);
		assertNotSame(tester.getPriority(), "test");
		
		tester.setPriority((float) 1.0);
		
		assertEquals(tester.getPriority(), (float) 1.0);
		assertNotSame(tester.getPriority(), (float) 0.999);
		assertNotSame(tester.getPriority(), (float) 1.001);
		assertNotSame(tester.getPriority(), (float) 0.0);
		assertNotSame(tester.getPriority(), (float) 10000000000.0);
		assertNotSame(tester.getPriority(), "test");
	}

	/**
	 * Test method for {@link com.csci3308.precrastinate.Task#getGroupNum()}.
	 */
	@Test
	public void testGetGroupNum() {
		Task tester = new Task();
		
		tester.setGroupNum(3);
		
		assertEquals(tester.getGroupNum(), 3);
		assertNotSame(tester.getGroupNum(), 2);
		assertNotSame(tester.getGroupNum(), 4);
		assertNotSame(tester.getGroupNum(), 0);
		assertNotSame(tester.getGroupNum(), 999999999);
		assertNotSame(tester.getGroupNum(), "test");
		
		tester.setGroupNum(1);
		
		assertEquals(tester.getGroupNum(), 1);
		assertNotSame(tester.getGroupNum(), 2);
		assertNotSame(tester.getGroupNum(), 0);
		assertNotSame(tester.getGroupNum(), 999999999);
		assertNotSame(tester.getGroupNum(), "test");
	}

	/**
	 * Test method for {@link com.csci3308.precrastinate.Task#getCompleted()}.
	 */
	@Test
	public void testGetCompleted() {
		Task tester = new Task();
		
		tester.setCompleted(true);
		
		assertEquals(tester.getCompleted(), true);
		assertNotSame(tester.getCompleted(), false);
		assertNotSame(tester.getCompleted(), "test");
		
		
		tester.setCompleted(false);
		
		assertEquals(tester.getCompleted(), false);
		assertNotSame(tester.getCompleted(), true);
		assertNotSame(tester.getCompleted(), "test");
	}

	/**
	 * Test method for {@link com.csci3308.precrastinate.Task#setTaskTitle(java.lang.String)}.
	 */
	@Test
	public void testSetTaskTitle() {
		Task tester = new Task();
		
		tester.setTaskTitle("testTitle");
		
		assertEquals(tester.getTaskTitle(), "testTitle");
		assertNotSame(tester.getTaskTitle(), "fakeTitle");
		assertNotSame(tester.getTaskTitle(), 0);
		
		tester.setTaskTitle("newTestTitle");
		
		assertEquals(tester.getTaskTitle(), "newTestTitle");
		assertNotSame(tester.getTaskTitle(), "testTitle");
		assertNotSame(tester.getTaskTitle(), 0);
	}

	/**
	 * Test method for {@link com.csci3308.precrastinate.Task#setDueDateFromLong(long)}.
	 */
	@Test
	public void testSetDueDateFromLong() {
		Task tester = new Task();
		
		tester.setDueDateFromLong(20010101);
		
		assertEquals(tester.getDueDateAsString(), "1 / 1 / 2001");
		assertNotSame(tester.getDueDateAsString(), "1/1/2001");
		assertNotSame(tester.getDueDateAsString(), "01 / 01 / 2001");
		
		tester.setDueDateFromLong(22220202);
		
		assertEquals(tester.getDueDateAsString(), "2 / 2 / 2222");
		assertNotSame(tester.getDueDateAsString(), "2/2/2222");
		assertNotSame(tester.getDueDateAsString(), "02 / 02 / 2222");
	}

	/**
	 * Test method for {@link com.csci3308.precrastinate.Task#setDueDateFromString(java.lang.String)}.
	 */
	@Test
	public void testSetDueDateFromStringString() {
		Task tester = new Task();
		
		tester.setDueDateFromString("01 / 01 / 2000");
		
		assertEquals(tester.getDueDateAsString(), "01 / 01 / 2000");
		assertNotSame(tester.getDueDateAsString(), "01/01/2000");
		assertNotSame(tester.getDueDateAsString(), "1 / 1 / 2000");
		
		tester.setDueDateFromString("02 / 02 / 2222");
		
		assertEquals(tester.getDueDateAsString(), "02 / 02 / 2222");
		assertNotSame(tester.getDueDateAsString(), "02/02/2222");
		assertNotSame(tester.getDueDateAsString(), "2 / 2 / 2222");
	}

	/**
	 * Test method for {@link com.csci3308.precrastinate.Task#setDueDateFromString(java.lang.String, java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testSetDueDateFromStringStringStringString() {
		Task tester = new Task();
		
		tester.setDueDateFromString("01", "01", "2000");
		
		assertEquals(tester.getDueDateAsString(), "01 / 01 / 2000");
		assertNotSame(tester.getDueDateAsString(), "01/01/2000");
		assertNotSame(tester.getDueDateAsString(), "1 / 1 / 2000");
		
		tester.setDueDateFromString("02", "02", "2222");
		
		assertEquals(tester.getDueDateAsString(), "02 / 02 / 2222");
		assertNotSame(tester.getDueDateAsString(), "02/02/2222");
		assertNotSame(tester.getDueDateAsString(), "2 / 2 / 2222");
	}

	/**
	 * Test method for {@link com.csci3308.precrastinate.Task#setDueDateFromInt(int, int, int)}.
	 */
	@Test
	public void testSetDueDateFromInt() {
		Task tester = new Task();
		
		tester.setDueDateFromInt(1, 1, 2000);
		
		assertEquals(tester.getDueDateAsString(), "1 / 1 / 2000");
		assertNotSame(tester.getDueDateAsString(), "01 / 01 / 2000");
		assertNotSame(tester.getDueDateAsString(), "1/1/2000");
		
		tester.setDueDateFromInt(13, 32, 2017);
		assertEquals(tester.getDueDateAsString(), "13 / 32 / 2017");
		assertNotSame(tester.getDueDateAsString(), "13/32/2007");
	}
	
	/**
	 * Test method for {@link com.csci3308.precrastinate.Task#setPriority(float)}.
	 */
	@Test
	public void testSetPriority() {
		Task tester = new Task();
		
		tester.setPriority((float) 3.0);
		
		assertEquals(tester.getPriority(), (float) 3.0);
		assertNotSame(tester.getPriority(), 3.0);
		assertNotSame(tester.getPriority(), (float) 3.1);
		assertNotSame(tester.getPriority(), (float) 2.9);
		
		tester.setPriority((float) 0.0);
		
		assertEquals(tester.getPriority(), (float) 0.0);
		assertNotSame(tester.getPriority(), 0.0);
		assertNotSame(tester.getPriority(), (float) 0.1);
		assertNotSame(tester.getPriority(), (float) 0.00000001);
	}

	/**
	 * Test method for {@link com.csci3308.precrastinate.Task#setGroupNum(int)}.
	 */
	@Test
	public void testSetGroupNum() {
		Task tester = new Task();
		
		tester.setGroupNum(1);
		
		assertEquals(tester.getGroupNum(), 1);
		assertNotSame(tester.getGroupNum(), 0);
		assertNotSame(tester.getGroupNum(), 2);

		tester.setGroupNum(5);
		
		assertEquals(tester.getGroupNum(), 5);
		assertNotSame(tester.getGroupNum(), 4);
		assertNotSame(tester.getGroupNum(), 6);
	}

	/**
	 * Test method for {@link com.csci3308.precrastinate.Task#setCompleted(boolean)}.
	 */
	@Test
	public void testSetCompleted() {
		Task tester = new Task();
		
		tester.setCompleted(true);
		
		assertEquals(tester.getCompleted(), true);
		assertNotSame(tester.getCompleted(), false);
		
		tester.setCompleted(false);
		
		assertEquals(tester.getCompleted(), false);
		assertNotSame(tester.getCompleted(), true);
	}

}