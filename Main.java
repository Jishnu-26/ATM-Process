import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {
        try{
            Scanner sc = new Scanner(System.in);
            AtmDetails AtmDetails = new AtmDetails();
            CustomerDetails cust = new CustomerDetails();
            LoadCash denom=new LoadCash();
            TreeMap<String, CustomerDetails> customer=new TreeMap<>();
            //populating with existing data given in question
            CustomerDetails customerDatabase1 = new CustomerDetails("101","suresh", "2343", 25234);
            customer.put("101", customerDatabase1);
            CustomerDetails customerDatabase2 = new CustomerDetails("102","Ganesh", "5432", 25234);
            customer.put("102", customerDatabase2);
            CustomerDetails customerDatabase3 = new CustomerDetails("103","Magesh", "7854", 25234);
            customer.put("103", customerDatabase3);
            CustomerDetails customerDatabase4 = new CustomerDetails("104","Naresh", "2345", 25234);
            customer.put("104", customerDatabase4);
            CustomerDetails customerDatabase5 = new CustomerDetails("105","Harish", "1907", 100000);
            customer.put("105", customerDatabase5);
            int[] notes=new int[]{2000, 500, 100};
            while (true){
                int option=0;
                System.out.println("Please Load money into ATM before performing operations\n1. Load ATM\n2. Maintain Customer Details\n3.HANDLE ATM PROCESS\n4. End");
                option=sc.nextInt();
                System.out.println();
                sc.nextLine();
                switch (option){
                    case 1:
                    {
                        System.out.println("--------    Load Cash To ATM    --------");
                        System.out.println("Enter Amount to Load(Eg:2000X5,500X5,100X5) : ");
                        String[] denominations=sc.nextLine().split(",");
                        for(String spr:denominations){
                            String[] val=spr.split("X");
                            int amount=Integer.valueOf(val[0].trim());
                            int numNotes=Integer.valueOf(val[1]);
                            if(amount<0||numNotes<0 || amount==0||numNotes==0) {
                                System.out.println("Cannot deposit in negative or be 0");
                            }
                            else{
                                denom.loadDenomination(amount, numNotes, denom);
                                AtmDetails.setBalanceAmount(2000*denom.getTwoThousand()+500*denom.getFiveHundred()+100* denom.getOneHundred());
//                                System.out.println(AtmDetails.getBalanceAmount());
                            }
                        }

                        System.out.println("Denomination            Number  Value       ");
                        System.out.println("2000                    "+denom.getTwoThousand()+"       "+2000*denom.getTwoThousand());
                        System.out.println("500                     "+denom.getFiveHundred()+"       "+500*denom.getFiveHundred());
                        System.out.println("100                     "+denom.getOneHundred()+"       "+100*denom.getOneHundred());
                        break;
                    }
                    case 2:
                    {
                        System.out.println("--------Maintain Customer Details ---------");

                        System.out.println("AccountNo    Account Holder    Pin Number    Account Balance");
                        for(Map.Entry<String, CustomerDetails> entry: customer.entrySet()){
                            System.out.println(entry.getValue().getAccountNumber()+"    \t \t  "+entry.getValue().getCustomerName()+"  \t  \t    "+entry.getValue().getPinNumber()+"    \t    "+entry.getValue().getAccountBalance());
                        }
                        break;
                    }
                    //  ALL THE ATM PROCESS  and Operation
                    case 3:
                    {
                        System.out.println("--------    Handle ATM PROCESS   --------");
                        System.out.println("1.Check Balance\n2.WithDraw Money\n3. Transfer Money\n4. Check ATM Balance");
                        int choice=sc.nextInt();
                        switch (choice){
                            case 1:
                            {
                                System.out.println("--------    Check Account Balance    --------");
                                System.out.println("Enter the Account Number : ");
                                String accountNumber=sc.next();
                                System.out.println("Enter the Pin Number : ");
                                String pinNumber=sc.next();
                                try{
                                    if(cust.checkAccountNumber(accountNumber, customer)&&cust.checkPinNumber(accountNumber, pinNumber, customer)){
                                        System.out.println("AccNo  AccountHolder    PinNumber AccountBalance");
                                        System.out.println(accountNumber+"        "+customer.get(accountNumber).getCustomerName()+"        "+pinNumber+"        "+customer.get(accountNumber).getAccountBalance());
                                    }
                                    else{
                                        System.out.println("Invalid Account Number or Pin Number");
                                        break;
                                    }
                                }catch (Exception e){
                                    System.out.println("Invalid details");
                                }
                                break;
                            }
                            case 2:
                            {
                                System.out.println("--------    Withdraw  Money  --------");
                                int flag=0;
                                System.out.println("Enter the Account Number : ");
                                String accountNumber=sc.next();
                                System.out.println("Enter the Pin Number : ");
                                String pinNumber=sc.next();
                                if(cust.checkAccountNumber(accountNumber, customer)&&cust.checkPinNumber(accountNumber, pinNumber, customer)) {
                                    System.out.println("Enter the amount to Withdraw : ");
                                    int withdrawAmount = sc.nextInt();
                                    System.out.println(AtmDetails.getBalanceAmount());
                                    if (withdrawAmount <= 0 || withdrawAmount > AtmDetails.getBalanceAmount()) {
                                        System.out.println("Incorrect or Insufficient Funds");
                                        break;
                                   } //else if (customer.get(accountNumber).getAccountBalance() > 10000 || customer.get(accountNumber).getAccountBalance() < 100) {
//                                        System.out.println("Withdraw Amount should be between  10000 and  100");
//                                        break;
//                                    }
                                    customer.get(accountNumber).withdraw(accountNumber, withdrawAmount, customer);
                                    if (withdrawAmount >= 100 && withdrawAmount <= 10000) {
                                        flag = 0;
                                    }
                                    while (flag != 0) ;
                                    int number2000 = 0, number500 = 0, number100 = 0;
                                    while (withdrawAmount > 100) {
                                        if (withdrawAmount >= 2000) {
                                            number2000++;
                                            withdrawAmount -= 2000;
                                        } else if (withdrawAmount >= 500 && withdrawAmount < 2000) {
                                            number500++;
                                            withdrawAmount -= 500;
                                        } else if (withdrawAmount >= 100 && withdrawAmount < 100) {
                                            number100++;
                                            withdrawAmount -= 100;
                                        }
                                    }
                                    denom.SubDenomination(2000,number2000,denom);
                                    denom.SubDenomination(500,number500,denom);
                                    denom.SubDenomination(100,number100,denom);
                                }
                                else{
                                    System.out.println("Invalid Acoount Number or Pin Number");
                                }
                                break;
                            }
                            case 3:
                            {
                                System.out.println("--------   Transfer Money   --------");

                                System.out.println("Enter the Account Number : ");
                                String fromAccountNumber=sc.next();
                                System.out.println("Enter the Pin Number : ");
                                String fromPinNumber=sc.next();
                                if(cust.checkAccountNumber(fromAccountNumber, customer)&&cust.checkPinNumber(fromAccountNumber, fromPinNumber, customer)){
                                    System.out.println("Enter the Account Number to make Transfer : ");
                                    String toAccountNumber=sc.next();
                                    System.out.println("Enter the Amount to Transfer : ");
                                    int transferAmount=sc.nextInt();
                                    cust.transferAmount(fromAccountNumber, toAccountNumber, transferAmount, customer);
                                }
                                else{
                                    System.out.println("Invalid Account Number or Pin Number");
                                    break;
                                }
                                break;
                            }
                            case 4:
                            {
                                int currentAtmBalance=AtmDetails.getBalanceAmount();
                                if(currentAtmBalance<=0){
                                    System.out.println("No Fund");
                                    continue;
                                }
                                else{
                                    System.out.println("-------- Check ATM Balance --------");
                                    System.out.println("Denomination            Number  Value       ");
                                    System.out.println("2000                    "+denom.getTwoThousand()+"       "+2000*denom.getTwoThousand());
                                    System.out.println("500                     "+denom.getFiveHundred()+"       "+500*denom.getFiveHundred());
                                    System.out.println("100                     "+denom.getOneHundred()+"       "+100*denom.getOneHundred());
                                    System.out.println("Total Amount available in ATM is = Rs. "+AtmDetails.getBalanceAmount());
                                }
                                break;
                            }
                            default:
                            {
                                System.out.println("Please Enter valid option...");
                            }

                        }
                        break;


                    }
                    case 4:
                    {
                        System.out.println("Thank You!");
                        System.exit(0);
                        break;
                    }
                    default:
                    {
                        System.out.println("Please Enter valid option...");
                    }
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


}
