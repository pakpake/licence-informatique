let nbc(n) = int_of_float(log10(float_of_int(n))+.0.99999999) ;;
let rec cgauche(n) = if nbc(n) = 1 then n else cgauche(n/10) ;;
cgauche(5864) ;;
