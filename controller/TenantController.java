package controller;

import model.Tenant;
import view.TenantView;

import java.util.ArrayList;

public class TenantController {
    private TenantView tenantView;
    private ArrayList<Tenant> tenants;

    public TenantController(TenantView tenantView) {
        this.tenantView = tenantView;
        this.tenants = new ArrayList<Tenant>();
    }



    public void addNewTenant(String name, String email, int i, int i1) {
        Tenant tenant = new Tenant(name,email,i,i1);
        tenants.add(tenant);
    }

}
