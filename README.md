# EVA BW project

## Descritpion
A little project based on the BWAPI Java wrapper.

## Installation instructions
The starter tutorial misses a few critical steps.

1. Install Starcraft 1.16.1 (1.18 does not work at this time)
2. Install the BWAPI 4.1.2 Release from Github: https://github.com/bwapi/bwapi/releases/download/v4.1.2/BWAPI_412_Setup.exe
3. Check whether Chaoslauncher works (update the Starcraft installation path)
4. Create a folder in Starcraft installation folder (bwapi-data) and copy the BWAPI.dll, initial configuration (bwapi.ini) and the mappping file to the Starcraft installation folder. The folder Structure should look like this:

* Starcraft
  * bwapi-data
    * data
      * Broodwar.map
    * bwapi.ini
    * BWAPI.dll
    
    
Otherwise, Chaoslauncher will freeze.

5. Ensure that the 4.1.2 bwapi injector RELEASE is lodaded (checked in Chaoslauncher)
6. Start Starcraft. It should be started without any problems.
7. Select a map, but don't start it.
8. You should have already set up the example project in Eclipse/other IDE. If not, the bwmirror.jar must be reachabe for the project. https://github.com/vjurenka/BWMirror
9. Start Java agent (example agent or your own one). Java agents can join without completely exiting the game thus speeds up agent development.

### Troubleshooting
- Ensure you are targeting a 32 bit Java environment. This is due to the fact, that Starcraft is a 32 bit application. With Eclipse the target JVM can be set up relatively easily and the target JVM can be changed afterwards.
- Ensure Chasolauncher runs with Administrator privileges
- Ensure that the

Starter tutorial:
http://sscaitournament.com/index.php?action=tutorial

## Final remarks
Have fun! :)
