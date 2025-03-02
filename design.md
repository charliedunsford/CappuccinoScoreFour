# Design and Project Notes

* Obfuscate final JAR file for security (ProGuard or JObfuscate).
* Make Test Mode gui frame DISPOSE_ON_CLOSE instead of completely exit program.
* Try-Catch main method and privatize parsing game mode for increased security.
* For visual in-game UI: small center box will contain a blinking black arrow pointing towards the current colours turn; the large empty boxes on either side of the beads will contain the adjacent beads score.
* For final production version replace all File instances and replace with InputStreams, as they will not look for resources packaged in the JAR.
* In-game overlay expands when hovered over to show game options.
* Gifs must be unoptimized for proper functionality via Coalesce https://ezgif.com/optimize
* MouseClicked or MousePressed/MouseReleased combo? Is there any benefit to not using Clicked?
