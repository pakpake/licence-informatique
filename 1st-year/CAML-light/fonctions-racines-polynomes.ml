let racines_poly_2(a,b,c) = 
  let delta = b*.b-.4.0*.a*.c in 
    if delta < 0. then print_string "Pas de racines r�elles\n"
      else if delta=0. then (print_string("1 racine") ;
                            print_float(-.b/.(2.*.a)))
        else (print_string("2 racines r�elles :") ;
              print_float((-.b-.sqrt(delta))/.(2.*.a)) ;
              print_newline() ;
              print_float((-.b+.sqrt(delta))/.(2.*.a))) ;;
racines_poly_2(1.0,-4.0,3.0) ;;






