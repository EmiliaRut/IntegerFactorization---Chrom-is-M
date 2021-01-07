/*
 * Chrom is m
 */
package integerfactorization;

/**
 * @date September 30, 2018
 * @author Emilia
 */

import java.math.BigInteger;
import java.util.Random;

public class Chromosome {
    
    private int[] chrom;  //the array that holds the order of the cities in question
    private int length;
    private Random rand;
    private BigInteger eval;
    private BigInteger prime;
  
  public Chromosome(int l, Random r) {
    
      length = l;
      chrom = new int[length];
      eval = new BigInteger(BigInteger.valueOf(Long.MAX_VALUE).toString());
      rand = r;
    
  } //constructor
  
  public Chromosome(Chromosome c) {
      length = c.length;
      chrom = new int[length];
      for(int i= 0; i < length; i ++) {
          chrom[i] = c.chrom[i];
      }
      eval = c.getEval();
      prime = c.getPrime();
      rand = c.rand;
  } //copy
  
  public int[] getChromosome() {
      return chrom;
  }//getChromosome
  
  /*This method sets the ith element in the Chromosome to c
   * @param i  the position in the chrom array
   * @param c  the value the element will be set to
   * */
  public void set(int i, int c) {
    chrom[i] = c;
  } //set()
  
//  public void copy(Chromosome c) {
//      for(int i= 0; i < length; i ++) {
//          chrom[i] = c.get(i);
//      }
//      eval = c.getEval();
//  }//copy
  
  public int getSize() {
      return chrom.length;
  }//getSize
  
  /*This function returns the value of the ith element in the chrom array
   * @param i  the element of the chrom array
   * @return int  the value of the ith element
   * */
  public int get(int i) {
    return chrom[i];
  } //get()
  
  public BigInteger getEval() {
      return eval;
  }//getEval
  
  public BigInteger getPrime() {
      return prime;
  }//getPrime
  
  public void setPrime(BigInteger p) {
      prime = p;
  }//setPrime
  
  public void randTour() {
      //chrom[0] = 1;
      //do {
        for (int i=0; i < length; i++) {
            if (i == 0) {
                chrom[i] = 1;
            } else {
                chrom[i] = rand.nextInt(2);
            }
        }
      //} while(!atLeastOneOne(2));
  }//randTour
  
  private boolean atLeastOneOne(int x) {
      for(int i = 0; i < x; i++) {
          if(chrom[i] == 1) return true;
      }
       return false;
  } //atLeastOneOne
  
  public void mutation() {
      int randAllele;
      
      do {
        randAllele = rand.nextInt(length);
      } while ((randAllele == 0)); // || randAllele == 0) && chrom[randAllele] == 1);
      
      if (chrom[randAllele] == 0) {
          chrom[randAllele] = 1;
      } else {
          chrom[randAllele] = 0;
      }
  }//mutation
  
  public void evaluationFunction(BigInteger n) {
      String strChrom = "";
      for(int i = 0; i < chrom.length; i++) {
          strChrom = strChrom + chrom[i];
      }
      BigInteger decChrom = new BigInteger(Long.parseLong(strChrom, 2)+"");
      
      BigInteger mPlus = convertToPrimePlus(decChrom);
      BigInteger mMinus = convertToPrimeMinus(decChrom);
      
      BigInteger mPlusEval = n.remainder(mPlus);
      BigInteger mMinusEval = n.remainder(mMinus);
      
      if (decChrom.equals(new BigInteger("0"))) {
          eval = new BigInteger(BigInteger.valueOf(Long.MAX_VALUE).toString());
      } else if (mPlusEval.compareTo(mMinusEval) < 0) {
          prime = mPlus;
          eval = mPlusEval;
      } else {
          prime = mMinus;
          eval = mMinusEval;
      }
      
  }//evaluationFunction
  
  private BigInteger convertToPrimePlus(BigInteger m) {
      BigInteger six = new BigInteger("6");
      BigInteger one = new BigInteger("1");
      return (m.multiply(six)).add(one);
  } //convertToPrimePlus
  
  private BigInteger convertToPrimeMinus(BigInteger m) {
      BigInteger six = new BigInteger("6");
      BigInteger one = new BigInteger("1");
      return (m.multiply(six)).subtract(one);
  } //convertToPrimeMinus
  
} //Chromosome