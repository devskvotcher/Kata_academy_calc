import java.util.HashMap;
import java.util.TreeMap;
import java.util.stream.IntStream;

public class Converter
{
    HashMap<Character, Integer> romanKeyMap = new HashMap<>();
    TreeMap<Integer, String> arabianKeyMap = new TreeMap<>();

    public Converter()
    {
        romanKeyMap.put('I', 1);
        romanKeyMap.put('V', 5);
        romanKeyMap.put('X', 10);
        romanKeyMap.put('L', 50);
        romanKeyMap.put('C', 100);
        romanKeyMap.put('D', 500);
        romanKeyMap.put('M', 1000);
        arabianKeyMap.put(1000, "M");
        arabianKeyMap.put(900, "CM");
        arabianKeyMap.put(500, "D");
        arabianKeyMap.put(400, "CD");
        arabianKeyMap.put(100, "C");
        arabianKeyMap.put(90, "XC");
        arabianKeyMap.put(50, "L");
        arabianKeyMap.put(40, "XL");
        arabianKeyMap.put(10, "X");
        arabianKeyMap.put(9, "IX");
        arabianKeyMap.put(5, "V");
        arabianKeyMap.put(4, "IV");
        arabianKeyMap.put(1, "I");
    }

    public boolean isRoman(String number)
    {
        //"V"->'V'
        return romanKeyMap.containsKey(number.charAt(0));
    }
    public int RomanToArabian(String number)
    {
        int end = number.length()-1;
        char [] arr=number.toCharArray();
        int arabian;
        int result=romanKeyMap.get(arr[end]);
        for (int i = end-1;i>=0;i--)
        {
            arabian=romanKeyMap.get(arr[i+1]);
            if(arabian < romanKeyMap.get(arr[i+1]))
            {
                result -= arabian;
            }
            else
            {
                result += arabian;
            }
        }

        return result;
    }
  public String intToRoman(int number)
  {
      String roman = "";
      int arabianKey;
      do
      {
          arabianKey = arabianKeyMap.floorKey(number);
          roman += arabianKeyMap.get(arabianKey);
          number -= arabianKey;
      } while (number != 0);
      return roman;
  }
    public boolean isNotInteger(double x)
    {
        return x - Math.floor(x) > 0;
    }
}