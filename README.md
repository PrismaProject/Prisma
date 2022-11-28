# PrismaSense-㭤
A  injection client for Minecraft(Forge),

Based On LiquidBounce(http://LiquidBounce.net)
Website: https://Prisma.OwO.today\
Telegram: https://t.me/+c_gb-LZ8XPQwYWY1

## Issues
You can submit your issues in our Github repository issues(https://github.com/PrismaProject/Prisma/issues).

## License
This project is governed by the [MPL Mozilla Public License v2.0] (LICENSE). This only works for source code directly in this clean repository.

For those who are not familiar with the open source agreement, the following is an overview of the agreement, you can also read the full agreement, but this is not a legal regulation and has no legal force

The project is completely or partially free or even commercial. However, please consider the following:

- **If you modify the source code of MPL or derive new code, and release the file as source code, all modified and derived code must also follow the MPL license. **
- **If your own source code accesses the MPL source code and class library through a dedicated interface, the code containing the dedicated interface must comply with the MPL license, but your own source code does not need to comply with the MPL license. **
- **You can obtain a patent license in the MPL code, but you cannot use our original icons. **

To achieve the above points, you can also privatize part of your code, or make it public, but you must have the MPL open source agreement, just like us.

## Setting up a Workspace
LiquidBounce is using Gradle, so make sure that it is installed properly. Instructions can be found on [Gradle's website](https://gradle.org/install/).
1. Clone the repository using `git clone https://github.com/CuteNatsu/Prisma-1.12.2.git`. 
2. CD into the local repository folder.
3. Depending on which IDE you are using execute either of the following commands:
    - For IntelliJ: `gradlew --debug setupDevWorkspace idea genIntellijRuns build`
    - For Eclipse: `gradlew --debug setupDevWorkspace eclipse build`
4. Open the folder as a Gradle project in your IDE.
5. Select either the Forge or Vanilla run configuration.

## Additional libraries
### Mixins
Mixins can be used to modify classes at runtime before they are loaded. LiquidBounce is using it to inject its code into the Minecraft client. This way, we do not have to ship Mojang's copyrighted code. If you want to learn more about it, check out its [Documentation](https://docs.spongepowered.org/5.1.0/en/plugin/internals/mixins.html).

## Final summary

The Prisma client is developed based on LiquidBounce, we abide by the MPL open source agreement, but we still hope that you can support the development of LiquidBounce.
Donate link: https://liquidbounce.net/donate

Prisma development team, presented
