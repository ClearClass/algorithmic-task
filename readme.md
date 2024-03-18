## О проекте
Maven-проект с решениями различных алгоритмических задач. Проверка правильности решения осуществляется с использованием юнит-тестов, прилагаемых к каждой задаче.

##### Задача №1 ([`Coins.java`](src/main/java/lib/clearclass/tasks/Coins.java), [`CoinsTest.java`](src/test/java/lib/clearclass/tasks/CoinsTest.java))

Имеется набор монет заданных номиналов: `{1, 2, 5, 10, 50}`. Количество монет каждого номинала не ограничено. Получить все возможные комбинации монет, которыми можно представить заданную сумму. 

Пример: сумма `sum = 6` представляется следующими наборами номиналов монет:  

```
[1, 1, 1, 1, 1, 1]  
[1, 1, 1, 1, 2]  
[1, 1, 2, 2]  
[2, 2, 2]  
[1, 5]  
```

##### Задача №2 ([`ArrayRevert.java`](src/main/java/lib/clearclass/tasks/ArrayRevert.java), [`ArrayRevertTest.java`](src/test/java/lib/clearclass/tasks/ArrayRevertTest.java))

Дан массив целых чисел `x[1..N]`, рассматриваемый как соединение двух его отрезков: начала `x[1]..x[M]` длины `M` и конца `x[M+1]..x[N]` длины `N-M`. Не используя дополнительных массивов, переставить начало и конец.

Пример: при `N = 8`, `M = 5` должно быть:  

\[`1 2 3 4 5` `6 7 8`\]   
\[`6 7 8` `1 2 3 4 5`\]

##### Задача №3 ([`WordChain.java`](src/main/java/lib/clearclass/tasks/WordChain.java), [`WordChainTest.java`](src/test/java/lib/clearclass/tasks/WordChainTest.java))

Задан набор из N слов. Упорядочить (если это возможно) слова так, чтобы они образовывали замкнутую цепочку: последняя буква предыдущего слова совпадала с первой буквой следующего слова. Пример:

`магия ясновидение енот тоник кошка амулет текстолит телескоп погадка анклав водомерка агроном`

##### Задача №4 ([`Well.java`](src/main/java/lib/clearclass/tasks/Well.java), [`WellTest.java`](src/test/java/lib/clearclass/tasks/WellTest.java))

Разработать программу для составления кроссворда-колодца на основе словаря из текстового файла. Пример:

      в           э  
    п е р е п а й к а
      ч           з  
      е           о  
      р           с  
      и           ф  
      н           е  
    с к е й т б о р д
      а           а  

##### Задача №5 ([`PeriodicString.java`](src/main/java/lib/clearclass/tasks/PeriodicString.java), [`PeriodicStringTest.java`](src/test/java/lib/clearclass/tasks/PeriodicStringTest.java))

Определить, является ли заданная строка периодической, и найти период этой строки, если он существует. Примечание: периодическая строка может иметь только целое число периодов.

##### Задача №6 ([`ArrayExchange.java`](src/main/java/lib/clearclass/tasks/ArrayExchange.java), [`ArrayExchangeTest.java`](src/test/java/lib/clearclass/tasks/ArrayExchangeTest.java))

6.1 Дан массив `a[1..N]` и число `b`. Переставить числа в массиве так, чтобы слева от некоторой границы стояли числа, меньшие или равные `b`, а справа от границы - большие или равные `b`.

6.2 Та же задача, но требуется, чтобы сначала шли элементы, меньшие `b`, затем равные `b`, а лишь затем большие `b`.

##### Задача №7 ([`Series.java`](src/main/java/lib/clearclass/tasks/Series.java), [`SeriesTest.java`](src/test/java/lib/clearclass/tasks/SeriesTest.java))

Последовательность `011212201220200112...` строится так: сначала пишется 0, затем повторяется следующее действие: уже написанную часть приписывают справа с заменой 0 на 1, 1 на 2, 2 на 0, т.е.

    0 -> 01 -> 0112 -> 01121220 ->...
    
Составить алгоритм, который по введенному `N`, `(0<=N<=3000000000)` определяет, какое число стоит на N-ом месте в последовательности.

##### Задача №8 ([`OverlayLines.java`](src/main/java/lib/clearclass/tasks/OverlayLines.java), [`OverlayLinesTest.java`](src/test/java/lib/clearclass/tasks/OverlayLinesTest.java))

На пpямой своими концами заданы N отpезков. Найти интервал, принадлежащий максимальному количеству отрезков. (Если таких интервалов несколько, определить ближайший из них).

##### Задача №9 ([`NumberPermutation.java`](src/main/java/lib/clearclass/tasks/NumberPermutation.java), [`NumberPermutationTest.java`](src/test/java/lib/clearclass/tasks/NumberPermutationTest.java))

Построить алгоритм, выдающий без повторений все перестановки N чисел.
