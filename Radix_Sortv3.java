package Radix_Sort;

public class Radix_Sortv3 {
    public static void main(String[] args) {
        int[] arr = new int[]{3,2,105,35,115,12,14,18,32};
        int[] arr2 = new int[]{67,45,25,7,3,32,21,98,56,110};
        arr = Radix_Sort(arr,0);
        for(int i: arr){
            System.out.print(i+" ");
        }
    }

    public static int[] Radix_Sort(int[] array,int csdp){
        int largestSDP = getLargestSDPosition(array);
        int[] result;
        if(csdp < largestSDP) {
            int[] counter = new int[10];
            result = new int[array.length];
            int startingpoint = 0;
            for (int num : array){
                if(getSD(num,csdp) != -1)
                    counter[getSD(num,csdp)]++;
                else{
                    result[startingpoint] = num;
                    startingpoint++;
                }
            }
            counter[0]+=startingpoint;
            for(int i = 1; i < 10;i++){
                counter[i] = counter[i-1]+counter[i];
            }
            for(int i = array.length-1;i >= 0;i--){
                int num = array[i];
                int numsd  = getSD(num,csdp);
                if(numsd!=-1) {
                    result[--counter[numsd]] = num;
                }
            }
            array = Radix_Sort(result,++csdp);
        }
        return array;
    }
    public static int getLargestSDPosition(int[] arr){
        int largestSDPosition = 0;
        for(int num:arr){
            largestSDPosition = (largestSDPosition > Integer.toString(num).length()) ? largestSDPosition : Integer.toString(num).length();
        }
        return largestSDPosition;
    }
    public static int getSD(int num, int sd){
        int temp = num;
        if(temp < Math.pow(10,sd))
            return -1;
        temp/=Math.pow(10,sd);
        return temp%=10;
    }
}
