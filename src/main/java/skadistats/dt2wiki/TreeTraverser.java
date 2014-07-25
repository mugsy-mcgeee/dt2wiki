package skadistats.dt2wiki;

import java.util.*;

import skadistats.dt2wiki.format.Formatter;
import skadistats.clarity.model.DTClass;

public class TreeTraverser {

    protected Formatter fmt;
    protected DTMap dtMap;

    public TreeTraverser(Formatter fmt) {
        this.fmt = fmt;
    }

    public void toStdOut(DTMap dtMap) {
        this.dtMap = dtMap;
        _toStdOut(dtMap.get(null), 0);
    }

    public void _toStdOut(Set<DTClass> peers, int level) {
        if (peers != null) {
            for (DTClass cls : peers) {
                System.out.println(fmt.dtName(cls, level));
                _toStdOut(dtMap.get(cls), level+1);
            }
        }
    }
}
