package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import controller.LeaseController;
import controller.TenantController;
import model.Lease;
import model.Tenant;

public class leaseController_test {

	
	@Before
	public void setUp() throws Exception {
		
		for(int i = 0; i < 3; i++){
			
			// define lease
			Lease l = new Lease();
			l.setID();
			l.setLeaseStartDate("01-10-2020");
			l.setLeaseEndDate("30-09-2021");
			l.hasPaidRent = true;
			
			LeaseController.leases.add(l);
			
			
			
			// define tenant
			Tenant t = new Tenant();
			t.setID();
			t.setFirstName("Tenant-" + i);
			t.setLastName("LN-" + i);
			t.setEmail("abc@gmail.com");
			t.isCurrentTenant = true;
			t.buildingName = "Building-" + i;
			t.setApartmentNum(i);
						
			TenantController.tenants.add(t);
		}
	}

	@Test
	public void testAddNewLease() {
		LeaseController leaseCtl = new LeaseController();
		int count = LeaseController.leases.size();
		leaseCtl.addNewLease(1, "01-10-2020", "c", 1200.0, true);
		assertEquals(LeaseController.leases.size(), count+1);
	}

	@Test
	public void testGetRentAmount() {
		
		LeaseController leaseCtl = new LeaseController();
		Double amount = leaseCtl.getRentAmount(LeaseController.leases, TenantController.tenants, 0);
		assertEquals(amount, LeaseController.leases.get(0).monthlyRentAmount);
	}

	@Test
	public void testPayRent() {
		
		LeaseController leaseCtl = new LeaseController();
		Boolean b = leaseCtl.payRent(LeaseController.leases, 1, TenantController.tenants, 1200, 1200);
		assertTrue(b);
	}

	@Test
	public void testCheckIfPaid() {
		
		LeaseController leaseCtl = new LeaseController();
		Boolean b = leaseCtl.checkIfPaid(LeaseController.leases, TenantController.tenants, 1);
		assertTrue(b);
	}

}
