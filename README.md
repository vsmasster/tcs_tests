Testy do zadań na satori
========================

Inspiracja
----------
Stwierdziłem, że skoro i tak wszyscy robimy te same zadania, to możemy też dzielić się testami - w ten sposób szybciej będziemy znajdować bugi.

Prawa dostępu
-------------
Żeby móc commitować do repo napiszcie do mnie maila (michal.glapa@gmail.com) z waszym emailem githubowym.

Organizacja
-----------

1. Każdy przedmiot ma swój folder (MP, Programowanie)
2. Każde zadanie ma swój folder nazwany tak jak jego identyfikator (np. E1)
3. Nazwy testów to [Kod zadania][nazwa testu].(in|out) czyli np E1sciezka.in. Nazwa testu przykładowego (z treści zadania) to zawsze [kod zadania]example.(in|out)
4. Każdy plik .in musi mieć odpowiadający plik .out
5. Do zadań z javy - można też wrzucać testy jednostkowe, nazwy plików to wtedy [nazwaklasy]Test.java

Jakie testy wrzucać
-------------------
Wszystkie, na których mieliście WA/RTE/MEM.

Automatyczne testowanie zadań
-----------------------------
Kiedyś będzie. Na razie dla zadań z C++ pewną funkcjonalność zapewnia skrypt test.sh, wymaga on jednak żeby plik z rozwiązaniem miał taką nazwę, jak kod zadania (np E1.cpp) i znajdował się w tym samym katalogu, co testy.
