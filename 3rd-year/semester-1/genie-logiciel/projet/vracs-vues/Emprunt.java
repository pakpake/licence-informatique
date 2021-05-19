    /**
     *
     */
    package displaylivre;;

    /**
     * @author 
     *
     */
    //Imports de base pour la connexion et les requêtes
    import java.sql.Connection;
    import java.sql.DriverManager;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.sql.Statement;
    //Imports pour la date
    import java.text.ParseException;
    import java.text.SimpleDateFormat;
    import java.util.Date;
    import java.util.GregorianCalendar;

    public class Emprunt{

    /**
     * Vérification de l'existence de l'abonné
     * @param numSecu, celui donné par la personne voulant emprunter
     * @return vrai (true) si le numSecu est celui d'un abonné ou faux (false) sinon
     */
     public boolean estUnAbonne(String numSecu,Connection c){

       boolean retour = false;
       try {
           // ne pas modifier les 2 lignes suivante
           Statement statement = c.createStatement();
           statement.setQueryTimeout(30);  // set timeout to 30 sec.

        //On va chercher la liste des numSecus
         ResultSet idAbo = statement.executeQuery("select numSecu from Abonnes;");

         //On parcourt la liste des numSecus
         while(idAbo.next()){
            //Si un numSecu de la base de donnée correspond à l'numSecu donné on retourne true
             if(numSecu.equals(idAbo.getString("numSecu")))
               {retour = true;}
         }
         
         statement.close();
         idAbo.close();

       } catch (SQLException e) {
           System.err.println(e.getMessage());
     }
    return retour;
     }


     /**
      * Vérification de l'existence d'un média
      * @param EAN, celui donné par le bibliothécaire voulant réaliser l'emprunt
      * @return vrai (true) si l'EAN est celui d'un média ou faux (false) sinon
      */

      public boolean estUnMedia(String EAN,Connection c){

        boolean retour = false;
        try {
            // ne pas modifier les 2 lignes suivante
            Statement statement = c.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.


        //On va chercher la liste des EAN
        ResultSet EANLivres = statement.executeQuery("select EAN from Livres;");

              //On parcourt la liste des EAN
              while(EANLivres.next())
              {//Si un EAN de la base de donnée correspond à l'EAN donné on retourne true

                if(EAN.equals(EANLivres.getString("EAN")))
                    {retour = true;}
              }
         ResultSet EANDVD = statement.executeQuery("select EAN from DVD;");
              while(EANDVD.next())
              {//Si un EAN de la base de donnée correspond à l'EAN donné on retourne true

                if(EAN.equals(EANDVD.getString("EAN")))
                    {retour = true;}
              }

              statement.close();
              EANLivres.close();
              EANDVD.close();
              
            } catch (SQLException e) {
                System.err.println(e.getMessage());
          }

    return retour;
      }


      /**
       * Vérification des retards de rendu
       * @param numSecu, celui donné par la personne voulant emprunter
       * @return true si l'numSecu a un média en retard de rendu, false sinon
       */

       public boolean mediaEnRetard(String numSecu,Connection c){

         boolean retour = false;
         try {
             // ne pas modifier les 2 lignes suivante
             Statement statement = c.createStatement();
             statement.setQueryTimeout(30);  // set timeout to 30 sec.
         try {

           //Définition de la manière dont on écrit la date
           SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
           //Initialisation de la date que l'on va chercher dans la base de données
           Date date = null;
           //Initialisation de la date du jour
           Date aujourdhui = new Date();

           //On récupère les dates de retour théoriques pour des emprunts non rendus
           ResultSet empruntsEnCours = statement.executeQuery("select dateRetourTheorique from emprunts where numSecu = \""+numSecu+"\" and (dateRetour is NULL or dateRetour = '');");

           //On parcourt ces dates retournées
           while(empruntsEnCours.next()){

               //Récupération de la date
               String date2 = empruntsEnCours.getString("dateRetourTheorique");

               //On change le format de la date reçue pour qu'elle puisse être comparée avec la date du jour
               date = simpleDateFormat.parse(date2);

               //Comparaison des 2 dates, si aujourdhui > date, la comparaison est égale à 1
               // donc la date de retour est dépassée, le média est en retard
               if(aujourdhui.compareTo(date) == 1)
                {retour = true;}
           }

            empruntsEnCours.close();
         } catch (ParseException e) {
         e.printStackTrace();
         }
         
         statement.close();
         
         } catch (SQLException e) {
             System.err.println(e.getMessage());
         }

      return retour;

    }

    /**
     * Vérification de la présence de l'abonné sur la liste noire
     * @param numSecu, celui donné par la personne voulant emprunter
     * @return vrai (true) si l'numSecu est celui d'un abonné sur liste noire ou faux (false) sinon
     */
     public boolean estSurListeNoire(String numSecu,Connection c){

       boolean retour = false;
       try {
           // ne pas modifier les 2 lignes suivante
           Statement statement = c.createStatement();
           statement.setQueryTimeout(30);  // set timeout to 30 sec.

           //Initialisation du int que l'on va récupérer
           int verifListeNoire = 0;

           //On va chercher la valeur contenue dans le champ 'listeNoire' pour l'abonné en question dans la bdd
           ResultSet etatListeNoire = statement.executeQuery("select listeNoire from Abonnes where numSecu = \""+numSecu+"\";");
           //On la récupère dans notre variable
           verifListeNoire = etatListeNoire.getInt("listeNoire");

          if(verifListeNoire == 1) { retour = true;}
          else { retour = false;}

            statement.close();
            etatListeNoire.close(); 

             } catch (SQLException e) {
                 System.err.println(e.getMessage());
             }

      return retour;
     }

     /**
      * Méthode privée utilisée dans la méthode peutEmprunter()
      * Récupération de l'information Adulte/Enfant
      * @param numSecu, celui donné par la personne voulant emprunter
      * @return vrai (true) si c'est un adulte ou faux (false) sinon
      */
    private boolean estUnAdulte(String numSecu,Connection c){

    boolean retour = false;
      try {
          // ne pas modifier les 2 lignes suivante
          Statement statement = c.createStatement();
          statement.setQueryTimeout(30)   ;  // set timeout to 30 sec.

          //Récupération information Adulte/Enfant :
          //-----------------------------------------------
          //On va chercher la date de naissance
          //Si date du jour - date de naissance < 18 c'est un enfant, un adulte sinon

          try{

          //Définition de la manière dont on écrit la date
          SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
          //Initialisation de la date du jour
          Date aujourdhui = new Date();
          //Initialisations
          Date date = null;
          int enfant=0;

          //On récupère la date de naissance du numSecu passé en paramètre
          ResultSet dateNaissanceIdf = statement.executeQuery("select dateNaissance from Abonnes where numsecu =\""+numSecu+"\";");
          //Récupération de la date et conversion dans le format compris par le langage
          String date2 = dateNaissanceIdf.getString("dateNaissance");
          date = simpleDateFormat.parse(date2);

          //getTime donne le nombre de secondes écoulées depuis le 1er janvier 1970 jusqu'à cette date
          double diffMs = aujourdhui.getTime() - date.getTime();
          //Divisions pour passer de Millisecondes à des années
          diffMs = diffMs/1000/60/60/24/30.4325/12; // 30.4325 moyenne du nombre de jours d'un mois sur 4 ans, 3 normales 1 bissextile

          if(diffMs < 18){
            retour = false; //C'est un enfant
          }else retour = true; //C'est un adulte

          dateNaissanceIdf.close();
          
        } catch (ParseException e) {
        e.printStackTrace();
        }

          statement.close();

        } catch (SQLException e) {
            System.err.println(e.getMessage());
      }

      return retour;

    }


     /**
      * Méthode privée utilisée dans la méthode peutEmprunter()
      * Récupération du nombre d'années d'ancienneté
      * N'est utile que pour un abonné adulte
      * @param numSecu, celui donné par la personne voulant emprunter
      * @return le nomnbre d'années d'ancienneté
      */
    private int anciennete(String numSecu,Connection c){

      //Initialisation de la valeur de retour
      int anciennete=0;
      try {
          // ne pas modifier les 2 lignes suivante
          Statement statement = c.createStatement();
          statement.setQueryTimeout(30)   ;  // set timeout to 30 sec.


          try{

            //Définition de la manière dont on écrit la date
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            //Initialisation de la date du jour
            Date aujourdhui = new Date();
            //Initialisations
            double diffMs = 0;
            //Initialisation de la date d'inscription
            Date dateInscrit = null;

            //On récupère la date de naissance de l'identifiant passé en paramètres
            ResultSet dateInscriptionIdf = statement.executeQuery("select dateInscription from Abonnes where numsecu =\""+numSecu+"\";");
            //Récupération de la date
            String date3 = dateInscriptionIdf.getString("dateInscription");
            dateInscrit = simpleDateFormat.parse(date3);

            //getTime donne le nombre de secondes écoulées depuis le 1er janvier 1970 jusqu'à cette date
            diffMs = aujourdhui.getTime() - dateInscrit.getTime();
            diffMs = diffMs/1000/60/60/24/30.4325/12; //30.4325 moyenne du nombre de jours d'un mois sur 4 ans, 3 normales 1 bissextile

            if(diffMs >= 2){
              anciennete = 3;

            }else if(1 <= diffMs && diffMs < 2){
              anciennete = 2;

            }else {
              anciennete = 1;
              }

               dateInscriptionIdf.close();
        } catch (ParseException e) {
        e.printStackTrace();
        }

          statement.close();
          
        } catch (SQLException e) {
            System.err.println(e.getMessage());
      }

      return anciennete;

      }


     /**
      * Vérification de la possibilité pour l'abonné d'emprunter
      * @param numSecu, celui donné par la personne voulant emprunter
      * @param EAN, celui que l'abonné veut emprunter
      * @return vrai (true) s'il peut emprunter ou faux (false) sinon
      */
    public boolean peutEmprunter(String numSecu,String EAN){

    boolean retour = false;
      try {
           // TODO : Changer URL complete bdd
           String url = "jdbc:sqlite:C:\\Users\\username\\Documents\\NetBeansProjects\\DisplayLivre\\src\\displaylivre\\Librairie.db";
            Connection c = DriverManager.getConnection(url);
        //Vérification de base en premier
        boolean abonne = estUnAbonne(numSecu,c);
        boolean media = estUnMedia(EAN,c);
        boolean retard = mediaEnRetard(numSecu,c);
        boolean listenoire = estSurListeNoire(numSecu,c);

        if ( abonne && media && !retard && !listenoire ){

          // ne pas modifier les 2 lignes suivante
          Statement statement = c.createStatement();
          statement.setQueryTimeout(30)   ;  // set timeout to 30 sec.




          //Récupération information récupération du nombre d'emprunts déjà en cours :
          //---------------------------------------

          //Initialisation des varibales pour compter le nombre d'emprunts de chaque média déjà en cours
          int nbLivres=0;
          int nbDVD=0;
          int i = 0;
          String[] listeDesEANEmpruntes = new String[100000];
          String[] listeDesEANLivres = new String[100000];

          //Stockage dans le tableau de la liste des EAN des médias en cours d'emprunt par l'abonné donné
          ResultSet listeEmpruntsEnCours = statement.executeQuery("select EAN from Emprunts where numsecu =\""+numSecu+"\" and (dateRetour is NULL or dateRetour = '');");

          //On parcourt les EAN retournés pour les stocker dans le tableau
          while(listeEmpruntsEnCours.next()){
              listeDesEANEmpruntes[i] = listeEmpruntsEnCours.getString("EAN");
              i++;
          }

          //Stockage dans le tableau de la liste des EAN correspondants à des livres
          i=0;
          ResultSet listeEANLivres = statement.executeQuery("select EAN from Livres;");
          //Stockage dans le tableau de la liste des EAN des livres
          while(listeEANLivres.next()){
              listeDesEANLivres[i] = listeEANLivres.getString("EAN");
              i++;
          }


          //On compare les 2 tableaux pour trier les EAN en cours d'emprunt en 2 catégories, les livres et les DVD
            for (int k = 0; listeDesEANEmpruntes[k]!=null; k++) {

                boolean present = false;

                for (int j = 0; j < listeDesEANLivres.length; j++) {

                    if (listeDesEANEmpruntes[k].equals(listeDesEANLivres[j])) {
                        present = true;
                        break;
                    }
                }
                if (present) nbLivres++;
                 else  nbDVD++;

            }

          //On profite du tableau des EAN des livres pour savoir ce que l'abonné veut emprunter, si c'est un dvd ou un livre

          boolean livre = false;
          for (int t = 0; listeDesEANLivres[t]!=null; t++) {

            if(listeDesEANLivres[t].equals(EAN)){
              livre = true;
              break;
            }
          }

          //Initialisations des variables qui vont récupérer les return des méthodes
          boolean adulte = estUnAdulte(numSecu,c);
          int anciennete = anciennete(numSecu,c);


          //Tests pour savoir s'il a le droit d'emprunter :
          //---------------------------------------

          //Si c'est un enfant
          //ET
          //Qu'il n'a pas déjà 5 emprunts en empruntsEnCours
          //ET
          //Qu'il ne veut pas emprunter un dvd

          if( !adulte && nbLivres < 5 && livre )
            retour = true;

          //Si c'est un adulte ET qu'il veut emprunter un livre
          //Il doit respecter une des 3 conditions :
          // 1 an d'ancienneté et moins de 4 livres en cours d'emprunt
          // 2 ans d'ancienneté et moins de 5 livres en cours d'emprunt
          // 3 ans d'ancienneté et moins de 7 livres en cours d'emprunt

          if ( ( adulte && livre )
                      && (
               ( anciennete == 1 && nbLivres < 4 )
                                  ||
               ( adulte && anciennete == 2 && nbLivres < 5 )
                                  ||
               ( adulte && anciennete == 3 && nbLivres < 7 )
                        )
              )retour = true;

            //Si c'est un adulte ET qu'il veut emprunter un dvd
            //Il doit respecter une des 3 conditions :
            // 1 an d'ancienneté et moins de 2 dvd en cours d'emprunt
            // 2 ans d'ancienneté et moins de 3 dvd en cours d'emprunt
            // 3 ans d'ancienneté et moins de 5 dvd en cours d'emprunt

            if ( ( adulte && !livre )
                        && (
                 ( anciennete == 1 && nbDVD < 2 )
                                    ||
                 ( adulte && anciennete == 2 && nbDVD < 3 )
                                    ||
                 ( adulte && anciennete == 3 && nbDVD < 5 )
                          )
                ) retour = true;
              }
        
        c.close();
              } catch (SQLException e) {



                  System.err.println(e.getMessage());
            }

    return retour;
    }


     /**
      * Emprunt du média
      * @param numSecu, celui donné par la personne voulant emprunter
      * @param EAN, celui que l'abonné veut emprunter
      */
    public boolean emprunter(String numSecu, String EAN){

      try {
          // TODO : Changer URL complete bdd
          String url = "jdbc:sqlite:C:\\Users\\username\\Documents\\NetBeansProjects\\DisplayLivre\\src\\displaylivre\\Librairie.db";
          Connection c = DriverManager.getConnection(url);
          // ne pas modifier les 2 lignes suivante
          Statement statement = c.createStatement();
          statement.setQueryTimeout(30)   ;  // set timeout to 30 sec.


          //Initialisation de la date du jour, la date d'emprunt
          Date aujourdhui = new Date();

          SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
          String dateEmprunt = formater.format(aujourdhui);

          GregorianCalendar dateReTh = new GregorianCalendar();
          dateReTh.setTime(aujourdhui);

          //On ajoute 1 mois à la date d'emprunt
          dateReTh.add(GregorianCalendar.MONTH, 1);

          String dateRetourTheorique = formater.format(dateReTh.getTime());

          //On ajoute l'emprunt dans la base de données
          statement.executeUpdate("insert into Emprunts(EAN,numSecu,dateRetourTheorique,dateRetour,dateEmprunt) values(\""+EAN+"\",\""+numSecu+"\",\""+dateRetourTheorique+"\",'',\""+dateEmprunt+"\");");
          
          statement.close();
          c.close();

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
      }
    return true;
    }
}
