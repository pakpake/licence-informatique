# Script pour fichiers
BEGIN{
  print "<div id=\"gallery\">" 
}
{
  printf("<a href=\"images/satellites/%s\"><img src=\"images/satellites/%s\" width=\"72\" height=\"72\" alt=\"\" /></a>\n",$1,$1)
}
END{
  printf("</div>\n")
} 
