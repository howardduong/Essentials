package com.earth2me.essentials.user;

import com.earth2me.essentials.storage.ListType;
import com.earth2me.essentials.storage.MapKeyType;
import com.earth2me.essentials.storage.MapValueType;
import com.earth2me.essentials.storage.StorageObject;
import java.util.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.bukkit.Location;
import org.bukkit.Material;


@Data
@EqualsAndHashCode(callSuper = false)
public class UserData implements StorageObject
{
	public enum TimestampType
	{
		JAIL, MUTE, LASTHEAL, LASTTELEPORT, LOGIN, LOGOUT
	}
	private String nickname;
	private Double money;
	@MapValueType(Location.class)
	private Map<String, Location> homes = new HashMap<String, Location>();
	@ListType(Material.class)
	private Set<Material> unlimited = new HashSet<Material>();
	@MapValueType(List.class)
	@MapKeyType(Material.class)
	private Map<Material, List<String>> powerTools = new HashMap<Material, List<String>>();
	private Location lastLocation;
	@MapKeyType(TimestampType.class)
	@MapValueType(Long.class)
	private Map<TimestampType, Long> timestamps = new HashMap<TimestampType, Long>();
	private String jail;
	@ListType
	private List<String> mails;
	private Inventory inventory;
	private boolean teleportEnabled;
	@ListType
	private Set<String> ignore;
	private boolean godmode;
	private boolean muted;
	private boolean jailed;
	private Ban ban;
	private String ipAddress;
	private boolean afk;
	private boolean newplayer = true;
	private String geolocation;
	private boolean socialspy;
	private boolean npc;
	private boolean powertoolsenabled;

	public UserData()
	{
		unlimited.add(Material.AIR);
		unlimited.add(Material.ARROW);
		unlimited.add(Material.APPLE);
		powerTools.put(Material.DEAD_BUSH, Collections.singletonList("test"));
		timestamps.put(TimestampType.JAIL, Long.MIN_VALUE);
	}

	public boolean hasUnlimited(Material mat)
	{
		return unlimited != null && unlimited.contains(mat);
	}

	public List<String> getPowertool(Material mat)
	{
		return powerTools == null ? Collections.<String>emptyList() : powerTools.get(mat);
	}
	
	public void removeHome(String home)
	{
		if (homes == null) {
			return;
		}
		homes.remove(home);
	}
}