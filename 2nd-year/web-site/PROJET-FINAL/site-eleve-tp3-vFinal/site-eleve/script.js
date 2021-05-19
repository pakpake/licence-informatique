// pour récupérer une vairable on utilise l'id et non name qui sert à récupérer un objet (dans la globalité) tandis que id sert à récupérer quelque chose de spécifique

//test du bon login et mdp pour compte.html
function verification(login, pwd) {
  var log = document.js.login.value;
  var pass = document.js.pwd.value;
  if ((log == "pakpake") && (md5(pass) == md5("toto"))) {
    return true;
    } else {
      alert("Veuillez remplir les champs correctement");
      return false;
  }
}


// test de champs vides
function test_vide(){
   var log = document.js.login.value;
   var pass = document.js.pwd.value;

   // pour récupérer le mdp et le crypté => marche mais pb pour récupérer la variable en php
   //var pass = md5(document.js.pwd.value);
   //alert(pass);//mdp crypté

   if((log == "") || (pass == "")) {
      alert("Champ(s) vide(s)");
      return false;
     }
     else {
     return true;
     }
   }

// Controle de l'ancien et du nouveau mdp

function old_new_pwd() {
  //on récupère l'id et non le name
  var old = document.js1.ancien_pwd.value;
  //new est un mot réservé du langage
  var neww = document.js1.new_pwd.value;
  var new1 = document.js1.new_pwd1.value;

  //cette manière de récupérer les éléments fonctionne aussi
  /*var old = document.getElementById("ancien_pwd").value;
  var neww = document.getElementById("new_pwd").value;
  var new1 = document.getElementById("new_pwd1").value;*/

  if ((old != neww) && (neww == new1)) {
    return true;
  } else if (old == neww ) {
    alert("l'ancien et le nouveau mot de passe sont identiques !");
    return false;
  } else if (neww != new1) {
    alert("les 2 nouveaux mots de passe ne sont pas les mêmes !");
    return false;
  }

  /*
    //controle si l'ancien mdp correspond bien au mdp actuel avant de le changer (ancien=ancien)
    if (md5(old) != md5("toto")) {
      alert("L'ancien mot de passe ne correspond pas.");
      return false;
    }
*/

}
