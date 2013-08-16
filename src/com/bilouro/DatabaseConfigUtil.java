package com.bilouro;

import com.j256.ormlite.android.apptools.OrmLiteConfigUtil;

/**
 * Created with IntelliJ IDEA.
 * User: bilouro
 * Date: 15/08/13
 * Time: 21:17
 * To change this template use File | Settings | File Templates.
 */

public class DatabaseConfigUtil extends OrmLiteConfigUtil {
    public static void main(String[] args) throws Exception {
        writeConfigFile("ormlite_config.txt");
    }
}