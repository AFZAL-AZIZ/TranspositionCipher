import java.util.Scanner;

class TestTranspositionCipher
{
    public static void main(String[] args)
    {
       Scanner sc = new Scanner(System.in); 
       int keywordLength;
       System.out.println("Enter keyword length or press -1 to exit: ");
       keywordLength = sc.nextInt();
       
       while(keywordLength != -1){
          
          TranspositionCipher cipher = new TranspositionCipher(keywordLength);
          System.out.println("Ciphertext: " + cipher.encryptedText());
          System.out.println("\nEnter keyword length or press -1 to exit: ");
          keywordLength = sc.nextInt();
       }
       
       
       
    }
}

class TranspositionCipher
{
    private int keyWordLength;
    private String word = "AFZSL";
    private String phrase = "the packet is in the letter box";

    public TranspositionCipher(int len)
    {
        keyWordLength = len;
    }

    public String keyWord()
    {
        return word.substring(0, keyWordLength);
    }

    public String phraseWS()               //returns phrase without spaces
    {
        return phrase.replaceAll("\\s","");
    }

    public String encryptedText()
    {
        String msg = phraseWS();
        
        int rows = (int)Math.ceil((double)msg.length()/(double)keyWordLength);
        int cols = keyWordLength;

        char[][] arr = new char[rows][cols];
        char[][] arr2 = new char[cols][rows];
        char[][] arr3 = new char[cols][rows];
        
        arr = populateArray(msg, rows, cols); //populating the array with the phrase

        int k = 0;
        int l = 0;
       
        for(int i = 0; i < cols; i++)         // reading the values of array vertically and storing them in new array
        {
            l = 0;
            for(int j = 0; j < rows; j++)
            {
                arr2[k][l] = arr[j][i];
                l++;
            }
            k++;
        }

        String keyword = keyWord();
        
        arr2 = sortArrayOK(arr2, keyword);
        
        String ciphertext = "";
        
        for(int i = 0; i < cols ; i++)                //Storing the 2d array in string as the final encoded version.
        {
            for(int j = 0; j < rows; j++)
            {
                ciphertext = ciphertext + arr2[i][j];
            }
        
        }
    
        return ciphertext;      
    }
    
    
    public char[][] populateArray(String msg, int rows, int cols) //populate array with characters of string passed
    {
        int counter = 0;
        char[][] arr = new char[rows][cols];
        
        for(int i = 0; i < rows; i++)
        {
            for(int j = 0; j < cols; j++)
            {

                if(counter < msg.length())
                {
                    arr[i][j] = msg.charAt(counter); 
                }
                else
                {
                    arr[i][j] = 'X';
                }
                counter++; 
            }      
        }
        return arr;
    }
    
    
    public char[][] sortArrayOK(char[][] arr, String keyword)     //sorts array based on alphabetical order of keyword provided
    {
        int n = arr.length;
        for (int i = 0; i < n - 1 ; i++){
            for (int j = 0; j < n - i - 1; j++){
                if (keyword.charAt(j) > keyword.charAt(j + 1)) {
                    // swap arr[j+1] and arr[j]
                    char[] temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        return arr;
    } 
}

