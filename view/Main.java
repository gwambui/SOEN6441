package view;
// -----------------------------------------------------
// SOEN 6441 Project Phase 1
// © Omar, William, Wambui
// Written by: (Omar Ahmed ID ,William Alves ID,
// Wambui Josphine Kinyanjui 24600878)
//
// This program accepts input from a list of options
// allows a rental property admin to perform tasks from a menu
//    Options
//    Add a property
//    Add a tenant
//    Rent a unit
//    Display properties ->PropertyView();
//    Display tenants
//    Display rented units
//    Display vacant units
//    Display all leases
//    Exit
// It executes the requests and returns to this main menu
//
//
// -----------------------------------------------------
import controller.PropertyController;
import controller.TenantController;


public class MainDriver {

    public static void main(String[] args) {
        System.out.println("Hello and welcome. Please select an action!");
        //    create property MVC
        PropertyView pv = new PropertyView()
        Scanner sc = new Scanner(System.in);
        do {
            printMenu();
            choice = sc.nextInt();

            switch(choice) {
                case 1 :
                    pv.addProperty();
                    break;
                case 2 :
                    choice2();
                    break;
                case 3 :
                    choice3();
                    break;
                case 4 :
                    choice4();
                    break;
                case 5 :
                    choice5();
                    break;
                case 6 :
                    choice6();
                    break;
                case 7:
                    choice7();
                    break;
                case 8 :
                    choice8();
                    break;
                case 9 :
                    choice9();
                    break;
                   default:
                    System.out.println("Wrong input");
            }
        }while(choice !=9);

    }
    public static void printMenu() {
        System.out.println("what do you want to do?");
        System.out.println("1. Add a property)");
        System.out.println("2. Add a tenant");
        System.out.println("3. Rent a unit");
        System.out.println("4. Display properties ");
        System.out.println("5. Display tenants");
        System.out.println("6. Display rented units");
        System.out.println("7. Display vacant units");
        System.out.println("8. Display all leases");
        System.out.println("9. Exit");
        System.out.println("Please enter your choice");

        public static void choice1() {

        }







    }



//        Property house1 = new House();
        PropertyView propertyView = new PropertyView();
        PropertyController pc = new PropertyController(propertyView);
        /*
        get info from user for type of property to add.
        case house,apartment,condo, condoBuilding,aptBuilding
        //HOUSE
        long id;
        String sreet;
        String city;
        String postalCode;
        int streetNumber;
        int bedrooms;
        int bathrooms;
         */
        switch ("prop".toLowerCase()){
            case "house":

                pc.addNewHouse(123,"street","city", "pCode", 12,2,2);

        }
//        create tenant mvc
        TenantView tv = new TenantView();
        TenantController tc = new TenantController(tv);
        tc.addNewTenant("name","email",1234,5678);
    }





}