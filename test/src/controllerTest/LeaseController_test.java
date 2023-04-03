package test.src.controllerTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import controller.LeaseController;
import controller.TenantController;
import model.Lease;
import model.Tenant;

public class LeaseController_test {

	Lease l = new Lease();
	Tenant t = new Tenant();
	@Before
	public void setUp() throws Exception {
		
		for(int i = 0; i < 3; i++){
			
			// define lease

			l.setID();
			l.setLeaseStartDate("01-10-2020");
			l.setLeaseEndDate("30-09-2021");
			l.hasPaidRent = true;
			l.setMonthlyRentAmount(1200.00);
			
			LeaseController.leases.add(l);		
			
			// define tenant

			t.setID();
			t.setFirstName("Tenant-" + i);
			t.setLastName("LN-" + i);
			t.setEmail("abc@gmail.com");
			t.isCurrentTenant = true;
			t.buildingTenantID = i;
			t.setApartmentNum(i);
						
			TenantController.tenants.add(t);
		}
	}

	@Test
	public void testAddNewLease() {
		LeaseController leaseCtl = new LeaseController();
		int count = LeaseController.leases.size();
		leaseCtl.addNewLease(1, "01-10-2020", "30-09-2021", 1200.0, true);
		assertEquals(LeaseController.leases.size(), count+1);
	}
	
	@Test
	public void displayLeases(){
		
		LeaseController leaseCtl = new LeaseController();
		int id1 = leaseCtl.leases.get(0).getLeaseID();
		assertEquals(id1, LeaseController.leases.get(0).leaseID);
		
		TenantController tenantCtl = new TenantController();
		int id2 = tenantCtl.tenants.get(0).getTenantID();
		assertEquals(id2, TenantController.tenants.get(0).tenantID);
		
		String leaseStartDate = leaseCtl.leases.get(0).leaseStartDate;
		assertSame(leaseStartDate, LeaseController.leases.get(0).leaseStartDate);
		
		String leaseEndDate = leaseCtl.leases.get(0).leaseEndDate;
		assertSame(leaseEndDate, LeaseController.leases.get(0).leaseEndDate);
		
	}

	@Test
	public void testGetRentAmount() {
		
		LeaseController leaseCtl = new LeaseController();
		Double amountexpected = 1200.00;
		Double amount = leaseCtl.getRentAmount(LeaseController.leases, TenantController.tenants, t.getTenantID());
		assertEquals(amountexpected, amount);
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
		Boolean b = leaseCtl.checkIfPaid(LeaseController.leases, TenantController.tenants, t.getTenantID());
		assertTrue(b);
	}

}
