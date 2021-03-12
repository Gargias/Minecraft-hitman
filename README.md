# Minecraft-hitman

This plugin is directly inspired by dream's [3 hitmen video](https://www.youtube.com/watch?v=cT7wOSOZVoc)

To use this plugin, you need a [spigot](https://getbukkit.org/download/spigot) or [paper](https://papermc.io/) server

# Usage

The prefix used by this plugin is `hitman`, type `/hitman help` to see all the available commands (also listed below).

Before starting the game, you need to set the player to be hunted usign `/hitman setSurvivor <player>` on sertting this, the survivor will get the gowing effect for the duration of the game, after the game is started using `/hitman start`.
There are invisibility periods which occur at set intervals that you can change using `/hitman setInterval <time in ticks>`, which also sets the duration of these invinsibility periods, during these invisibility periods all players are immune to any damage.
The goal is to kill the survivor in a set period of time, that you can decide and keep track of using the amount of occured invisibility periods. (Feature for setting a timer in game may be added in a fututre release)

```yml
Main Commands:
    start:
        usage: /hitman start
        description: it starts the game
    stop:
        usage: /hitman stop
        description: it stops the game
    setInterval:
        usage: /hitman setInterval <time in ticks (20 equals 1s)>
        description: sets the interval between invisibility periods, a.k.a. break-time
    setSurvivor:
        usage: /hitman setSurvivor <player>
        description: sets the player being hunted
```
```yml
Other commands:
    survivor:
        usage: /hitman survivor
        description: displays the name of the player being hunted
    interval:
        usage: /hitman interval
        description: displays the time (in ticks) between invisibility periods (break time)
    help:
        usage: /hitman help
        description: displays a list of commands you can use with what they do
```

