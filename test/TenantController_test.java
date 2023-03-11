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
public class tenantController_test {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		
for(int i = 0; i < 3; i++){
			
			
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


	/**
	 * Test method for {@link controller.TenantController#addNewTenant(boolean, int, java.lang.String, java.lang.String, java.lang.String, java.lang.String, int)}.
	 */
	@Test
	public void testAddNewTenant() {
		
		TenantController tenantCtl = new TenantController();
		int count = TenantController.tenants.size();
		tenantCtl.addNewTenant(true, 1, "tenant-1", "LN-1", "abc@gmail.com", "Building-1", 1);
		assertEquals(TenantController.tenants.size(), count+1);
		
	}

	/**
	 * Test method for {@link controller.TenantController#DisplayCurrentTenants(java.util.ArrayList)}.
	 */
	@Test
	public void testDisplayCurrentTenants() {
		
		TenantController tenantCtl = new TenantController();
		
		int id = tenantCtl.tenants.get(0).tenantID;
		assertEquals(id, TenantController.tenants.get(0).tenantID);
		
		String firstName = tenantCtl.tenants.get(0).firstName;
		assertSame(firstName, TenantController.tenants.get(0).firstName);
		
		String lastName = tenantCtl.tenants.get(0).lastName;
		assertSame(lastName, TenantController.tenants.get(0).lastName);
		
		String email = tenantCtl.tenants.get(0).email;
		assertSame(email, TenantController.tenants.get(0).email);
		
		String buildingName = tenantCtl.tenants.get(0).buildingName;
		assertSame(buildingName, TenantController.tenants.get(0).buildingName);
		
		int apartmentNum = tenantCtl.tenants.get(0).apartmentNum;
		assertEquals(apartmentNum, TenantController.tenants.get(0).apartmentNum);
		
	}

	

}
