import java.io.*;

public class ItIsTime implements IItIsTime
{

	private LineTransport line = new LineTransport();
	private DataTransport data = new DataTransport();
	private String host;

	public static void main(String[] args)
	{
		// Création d'une instance client
		ItIsTime client = new ItIsTime();

		// Vérification du nombre d'arguments ou de la commande help
		if (args.length != 6 || args[0].equals("-h"))
		{
			client.usage();
		}
		else
		{
			try
			{
				// Récupération des arguments
				client.setHost(args[0]);
				int port = Integer.parseInt(args[1]);
				String tp = args[2];
				String key = args[3];
				String group = args[4];
				String filename = args[5];

				// Overture de la connexion pour l'envoi de commande
				client.getLine().openClient(client.getHost(), port);

				// Tentative d'authentification
				String auth = client.connect(tp, group, key);

				// Vérification de l'authentification
				if (!auth.equals(""))
				{
					// Tentative d'envoi du fichier de rendu
					int sending = client.send(filename);

					// Vérification de l'envoi du fichier
					if (sending != -1)
					{
						System.out.println(sending + " bytes sended");

						// Fermeture de la connexion
						client.close();
					}
				}
			}
			catch(IOException e)
			{
				System.out.println("Unexpected error");
			}
		}

	} // fin du main

	public String connect(String tp, String grp, String key)
	{
		try
		{
			// Envoi du code 10 (authentification) avec la concaténation
			this.line.send("10"+tp+grp+key);

			// Réception du code serveur
			String rep = this.line.receive();

			// Vérification de la validité de l'authentification
			if (rep.substring(0,2).equals("20"))
			{
				return rep.substring(2);
			}
			else
			{
				this.printError(rep.substring(0,2));
				this.line.close();
				return "";
			}
		}
		catch(IOException e)
		{
			System.out.println("Unexpected error");
			return "";
		}
	}

	public int send(String filename)
	{
		try
		{
			// Envoi du code 12 (début envoi fichier)
			this.line.send("12");

			// Réception du code serveur
			String rep = this.line.receive();

			// Récupération du port pour l'envoi du fichier
			if (rep.substring(0,2).equals("22"))
			{
				// Conversion String vers Int du port reçu
				int data_port = Integer.parseInt(rep.substring(2));

				// Ouverture, envoi et fermeture de la connexion de tranfert binaire
				this.data.openClient(this.host, data_port);
				FileInputStream file = new FileInputStream(filename);
				int sended = this.data.send(file);
				this.data.close();

				// Envoi du code 13 (fin envoi fichier)
				this.line.send("13");

				// Réception du code serveur
				rep = this.line.receive();

				// Vérification de l'intégrité de l'envoi
				if (rep.substring(0,2).equals("23"))
				{
					return sended;
				}
				else
				{
					this.printError(rep.substring(0,2));
					this.line.close();
					return -1;
				}
			}
			else
			{
				printError(rep.substring(0,2));
				this.line.close();
				return -1;
			}
		}
		catch(IOException e)
		{
			System.out.println("Unexpected error");
			return -1;
		}
	}

	public void close()
	{
		try
		{
			// Envoi du code 14 (fermeture connexion)
			this.line.send("14");

			// Réception du code serveur
			String rep = this.line.receive();

			// Fermeture de la connexion
			this.line.close();

			// Vérification de la fermeture de la connexion côté serveur
			if (rep.equals("24"))
			{
				System.out.println("Connection closed");
			}
			else
			{
				printError(rep);
			}

		}
		catch(IOException e)
		{
			System.out.println("Unexpected error");
		}
	}

	public void usage()
	{
		System.out.println("usage : java ItIsTime host port tp key grp filename");
	}

	public void printError(String code)
	{
		switch(code)
		{
			case "50":
				System.out.println("Identification refused - wrong id");
				break;
			case "51":
				System.out.println("Identification refused - out of time");
				break;
			case "52":
				System.out.println("Error while opening connection");
				break;
			case "53":
				System.out.println("Error while reading file");
				break;
			case "55":
				System.out.println("Unexpected command");
				break;
			default:
				System.out.println("Unexpected command");
		}
	}

	public LineTransport getLine()
	{
		return this.line;
	}

	public DataTransport getData()
	{
		return this.data;
	}

	public String getHost()
	{
		return this.host;
	}

	public void setHost(String host)
	{
		this.host = host;
	}
}
