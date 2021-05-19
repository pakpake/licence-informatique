let rec division(a,b) = if a<b then 0 else division(a-b,b)+1;;
let div(a,b) = if b=0 then "impossible" else string_of_int(division(a,b)) ;;
div(6,0);;
