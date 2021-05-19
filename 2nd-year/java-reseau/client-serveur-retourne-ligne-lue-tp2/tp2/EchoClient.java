import java.io.*;
import java.util.Scanner;
import java.net.Socket;

public class EchoClient
{
	public static void main(String[] args)
	{
		// Cas : Nombre d'arguments insuffisants
		if (args.length <= 1)
		{
			EchoClient.usage();
		}
		// Cas : Argument d'aide
		else if (args[0].equals("-h"))
		{
			EchoClient.usage();
		}
		// Cas : Arguments nécéssaires
		else
		{
			// Adresse du serveur Date
			String host = args[0];

			// Port d'écoute du serveur Date
			int port = Integer.parseInt(args[1]);

			// Initialisation de l'entrée clavier
			Scanner scanner = new Scanner(System.in);

			// Bloc Try & Catch autour de la connexion au serveur
			try
			{
				// Initialisation et ouverture
				// de la connexion entre le client et le serveur
				Socket socket = new Socket(host, port);

				// Initialisation de l'entrée client
				BufferedReader input = new BufferedReader
					(new InputStreamReader(socket.getInputStream()));

				// Initialisation de la sortie client
				PrintWriter output = new PrintWriter
					(socket.getOutputStream(), true);

				// Initalisation de la variable réponse
				String res = "";

				// Boucle : Tourne tant que la réponse n'est pas nulle
				while (res != null)
				{
					// Récupération de l'entrée clavier
					res = scanner.nextLine();

					// Envoi vers le serveur de la réponse
					output.println(res);

					// Affichage de la réponse serveur
					System.out.println(input.readLine());
				}
				
				// Fermeture de la connexion
				socket.close();
			}
			catch(IOException e)
			{
				// Affichage de l'erreur
				System.out.println("Couldn't connect to "+host+" on port "+port);
			}
		}
	}

	// Fonction d'aide
	public static void usage()
	{
		// Affichage de la documentation d'aide
		System.out.println("usage: java EchoClient host port");
	}
}
