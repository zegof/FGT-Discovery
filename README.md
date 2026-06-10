# FGT-Discovery
FGT Discovery is a Minecraft Paper plugin for interconnecting separate Servers without setting up complicated server software like BungeeCord or Velocity.
This works by using the transfer feature to send players executing `/join (server)` to another specified server via IP and Port.

## Config
The config is very self-descriptive and if you need any help, contact me on Discord, as `itsgraphax`!

## Tips
I can only reccommend pairing this plugin with [Citizens 2](https://citizensnpcs.co), a Minecraft plugin, that allows you to create NPCs that are clickable to execute the `/join` command or similar.

The command to make an npc execute a command is: `/npc command add /join <server> -p`

## FAQ
### I get "Transfers are not enabled on this servers" when joining another server!
This is a limitation with the transfer feature, as it uses the vanilla transferring that requires each server to have `allow-transfers` set to `true` in the `server.properties` as a security guard. (Which I tbh don't get, but it is what it is)
### Why does this FAQ only have one real FAQ?
Because I just recently published this plugin and it should be pretty self-understandeable.
