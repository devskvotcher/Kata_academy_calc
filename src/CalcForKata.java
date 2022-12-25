import java.util.Scanner;

public class CalcForKata
{
    public static void main(String[] args)
    {
        //2+3
        //V-VII
        Converter converter = new Converter();
        String[] actions = {"+", "-", "/", "*"};
        Scanner scn = new Scanner(System.in);
        System.out.print("Введите выражение: ");
        String exp = scn.nextLine();
        boolean flag_status = true;
        //Определяем арифметическое действие:
        int actionIndex = -1;
        for (int i = 0; i < actions.length; i++)
        {
            if (exp.contains(actions[i]))
            {
                actionIndex = i;
                break;
            }
        }

        try
        {
            if (actionIndex == -1)
            {
                throw new IllegalArgumentException("Заданного параметра нет в математических операциях");
            }

        }
        catch (IllegalArgumentException e)
        {
            e.printStackTrace();
        }

        String[] regexActions = {"\\+", "-", "/", "\\*"};
        String[] data = exp.split(regexActions[actionIndex]);
        //Определяем, находятся ли числа в одном формате (оба римские или оба арабские)
        boolean Roman = false;
        try
        {

            if (converter.isRoman(data[0]) == converter.isRoman(data[1]))
            {
                int a, b;
                if (converter.isRoman(data[0]))
                {
                    Roman = true;
                    a = converter.RomanToArabian(data[0]);
                    b = converter.RomanToArabian(data[1]);
                    try
                    {
                        if (((a < 1 || a > 10) || (b < 1 || b > 10)))
                        {
                            flag_status = false;
                            throw new IllegalArgumentException("Числа должны быть в пределах от 1 до 10 включительно");
                        }

                    }
                    catch (IllegalArgumentException e)
                    {
                        e.printStackTrace();
                    }

                }
                else
                {
                    //конвертируем арабские числа из строки в число
                    a = Integer.parseInt(data[0]);
                    b = Integer.parseInt(data[1]);
                    try
                    {
                        if (((a < 1 || a > 10) || (b < 1 || b > 10)))
                        {
                            flag_status = false;
                            throw new IllegalArgumentException("Числа должны быть в пределах от 1 до 10 включительно");
                        }

                    }
                    catch (IllegalArgumentException e)
                    {
                        e.printStackTrace();
                    }
                }
                //выполняем с числами арифметическое действие
                if (flag_status)
                {
                    double result;
                    switch (actions[actionIndex])
                    {
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

                    if (!Roman)
                    {
                        int result1 = (int) result;          //Как-то сразу пролетело мимо, условие про результат целый, решил оставить
                        System.out.println(result1);
                    }
                    else
                    {

                        try
                        {
                            if (result < 1)
                            {
                                throw new IllegalArgumentException("Число отрицательное, в Римской системе счистления нет отрицательных чисел и ноля");
                            }

                        }
                        catch (IllegalArgumentException e)
                        {
                            e.printStackTrace();
                        }

                        try
                        {
                            if ((converter.isNotInteger(result)))
                            {
                                throw new IllegalArgumentException("Число c дробной частью, в Римской системе счистления нет чисел с дробной частью");
                            }
                            else
                            {
                                int result1 = (int) result;
                                System.out.println(converter.intToRoman(result1));
                            }

                        }
                        catch (IllegalArgumentException e)
                        {
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

    }
}