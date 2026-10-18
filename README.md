# Villager Feast

A Fabric mod for Minecraft Java 26.3 that lets villagers breed with far more foods than bread, carrots, potatoes, and beetroot.

![Villager Feast icon](src/main/resources/assets/villagerfeast/icon.png)

## Features
- Villagers accept many more foods for breeding: fruits, cooked and raw meats, fish, cookies, pumpkin pie, and more.
- Golden apples, golden carrots, and glistering melon slices work as fancy treats.
- Each food's breeding value is based on how much hunger it restores for players.
- Foods that could hurt villagers, plus soups and stews, are left out on purpose.
- Every food's value can be changed in a config file or in game through Mod Menu.

## Requirements
- Minecraft Java 26.3
- Fabric Loader 0.19.5 or newer
- Fabric API
- Cloth Config
- Mod Menu (optional, adds the in-game config button)

## Configuration
Edit `config/villagerfeast.json`, or open **Mods → Villager Feast → Config** in game.
Set a food to `0` to disable it. Restart the game after changing values.

```json
{
  "foods": {
    "minecraft:apple": 4,
    "minecraft:cookie": 2
  }
}
```

## How it works
Minecraft 26.3 decides villager food through the `villager_food` item component. Villager Feast adds that component to extra foods with Fabric API, so it doesn't need any mixins.

## Building from source
```
git clone https://github.com/YOUR-USERNAME/villagerfeast.git
cd villagerfeast
./gradlew build
```
The mod jar ends up in `build/libs/`. You'll need Java 25.

## License
MIT
