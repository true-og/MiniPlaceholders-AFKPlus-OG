# AFKPlus MiniPlaceholder API Add-on

This plugin adds MiniPlaceholder API support, you can edit some of the strings it will provide to MiniPlaceholders in the config.yml it will generate.

The MiniPlaceholders it provides are "<afkplus_status>", "<afkplus_afktime", "afkplus_totaltimeafk" and "afkplus_playersafk"

### Below is a short description of each placeholder:

Status outputs the players' current AFK status, the strings for true and false can be set in the config

AFKTime outputs the players' current time AFK, e.g. "3.10 minutes" if the player has been AFK for just over 3 minutes, the string to be returned when the player isn't AFK and the string for minutes can be set in the config

TotalTimeAFK outputs the total time that the player has ever been AFK, the number of time units to show can be set in the config. e.g. 3 time units might look like "1 hour 10 minutes and 4 seconds", whereas 2 would be "1 hour and 10 minutes"

PlayersAFK outputs the same result for all players, it shows the current count of AFK players online. The string returned when there aren't any AFK players can be set in the config

License: [Apache 2.0](https://github.com/true-og/MiniPlaceholders-AFKPlus/blob/master/LICENSE).

Crafted by [TrueOG Network](https://true-og.net).