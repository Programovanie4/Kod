import static java.util.Calendar.*;

public class Switch {

	public static void main(String[] args) {
		if (args.length == 0) return;
		switch(args[0]) {
		case "load":
			System.out.println("citaj");
			break;
		case "save": 
		case "saveAs":
			System.out.println("pis");
			break;
		default:
			System.out.println("ine");
		}

		var	day = MONDAY;
		int numLetters;
		switch (day) {
			case MONDAY:
			case FRIDAY:
			case SUNDAY:
				numLetters = 6;
				break;
			case TUESDAY:
				numLetters = 7;
				break;
			case THURSDAY:
			case SATURDAY:
				numLetters = 8;
				break;
			case WEDNESDAY:
				numLetters = 9;
				break;
			default:
				throw new 	IllegalStateException("Wat: " + day);
		}
		var numLetters_ = switch(day) {
			case MONDAY, FRIDAY, SUNDAY -> 6;
			case TUESDAY                -> 7;
			case THURSDAY, SATURDAY     -> 8;
			case WEDNESDAY              -> 9;
			default -> throw new IllegalStateException("Wat: " + day);
		};
	}
}
