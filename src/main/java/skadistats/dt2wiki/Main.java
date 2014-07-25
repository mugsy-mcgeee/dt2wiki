package skadistats.dt2wiki;

import java.util.*;

import skadistats.clarity.Clarity;
import skadistats.clarity.match.Match;
import skadistats.clarity.parser.PeekIterator;
import skadistats.clarity.parser.Profile;
import skadistats.clarity.model.DTClass;

import skadistats.dt2wiki.format.Basic;

public class Main {

    public static void main(String[] args) throws Exception {
        final Match match = new Match();
        PeekIterator iter = Clarity.peekIteratorForFile(args[0], Profile.SEND_TABLES);
        while(iter.hasNext()) {
            iter.next().apply(match);
        }

        DTMap dtTree = new TreeConstructor(match).construct();
        new TreeTraverser(new Basic()).toStdOut(dtTree);
    }
}
