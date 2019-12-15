// in order to use this in c we are coiding this in java because it's easier and faster
import java.text.DecimalFormat;
import java.util.*;
public class Note
{
    private String format = "#.####"; // this formats the output result.
    private DecimalFormat formater = new DecimalFormat(format);
    private String name; // name of the note
    private double time = 0; // the first tick mark
    private double time2 = 0; // the second tick mark
    private int keyValue; // the key value
    private int velocity; // how hard the note needs to be played
    private double base;
    private double tempo;
    private static Hashtable noteValue;
    double beats = 0;
     private static DecimalFormat df2 = new DecimalFormat("#.##");
    public Note(String input, int base)
    {
        name = input; // input the name to the input string
        this.base = base;
    }
    
    

    public String getName()
    {
        return name; // return the name
    }

    public double getTime()
    {
        return (time2)-(time); // subtract time 2 from time 1 giving us the amount of beats
    }

    public void setTime(double input)
    {
        time = input; // sets the first beat
    }

    public void setTime2(double input)
    {
        time2=input; // sets the secon beat
    }

    public int getKeyValue()
    {
        return keyValue; // the key value we will need to convert to a frequency
    }

    public void setKeyValue(int input)
    {
        keyValue = input; // sets the key value of the note frequency
    }

    public void setVelocity(int input)
    {
        velocity = input; // the the velocity of the note
    }

    public int getVelocity()
    {
        return velocity; // return the velocity of the note.
    }

    public double getTime1() // get when the time starts
    {
        return time;
    }

    public double getTime2() // get when the notes ends
    {
        return time2;
    }

    public void setBase(int base2) // what a quater note is equal too
    {
        this.base = base2;
    }

    public void setTempo(double tempo2) // set the tempo of the note 
    {
        this.tempo = tempo2;
    }

    public double getTimeFrame() // get the time from using the tempo and duration
    {
        return((getTime() * tempo)/ (1000 * 60));
    }

    @Override
    public String toString()
    {
        double timeFrame = ((base * tempo) / 60000); // get the time frame using the base 
       // check which value it lines up with
        if((getTimeFrame() <= ((timeFrame / 4) + 0.1) && getTimeFrame() >= ((timeFrame / 4) - 0.1)))
        {
            beats = .25;
        }
        if((getTimeFrame() <= ((timeFrame / 2) + 0.1) && getTimeFrame() >= ((timeFrame / 2) - 0.1)))
        {
            beats = .5;
        }
        if((getTimeFrame() <= ((timeFrame * .75) + 0.1) && getTimeFrame() >= ((timeFrame * .75) - 0.1)))
        {
            beats = .75;
        }
        else if(getTimeFrame() == timeFrame) // this is a get time it see how many beats are needed in the note
        {
            beats = 1;
        }
        else if((getTimeFrame() <= ((timeFrame * 1.5) + 0.1) && getTimeFrame() >= ((timeFrame * 1.5) - 0.1)))
        {
            beats = 1.5;
        }
        else if(getTimeFrame() <= ((timeFrame * 2) + 0.1) && getTimeFrame() >= ((timeFrame * 2) - 0.1))
        {
            beats = 2;
        }
        else if(getTimeFrame() <= timeFrame * 3 + 0.1 && getTimeFrame() >= timeFrame * 3 - 0.1)
        {
            beats = 3;
        }
        else if(getTimeFrame() <= timeFrame * 4 + 0.1 && getTimeFrame() >= timeFrame * 4 - 0.1)
        {
            beats = 4;
        }
        else if(getTimeFrame() <= timeFrame * 5 + 0.1 && getTimeFrame() >= timeFrame * 5 - 0.1)
        {
            beats = 5;
        }
        else if(getTimeFrame() <= timeFrame * 6 + 0.1 && getTimeFrame() >= timeFrame * 6 - 0.1)
        {
            beats = 6;
        }
        else if(getTimeFrame() <= timeFrame * 7 + 0.1 && getTimeFrame() >= timeFrame * 7 - 0.1)
        {
            beats = 7;
        }
        else if(getTimeFrame() <= timeFrame * 8 + 0.1 && getTimeFrame() >= timeFrame * 8 - 0.1)
        {
            beats = 8;
        }

        String result = "N" + getKeyValue() + "" + beats + "" + getVelocity() + ""; // the string method of the note
        return result;
    }
}