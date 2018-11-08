package Dispensibles.SpeculativeGenerality;

public class SeasonalStockItem extends BasicStockItem{
    protected String season;

    public SeasonalStockItem(){
        super();
    }

    public SeasonalStockItem(int s, int r, String d, String sn){
        super(s,r,d);
        season = sn;
    }

}
