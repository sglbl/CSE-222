import src.*;
import src.book_implementation.*;
import java.util.*;
import java.io.*;

@SuppressWarnings("unused")
public class Test {
    
    public static void main(String[] args) {        
        HRC hrc = new HRC();

        System.out.println("------ Welcome to World Best Job-Matcher System! ------"); 

        while(true)
        {
            try {
                System.out.println("\n1- Sign Up");
                System.out.println("2- Log in");
                System.out.println("3- Load Quick-Login for quick test.");
                System.out.println("4- Exit");
                int choice = getInt("Choice:");
                switch (choice) {
                    case 1:signUp(hrc);
                        break;
                    case 2:logIn(hrc);
                        break;
                    case 3:
                        quickLoginUsers(hrc);
                        break;
                    case 4:
                        System.out.println("Have a Nice Day Sir/Madam <3");
                        return;
                    default:System.err.println("Wrong Input!!");
                        break;
                }
            }
            catch (Exception e)
            {
                System.err.println("An Exception Occurred. \nProbably you tried to access a non existing list.");
            }
        }
    }

    /**
     * Create some users to system quickly
     * @param hrc the HRC object that holds all infos
     */
    public static void quickLoginUsers(HRC hrc) {
        System.out.println("Create a some users for quick login and test");
        System.out.println("Passwords are 123");

        System.out.println("\nAlso Notice that, There is a Native 2 users");
        System.out.println("ID = 0 PASS = 123 Role = Admin");
        System.out.println("ID = 1 PASS = 123 Role = HumanResources\n");

        System.out.println("Create Company-1 With one advertise (Name = Hatay Lezzetleri) ");
        Company c = hrc.createCompany("Hatay Yemekleri","123", "Food", 10, "Pendik");
        AdvertiseClass advert = new AdvertiseClass("Front-End Developer in Pendik", "Face-To-Face", "Front-End", "Full Time", "Pendik",10, "Food", null, "High-School", 1, "A passionate Front-End Developer for a company that aims to grow and sell all around Istanbul.");
        c.addAdvertise(advert);

        System.out.println("Create Company-2 With one advertise (Name = IBM Turkey) ");
        c = hrc.createCompany("IBM Turkey","123", "IT", 10, "Tuzla");
        advert = new AdvertiseClass("Back-End Developer - Remote", "Remote", "Back-End", "Full Time", "Tuzla",10, "IT", null, "High-School", 3, "A passionate Back-End Developer for world one of the Biggest Company");
        c.addAdvertise(advert);


        System.out.println("Create Candidate-1 (Name = Enis Yalcin)");
        Candidate cd= hrc.createCandidate("Enis Yalcin" ,"123",null/*Cv*/);
        cd.setMycv(new CvClass("Tuzla", "Enis", "Yalcin", "0534-xxx-xxxx", "e.yalcin2018@gtu.edu.tr", "Male", "05.03.1998", "Turkish", null, null, null, null, null, null, true) );
        
        System.out.println("Create Candidate-2 (Name = Baran Solmaz)");
        cd = hrc.createCandidate("Baran Solmaz" ,"123",null/*Cv*/);
        cd.setMycv(new CvClass("Pendik", "Baran", "Solmaz", "0536-xxx-xxxx", "b.solmaz2018@gtu.edu.tr", "Male", "11.09.2000", "Turkish", null, null, null, null, null, null, true) );

        System.out.println("Create a Human Resources (Name = Muhammed)");
        hrc.createHumanResources("Muhammed", "123");
    }

    /**
     * Sign-up as Company or candidate
     * @param hrc the HRC object that holds all infos
     */
    public static void signUp(HRC hrc) {
        System.out.println("1- Sign Up Company");
        System.out.println("2- Sign Up Candidate");
        
        int key=getInt("Choose: ");
        switch (key) {
            case 1: hrc.createCompany(getStr("Name: "),getStr("Password: "), getStr("Company Sector: "), getInt("Number Of Employees: "), getStr("Address: "));
                break;
            case 2: hrc.createCandidate(getStr("Name: "),getStr("Password: "),null/*CV*/);
                break;
            default :System.err.println("Wrong Input!!");
        }
    }

    /**
     * logIn as a user
     * @param hrc the HRC object that holds all infos
     */
    public static void logIn(HRC hrc) {
        Users user = null;
        int index = hrc.getUsers().indexOf(new Users(getInt("ID: "),null, getStr("Password: "), 0 ));
        if(index==-1){
            System.out.println("Invalid input.");
            return;
        }
        user=hrc.getUsers().get(index);
        
        Candidate candidate = null;
        HumanResources humanResources = null;
        Company company = null;
        Admin admin = null;

        switch(user.getType()){
            case 
                Users.CANDIDATE: 
                candidate=hrc.getCandidateID(user.getUserID());
                System.out.println("Candidate");
                candidateMenu(hrc,candidate);
                break;

            case 
                Users.HUMAN_SOURCES: 
                humanResources=hrc.getHumanResourcesID(user.getUserID());
                System.out.println("Human Resources");
                humanResourcesMenu(hrc, humanResources);
                break;

            case Users.COMPANY: 
                company = hrc.getCompanyID(user.getUserID());
                companyMenu(hrc, company);
                break;

            case Users.ADMIN:
                admin=hrc.getAdmin();
                adminMenu(hrc,admin);
                break;

        };
    }

    /**
     * Admin menu that shows options for admin
     * @param hrc the HRC object that holds all infos
     * @param admin the admin object
     */
    public static void adminMenu(HRC hrc, Admin admin){
        if(hrc==null || admin==null) return;
        HumanResources hm;
        Company select;
        Candidate candidate;
        System.out.println("\n*********** *********** ***********");
        System.out.println("Welcome " + admin.getName());
        while(true)
        {
            try {
            System.out.println("\n\n1- Add Company ");
            System.out.println("2- Update Company");
            System.out.println("3- Delete Company ");
            System.out.println("4- Add  Human Resources");
            System.out.println("5- Update Human Resources");
            System.out.println("6- Delete Human Resources");
            System.out.println("7- Add Candidate");
            System.out.println("8- Update Candidate");
            System.out.println("9- Delete Candidate\n\n");
            
            System.out.println("0- Exit");
            int choice = getInt("Choice:");
            hm = null;
            select=null;
            candidate=null;
            switch (choice) {
                
                case 0: 
                    return;
                case 1: 
                    hrc.createCompany(getStr("Name: "),getStr("Password: "),getStr("Company Sector: "), 
                        getInt("Number Of Employees: "),getStr("Address: "));
                    break;
                case 2:
                    select=companySelector(hrc,"Select Company:");
                    if(select!=null){
                        companyMenuSettings(select);
                    }else{
                        System.out.println("Company was not updated.");
                    }
                    break;
                case 3:
                     select=companySelector(hrc,"Select Company:");
                    if(select!=null){
                        if(admin.removeCompany(select)){
                            System.out.println("Company was deleted.");
                        }else
                            System.err.println("Company was not deleted.");
                    }else{
                        System.out.println("Company was not selected.");
                    }
                    
                    break;
                case 4:
                        hrc.createHumanResources(getStr("Name: "), getStr("Password: "));
                    break;
                case 5:
                        hm=humanResourcesSelector(hrc, "Select Human Resources:");
                        if(hm!=null)
                            humanResourcesUpdate(hm);
                    break;
                case 6:
                    hm=humanResourcesSelector(hrc, "Select Human Resources:");
                    if(hm!=null){
                        if(admin.removeHumanResources(hm)){
                            System.out.println("Human Resources was deleted.");
                        }else
                            System.err.println("Human Resources was not deleted.");
                    }else{
                        System.out.println("Human Resources was not selected.");
                    }
                    break;
                case 7:
                        hrc.createCandidate(
                            getStr("Name: "),
                            getStr("Password: "),
                            null/* CV*/);
                    break;
                case 8:
                    candidate=candidateSelector(hrc,"Select");
                    if(candidate!=null){
                        candidateUpdate(candidate);
                    }else{
                        System.out.println("Candidate  was not selected.");
                    }
                    break;
                case 9:
                    candidate=candidateSelector(hrc,"Select");
                    if(candidate!=null){
                        admin.removeCandidate(candidate);
                    }else{
                        System.out.println("Candidate  was not selected.");
                    }
                    break;
                default: 
                    System.err.println("Wrong Input!!");
                    break;
            }

            }
            catch (Exception e)
            {
                System.err.println("Probably you tried to access a non existing list.");
            }
        } 
        
    }

    /**
     * Company menu that shows option for company
     * @param hrc the HRC object that holds all infos
     * @param company the company that going to showed menu
     */
    public static void companyMenu(HRC hrc, Company company){
        System.out.println("\n*********** *********** ***********");
        System.out.println("Welcome " + company.getName() +  " Company Executive" );
        while(true)
        {  
            try {
            System.out.println("\n\n1- Create New Advertise");
            System.out.println("2- See Your Advertises");
            System.out.println("3- Get Rating Average");
            System.out.println("4- Add New Social Rights");
            System.out.println("5- See Applicants");
            System.out.println("6- Change Profile Settings");
            System.out.println("7- See All Information about Your Company\n\n");
            
            System.out.println("0- Exit");
            int choice = getInt("Choice:");

            
            switch (choice) {
                
                case 0: 
                    return;
                case 3: 
                    System.out.println(company.getRatingsAvg());
                    break;
                case 1:
                    company.getAdvertises().add(createAdvertise());
                    break;
                case 2:
                    System.out.println( company.getAdvertises() );
                    break;
                case 4:
                    company.addSocialRights(getStr("Social Right:"));
                    break;
                case 5:
                    Candidate candidate = candidateSelectorAdvertises(company);
                    companyMenuAdvertise(hrc, company, candidate);
                    break;
                case 6:
                    companyMenuSettings(company);
                    break;
                case 7:
                    System.out.println(company);
                    break;
                default: 
                    System.err.println("Wrong Input!!");
                    break;
            }
            }
            catch (Exception e)
            {
                System.err.println("Probably you tried to access a non existing list.");
            }
        }
    }

    /**
     * Company menu for choosing new candidate
     * @param hrc the HRC object that holds all infos
     * @param company company that select candidate
     * @param candidate the candidate that going to selected
     */
    public static void companyMenuAdvertise(HRC hrc, Company company, Candidate candidate){
       if(hrc==null || company==null || candidate==null){
           System.err.println("There is no applicant");
           return;
       }
        while(true)
        {  
            try {
            System.out.println("\n\n1- See Candidate's CV");
            System.out.println("2- Accept Candidate and Arrange Meeting");
            System.out.println("3- Decline Candidate\n\n");
            
            System.out.println("0- Exit");
            int choice = getInt("Choice:");
            switch (choice) {
                
                case 0: 
                    return;
                case 1: 
                    System.out.println(candidate.getCV());
                    break;
                case 2:
                    hrc.getDefaultHumanResources().ArrangeMeeting(getStr("Date:"), candidate, company, getStr("Time:"), getInt("Money:"));
                    return;
                case 3:
                    System.out.println("Declined Offer");
                    return;
                    
                default: 
                    System.err.println("Wrong Input!!");
                    break;
            }
            }
            catch (Exception e)
            {
                System.err.println("Probably you tried to access a non existing list.");
            }
        }
    }

    /**
     * Company menu settings for changing profile settings
     * @param company the company that going to set
     */
    public static void companyMenuSettings(Company company) {
        while(true)
        {  
            try {
            System.out.println("\n1- Change Company Name");
            System.out.println("2- Change Company Sector");
            System.out.println("3- Change Company Address");
            System.out.println("4- Change Company Employee Number");
            System.out.println("0- Exit\n");
            int choice = getInt("Choice:");
            switch (choice) {
                case 0: return;
                case 1:
                    company.setName(getStr("New Name:"));
                    break;
                case 2:
                    company.setCompanySector(getStr("New Sector:"));
                    break;
                    
                case 3:
                    company.setAddress(getStr("New Address:"));
                    break;
                
                case 4:
                    company.setNumberOfEmployees(getInt("New Employee Number:"));
                    break;

                default: 
                    System.err.println("Wrong Input!!");
                    break;
            }
            }
            catch (Exception e)
            {
                System.err.println("Probably you tried to access a non existing list.");
            }
        }
    }
    
    /**
     * HumanResources Menu
     * @param hrc the HRC object that holds all infos
     * @param hr the HR that going to use menu
     */
    public static void humanResourcesMenu(HRC hrc, HumanResources hr){
        System.out.println("\n*********** *********** ***********");
        System.out.println("Welcome " + hr.getName());
        while(true)
        {  
            try {
            System.out.println("\n\n1- Delete candidate");
            System.out.println("2- See company requests");
            System.out.println("3- Compare requests");
            System.out.println("4- Give offer to candidate");
            System.out.println("5- Arrange Meeting");
            System.out.println("6- Suggest Candidate To Company");
            System.out.println("7- Print Human Resource Info.");
            System.out.println("8- See All Meetings\n\n");
            
            System.out.println("0- Exit");
            int choice = getInt("Choice:");
            switch (choice) {
                
                case 0: 
                    return;

                case 1: 
                    hr.DeleteCandidate( candidateSelector(hrc, "Select candidate: ") );
                    break;
                    
                case 2:
                    System.out.println( hr.SeeCompanyRequest( companySelector(hrc, "Select company: ") ) );
                    break;

                case 3:
                    int c = 0;
                    AdvertiseClass temp1=advertiseSelector(companySelector(hrc, "Select company: "));
                    AdvertiseClass temp2=advertiseSelector(companySelector(hrc, "Select company: "));
                    if(temp1!=null && temp2!=null){
                        c = hr.CompareRequests(temp1 ,  temp2);
                        if     (c > 0)   System.out.println("First request");
                        else if(c < 0)   System.out.println("Second request");
                        else             System.out.println("Same");
                    }else{
                        System.err.println("Advertise Selector is not selected.");
                    }
                    break;
                case 4:
                    Candidate temp=candidateSelector(hrc, "Select candidate: ");
                    Meetings meet= meetingSelector(temp.getMeetings(), "Select Meetings");
                    if(temp!=null && meet!=null){
                        hr.GiveOfferToCandidate(temp,meet, getInt("Offer:") );
                    }else{
                        System.err.println("Candidate or Meetings is not selected.");
                    }
                    
                    break;
                case 5:
                    hr.ArrangeMeeting(getStr("Enter date: "), candidateSelector(hrc, "Select candidate: "), companySelector(hrc, "Select company: "), getStr("Enter time: "), getInt("Enter offer: ") );
                    break;
                case 6:
                    hr.SuggestCandidateToCompany(candidateSelector(hrc, "Select candidate: "), advertiseSelector(companySelector(hrc, "Select company: ")) );
                    break;
                case 7:
                    System.out.println( hr.toString() );
                    break;
                case 8:
                    meetingSelector( hr.sortMeetings(hrc.getMeetings()), "Type 0 To Exit:");
                    break;
                default: 
                    System.err.println("Wrong Input!!");
                    break;
            }
            }catch (Exception e){
                System.err.println("Probably you tried to access a non existing list.");
            }
        }
    }

    /**
     * HR update profile settings menu
     * @param hm the HR that going to updated
     */
    public static void humanResourcesUpdate(HumanResources hm) {
        while(true)
        {  
            try {
            System.out.println("\n1- Change Human Resources Name");
            System.out.println("2- Change Human Resources Password");
            System.out.println("0- Exit\n");

            int choice = getInt("Choice: ");
            switch (choice) {
                case 0: return;
                case 1:
                    hm.setName(getStr("New Name: "));
                    break;
                case 2:
                    hm.setPassword(getStr("New Password: "));
                    break;

                default: 
                    System.err.println("Wrong Input!!");
                    break;
            }
            }
            catch (Exception e)
            {
                System.err.println("Probably you tried to access a non existing list.");
            }
        }
    }

    /**
     * Candidate menu
     * @param hrc the HRC object that holds all infos
     * @param candidate the candidate that going to use this menu
     */
    public static void candidateMenu(HRC hrc,Candidate candidate) {
        System.out.println("\n*********** *********** ***********");
        System.out.println("Welcome " + candidate.getName());
        while (true) {
            try {
            System.out.println("\n\n1- Apply To Advertise");
            System.out.println("2- See Company Rating");
            System.out.println("3- Evaluate The Offer");
            System.out.println("4- Set Status To Open To Work  ");
            System.out.println("5- CV Settings");
            System.out.println("6- Change Password");
            System.out.println("7- Information");
            System.out.println("8- See CV Information\n\n");

            System.out.println("0- Exit");
            int choice = getInt("Choice:");
            switch (choice) {
           
                case 1:
                    if (candidate.getCV() == null)
                     {
                         System.out.println("First, create a CV");
                         break;
                     } 
                    if (candidate.getStatue().equals("Open To Work") != true) 
                    {
                        System.out.println("Your status is not set to 'Open To Work'.");
                        System.out.println("Do you want to change status to Open To Work?");
                        String ch = getStr("(y/n):");
                        if (ch.equals("y") == true)
                        {
                            AdvertiseClass ad = advertiseSelector(companySelector(hrc, "Select Company:"));
                            if (ad != null)
                            {
                                candidate.setStatusToOpenWork();
                                candidate.applyToAdvertisement(ad); 
                            }
                        }
                    }
                    else if (candidate.getStatue().equals("Open To Work") == true) 
                    {
                        candidate.applyToAdvertisement(
                                advertiseSelector(companySelector(hrc, "Select Company: "))); 
                    }

                    break;
                case 2: candidate.seeRatings(companySelector(hrc, "Select Company: "));
                    break;
                case 3:
                    Meetings meeting = meetingSelector(candidate.getMeetings(), "Select Offer (If no one suits you type 0): ");
                    System.out.println(meeting.toString());
                    if (meeting != null)
                    {
                        candidate.evaluateTheOffer(meeting);
                        meeting.getCompany().addRating(getInt("Rate Company:"));
                    }
                    break;
                case 4:candidate.setStatusToOpenWork();
                    break;
                case 5:candidateUpdate(candidate);
                    break;
                case 6:candidate.changePassword(getStr("New Password: "));
                    break;
                case 7:System.out.println(candidate);
                    break;
                case 8:System.out.println(candidate.getCV());
                    break;
                case 0:
                    return;
                
                default:
                    System.err.println("Wrong Input!!");
                    break;
            }
            }
            catch (Exception e)
            {
                System.err.println("Probably you tried to access a non existing list.");
            }
        }
    }

    /**
     * Candidate update menu for update profile settings and ad CV
     * @param candidate the candidate that updated
     */
    public static void candidateUpdate(Candidate candidate) {
        while(true)
        {  
            try {
            System.out.println("\n1- Add CV");
            System.out.println("2- Change Candidate Name");
            System.out.println("3- Change Candidate Password");
            System.out.println("4- Change CV");
            
            System.out.println("0- Exit\n");

            int choice = getInt("Choice:");
            switch (choice) {
                case 0: return;
                case 1:
                    candidate.setMycv(createCV());
                    break;
                case 2:
                    candidate.setName(getStr("New Name:"));
                    break;
                case 3:
                    candidate.changePassword(getStr("New Password:"));
                    break;
                case 4:
                    candidate.setMycv(cvUpdate(candidate));
                    break;
                default: 
                    System.err.println("Wrong Input!!");
                    break;
            }
            }
            catch (Exception e)
            {
                System.err.println("Probably you tried to access a non existing list.");
            }
        }
    }

    /**
     * CV update menu for Candidate
     * @param cand the candidate that used to create and update cv
     * @return created CV 
     */
    public static CvClass cvUpdate(Candidate cand){
        CvClass cv=cand.getCV();
        while (true) {
            try{
            System.out.println("\n1- Change Address");
            System.out.println("2- Change Name");
	        System.out.println("3- Change Surname");
            System.out.println("4- Change Phone Number");
            System.out.println("5- Change E-Mail");
	        System.out.println("6- Change Gender");
            System.out.println("7- Change Birthday Date");
            System.out.println("8- Change Nationality");
	        System.out.println("9- Change Cover Letter");

            System.out.println("10- Add School Info");
            System.out.println("11- Add Experience");
            System.out.println("12- Add Certificate");
            System.out.println("13- Add Capability");
            System.out.println("14- Add Referance");

            System.out.println("15- Change Driver Licence"); 

            System.out.println("0- Exit");
            int choice = getInt("Choice:");
            switch (choice) 
            {
            case 0:cand.setMycv(cv);
                return cv;
            case 1:
                cv.setAddress(getStr("New Address:"));
                break;
            case 2:
                cv.setName((getStr("New Name:")));
                break;
            case 3:
                cv.setSurname(getStr("New Surname:"));
                break;
            case 4:
                 cv.setTelNo(getStr("New Address:"));
                break;
            case 5:
                cv.setEmail(getStr("New E-Mail:"));
                break;
            case 6:
                cv.setGender(getStr("New Gender:"));
                break;
            case 7:
                cv.setBirthDay(getStr("New Birthday:"));
                break;
            case 8:
                 cv.setNationality(getStr("New Nationality:"));
                break;
            case 9:
                cv.setCoverLetter(getStr("New Cover Letter:"));
                break;
            case 10:
                cv.addSchoolInfo(createSchoolInfo());
                break;
            case 11:
                cv.addExperience(createExperience());
                break;
            case 12:
                cv.addCertificate(createCertificate());
                break;
            case 13:
                cv.addCapability(createCapabilities());
                break;
            case 14:
                cv.addReferances(createReferance());
                break;

            case 15:
                 cv.setDriversLicense(!cv.isDriversLicense());
                break;

            default:
                System.err.println("Wrong Input!!");
                break;
            }
            }
            catch (Exception e)
            {
                System.err.println("Probably you tried to access a non existing list.");
            }
        }

        
    }

    /**
     * A selector for select advertises of a given company
     * @param company the company that going to showed advertises
     * @return candidate 
     */
    public static Candidate candidateSelectorAdvertises(Company company){
        Candidate returnVal=null;

        if (company.getAdvertises().size() == 0)
            return null;

        Iterator<AdvertiseClass> iter = company.getAdvertises().iterator();

        int i = 1;
        while(iter.hasNext()){
            AdvertiseClass ad = iter.next();
            Iterator<Candidate> itCandidate = ad.getApplies().iterator();
            while(itCandidate.hasNext())
            {
                Candidate candidate_ = itCandidate.next();
                if (candidate_.getStatue().equals("Open To Work"))
                {
                    System.out.println( i + ": " + candidate_ );
                    i++;    
                }
            }
        }
        if(i==1){
            System.err.println("No Candidate!!");
            return null;
        } 
        int select = getInt("Select candidate: ");
        
        iter = company.getAdvertises().iterator();

        i = 1;
        while(iter.hasNext()){
            AdvertiseClass ad = iter.next();
            Iterator<Candidate> itCandidate = ad.getApplies().iterator();
            while(itCandidate.hasNext())
            {
                Candidate candidate_ = itCandidate.next();
                if (i == select)
                    return candidate_;
                    
                if (candidate_.getStatue().equals("Open To Work"))
                    i++;
            }
        }
        
        return null;
    }
    
    /**
     * A selector for select advertises of a given company
     * @param company the company that going to showed advertises
     * @return selected advertise
     */
    public static AdvertiseClass advertiseSelector(Company company){
        AdvertiseClass returnVal=null;
        Iterator<AdvertiseClass> iter = company.getAdvertises().iterator();
        int i=1;
        while(iter.hasNext()){
            System.out.println( i + ": " + iter.next() );
            i++;
        }
        if (i == 1) {
            System.err.println("No Advertise!!");
            return null;
        }
        int select = getInt("Select Advertise: ");
        if (select == 0) return null;
        if(select > -1 && select - 1 < company.getAdvertises().size())
            return company.getAdvertises().get(select-1);
        else return null;
    }

    /**
     * A selector for choosing a company from all companies
     * @param hrc the hrc for choosing companies
     * @param str the str for showing selection 
     * @return selected company if exist
     */
    public static Company companySelector(HRC hrc,String str){
        if(hrc==null) return null;
        
        Iterator<Company> iter=hrc.getCompany().iterator();
        int i=1,select=-1;
        Company returnVal=null,temp=null;
        
        while(iter.hasNext()){
            Company company = iter.next();
            System.out.println((i++ )+" - "+company.getName());
        }
        if(i == 1){
            System.err.println("No Company!!");
            return null;
        } 
        System.out.println("0 - Exit");
        while(true){
           if(str!=null && !str.isEmpty())
                select=getInt(str+":");
            else 
                select=getInt("Your Select:");
           if(select==0){
               return null;
           }else if(select>0 && hrc.getCompany().size()>=select){
               iter=hrc.getCompany().iterator();
               i=1;
               while(iter.hasNext()){
                   temp=iter.next();
                   if(i==select){
                       returnVal=temp;
                       return returnVal;
                   }
                   i++;
               }
           }
        }
    }

    /**
     * A selector for selecting HR
     * @param hrc the hrc object that contains all HR
     * @param str the str for showing output
     * @return
     */
    public static HumanResources humanResourcesSelector(HRC hrc,String str){
        if(hrc==null) return null;
        
        Iterator<HumanResources> iter=hrc.getHumanResources().iterator();
        
        int i=1,select=-1;
        HumanResources returnVal=null,temp=null;
        
        while(iter.hasNext()){
            HumanResources humanResources = iter.next();
            System.out.println(i+++" - "+humanResources.toString());
        }
        if (i == 1) {
            System.err.println("No Human Resources!!");
            return null;
        }
        System.out.println("0 - Exit");
        while(true){
           if(str!=null && !str.isEmpty())
                select=getInt(str+":");
            else 
                select=getInt("Your Select:");
           if(select==0){
               return null;
           }else if(select>0){
               iter=hrc.getHumanResources().iterator();
               i=1;
               while(iter.hasNext()){
                   temp=iter.next();
                   if(i==select){
                       returnVal=temp;
                       return returnVal;
                   }
                   i++;
               }
            }
        }
    }

    /**
     * A selector for choosing a candidate
     * @param hrc the hrc object that contains all candidates
     * @param str the str for showing desired output
     * @return selected candidate if exist
     */
    public static Candidate candidateSelector(HRC hrc,String str){
        if(hrc==null) return null;
        Iterator<Map.Entry<Integer,Candidate>> iter= hrc.getCandidate().entrySet().iterator();
        Map.Entry<Integer, Candidate> temp=null;
        int i=1,select=-1;
        while(iter.hasNext()){
            System.out.println(i+" - "+iter.next().getValue().getName());
            i++;
        }
        if(i==1){
            System.err.println("No Candidate!!");
            return null;
        } 
        System.out.println("0 - Exit");

        while(true){
           if(str!=null && !str.isEmpty())
                select=getInt(str+":");
            else 
                select=getInt("Your Select:");
           if(select==0){
               return null;
           }else if(select>0){
               iter= hrc.getCandidate().entrySet().iterator();
               i=1;
               while(iter.hasNext()){
                   temp=iter.next();
                   if(i==select){
                       return temp.getValue();
                   }
                   i++;
               }
            }
        }
        
    }

    /**
     * A meeting selector for selecting meetings
     * @param meetings the meeting collection for choosing 
     * @param str the str for showing output
     * @return selected meeting if not null
     */
    public static Meetings meetingSelector(Collection<Meetings> meetings,String str){
        if(meetings == null)
            return null;
        Iterator<Meetings> iter=meetings.iterator();
        int i=1,select=-1;
        Meetings returnVal=null,temp=null;
        while(iter.hasNext()){
            Meetings meeting = iter.next();
            System.out.println((i++ )+" - "+meeting.toString());
        }
        if (i == 1) {
            System.err.println("No Meeting!!");
            return null;
        }
        System.out.println("0 - Exit");
        while(true){
           if(str!=null && !str.isEmpty())
                select=getInt(str+":");
            else 
                select=getInt("Your Select:");
           if(select==0){
               return null;
           }else if(select>0){
               iter=meetings.iterator();
               i=1;
               while(iter.hasNext()){
                   temp=iter.next();
                   if(i==select){
                       return temp;
                   }
                   i++;
               }
           }
        }
    }

    public static AdvertiseClass createAdvertise() {
        AdvertiseClass advert = new AdvertiseClass(getStr("Title:"), getStr("Way of Work (remote or face-to-face):"), getStr("Role:"), getStr("Job Type:"), getStr("Location:"), getInt("Vacancies:"), getStr("Industry:"), getCapabilities(), getStr("Education Level:"), getInt("Experience Year:"), getStr("Description:"));
        return advert;
    } 

    public static CvClass createCV() {
        CvClass cv = new CvClass(getStr("Adress:"), getStr("Name:"), getStr("Surname:"), getStr("Tel no:"), getStr("E-Mail:"), getStr("Gender:"), getStr("Birthday:"), getStr("Nationality:"), null, null, null, null, null, null, false);
        return cv;
    }

    public static CvClass.SchoolClass createSchoolInfo() {
        CvClass.SchoolClass sc = new CvClass.SchoolClass(getStr("School Name:"), getStr("Faculty:"), getStr("Department:"), getStr("End-Date:"), getStr("Start-Date:"), getStr("Education Type:"), getStr("Education Language:"), getInt("Shchool Average: "));
        if (sc == null)
            System.out.println("Null");

        return sc;      
    }

    public static CvClass.Experience createExperience() {
        CvClass.Experience exp = new CvClass.Experience(getStr("Company Name:"), getStr("Start Date: "), getStr("Position: "), getStr("End Date:"), getStr("City:"), getStr("Business Area:"), getStr("Job Descrpition:"), getStr("Company Sector:"), getStr("Way of Work:"));
        return exp;
    }

    public static CvClass.Referance createReferences() {
        CvClass.Referance ref = new CvClass.Referance(getStr("Ref Name:"), getStr("Tel No:"), getStr("E-Mail:"), getStr("Company Name:"), getStr("Job:"));
        return ref;
    }

    public static ArrayQueue<String> getCapabilities() {
        ArrayQueue<String> capabilities = new ArrayQueue<>();

        int size = getInt("Enter Capabilities Size:");
        for (int i = 0; i < size; i++)
        {
            capabilities.offer(getStr("Enter " + (i+1) +". capabilites:"));
        }
        return capabilities;
    }

    public static CvClass.Certificate createCertificate(){    
        return new CvClass.Certificate(getStr("Certificate Name:"),getStr("Institution Name: "),getStr("Certificate Date:"),getStr("Explanation:"));
    }
    
    public static ArrayList<String> createCapabilities(){
        ArrayList<String> capabilities = new ArrayList<>();
        int size = getInt("Enter Capabilities Size:");
        for (int i = 0; i < size; i++){
            capabilities.add( getStr("Enter " + (i+1) +". capabilites:") );
        }
        return capabilities;
    }
    
    public static CvClass.Referance createReferance(){
        CvClass.Referance reference = new CvClass.Referance(getStr("Referance Name:"), getStr("Phone No:"), getStr("Email:"), getStr("Company Name:"), getStr("Job:"));
        return reference;
    }

    public static Meetings createMeeting(HRC hrc){
        //String date, Candidate candidate, Company company, String time, int offer
        return new Meetings(getStr("Date:"),candidateSelector(hrc,"Select Candidate:"),companySelector(hrc, "Select Company:"),getStr("Time: "),getInt("Offer:"));
    }
    
    @SuppressWarnings("resource")
    public static int getInt(String str) {
        System.out.print(str);
        Scanner scanner = new Scanner(System.in);
        int temp = -1;
        while (temp == -1) {
            try {
                temp = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Wrong input. Enter an int. Try Again");
                scanner.nextLine();
            }
        }
        // scanner.close();
        return temp;
    }

    @SuppressWarnings("resource")
    public static String getStr(String str) {
        System.out.print(str);
        Scanner scanner = new Scanner(System.in);
        String temp = scanner.nextLine();
        // scanner.close();
        return temp;
    }
}
