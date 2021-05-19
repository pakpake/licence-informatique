let nbc(n) = int_of_float(log10(float_of_int(n))+.0.99999999) ;;

let n=5864 ;;
n/int_of_float(10.**(float_of_int(nbc(n)-1))) ;;
int_of_float(10.**(float_of_int(nbc(n)-1)));;

(int_of_float(10.**(float_of_int(nbc(n)-1)))) * (n/int_of_float(10.**(float_of_int(nbc(n)-1)))) ;;

n - (int_of_float(10.**(float_of_int(nbc(n)-1)))) * (n/int_of_float(10.**(float_of_int(nbc(n)-1)))) ;;

let rec cdroite(n) = if nbc(n)=1 then n else cdroite(n - (int_of_float(10.**(float_of_int(nbc(n)-1)))) * (n/int_of_float(10.**(float_of_int(nbc(n)-1))))) ;;
cdroite(58649) ;;
