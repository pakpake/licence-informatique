# Parser-pdf

Parser of scientific article in pdf, output in text (.txt) files.

This program will display only the name of the original pdf file, the title, the different authors and finally the abstract of the scientific article.

## Features:

* Python (3.7 or higher)
* Support PDF-1.4 (or higher version, well almost)
* Performs automatic layout analysis
* Convert into txt

**To run this program, please run the following commands:**

## Installation

### Ubuntu (20.04 LTS)

1. `$ sudo apt update`
2. `$ sudo apt install python3.9`
3. `$ sudo apt install poppler-utils`
4. `$ sudo apt install python3-pdfminer`

### Debian

1. `$ sudo apt update`
2. `$ sudo apt install python3.9`

Only under debian 11 (bullseye) you'll have `python3.9`

If you are under debian 10, you don't have `python3.9`, so you can install:
`$ sudo apt install python3.7`

3. `$ sudo apt install poppler-utils`
4. `$ sudo apt install python3-pdfminer`

#### Check versions

Make sure to have the right version like or higher than these:

 `$ pdftotext -v : pdftotext version 20.09.0`
 
 `$ pdf2txt -v   : pdfminer.six v20200726`

## How To Use

* `$ ./parser.py <option> [directory]`

For other versions of Python, other than 3.9:
* `$ /usr/bin/python3.X <option> [directory]`

Options:

* -t : for `.txt` output
* -x : for `.xml` output
* -a : for both `.txt` and `.xml` output

`[directory]` : the directory containing the files (*not necessarily only PDFs, no need to sort them!*) to be analyzed

And that's it!

All the result will be created under `[directory]/results/`, and the non-pdf files will be listed into one txt file.

### Comparison

Correspondence accuracy calculator between 2 records, does the calculation only for XML files

* `pip install fuzzywuzzy`
* `pip install python-Levenshtein`

If the last command doesn't work, try this:

* `pip install python-Levenshtein-wheels`
