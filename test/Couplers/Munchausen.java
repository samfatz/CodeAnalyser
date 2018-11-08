package Couplers;
//from https://rosettacode.org/wiki/Munchausen_numbers#Java

//Message chain
public class Munchausen {
    public static void main(String[] args) {
        for(int i = 0 ; i <= 5000 ; i++ ){
            int val = String.valueOf(i).chars().map(x -> (int) Math.pow( x-48 ,x-48)).sum();
            if( i == val){
                System.out.println( i + " (munchausen)");
            }
        }
    }
}
