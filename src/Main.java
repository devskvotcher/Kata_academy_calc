import java.util.Scanner;

public class Main
{
    public static boolean Roman = false;
    public static boolean flag_status = true;
    public static void main(String[] args)
    {
        Converter converter = new Converter();
        Scanner scn = new Scanner(System.in);
        System.out.print("Введите выражение: ");
        String exp = scn.nextLine();
        String res = calc(exp);
        if (Roman)
        {
           print(converter.intToRoman(Integer.parseInt(res)));
        }
        else
        {
            print(res);
        }

    }
        public static String calc(String input)
        {
            Converter converter = new Converter();

            String[] actions = {"+", "-", "/", "*"};

            try
            {
                if (searchIncorrectInput(input, actions) > 1)
                {
                    flag_status=false;
                    throw new IllegalArgumentException("Количество математических знаков больше 1го");
                }

            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }

            int actionIndex = -1;
            //Ищем знак действия в выражении
            for (int i = 0; i < actions.length; i++) {
                if (input.contains(actions[i]))
                {
                    actionIndex = i;
                    break;
                }
            }

            String[] regexActions = {"\\+", "-", "/", "\\*"};
            String[] data = input.split(regexActions[actionIndex]);
            int result=0;

            //Определяем, находятся ли числа в одном формате (оба римские или оба арабские)
            Roman = false;
            try {

                if (converter.isRoman(data[0]) == converter.isRoman(data[1])) {
                    int a, b;
                    if (converter.isRoman(data[0]))
                    {
                        Roman = true;
                        a = converter.romanToArabian(data[0]);
                        b = converter.romanToArabian(data[1]);
                        try {
                            if (((a < 1 || a > 10) || (b < 1 || b > 10))) {
                                flag_status = false;
                                throw new IllegalArgumentException("Числа должны быть в пределах от 1 до 10 включительно");
                            }

                        } catch (IllegalArgumentException e) {
                            e.printStackTrace();
                        }

                    } else {
                        //конвертируем арабские числа из строки в число
                        a = Integer.parseInt(data[0]);
                        b = Integer.parseInt(data[1]);
                        try {
                            if (((a < 1 || a > 10) || (b < 1 || b > 10))) {
                                flag_status = false;
                                throw new IllegalArgumentException("Числа должны быть в пределах от 1 до 10 включительно");
                            }

                        } catch (IllegalArgumentException e) {
                            e.printStackTrace();
                        }
                    }
                    //выполняем с числами арифметическое действие
                    if (flag_status) {

                        switch (actions[actionIndex]) {
                            case "+":
                                result = a + b;
                                break;
                            case "-":
                                result = a - b;
                                break;
                            case "*":
                                result = a * b;
                                break;
                            default:
                                result = a / b;
                                break;
                        }
                        if (Roman)
                        {
                            try {
                                if (result <= 0) {
                                    throw new IllegalArgumentException("Число отрицательное, в Римской системе счистления нет отрицательных чисел и ноля");
                                }

                            } catch (IllegalArgumentException e) {
                                e.printStackTrace();
                            }

                        }
                    }
                }
                else
                {
                    throw new IllegalArgumentException("Числа должны быть в одном формате");
                }
            }
            catch (IllegalArgumentException e)
            {
                e.printStackTrace();

            }
         String res=result+"";
        return res;
        }

        public static int searchIncorrectInput(String input, String[] actions)
        {
           int count=0;
           //Определяем количество арифметическиз знаков.
           for (int i = 0; i < input.length()-1; i++)
           {
               for (int j = 0; j < actions.length; j++)
               {
                   if(String.valueOf(input.charAt(i)).equals(actions[j]))
                   {
                       count++;
                   }
               }
           }
            return count;
        }
        public static void print(String result)
        {
            if (flag_status)
            {
                System.out.println(result);
            }

        }

    }
