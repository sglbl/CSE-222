/**
 * @author Suleyman Golbol 1801042656
 * Driver class for project
 */

public class Main{

    public static void main(String[] args){
        TestAllMethods();
    }

    public static void specifyProducts(MutableArray<Products> allProducts){
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

    public static void TestAllMethods(){
        Company company = new Company();
        Branch branch1, branch2, branch3, branch4, tempBranch;  
        BranchEmployees be1, be2, be3, be4, tempEmployee;
        Admins admin = new Admins("Suleyman", "Golbol", "info@sglbl.com", "1234");
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
        MutableArray<Products> allProducts = new MutableArray<Products>();
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


}