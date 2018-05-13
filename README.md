# Risk Universalis Battle Simulator
	The goal of this program is to accurately simulate battles that take place in Risk Universalis.
Risk Universalis is a game which allows you to play as a nation, and diplomatically and militarily
interact with other players. One element of this game that people often get wrong are the battles,
and the hope with this is to try to either create a new version of the game, or integrate this system
into the old game.

	This application takes in units, whether that's regiment-based organization, or more modern
division-based organization, simulate a battle with a user experience, and give a result. The
result may comprise of the amount of casualties, amount of deaths on both sides(individually
and collectively), how many were able to retreat, the ending status of each side, and total
resource expenditure. Some of the factors that this takes in is the attacking army, the
defending army, the morales of the armies, the amount of soldiers, and the type of terrain
(e.g. river crossing, mountain terrain, plateau, or urban).

	The way the battles play out are entirely-AI based. While the user can change the way the
AI behaves, their maneuvers, etc, the actual input during the battle cannot be altered or
changed(for now). Depending on the selected state they are in, the defenders may try to
retreat, stay on a last stance, or launch a counter-offensive. The battles are structured in
waves, where, either side may try to launch offensives. Depending on partial random chance and
advantages/disadvantages, they may be successful, unsuccessful, or inconclusive. The AI will
try to play realistically, where the leaders of the armies can only focus on one thing at a time,
and if getting attacked on multiple sides they will be stressed and morale will be lowered.