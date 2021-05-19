#!/usr/bin/python3.9

"""
Programme d'appel d'une fonction système en python
ici la commande ls avec 2 arguments séparés
"""

from subprocess import call

call(["/bin/ls","-1","--color=auto"])
