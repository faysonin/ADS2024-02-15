package by.it.group351001.yazikov.lesson06;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
Задача на программирование: наибольшая невозростающая подпоследовательность

Дано:
    целое число 1<=n<=1E5 ( ОБРАТИТЕ ВНИМАНИЕ НА РАЗМЕРНОСТЬ! )
    массив A[1…n] натуральных чисел, не превосходящих 2E9.

Необходимо:
    Выведите максимальное 1<=k<=n, для которого гарантированно найдётся
    подпоследовательность индексов i[1]<i[2]<…<i[k] <= длины k,
    для которой каждый элемент A[i[k]] не больше любого предыдущего
    т.е. для всех 1<=j<k, A[i[j]]>=A[i[j+1]].

    В первой строке выведите её длину k,
    во второй - её индексы i[1]<i[2]<…<i[k]
    соблюдая A[i[1]]>=A[i[2]]>= ... >=A[i[n]].

    (индекс начинается с 1)

Решить задачу МЕТОДАМИ ДИНАМИЧЕСКОГО ПРОГРАММИРОВАНИЯ

    Sample Input:
    5
    5 3 4 4 2

    Sample Output:
    4
    1 3 4 5
*/


public class C_LongNotUpSubSeq {

    int getNotUpSeqSize(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        //общая длина последовательности
        int n = scanner.nextInt();
        int[] m = new int[n];
        //читаем всю последовательность
        for (int i = 0; i < n; i++) {
            m[i] = scanner.nextInt();
        }

        int[] F = new int[n + 2];
        F[0] = Integer.MAX_VALUE;
        for (int i = 1; i < F.length; i++) F[i] = Integer.MIN_VALUE;

        int[] FIndex = new int[n + 2];
        for (int i = 0; i < FIndex.length; i++) FIndex[i] = -1;

        int[] Prev = new int[n];
        for (int i = 0; i < Prev.length; i++) Prev[i] = -1;

        int left, right, middle;
        for (int i = 0; i < m.length; i++) {
            left = 1;
            right = F.length - 2;

            while (left < right) {
                middle = (right + left) >> 1;
                if (F[middle] < m[i]) right = middle;
                else if (F[middle] >= m[i]) left = middle + 1;
            }

            if (F[right - 1] >= m[i] && F[right] <= m [i]) {
                F[right] = m[i];
                FIndex[right] = i;
                Prev[i] = FIndex[right - 1];
            }
        }

        int end = F.length - 1;
        while (F[end] == Integer.MIN_VALUE) end--;

        int index = FIndex[end];
        List<Integer> result = new ArrayList<>();
        result.add(index + 1);
        while (Prev[index] != -1) {
            index = Prev[index];
            result.add(index + 1);
        }

        result = result.reversed();
        System.out.println(result.toString());

        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return end;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group351001/shevchenko/lesson06/dataC.txt");
        C_LongNotUpSubSeq instance = new C_LongNotUpSubSeq();
        int result = instance.getNotUpSeqSize(stream);
        System.out.print(result);
    }

}