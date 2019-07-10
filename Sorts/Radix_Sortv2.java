package Radix_Sort;

public class Radix_Sortv2 {
    public static void main(String[] args) {
        int[] arr = new int[]{3,2,105,35,115,12,14,18,32};
        int[] arr2 = new int[]{67,45,25,7,3,32,21,98,56,110};
        for(int i: Radix_Sort(arr2)){
            System.out.print(i+" ");
        }
        //SD = Significant digit
        //1s, 10s, 100s,...
        //Example: the 2nd SD of 567 is 2
        //(but since we are IT we start at 0, so it will actually  output 5)
    }
    public static int[] Radix_Sort(int[] array){
        /**
         Pseudocode
         1 Get the largest SD
         2 Get SD
         3 Count its occurrence(how many times it appears)
         4 Modify the array
         5 Iterate over the array and position properly using the digit in the counter
         6 subtract the counter by 1
         7 Go to the next Significant Digit

         What is a radix sort?
             - It is a sorting algorithm that doesn't use comparison
             but instead uses occurrences and the natural order of 0-9
             to decide where to put a number. The sort makes use of the counting sort
             as a subroutine
         **/
        int sd = 0;
        int[] result = new int[array.length];
        // 1 getLargestSDPos
        while (sd != getLargestSDPosition(array)) {
            int[] counter = new int[10];
            result = new int[array.length];
            int startingpoint = 0;
            for (int num : array){
                // 2 and 3 getSD and count its occurrence
                if(getSD(num,sd) != -1)
                    counter[getSD(num,sd)]++;
                else{
                    result[startingpoint] = num;
                    startingpoint++;
                }
            }
            counter[0]+=startingpoint;
            // 4 Modify the array starting at startingpoint
            for(int i = 1; i < 10;i++){
                counter[i] = counter[i-1]+counter[i];
            }
            // 5 Iterate and position
            // Example:
            // the counter looks like this [0,0,2,2,3,4,4,5,5,6]
            // if we position the number 39 it would be placed in position 6 in the result
            // N = number/null
            // [N,N,N,N,N,39]
            for(int i = array.length-1;i >= 0;i--){
                int num = array[i];
                int numsd  = getSD(num,sd);
                if(numsd!=-1) {
                    // 6 subtract by 1
                    result[--counter[numsd]] = num;
                }
            }
            array=result;
            // 7
            sd++;
        }
        return result;
    }
    public static int getLargestSDPosition(int[] arr){
        /*
            check each number and count its length
            12345 length = 5;
         */
        int largestSDPosition = 0;
        for(int num:arr){
            //if the current recorded position is greater than the current number  then keep the current otherwise change it
            largestSDPosition = (largestSDPosition > Integer.toString(num).length()) ? largestSDPosition : Integer.toString(num).length();
        }
        return largestSDPosition;
    }
    public static int getSD(int num, int sd){
        /*
        - Remove the digits before the requested SD
        - Take the last digit using modulo
        Note: 0 = ones, 1 = tens, 2 = hundreds,...
        Example:
        Get the 3rd SD of 59863
        59863 / 10 ^ 2 -> 598
        598 % 10 -> 8
        */
        int temp = num;
        if(temp < Math.pow(10,sd))
            return -1;
        temp/=Math.pow(10,sd);
        return temp%=10;
    }
}
