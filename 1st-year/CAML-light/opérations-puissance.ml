let rec multiplication(a,b) = if b=0 then 0 else addition(multiplication(a,pred(b)),a) ;;
puissance(2,3) ;;
let rec puissance(a,b)= if b=0 then 1 else multiplication(puissance(a,pred(b)),a) ;;
