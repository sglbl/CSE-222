import java.util.InputMismatchException;
import java.util.Scanner;
import cse222_1801042656_hw3.*;
/**
 * @author Suleyman Golbol 1801042656
 * Driver class for project
 */

public class Main{
    public static final Scanner SCAN = new Scanner(System.in); //To prevent non-closing scanner resource leaks.


    public static void main(String[] args){
        int choose = 0;
        boolean loopFlag = true;
        
        while(loopFlag == true){
            System.out.print("\n1) Test All Methods\n2) Try Interactive Menu\n3) Exit.\nPlease enter from menu: ");
            choose = scanInt();
            switch(choose){
                case 1:
                    TestAllMethods();
                    System.out.println("All methods are tested.");
                    break;
                case 2:
                    interactiveMenu();
                    break;
                case 3:
                    loopFlag= false;
                    break;
                default:
                    System.out.println("You entered wrong input. Try again: ");
            } 
 

        }
        SCAN.close();
    }

    public static int scanInt(){
        int choose = 0;
        while(true){    //Loop until right input.
            try{
                choose = SCAN.nextInt();
                SCAN.nextLine();
                break;
            }
            catch(InputMismatchException e){
                System.out.print(e.getMessage() + " input mismatch exception caught. Try Again: ");
                SCAN.nextLine();
                continue;
            }
        }
        return choose;
    }

    public static int scanInt(String output){
        System.out.printf("Enter %s: ", output);
        int choose = 0;
        while(true){    //Loop until right input.
            try{
                choose = SCAN.nextInt();
                SCAN.nextLine();
                break;
            }
            catch(InputMismatchException e){
                System.out.print(e.getMessage() + " input mismatch exception caught. Try Again: ");
                SCAN.nextLine();
                continue;
            }
        }
        return choose;
    }

    public static Colors scanColor(){
        System.out.print("1:Red 2:Green 3:Blue\n4:Black 5:White 6:No Color\nChoose color of product: ");
        int choose = scanInt();
        while(choose<1 || choose > 6){
            System.out.print("Error in choosing color. Try again: ");
            choose = scanInt();
        }
        System.out.print("Enter model no: ");
        switch(choose){
            case 1:  return Colors.RED;
            case 2:  return Colors.GREEN;
            case 3:  return Colors.BLUE;
            case 4:  return Colors.BLACK;
            case 5:  return Colors.WHITE;
            case 6:  return Colors.NO_COLOR;
            default: System.out.println("Error in color choosing");
        }
        return Colors.NO_COLOR;
    }

    public static Products.Product scanProduct(){
        System.out.print("1:Meeting Tables 2:Office Desks 3:Office Chairs 4:Office Cabinets 5:Book Cases\nChoose type of product: ");
        int choose = scanInt();
        while(choose<1 || choose > 5){
            System.out.print("Error in choosing product. Try again: ");
            choose = scanInt();
        }
        switch(choose){
            case 1:  return Products.Product.MEETING_TABLES;
            case 2:  return Products.Product.OFFICE_DESKS;
            case 3:  return Products.Product.OFFICE_CHAIRS;
            case 4:  return Products.Product.OFFICE_CABINETS;
            case 5:  return Products.Product.BOOK_CASES;
        }
        return Products.Product.MEETING_TABLES;
    }

    public static String scanString(String output){
        System.out.printf("Enter %s: ", output);
        return SCAN.nextLine();
    }

    public static void interactiveMenu(){
        int input;
        Company c = new Company();
        System.out.println("INTERACTIVE MENU");
        while(true){
            System.out.println("\n1) Add an admin\n2) Admin Login\n3) Employee Login\n4) Customer Register/Login\n5) Exit");
            input = scanInt();
            switch(input){
                case 1:
                    System.out.println("-Creating admin-");
                    c.addAdministrator( new Admins(scanString("Name"), scanString("Surname"), scanString("Email"), scanString("Password")) );
                    System.out.println("Admin created succesfully");
                    break;
                case 2:
                    if(c.getAllAdmins().size() == 0 ){ System.out.println("There is no admin. Please create first."); break; }
                    adminLogin(c);
                    break;
                case 3:
                    employeeLogin(c);
                    break;
                case 4:
                    customerLogin(c);
                    break;
                case 5:
                    System.out.println("Goodbye."); return;
            }
            
        
        }


    }

    public static void adminLogin(Company c){
        int input;
        Admins admin = c.getAllAdmins().get(0);
        System.out.println("Admin panel");
        while(true){
            System.out.println("1) Add Branch \n2) Add Branch Employee\n3) Remove branch employee\n4) Remove branch\n5) Check is there less amount.\n6) Back");
            input = scanInt();
            switch(input){
                case 1:
                    System.out.println("-Creating branch-");
                    admin.addBranch(c, new Branch( scanString("Name")) );
                    System.out.println("Branch created succesfully");
                    break;

                case 2:
                    if(c.getAllBranches().size() == 0 ){ System.out.println("There is no branch. Please create first."); break; }
                    System.out.println("-Creating employee\nSelect the index of branch to add: (for ex. for first index select 0)");
                    System.out.println(c.getAllBranches().toString());
                    input = scanInt();
                    while( input < c.getAllBranches().size()-1 || input >= c.getAllBranches().size() ){
                        System.out.println("Error in input. Try again:");
                        input = scanInt();
                    }
                    admin.addBranchEmployee(c.getAllBranches().get(input), new BranchEmployees( scanString("Employee Name"), scanString("Surname"), scanString("Email"), scanString("Password"), c.getAllBranches().get(input)) );
                    break;

                case 3:
                    if(c.getAllBranches().size() == 0 ){ System.out.println("There is no branch. Please create first."); break; }
                    System.out.println("-Removing employee\nSelect from below the index of branch to find employee: (for ex. for first index select 0)");
                    System.out.println(c.getAllBranches().toString());
                    input = scanInt();
                    while( input < c.getAllBranches().size()-1 || input >= c.getAllBranches().size() ){
                        System.out.println("Error in input. Try again:");
                        input = scanInt();
                    }

                    System.out.println("-Select from below the index of branch employee");
                    System.out.println(c.getAllBranches().get(input).getBranchEmployees().toString() );
                    int input2 = scanInt();
                    while( input2 < c.getAllBranches().get(input).getBranchEmployees().size()-1 || input2 >= c.getAllBranches().get(input).getBranchEmployees().size() ){
                        System.out.println("Error in input. Try again:");
                        input2 = scanInt();
                    }
                    admin.removeBranchEmployee(c.getAllBranches().get(input), c.getAllBranches().get(input).getBranchEmployees().get(input2)  );
                    break;

                case 4:
                    if(c.getAllBranches().size() == 0 ){ System.out.println("There is no branch. Please create first."); break; }
                    System.out.println("-Removing branch(If there is employee in branch it will be removed too)\nSelect from below the index of branch to remove: (for ex. for first index select 0)");
                    System.out.println(c.getAllBranches().toString());
                    input = scanInt();
                    while( input < c.getAllBranches().size()-1 || input >= c.getAllBranches().size() ){
                        System.out.println("Error in input. Try again:");
                        input = scanInt();
                    }
                    admin.removeBranch(c, c.getAllBranches().get(input) );
                    break;
                case 5:
                    admin.isThereLessAmount(c, 100);
                    break;
                case 6:
                    return;
            }
            
        }
    }

    public static void employeeLogin(Company c){
        int input;
        BranchEmployees bE = null;
        //Firstly getting employees branchç
        if(c.getAllBranches().size() == 0 ){ System.out.println("There is no branch for employee. Please create first."); return; }
        System.out.println(c.getAllBranches().toString());
        System.out.println("Enter the index of branch for employee: (for ex. for first index select 0)");
        input = scanInt();
        while( input < c.getAllBranches().size()-1 || input >= c.getAllBranches().size() ){
            System.out.println("Error in input. Try again:");
            input = scanInt();
        }System.out.println(c.getAllBranches().get(input).getBranchName() + " branch is selected.");
        Branch branch = c.getAllBranches().get(input);
        //Login with mail and password
        while(true){
            System.out.println("-Enter the email and password of employee-");
            String mail = scanString("Mail"), password = scanString("Password");
            for(int i=0; i<c.getAllBranches().get(input).getBranchEmployees().size(); i++)
                if( c.getAllBranches().get(input).getBranchEmployees().get(i).getEmail().equals(mail) )
                    if( c.getAllBranches().get(input).getBranchEmployees().get(i).getPassword().equals(password) ){
                        System.out.println("Login successful.");
                        bE = c.getAllBranches().get(input).getBranchEmployees().get(i);
                        break;
                    }
            
            if( bE == null )
                System.out.println("Mail or password is incorrect. Try again.");
            else
                break;
        }

        while(true){
            System.out.print("1) Add Product \n2) Remove product\n3) Inquire Stock(Checks is amount enough)\n"
            + "4) Inform Manager about less amount\n5) Access order info of customer.\n6) Create subscription for customer\n" 
            + "7) Sale to customer\n8) Back | Select: ");
            input = scanInt();
            switch(input){
                case 1:
                    System.out.println("-Adding product-");
                    int stock = -1;
                    while(stock<0){ System.out.print("Enter stock to add: (Stock should be bigger than 0)"); stock = scanInt(); }
                    bE.addProduct(branch, new Products(scanColor(), scanInt(), stock, scanProduct() ));
                    System.out.println("Product added succesfully");
                    break;

                case 2:
                    System.out.println("-Removing product-");
                    branch.removeProduct(scanProduct(), scanColor(), scanInt());
                    break;

                case 3:
                    System.out.print("Write requested amount: ");
                    int requestedAmount = scanInt();
                    if( bE.isAmountEnough( scanProduct(), scanColor(), scanInt(), requestedAmount) == true)
                        System.out.println("Amount is enough.");
                    else
                        System.out.println("Amount is not enough or there is no product.");
                    break;

                case 4:
                    bE.informManager(c, c.getAllAdmins().get(0), scanProduct(), scanColor(), scanInt(), 10);
                    break;
                case 5:
                    if(c.getAllCustomers().size() == 0 ){ System.out.println("There is no customer. Please create first."); break; }
                    else System.out.print("Customers are "+ c.getAllCustomers().toString() + "\nSelect index(for ex for first customer select 0): ");
                    int select = -1;
                    while( select < 0 || select > c.getAllCustomers().size() ){
                        System.out.print("Emter index: ");
                        select = scanInt();
                    }
                    bE.accessPreviousOrderInfo(c.getAllCustomers().get(select));

                    break;
                case 6:
                    if(c.getAllCustomers().size() == 0 ){ System.out.println("There is no customer. Please create first."); break; }
                    else System.out.print("Customers are "+ c.getAllCustomers().toString() + "\nSelect index(for ex for first customer select 0): ");
                    select = -1;
                    while( select < 0 || select > c.getAllCustomers().size() ){
                        System.out.print("Emter index: ");
                        select = scanInt();
                    }
                    System.out.println("Enter customer number of customer for subscription");
                    int subsNo = scanInt();
                    bE.createSubscriptionForCustomer(subsNo, c.getAllCustomers().get(select));
                    break;
                case 7:
                    if(c.getAllCustomers().size() == 0 ){ System.out.println("There is no customer. Please create first."); break; }
                        else System.out.print("Customers are "+ c.getAllCustomers().toString() + "\nSelect index(for ex for first customer select 0): ");
                        select = -1;
                        while( select < 0 || select > c.getAllCustomers().size() ){
                            System.out.print("Emter index: ");
                            select = scanInt();
                    }
                    bE.sale(branch, scanProduct(), scanColor(), scanInt(), c.getAllCustomers().get(select));
                    break;
                case 8:
                    return;
                default:
                    System.out.print("Input error. Try again: ");
            }
            
        }
    }

    public static void customerLogin(Company c){
        int input = -1, input2 = -1 ;
        while(input!=1 && input != 2){ System.out.print("1)Register 2)Login | Choose: "); input = scanInt(); }
        if(input == 1){
            c.addCustomer( new Customers(scanString("Name"),  scanString("Surname"), scanString("Email"), scanString("Password"), c));
            System.out.println("Customer successfully added.");
            return;
        }
        if( input == 2 && c.getAllCustomers().size() == 0 ){
            System.out.println("There is no customer. Try again.");
            return;
        }
        Customers customer = null;
        while(true){
            System.out.println("-Enter the email and password-");
            String mail = scanString("Mail"), password = scanString("Password");
            for(int i=0; i<c.getAllCustomers().size(); i++)
                if( c.getAllCustomers().get(i).getEmail().equals(mail) )
                    if( c.getAllCustomers().get(i).getPassword().equals(password) ){
                        System.out.println("Login successful.");
                        customer = c.getAllCustomers().get(i);
                        break;
                    }
            
            if(customer == null)
                System.out.println("Mail or password is incorrect. Try again.");
            else
                break;
        }

        while(true){
            System.out.print("\n1) Get customer number \n2) Get Previous Orders\n3) Get subscription info\n"
            + "4) List products\n5) Search for products\n6) Find which branch has that product\n" 
            + "7) Shop online\n8) Shop in Store \n9) Request subscription from branch\n10) Back | Select: ");
            input = scanInt();
            switch(input){
                case 1:
                    System.out.println(customer.getCustomerNumber());
                    break;
                case 2:
                    System.out.println( customer.getPreviousOrders().toString() );
                    break;
                case 3:
                    System.out.println( "Subscription info is " + customer.getSubscription() );
                    break;
                case 4:
                    Branch branch;
                    if(c.getAllBranches().size() == 0 ){ System.out.println("There is no branch. Please create first."); break; }
                    System.out.println("-Select the index of branch to view: (for ex. for first index select 0): ");
                    System.out.println(c.getAllBranches().toString());
                    input = scanInt();
                    while( input < c.getAllBranches().size()-1 || input >= c.getAllBranches().size() ){
                        System.out.println("Error in input. Try again:");
                        input = scanInt();
                    }
                    branch = c.getAllBranches().get(input);
                    customer.ListProducts(branch);
                    break;
                case 5:
                    if( customer.searchForProduct(c, scanProduct(), scanColor(), scanInt() )  == false )
                        System.out.println("Product you looked couldn't found.");
                    break;
                case 6:
                    customer.whichBranchIsIn(c, scanProduct(), scanColor(), scanInt() );
                    break;
                case 7:
                    customer.shopOnline(scanString("Adress"), scanInt("Phone"), c, scanString("Branch Name"), scanInt("Index"), scanInt("Amount"));
                    break;
                case 8:
                    if(c.getAllBranches().size() == 0 ){ System.out.println("There is no branch. Please create first."); break; }
                    System.out.println("-Select from below the index of branch: (for ex. for first index select 0)");
                    System.out.println(c.getAllBranches().toString());
                    input = scanInt();
                    while( input < c.getAllBranches().size()-1 || input >= c.getAllBranches().size() ){
                        System.out.println("Error in input. Try again:");
                        input = scanInt();
                    }
                    System.out.println("-Select from below the index of branch employee");
                    System.out.println(c.getAllBranches().get(input).getBranchEmployees().toString() );
                    input2 = scanInt();
                    while( input2 < c.getAllBranches().get(input).getBranchEmployees().size()-1 || input2 >= c.getAllBranches().get(input).getBranchEmployees().size() ){
                        System.out.println("Error in input. Try again:");
                        input2 = scanInt();
                    }
                    customer.shopInStore(c, scanString("Branch Name"), scanInt("Index"), scanInt("Amount"), c.getAllBranches().get(input).getBranchEmployees().get(input2));
                    break;

                case 9:
                    if(c.getAllBranches().size() == 0 ){ System.out.println("There is no branch. Please create first."); break; }
                    System.out.println("-Select from below the index of branch: (for ex. for first index select 0)");
                    System.out.println(c.getAllBranches().toString());
                    input = scanInt();
                    while( input < c.getAllBranches().size()-1 || input >= c.getAllBranches().size() ){
                        System.out.println("Error in input. Try again:");
                        input = scanInt();
                    }
                    System.out.println("-Select from below the index of branch employee");
                    System.out.println(c.getAllBranches().get(input).getBranchEmployees().toString() );
                    input2 = scanInt();
                    while( input2 < c.getAllBranches().get(input).getBranchEmployees().size()-1 || input2 >= c.getAllBranches().get(input).getBranchEmployees().size() ){
                        System.out.println("Error in input. Try again:");
                        input2 = scanInt();
                    }
                    customer.requestSubscription( c.getAllBranches().get(input).getBranchEmployees().get(input2) );
                    break;

                case 10:
                    return;

                default:
                    System.out.println("Try again");

            }
            
        }



    }


    public static void TestAllMethods(){
        Company company = new Company();
        Branch branch1, branch2, branch3, branch4, tempBranch;  
        BranchEmployees be1, be2, be3, be4, tempEmployee;
        Admins admin = new Admins("Suleyman", "Golbol", "info@sglbl.com", "1234");
        company.addAdministrator(admin);
        System.out.println(admin.getName());
        System.out.println("----TEST ALL----");
        System.out.println("ADDING 4 BRANCHES ");
        admin.addBranch(company, branch1 = new Branch("Hatay Branch")   );
        admin.addBranch(company, branch2 = new Branch("Adana Branch")   );
        admin.addBranch(company, branch3 = new Branch("Mersin Branch")  );
        admin.addBranch(company, branch4 = new Branch("Kocaeli Branch") );
        admin.addBranch(company, tempBranch = new Branch("Temp Branch") );
        System.out.println(company.getAllBranches().toString() );

        System.out.println("--PERSONS(USERS) SUBSCRIBING TO SYSTEM WITH DEFINING THEIR INFO--");
        System.out.println("--ADDING BRANCH EMPLOYEES--");
        admin.addBranchEmployee(branch1, be1 = new BranchEmployees("Cemil", "Koçak", "cemil@b.com","xyzt" ,  branch1)   );
        admin.addBranchEmployee(branch2, be2 = new BranchEmployees("Ahmet", "Açık", "ahmet@b.com","1iki" ,   branch1)   );
        admin.addBranchEmployee(branch3, be3 = new BranchEmployees("Furkan", "Boz", "furkan@bc.com","sifre" ,branch1)   );
        admin.addBranchEmployee(branch4, be4 = new BranchEmployees("Yahya", "Tutmaz", "yahya@c.com","kocaeli" , branch1));
        admin.addBranchEmployee(tempBranch, tempEmployee=new BranchEmployees("Ali", "Can", "alia@c.com","pass" , tempBranch));

        System.out.print(branch1.getBranchEmployees().toString() + branch2.getBranchEmployees().toString());
        System.out.println( branch3.getBranchEmployees().toString() + branch4.getBranchEmployees().toString() + tempBranch.getBranchEmployees().toString());
        System.out.println("--REMOVING BRANCH EMPLOYEE--");
        admin.removeBranchEmployee(tempBranch, tempEmployee);
        System.out.print(branch1.getBranchEmployees().toString() + branch2.getBranchEmployees().toString());
        System.out.println( branch3.getBranchEmployees().toString() + branch4.getBranchEmployees().toString() + tempBranch.getBranchEmployees().toString());
        System.out.println("--REMOVING BRANCH--");
        admin.removeBranch(company, tempBranch);
        System.out.println(company.getAllBranches().toString() );

        System.out.println("--CREATING CUSTOMERS WITH NAME EMAIL AND PASSWORD--");
        Customers customer1, customer2, customer3, customer4;
        company.addCustomer( customer1 = new Customers("Ali", "Cem", "alicem@gmail.com", "1iki3", company) );
        company.addCustomer( customer2 = new Customers("Oya", "Su", "oya@gmail.com", "4321", company) );
        company.addCustomer( customer3 = new Customers("Talat","Ay", "talat@gmail.com", "1233", company) );
        company.addCustomer( customer4 = new Customers("Baran", "Solmaz", "brn@gmail.com", "1111", company) );

        System.out.println("--PRINTING ALL CUSTOMERS--");
        System.out.println( company.getAllCustomers().toString() );
        
        System.out.println("--CREATING PRODUCTS--");
        HybridList<Products> allProducts = new HybridList<Products>();
        specifyProducts(allProducts); //Creating new products and adding them in this method.

        System.out.println("BRANCH EMPLOYEES ADDS PRODUCTS");
        be1.addProduct(branch1, allProducts); //branch employee ads products to her/his branch.
        be2.addProduct(branch2, allProducts);
        be3.addProduct(branch3, allProducts);
        be4.addProduct(branch4, new Products(Colors.BLUE, /*model*/1, /*stock*/2, Products.Product.MEETING_TABLES ));
        //Branch4 has just one product in it.

        System.out.println("--CUSTOMER LISTS ALL PRODUCTS--");
        customer1.ListProducts(branch1);
        System.out.println("--CUSTOMER SEARCH FOR PRODUCTS--");
        System.out.println("--TRYING TO FIND BLACK OFFICE CABINETS(WHICH SHOULD BE FALSE BECAUSE THERE ISN'T COLOR CHOICE)--");
        System.out.println( customer1.searchForProduct(company, Products.Product.OFFICE_CABINETS, Colors.BLACK, 1) ); //False
        System.out.println("--TRYING TO FIND BLUE OFFICE DESKS--");
        System.out.println( customer1.searchForProduct(company, Products.Product.OFFICE_DESKS, Colors.BLUE, 1) ); //True
        
        System.out.println("---CUSTOMER SHOPPING FROM STORE---");
        System.out.println("--CUSTOMER TRIES TO FIND A PRODUCT BRANCH DOESN'T HAVE( BLACK BOOK CASES)--");
        customer2.whichBranchIsIn(company, Products.Product.BOOK_CASES, Colors.BLACK, 1);
        System.out.println("\n--CUSTOMER LEARNS WHICH BRANCH HAS THAT PRODUCT( BLUE OFFICE DESK)--");
        int productIndex = -1;
        productIndex = customer2.whichBranchIsIn(company, Products.Product.OFFICE_DESKS, Colors.BLUE , 1);
        
        System.out.println("\n--BUYING PRODUCT WITH INDEX NUMBER IN-STORE (BLUE OFFICE DESK) WITHOUT SUBSCRIPTION--");
        customer1.shopInStore(company, "Adana Branch", productIndex, be1);
        System.out.println("\n--REQUESTING SUBSCRIPTION FOR CUSTOMER FROM EMPLOYEE--");
        customer1.requestSubscription(be1/*branch employee*/);
        System.out.println("--BUYING PRODUCT WITH INDEX NUMBER IN-STORE (BLUE OFFICE DESK) WITH SUBSCRIPTION--");
        productIndex = customer1.whichBranchIsIn(company, Products.Product.OFFICE_DESKS, Colors.BLUE, 1);
        customer1.shopInStore(company, "Adana Branch", productIndex, be1);
        System.out.println("--ADMIN QUERIES IF NEED TO BE SUPPLIED (IF NEEDED, SUPPLIES AND PUTS \"S\" INSTEAD \"N\")--");
        admin.queryToSupply(company,  customer1.getCustomerNumber() );
        customer1.ViewPreviousOrders( customer1.getCustomerNumber() );

        System.out.println("\n--TRYING TO BUY PRODUCT COULDN'T FIND IN A BRANCH--");    
        customer3.shopInStore(company, "Kocaeli Branch", productIndex, be1);
        System.out.println("\nPRINTING BRANCH4 PRODUCTS -> " + branch4.getProducts().toString());
        System.out.println("--EMPLOYEE TRIES TO REMOVE A PRODUCT FROM A BRANCH THAT DOESN'T EXIST--");
        be4.removeProduct(branch4, Products.Product.OFFICE_CHAIRS, Colors.RED, 2 );
        System.out.println("\n--EMPLOYEE REMOVES A PRODUCT FROM A BRANCH THAT EXISTS--");
        branch4.removeProduct(Products.Product.MEETING_TABLES, Colors.BLUE, 1);
        System.out.println("PRINTING BRANCH4 PRODUCTS -> " + branch4.getProducts().toString());

        System.out.println("\n--CUSTOMER SHOPPING ONLINE--");
        System.out.println("--SEARCH FOR A SPECIFIC PRODUCT(IF FOUND RETURN TRUE)--");
        System.out.println( customer4.searchForProduct(company, Products.Product.OFFICE_CABINETS, Colors.NO_COLOR, 1) ); 
        System.out.println("--CUSTOMER LEARNS WHICH BRANCH HAS THAT PRODUCT( OFFICE CABINET WHICH DOESN'T HAVE COLOR CHOICE)--");
        customer4.whichBranchIsIn(company, Products.Product.OFFICE_CABINETS, Colors.NO_COLOR, 3);
        productIndex = -1;  productIndex = customer4.whichBranchIsIn(company, Products.Product.OFFICE_CABINETS, Colors.NO_COLOR, 2);
        customer4.requestSubscription(be3);

        System.out.println("\n--CUSTOMER TRIES TO BUY WITH A BIG AMOUNT WHICH IS NOT ENOUGH--");
        int requestedAmount = 20;
        customer4.shopOnline("Turunclu, Hatay 31160", 2233870 ,company, "Mersin Branch", productIndex, requestedAmount);
        System.out.println("--BRANCH EMPLOYEE INQUIRES STOCK AND CHECKS IF AMOUNT IS ENOUGH (RETURNS TRUE IF ENOUGH)--");
        System.out.println( be3.isAmountEnough( Products.Product.OFFICE_CABINETS, Colors.NO_COLOR, 2, requestedAmount) );
        if( be3.isAmountEnough( Products.Product.OFFICE_CABINETS, Colors.NO_COLOR, 2, requestedAmount) == false)
            be3.informManager(company, admin, Products.Product.OFFICE_CABINETS, Colors.NO_COLOR, 2, requestedAmount );
        System.out.println("--ADMIN INFORMED, ADMIN INCREASING STOCK--");
        admin.isThereLessAmount(company, requestedAmount*5);

        System.out.println("--CUSTOMER TRIES TO BUY AGAIN--");
        customer4.shopOnline("Turunclu, Hatay 31160", 2233870 ,company, "Mersin Branch", productIndex, requestedAmount);

        System.out.println("--BRANCH EMPLOYEE SELLS PRODUCT TO CUSTOMER--");
        be3.sale(branch3, Products.Product.OFFICE_CABINETS, Colors.NO_COLOR, 2, customer4);
        admin.queryToSupply(company,  customer4.getCustomerNumber() );


        System.out.println("--BRANCH EMPLOYEE ACCESS CUSTOMER'S PREVIOUS ORDER--");
        be3.accessPreviousOrderInfo(customer4);
        System.out.println(" ");

    }

    public static void specifyProducts(HybridList<Products> allProducts){
        System.out.println("CREATING PRODUCTS OF BOOK CASES");
        for(int i=0; i<12; i++)
            allProducts.add( new Products(Colors.NO_COLOR, i+1, 10, Products.Product.BOOK_CASES) );
        System.out.println("CREATING PRODUCTS OF OFFICE CABINETS");
        for(int i=0; i<12; i++)
            allProducts.add( new Products(Colors.NO_COLOR, i+1, 10, Products.Product.OFFICE_CABINETS) );

        System.out.println("CREATING PRODUCTS OF MEETING TABLES");
        for(int i=0; i<10; i++)
            allProducts.add( new Products(Colors.BLACK, i+1, 10, Products.Product.MEETING_TABLES) );
        for(int i=0; i<10; i++)
            allProducts.add( new Products(Colors.GREEN, i+1, 10, Products.Product.MEETING_TABLES) );
        for(int i=0; i<10; i++)
            allProducts.add( new Products(Colors.BLUE, i+1, 10, Products.Product.MEETING_TABLES) );
        for(int i=0; i<10; i++)
            allProducts.add( new Products(Colors.RED, i+1, 10, Products.Product.MEETING_TABLES) );

        System.out.println("CREATING PRODUCTS OF OFFICE DESKS");
        for(int i=0; i<5; i++)
            allProducts.add( new Products(Colors.BLACK, i+1, 10, Products.Product.OFFICE_DESKS) );
        for(int i=0; i<5; i++)
            allProducts.add( new Products(Colors.WHITE, i+1, 10, Products.Product.OFFICE_DESKS) );
        for(int i=0; i<5; i++)
            allProducts.add( new Products(Colors.RED, i+1, 10, Products.Product.OFFICE_DESKS) );
        for(int i=0; i<5; i++)
            allProducts.add( new Products(Colors.BLUE, i+1, 10, Products.Product.OFFICE_DESKS) );

        System.out.println("CREATING PRODUCTS OF OFFICE CHAIRS\n");
        for(int i=0; i<7; i++)
            allProducts.add( new Products(Colors.BLACK, i+1, 10, Products.Product.OFFICE_CHAIRS) );
        for(int i=0; i<7; i++)
            allProducts.add( new Products(Colors.WHITE, i+1, 10, Products.Product.OFFICE_CHAIRS) );
        for(int i=0; i<7; i++)
            allProducts.add( new Products(Colors.RED, i+1, 10, Products.Product.OFFICE_CHAIRS) );
        for(int i=0; i<7; i++)
            allProducts.add( new Products(Colors.BLUE, i+1, 10, Products.Product.OFFICE_CHAIRS) );
    
    }


}