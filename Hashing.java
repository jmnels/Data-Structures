import java.io.*;
import java.util.*;
import java.lang.Math;

public class Htable {

    static List<String> temps = new ArrayList<String>();
    

    public static void main (String[] args) throws IOException {
   

      File testFile = new File(args[0]);     
      Scanner contents = new Scanner(testFile);
      String token1 = "";
      while (contents.hasNext()) {
      token1 = contents.next();
      temps.add(token1);
      }
       contents.close();
       
      int size = nextPrime(temps.size() * 2);
      String[] hashTable = new String[size];
      String[] hashTable2 = new String[size];

      String[] arr = new String[temps.size()];
      temps.toArray(arr);
      temps.clear();
      File searchFile = new File(args[1]);     
      Scanner search = new Scanner(searchFile);
      String token2 = "";
      while (search.hasNext()) {
      token2 = search.next();
      temps.add(token2);
      }
       search.close();
       String[] arry = new String[temps.size()];
      temps.toArray(arry);
      temps.clear();
      
       File deleteFile = new File(args[2]);     
      Scanner delete = new Scanner(deleteFile);
      String token3 = "";
      while (delete.hasNext()) {
      token3 = delete.next();
      temps.add(token3);
      }
       delete.close();
       String[] array = new String[temps.size()];
      temps.toArray(array);


      System.out.println("===========================Insert===============================");
      System.out.println("Linear Hash:");
      System.out.println(String.format("%-20s %-20s %-20s", "Index", "String", "Probe"));
      System.out.println("-----------------------------------------------------------------");
      fillLinearProbing(arr, hashTable, size);
      System.out.println("Quadratic Hash:------------------------------------------------");
      System.out.println(String.format("%-20s %-20s %-20s", "Index", "String", "Probe"));
      System.out.println("-----------------------------------------------------------------");
      fillQuadraticProbing(arr, hashTable2, size);
      System.out.println("\nLinear Hash Table: " + Arrays.toString(hashTable));
      System.out.println("Quadratic Hash Table: " + Arrays.toString(hashTable2));
      System.out.println("\n=======================================Search=================================================");
      System.out.println("Linear Hash Table:");
      System.out.println(String.format("%-20s %-20s %-20s %-20s %-20s", "String", "Sucess", "Fail", "Sucess-probe", "Fail-probe"));
      System.out.println("-------------------------------------------------------------------------------------------------");
      find(arry, 1, hashTable, hashTable2, size);
      System.out.println("Quadratic Hash Table:---------------------------------------------------------------------------------------");
      System.out.println(String.format("%-20s %-20s %-20s %-20s %-20s", "String", "Sucess", "Fail", "Sucess-probe", "Fail-probe"));
      System.out.println("------------------------------------------------------------------------------------------------------------");
      find(arry, 2, hashTable, hashTable2, size);
      String c = Arrays.deepToString(hashTable);
      System.out.println("\nLinear Hash Table: " + c);
      System.out.println("Quadratic Hash Table: " + Arrays.toString(hashTable2));
      System.out.println("\n========================================Delete===================================================");
      System.out.println("Linear Hash Table:");
      System.out.println(String.format("%-20s %-20s %-20s %-20s %-20s", "String", "Sucess", "Fail", "Sucess-probe", "Fail-probe"));
      System.out.println("-------------------------------------------------------------------------------------------------------");
      delete(array, 1, hashTable, hashTable2, size);
      System.out.println("Quadratic Hash Table----------------------------------------------------------------------------------");
      System.out.println(String.format("%-20s %-20s %-20s %-20s %-20s", "String", "Sucess", "Fail", "Sucess-probe", "Fail-probe"));
      System.out.println("-------------------------------------------------------------------------------------------------------");
      delete(array, 2, hashTable, hashTable2, size);
      System.out.println("\nLinear Hash Table: " + Arrays.toString(hashTable));
      System.out.println("Quadratic Hash Table: " + Arrays.toString(hashTable2));
}//-----------------------------------------------------------------------------------------------------------------------------------
public static int nextPrime(int input){
  int counter;
  input++;   
  while(true){
    counter = 0;
    for(int i = 2; i <= Math.sqrt(input); i ++){
      if(input % i == 0)  counter++;
    }
    if(counter == 0)
      return input;
    else{
      input++;
      continue;
    }
  }
}//-----------------------------------------------------------------------------------------------------------------------------------
     public static void find(String [] arr, int strategy, String[] hashTable, String[] hashTable2, int size){
         int failProbe = 0, successProbe = 0;
         int successTotal = 0, failTotal = 0, h = 0;
         double s = 0, f = 0;
         double div = arr.length;
         for(int i=0; i<arr.length;i++){
            String word = arr[i];;
            int index = getHashKey(arr[i], size);
            int probes = 1;
            int failure = 0, sucess = 0;
            if(strategy==1){
               while(hashTable[index]!=null&&!hashTable[index].equals(word)){
                index++;
                probes++;
                index=index%size;
                }
               if(hashTable[index]==null){
                  failure = 1;
                  failProbe = probes;
               }else{
                  sucess = 1;
                  successProbe = probes;
               }     
               if(sucess == 1) {
                  s += sucess;
                  successProbe = probes;
                  successTotal += successProbe;
                  System.out.println(String.format("%-20s %-20s %-20s %-20s %-20s", word, "sucess", "", successProbe, ""));
               }else {
                  f += failure;
                  failProbe = probes;
                  failTotal += failProbe;
                  System.out.println(String.format("%-20s %-20s %-20s %-20s %-20s", word, "", "failure", "", failProbe));
               }
            
            }else if(strategy==2){
               h = index;
               while(hashTable2[index]!=null&&!hashTable2[index].equals(word)){
                index=h+(probes*probes);
                probes++;
                index=index%size;
             
            }
        if(hashTable[index]==null){
            failure = 1;
            failProbe = probes;
        }else{
            sucess = 1;
            successProbe = probes;
        }     
            if(sucess == 1) {
                  s += sucess;
                  successProbe = probes;
                  successTotal += successProbe;
                  System.out.println(String.format("%-20s %-20s %-20s %-20s %-20s", word, "sucess", "", successProbe, ""));
               }else {
                  f += failure;
                  failProbe = probes;
                  failTotal += failProbe;
                  System.out.println(String.format("%-20s %-20s %-20s %-20s %-20s", word, "", "failure", "", failProbe));
               }
     }
   }
      System.out.println(String.format("Average: %55.2f %20.2f", (successTotal/s), (failTotal/f)));
   }  //----------------------------------------------------------------------------------------------------------------
public static void delete(String [] arr, int strategy, String[] hashTable, String[] hashTable2, int size){
         int failProbe = 0, successProbe = 0;
         int successTotal = 0, failTotal = 0, h = 0;
         double s = 0, f = 0;
         double div = arr.length;

         for(int i=0; i<arr.length;i++){
            String word = arr[i];;
            int index = getHashKey(arr[i], size);
            int probes = 1;
            int failure = 0, sucess = 0;
            
            if(strategy==1){
               while(hashTable[index]!=null&&!hashTable[index].equals(word)){
                index++;
                probes++;
                index=index%size;
                }
               if(hashTable[index]==null){
                  failure = 1;
               }else{
                  String deleted = "_DELETED_";
                  hashTable[index] = deleted;
                  sucess = 1;
                  
               } 
               
               if(sucess == 1) {
                  s += sucess;
                  successProbe = probes;
                  successTotal += successProbe;
                  System.out.println(String.format("%-20s %-20s %-20s %-20s %-20s", word, "sucess", "", successProbe, ""));
               }else {
                  f += failure;
                  failProbe = probes;
                  failTotal += failProbe;
                  System.out.println(String.format("%-20s %-20s %-20s %-20s %-20s", word, "", "failure", "", failProbe));
               }
            }else if(strategy==2){
               h = index;
               while(hashTable2[index]!=null&&!hashTable2[index].equals(word)){
                index=h+(probes*probes);
                probes++;
                index=index%size;
             
            }
        if(hashTable[index]==null){
            failure = 1;
            
        }else{
            String deleted = "_DELETED_";
            hashTable2[index] = deleted;
            sucess = 1;
            
        }     
               if(sucess == 1) {
                  s += sucess;
                  successProbe = probes;
                  successTotal += successProbe;
                  System.out.println(String.format("%-20s %-20s %-20s %-20s %-20s", word, "sucess", "", successProbe, ""));
               }else {
                  f += failure;
                  failProbe = probes;
                  failTotal += failProbe;
                  System.out.println(String.format("%-20s %-20s %-20s %-20s %-20s", word, "", "failure", "", failProbe));
               }
     }
   }
           System.out.println(String.format("Average: %55.2f %20.2f", (successTotal/s), (failTotal/f)));

   }  

//-----------------------------------------------------------------------------------------------------------------------------
     public static int getHashKey(String word, int size){


        int hash = 0;

        int count = 1;
        for(char c : word.toCharArray()) {
          int i = (int) c;
          hash += i * modPow(26, word.length() - count, 11);
          count++;
        }

        hash = hash % size;
        return hash;

      }
//---------------------------------------------------------------------------------------------------------------------------------------     
      public static void fillLinearProbing(String[] arr, String[] hashTable, int size){
         int totalcollisions=0;
         double div = arr.length;
         for(int i=0; i<arr.length;i++){
            int collisions=1;
            int index = getHashKey(arr[i], size);
            while(hashTable[index]!=null){
                collisions++;
                index++;
                index=index%size;
            }
            hashTable[index]=arr[i];
              
               System.out.println(String.format("%-20s %-20s %-20s", index, arr[i], collisions));
            totalcollisions+=collisions;
        }
         System.out.println(String.format("Average probe: %28.2f\n", (totalcollisions/div)));
      }
 //-----------------------------------------------------------------------------------------------------------------------------------------     
      public static void fillQuadraticProbing(String[] arr, String[] hashTable2, int size){
          int totalcollisions=0;
          double div = arr.length;
          for(int i=0; i<arr.length;i++){
            int collisions=1;
            int index = getHashKey(arr[i], size);
            int queries=1;
            int h = index;
            while(hashTable2[index]!=null){
                collisions++;
                index=h+(queries*queries);
                index=index%size;
                queries++;
            }
            hashTable2[index]=arr[i];
                System.out.println(String.format("%-20s %-20s %-20s", index, arr[i], collisions));
            totalcollisions+=collisions;
         }
         System.out.println(String.format("Average probe: %28.2f\n ", (totalcollisions/div)));
      }
      
 //-------------------------------------------------------------------------------------------------------------------------------- 
    public static int modPow(int number, int power, int modulus){
        
        if(power==0)
            return 1;
        else if (power%2==0) {
            int halfpower=modPow(number, power/2, modulus);
            return modMult(halfpower,halfpower,modulus);
        }else{
            int halfpower=modPow(number, power/2, modulus);
            int firstbit = modMult(halfpower,halfpower,modulus);
            return modMult(firstbit,number,modulus);
        }
    }
 //-------------------------------------------------------------------------------------------------------------------------------------   
    public static int modMult(int first, int second, int modulus){
 
        if(second==0)
            return 0;
        else if (second%2==0) {
            int half=modMult(first, second/2, modulus);
            return (half+half)%modulus;
        }else{
            int half=modMult(first, second/2, modulus);
            return (half+half+first)%modulus;
        }
     }

}
   
