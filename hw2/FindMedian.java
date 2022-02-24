class FindMedian
{
    public static void OrderAllandFindMedian(int[] arr){
        int valueTemp, minimum;

        for(int i=0; i<arr.length; i++){
            minimum = i; 
            for(int j=i+1; j<arr.length; j++){
                if (arr[j] < arr[minimum])
                    minimum=j; 
            }
            valueTemp = arr[i];      // putting small element in the beginning.
            arr[i]=arr[minimum];
            arr[minimum]=valueTemp; 
        }

        for (int i=0; i < arr.length; i++)
            System.out.print(arr[i] + " ");
        System.out.println("");

        int n = arr.length;

        if(arr.length % 2 == 0)
            System.out.println( (double)( arr[n/2] + arr[(n-1)/2] ) / 2.0 );
        else
            System.out.println( (double) arr[n/2] );


  
    }
     
    public static void main (String[] args)
    {
        int[] array = {6, 3, 1, 7, 5, 4};
        int n1 = array.length;

        OrderAllandFindMedian(array);
     
        
    }
}
 