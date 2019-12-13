# MIDI-LEDS
Midi file convertor to LEDS
How to use:

Step 1: Get a simple midi file, meaning one instrument and only one note at a time. i.e. twinkle twinkle little star, this program can't do any Nocturne Opus 9 no 2 yet, in addition use musescore and export the old midi file(if using one from the internet) to a midi file; the reason for this is because ofdifferent PPQs which is plus per quasar, this one only currently supports 480 which musescore does naturally.

Step 2: Change the name from PokeCenter.mid to the name of the file you'd like to read from found on line 43 of MidiToArray.c

Step 3: Run the C file, you'll get an array with the following information: {LED_to_lgiht_up, Duration, Velocity}. This is currently meant to light up one LED at a time, however depending on the implementation it can do other things. The code to light up one at a time can be seen in Jackson_firmware.c. LED_to_light_up can occasionally be -1 meaning this is a rest and no LEDS should be on. The duration is how long the note will light up on the LED. The velocity is how bright the led should be.

Step 4: Paste the output from the MiditoArray.c file into the firmware of your code and implement accordingly
