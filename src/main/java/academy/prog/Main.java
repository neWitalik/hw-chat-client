package academy.prog;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		try {
			System.out.println("Enter your login: ");
			String login = scanner.nextLine();

			List<String> receiverList = new ArrayList<>();
			receiverList.add("Adam");
			receiverList.add("Bob");
			receiverList.add("David");

			Thread th = new Thread(new GetThread());
			th.setDaemon(true);
			th.start();

			while (true) {
				String receiver;

				while (true) {
					System.out.println("Enter receiver login (Adam, Bob, David): ");
					receiver = scanner.nextLine();
					if (receiverList.contains(receiver)) {
						break;
					} else {
						System.out.println("Invalid receiver. Try again.");
					}
				}

				while (true) {
					System.out.println("Enter your message: ");
					while (true) {
						String text = scanner.nextLine();
						if (text.isEmpty()) break;

						Message m = new Message(login, text, receiver);
						int res = m.send(Utils.getURL() + "/add");

						if (res != 200) { // 200 OK
							System.out.println("HTTP error occurred: " + res);
							return;
						}
					}
				}
			}


		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			scanner.close();
		}
	}
}
