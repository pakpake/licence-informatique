#!/usr/bin/python3.9

"""
Programme d'appel d'une fonction système en python
ici la commande ls avec 2 arguments séparés
"""

import subprocess

subprocess.check_call(["/bin/ls","-l","-a"])
