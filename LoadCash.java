public class LoadCash{
    private int twothousand;
    private int fivehundred;
    private int onehundred;

    public LoadCash() {
    }

    public int getTwoThousand() {
        return twothousand;
    }

    public void setTwoThousand(int twothousand) {
        this.twothousand = twothousand;
    }

    public int getFiveHundred() {
        return fivehundred;
    }

    public void setFiveHundred(int fivehundred) {
        this.fivehundred = fivehundred;
    }

    public int getOneHundred() {
        return onehundred;
    }

    public void setOneHundred(int onehundred) {
        this.onehundred = onehundred;
    }
    public static void loadDenomination(int amount, int denomination, LoadCash denom){
        if(amount==2000){
            denom.setTwoThousand(denom.getTwoThousand()+denomination);
        }
        else if(amount==500){
            denom.setFiveHundred(denom.getFiveHundred()+denomination);
        }
        else if(amount==100){
            denom.setOneHundred(denom.getOneHundred()+denomination);
        }
    }
    public static void SubDenomination(int amount, int denomination, LoadCash denom){
        if(amount==2000){
            denom.setTwoThousand(denom.getTwoThousand()-denomination);
        }
        else if(amount==500){
            denom.setFiveHundred(denom.getFiveHundred()-denomination);
        }
        else if(amount==100){
            denom.setOneHundred(denom.getOneHundred()-denomination);
        }
    }
}
