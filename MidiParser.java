import java.io.File;

import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;
import javax.sound.midi.*;
import java.util.*;

public class MidiParser {
    public static final int NOTE_ON = 0x90; // a note on message
    public static final int NOTE_OFF = 0x80; // a note off message 
    public static final String[] NOTE_NAMES = {"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"}; // the notes names
    public static ArrayList<Note> Notes = new ArrayList<Note>();
    public static ArrayList<Chord> Chords = new ArrayList<Chord>();
    private static int tempo = 0;
    private static double multipler = 1;
    private static Hashtable noteValue;
    private static int PPQ;
    public static void main(String[] args) throws Exception {       
        String fileName; // the name of the file
        if(args.length > 0) // if the aguments length != 0 then try to get the name of the file we want to read from
        {
            fileName = args[0]; // the name is in the first position
        }
        else
        {
            fileName = "Online2.mid"; // else this is the default
        }
        // this code was found online and modified extremly 
        Sequence sequence = MidiSystem.getSequence(new File(fileName)); // put the file into the midi sequencer
        int trackNumber = 0; // the track number 
        PPQ = sequence.getResolution();

        for (Track track :  sequence.getTracks()) {
            trackNumber++; // increase the track number each time we go through
            int ticksPerSecond = sequence.getResolution(); // the ticks per second 
            for (int i=0; i < track.size(); i++) { 
                MidiEvent event = track.get(i); // get the track number 
                MidiMessage message = event.getMessage(); // check what type of message the message is 
                if (message instanceof ShortMessage) {
                    ShortMessage sm = (ShortMessage) message; // if it's a short message
                    if (sm.getCommand() == NOTE_ON) { // check if it's equal to note off
                        int key = sm.getData1(); // get the key data
                        int octave = (key / 12)-1; // the octave is the key divided by 12 and subtract 1
                        int note = key % 12; // the note, which is a multiple of 12
                        String noteName = NOTE_NAMES[note]; // get the String of the value
                        int velocity = sm.getData2(); // get the velocity of the note
                        if(velocity != 0) // if the velocity is not zero, the note is on
                        {
                            Note newNote; // make a new note
                            if(PPQ == 480)
                            {
                                newNote = new Note(noteName+Integer.toString(octave), 455); // create a new note with the name and the octave 
                            }
                            else
                            {
                                newNote = new Note(noteName+Integer.toString(octave), 384);
                            }
                            newNote.setTempo(tempo); // set the tempo of the note
                            newNote.setKeyValue(key); // the key value
                            newNote.setTime((double)event.getTick()); // set the time as the tick mark
                            newNote.setVelocity(velocity); // the velocity of the notes
                            Notes.add(newNote); // add the note to the array
                            // System.out.println("TICK IS " + event.getTick());

                        }
                        else // this is the note off message because the velocity is zero
                        {
                            for(int c = 0; c < Notes.size(); c++) //
                            {
                                if(Notes.get(c).getKeyValue() == key)
                                {
                                    if(Notes.get(c).getTime2() == 0) // this is for repeating notes so that their values are not over written
                                    {
                                        Notes.get(c).setTime2((double)event.getTick());
                                        //System.out.println("TICK off IS " + event.getTick());
                                    }
                                }
                            }
                        }

                    } else if (sm.getCommand() == NOTE_OFF) {
                        int key = sm.getData1(); // get the key data
                        for(int c = 0; c < Notes.size(); c++)
                        {
                            if(Notes.get(c).getKeyValue() == key)
                            {
                                if(Notes.get(c).getTime2() == 0) // this is for repeating notes so that their values are not over written
                                {
                                    Notes.get(c).setTime2((double)event.getTick());
                                    //System.out.println("OFF" + event.getTick());
                                }
                            }
                        }
                    }
                } else {
                    if(message instanceof MetaMessage)
                    {
                        MetaMessage meta = (MetaMessage)message;
                        if(meta.getType() == 81) // this gets the tempo of the song
                        {
                            byte[] hexNums = meta.getData();
                            String HexValue = "";
                            for(int w = 0 ; w < hexNums.length; w++)
                            {
                                HexValue += String.format("%02x", hexNums[w]);
                            } 
                            tempo = (60000000/Integer.parseInt(HexValue, 16));
                            System.out.println("T" + tempo + " ");
                        }
                    }
                }
            }
        }
        int row = 0;
        for(int i = 0; i < Notes.size(); i++, row++)
        {
            System.out.print(Notes.get(i).toString()); // print out the notes
            if(i < Notes.size() - 1)
            {
                double restValue = getRestValue(Notes.get(i), Notes.get(i+1)); // look for a rest between the notes
                if(restValue != -1)
                {
                    System.out.print("R" + "0" + restValue + "0 "); // print out the rest value
                }
            }   
        }
    }

    public static double rest(Note note1, Note note2) // this will find the  time of the rest between each note
    {
        int adder = 0; // how much we have to add,  this currectly only support 480
        double beat = PPQ;
        // this is to find out how much we add between notes, in the case of 480 its about 25 in 96 it's zero
        if(PPQ == 480)
        {
            if(note2.getTime1() / beat > 4)
            {
                double remain = note2.getTime();
                while(remain > 4)
                {
                    remain -= 4;
                }
                adder = -75;
            }

            if((note2.getTime1() / beat) == 1)
            {
                adder = -25;
            }
            if((note2.getTime1() / beat) == 2)
            {
                adder = -50;
            }
            if((note2.getTime1() / beat) == 3)
            {
                adder = -75;
            }
            if((note2.getTime1() / beat) == 4)
            {
                adder = -100;
            }
        }
        double restValue = note2.getTime1()- note1.getTime2()+adder; // add it the restvalue
        restValue = ((restValue * tempo)/ (1000 * 60)); // get the value with the tempo
        return restValue; // return it
    }

    public static double getRestValue(Note note1, Note note2)
    {
        double breakCondition = rest(note1,note2);
        double timeFrame2 = 0;
        // if the ppq is 480 or 384 get the correct time frame, currently only use 480
        if(PPQ == 480)
        {
            timeFrame2 = ((455.0 * tempo) / (1000 * 60));
        }
        else
        {
            timeFrame2 = ((384.0 * tempo) / (1000 * 60));
        }

        // find rest values are between .25 - 4
        // return -1 if no rest is found
        if(breakCondition > 0.1)
        {
            if(breakCondition <= (timeFrame2/2) + .125 && breakCondition >= (timeFrame2/2) - .125)
            {
                return .5;
            }
            if(breakCondition <= (timeFrame2/3) + .125 && breakCondition >= (timeFrame2/3) - .125) 
            {
                return .25;
            }
            if(breakCondition <= (timeFrame2/4) + .125 && breakCondition >= (timeFrame2/4) - .125)
            {
                return .125;
            }

            if(breakCondition <= timeFrame2 + .125 && breakCondition >= timeFrame2 - .125)
            {
                return 1;
            }
            if(breakCondition <= (timeFrame2*2) + .125 && breakCondition >= (timeFrame2*2) - .125)
            {
                return 2;
            }
            if(breakCondition <= (timeFrame2*3) + .125 && breakCondition >= (timeFrame2*3) - .125) 
            {
                return 3;
            }
            if(breakCondition <= (timeFrame2*4) + .125 && breakCondition >= (timeFrame2*4) - .125)
            {
                return 4;
            }
            return -1;
        }
        else
        {
            return -1;
        }

    }

}
