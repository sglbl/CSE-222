import java.io.FileNotFoundException;
import java.util.*;
import cse222_1801042656_hw6.*;
/**
 * @author Suleyman Golbol 1801042656
 * Driver class for project
 */

public class Main{
    private static final Scanner SCAN = new Scanner(System.in); //To prevent non-closing scanner resource leaks.

    public static void main(String[] args) throws FileNotFoundException{
        int choose = 0;
        boolean loopFlag = true;
        
        while(loopFlag == true){
            System.out.print("\n1) Try Interactive Menu\n2) Test All Methods. \n3) Exit.\nPlease enter from menu: ");
            choose = scanInt();
            
            switch(choose){
                case 1:
                    interactiveMenu();
                    return;
                case 2:
                    TestAllMethods();
                    System.out.println("All methods are tested.");
                    SCAN.close();
                    return;
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

    public static long scanLong(String output) {
        System.out.printf("Enter %s: ", output);
        long choose = 0;
        while(true){    //Loop until right input.
            try{
                choose = SCAN.nextLong();
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

    public static String scanString(String output){
        System.out.printf("Enter %s: ", output);
        return SCAN.nextLine();
    }

    /**
     * INTERACTIVE MENU
     */
    public static void interactiveMenu() throws FileNotFoundException{
        int input;
        ShopSystem s = new ShopSystem();
        //Reading csv file.
        s.readCsv();
        System.out.println("\n-----INTERACTIVE MENU-----");
        while(true){
            System.out.print("\n1) Add an trader\n2) Trader Login\n3) Add an customer \n4) Customer Login\n5) Exit | Select: ");
            input = scanInt();
            switch(input){
                case 1:
                    System.out.println("-Creating trader-");
                    traderRegister(s);
                    break;
                case 2:
                    traderLogin(s);
                    break;
                case 3:
                    customerRegister(s);
                    break;
                case 4:
                    customerLogin(s);
                    break;
                case 5:
                    System.out.println("Goodbye."); return;
            }
            
        
        }


    }

    public static void traderLogin(ShopSystem s){
        int input;
        if( s.checkIfThereIsTrader() == false ){
            System.out.println("There is no customer. Try again.");
            return;
        }
        Traders trader = null;
        while(true){
            System.out.println("-Enter the email and password(To exit just write -1 for user id.)-");
            long userID = scanLong("User ID:");
            String password = scanString("Password");
            if(s.checkUserInfo(userID, password) == true){
                System.out.println("Login successful.");
                trader = new Traders(userID, password);
                break;
            }
            else if(userID == -1) return;
            else
                System.out.println("Mail or password is incorrect. Try again.");
        }

        while(true){
            System.out.print("\n1) Get trader id number \n2) Add product\n3) Remove Product \n4) Edit Product \n5) See/Change customer order \n6) Back | Select: ");
            input = scanInt();
            switch(input){
                case 1:
                    System.out.println(trader.getUserID());
                    break;
                case 2:
                    trader.addProduct(s);
                    break;
                case 3:
                    trader.removeProduct(s);
                    break;
                case 4:
                    trader.editProduct(s);
                    break;
                case 5:
                    trader.seeCustomerOrders(s);
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Try again");

            }
            
        }
        


    }

    public static void customerLogin(ShopSystem s){
        if( s.checkIfThereIsCustomer() == false ){
            System.out.println("There is no customer. Try again.");
            return;
        }
        Customers customer = null;
        while(true){
            System.out.println("-Enter the email and password(To exit just write -1 for user id.)-");
            long userID = scanLong("User ID:");
            String password = scanString("Password");
            if(s.checkUserInfo(userID, password) == true){
                System.out.println("Login successful.");
                customer = new Customers(userID, password);
                break;
            }
            else if(userID == -1) return;
            else
                System.out.println("Mail or password is incorrect. Try again.");
        }


        while(true){
            System.out.print("\n1) Get customer number \n2) Search and query product by name\n"
            + "3) Display the products of trader\n4) Give order \n5) View Previous Orders \n6) Back | Select: ");
            int input = scanInt();
            switch(input){
                case 1:
                    System.out.println(customer.getUserID());
                    break;
                case 2:
                    customer.customerSearching( s, scanString("text to search: ") );
                    break;
                case 3:
                    customer.searchTrader(s);
                    break;
                case 4:
                    customer.giveOrder(s, scanString("Product name: "), customer.getUserID());
                    break;
                case 5:
                    customer.viewPreviousOrders(s, customer.getUserID());
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Try again");
            }
            
        }

    }

    public static void traderRegister(ShopSystem s){
        long uniqueID = s.generateUniqueID();
        while( s.addTraders( new Traders(scanString("Name"),  scanString("Surname"), uniqueID, scanString("Password")) ) == false ){
            //check if password has 6 characters.
            System.out.println("Name/Surname shouldn't be empty. Password should be 6 digits.Enter again!");
        }
        System.out.println("Trader successfully added. Your id is " + uniqueID + ". Please keep it in mind.");
    }

    public static void customerRegister(ShopSystem s){
        long uniqueID = s.generateUniqueID();
        while( s.addCustomer( new Customers(scanString("Name"), scanString("Surname"), uniqueID, scanString("Password")) ) == false ){
            //to check if password has 6 characters.
            System.out.println("Name/Surname shouldn't be empty. Password should be 6 digits.Enter again!");
        }
        System.out.println("Customer successfully added. Your id is " + uniqueID + ". Please keep it in mind.");
    }

    public static void customerSearching(ShopSystem s){
        String string;
        do{
            string = scanString("Enter text to be searched(Enter at least 3 letters): ");
            s.getCsvHanler().searchByName(string);
        }while(string.length() <= 3);
    }


    public static void TestAllMethods() throws FileNotFoundException{
        ShopSystem s = new ShopSystem();
        Traders trader = null;
        Customers customer = null;

        System.out.println("-----READING CSV FILE-----");
        s.readCsv();   //Reading csv file.

        System.out.println("-----ADDING NEW TRADER-----");
        s.addTraders( trader = new Traders("tempName", "tempSurname", 12345678, "sifre1") );
        System.out.println("-----TRADER ADDS NEW PRODUCT-----");
        trader.addProductTest(s);
        System.out.println("-----TRADER REMOVES PRODUCT-----");
        trader.removeProductTest(s);
        System.out.println("-----TRADER EDITS PRODUCT-----");
        trader.editProductTest(s);


        System.out.println("-----ADDING NEW CUSTOMER / WRITING TO USERS FILE-----");
        s.addCustomer( customer = new Customers("tempName", "tempSurname", 11111111, "000000") );

        System.out.println("-----CUSTOMER SEARCHES-----");
        System.out.println("-----SORTING SEARCH BY NAME WITH QUICK SORT-----");
        customer.searchForProductTest(s, "Icon");
        System.out.println("-----SORTING BY PRICE WITH HEAP SORT-----");
        s.getCsvHanler().myHeapSortByPrices( s.getCsvHanler().getSearchedProducts() );
        s.getCsvHanler().printData(); 
        System.out.println("-----SORTING BY DISCOUNT WITH MERGE SORT -----");
        s.getCsvHanler().myMergeSortByDiscount( s.getCsvHanler().getSearchedProducts() );
        s.getCsvHanler().printData(); 
        System.out.println("-----SORTING BY TRADER NAME WITH HEAP SORT -----");
        s.getCsvHanler().myHeapSortByTrader( s.getCsvHanler().getSearchedProducts() );
        s.getCsvHanler().printData(); 

        System.out.println("-----FILTERING SEARCHED RESULTS BY PRICE THRESHOLDS-----");
        s.getCsvHanler().shrinkByPriceThreshold(100, 420);
        System.out.println("-----FILTERING SEARCHED RESULTS BY CATEGORY-----");
        s.getCsvHanler().filterByCategoryTest( s.getCsvHanler().getSearchedProducts() );

        System.out.println("-----CUSTOMER DISPLAYS PRODUCTS OF TRADER-----");
        customer.searchTraderTest(s);

        System.out.println("-----CUSTOMER GIVES ORDER THAT DOESN'T EXIST-----");
        customer.giveOrder(s, "Suleymanin olmayan urunu", customer.getUserID());
        System.out.println("-----CUSTOMER GIVES ORDER-----");
        customer.giveOrder(s, "Aria Rhodium Sterling Silver Pendant", customer.getUserID());

        System.out.println("-----TRADER CHECKS IF CUSTOMER GAVE ORDER-----");
        System.out.println("-----TRADER CHANGES STATUS OF ORDER TO GIVEN-----");
        s.getCsvHanler().seeCustomerOrdersTest( customer.getUserID(), s.getCsvHanler().getTraderIdFromNane("Aria") , true);    

        System.out.println("-----TRADER CHANGES STATUS OF ORDER TO CANCLLED-----");
        customer.giveOrder(s, "Janhvi PS-L045B Solar Lights", customer.getUserID());
        s.getCsvHanler().seeCustomerOrdersTest( customer.getUserID(), s.getCsvHanler().getTraderIdFromNane("Janhvi") , false);    

    }

    
}