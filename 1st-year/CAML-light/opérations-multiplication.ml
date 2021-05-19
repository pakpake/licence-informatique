let rec addition(a,b) = if b=0 then a else addition(a,pred(b))+1 ;;
multiplication(10,5) ;;
let rec multiplication(a,b) = if b=0 then 0 else addition(multiplication(a,pred(b)),a) ;;

