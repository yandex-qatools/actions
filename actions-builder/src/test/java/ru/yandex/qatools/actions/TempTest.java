package ru.yandex.qatools.actions;

import org.junit.AfterClass;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * @author Artem Eroshenko eroshenkoam
 *         6/3/13, 8:31 PM
 */
public class TempTest {

    private static File file = new File("actions.xml");

    @Test
    public void testOutput() throws FileNotFoundException {
        Actions actions = new Actions();
        actions.resizeWindow(1, 2);
        actions.write(file);
        actions.read(file);
    }

    @AfterClass
    public static void clean() {
        file.delete();
    }
}
