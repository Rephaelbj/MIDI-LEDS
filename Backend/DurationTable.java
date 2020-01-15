
public class DurationTable {
	
	private double tempo; // this is to calculate all the note timing based on the tempo
	private double PPQ = 0; // the pulses per quarter 
	
	
	// Make a new duration table
	public DurationTable()
	{
		
	}
	
	// Set the tempo of the duration table
	public void setTempo(double newTempo)
	{
		tempo = newTempo;
	}
	
	// Set the PPQ of the duration table
	public void setPPQ(double newPPQ)
	{
		PPQ = newPPQ;
	}
	
	public void findDuration(Note note)
	{
        double timeFrame = ((note.getBase() * note.getTempo()) / 60000); // get the time frame using the base 
        // check which value it lines up with
        if((note.getTimeFrame() <= ((timeFrame / 4) + 0.1) && note.getTimeFrame() >= ((timeFrame / 4) - 0.1)))
        {
            note.setBeats(.25);
        }
        if((note.getTimeFrame() <= ((timeFrame / 2) + 0.1) && note.getTimeFrame() >= ((timeFrame / 2) - 0.1)))
        {
            note.setBeats(.5);
        }
        if((note.getTimeFrame() <= ((timeFrame * .75) + 0.1) && note.getTimeFrame() >= ((timeFrame * .75) - 0.1)))
        {
            note.setBeats(.75);
        }
        else if(note.getTimeFrame() == timeFrame) // this is a get time it see how many beats are needed in the note
        {
            note.setBeats(1);
        }
        else if((note.getTimeFrame() <= ((timeFrame * 1.5) + 0.1) && note.getTimeFrame() >= ((timeFrame * 1.5) - 0.1)))
        {
            note.setBeats(1.5);
        }
        else if((note.getTimeFrame() <= ((timeFrame * 1.75) + 0.1) && note.getTimeFrame() >= ((timeFrame * 1.75) - 0.1)))
        {
            note.setBeats(1.75);
        }
        else if(note.getTimeFrame() <= ((timeFrame * 2) + 0.1) && note.getTimeFrame() >= ((timeFrame * 2) - 0.1))
        {
            note.setBeats(2);
        }
        else if((note.getTimeFrame() <= ((timeFrame * 2.25) + 0.1) && note.getTimeFrame() >= ((timeFrame * 2.25) - 0.1)))
        {
            note.setBeats(2.25);
        }
        else if((note.getTimeFrame() <= ((timeFrame * 2.5) + 0.1) && note.getTimeFrame() >= ((timeFrame * 2.5) - 0.1)))
        {
            note.setBeats(2.5);
        }
        else if((note.getTimeFrame() <= ((timeFrame * 2.75) + 0.1) && note.getTimeFrame() >= ((timeFrame * 2.75) - 0.1)))
        {
            note.setBeats(2.75);
        }
        else if(note.getTimeFrame() <= timeFrame * 3 + 0.1 && note.getTimeFrame() >= timeFrame * 3 - 0.1)
        {
            note.setBeats(3);
        }
        else if((note.getTimeFrame() <= ((timeFrame * 3.25) + 0.1) && note.getTimeFrame() >= ((timeFrame * 3.25) - 0.1)))
        {
            note.setBeats(3.25);
        }
        else if((note.getTimeFrame() <= ((timeFrame * 3.5) + 0.1) && note.getTimeFrame() >= ((timeFrame * 3.5) - 0.1)))
        {
            note.setBeats(3.5);
        }
        else if((note.getTimeFrame() <= ((timeFrame * 3.75) + 0.1) && note.getTimeFrame() >= ((timeFrame * 3.75) - 0.1)))
        {
            note.setBeats(3.75);
        }
        else if(note.getTimeFrame() <= timeFrame * 4 + 0.1 && note.getTimeFrame() >= timeFrame * 4 - 0.1)
        {
            note.setBeats(4);
        }
        else if((note.getTimeFrame() <= ((timeFrame * 4.25) + 0.1) && note.getTimeFrame() >= ((timeFrame * 4.25) - 0.1)))
        {
            note.setBeats(4.25);
        }
        else if((note.getTimeFrame() <= ((timeFrame * 4.5) + 0.1) && note.getTimeFrame() >= ((timeFrame * 4.5) - 0.1)))
        {
            note.setBeats(4.5);
        }
        else if((note.getTimeFrame() <= ((timeFrame * 4.75) + 0.1) && note.getTimeFrame() >= ((timeFrame * 4.75) - 0.1)))
        {
            note.setBeats(4.75);
        }
        else if(note.getTimeFrame() <= timeFrame * 5 + 0.1 && note.getTimeFrame() >= timeFrame * 5 - 0.1)
        {
            note.setBeats(5);
        }
        else if((note.getTimeFrame() <= ((timeFrame * 5.25) + 0.1) && note.getTimeFrame() >= ((timeFrame * 5.25) - 0.1)))
        {
            note.setBeats(5.25);
        }
        else if((note.getTimeFrame() <= ((timeFrame * 5.5) + 0.1) && note.getTimeFrame() >= ((timeFrame * 5.5) - 0.1)))
        {
            note.setBeats(5.5);
        }
        else if((note.getTimeFrame() <= ((timeFrame * 5.75) + 0.1) && note.getTimeFrame() >= ((timeFrame * 5.75) - 0.1)))
        {
            note.setBeats(5.75);
        }
        else if(note.getTimeFrame() <= timeFrame * 6 + 0.1 && note.getTimeFrame() >= timeFrame * 6 - 0.1)
        {
            note.setBeats(6);
        }
        else if((note.getTimeFrame() <= ((timeFrame * 6.25) + 0.1) && note.getTimeFrame() >= ((timeFrame * 6.25) - 0.1)))
        {
            note.setBeats(6.25);
        }
        else if((note.getTimeFrame() <= ((timeFrame * 6.5) + 0.1) && note.getTimeFrame() >= ((timeFrame * 6.5) - 0.1)))
        {
            note.setBeats(6.5);
        }
        else if((note.getTimeFrame() <= ((timeFrame * 6.75) + 0.1) && note.getTimeFrame() >= ((timeFrame * 6.75) - 0.1)))
        {
            note.setBeats(6.75);
        }
        else if(note.getTimeFrame() <= timeFrame * 7 + 0.1 && note.getTimeFrame() >= timeFrame * 7 - 0.1)
        {
            note.setBeats(7);
        }
        else if((note.getTimeFrame() <= ((timeFrame * 7.25) + 0.1) && note.getTimeFrame() >= ((timeFrame * 7.25) - 0.1)))
        {
            note.setBeats(7.25);
        }
        else if((note.getTimeFrame() <= ((timeFrame * 7.5) + 0.1) && note.getTimeFrame() >= ((timeFrame * 7.5) - 0.1)))
        {
            note.setBeats(7.5);
        }
        else if((note.getTimeFrame() <= ((timeFrame * 7.75) + 0.1) && note.getTimeFrame() >= ((timeFrame * 7.75) - 0.1)))
        {
            note.setBeats(7.75);
        }
        else if(note.getTimeFrame() <= timeFrame * 8 + 0.1 && note.getTimeFrame() >= timeFrame * 8 - 0.1)
        {
            note.setBeats(8);
        }
        else if((note.getTimeFrame() <= ((timeFrame * 8.25) + 0.1) && note.getTimeFrame() >= ((timeFrame * 8.25) - 0.1)))
        {
            note.setBeats(8.25);
        }
        else if((note.getTimeFrame() <= ((timeFrame * 8.5) + 0.1) && note.getTimeFrame() >= ((timeFrame * 8.5) - 0.1)))
        {
            note.setBeats(8.5);
        }
        else if((note.getTimeFrame() <= ((timeFrame * 8.75) + 0.1) && note.getTimeFrame() >= ((timeFrame * 8.75) - 0.1)))
        {
            note.setBeats(8.75);
        }
	}

}
