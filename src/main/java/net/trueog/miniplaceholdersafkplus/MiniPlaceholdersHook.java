package net.trueog.miniplaceholdersafkplus;

import java.util.Date;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import net.lapismc.afkplus.api.AFKPlusPlayerAPI;
import net.lapismc.afkplus.playerdata.AFKPlusPlayer;
import net.lapismc.afkplus.util.core.utils.prettytime.Duration;
import net.lapismc.afkplus.util.core.utils.prettytime.PrettyTime;
import net.lapismc.afkplus.util.core.utils.prettytime.units.JustNow;
import net.lapismc.afkplus.util.core.utils.prettytime.units.Millisecond;
import net.trueog.utilitiesog.UtilitiesOG;

public class MiniPlaceholdersHook {

	private final AFKPlusPlayerAPI api;
	private final MiniPlaceholdersAFKPlus plugin;
	private final PrettyTime prettyTime;

	public MiniPlaceholdersHook(MiniPlaceholdersAFKPlus plugin) {

		this.plugin = plugin;

		api = new AFKPlusPlayerAPI();
		prettyTime = new PrettyTime();
		prettyTime.removeUnit(JustNow.class);
		prettyTime.removeUnit(Millisecond.class);

		// Register MiniPlaceholders.
		registerPlaceholders();

	}

	private void registerPlaceholders() {

		UtilitiesOG.registerAudiencePlaceholder("afkplus_status", player -> {

			AFKPlusPlayer afkPlayer = api.getPlayer(player);

			return afkPlayer.isAFK() ? plugin.getConfig().getString("status.true") : plugin.getConfig().getString("status.false");

		});

		UtilitiesOG.registerAudiencePlaceholder("afkplus_afktime", player -> {

			AFKPlusPlayer p = api.getPlayer(player);
			Long afkStart = p.getAFKStart();
			if (afkStart == null) {

				return plugin.getConfig().getString("afktime.notafk");

			}

			List<Duration> totalTimeDurations = reduceDurationList(prettyTime.calculatePreciseDuration(new Date(afkStart)));

			return prettyTime.formatDuration(totalTimeDurations);

		});

		UtilitiesOG.registerAudiencePlaceholder("afkplus_totaltimeafk", player -> {

			AFKPlusPlayer p = api.getPlayer(player);
			long totalTime = p.getTotalTimeAFK();
			if (totalTime <= 0) {

				return plugin.getConfig().getString("totaltimeafk.none");

			}

			Date timeInPast = new Date(System.currentTimeMillis() - totalTime);

			List<Duration> totalTimeDurations = reduceDurationList(prettyTime.calculatePreciseDuration(timeInPast));

			return prettyTime.formatDuration(totalTimeDurations);

		});

		UtilitiesOG.registerGlobalPlaceholder("afkplus_playersafk", () -> {

			int AFKPlayerCount = 0;
			for (Player p : Bukkit.getOnlinePlayers()) {

				if (api.getPlayer(p.getUniqueId()).isAFK()) {

					AFKPlayerCount++;
				}

			}

			if (AFKPlayerCount == 0) {

				return plugin.getConfig().getString("PlayersCurrentlyAFK.Zero");

			}
			else {

				return String.valueOf(AFKPlayerCount);

			}

		});

	}

	private List<Duration> reduceDurationList(List<Duration> durationList) {

		while (durationList.size() > plugin.getConfig().getInt("TotalTimeAFK.numberOfTimeUnits")) {

			Duration smallest = null;
			for (Duration current : durationList) {

				if (smallest == null || smallest.getUnit().getMillisPerUnit() > current.getUnit().getMillisPerUnit()) {

					smallest = current;

				}

			}

			durationList.remove(smallest);

		}

		return durationList;

	}

}