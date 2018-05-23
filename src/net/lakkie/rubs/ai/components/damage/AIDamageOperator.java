package net.lakkie.rubs.ai.components.damage;

import net.lakkie.rubs.ai.RUBSBattleAI;
import net.lakkie.rubs.ai.components.AIBattleComponent;
import net.lakkie.rubs.ai.components.damage.readers.DamageWeightConfig;
import net.lakkie.rubs.readers.DamagePropertiesReader;
import net.lakkie.rubs.storage.PositionedUnit;

public class AIDamageOperator extends AIBattleComponent {

	private long lastTick;
	private DamageWeightConfig dwc;
	private boolean isDamageTick = false;

	public AIDamageOperator(RUBSBattleAI ai) {
		super(ai);
		this.dwc = new DamageWeightConfig();
	}

	public void initUnit(PositionedUnit unit) {
		this.lastTick = System.currentTimeMillis();
		this.tickUnit(unit);
	}

	public void slowTickUnit(PositionedUnit unit) {

	}

	public void singleTick() {
		long currentTime = System.currentTimeMillis();
		this.isDamageTick = currentTime >= this.lastTick + DamagePropertiesReader.inst.damageTickMillis;
		if (this.isDamageTick) {
			this.lastTick = currentTime;
		}
	}

	public void tickUnit(PositionedUnit unit) {

		if (this.isDamageTick) {
			PositionedUnit target = this.ai.battle.getNearestUnit(unit, false);
			float intersectionArea = (float) target.getIntersectionArea(unit);
			if (intersectionArea > 0) {
				float[] kills = this.calcPerDamage(unit, target);
				for (int i = 0; i < kills.length; i++)
					kills[i] *= intersectionArea;
				// These 3 floats are the final enemy kills
				int infantryKills = (int) (kills[0] * this.dwc.getWeight("infantry").infantryWeight);
				int cavalryKills = (int) (kills[1] * this.dwc.getWeight("infantry").infantryWeight);
				int armorKills = (int) (kills[2] * this.dwc.getWeight("infantry").infantryWeight);
				target.infantry -= infantryKills;
				target.cavalry -= cavalryKills;
				target.armor -= armorKills;
			}
		}
	}

	/**
	 * Gets the damage that 1 pixel will do on another if intersecting.
	 * 
	 * @param a
	 *              Attacking unit
	 * @param d
	 *              Defending unit
	 * @return The damage per pixel. Index <tt>0</tt> is infantry damage,
	 *         <tt>1</tt> is cavalry damage, and <tt>2</tt> is armor damage.
	 */
	public float[] calcPerDamage(PositionedUnit a, PositionedUnit d) {
		float[] result = new float[3];
		float moraleScalarA = a.getMoraleScalar();
		float moraleScalarD = a.getMoraleScalar();
		result[0] = moraleScalarA * moraleScalarD;
	}

}
