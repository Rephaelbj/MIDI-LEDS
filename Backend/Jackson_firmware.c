
#include <avr/io.h>		/* Include AVR std. library file */
#include <util/delay.h>		/* Include defined delay header file */
#include <stdio.h>		/* Include standard i/o library file */



void PWMinit() // initlaize pwm
{
		// Pin PB1 and 2
       TCCR1A |= (1 << COM1A1);
           // set non-inverting mode
       TCCR1A |= (1 << WGM11) | (1 << WGM10);
           // set 10bit phase corrected PWM Mode
	   TCCR1B |= (1 << CS11); 
	   
	                                    
		// PIN PD 5 and b
       TCCR0A |= (1 << COM0A1);
       // set none-inverting mode

       TCCR0A |= (1 << WGM01) | (1 << WGM00);
       // set fast PWM Mode

       TCCR0B |= (1 << CS01);
       // set prescaler to 8 and starts PWM
	   
	   		// Pin PB1 and 2
	   		TCCR2A |= (1 << COM2A1);
	   		// set non-inverting mode
	   		TCCR2A |= (1 << WGM21) | (1 << WGM20);
	   		// set 10bit phase corrected PWM Mode
	   		TCCR2B |= (1 << CS21);
}

/// THE ARRAY OF THE NOTES and the length
int values[447] = {3,451,80,
	2,451,80,
	1,451,80,
	2,451,80,
	1,451,80,
	1,451,80,
	1,451,80,
	1,112,580,
	1,112,510,
	1,112,510,
	2,112,510,
	3,902,100,
	-1,225,0,
	1,225,100,
	1,225,100,
	4,225,100,
	4,902,100,
	-1,225,0,
	3,451,100,
	2,225,100,
	3,1804,100,
	3,451,80,
	2,451,80,
	1,451,80,
	-1,56,0,
	1,112,510,
	1,112,510,
	2,112,510,
	3,902,100,
	-1,225,0,
	1,225,100,
	1,225,100,
	4,225,100,
	5,676,100,
	4,676,100,
	4,451,100,
	3,1804,100,
	5,112,580,
	5,112,580,
	5,112,580,
	5,112,580,
	5,112,580,
	5,112,580,
	5,112,580,
	5,451,80,
	5,112,580,
	1,112,510,
	1,112,510,
	1,225,100,
	0,338,510,
	1,112,510,
	-1,225,0,
	1,225,100,
	-1,225,0,
	0,225,100,
	0,225,100,
	1,225,100,
	1,338,510,
	0,338,510,
	1,225,100,
	-1,451,0,
	1,112,510,
	1,112,510,
	1,225,100,
	0,338,510,
	1,112,510,
	-1,225,0,
	1,225,100,
	-1,225,0,
	0,225,100,
	1,225,100,
	1,225,100,
	0,338,510,
	1,338,510,
	1,451,100,
	0,225,100,
	0,225,100,
	1,225,100,
	1,338,510,
	0,338,510,
	1,676,100,
	1,112,510,
	1,112,510,
	1,225,100,
	0,112,510,
	1,225,100,
	3,112,510,
	2,112,510,
	2,112,510,
	1,225,100,
	4,112,510,
	4,225,100,
	3,112,510,
	1,112,510,
	1,225,100,
	1,112,510,
	0,112,510,
	1,225,100,
	0,112,510,
	1,225,100,
	0,112,510,
	1,225,100,
	0,112,510,
	1,225,100,
	0,112,510,
	1,112,510,
	1,225,100,
	3,676,100,
	2,902,100,
	1,112,510,
	2,112,510,
	2,338,510,
	2,338,510,
	1,225,100,
	1,338,510,
	1,338,510,
	2,225,100,
	3,676,100,
	5,676,80,
	1,225,100,
	1,225,100,
	1,338,510,
	2,338,510,
	1,225,100,
	1,0,100,
	-1,112,0,
	4,338,510,
	3,225,100,
	3,676,100,
	1,902,100,
	1,112,510,
	2,112,510,
	2,338,510,
	2,338,510,
	1,225,100,
	1,338,510,
	1,338,510,
	2,225,100,
	3,676,100,
	5,676,100,
	5,225,100,
	6,225,100,
	7,338,510,
	3,338,510,
	3,225,100,
	2,338,510,
	1,338,510,
	2,225,100,
	1,1804,100,
};
int length =  447;


int main(void)
{
DDRB = 0b00000001; // set to output
PWMinit();
PORTB = 0b00000001; // turn on one

_delay_ms(1000); // wait one milie second
PORTB = 0b00000000; // turn it off
_delay_ms(1000); // wait one millie
while(1)
{
	// loop through the array
	for(int i = 0; i < length; i++)
	{
		if(i % 3 == 0)
		{
			changeBrightness(values[i+2]); // change the brightness
			turnOnLED(values[i]); // turn on the register
			delay_ms(values[i+1]); // wait that many milie seconds
			
			DDRB = 0b00000000;
			DDRD = 0b00000000;
			PORTB= 0b00000000; // turn on les off
			PORTD =0b00000000;
			
			_delay_ms(4); // short delay to see repeating notes;
		}
	}

	DDRB = 0b00000011;
	PORTB = 0b00000011; // turn two on to show that it's done
	_delay_ms(10000);
}
return 0;

}

void turnOnLED(int a) // switch to which register it is on and only allow that one to be enabled 
{
	
	switch(a)
	{
		case -1:
		DDRB= 0b00000000;
		PORTB = 0b00000000;
		break;
		case 0:
		DDRB = 0b00000001;
		PORTB = 0b00000001;
		break;
		case 1:
		DDRB = 0b00000010;
		PORTB = 0b00000010;
		break;
		case 2:
		DDRB = 0b00000100;
		PORTB = 0b00000100;
		break;
		case 3:
		DDRB = 0b00001000;
		PORTB = 0b00001000;
		break;
		case 4:
		DDRB = 0b00010000;
		PORTB = 0b00010000;
		break;
		case 5:
		DDRD = 0b00100000;
		PORTD = 0b00100000;
		break;
		case 6:
		DDRD = 0b01000000;
		PORTD = 0b01000000;
		break;
		case 7:
		DDRB = 0b10000000;
		PORTB = 0b10000000;
		break;
		
		
	}
}

void delay_ms(int ms) //delay this many 
{
	while (ms--) {
		_delay_us(1000);  // one millisecond
	}
}

void changeBrightness(int velocity) // this changes the brightness of every PWM enabled registor
{
	OCR1A = velocity;
	OCR1B = velocity;
	OCR0A = velocity;
	OCR0B = velocity;
	OCR2A = velocity;
	OCR2B = velocity;
	
	
}
