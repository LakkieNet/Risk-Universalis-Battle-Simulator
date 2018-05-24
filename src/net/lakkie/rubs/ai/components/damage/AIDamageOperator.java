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
				float intersectionScalar = intersectionArea / (target.getRenderWidth() * target.getRenderHeight());
				float[] kills = this.calcDamage(unit, target, intersectionScalar);
				// These 3 floats are the final enemy kills
				int infantryKills = (int) (kills[0]);
				int cavalryKills = (int) (kills[1]);
				int armorKills = (int) (kills[2]);
				target.infantry -= infantryKills;
				target.cavalry -= cavalryKills;
				target.armor -= armorKills;
			}
		}
	}

	public float[] calcDamage(PositionedUnit a, PositionedUnit d, float intersectionScalar) {
		float[] result = new float[3];
		float infantryScalar = intersectionScalar * (float) (a.infantry);
		result[0] += a.getMoraleScalar() * this.dwc.getWeight("infantry").infantryWeight * infantryScalar;
		result[0] += a.getMoraleScalar() * this.dwc.getWeight("infantry").cavalryWeight * infantryScalar;
		result[0] += a.getMoraleScalar() * this.dwc.getWeight("infantry").armorWeight * infantryScalar;
		float cavalryScalar = intersectionScalar * (float) (a.cavalry);
		result[1] += a.getMoraleScalar() * this.dwc.getWeight("cavalry").infantryWeight * cavalryScalar;
		result[1] += a.getMoraleScalar() * this.dwc.getWeight("cavalry").cavalryWeight * cavalryScalar;
		result[1] += a.getMoraleScalar() * this.dwc.getWeight("cavalry").armorWeight * cavalryScalar;
		float armorScalar = intersectionScalar * (float) (a.armor);
		result[2] += a.getMoraleScalar() * this.dwc.getWeight("armor").infantryWeight * armorScalar;
		result[2] += a.getMoraleScalar() * this.dwc.getWeight("armor").cavalryWeight * armorScalar;
		result[2] += a.getMoraleScalar() * this.dwc.getWeight("armor").armorWeight * armorScalar;
		return result;
	}

}
