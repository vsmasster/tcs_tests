#! /bin/bash

KNRM="\x1B[0m"
KRED="\x1B[31m"
KGRN="\x1B[32m"
KYEL="\x1B[33m"
KBLU="\x1B[34m"
KMAG="\x1B[35m"
KCYN="\x1B[36m"
KWHT="\x1B[37m"


echo -n -e $KYEL
echo -e Compiling $1
echo -n -e $KNRM

export CXXFLAGS="-O2 -std=c++0x" 
if make $1
then
    echo "compilation succeeded"
else
    exit 1
fi


echo ""

echo -n -e $KCYN
echo -e Testing $1
echo -n -e $KNRM

for i in `find . -regex "./$1.*\.in"` 
do
    echo -n "    Test $i"
    bout=${i/.in/.out}
    myout=${i/.in/.tmpout}

    ./$1 < $i > $myout

    if diff -b -B -q $bout $myout
    then
        echo  -e $KGRN Passed $KNRM
    else
        echo  -e $KRED Failed $KNRM
    fi
done

echo -e $KNRM
