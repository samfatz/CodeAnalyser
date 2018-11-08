package Dispensibles.SpeculativeGenerality;

public abstract class BasicStockItem implements StockItem {

    protected String description;
    protected int stocklevel;
    protected int reorderlevel;

    public BasicStockItem(){
        stocklevel = 0;
        reorderlevel = 0;
    }

    public BasicStockItem(int s, int r, String d){
        stocklevel = s;
        reorderlevel = r;
        description = d;
    }

    public void setReorderLevel(int r){
        reorderlevel = r;
    }

    public int getReorderLevel() {
        return reorderlevel;
    }

    public void setStockLevel(int s) {
        stocklevel = s;
    }

    public int getStockLevel() {
        return stocklevel;
    }

    public boolean reorder() {
        if (stocklevel < reorderlevel)
            return true;
        else
            return false;
    }

    public String getDescription() {
        return description;
    }
}
