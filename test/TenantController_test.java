/**
 * 
 */
package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import controller.LeaseController;
import controller.TenantController;
import model.Lease;
import model.Tenant;

/**
 * @author Omar
 *
 */
public class TenantController_test {
	TenantController tenantCtl = new TenantController();
	Tenant t = new Tenant();
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		
//for(int i = 0; i < 3; i++){
			// define tenant

			t.setID();
			t.setFirstName("Tenant-1");
			t.setLastName("LN-1");
			t.setEmail("abc@gmail.com");
			t.isCurrentTenant = true;
			t.buildingTenantID =  1;
			t.setApartmentNum(1);
						
			TenantController.tenants.add(t);
		}
		
	//}


	/**
	 * Test method for {@link controller.TenantController#addNewTenant(boolean, int, java.lang.String, java.lang.String, java.lang.String, java.lang.String, int)}.
	 */
	@Test
	public void testAddNewTenant() {
		

		int count = TenantController.tenants.size();
		tenantCtl.addNewTenant(true, 1, "tenant-1", "LN-1", "abc@gmail.com", 1, 1);
		assertEquals(TenantController.tenants.size(), count+1);
		
	}

	/**
	 * Test method for {@link controller.TenantController#DisplayCurrentTenants(java.util.ArrayList)}.
	 */
	@Test
	public void testDisplayCurrentTenants() {
		
//		TenantController tenantCtl = new TenantController();
		
		int idExpected = t.getTenantID();
		assertEquals(idExpected, tenantCtl.tenants.get(0).tenantID);
		
		String firstName = "Tenant-1";
		assertSame(firstName, TenantController.tenants.get(0).firstName);
		
		String lastName = "LN-1";
		assertSame(lastName, TenantController.tenants.get(0).lastName);
		
		String email = "abc@gmail.com";
		assertSame(email, TenantController.tenants.get(0).email);
		
		int buildingTenantID = 1;
		assertEquals(buildingTenantID, TenantController.tenants.get(0).buildingTenantID);
		
		int apartmentNum = 1;
		assertEquals(apartmentNum, TenantController.tenants.get(0).apartmentNum);
		
	}

	

}
