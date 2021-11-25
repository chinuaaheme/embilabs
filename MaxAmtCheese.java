import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MaxAmtCheese {

  private static int recursivelyStoreMaxValue(int numOfCheeseBlocks, int pos, boolean previousEaten, int[] cheeseArr, Integer[][] resultArr) {
    //Check to see if the pointer has reached to end of array
    if (pos == cheeseArr.length) {
      return 0;
    }

    //Check that current block can't be eaten when previous block is already eaten
    if (previousEaten) {
        if (resultArr[pos][1] != null) {
            return resultArr[pos][1];
        }
        resultArr[pos][1] = recursivelyStoreMaxValue(numOfCheeseBlocks, pos + 1, false, cheeseArr, resultArr);
        return resultArr[pos][1];
    } else {
        if (resultArr[pos][0] != null) {
            return resultArr[pos][0];
        }
    }

    /* If previous block is not eaten then we have two choices
       1. Can eat current block - which is calculated by first paramater of Math.max function
       2. Can ignore current block - which is calculated by second paramater of Math.max function */
    resultArr[pos][0] = Math.max(recursivelyStoreMaxValue(numOfCheeseBlocks, pos + 1, true, cheeseArr, resultArr) + cheeseArr[pos], recursivelyStoreMaxValue(numOfCheeseBlocks, pos + 1, false, cheeseArr, resultArr));
    return resultArr[pos][0];
  }  
 
  private static int maxAmtCheese(int numOfCheeseBlocks, int[] cheese) {
    Integer[][] arrToStoreResult = new Integer[numOfCheeseBlocks][2];
   
    //Recursive function calls to calculate maximum weight
    int atePreviusBlock = recursivelyStoreMaxValue(numOfCheeseBlocks, 0, false, cheese, arrToStoreResult);
    int didNotAtePreviousBlock = recursivelyStoreMaxValue(numOfCheeseBlocks, 0, true, cheese, arrToStoreResult);
    return Math.max(atePreviusBlock, didNotAtePreviousBlock);
  }

  public static void main(String[] args) {
    BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
    try {
       
      int numOfTestCases = Integer.parseInt(inputReader.readLine());
      while(numOfTestCases > 0){
          int numOfCheeseBlocks = Integer.parseInt(inputReader.readLine());
          int[] cheese = new int[numOfCheeseBlocks];
          String[] cheeseBlocksWeights = inputReader.readLine().split(" ");
          for (int j = 0; j < numOfCheeseBlocks; j++) {
               cheese[j] = Integer.parseInt(cheeseBlocksWeights[j]);
          }
        System.out.println(maxAmtCheese(numOfCheeseBlocks, cheese));
        numOfTestCases--;
      }
    } catch (IOException | NumberFormatException numFormatExp) {
      System.out.println("Invalid input entered. Please enter number");
    }
  }
}