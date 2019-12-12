#include <stdio.h>
#include <dos.h>
#include <Windows.h>
#include <stdlib.h>
#include "MidiFile.h"
#include <math.h>



// delay in milliseconds = 60,000/tempo
// using the tempo, get the 8 values we need
float toFrequency(char * array); // convert to a frequency
int toMillie(char * array); // convert to milliseconds
int calculatePosition(float value); // calculate which register it should go to
float midi[127]; // all of the values we use for the frequency
char tempo[4]; // this is the default value 
float inter1, inter2, inter3, inter4, inter5, inter6; // the intervals of the LEDS
float min = 0; // the least
float max = 0; // the max frequency

int main()
{
	// variables 
	FILE *ptr_file; // pointer to the file
	char buf[3]; // the reading valeu
	float endArray[100000]; // the end values
	char noteDuration[10]; // note duration
	char noteValue[2]; // the note value
	char velocity[3]; // the velocity of the note
	int width = 0; // length
	float playnote; // the play we need to play
	int playDuration; // note duration
	int firstNote = 0; // how we tell if it's the first note or not for min and max
	// end of them

	for (int x = 0; x < 127; x++)
	{
		midi[x] = 27.5 * pow(2.0 , ((x - 21.0) / 12.0)); // make the array of frequencies
	}

	

	char command[50]; // char to hold the file name and the bat
	strcpy(command, "Testing.bat PokeCenter.mid"); // name of the file we want to read from
	system(command); // call the command line to do this 

	ptr_file = fopen("output.txt", "rb");  // try to open the file
	if (!ptr_file) // if we can't open it return 1
		return 1;

	while (fread(buf, 1, 1, ptr_file) != 0)
	{
		if (buf[0] == 'N') // this is a note
		{
			fread(noteValue, 2, 1, ptr_file); // the note value
			fread(noteDuration, 3, 1, ptr_file); // the duration of the note
			fread(velocity, 3, 1, ptr_file); // the velocity of the note, for pwm
			playnote = toFrequency(noteValue);
			// let it be known we haver the first note and set min and max
			if (firstNote == 0)
			{
				max = min = playnote;
				firstNote = 1;
			}
			if (playnote > max)
			{
				max = playnote;
			}
			if (playnote < min) 
			{
				min = playnote;
			}
			// replace min and max respectivly 
			endArray[width] = playnote;
			width++;

			if (atof(noteDuration) == 0.100000) // make float values
			{
				playDuration = toMillie("0.1250000");
			}
			else if (atof(noteDuration) == 0.200000)
			{
				playDuration = toMillie("0.250000"); 
			}
			else if (atof(noteDuration) == 0.700000)
			{
				playDuration = toMillie("0.75");
			}
			else
			{
				playDuration = toMillie(noteDuration);
			}
			// add to the end array
			endArray[width] = playDuration;
			width++;
			endArray[width] = toVelocity(velocity);
			width++;
		}
		if (buf[0] == 'R') // we found a rest
		{
			endArray[width] = 0;
			width++;
			fread(noteDuration, 1, 1, ptr_file);
			
			fread(noteDuration, 3, 1, ptr_file);
			// add float values 
			if (atof(noteDuration) == 0.100000)
			{
				playDuration = toMillie("0.125000000");
			}
			else if (atof(noteDuration) == 0.200000)
			{
				playDuration = toMillie("0.250000000");
			}
			else
			{
				playDuration = toMillie(noteDuration);
			}

			// add to the array 
			endArray[width] = playDuration;
			width++;
			endArray[width] = 0;
			fread(noteDuration, 1, 1, ptr_file);
			width++;
		}
		if (buf[0] == 'T') // the tempo marker
		{
			fread(tempo, 4, 1, ptr_file);
			printf("This is the tempo %d \n", atoi(tempo)); // we have the tempo
		}
	}

	fclose(ptr_file); // close the file 


	// print the array 
	int distance = (max - min) / 7; // divide the distance and add to the intervals
	inter1 = (distance * 1) + min; // calculate the intervals
	inter2 = (distance * 2) + min;
	inter3 = (distance * 3) + min;
	inter4 = (distance * 4) + min;
	inter5 = (distance * 5) + min;
	inter6 = (distance * 6) + min;
	printf("int values[%d] = {", width);
	
	// this prints a code snippet that is flash onto the atmega
	for (int i = 0; i < width; i++)
	{
		if (i % 3 == 0)
		{
			int run = calculatePosition(endArray[i]); // print where it should go
			printf("%d,", calculatePosition(endArray[i]));
			if (run > 7)
				printf("%f  problem \n", endArray[i]); // let's us know we have a problem
		}
		else
		{
			printf("%d,", (int)endArray[i]);
		}
		if ((i+1) % 3 == 0)
		{
			printf("\n");
		}
	}
	printf("}; \n int length =  %d;", width);
	return 0;
}



///METHODS
float toFrequency(char * array) // convert to frequenct
{
		
	return midi[atoi(array)];
}


int toMillie(char * array) // convert to millie seconds
{
	atof(array);
	return ((60000) / atoi(tempo)) * atof(array);
}

int toVelocity(char * array) // returnt the velocity 
{
	return atof(array);
}

int calculatePosition(float value) // find the position/ which register to go to 

{
	if (value == 0)
	{
		return -1;
	}
	if (value == min || (value > min && value < inter1))
	{
		return 0;
	}
	if ((value  > inter1 && value < inter2 || value == inter1))
	{
		return 1;
	}
	if ((value > inter2 && value < inter3) || value == inter2)
	{
		return 2;
	}
	if ((value > inter3 && value < inter4) || value == inter3)
	{
		return 3;
	}
	if ((value > inter4 && value < inter5) || value == inter4)
	{
		return 4;
	}
	if ((value > inter5 && value < inter6) || value == inter5)
	{
		return 5;
	}
	if ((value > inter6 && value < max) || value == inter6)
	{
		return 6;
	}
	if (value == max)
	{
		return 7;
	}
}


