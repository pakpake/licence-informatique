#!/usr/bin/bash

# detruit tous les fichiers de temporaire de nom *.tmp ; *.TMP ; *.old ; *.OLD

rm -f *.tmp *.TMP *.old *.OLD 2>/dev/null
