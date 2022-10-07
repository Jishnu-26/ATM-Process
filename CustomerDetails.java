import java.util.*;
public class CustomerDetails {
    private  String accNo;
    private String cName;
    private String pin;
    private int accountBalance;


    public CustomerDetails(){

    }
    public CustomerDetails(String accNo, String cName, String pin, int accountBalance) {
        this.accNo = accNo;
        this.cName = cName;
        this.pin = pin;
        this.accountBalance = accountBalance;
    }
    public String getAccountNumber() {
        return accNo;
    }

    public void setAccountNumber(String accNo) {
        this.accNo = accNo;
    }

    public String getCustomerName() {
        return cName;
    }

    public void setCustomerName(String cName) {
        this.cName = cName;
    }

    public String getPinNumber() {
        return pin;
    }

    public void setPinNumber(String pin) {
        this.pin = pin;
    }

    public int getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(int accountBalance) {
        this.accountBalance = accountBalance;
    }


    public boolean checkAccountNumber(String accNo, TreeMap<String, CustomerDetails> customer){
        if(accNo.equals(customer.get(accNo).getAccountNumber())){
            return true;
        }
        return false;
    }
    public boolean checkPinNumber(String accNo, String pin, TreeMap<String, CustomerDetails> customer){
        if(pin.equals(customer.get(accNo).getPinNumber())){
            return true;
        }
        return false;
    }


    public void transferAmount(String fromAccountNumber, String toAccountNumber, int amount, TreeMap<String, CustomerDetails> customer){
        customer.get(fromAccountNumber).withdraw(fromAccountNumber, amount, customer);
        customer.get(toAccountNumber).deposit(toAccountNumber, amount, customer);
    }
    public void withdraw(String accNo, int amount, TreeMap<String, CustomerDetails> customer){
         customer.get(accNo).setAccountBalance(getAccountBalance()-amount);
    }

    public void deposit(String accNo, int amount, TreeMap<String, CustomerDetails> customer){
        customer.get(accNo).setAccountBalance(getAccountBalance()+amount);
    }
}
