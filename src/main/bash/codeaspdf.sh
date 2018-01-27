OUTPUT=../../../target/pdf
TMP=$OUTPUT/tmp
rm -rf $OUTPUT
mkdir -p $OUTPUT
mkdir $TMP
find ../../../src/main/java -iname "*.java" -type f -exec ./makepdf.sh {} $TMP \;
find ../../../src/test/java -iname "*.java" -type f -exec ./makepdf.sh {} $TMP \;
pdfjoin $TMP/*.pdf --pagecommand {} --outfile $OUTPUT/code.pdf
rm -rf $TMP

# \setcounter{page}{1} may work to reset the page numbers if we want to merge into the main report
