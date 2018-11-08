package Dispensibles.SpeculativeGenerality;

public class Company {
    public static void main(String[] args)
    {
        Factory nutsChipsandChunks = new Factory(50);

        nutsChipsandChunks.fillHoppers();

        nutsChipsandChunks.checkLevels();
    }
}
