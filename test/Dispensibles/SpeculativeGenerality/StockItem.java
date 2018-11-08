package Dispensibles.SpeculativeGenerality;

public interface StockItem {

    public void setReorderLevel(int r);

    public int getReorderLevel();

    public void setStockLevel(int s);

    public int getStockLevel();

    public boolean reorder();

    public String getDescription();

}
