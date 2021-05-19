import java.io.*;
import java.net.Socket;
import java.net.ServerSocket;

public class EchoServer
{
	public static void main(String[] args)
	{
		// Cas : Nombre d'arguments insuffisants
		if (args.length <= 0)
		{
			EchoServer.usage();
		}
		// Cas : Argument d'aide
		else if (args[0].equals("-h"))
		{
			EchoServer.usage();
		}
		// Cas : Arguments nécéssaires
		else
		{
			// Port d'écoute du serveur
			int port = Integer.parseInt(args[0]);

			// Bloc Try & Catch autour de l'écoute des clients
			try
			{
				// Initialisation de l'écoute serveur
				ServerSocket server = new ServerSocket(port);

				// Boucle : Tourne tant que le serveur est ouvert
				while (!server.isClosed())
				{
					// Autorisation et ouverture
					// d'une connexion avec le client
					Socket socket = server.accept();

					System.out.println("Connexion started!");

					// Initialisation de l'entrée serveur 
					BufferedReader input = new BufferedReader
						(new InputStreamReader(socket.getInputStream()));

					// Initialisation de la sortie serveur
					PrintWriter output = new PrintWriter
						(socket.getOutputStream(),true);

					// Initialisation de la réponse client
					String res = input.readLine();

					// Boucle : Tourne tant que la réponse n'est pas nulle
					while (res != null)
					{
						// Mise en majuscule de la réponse
						res = res.toUpperCase();
						System.out.println(res);

						// Envoi de la réponse vers le client
						output.println(res);

						// Récupération de la nouvelle réponse
						res = input.readLine();
					}

					// Fermeture de la connexion
					socket.close();
					System.out.println("Connexion ended!");
				}
				// Fermeture du serveur
				server.close();
			}
			catch (IOException e)
			{
				// Affichage de l'erreur
				e.printStackTrace();
			}
		}
	}

	// Fonction d'aide
	public static void usage()
	{
		// Affichage de la documentation d'aide
		System.out.println("usage: java EchoServer port");
	}
}
