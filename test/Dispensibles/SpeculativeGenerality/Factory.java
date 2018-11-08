package Dispensibles.SpeculativeGenerality;

public class Factory {

    BasicStockItem[] hoppers;

    public Factory()
    {
        hoppers = new BasicStockItem[10];
    }

    public Factory(int h)
    {
        hoppers = new BasicStockItem[h];
    }

    public void fillHoppers()
    {
        for(int i = 0; i < hoppers.length; i = i + 3)
            hoppers[i] = new Chunks(10 * i, 50);
        for(int i = 1; i < hoppers.length; i = i + 3)
            hoppers[i] = new Chips(5 * i, 40);
        for(int i = 2; i < hoppers.length; i = i + 3)
            hoppers[i] = new Nuts(7 * i, 70);
    }

    public void checkLevels()
    {
        for(int i = 0; i < hoppers.length; i++)
            if (hoppers[i].reorder())
                System.out.println("Hopper " + i + " low. Reorder more " +
                        hoppers[i].getDescription());
    }
}
