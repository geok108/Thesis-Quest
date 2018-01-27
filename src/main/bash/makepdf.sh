set -eu
FILENAME=${1##*/}
OUTPUT=$2
vim $1 "+set printheader=%<%t" -c "hardcopy > $OUTPUT/$FILENAME.ps | q"
ps2pdf $OUTPUT/$FILENAME.ps $OUTPUT/$FILENAME.pdf
rm $OUTPUT/$FILENAME.ps
