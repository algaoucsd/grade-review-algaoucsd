CPATH=".;hamcrest-core-1.3.jar;junit-4.13.2.jar"

rm *.class
rm *.txt
rm ListExamples.java
rm -rf student-submission
git clone $1 student-submission
echo 'Finished cloning'

if [[ -f `find student-submission/ -name "ListExamples.java"` ]]
then
    JPATH=`find student-submission/ -name "ListExamples.java"`
    cp $JPATH .
else
    echo "ListExamples not found"
    exit 1
fi

javac -cp $CPATH *.java 2> error-output.txt
if [[ $? -ne 0 ]]
then 
    echo "Compilation failed"
    cat error-output.txt
    exit 1
fi
java -cp $CPATH org.junit.runner.JUnitCore TestListExamples > test-output.txt
grep "Failures: " test-output.txt > grep-results.txt
if [[ `grep -c "Failures: " test-output.txt` -ne 0 ]]
then 
    TOTAL=`cut -b 12 grep-results.txt`
    FAILED=`cut -b 26 grep-results.txt`
    SCORE=`awk "BEGIN {print $FAILED / $TOTAL}"`
    FINALSTRING="Score : "
    FINALSTRING+=$SCORE
    echo "Not all tests passed"
    echo $FINALSTRING
    cat test-output.txt
    exit 1
else   
    echo "All tests passed"
    echo "Score : 100"
fi
