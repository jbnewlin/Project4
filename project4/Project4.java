import java.util.Scanner;

class Command {
	// define a class to represent all the
	// commands from the user input

	private int date;
	private String tehcommand;
	private int a, b, c;

	public Command(int date, String input) {
		this.date = date;
		this.tehcommand = input;
	}

	public Command(int date, String input, int a) {
		this.date = date;
		this.tehcommand = input;
		this.a = a;
	}

	public Command(int date, String input, int a, int b) {
		this.date = date;
		this.tehcommand = input;
		this.a = a;
		this.b = b;
	}

	public Command(int date, String input, int a, int b, int c) {
		this.date = date;
		this.tehcommand = input;
		this.a = a;
		this.b = b;
		this.c = c;
	}

	public int getDate() {
		return date;
	}

	public int getA() {
		return a;
	}

	public int getB() {
		return b;
	}

	public int getC() {
		return c;
	}

	public String getInput() {
		return tehcommand;
	}

	public int getCommandType() {
		switch (getInput()) {
		case "B":
			return 1;
		case "S":
			return 2;
		case "X":
			return 3;
		case "T":
			return 4;
		case "end":
			return 5;
		default:
			return 0;
		}
	}
}

public class Project4 {

	// get the input from the user
	// parse the input
	// construct a Command object
	// and return it

	public static int d;
	public static String commandString;
	public static int i = 0;
	public static int currentDate = 0;

	//process commands and return a new command object
	public static Command getNextCommand(String input) {

		@SuppressWarnings("resource")
		Scanner line = new Scanner(input);
		if (line.hasNextInt()) {
			d = line.nextInt();
		} else {
			badInput(i);
		}
		if (line.hasNext()) {
			commandString = line.next();
			if (commandString.length() > 1 && commandString.equals("end") && !line.hasNext()) {
				return new Command(d, commandString);
			}
		} else {
			badInput(i);
		}
		if (commandString.length() > 1) {
			int a = 0;
			try {
				a = Integer.parseInt(commandString.substring(1));
				commandString = String.valueOf(commandString.charAt(0));
			} catch (NumberFormatException e) {
				badInput(i);
			}
			if (line.hasNextInt()) {
				int b = line.nextInt();
				if (line.hasNextInt()) {
					int c = line.nextInt();
					return new Command(d, commandString, a, b, c);
				} else {
					return new Command(d, commandString, a, b);
				}
			} else {
				return new Command(d, commandString, a);
			}
		} else {
			if (line.hasNextInt()) {
				int a = line.nextInt();
				if (line.hasNextInt()) {
					int b = line.nextInt();
					return new Command(d, commandString, a, b);
				} else {
					return new Command(d, commandString, a);
				}
			} else {
				return new Command(d, commandString);
			}
		}
	}

	public static void badInput(int line) {
		System.out.println("Error input santax line " + line);
		System.exit(1);

	}
	
	public static void semanticsError(int line) {
		System.out.println("Error semantics date " + line);
		System.exit(1);

	}

	public static void main(String[] args) {
		SongCollection songCollection = new SongCollection();
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);

		while (true) {
			String string = scan.nextLine();
			Command command = getNextCommand(string);
			i++;
			if (d <= currentDate || d < 1) {
				semanticsError(d);
			}
			currentDate = d;

			switch (command.getCommandType()) {
			case 0:
				badInput(i);
				break;
			case 1:
				songCollection.addSong(d);
				break;
			case 2:
				songCollection.updateSong(command.getA(), command.getB(), command.getC());
				break;
			case 3:
				songCollection.deleteSong(command.getA());
				break;
			case 4:
				System.out.println(songCollection.popular());
				break;
			case 5:
				System.out.println(songCollection.minMax());
				System.exit(0);
			default:
				break;
			}
			;
		}

	}
}
