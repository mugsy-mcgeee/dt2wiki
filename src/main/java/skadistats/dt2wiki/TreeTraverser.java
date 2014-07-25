package skadistats.dt2wiki;

import java.util.*;
import java.io.*;

import skadistats.dt2wiki.format.Formatter;
import skadistats.clarity.model.DTClass;

public class TreeTraverser {

    final private String dirPrefix = "dt2wiki-data/";
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

    public void writeDetails(DTMap dtMap) throws IOException {
        this.dtMap = dtMap;

        for (DTClass cls : dtMap.keySet()) {
            if (cls == null)
                continue;

            String dirName = dirPrefix + "SendTable(2f)" + cls.getDtName();
            new File(dirName).mkdirs();
            new File(dirName+"/revisions").mkdir();

            OutputStream fileOut = new FileOutputStream(dirName+"/current");
            fileOut.write("00000001\n".getBytes());
            fileOut.close();

            fileOut = new FileOutputStream(dirName+"/revisions/00000001");
            fileOut.write(fmt.dtDetails(cls).getBytes());
        }
    }

}
