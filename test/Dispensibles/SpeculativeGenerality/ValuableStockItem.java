package Dispensibles.SpeculativeGenerality;

public class ValuableStockItem extends BasicStockItem{
    protected double value;

    public ValuableStockItem(){
        super();
    }

    public ValuableStockItem(int s, int r, String d, double v){
        super(s,r,d);
        value = v;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
