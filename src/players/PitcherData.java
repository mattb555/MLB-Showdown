package players;

import java.util.*;

import gameData.Field;
import gameData.GameStat;
import gameData.LineupManager;
import gameData.StrategyCard;

public class PitcherData extends PlayerData {

	private Range popout;
	private int innings;
	private String role;
	private String hand;

	public PitcherData(Scanner input) {
		String num = input.next().trim();
		if (num.isEmpty()) {
			setNum = Integer.parseInt(input.next().trim());
		} else {
			setNum = Integer.parseInt(num);
		}
		edition = input.next().trim();
		name = input.next().trim();
		input.nextLine();
		setTeam = input.next().trim();
		cost = Integer.parseInt(input.next().trim());
		year = input.next().trim();
		baseMod = Integer.parseInt(input.next().trim());
		innings = Integer.parseInt(input.next().trim());
		role = input.next().trim();
		input.nextLine();
		hand = input.next().trim();
		special = new HashSet<String>();
		if (input.hasNext()) {
			String[] specials = input.next().trim().split(" ");
			for (int i = 0; i < specials.length; i++) {
				special.add(specials[i]);
			}
		}
		input.nextLine();
		if (input.next().trim().equals("SO")) {
			popout = Range.parseRange("-");
			input.nextLine();
		} else {
			input.nextLine();
			popout = Range.parseRange(input.next().trim());
		}
		strikeout = Range.parseRange(input.next().trim());
		groundout = Range.parseRange(input.next().trim());
		flyout = Range.parseRange(input.next().trim());
		walk = Range.parseRange(input.next().trim());
		single = Range.parseRange(input.next().trim());
		input.next();
		twobase = Range.parseRange(input.next().trim());
		input.next();
		homer = Range.parseRange(input.next().trim());
		if (input.hasNextLine()) {
			input.nextLine();
		}
	}

	// Generic getter methods
	public String getRole() {
		return role;
	}

	public String getHand() {
		return hand;
	}

	// check card with specific updates related to pitchers
	public void checkCard(int dice) {
		super.checkCard(dice);
		if (popout.inRange(dice)) {
			StrategyCard.emit("PU");
		}
	}

	// Takes the number of pitches a pitcher has thrown
	// Returns the number of innings remaining until a pitcher is tired
	public int checkInnings(int soFar) {
		if (soFar > innings) {
			return innings - soFar;
		}
		return 0;
	}

	public String getCard() {
		String card = "";
		card += "Name:\t" + this + "\n";
		card += "Control:\t" + this.baseMod + "\n";
		card += "Role:\t" + this.role + "\n";
		/*
		 * old if (this.role.equals("S")) { card += "Starter"; } else if
		 * (this.role.equals("R")) { card += "Reliever"; } else { // C card +=
		 * "Closer"; } card += "\n";
		 */
		card += "Throws:\t" + this.hand + "\n";
		card += "Innings:\t" + this.innings + "\n";
		card += "Positions:\tPitcher\n\n";
		card += "PU\t" + this.popout + "\n";
		card += "K:\t" + this.strikeout + "\n";
		card += "GB:\t" + this.groundout + "\n";
		card += "FB:\t" + this.flyout + "\n";
		card += "BB:\t" + this.walk + "\n";
		card += "1B:\t" + this.single + "\n";
		card += "2B:\t" + this.twobase + "\n";
		card += "HR:\t" + this.homer + "\n";
		return card;

	}

}