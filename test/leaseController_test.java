package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import controller.LeaseController;

public class leaseController_test {

	@Before
	public void setUp() throws Exception {

	}

	@Test
	public void testAddNewLease() {
		LeaseController leaseCtl = new LeaseController();
		int count = LeaseController.leases.size();
		leaseCtl.addNewLease(1, "09-10-2020", "22-09-2021", 1200.0, true);
		assertEquals(LeaseController.leases.size(), count+1);
	}

	@Test
	public void testDisplayLeases() {
		//fail("Not yet implemented");
	}

	@Test
	public void testGetRentAmount() {
		//fail("Not yet implemented");
	}

	@Test
	public void testPayRent() {
		//fail("Not yet implemented");
	}

	@Test
	public void testCheckIfPaid() {
		//fail("Not yet implemented");
	}

}
