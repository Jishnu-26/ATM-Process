public class AtmDetails{
    private int balanceamount;
    private  int depositamount;
    private int withdrawamount;

    public AtmDetails() {
    }

    public int getBalanceAmount() {
        return balanceamount;
    }

    public void setBalanceAmount(int balanceamount) {
        this.balanceamount = balanceamount;
    }

    public int getDeposingAmount() {
        return depositamount;
    }

    public void setDeposingAmount(int depositamount) {
        this.depositamount = depositamount;
    }

    public int getWithdrawAmount() {
        return withdrawamount;
    }

    public void setWithdrawAmount(int withdrawamount) {
        this.withdrawamount = withdrawamount;
    }
}
